package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.BranchActivitiesEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Maximilien Zabbara
 */
public interface BranchActivitiesDao {

    /**
     * Add BranchActivitiesEntity
     *
     * @param em                     EntityManager
     * @param branchActivitiesEntity BranchActivitiesEntity
     */
    void add(EntityManager em, BranchActivitiesEntity branchActivitiesEntity);

    /**
     * Find all BranchActivitiesEntity
     *
     * @return List<BranchActivitiesEntity>
     */
    List<BranchActivitiesEntity> findAll();

    /**
     * Update BranchActivityEntity
     *
     * @param em                     EntityManager
     * @param branchActivitiesEntity BranchActivitiesEntity
     */
    void update(EntityManager em, BranchActivitiesEntity branchActivitiesEntity);

    /**
     * Find BranchActivitiesEntity by id
     *
     * @param em EntityManager
     * @param id int
     * @return BranchActivitiesEntity
     */
    BranchActivitiesEntity findById(EntityManager em, int id);

    /**
     * Find BranchActivitesEntities by label
     *
     * @param em    EntityManager
     * @param label String
     * @return List<BranchActivitiesEntity>
     */
    List<BranchActivitiesEntity> findByLabel(EntityManager em, String label);
}
