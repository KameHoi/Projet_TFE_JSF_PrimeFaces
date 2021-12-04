package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.ContactsDao;
import be.atc.salesmanagercrm.dao.impl.ContactsDaoImpl;
import be.atc.salesmanagercrm.entities.*;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.utils.JsfUtils;
import be.atc.salesmanagercrm.validators.ContactValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.UnselectEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Maximilien Zabbara
 */
@Slf4j
@Named(value = "contactsBean")
@SessionScoped
public class ContactsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 848519777793777451L;

    @Getter
    @Setter
    private ContactsEntity contactsEntity;

    @Getter
    @Setter
    private List<ContactsEntity> contactsEntityList;

    @Getter
    @Setter
    private List<ContactsEntity> contactsEntityDisableList;

    @Getter
    @Setter
    private ContactsDao contactsDao = new ContactsDaoImpl();

    @Getter
    @Setter
    private ContactsEntity selectedContactsEntity;

    @Getter
    @Setter
    private Long countActiveContacts;

    @Getter
    @Setter
    private double percentActiveContactsByAllContact;

    @Getter
    @Setter
    private List<ContactsEntity> contactEntitiesFiltered;
    @Inject
    private NotesBean notesBean;
    @Inject
    private TasksBean tasksBean;
    @Inject
    private VouchersBean vouchersBean;
    @Inject
    private TransactionsBean transactionsBean;
    @Inject
    private TransactionHistoriesBean transactionHistoriesBean;
    @Inject
    private VoucherHistoriesBean voucherHistoriesBean;
    @Inject
    private AddressesBean addressesBean;
    @Inject
    private CompaniesContactsBean companiesContactsBean;
    @Inject
    private UsersBean usersBean;

    @Getter
    @Setter
    private Map<LocalDateTime, Object> listActivity;


    /**
     * this method is used in activity page
     */
    public void activityThread() {
        log.info("ContactsBean => method : activityThread()");
        if (contactsEntity == null) {
            return;
        }
        log.info("ContactsBean : activityThread()");

        this.listActivity = new TreeMap<>(Collections.reverseOrder());

        for (NotesEntity n : notesBean.getNotesEntities()) {
            ObjectActivity objectActivity = new ObjectActivity(n.getClass().getName(), n);
            listActivity.put(n.getCreationDate(), objectActivity);
        }

        for (TasksEntity t : tasksBean.getTasksEntities()) {
            ObjectActivity objectActivity = new ObjectActivity(t.getClass().getName(), t);
            listActivity.put(t.getCreationDate(), objectActivity);
        }

        List<TransactionsEntity> transactionsEntities = transactionsBean.findTransactionsEntityByContactsByIdContacts(contactsEntity, usersBean.getUsersEntity());
        for (TransactionsEntity t : transactionsEntities) {
            ObjectActivity objectActivity = new ObjectActivity(t.getClass().getName(), t);
            listActivity.put(t.getCreationDate(), objectActivity);

            List<TransactionHistoriesEntity> transactionHistoriesEntities = transactionHistoriesBean.findAllByIdUserAndByIdTransaction(t.getId(), usersBean.getUsersEntity());
            for (TransactionHistoriesEntity tH : transactionHistoriesEntities) {
                ObjectActivity objectActivity1 = new ObjectActivity(tH.getClass().getName(), tH);
                listActivity.put(tH.getSaveDate(), objectActivity1);
            }
        }

        List<VouchersEntity> vouchersEntities = vouchersBean.findVouchersEntityByContactsByIdContacts(contactsEntity, usersBean.getUsersEntity());
        for (VouchersEntity v : vouchersEntities) {
            ObjectActivity objectActivity = new ObjectActivity(v.getClass().getName(), v);
            listActivity.put(v.getCreationDate(), objectActivity);

            List<VoucherHistoriesEntity> voucherHistoriesEntities = voucherHistoriesBean.findAllByIdUserAndByIdVoucher(v.getId(), usersBean.getUsersEntity());
            for (VoucherHistoriesEntity vH : voucherHistoriesEntities) {
                ObjectActivity objectActivity1 = new ObjectActivity(vH.getClass().getName(), vH);
                listActivity.put(vH.getSaveDate(), objectActivity1);
            }
        }

        log.info("Liste : " + listActivity);
    }

    /**
     * Public method that call loadsListEntities()
     */
    public void findAllActiveContacts() {
        log.info("ContactsBean => method : findAllActiveContacts()");
        loadListEntities();
    }

    /**
     * Find Contacts entities by id User
     *
     * @param usersEntity UsersEntity
     * @return List ContactsEntity
     */
    public List<ContactsEntity> findContactsEntityByIdUser(UsersEntity usersEntity) {
        log.info("ContactsBean => method : findContactsEntityByIdUser()");
        if (usersEntity == null) {
            log.error("User Entity is null");
            return Collections.emptyList();
        }

        EntityManager em = EMF.getEM();

        List<ContactsEntity> contactsEntities = contactsDao.findContactsEntityByIdUser(em, usersEntity.getId());

        em.clear();
        em.close();

        return contactsEntities;
    }

    /**
     * method to know which entity to reload
     */
    public void loadListEntities() {
        log.info("ContactsBean => method : loadListEntities()");
        List<ContactsEntity> contactsEntities = findAllContactsEntityByIdUser(usersBean.getUsersEntity().getId());

        this.contactsEntityList = contactsEntities.stream().filter(ContactsEntity::isActive).collect(Collectors.toList());
        this.contactsEntityDisableList = contactsEntities.stream().filter(c -> !c.isActive()).collect(Collectors.toList());

    }

    /**
     * Method for soft delete
     */
    public void deleteContact() {
        log.info("ContactsBean => method : deleteContact()");
        log.info("Id de contacts = " + getParam("contactsID"));

        delete(Integer.parseInt(getParam("contactsID")));

        loadListEntities();
    }

    /**
     * Find all contacts by id user
     *
     * @param id id
     * @return contactsEntityList1
     */
    protected List<ContactsEntity> findAllContactsEntityByIdUser(int id) {
        log.info("ContactsBean => method : findAllContactsEntityByIdUser()");
        EntityManager em = EMF.getEM();

        List<ContactsEntity> contactsEntityList1 = contactsDao.findAllContactsEntityByIdUser(em, id);

        em.clear();
        em.close();

        return contactsEntityList1;
    }

    /**
     * Public method for activateContact
     */
    public void activateContactbyIdContact() {
        log.info("ContactsBean => method : activateContactbyIdContact()");
        log.info("Id de contact = " + getParam("contactsID"));

        activateContact(Integer.parseInt(getParam("contactsID")));

        loadListEntities();
    }

    /**
     * Activate contact by its id
     *
     * @param id contact id
     */
    protected void activateContact(int id) {
        log.info("ContactsBean => method : activateContact()");

        FacesMessage facesMessage;

        ContactsEntity contactsEntityToActivate;
        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;

        try {
            contactsEntityToActivate = findByIdContactAndByIdUser(id, usersBean.getUsersEntity());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "contactNotExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        contactsEntityToActivate.setActive(true);

        try {
            tx = em.getTransaction();
            tx.begin();
            contactsDao.update(em, contactsEntityToActivate);
            tx.commit();
            log.info("Contact enable ok");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "contacts.activated"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Contact enable failed");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } finally {
            em.clear();
            em.clear();
        }
    }

    /**
     * Soft delete for contacts by id
     *
     * @param id id Contacts
     */
    protected void delete(int id) {
        log.info("ContactsBean => method : delete()");

        FacesMessage facesMessage;

        ContactsEntity contactsEntityToDelete;
        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;

        try {
            contactsEntityToDelete = findByIdContactAndByIdUser(id, usersBean.getUsersEntity());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "contactNotExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        contactsEntityToDelete.setActive(false);

        try {
            tx = em.getTransaction();
            tx.begin();
            contactsDao.update(em, contactsEntityToDelete);
            tx.commit();
            log.info("Soft delete ok");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "contacts.deleted"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Soft delete échec");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } finally {
            em.clear();
            em.clear();
        }
    }

    /**
     * Deselect company in contactDetails
     *
     * @param event event
     */
    public void onItemUnselect(UnselectEvent event) {
        log.info("ContactsBean => method : onItemUnselect()");

        FacesMessage facesMessage;

        facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "contacts.removed"), null);

        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    /**
     * Find Contact by ID
     *
     * @param id ContactsEntity
     * @return Contacts Entity
     */
    public ContactsEntity findByIdContactAndByIdUser(int id, UsersEntity usersEntity) {
        log.info("ContactsBean => method : findByIdContactAndByIdUser()");

        FacesMessage msg;

        if (id == 0) {
            log.error("Contact ID is null");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "contactNotExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
        if (usersEntity == null) {
            log.error("User Entity is null");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "userNotExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

        EntityManager em = EMF.getEM();
        Optional<ContactsEntity> optionalContactsEntity;
        try {
            optionalContactsEntity = contactsDao.findByIdContactAndByIdUser(em, id, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return optionalContactsEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun contact avec l ID " + id + " et l ID User " + usersEntity.getId() + " n a ete trouvee dans la BDD",
                        ErrorCodes.CONTACT_NOT_FOUND
                ));
    }

    /**
     * public method that call displayOneContact
     */
    public void getDisplayOneContact() {
        log.info("ContactsBean => method : getDisplayOneContact()");

        displayOneContact();
    }

    /**
     * display one contact by CompanyId
     */
    protected void displayOneContact() {
        log.info("ContactsBean => method : displayOneContact()");
        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        FacesMessage facesMessage;

        log.info("Début méthode displayOneContact");
        log.info("Param : " + getParam("contactID"));

        if (getParam("contactID") != null) {
            int idContact;

            try {
                idContact = Integer.parseInt(getParam("contactID"));
            } catch (NumberFormatException exception) {
                log.info(exception.getMessage());
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, JsfUtils.returnMessage(getLocale(), "contact.error"), null);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                nav.performNavigation("contacts");
                return;
            }

            try {
                this.contactsEntity = findByIdContactAndByIdUser(idContact, usersBean.getUsersEntity());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, JsfUtils.returnMessage(getLocale(), "contact.error"), null);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                nav.performNavigation("contacts");
                return;
            }
        }

        addressesBean.getAddressByIdContacts();
    }

    /**
     * Create new instance for objects
     */
    public void createNewEntity() {
        log.info("ContactsBean => method : createNewEntity()");

        contactsEntity = new ContactsEntity();
    }

    /**
     * Public method that call save() method
     */
    public void addContact() {
        log.info("ContactsBean => method : addContact()");

        save(contactsEntity);
        createNewEntity();
        findAllActiveContacts();
    }

    /**
     * Auto Complete form creation
     *
     * @param query String
     * @return List Contacts Entities
     */
    public List<ContactsEntity> completeContactsContains(String query) {
        log.info("ContactsBean => method : completeContactsContains()");

        String queryLowerCase = query.toLowerCase();

        List<ContactsEntity> contactsEntitiesForm = findContactsEntityByIdUser(usersBean.getUsersEntity());
        String space = " ";
        return contactsEntitiesForm.stream().filter(t -> (t.getFirstname().concat(space.concat(t.getLastname())).toLowerCase().contains(queryLowerCase)) || (t.getLastname().concat(space.concat(t.getFirstname())).toLowerCase().contains(queryLowerCase))).collect(Collectors.toList());
    }

    /**
     * Sort Contacts by group in form
     *
     * @param entityGroup ContactsEntity
     * @return list of firstname from ContactsEntity
     */
    public char getContactsEntityGroup(ContactsEntity entityGroup) {
        log.info("ContactsBean => method : getContactsEntityGroup()");
        return entityGroup.getFirstname().charAt(0);
    }

    /**
     * Save contact entity
     *
     * @param contactsEntity ContactsEntity
     */
    protected void save(ContactsEntity contactsEntity) {
        log.info("ContactsBean => method : save()");

        contactsEntity.setRegisterDate(LocalDateTime.now());
        contactsEntity.setActive(true);

        contactsEntity.setUsersByIdUsers(usersBean.getUsersEntity());

        try {
            validateContacts(contactsEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        FacesMessage facesMessage;
        CheckEntities checkEntities = new CheckEntities();

        try {
            checkEntities.checkUser(contactsEntity.getUsersByIdUsers());
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "userNotExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "userNotExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            contactsDao.add(em, contactsEntity);
            tx.commit();
            log.info("Persist ok");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "contacts.save"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Persist echec");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } finally {
            em.clear();
            em.clear();
        }

    }

    /**
     * Public method that call update
     */
    public void updateContact() {
        log.info("ContactsBean => method : updateContact()");

        update(contactsEntity);
    }

    /**
     * Update contacts entity
     *
     * @param contactsEntity contactsEntity
     */
    protected void update(ContactsEntity contactsEntity) {
        log.info("ContactsBean => method : update()");

        FacesMessage facesMessage;
        CheckEntities checkEntities = new CheckEntities();

        contactsEntity.setModificationDate(LocalDateTime.now());

        try {
            validateContacts(contactsEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        try {
            checkEntities.checkUser(contactsEntity.getUsersByIdUsers());
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "userNotExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "userNotExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        try {
            checkEntities.checkContactType(contactsEntity.getContactTypesByIdContactTypes());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "contact"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        if (contactsEntity.getBranchActivitiesByIdBranchActivities() != null) {
            try {
                checkEntities.checkBranchActivities(contactsEntity.getBranchActivitiesByIdBranchActivities());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "contactTypes.NotExist"), null);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                return;
            }
        }

        if (contactsEntity.getJobTitlesByIdJobTitles() != null) {
            try {
                checkEntities.checkJobTitles(contactsEntity.getJobTitlesByIdJobTitles());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "jobTitles.notExist"), null);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                return;
            }
        }

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            contactsDao.update(em, contactsEntity);
            addressesBean.getAddressesEntity().setContactsByIdContacts(contactsEntity);
            addressesBean.updateEntity();
            List<CompaniesContactsEntity> companiesContactsEntityList = companiesContactsBean.findByIdContacts(contactsEntity.getId());
            if (!companiesContactsEntityList.isEmpty()) {
                for (CompaniesContactsEntity c : companiesContactsEntityList) {
                    companiesContactsBean.delete(c.getId());
                }
            }
            companiesContactsBean.createCompaniesContacts();
            tx.commit();
            log.info("Update ok");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "company.updated"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Update échec");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } finally {
            em.clear();
            em.clear();
        }

    }

    /**
     * Validate Contacts !
     *
     * @param entity ContactsEntity
     */
    private void validateContacts(ContactsEntity entity) {
        log.info("ContactsBean => method : validateContacts()");

        List<String> errors = ContactValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("Contacts is not valid {}", entity);
            throw new InvalidEntityException("Les données du contact ne sont pas valide", ErrorCodes.CONTACT_NOT_VALID, errors);
        }
    }

    /**
     * method to calculate the rate of active contacts by all contacts
     */
    public void calculatePercentContact() {
        long activeContacts = countActiveContacts(usersBean.getUsersEntity());
        long allContacts = countAllContacts(usersBean.getUsersEntity());

        this.percentActiveContactsByAllContact = allContacts != 0 ? (double) activeContacts / (double) allContacts : 0;
    }

    /**
     * public method for countActiveContacts()
     */
    public void countActiveContactsEntity() {
        log.info("ContactsBean => method : countActiveContactsEntity()");
        this.countActiveContacts = countActiveContacts(usersBean.getUsersEntity());
    }

    /**
     * Count all active contacts
     *
     * @param usersEntity userEntity
     * @return resultCount
     */
    protected Long countActiveContacts(UsersEntity usersEntity) {
        log.info("ContactsBean => method : countActiveContacts()");

        if (usersEntity == null) {
            log.error("User Entity is null");
            throw new EntityNotFoundException(
                    "Aucun utilisateur n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Long resultCount;

        try {
            resultCount = contactsDao.countActiveContacts(em, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return resultCount;
    }

    /**
     * Count all contacts
     *
     * @param usersEntity userEntity
     * @return resultCount
     */
    protected Long countAllContacts(UsersEntity usersEntity) {
        log.info("ContactsBean => method : countAllContacts()");

        if (usersEntity == null) {
            log.error("User Entity is null");
            throw new EntityNotFoundException(
                    "Aucun utilisateur n a ete trouve", ErrorCodes.USER_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Long resultCount;

        try {
            resultCount = contactsDao.countAllContacts(em, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return resultCount;
    }
}
