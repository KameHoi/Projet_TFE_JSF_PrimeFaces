package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.TransactionsEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public interface TransactionsDao {

    /**
     * Save TransactionEntity
     *
     * @param em     EntityManager
     * @param entity TransactionsEntity
     */
    void save(EntityManager em, TransactionsEntity entity);

    /**
     * Find TransactionsEntity By id and By ID User
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return Optional<TransactionsEntity>
     */
    Optional<TransactionsEntity> findById(EntityManager em, int id, int idUser);

    /**
     * Find All transactionsEntities By Id user and by id Contact
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return Optional<TransactionsEntity>
     */
    List<TransactionsEntity> findTransactionsEntityByContactsByIdContacts(EntityManager em, int id, int idUser);

    /**
     * Find All transactionsEntities By Id user and by id company
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return List<TransactionsEntity>
     */
    List<TransactionsEntity> findTransactionsEntityByCompaniesByIdCompanies(EntityManager em, int id, int idUser);

    /**
     * Find All TransactionsEntities by ID User
     *
     * @param em     EntityManager
     * @param idUser int
     * @return List<TransactionsEntity>
     */
    List<TransactionsEntity> findAll(EntityManager em, int idUser);

    /**
     * Find All TransactionsEntities By Phase
     *
     * @param em               EntityManager
     * @param idUser           int
     * @param phaseTransaction String
     * @return List<TransactionsEntity>
     */
    List<TransactionsEntity> findAllByPhase(EntityManager em, int idUser, String phaseTransaction);

    /**
     * Update TransactionsEntity
     *
     * @param em     EntityManager
     * @param entity TransactionsEntity
     */
    void update(EntityManager em, TransactionsEntity entity);

    /**
     * Count All active transaction By Phase
     *
     * @param em               EntityManager
     * @param idUser           int
     * @param phaseTransaction String
     * @return Long
     */
    Long countTransactionsActivePhase(EntityManager em, int idUser, String phaseTransaction);

    /**
     * Count All active Transactions
     *
     * @param em     EntityManager
     * @param idUser int
     * @return Long
     */
    Long countActiveTransactions(EntityManager em, int idUser);
}
