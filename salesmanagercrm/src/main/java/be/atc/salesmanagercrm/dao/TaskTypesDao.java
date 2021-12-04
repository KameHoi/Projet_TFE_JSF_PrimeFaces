package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.TaskTypesEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public interface TaskTypesDao {

    /**
     * Save TaskTypesEntity
     *
     * @param em     EntityManager
     * @param entity TaskTypesEntity
     */
    void save(EntityManager em, TaskTypesEntity entity);

    /**
     * Find TaskTypesEntity by id
     *
     * @param em EntityManager
     * @param id TaskTypesEntity
     * @return TaskTypesEntity
     */
    TaskTypesEntity findById(EntityManager em, int id);

    /**
     * Find All TaskTypesEntities
     *
     * @return List<TaskTypesEntity>
     */
    List<TaskTypesEntity> findAll();

    /**
     * Update TaskTypesEntity
     *
     * @param em     EntityManager
     * @param entity TaskTypesEntity
     */
    void update(EntityManager em, TaskTypesEntity entity);

    /**
     * Find TaskTypesEntity by label
     *
     * @param em    EntityManager
     * @param label String
     * @return Optional<TaskTypesEntity>
     */
    Optional<TaskTypesEntity> findByLabel(EntityManager em, String label);
}
