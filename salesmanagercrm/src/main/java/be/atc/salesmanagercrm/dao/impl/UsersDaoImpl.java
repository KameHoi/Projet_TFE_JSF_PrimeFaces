package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.UsersDao;
import be.atc.salesmanagercrm.entities.UsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Larché Marie-Élise
 */
public class UsersDaoImpl implements UsersDao {


    @Override
    public UsersEntity findById(EntityManager em, int id) {
        return em.find(UsersEntity.class, id);
    }


    @Override
    public UsersEntity findByUsername(EntityManager em, String username) {
        try {
            return em.createNamedQuery("Users.findByUsername",
                    UsersEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void register(EntityManager em, UsersEntity usersEntity) {
        em.persist(usersEntity);
    }

    @Override
    public void update(EntityManager em, UsersEntity usersEntity) {
        em.merge(usersEntity);
    }


    @Override
    public List<UsersEntity> findAllUsers(EntityManager em) {
        return em.createNamedQuery("Users.findAllUsers",
                UsersEntity.class)
                .getResultList();
    }

    @Override
    public List<UsersEntity> findActiveUsers(EntityManager em) {
        return em.createNamedQuery("Users.findActiveUsers",
                UsersEntity.class)
                .getResultList();

    }

    @Override
    public UsersEntity findActiveUserForConnection(EntityManager em, String username) {
        try {
            return em.createNamedQuery("Users.findUserActiveForConnection",
                    UsersEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<UsersEntity> findDisableUsers(EntityManager em) {
        return em.createNamedQuery("Users.findDisableUsers",
                UsersEntity.class)
                .getResultList();
    }


}
