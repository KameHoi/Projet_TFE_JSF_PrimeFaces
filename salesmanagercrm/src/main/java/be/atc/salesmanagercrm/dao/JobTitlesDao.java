package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.JobTitlesEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Maximilien Zabbara
 */
public interface JobTitlesDao {

    /**
     * Add JobTitlesEntity
     *
     * @param em              EntityManager
     * @param jobTitlesEntity JobTitlesEntity
     */
    void add(EntityManager em, JobTitlesEntity jobTitlesEntity);

    /**
     * Find all JobTitlesEntity
     *
     * @return List<JobTitlesEntity>
     */
    List<JobTitlesEntity> findAll();

    /**
     * Update JobTitlesEntity
     *
     * @param em              EntityManager
     * @param jobTitlesEntity JobTitlesEntity
     */
    void update(EntityManager em, JobTitlesEntity jobTitlesEntity);

    /**
     * find JobTitlesEntity by its id
     *
     * @param em EntityManager
     * @param id int
     * @return JobTitlesEntity
     */
    JobTitlesEntity findById(EntityManager em, int id);

    /**
     * Find JobTitlesEntity by label
     *
     * @param em    EntityManager
     * @param label String
     * @return List<JobTitlesEntity>
     */
    List<JobTitlesEntity> findByLabel(EntityManager em, String label);

}
