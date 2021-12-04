package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.PermissionsEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface PermissionsDao {

    /**
     * @param em EntityManager
     * @param id int
     * @return PermissionsEntity
     */
    PermissionsEntity findById(EntityManager em, int id);

    /**
     * @param em EntityManager
     * @return List<PermissionsEntity>
     */
    List<PermissionsEntity> findAllPermissions(EntityManager em);

    /**
     * @param em                EntityManager
     * @param permissionsEntity PermissionsEntity
     */
    void register(EntityManager em, PermissionsEntity permissionsEntity);

    /**
     * @param em    EntityManager
     * @param label String
     * @return Optional<PermissionsEntity>
     */
    Optional<PermissionsEntity> findByLabel(EntityManager em, String label);


}
