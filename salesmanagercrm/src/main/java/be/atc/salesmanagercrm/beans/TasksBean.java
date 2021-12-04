package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.TasksDao;
import be.atc.salesmanagercrm.dao.impl.TasksDaoImpl;
import be.atc.salesmanagercrm.entities.*;
import be.atc.salesmanagercrm.exceptions.AccessDeniedException;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.validators.TasksValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Younes Arifi
 */
@Slf4j
@Named(value = "tasksBean")
@ViewScoped
public class TasksBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 8865671023396118126L;

    @Getter
    @Setter
    private TasksDao dao = new TasksDaoImpl();
    @Getter
    private final LocalDateTime now = LocalDateTime.now().plusSeconds(10);
    @Getter
    @Setter
    private TasksEntity tasksEntity;
    @Getter
    @Setter
    private TasksEntity selectedTaskEntity;
    @Getter
    @Setter
    private ScheduleModel eventModel;

    @Getter
    @Setter
    private List<TasksEntity> tasksEntities;
    @Getter
    @Setter
    private List<TasksEntity> tasksEntitiesFiltered;
    @Getter
    @Setter
    private List<TasksEntity> tasksEntitiesDueToday;
    @Getter
    @Setter
    private List<TasksEntity> tasksEntitiesDueTodayFiltered;
    @Getter
    @Setter
    private List<TasksEntity> tasksEntitiesToLate;
    @Getter
    @Setter
    private List<TasksEntity> tasksEntitiesToLateFiltered;
    @Getter
    @Setter
    private List<TasksEntity> tasksEntitiesToCome;
    @Getter
    @Setter
    private List<TasksEntity> tasksEntitiesToComeFiltered;
    @Getter
    @Setter
    private List<TasksEntity> tasksEntitiesFinished;
    @Getter
    @Setter
    private List<TasksEntity> tasksEntitiesFinishedFiltered;
    @Getter
    @Setter
    private TaskTypesEntity taskTypesEntity = new TaskTypesEntity();
    @Getter
    @Setter
    private String paramType = "";

    @Inject
    private ContactsBean contactsBean;
    @Inject
    private CompaniesBean companiesBean;
    @Inject
    private UsersBean usersBean;
    @Inject
    private AccessControlBean accessControlBean;

    /**
     * Save entity form
     */
    public void saveEntity() {
        log.info("TasksBean => method : saveEntity()");

        log.info("TaskEntity = : " + tasksEntity);

        tasksEntity.setUsersByIdUsers(usersBean.getUsersEntity());

        save(tasksEntity);

        createNewEntity();

        loadListEntities(getParamType());
    }


    /**
     * Find all entities for Users
     */
    public void listEntities() {
        log.info("TasksBean => method : listEntities()");
        loadListEntities("all");
    }

    /**
     * Find all entities for Contacts
     */
    public void listEntitiesContacts() {
        log.info("TasksBean => method : listEntitiesContacts()");
        loadListEntities("displayByContact");
    }

    /**
     * Find all entities for Companies
     */
    public void listEntitiesCompanies() {
        log.info("TasksBean => method : listEntitiesCompanies()");
        loadListEntities("displayByCompany");
    }

    public void listEntitiesDueToday() {
        log.info("TasksBean => method : listEntitiesCompanies()");
        loadListEntities("displayToday");
    }

    /**
     * Create new instance for objects
     */
    public void createNewEntity() {
        log.info("TasksBean => method : createNewEntity()");
        this.tasksEntity = new TasksEntity();
        this.tasksEntities = new ArrayList<>();

        checkTypeParamAndSetContactOrCompanyInTasksEntity();
        if (!(paramType.equalsIgnoreCase("displayByContact") || paramType.equalsIgnoreCase("displayByCompany"))) {
            this.tasksEntitiesDueToday = new ArrayList<>();
            this.tasksEntitiesToLate = new ArrayList<>();
            this.tasksEntitiesToCome = new ArrayList<>();
            this.tasksEntitiesFinished = new ArrayList<>();
        }
    }

    /**
     * Check type Param on create Tasks and set Contact or Company entity in NotesEntity !!
     */
    public void checkTypeParamAndSetContactOrCompanyInTasksEntity() {
        if (this.paramType.equalsIgnoreCase("displayByContact")) {
            setContactEntityInTasksEntity();
        } else if (this.paramType.equalsIgnoreCase("displayByCompany")) {
            setCompanyEntityInTasksEntity();
        }
    }

    /**
     * Set ContactEntity in TasksEntity
     */
    public void setContactEntityInTasksEntity() {
        log.info("NotesBean => method : setContactEntityInTasksEntity()");
        try {
            this.tasksEntity.setContactsByIdContacts(contactsBean.getContactsEntity());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "contactNotExist");
        }
    }

    /**
     * Set ContactEntity in NotesEntity
     */
    public void setCompanyEntityInTasksEntity() {
        log.info("NotesBean => method : setCompanyEntityInTasksEntity()");
        try {
            this.tasksEntity.setCompaniesByIdCompanies(companiesBean.getCompaniesEntity());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "companyNotExist");
        }
    }

    /**
     * Method for update
     *
     * @param event RowEditEvent<TasksEntity>
     */
    public void onRowEdit(RowEditEvent<TasksEntity> event) {
        log.info("TasksBean => method : onRowEdit(RowEditEvent<TasksEntity> event)");

        int idEntity;

        try {
            idEntity = event.getObject().getId();
        } catch (NumberFormatException exception) {
            log.info(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "tasks.notExist");
            return;
        }
        try {
            findById(idEntity, usersBean.getUsersEntity());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "tasks.notExist");
            return;
        }

        log.info("event : " + event);
        update(event.getObject());

        loadListEntities(getParamType());
    }

    /**
     * On cancel delete
     * @param event         RowEditEvent<TasksEntity>
     */
    public void onRowCancel(RowEditEvent<TasksEntity> event) {
        log.info("TasksBean => method : onRowCancel()");
        getFacesMessage(FacesMessage.SEVERITY_INFO, "canceled");
    }

    /**
     * Method for delete entity
     */
    public void deleteEntity() {
        log.info("TasksBean => method : deleteEntity()");

        try {
            int idEntity = Integer.parseInt(getParam("idEntity"));
            log.info("ID Task : " + idEntity);
            delete(idEntity, usersBean.getUsersEntity());
        } catch (NumberFormatException exception) {
            log.warn(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "errorOccured");
            return;
        }
        loadListEntities(getParamType());
    }

    /**
     * Method to show modal in update task
     */
    public void showModalUpdate() {
        log.info("TasksBean => method : showModalUpdate()");

        int idEntity;
        try {
            idEntity = Integer.parseInt(getParam("idEntity"));
        } catch (
                NumberFormatException exception) {
            log.info(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "tasks.notExist");
            return;
        }
        try {
            tasksEntity = findById(idEntity, usersBean.getUsersEntity());
        } catch (
                NumberFormatException exception) {
            log.warn(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "errorOccured");
        }

    }

    /**
     * Method to show modal in create task
     */
    public void showModalCreate() {
        log.info("TasksBean => method : showModalCreate()");
        tasksEntity = new TasksEntity();
        checkTypeParamAndSetContactOrCompanyInTasksEntity();
    }

    /**
     * Update Entity form
     */
    public void updateEntity() {
        log.info("method : updateEntity()");
        log.info("TaskEntity = : " + tasksEntity);

        update(tasksEntity);

        loadListEntities(getParam("typeEntities"));
    }

    /**
     * Method to change status of task
     *
     * @param event             AjaxBehaviorEvent
     */
    public void updateStatus(AjaxBehaviorEvent event) {
        log.info("TasksBean => method : updateStatus(AjaxBehaviorEvent event)");

        int idEntity;
        try {
            idEntity = (int) event.getComponent().getAttributes().get("idTask");
        } catch (NumberFormatException exception) {
            log.info(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "tasks.notExist");
            return;
        }

        TasksEntity tasksEntityToUpdate;
        try {
            tasksEntityToUpdate = findById(idEntity, usersBean.getUsersEntity());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "tasks.notExist");
            return;
        }

        tasksEntityToUpdate.setStatus(!tasksEntityToUpdate.isStatus());
        update(tasksEntityToUpdate);

        loadListEntities("all");

    }

    /**
     * method to know which entity to reload
     *
     * @param typeLoad              String
     */
    public void loadListEntities(String typeLoad) {

        if (typeLoad.equalsIgnoreCase("displayByCompany")) {
            if (companiesBean.getCompaniesEntity() == null) {
                return;
            }
            log.info("id company : " + companiesBean.getCompaniesEntity().getId());
            log.info("id User : " + usersBean.getUsersEntity().getId());

            this.tasksEntities = findTasksEntityByCompaniesByIdCompanies(companiesBean.getCompaniesEntity(), usersBean.getUsersEntity());
        } else if (typeLoad.equalsIgnoreCase("displayByContact")) {
            if (contactsBean.getContactsEntity() == null) {
                return;
            }
            log.info("id contact : " + contactsBean.getContactsEntity().getId());
            log.info("id User : " + usersBean.getUsersEntity().getId());

            this.tasksEntities = findTasksEntityByContactsByIdContacts(contactsBean.getContactsEntity(), usersBean.getUsersEntity());
        } else if (typeLoad.equalsIgnoreCase("displayToday")) {
            LocalDateTime dateTime = LocalDate.now().atTime(0, 0, 0, 0).plusDays(1);

            this.tasksEntities = findAll(usersBean.getUsersEntity());
            this.tasksEntitiesDueToday = this.tasksEntities.stream().filter(t -> t.getEndDate() != null && t.getEndDate().isBefore(dateTime) && t.getEndDate().isAfter(LocalDateTime.now())).collect(Collectors.toList());
        } else if (typeLoad.equalsIgnoreCase("all")) {

            LocalDateTime dateTime = LocalDate.now().atTime(0, 0, 0, 0).plusDays(1);

            this.tasksEntities = findAll(usersBean.getUsersEntity());

            this.tasksEntitiesToCome = tasksEntities.stream().filter(t -> t.getEndDate() != null && t.getEndDate().isAfter(LocalDateTime.now())).collect(Collectors.toList());
            this.tasksEntitiesDueToday = tasksEntities.stream().filter(t -> t.getEndDate() != null && t.getEndDate().isBefore(dateTime) && t.getEndDate().isAfter(LocalDateTime.now())).collect(Collectors.toList());
            this.tasksEntitiesToLate = tasksEntities.stream().filter(t -> t.getEndDate() != null && t.getEndDate().isBefore(LocalDateTime.now())).collect(Collectors.toList());

            this.tasksEntitiesFinished = findTasksFinished(usersBean.getUsersEntity());
        }
    }

    /**
     * Fill the calendar with tasks
     */
    public void setCalendar() {
        eventModel = new DefaultScheduleModel();

        for (TasksEntity t : tasksEntities) {
            if (t.getEndDate() != null && !t.isStatus()) {
                DefaultScheduleEvent event = DefaultScheduleEvent.builder()
                        .title(t.getTitle())
                        .startDate(t.getCreationDate())
                        .endDate(t.getEndDate())
                        .description(t.getDescription())
                        .overlapAllowed(true)
                        .build();
                eventModel.addEvent(event);
            }
        }
    }

    /**
     * Save Task Entity
     *
     * @param entity            TasksEntity
     */
    protected void save(TasksEntity entity) {
        log.info("TasksBean => method : save(TasksEntity entity)");

        try {
            accessControlBean.checkPermission("addTasks");
        } catch (AccessDeniedException exception) {
            log.info(exception.getMessage());
            accessControlBean.hasNotPermission();
            return;
        }

        try {
            validateTask(entity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        entity.setCreationDate(LocalDateTime.now());

        if (entity.getEndDate() != null) {
            try {
                validateTaskDateEnd(entity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "task.validator.dateEnd");
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

        if (entity.getTaskTypesByIdTaskTypes() != null) {
            try {
                checkEntities.checkTaskType(entity.getTaskTypesByIdTaskTypes());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "taskTypeNotExist");
                return;
            }
        }

        entity.setStatus(false);

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.save(em, entity);
            tx.commit();
            log.info("Persist ok");
            getFacesMessage(FacesMessage.SEVERITY_INFO, "tasks.saved");
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
     * Find Task by ID
     *
     * @param id                int
     * @param usersEntity       UsersEntity
     * @return Task             TasksEntity
     */
    protected TasksEntity findById(int id, UsersEntity usersEntity) {
        log.info("TasksBean => method : findById(int id, UsersEntity usersEntity)");

        if (id == 0) {
            log.error("Task ID is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "tasks.notExist");
            throw new EntityNotFoundException(
                    "L ID de la tache est incorrect", ErrorCodes.TASK_NOT_FOUND
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
        Optional<TasksEntity> optionalTasksEntity;
        try {
            optionalTasksEntity = dao.findById(em, id, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return optionalTasksEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune tache avec l'ID " + id + " n a ete trouve dans la BDD",
                        ErrorCodes.TASK_NOT_FOUND
                ));
    }

    /**
     * Find tasks entities by id contact
     *
     * @param contactsEntity        ContactsEntity
     * @param usersEntity           UsersEntity
     * @return List TasksEntities
     */
    protected List<TasksEntity> findTasksEntityByContactsByIdContacts(ContactsEntity contactsEntity, UsersEntity usersEntity) {
        log.info("TasksBean => method : findTasksEntityByContactsByIdContacts(ContactsEntity contactsEntity, UsersEntity usersEntity)");

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

        List<TasksEntity> tasksEntities;
        try {
            tasksEntities = dao.findTasksEntityByContactsByIdContacts(em, contactsEntity.getId(), usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return tasksEntities;
    }

    /**
     * Find tasks entities by id company
     *
     * @param companiesEntity           CompaniesEntity
     * @param usersEntity               UsersEntity
     * @return List TasksEntities
     */
    protected List<TasksEntity> findTasksEntityByCompaniesByIdCompanies(CompaniesEntity companiesEntity, UsersEntity usersEntity) {
        log.info("TasksBean => method : findTasksEntityByCompaniesByIdCompanies(CompaniesEntity companiesEntity, UsersEntity usersEntity)");

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
        List<TasksEntity> tasksEntities;
        try {
            tasksEntities = dao.findTasksEntityByCompaniesByIdCompanies(em, companiesEntity.getId(), usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return tasksEntities;
    }


    /**
     * Find All tasks Entities
     * @param usersEntity               UsersEntity
     * @return List TasksEntity
     */
    protected List<TasksEntity> findAll(UsersEntity usersEntity) {
        log.info("TasksBean => method : findAll(UsersEntity usersEntity)");

        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return Collections.emptyList();
        }
        EntityManager em = EMF.getEM();
        List<TasksEntity> tasksEntities;
        try {
            tasksEntities = dao.findAll(em, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return tasksEntities;
    }

    /**
     * Find all tasks finished
     * @param usersEntity               UsersEntity
     */
    protected List<TasksEntity> findTasksFinished(UsersEntity usersEntity) {
        log.info("TasksBean => method : findTasksFinished(UsersEntity usersEntity)");

        if (usersEntity == null) {
            log.error("User ID is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return Collections.emptyList();
        }
        EntityManager em = EMF.getEM();
        List<TasksEntity> tasksEntities;
        try {
            tasksEntities = dao.findTasksFinished(em, usersEntity.getId());
        } finally {
            em.clear();
            em.close();
        }

        return tasksEntities;
    }

    /**
     * delete task by id
     *
     * @param id                        Task
     * @param usersEntity               UsersEntity
     */
    protected void delete(int id, UsersEntity usersEntity) {
        log.info("TasksBean => method : delete(int id, UsersEntity usersEntity)");

        try {
            accessControlBean.checkPermission("deleteTasks");
        } catch (AccessDeniedException exception) {
            log.info(exception.getMessage());
            accessControlBean.hasNotPermission();
            return;
        }

        if (id == 0) {
            log.error("Task ID is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "tasks.notExist");
            return;
        }
        if (usersEntity == null) {
            log.error("User Entity is null");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "userNotExist");
            return;
        }

        TasksEntity tasksEntityToDelete;

        try {
            tasksEntityToDelete = findById(id, usersEntity);
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "tasks.notExist");
            return;
        }

        EntityManager em = EMF.getEM();
        em.getTransaction();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.delete(em, tasksEntityToDelete);
            tx.commit();
            log.info("Delete ok");
            getFacesMessage(FacesMessage.SEVERITY_INFO, "tasks.deleted");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.error("Delete Error");
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "errorOccured");
        } finally {
            em.clear();
            em.close();
        }
    }


    /**
     * Update TasksEntity
     *
     * @param entity                TasksEntity
     */
    protected void update(TasksEntity entity) {
        log.info("TasksBean => method : update(TasksEntity entity)");
        try {
            accessControlBean.checkPermission("updateTasks");
        } catch (AccessDeniedException exception) {
            log.info(exception.getMessage());
            accessControlBean.hasNotPermission();
            return;
        }
        try {
            validateTask(entity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        try {
            TasksEntity tasksEntityToFind = findById(entity.getId(), entity.getUsersByIdUsers());
            entity.setCreationDate(tasksEntityToFind.getCreationDate());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "tasks.notExist");
            return;
        }

        if (entity.getEndDate() != null) {
            try {
                validateTaskDateEnd(entity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "task.validator.dateEnd");
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

        if (entity.getTaskTypesByIdTaskTypes() != null) {
            try {
                checkEntities.checkTaskType(entity.getTaskTypesByIdTaskTypes());
            } catch (EntityNotFoundException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                getFacesMessage(FacesMessage.SEVERITY_ERROR, "taskTypeNotExist");
                return;
            }
        }

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.update(em, entity);
            tx.commit();
            log.info("Update ok");
            getFacesMessage(FacesMessage.SEVERITY_INFO, "tasks.updated");
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
     * Validate Task !
     *
     * @param entity            TasksEntity
     */
    private void validateTask(TasksEntity entity) {
        log.info("TasksBean => method : validateTask(TasksEntity entity)");

        List<String> errors = TasksValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("Task is not valide {}", entity);
            throw new InvalidEntityException("La tache n est pas valide", ErrorCodes.TASK_NOT_VALID, errors);
        }
    }

    /**
     * Valide Task date End
     *
     * @param entity            TasksEntity
     */
    private void validateTaskDateEnd(TasksEntity entity) {
        log.info("TasksBean => method : validateTaskDateEnd(TasksEntity entity)");

        if (entity.getEndDate() != null) {
            if (entity.getEndDate().isBefore(entity.getCreationDate())) {
                log.error("Task end date in not valide {}", entity);
                throw new InvalidEntityException("La date de fin de tâche doit etre superieur à la date de creation", ErrorCodes.TASK_NOT_VALID);
            }
        }
    }
}
