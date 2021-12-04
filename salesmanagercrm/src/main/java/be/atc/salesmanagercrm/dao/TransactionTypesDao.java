package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.TransactionTypesEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public interface TransactionTypesDao {

    /**
     * Find Transaction Type By Id
     *
     * @param em EntityManager
     * @param id int
     * @return TransactionTypesEntity
     */
    TransactionTypesEntity findById(EntityManager em, int id);

    /**
     * Find All TransactionTypesEntities
     *
     * @return List<TransactionTypesEntity>
     */
    List<TransactionTypesEntity> findAll();

    /**
     * Find TransactionTypesEntity By Label
     *
     * @param em    EntityManager
     * @param label String
     * @return Optional<TransactionTypesEntity>
     */
    Optional<TransactionTypesEntity> findByLabel(EntityManager em, String label);
}
