package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.TransactionHistoriesEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Younes Arifi
 */
public interface TransactionHistoriesDao {

    /**
     * Save TransactionHistoriesEntity
     *
     * @param em     EntityManager
     * @param entity TransactionHistoriesEntity
     */
    void save(EntityManager em, TransactionHistoriesEntity entity);

    /**
     * Find All tranTransactionHistoriesEntities by ID Transaction and by ID User
     *
     * @param em            EntityManager
     * @param idTransaction int
     * @param idUser        int
     * @return List<TransactionHistoriesEntity>
     */
    List<TransactionHistoriesEntity> findAllByIdUserAndByIdTransaction(EntityManager em, int idTransaction, int idUser);
}
