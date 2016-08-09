package com.magneto.data.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.magneto.data.dataobject.ConnectionStatus;
import com.magneto.data.entity.UserEntity;
import org.springframework.stereotype.Repository;

import com.magneto.data.entity.UserConnectionEntity;


@Repository("userConnectionRepository")
public class UserConnectionRepositoryImpl implements UserConnectionRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    public void setNewConnectionRequest(int userOne, int userTwo) {
        ConnectionStatus conStatus = this.getUserConnectionStatus(userOne, userTwo);
        if (conStatus == ConnectionStatus.DISCONNECTED) {
            UserConnectionEntity connectionEntity = new UserConnectionEntity();
            UserEntity uOne = new UserEntity();
            UserEntity uTwo = new UserEntity();
            uOne.setId(userOne);
            uTwo.setId(userTwo);
            connectionEntity.setUserOne(uOne);
            connectionEntity.setUserTwo(uTwo);
            connectionEntity.setConnectionStatus(ConnectionStatus.PENDING);
            em.persist(connectionEntity);
        }
    }

    @Override
    public void acceptConnectionRequest(int userOne, int userTwo) {
        ConnectionStatus conStatus = this.getUserConnectionStatus(userOne, userTwo);
        if (conStatus == ConnectionStatus.PENDING) {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<UserConnectionEntity> criteria = builder.createQuery(UserConnectionEntity.class);
            Root<UserConnectionEntity> userConnection = criteria.from(UserConnectionEntity.class);

            criteria.select(userConnection)
                    .where(builder.or(
                            builder.and(builder.equal(userConnection.get("userOne"), userOne),
                                    builder.equal(userConnection.get("userTwo"), userTwo)),
                            builder.and(builder.equal(userConnection.get("userOne"), userTwo),
                                    builder.equal(userConnection.get("userTwo"), userOne))));

            TypedQuery<UserConnectionEntity> query = em.createQuery(criteria);

            UserConnectionEntity oldPendingConnection = query.getSingleResult();
            oldPendingConnection.setConnectionStatus(ConnectionStatus.CONNECTED);

            UserConnectionEntity connectionEntity = new UserConnectionEntity();
            UserEntity uOne = new UserEntity();
            UserEntity uTwo = new UserEntity();
            uOne.setId(userOne);
            uTwo.setId(userTwo);
            connectionEntity.setUserOne(uOne);
            connectionEntity.setUserTwo(uTwo);
            connectionEntity.setConnectionStatus(ConnectionStatus.CONNECTED);
            em.persist(connectionEntity);
            em.merge(oldPendingConnection);
        }
    }

    @Override
    public void rejectConnectionRequest(int userOne, int userTwo) {
        ConnectionStatus conStatus = this.getUserConnectionStatus(userOne, userTwo);
        if (conStatus == ConnectionStatus.PENDING) {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<UserConnectionEntity> criteria = builder.createQuery(UserConnectionEntity.class);
            Root<UserConnectionEntity> userConnection = criteria.from(UserConnectionEntity.class);

            criteria.select(userConnection)
                    .where(builder.or(
                            builder.and(builder.equal(userConnection.get("userOne"), userOne),
                                    builder.equal(userConnection.get("userTwo"), userTwo)),
                            builder.and(builder.equal(userConnection.get("userOne"), userTwo),
                                    builder.equal(userConnection.get("userTwo"), userOne))));

            TypedQuery<UserConnectionEntity> query = em.createQuery(criteria);

            UserConnectionEntity oldPendingConnection = query.getSingleResult();


            em.remove(oldPendingConnection);
        }
    }

    @Override
    public ConnectionStatus getUserConnectionStatus(int userOne, int userTwo) {


        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ConnectionStatus> criteria = builder.createQuery(ConnectionStatus.class);
        Root<UserConnectionEntity> userConnection = criteria.from(UserConnectionEntity.class);

        criteria.select(userConnection.get("connectionStatus"))
                .where(builder.or(
                        builder.and(builder.equal(userConnection.get("userOne"), userOne),
                                builder.equal(userConnection.get("userTwo"), userTwo)),
                        builder.and(builder.equal(userConnection.get("userOne"), userTwo),
                                builder.equal(userConnection.get("userTwo"), userOne))));

        TypedQuery<ConnectionStatus> query = em.createQuery(criteria);

        ConnectionStatus status = ConnectionStatus.UNKNOWN;
        try {

            status = query.getSingleResult();
        } catch (NoResultException ex) {
            status = ConnectionStatus.DISCONNECTED;
        } catch (NonUniqueResultException ex) {
            status = ConnectionStatus.UNKNOWN;
        }
        return status;

    }

    @Override
    public List<UserEntity> getConnectedUsers(int userId) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserConnectionEntity> userConnection = criteria.from(UserConnectionEntity.class);

        criteria.select(userConnection.get("userTwo"))
                .where(builder.equal(userConnection.get("userOne"), userId), builder.equal(userConnection.get("connectionStatus"), ConnectionStatus.CONNECTED));

        TypedQuery<UserEntity> query = em.createQuery(criteria);
        List<UserEntity> users = new ArrayList<>();
        try {
            users = query.getResultList();

        } catch (NoResultException ex) {
            //return empty array is OK
        }
        return users;


    }

    @Override
    public List<UserEntity> getSentRequests(int userId) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserConnectionEntity> userConnection = criteria.from(UserConnectionEntity.class);

        criteria.select(userConnection.get("userTwo"))
                .where(
                        builder.equal(userConnection.get("userOne").get("id"), userId),
                        builder.equal(userConnection.get("connectionStatus"), ConnectionStatus.PENDING)
                );

        TypedQuery<UserEntity> query = em.createQuery(criteria);
        List<UserEntity> users = new ArrayList<>();
        try {
            users = query.getResultList();

        } catch (NoResultException ex) {
            //return empty array is OK
        }
        return users;


    }

    @Override
    public List<UserEntity> getRecivedRequests(int userId) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserConnectionEntity> userConnection = criteria.from(UserConnectionEntity.class);

        criteria.select(userConnection.get("userOne"))
                .where(
                        builder.equal(userConnection.get("userTwo").get("id"), userId),
                        builder.equal(userConnection.get("connectionStatus"), ConnectionStatus.PENDING)
                );

        TypedQuery<UserEntity> query = em.createQuery(criteria);

        List<UserEntity> users = new ArrayList<>();
        try {
            users = query.getResultList();

        } catch (NoResultException ex) {
            //return empty array is OK
        }
        return users;

    }


}
