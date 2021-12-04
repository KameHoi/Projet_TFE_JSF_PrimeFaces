package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.TransactionHistoriesDao;
import be.atc.salesmanagercrm.dao.impl.TransactionHistoriesDaoImpl;
import be.atc.salesmanagercrm.entities.TransactionHistoriesEntity;
import be.atc.salesmanagercrm.entities.UsersEntity;
import be.atc.salesmanagercrm.utils.EMF;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author Younes Arifi
 */
@Slf4j
@Named(value = "transactionHistoriesBean")
@ViewScoped
public class TransactionHistoriesBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -6742454252414830482L;

    @Getter
    @Setter
    private TransactionHistoriesDao dao = new TransactionHistoriesDaoImpl();

    @Getter
    @Setter
    private List<TransactionHistoriesEntity> transactionHistoriesEntities;
    @Getter
    @Setter
    private List<TransactionHistoriesEntity> transactionHistoriesEntitiesFiltered;


    /**
     * Find All TransactionHistories for 1 transaction
     *
     * @param idTransaction int
     * @param usersEntity   UsersEntity
     */
    public void findAllEntities(int idTransaction, UsersEntity usersEntity) {
        log.info("TransactionPhasesBean => method : findAllEntities(int idTransaction, UsersEntity usersEntity)");
        transactionHistoriesEntities = findAllByIdUserAndByIdTransaction(idTransaction, usersEntity);
    }

    /**
     * Find Transactions entities by id company
     *
     * @param idTransaction int
     * @param usersEntity   UsersEntity
     * @return List<TransactionHistoriesEntity>
     */
    protected List<TransactionHistoriesEntity> findAllByIdUserAndByIdTransaction(int idTransaction, UsersEntity usersEntity) {
        log.info("TransactionPhasesBean => method : findAllByIdUserAndByIdTransaction(int idTransaction, UsersEntity usersEntity)");

        if (idTransaction == 0) {
            log.error("Transaction ID is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactions.notExist");
            return Collections.emptyList();
        }
        if (usersEntity == null) {
            log.error("User ID is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return Collections.emptyList();
        }

        EntityManager em = EMF.getEM();
        List<TransactionHistoriesEntity> transactionHistoriesEntities;
        try {
            transactionHistoriesEntities = dao.findAllByIdUserAndByIdTransaction(em, idTransaction, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return transactionHistoriesEntities;
    }
}
