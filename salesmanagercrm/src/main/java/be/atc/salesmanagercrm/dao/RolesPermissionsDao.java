package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.RolesPermissionsEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface RolesPermissionsDao {

    /**
     * @param em EntityManager
     * @param id int
     * @return RolesPermissionsEntity
     */
    RolesPermissionsEntity findById(EntityManager em, int id);

    /**
     * @param em EntityManager
     * @return List<RolesPermissionsEntity>
     */
    List<RolesPermissionsEntity> findAllRolesPermissions(EntityManager em);

    /**
     * @param em     EntityManager
     * @param idRole int
     * @return List<RolesPermissionsEntity>
     */
    List<RolesPermissionsEntity> findAllRolesPermissionsWithIdRole(EntityManager em, int idRole);

    /**
     * @param em                     EntityManager
     * @param rolesPermissionsEntity rolesPermissionsEntity
     */
    void addRolePermissions(EntityManager em, RolesPermissionsEntity rolesPermissionsEntity);

    /**
     * @param em                     EntityManager
     * @param rolesPermissionsEntity rolesPermissionsEntity
     */
    void update(EntityManager em, RolesPermissionsEntity rolesPermissionsEntity);

    /**
     * @param em     EntityManager
     * @param idRole int
     * @param idPerm int
     * @return RolesPermissionsEntity
     */
    RolesPermissionsEntity getComboRolePerm(EntityManager em, int idRole, int idPerm);

    /**
     * @param em                     EntityManager
     * @param rolesPermissionsEntity RolesPermissionsEntity
     */
    void delete(EntityManager em, RolesPermissionsEntity rolesPermissionsEntity);

    /**
     * delete all permissions's role
     *
     * @param em     EntityManager
     * @param idRole int
     */
    void deleteRolesPermissions(EntityManager em, int idRole);

    /**
     * find all permissions's role
     *
     * @param em     EntityManager
     * @param idRole String
     * @return List<RolesPermissionsEntity>
     */
    List<RolesPermissionsEntity> findPermissionWithRole(EntityManager em, int idRole);


}
