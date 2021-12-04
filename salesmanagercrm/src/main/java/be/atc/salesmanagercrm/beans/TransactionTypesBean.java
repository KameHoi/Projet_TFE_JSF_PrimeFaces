package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.TransactionTypesDao;
import be.atc.salesmanagercrm.dao.impl.TransactionTypesDaoImpl;
import be.atc.salesmanagercrm.entities.TransactionTypesEntity;
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
@Named(value = "transactionTypesBean")
@ViewScoped
public class TransactionTypesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 4484460693560207026L;

    @Getter
    @Setter
    private TransactionTypesDao dao = new TransactionTypesDaoImpl();

    @Getter
    @Setter
    private TransactionTypesEntity transactionTypesEntity;

    @Getter
    @Setter
    private List<TransactionTypesEntity> transactionTypesEntities;


    /**
     * Fill the list with Task Entities
     */
    public void findAllTransactionTypesEntities() {
        log.info("TransactionTypesBean => method : findAllTransactionTypesEntities()");
        transactionTypesEntities = findAll();
    }

    /**
     * Find TransactionTypesEntity by ID
     *
     * @param id TransactionTypesEntity
     * @return TransactionTypesEntity
     */
    protected TransactionTypesEntity findById(int id) {
        log.info("TransactionTypesBean => method : findById(int id)");

        if (id == 0) {
            log.error("TransactionTypes ID is null");
            throw new EntityNotFoundException(
                    "L ID de la tansaction est incorrect",
                    ErrorCodes.TRANSACTIONTYPE_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Optional<TransactionTypesEntity> optionalTransactionTypesEntity;
        try {
            optionalTransactionTypesEntity = Optional.ofNullable(dao.findById(em, id));
        } finally {
            em.clear();
            em.close();
        }
        return optionalTransactionTypesEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun type de transaction avec l'ID " + id + " n a ete trouve dans la BDD",
                        ErrorCodes.TRANSACTIONTYPE_NOT_FOUND
                ));
    }

    /**
     * Find TransactionTypesEntity by LABEL
     *
     * @param label     String
     * @return TransactionTypesEntity
     */
    public TransactionTypesEntity findByLabel(String label) {
        log.info("TransactionTypesBean => method : findByLabel(String label)");

        if (label == null) {
            log.error("Transaction type label is null");
            throw new EntityNotFoundException(
                    "Le label de la tansaction est incorrect",
                    ErrorCodes.NOTE_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Optional<TransactionTypesEntity> optionalTransactionTypesEntity;
        try {
            optionalTransactionTypesEntity = dao.findByLabel(em, label);
        } finally {
            em.clear();
            em.close();
        }
        return optionalTransactionTypesEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun type de transaction avec le label " + label + " n a ete trouve dans la BDD",
                        ErrorCodes.TRANSACTIONTYPE_NOT_FOUND
                ));
    }

    /**
     * Find All TransactionTypes Entities
     *
     * @return List TransactionTypesEntity
     */
    protected List<TransactionTypesEntity> findAll() {
        log.info("TransactionTypesBean => method : findAll()");

        return dao.findAll();
    }

}
