package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.RolesPermissionsDao;
import be.atc.salesmanagercrm.entities.RolesPermissionsEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Larché Marie-Élise
 */
public class RolesPermissionsDaoImpl implements RolesPermissionsDao {


    @Override
    public RolesPermissionsEntity findById(EntityManager em, int id) {
        return em.find(RolesPermissionsEntity.class, id);
    }

    @Override
    public List<RolesPermissionsEntity> findAllRolesPermissions(EntityManager em) {
        return em.createNamedQuery("RolesPermissions.findAllRolesPermissions",
                RolesPermissionsEntity.class)
                .getResultList();
    }

    @Override
    public List<RolesPermissionsEntity> findAllRolesPermissionsWithIdRole(EntityManager em, int idRole) {
        return em.createNamedQuery("RolesPermissions.findAllRolesPermissionsWithIdRole",
                RolesPermissionsEntity.class)
                .setParameter("idRole", idRole)
                .getResultList();
    }


    @Override
    public void update(EntityManager em, RolesPermissionsEntity rolesPermissionsPermissionsEntity) {
        em.merge(rolesPermissionsPermissionsEntity);
    }

    @Override
    public void addRolePermissions(EntityManager em, RolesPermissionsEntity rolesPermissionsEntity) {
        em.persist(rolesPermissionsEntity);
    }

    @Override
    public void delete(EntityManager em, RolesPermissionsEntity entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public void deleteRolesPermissions(EntityManager em, int idRole) {
        em.createNamedQuery("RolesPermissions.deleteRolesPermissions",
                RolesPermissionsEntity.class)
                .setParameter("idRole", idRole)
                .executeUpdate();

    }

    @Override
    public RolesPermissionsEntity getComboRolePerm(EntityManager em, int idRole, int idPerm) {
        try {
            return em.createNamedQuery("RolesPermissions.findComboRolePermission",
                    RolesPermissionsEntity.class)
                    .setParameter("idRole", idRole)
                    .setParameter("idPermission", idPerm)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<RolesPermissionsEntity> findPermissionWithRole(EntityManager em, int idRole) {
        return em.createNamedQuery("RolesPermissions.findPermissionWithRole",
                RolesPermissionsEntity.class)
                .setParameter("idRole", idRole)
                .getResultList();
    }




}
