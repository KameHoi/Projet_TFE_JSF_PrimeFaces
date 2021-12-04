package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.TransactionTypesDao;
import be.atc.salesmanagercrm.entities.TransactionTypesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public class TransactionTypesDaoImpl extends EntityFinderImpl<TransactionTypesEntity> implements TransactionTypesDao {
    @Override
    public TransactionTypesEntity findById(EntityManager em, int id) {
        return em.find(TransactionTypesEntity.class, id);
    }

    @Override
    public List<TransactionTypesEntity> findAll() {
        return this.findByNamedQuery("TransactionTypes.findAll", new TransactionTypesEntity());
    }

    @Override
    public Optional<TransactionTypesEntity> findByLabel(EntityManager em, String label) {
        return em.createNamedQuery("TransactionTypes.findTransactionTypesEntityByLabel",
                        TransactionTypesEntity.class)
                .setParameter("label", label)
                .getResultList()
                .stream()
                .findFirst();
    }
}
