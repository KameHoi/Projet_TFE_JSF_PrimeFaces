package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.TransactionPhasesDao;
import be.atc.salesmanagercrm.dao.impl.TransactionPhasesDaoImpl;
import be.atc.salesmanagercrm.entities.TransactionPhasesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.utils.EMF;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
@Slf4j
@Named(value = "transactionPhasesBean")
@ViewScoped
public class TransactionPhasesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -561867803256468054L;

    @Getter
    @Setter
    private TransactionPhasesDao dao = new TransactionPhasesDaoImpl();

    @Getter
    @Setter
    private TransactionPhasesEntity transactionPhasesEntity;

    @Getter
    @Setter
    private List<TransactionPhasesEntity> transactionPhasesEntities;


    /**
     * Fill the list with Task Entities
     */
    public void findAllTransactionPhasesEntities() {
        log.info("TransactionPhasesBean => method : findAllTransactionPhasesEntities()");

        transactionPhasesEntities = findAll();
    }

    /**
     * Find TransactionPhasesEntity by ID
     *
     * @param id int
     * @return TransactionPhasesEntity
     */
    protected TransactionPhasesEntity findById(int id) {
        log.info("TransactionPhasesBean => method : findById(int id)");

        if (id == 0) {
            log.error("TransactionPhases ID is null");
            throw new EntityNotFoundException(
                    "L ID de la phase tansaction est incorrect",
                    ErrorCodes.TRANSACTIONPHASE_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Optional<TransactionPhasesEntity> optionalTransactionPhasesEntity;
        try {
            optionalTransactionPhasesEntity = Optional.ofNullable(dao.findById(em, id));
        } finally {
            em.clear();
            em.close();
        }
        return optionalTransactionPhasesEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune phase de transaction avec l'ID " + id + " n a ete trouve dans la BDD",
                        ErrorCodes.TRANSACTIONPHASE_NOT_FOUND
                ));
    }

    /**
     * Find TransactionPhasesEntity by LABEL
     *
     * @param label             TransactionPhasesEntity
     * @return TransactionPhasesEntity
     */
    public TransactionPhasesEntity findByLabel(String label) {
        log.info("TransactionPhasesBean => method : findByLabel(String label)");

        if (label == null) {
            log.error("Transaction Phases label is null");
            throw new EntityNotFoundException(
                    "Le label de la phase tansaction est incorrect",
                    ErrorCodes.TRANSACTIONPHASE_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Optional<TransactionPhasesEntity> optionalTransactionPhasesEntity;
        try {
            optionalTransactionPhasesEntity = dao.findByLabel(em, label);
        } finally {
            em.clear();
            em.close();
        }
        return optionalTransactionPhasesEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune phase de transaction avec le label " + label + " n a ete trouve dans la BDD",
                        ErrorCodes.TRANSACTIONPHASE_NOT_FOUND
                ));
    }

    /**
     * Find All TransactionPhases Entities
     *
     * @return List TransactionPhasesEntity
     */
    protected List<TransactionPhasesEntity> findAll() {
        log.info("TransactionPhasesBean => method : findAll() ");
        return dao.findAll();
    }

}
