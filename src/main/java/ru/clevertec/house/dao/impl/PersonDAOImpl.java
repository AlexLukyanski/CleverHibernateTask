package ru.clevertec.house.dao.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.house.dao.PersonDAO;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.NotFoundException;
import ru.clevertec.house.mapper.JdbcPersonMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private final SessionFactory sessionFactory;
    private final JdbcTemplate jdbcTemplate;
    private final JdbcPersonMapper personMapper;

    @Override
    public UUID save(Person person) {

        String selectPersonUuidHQL = "select uuid from Person where passport_series=:passport_series and passport_number=:passport_number";

        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
        Query<UUID> query = session.createQuery(selectPersonUuidHQL, UUID.class);
        query.setParameter("passport_series", person.getPassportSeries());
        query.setParameter("passport_number", person.getPassportNumber());
        UUID uuid = query.uniqueResult();
        return uuid;
    }

    @Override
    public Optional<Person> getById(UUID uuid) {
        String selectPersonByUUID = "select id,uuid,name,surname,sex,passport_series,passport_number," +
                "create_date, update_date from \"Person\" where uuid = ?";
        Object[] args = {uuid.toString()};
        Person person = jdbcTemplate.queryForObject(selectPersonByUUID,personMapper,args);
        return Optional.of(person);
    }

    @Override
    public List<Person> getAll(int pageNumber, int pageSize) {

        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("from Person", Person.class);
        List<Person> persons = query.getResultList().stream()
                .skip(pageSize * pageNumber - pageSize)
                .limit(pageSize * pageNumber + pageSize)
                .toList();

        return persons;
    }

    @Override
    public UUID update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person updatedPerson = session.merge(person);
        UUID uuid = updatedPerson.getUuid();
        return uuid;
    }

    @Override
    public void delete(UUID uuid) {
        Optional<Person> optionalPerson = getById(uuid);
        Person person = optionalPerson.orElseThrow(() -> NotFoundException.of(Person.class, uuid));
        Session session = sessionFactory.getCurrentSession();
        session.remove(person.getId());
    }

    @Override
    public List<House> getOwnedHouses(UUID uuid) {
        String selectOwnedHousesByUUIDHQL = "select ownedHouses from Person where uuid=:uuid";
        Session session = sessionFactory.getCurrentSession();
        Query<House> query = session.createQuery(selectOwnedHousesByUUIDHQL, House.class);
        query.setParameter("uuid", uuid);
        List<House> ownedHouses = query.getResultList();
        return ownedHouses;
    }
}
