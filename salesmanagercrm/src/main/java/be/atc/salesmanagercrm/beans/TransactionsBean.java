package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.TransactionHistoriesDao;
import be.atc.salesmanagercrm.dao.TransactionsDao;
import be.atc.salesmanagercrm.dao.impl.TransactionHistoriesDaoImpl;
import be.atc.salesmanagercrm.dao.impl.TransactionsDaoImpl;
import be.atc.salesmanagercrm.entities.*;
import be.atc.salesmanagercrm.exceptions.AccessDeniedException;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.validators.TransactionsValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
@Slf4j
@Named(value = "transactionsBean")
@ViewScoped
public class TransactionsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -5437009720221374165L;

    @Getter
    @Setter
    private TransactionsDao dao = new TransactionsDaoImpl();

    @Getter
    @Setter
    private TransactionsEntity transactionsEntity;
    @Getter
    @Setter
    private TransactionsEntity selectedTransactionEntity;

    @Getter
    @Setter
    private List<TransactionsEntity> transactionsEntities;
    @Getter
    private final LocalDateTime now = LocalDateTime.now().plusSeconds(10);
    @Getter
    @Setter
    private List<TransactionsEntity> transactionsEntitiesFiltered;
    @Getter
    @Setter
    private Long countActiveTransactions;
    @Getter
    @Setter
    private double percentActiveTransactionsByPhase;


    @Inject
    private TransactionHistoriesBean transactionHistoriesBean;
    @Inject
    private UsersBean usersBean;
    @Inject
    private AccessControlBean accessControlBean;

    /**
     * Save entity form
     */
    public void saveEntity() {
        log.info("TransactionsBean => method : saveEntity()");

        log.info("TransactionsEntity = : " + transactionsEntity);

        transactionsEntity.setUsersByIdUsers(usersBean.getUsersEntity());

        save(transactionsEntity);
        findAllEntitiesAndFilter();

    }

    /**
     * Update Entity form
     */
    public void updateEntity() {
        log.info("TransactionsBean => method : updateEntity()");

        log.info("transactionsEntity = : " + this.transactionsEntity);

        update(this.transactionsEntity);

        findAllEntitiesAndFilter();
    }

    /**
     * Method for delete entity
     */
    public void deleteEntity() {
        log.info("TransactionsBean => method : deleteEntity()");

        log.info("Param : " + getParam("idTransaction"));
        int idTransaction;
        try {
            idTransaction = Integer.parseInt(getParam("idTransaction"));
        } catch (NumberFormatException exception) {
            log.info(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "transactions.notExist");
            return;
        }
        delete(idTransaction, usersBean.getUsersEntity());

        findAllEntitiesAndFilter();
    }

    /**
     * Update entity form
     */
    public void showModalUpdate() {
        log.info("TransactionsBean => method : showModalUpdate()");

        log.info("param : " + getParam("idEntity"));

        int idTransaction;
        try {
            idTransaction = Integer.parseInt(getParam("idEntity"));
        } catch (NumberFormatException exception) {
            log.info(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "transactions.notExist");
            return;
        }

        try {
            transactionsEntity = findById(idTransaction, usersBean.getUsersEntity());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "transactions.notExist");
        }
    }

    /**
     * Create New instance
     */
    public void createNewEntity() {
        log.info("TransactionsBean => method : createNewEntity()");

        transactionsEntity = new TransactionsEntity();
    }

    /**
     * Find All Transactions and filter
     */
    public void findAllEntitiesAndFilter() {
        log.info("TransactionsBean => method : findAllEntitiesAndFilter()");

        this.transactionsEntities = findAll(usersBean.getUsersEntity());
    }

    /**
     * Call this method in transactionDetails
     */
    public void displayOneTransaction() {
        log.info("TransactionsBean => method : displayOneTransaction()");

        log.info("Param : " + getParam("idTransaction"));
        int idTransaction;

        try {
            idTransaction = Integer.parseInt(getParam("idTransaction"));
        } catch (NumberFormatException exception) {
            log.info(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "transactions.notExist");
            return;
        }

        try {
            transactionsEntity = findById(idTransaction, usersBean.getUsersEntity());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "transactions.notExist");
            return;
        }
        transactionHistoriesBean.findAllEntities(idTransaction, usersBean.getUsersEntity());
    }

    /**
     * Method to show modal in create transaction
     */
    public void showModalCreate() {
        log.info("TransactionsBean => method : showModalCreate()");
        transactionsEntity = new TransactionsEntity();
    }

    /**
     * method to calculate the transaction rate concluded on canceled transactions
     */
    public void calculPercentTransactionConclueAnnule() {
        Long conclue = countTransactionsActivePhase(usersBean.getUsersEntity(), "Conclue");
        Long annule = countTransactionsActivePhase(usersBean.getUsersEntity(), "Annulé");
        this.percentActiveTransactionsByPhase = conclue + annule != 0 ? (double) conclue / ((double) conclue + (double) annule) : 1;

    }

    /**
     * Save Transaction Entity
     *
     * @param entity TransactionsEntity
     */
    protected void save(TransactionsEntity entity) {
        log.info("TrasnactionsBean => method : save(TransactionsEntity entity)");

        try {
            accessControlBean.checkPermission("addTransactions");
        } catch (AccessDeniedException exception) {
            log.info(exception.getMessage());
            accessControlBean.hasNotPermission();
            return;
        }

        try {
            validateTransaction(entity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        entity.setCreationDate(LocalDateTime.now());

        if (entity.getEndDate() != null) {
            try {
                validateTransactionDateEnd(entity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactions.validator.dateEnd");
                return;
            }
        }

        CheckEntities checkEntities = new CheckEntities();

        try {
            checkEntities.checkUser(entity.getUsersByIdUsers());
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return;
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return;
        }

        if (entity.getTransactionTypesByIdTransactionTypes() != null) {
            try {
                checkEntities.checkTransactionTypes(entity.getTransactionTypesByIdTransactionTypes());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactionTypeNotExist");
                return;
            }
        }

        if (entity.getTransactionPhasesByIdTransactionPhases() != null) {
            try {
                checkEntities.checkTransactionPhases(entity.getTransactionPhasesByIdTransactionPhases());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactionPhaseNotExist");
                return;
            }
        }

        if (entity.getContactsByIdContacts() != null) {
            try {
                checkEntities.checkContact(entity.getContactsByIdContacts());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "contactNotExist");
                return;
            }
        }

        if (entity.getCompaniesByIdCompanies() != null) {
            try {
                checkEntities.checkCompany(entity.getCompaniesByIdCompanies());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "companyNotExist");
                return;
            }
        }

        entity.setActive(true);

        TransactionHistoriesEntity transactionHistoriesEntity = saveTransactionHistories(entity);

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.save(em, entity);
            TransactionHistoriesDao transactionHistoriesDao = new TransactionHistoriesDaoImpl();
            transactionHistoriesDao.save(em, transactionHistoriesEntity);
            tx.commit();
            log.info("Persist ok");
            getFacesMessage(FacesMessage.SEVERITY_INFO, "transactions.saved");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Persist echec");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "errorOccured");
        } finally {
            em.clear();
            em.clear();
        }
    }


    /**
     * Find Transaction entity by id
     *
     * @param id            Transaction
     * @param usersEntity   UsersEntity
     * @return TransactionsEntity
     */
    protected TransactionsEntity findById(int id, UsersEntity usersEntity) {
        log.info("TransactionsBean => method : findById(int id, UsersEntity usersEntity)");

        if (id == 0) {
            log.error("Transaction ID is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactions.notExist");
            throw new EntityNotFoundException(
                    "L ID de la tansaction est incorrect",
                    ErrorCodes.TRANSACTION_NOT_FOUND
            );
        }
        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            throw new EntityNotFoundException(
                    "Aucun utilisateur n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Optional<TransactionsEntity> optionalTransactionsEntity;
        try {
            optionalTransactionsEntity = dao.findById(em, id, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return optionalTransactionsEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune transaction avec l ID " + id + " et l ID User " + usersEntity.getId() + " n a ete trouvee dans la BDD",
                        ErrorCodes.TRANSACTION_NOT_FOUND
                ));
    }

    /**
     * Find Transactions entities by id contact
     *
     * @param contactsEntity    ContactsEntity
     * @param usersEntity       UsersEntity
     * @return List TransactionsEntities
     */
    protected List<TransactionsEntity> findTransactionsEntityByContactsByIdContacts(ContactsEntity contactsEntity, UsersEntity usersEntity) {
        log.info("TransactionsBean => method : findTransactionsEntityByContactsByIdContacts(ContactsEntity contactsEntity, UsersEntity usersEntity)");

        if (contactsEntity == null) {
            log.error("Contact Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "contactNotExist");
            return Collections.emptyList();
        }
        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return Collections.emptyList();
        }
        EntityManager em = EMF.getEM();

        List<TransactionsEntity> transactionsEntities;
        try {
            transactionsEntities = dao.findTransactionsEntityByContactsByIdContacts(em, contactsEntity.getId(), usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return transactionsEntities;
    }


    /**
     * Find Transactions entities by id company
     *
     * @param companiesEntity       CompaniesEntity
     * @return List TransactionsEntities
     */
    protected List<TransactionsEntity> findTransactionsEntityByCompaniesByIdCompanies(CompaniesEntity companiesEntity, UsersEntity usersEntity) {
        log.info("TransactionsBean => method : findTransactionsEntityByCompaniesByIdCompanies(CompaniesEntity companiesEntity, UsersEntity usersEntity)");

        if (companiesEntity == null) {
            log.error("Company Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "companyNotExist");
            return Collections.emptyList();
        }
        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return Collections.emptyList();
        }

        EntityManager em = EMF.getEM();
        List<TransactionsEntity> transactionsEntities;
        try {
            transactionsEntities = dao.findTransactionsEntityByCompaniesByIdCompanies(em, companiesEntity.getId(), usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return transactionsEntities;
    }

    /**
     * Find All Transactions Entities
     *
     * @return List TransactionsEntity
     */
    protected List<TransactionsEntity> findAll(UsersEntity usersEntity) {
        log.info("TransactionsBean => method : findAll(UsersEntity usersEntity)");

        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return Collections.emptyList();
        }
        EntityManager em = EMF.getEM();
        List<TransactionsEntity> transactionsEntities;
        try {
            transactionsEntities = dao.findAll(em, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return transactionsEntities;
    }

    /**
     * Find All Transactions Entities by Phase
     *
     * @return List TransactionsEntity
     */
    protected List<TransactionsEntity> findAllByPhase(UsersEntity usersEntity, String phaseTransaction) {
        log.info("TransactionsBean => method : findAllByPhase(UsersEntity usersEntity, String phaseTransaction)");

        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return Collections.emptyList();
        }
        EntityManager em = EMF.getEM();
        List<TransactionsEntity> transactionsEntitiesToFind;
        try {
            transactionsEntitiesToFind = dao.findAllByPhase(em, usersEntity.getId(), phaseTransaction);
        } finally {
            em.clear();
            em.close();
        }

        return transactionsEntitiesToFind;
    }


    /**
     * Update TransactionsEntity
     *
     * @param entity            TransactionsEntity
     */
    protected void update(TransactionsEntity entity) {
        log.info("TransactionsBean => method : update(TransactionsEntity entity)");

        try {
            accessControlBean.checkPermission("updateTransactions");
        } catch (AccessDeniedException exception) {
            log.info(exception.getMessage());
            accessControlBean.hasNotPermission();
            return;
        }

        try {
            validateTransaction(entity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        try {
            TransactionsEntity transactionsEntityToFind = findById(entity.getId(), entity.getUsersByIdUsers());
            entity.setCreationDate(transactionsEntityToFind.getCreationDate());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactions.notExist");
            return;
        }

        if (entity.getEndDate() != null) {
            try {
                validateTransactionDateEnd(entity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactions.validator.dateEnd");
                return;
            }
        }

        CheckEntities checkEntities = new CheckEntities();

        try {
            checkEntities.checkUser(entity.getUsersByIdUsers());
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return;
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return;
        }

        if (entity.getTransactionTypesByIdTransactionTypes() != null) {
            try {
                checkEntities.checkTransactionTypes(entity.getTransactionTypesByIdTransactionTypes());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactionTypeNotExist");
                return;
            }
        }

        if (entity.getTransactionPhasesByIdTransactionPhases() != null) {
            try {
                checkEntities.checkTransactionPhases(entity.getTransactionPhasesByIdTransactionPhases());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactionPhaseNotExist");
                return;
            }
        }

        if (entity.getContactsByIdContacts() != null) {
            try {
                checkEntities.checkContact(entity.getContactsByIdContacts());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "contactNotExist");
                return;
            }
        }

        if (entity.getCompaniesByIdCompanies() != null) {
            try {
                checkEntities.checkCompany(entity.getCompaniesByIdCompanies());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "companyNotExist");
                return;
            }
        }

        TransactionHistoriesEntity transactionHistoriesEntity = saveTransactionHistories(entity);

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.update(em, entity);
            TransactionHistoriesDao transactionHistoriesDao = new TransactionHistoriesDaoImpl();
            transactionHistoriesDao.save(em, transactionHistoriesEntity);
            tx.commit();
            log.info("Update ok");
            getFacesMessage(FacesMessage.SEVERITY_INFO, "transactions.updated");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Update echec");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "errorOccured");
        } finally {
            em.clear();
            em.clear();
        }

    }


    /**
     * Logical Delete transaction
     *
     * @param id                int
     * @param usersEntity       UsersEntity
     */
    protected void delete(int id, UsersEntity usersEntity) {
        log.info("TransactionsBean => method : delete(int id, TransactionsEntity entity)");

        try {
            accessControlBean.checkPermission("deleteTransactions");
        } catch (AccessDeniedException exception) {
            log.info(exception.getMessage());
            accessControlBean.hasNotPermission();
            return;
        }

        if (id == 0) {
            log.error("Transaction ID is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactions.notExist");
            return;
        }
        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return;
        }
        TransactionsEntity transactionsEntityToDelete;
        try {
            transactionsEntityToDelete = findById(id, usersEntity);
        } catch (EntityNotFoundException exception) {
            log.warn("Code erreur : " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "transactions.notExist");
            return;
        }

        transactionsEntityToDelete.setActive(false);

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.update(em, transactionsEntityToDelete);
            tx.commit();
            log.info("Logical delete ok");
            getFacesMessage(FacesMessage.SEVERITY_INFO, "transactions.deleted");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Logical delete echec");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "errorOccured");
        } finally {
            em.clear();
            em.clear();
        }

    }


    /**
     * Count Transaction Active by Phase
     *
     * @param usersEntity           UsersEntity
     * @param phaseTransaction      String
     * @return Long
     */
    protected Long countTransactionsActivePhase(UsersEntity usersEntity, String phaseTransaction) {
        log.info("TransactionsBean => method : countTransactionsActiveConclue(UsersEntity usersEntity, String phaseTransaction)");

        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return null;
        }
        EntityManager em = EMF.getEM();
        try {
            return dao.countTransactionsActivePhase(em, usersEntity.getId(), phaseTransaction);
        } finally {
            em.clear();
            em.clear();
        }
    }

    /**
     * Validate Transaction !
     *
     * @param entity        TransactionsEntity
     */
    private void validateTransaction(TransactionsEntity entity) {
        log.info("TransactionsBean => method : validateTransaction(TransactionsEntity entity)");

        List<String> errors = TransactionsValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("Transaction is not valide {}", entity);
            throw new InvalidEntityException("La transaction n est pas valide", ErrorCodes.TRANSACTION_NOT_VALID, errors);
        }
    }

    /**
     * Validate Transaction Date End
     *
     * @param entity        TransactionsEntity
     */
    private void validateTransactionDateEnd(TransactionsEntity entity) {
        log.info("TransactionsBean => method : validateTransactionDateEnd(TransactionsEntity entity)");

        if (entity.getEndDate() != null) {
            if (entity.getEndDate().isBefore(entity.getCreationDate())) {
                log.error("Transaction end date in not valide {}", entity);
                throw new InvalidEntityException("La date de fin de la transaction doit etre superieur à la date de creation", ErrorCodes.TRANSACTION_NOT_VALID);
            }
        }
    }

    /**
     * Save Transaction Histories ! Use after create or update
     *
     * @param entity        TransactionsEntity
     * @return TransactionHistoriesEntity
     */
    private TransactionHistoriesEntity saveTransactionHistories(TransactionsEntity entity) {
        log.info("TransactionsBean => method : saveTransactionHistories(TransactionsEntity entity)");

        TransactionHistoriesEntity transactionHistoriesEntity = new TransactionHistoriesEntity();

        transactionHistoriesEntity.setTransactionsByIdTransactions(entity);
        transactionHistoriesEntity.setTransactionPhasesByIdTransactionPhases(entity.getTransactionPhasesByIdTransactionPhases());
        transactionHistoriesEntity.setSaveDate(LocalDateTime.now());

        return transactionHistoriesEntity;
    }

    /**
     * public method for countActiveTransactions()
     */
    public void countActiveTransactionsEntity() {
        log.info("ContactsBean => method : countActiveContactsEntity()");
        this.countActiveTransactions = countActiveTransactions(usersBean.getUsersEntity());

    }

    /**
     * Count all active transactions
     *
     * @param usersEntity       userEntity
     * @return resultCount
     */
    protected Long countActiveTransactions(UsersEntity usersEntity) {
        log.info("TransactionsBean => method : countActiveContacts()");

        if (usersEntity == null) {
            log.error("User Entity is null");
            throw new EntityNotFoundException(
                    "Aucun utilisateur n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Long resultCount;

        try {
            resultCount = dao.countActiveTransactions(em, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return resultCount;
    }

}
