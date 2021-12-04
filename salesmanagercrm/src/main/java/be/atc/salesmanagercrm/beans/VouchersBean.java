package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.VoucherHistoriesDao;
import be.atc.salesmanagercrm.dao.VouchersDao;
import be.atc.salesmanagercrm.dao.impl.VoucherHistoriesDaoImpl;
import be.atc.salesmanagercrm.dao.impl.VouchersDaoImpl;
import be.atc.salesmanagercrm.entities.*;
import be.atc.salesmanagercrm.exceptions.AccessDeniedException;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.validators.VouchersValidator;
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
@Named(value = "vouchersBean")
@ViewScoped
public class VouchersBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -5669814935928849238L;

    @Getter
    @Setter
    private VouchersDao dao = new VouchersDaoImpl();

    @Getter
    @Setter
    private VouchersEntity vouchersEntity;
    @Getter
    @Setter
    private VouchersEntity selectedVoucherEntity;

    @Getter
    @Setter
    private List<VouchersEntity> vouchersEntities;
    @Getter
    @Setter
    private List<VouchersEntity> vouchersEntitiesFiltered;
    @Getter
    @Setter
    private Long countActiveVouchers;
    @Getter
    @Setter
    private double percentActiveVouchersByStatus;

    @Inject
    private VoucherHistoriesBean voucherHistoriesBean;
    @Inject
    private UsersBean usersBean;
    @Inject
    private AccessControlBean accessControlBean;

    /**
     * Save entity form
     */
    public void saveEntity() {
        log.info("VouchersBean => method : saveEntity()");

        log.info("VouchersEntity = : " + this.vouchersEntity);

        this.vouchersEntity.setUsersByIdUsers(usersBean.getUsersEntity());

        save(this.vouchersEntity);
        findAllEntitiesAndFilter();

    }


    /**
     * Update Entity form
     */
    public void updateEntity() {
        log.info("VouchersBean => method : updateEntity()");
        log.info("vouchersEntity = : " + this.vouchersEntity);

        update(this.vouchersEntity);

        findAllEntitiesAndFilter();
    }

    /**
     * Update entity form
     */
    public void showModalUpdate() {
        log.info("VouchersBean => method : showModalUpdate()");
        log.info("param : " + getParam("idEntity"));
        int idVoucher;

        try {
            idVoucher = Integer.parseInt(getParam("idEntity"));
        } catch (NumberFormatException exception) {
            log.info(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "vouchers.notExist");
            return;
        }

        try {
            this.vouchersEntity = findById(idVoucher, usersBean.getUsersEntity());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "vouchers.notExist");
        }
    }

    /**
     * Create New instance
     */
    public void createNewEntity() {
        log.info("VouchersBean => method : createNewEntity()");
        this.vouchersEntity = new VouchersEntity();
    }

    /**
     * Find All Vouchers and filter
     */
    public void findAllEntitiesAndFilter() {
        log.info("VouchersBean => method : findAllEntitiesAndFilter()");
        this.vouchersEntities = findAll(usersBean.getUsersEntity());
    }


    /**
     * Call this method in voucherDetails
     */
    public void displayOneVoucher() {
        log.info("VouchersBean => method : displayOneVoucher()");
        log.info("Param : " + getParam("idVoucher"));
        int idVoucher;

        try {
            idVoucher = Integer.parseInt(getParam("idVoucher"));
        } catch (NumberFormatException exception) {
            log.info(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "vouchers.notExist");
            return;
        }

        try {
            this.vouchersEntity = findById(idVoucher, usersBean.getUsersEntity());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "vouchers.notExist");
            return;
        }
        voucherHistoriesBean.findAllEntities(idVoucher, usersBean.getUsersEntity());
    }

    /**
     * Method to show modal in create voucher
     */
    public void showModalCreate() {
        log.info("VouchersBean => method : showModalCreate()");
        this.vouchersEntity = new VouchersEntity();

    }

    /**
     * method to calculate the vouchers rate on closed vouchers
     */
    public void calculPercentVouchers() {
        Long all = countActiveVouchers(usersBean.getUsersEntity());
        Long closed = countVouchersActiveStatus(usersBean.getUsersEntity(), "Fermé");
        this.percentActiveVouchersByStatus = all != 0 ? ((double) all - (double) closed) / (double) all : 0;
    }

    /**
     * Save Voucher Entity
     *
     * @param entity VouchersEntity
     */
    protected void save(VouchersEntity entity) {
        log.info("VouchersBean => method : save(VouchersEntity entity)");

        try {
            accessControlBean.checkPermission("addVouchers");
        } catch (AccessDeniedException exception) {
            log.info(exception.getMessage());
            accessControlBean.hasNotPermission();
            return;
        }

        try {
            validateVoucher(entity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        entity.setCreationDate(LocalDateTime.now());

        if (entity.getEndDate() != null) {
            try {
                validateVoucherDateEnd(entity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "vouchers.validator.dateEnd");
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

        try {
            checkEntities.checkVoucherStatus(entity.getVoucherStatusByIdVoucherStatus());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "voucherStatusNotExist");
            return;
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

        VoucherHistoriesEntity voucherHistoriesEntity = saveVoucherHistories(entity);

        entity = checkStatusAndSetClosingDate(entity);

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.save(em, entity);
            VoucherHistoriesDao voucherHistoriesDao = new VoucherHistoriesDaoImpl();
            voucherHistoriesDao.save(em, voucherHistoriesEntity);
            tx.commit();
            log.info("Persist ok");
            getFacesMessage(FacesMessage.SEVERITY_INFO, "vouchers.saved");
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
     * Find Voucher entity by id
     *
     * @param id            Voucher
     * @param usersEntity   UsersEntity
     * @return VouchersEntity
     */
    protected VouchersEntity findById(int id, UsersEntity usersEntity) {
        log.info("VouchersBean => method : findById(int id, UsersEntity usersEntity)");

        if (id == 0) {
            log.error("Voucher ID is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "vouchers.notExist");
            throw new EntityNotFoundException(
                    "L ID du ticket est incorrect",
                    ErrorCodes.VOUCHER_NOT_FOUND
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
        Optional<VouchersEntity> optionalVouchersEntity;
        try {
            optionalVouchersEntity = dao.findById(em, id, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return optionalVouchersEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun ticket avec l ID " + id + " et l ID User " + usersEntity.getId() + " n a ete trouve dans la BDD",
                        ErrorCodes.VOUCHER_NOT_FOUND
                ));
    }

    /**
     * Find vouchers entities by id contact
     *
     * @param contactsEntity        ContactsEntity
     * @param usersEntity           UsersEntity
     * @return List                 VouchersEntities
     */
    protected List<VouchersEntity> findVouchersEntityByContactsByIdContacts(ContactsEntity contactsEntity, UsersEntity usersEntity) {
        log.info("VouchersBean => method : findVouchersEntityByContactsByIdContacts(ContactsEntity contactsEntity, UsersEntity usersEntity)");

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

        List<VouchersEntity> vouchersEntities;
        try {
            vouchersEntities = dao.findVouchersEntityByContactsByIdContacts(em, contactsEntity.getId(), usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return vouchersEntities;
    }


    /**
     * Find vouchers entities by id company
     *
     * @param companiesEntity       CompaniesEntity
     * @param usersEntity           UsersEntity
     * @return List VouchersEntities
     */
    protected List<VouchersEntity> findVouchersEntityByCompaniesByIdCompanies(CompaniesEntity companiesEntity, UsersEntity usersEntity) {
        log.info("VouchersBean => method : findVouchersEntityByCompaniesByIdCompanies(CompaniesEntity companiesEntity, UsersEntity usersEntity)");

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
        List<VouchersEntity> vouchersEntities;
        try {
            vouchersEntities = dao.findVouchersEntityByCompaniesByIdCompanies(em, companiesEntity.getId(), usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return vouchersEntities;
    }

    /**
     * Find All vouchers Entities
     *
     * @param usersEntity       UsersEntity
     * @return List VouchersEntity
     */
    protected List<VouchersEntity> findAll(UsersEntity usersEntity) {
        log.info("VouchersBean => method : findAll(UsersEntity usersEntity)");

        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return Collections.emptyList();
        }
        EntityManager em = EMF.getEM();
        List<VouchersEntity> vouchersEntities;
        try {
            vouchersEntities = dao.findAll(em, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return vouchersEntities;
    }


    /**
     * Update VouchersEntity
     *
     * @param entity            VouchersEntity
     */
    protected void update(VouchersEntity entity) {
        log.info("VouchersBean => method : update(VouchersEntity entity)");

        try {
            accessControlBean.checkPermission("updateVouchers");
        } catch (AccessDeniedException exception) {
            log.info(exception.getMessage());
            accessControlBean.hasNotPermission();
            return;
        }

        try {
            validateVoucher(entity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        try {
            VouchersEntity vouchersEntityToFind = findById(entity.getId(), entity.getUsersByIdUsers());
            entity.setCreationDate(vouchersEntityToFind.getCreationDate());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "vouchers.notExist");
            return;
        }

        if (entity.getEndDate() != null) {
            try {
                validateVoucherDateEnd(entity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "vouchers.validator.dateEnd");
                return;
            }
        }

        if (entity.getEndDate() != null) {
            log.error("Voucher status is closed {}", entity);
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "vouchers.validator.statusClose");
            return;
        }

        CheckEntities checkEntities = new CheckEntities();

        try {
            checkEntities.checkUser(entity.getUsersByIdUsers());
        } catch (
                InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return;
        } catch (
                EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return;
        }

        try {
            checkEntities.checkVoucherStatus(entity.getVoucherStatusByIdVoucherStatus());
        } catch (
                EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "voucherStatusNotExist");
            return;
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

        VoucherHistoriesEntity voucherHistoriesEntity = saveVoucherHistories(entity);

        entity = checkStatusAndSetClosingDate(entity);

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.update(em, entity);
            VoucherHistoriesDao voucherHistoriesDao = new VoucherHistoriesDaoImpl();
            voucherHistoriesDao.save(em, voucherHistoriesEntity);
            tx.commit();
            log.info("Update ok");
            getFacesMessage(FacesMessage.SEVERITY_INFO, "vouchers.updated");
        } catch (
                Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Update echec");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "errorOccured");
        } finally {
            em.clear();
            em.clear();
        }

    }

    /**
     * Validate Voucher !
     *
     * @param entity        VouchersEntity
     */
    private void validateVoucher(VouchersEntity entity) {
        log.info("VouchersBean => method : validateVoucher(VouchersEntity entity)");

        List<String> errors = VouchersValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("Voucher is not valide {}", entity);
            throw new InvalidEntityException("Le ticket n est pas valide", ErrorCodes.VOUCHER_NOT_VALID, errors);
        }
    }

    /**
     * Validate Voucher Date End
     *
     * @param entity        VouchersEntity
     */
    private void validateVoucherDateEnd(VouchersEntity entity) {
        log.info("VouchersBean => method : validateVoucherDateEnd(VouchersEntity entity)");

        if (entity.getEndDate() != null) {
            if (entity.getEndDate().isBefore(entity.getCreationDate())) {
                log.error("Voucher end date in not valide {}", entity);
                throw new InvalidEntityException("La date de fin du ticket doit etre superieur à la date de creation", ErrorCodes.VOUCHER_NOT_VALID);
            }
        }
    }

    /**
     * Save Voucher Histories ! Use after create or update
     *
     * @param entity        VouchersEntity
     * @return VoucherHistoriesEntity
     */
    private VoucherHistoriesEntity saveVoucherHistories(VouchersEntity entity) {
        log.info("VouchersBean => method : saveVoucherHistories(VouchersEntity entity)");

        VoucherHistoriesEntity voucherHistoriesEntity = new VoucherHistoriesEntity();

        voucherHistoriesEntity.setVouchersByIdVouchers(entity);
        voucherHistoriesEntity.setVoucherStatusByIdVoucherStatus(entity.getVoucherStatusByIdVoucherStatus());
        voucherHistoriesEntity.setSaveDate(LocalDateTime.now());

        return voucherHistoriesEntity;
    }

    /**
     * If status is : 'fermé' => Set End Date at now
     *
     * @param entity    VouchersEntity
     * @return VouchersEntity
     */
    private VouchersEntity checkStatusAndSetClosingDate(VouchersEntity entity) {
        log.info("VouchersBean => method : checkStatusAndSetClosingDate(VouchersEntity entity)");

        if (entity != null) {
            if (entity.getVoucherStatusByIdVoucherStatus().getLabel().equalsIgnoreCase("fermé")) {
                entity.setEndDate(LocalDateTime.now());
            }
        }
        return entity;
    }

    /**
     * public method for countActiveVouchers()
     */
    public void countActiveVouchersEntity() {
        log.info("VouchersBean => method : countActiveVouchersEntity()");
        this.countActiveVouchers = countActiveVouchers(usersBean.getUsersEntity());

    }

    /**
     * Count all active vouchers
     *
     * @param usersEntity       userEntity
     * @return resultCount
     */
    protected Long countActiveVouchers(UsersEntity usersEntity) {
        log.info("VouchersBean => method : countActiveVouchers()");

        if (usersEntity == null) {
            log.error("User Entity is null");
            throw new EntityNotFoundException(
                    "Aucun utilisateur n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Long resultCount;

        try {
            resultCount = dao.countActiveVouchers(em, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return resultCount;
    }

    /**
     * Count all vouchers not Fermé
     *
     * @param usersEntity       UsersEntity
     * @param voucherStatus     String
     * @return Long
     */
    protected Long countVouchersActiveStatus(UsersEntity usersEntity, String voucherStatus) {
        log.info("VouchersBean => method : countVouchersActiveStatus(UsersEntity usersEntity, String voucherStatus)");

        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return null;
        }
        EntityManager em = EMF.getEM();
        try {
            return dao.countVouchersActiveStatus(em, usersEntity.getId(), voucherStatus);
        } finally {
            em.clear();
            em.clear();
        }
    }
}
