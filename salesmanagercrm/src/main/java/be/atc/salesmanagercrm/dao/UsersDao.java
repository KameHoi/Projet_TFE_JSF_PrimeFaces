package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.UsersEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface UsersDao {

    /**
     * @param em EntityManager
     * @param id int
     * @return UsersEntity
     */
    UsersEntity findById(EntityManager em, int id);


    /**
     * @param em       EntityManager
     * @param username String
     * @return UsersEntity
     */
    UsersEntity findByUsername(EntityManager em, String username);

    /**
     * @param em          EntityManager
     * @param usersEntity UsersEntity
     */
    void register(EntityManager em, UsersEntity usersEntity);

    /**
     * @param em          EntityManager
     * @param usersEntity UsersEntity
     */
    void update(EntityManager em, UsersEntity usersEntity);

    /**
     * @param em EntityManager
     * @return List<UsersEntity>
     */
    List<UsersEntity> findAllUsers(EntityManager em);


    /**
     * find all active users
     *
     * @param em EntityManager
     * @return List<UsersEntity>
     */
    List<UsersEntity> findActiveUsers(EntityManager em);

    /**
     * for validate if an user is actif for the connection
     *
     * @param em       EntityManager
     * @param username String
     * @return UsersEntity
     */
    UsersEntity findActiveUserForConnection(EntityManager em, String username);

    /**
     * find all disable users
     *
     * @param em EntityManager
     * @return List<UsersEntity>
     */
    List<UsersEntity> findDisableUsers(EntityManager em);

}
