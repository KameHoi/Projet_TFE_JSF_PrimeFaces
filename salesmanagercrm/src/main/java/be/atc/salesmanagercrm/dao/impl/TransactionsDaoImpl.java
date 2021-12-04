package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.TransactionsDao;
import be.atc.salesmanagercrm.entities.TransactionsEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public class TransactionsDaoImpl extends EntityFinderImpl<TransactionsEntity> implements TransactionsDao {
    @Override
    public void save(EntityManager em, TransactionsEntity entity) {
        em.persist(entity);
    }

    @Override
    public Optional<TransactionsEntity> findById(EntityManager em, int id, int idUser) {
        return em.createNamedQuery("Transactions.findById",
                        TransactionsEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<TransactionsEntity> findTransactionsEntityByContactsByIdContacts(EntityManager em, int id, int idUser) {

        return em.createNamedQuery("Transactions.findTransactionsEntityByContactsByIdContacts",
                        TransactionsEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public List<TransactionsEntity> findTransactionsEntityByCompaniesByIdCompanies(EntityManager em, int id, int idUser) {

        return em.createNamedQuery("Transactions.findTransactionsEntityByCompaniesByIdCompanies",
                        TransactionsEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public List<TransactionsEntity> findAll(EntityManager em, int idUser) {

        return em.createNamedQuery("Transactions.findAll",
                        TransactionsEntity.class)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public List<TransactionsEntity> findAllByPhase(EntityManager em, int idUser, String phaseTransaction) {
        return em.createNamedQuery("Transactions.findAllByPhase",
                        TransactionsEntity.class)
                .setParameter("idUser", idUser)
                .setParameter("phaseTransaction", phaseTransaction)
                .getResultList();
    }

    @Override
    public void update(EntityManager em, TransactionsEntity entity) {
        em.merge(entity);
    }

    @Override
    public Long countActiveTransactions(EntityManager em, int idUser) {

        Object activeTransactions = em.createNamedQuery("Transactions.countActiveTransactions",
                        TransactionsEntity.class)
                .setParameter("idUser", idUser)
                .getSingleResult();

        return (Long) activeTransactions;
    }

    @Override
    public Long countTransactionsActivePhase(EntityManager em, int idUser, String phaseTransaction) {
        Object test = em.createNamedQuery("Transactions.countTransactionsActivePhase",
                        TransactionsEntity.class)
                .setParameter("idUser", idUser)
                .setParameter("phaseTransaction", phaseTransaction)
                .getSingleResult();
        return (Long) test;
    }
}
