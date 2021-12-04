package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.RolesEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface RolesDao {

    /**
     * find a role with role's id
     *
     * @param em EntityManager
     * @param id int
     * @return RolesEntity
     */
    RolesEntity findById(EntityManager em, int id);

    /**
     * find all roles
     *
     * @param em EntityManager
     * @return List<RolesEntity>
     */
    List<RolesEntity> findAllRoles(EntityManager em);

    /**
     * find all roles active. Use for register or update user methods
     *
     * @param em EntityManager
     * @return List<RolesEntity>
     */
    List<RolesEntity> findAllRolesActive(EntityManager em);

    /**
     * find a role with a label
     *
     * @param em    EntityManager
     * @param label String
     * @return RolesEntity
     */
    RolesEntity findByLabel(EntityManager em, String label);

    /**
     * create a role
     *
     * @param em          EntityManager
     * @param rolesEntity RolesEntity
     */
    void register(EntityManager em, RolesEntity rolesEntity);

    /**
     * update a role
     *
     * @param em          EntityManager
     * @param rolesEntity RolesEntity
     */
    void update(EntityManager em, RolesEntity rolesEntity);

    /**
     * check if user have a role before delete role
     *
     * @param em EntityManager
     * @param id int
     * @return List<RolesEntity>
     */
    List<RolesEntity> findForDeleteSafe(EntityManager em, int id);

    /**
     * @param em    EntityManager
     * @param label String
     * @return RolesEntity
     */
    RolesEntity findRoleForConnection(EntityManager em, String label);

    /**
     * check if role is active by id
     *
     * @param em          EntityManager
     * @param rolesEntity rolesEntity
     * @return RolesEntity RolesEntity
     */
    RolesEntity findActiveById(EntityManager em, RolesEntity rolesEntity);


}
