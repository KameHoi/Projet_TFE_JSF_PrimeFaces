package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.RolesDao;
import be.atc.salesmanagercrm.entities.RolesEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Larché Marie-Élise
 */
@Slf4j
public class RolesDaoImpl implements RolesDao {


    @Override
    public RolesEntity findById(EntityManager em, int id) {
        return em.find(RolesEntity.class, id);
    }

    @Override
    public List<RolesEntity> findAllRoles(EntityManager em) {
        return em.createNamedQuery("Roles.findAllRoles",
                RolesEntity.class)
                .getResultList();
    }

    @Override
    public List<RolesEntity> findAllRolesActive(EntityManager em) {
        return em.createNamedQuery("Roles.findAllActiveRoles",
                RolesEntity.class)
                .getResultList();
    }

    @Override
    public RolesEntity findByLabel(EntityManager em, String label) {
        try {
            return em.createNamedQuery("Roles.findByLabel",
                    RolesEntity.class)
                    .setParameter("label", label)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void register(EntityManager em, RolesEntity rolesEntity) {
        em.persist(rolesEntity);
    }

    public void update(EntityManager em, RolesEntity rolesEntity) {
        em.merge(rolesEntity);
    }

    public List<RolesEntity> findForDeleteSafe(EntityManager em, int id) {
        return em.createNamedQuery("Roles.findForDeleteSafe",
                RolesEntity.class)
                .setParameter("id", id)
                .getResultList();

    }


    @Override
    public RolesEntity findRoleForConnection(EntityManager em, String label) {
        try {
            return em.createNamedQuery("Roles.checkRoleForConnection",
                    RolesEntity.class)
                    .setParameter("label", label)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public RolesEntity findActiveById(EntityManager em, RolesEntity rolesEntity) {
        try {
            return em.createNamedQuery("Roles.checkRoleActive",
                    RolesEntity.class)
                    .setParameter("id", rolesEntity.getId())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
