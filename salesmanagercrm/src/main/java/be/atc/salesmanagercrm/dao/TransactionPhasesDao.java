package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.TransactionPhasesEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public interface TransactionPhasesDao {

    /**
     * Find TransactionPhasesEntity by id
     *
     * @param em EntityManager
     * @param id int
     * @return TransactionPhasesEntity
     */
    TransactionPhasesEntity findById(EntityManager em, int id);

    /**
     * Find All TransactionPhasesEntities
     *
     * @return TransactionPhasesEntity
     */
    List<TransactionPhasesEntity> findAll();

    /**
     * Find TransactionPhasesEntity by label
     *
     * @param em    EntityManager
     * @param label String
     * @return List<TransactionPhasesEntity>
     */
    Optional<TransactionPhasesEntity> findByLabel(EntityManager em, String label);
}
