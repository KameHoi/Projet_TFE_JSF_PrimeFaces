package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.TransactionHistoriesDao;
import be.atc.salesmanagercrm.entities.TransactionHistoriesEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Younes Arifi
 */
public class TransactionHistoriesDaoImpl implements TransactionHistoriesDao {
    @Override
    public void save(EntityManager em, TransactionHistoriesEntity entity) {
        em.persist(entity);
    }

    @Override
    public List<TransactionHistoriesEntity> findAllByIdUserAndByIdTransaction(EntityManager em, int idTransaction, int idUser) {
        return em.createNamedQuery("TransactionHistories.findAllByIdUserAndByIdTransaction",
                TransactionHistoriesEntity.class)
                .setParameter("idUser", idUser)
                .setParameter("idTransaction", idTransaction)
                .getResultList();
    }
}
