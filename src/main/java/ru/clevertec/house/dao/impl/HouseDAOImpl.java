package ru.clevertec.house.dao.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.clevertec.house.dao.HouseDAO;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HouseDAOImpl implements HouseDAO {

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public UUID save(House house) {

        String selectHouseUuidHQL = "select uuid from House where country=:country and city=:city" +
                " and street=:street and number=:number";

        Session session = sessionFactory.getCurrentSession();
        session.persist(house);
        Query<UUID> query = session.createQuery(selectHouseUuidHQL, UUID.class);
        query.setParameter("country", house.getCountry());
        query.setParameter("city", house.getCity());
        query.setParameter("street", house.getStreet());
        query.setParameter("number", house.getNumber());
        UUID uuid = query.uniqueResult();
        return uuid;
    }

    @Override
    public Optional<House> getById(UUID uuid) {
        String selectHouseByUUIDHQL = "from House where uuid=:uuid";
        Session session = sessionFactory.getCurrentSession();
        Query<House> query = session.createQuery(selectHouseByUUIDHQL, House.class);
        query.setParameter("uuid", uuid);

        Optional<House> optionalHouse = Optional.of(query.getSingleResult());
        return optionalHouse;
    }

    @Override
    public List<House> getAll(int pageNumber, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        Query<House> query = session.createQuery("from House", House.class);
        List<House> houses = query.getResultList().stream()
                .skip(pageSize * pageNumber - pageSize)
                .limit(pageSize * pageNumber + pageSize)
                .toList();

        return houses;
    }

    @Override
    public UUID update(House house) {
        Session session = sessionFactory.getCurrentSession();
        House updatedHouse = session.merge(house);
        UUID uuid = updatedHouse.getUuid();
        return uuid;
    }

    @Override
    public void delete(UUID uuid) {
        Optional<House> optionalHouse = getById(uuid);
        House house = optionalHouse.orElseThrow(() -> NotFoundException.of(House.class, uuid));
        Session session = sessionFactory.getCurrentSession();
        session.remove(house.getId());
    }

    @Override
    public List<Person> getAllResidents(UUID uuid) {
        String selectResidentsByUUIDHQL = "select residents from House where uuid=:uuid";
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery(selectResidentsByUUIDHQL, Person.class);
        query.setParameter("uuid", uuid);
        List<Person> residents = query.getResultList();
        return residents;
    }
}
