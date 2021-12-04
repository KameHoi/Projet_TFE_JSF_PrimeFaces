package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.TransactionPhasesDao;
import be.atc.salesmanagercrm.entities.TransactionPhasesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public class TransactionPhasesDaoImpl extends EntityFinderImpl<TransactionPhasesEntity> implements TransactionPhasesDao {
    @Override
    public TransactionPhasesEntity findById(EntityManager em, int id) {
        return em.find(TransactionPhasesEntity.class, id);
    }

    @Override
    public List<TransactionPhasesEntity> findAll() {
        return this.findByNamedQuery("TransactionPhases.findAll", new TransactionPhasesEntity());
    }

    @Override
    public Optional<TransactionPhasesEntity> findByLabel(EntityManager em, String label) {
        return em.createNamedQuery("TransactionPhases.findTransactionPhasesEntityByLabel",
                        TransactionPhasesEntity.class)
                .setParameter("label", label)
                .getResultList()
                .stream()
                .findFirst();
    }
}
