package ru.clevertec.house.dao.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.clevertec.house.dao.HouseHistoryDAO;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HouseHistoryDAOImpl implements HouseHistoryDAO {

    @Autowired
    private final SessionFactory sessionFactory;

    @Override
    public List<UUID> getAllPersonsUuidLivedInHouse(UUID houseId) {

        String selectHQL = "select uuid from HouseHistory where house_id=:uuid and type=: TENANT";

        Session session = sessionFactory.getCurrentSession();
        Query<UUID> query = session.createQuery(selectHQL, UUID.class);
        query.setParameter("uuid", houseId);
        List<UUID> uuidList = query.getResultList();
        return uuidList;
    }

    @Override
    public List<UUID> getAllPersonsUuidOwnedHouse(UUID houseId) {
        String selectHQL = "select uuid from HouseHistory where house_id=:uuid and type=: OWNER";

        Session session = sessionFactory.getCurrentSession();
        Query<UUID> query = session.createQuery(selectHQL, UUID.class);
        query.setParameter("uuid", houseId);
        List<UUID> uuidList = query.getResultList();
        return uuidList;
    }

    @Override
    public List<UUID> getAllHousesUuidWherePersonLived(UUID personId) {

        String selectHQL = "select uuid from HouseHistory where person_id=:uuid and type=: TENANT";

        Session session = sessionFactory.getCurrentSession();
        Query<UUID> query = session.createQuery(selectHQL, UUID.class);
        query.setParameter("uuid", personId);
        List<UUID> uuidList = query.getResultList();
        return uuidList;
    }

    @Override
    public List<UUID> getAllHousesUuidWhichPersonOwned(UUID personId) {

        String selectHQL = "select uuid from HouseHistory where person_id=:uuid and type=: OWNER";
        Session session = sessionFactory.getCurrentSession();
        Query<UUID> query = session.createQuery(selectHQL, UUID.class);
        query.setParameter("uuid", personId);
        List<UUID> uuidList = query.getResultList();
        return uuidList;
    }
}
