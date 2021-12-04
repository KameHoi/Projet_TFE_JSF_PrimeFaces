package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.TasksEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public interface TasksDao {

    /**
     * Save TasksEntity
     *
     * @param em     EntityManager
     * @param entity TasksEntity
     */
    void save(EntityManager em, TasksEntity entity);

    /**
     * Find TasksEntity by ID and By ID User
     *
     * @param em     EntityManager
     * @param id     TasksEntity
     * @param idUser int
     * @return Optional<TasksEntity>
     */
    Optional<TasksEntity> findById(EntityManager em, int id, int idUser);

    /**
     * Find TasksEntities By ID Task and By ID Contact
     *
     * @param em     EntityManager
     * @param id     TasksEntity
     * @param idUser int
     * @return List<TasksEntity>
     */
    List<TasksEntity> findTasksEntityByContactsByIdContacts(EntityManager em, int id, int idUser);

    /**
     * Find TasksEntities By ID Task and By ID Company
     *
     * @param em     EntityManager
     * @param id     TasksEntity
     * @param idUser int
     * @return List<TasksEntity>
     */
    List<TasksEntity> findTasksEntityByCompaniesByIdCompanies(EntityManager em, int id, int idUser);

    /**
     * Find All TasksEntities
     *
     * @param em     EntityManager
     * @param idUser int
     * @return List<TasksEntity>
     */
    List<TasksEntity> findAll(EntityManager em, int idUser);

    /**
     * Delete TasksEntity
     *
     * @param em     EntityManager
     * @param entity TasksEntity
     */
    void delete(EntityManager em, TasksEntity entity);

    /**
     * Update TasksEntity
     *
     * @param em     EntityManager
     * @param entity TasksEntity
     */
    void update(EntityManager em, TasksEntity entity);

    /**
     * Find Tasks Finished By ID User
     *
     * @param em     EntityManager
     * @param idUser TasksEntity
     * @return List<TasksEntity>
     */
    List<TasksEntity> findTasksFinished(EntityManager em, int idUser);
}
