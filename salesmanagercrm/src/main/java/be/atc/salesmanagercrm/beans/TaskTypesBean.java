package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.TaskTypesDao;
import be.atc.salesmanagercrm.dao.impl.TaskTypesDaoImpl;
import be.atc.salesmanagercrm.entities.TaskTypesEntity;
import be.atc.salesmanagercrm.exceptions.AccessDeniedException;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.validators.TaskTypesValidator;
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
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
@Slf4j
@Named(value = "taskTypesBean")
@ViewScoped
public class TaskTypesBean extends ExtendBean implements Serializable {


    private static final long serialVersionUID = 6295899008521191899L;

    @Getter
    @Setter
    private TaskTypesDao dao = new TaskTypesDaoImpl();

    @Getter
    @Setter
    private TaskTypesEntity taskTypesEntity;
    @Getter
    @Setter
    private TaskTypesEntity selectedTaskTypeEntity;
    @Getter
    @Setter
    private List<TaskTypesEntity> taskTypesEntities;
    @Getter
    @Setter
    private List<TaskTypesEntity> taskTypesEntitiesFiltered;

    @Inject
    private AccessControlBean accessControlBean;

    /**
     * Method to show modal in create TaskTypes
     */
    public void showModalCreate() {
        log.info("TaskTypesBean => method : showModalCreate()");

        taskTypesEntity = new TaskTypesEntity();
    }

    /**
     * Update entity form
     */
    public void showModalUpdate() {
        log.info("TaskTypesBean => method : showModalUpdate()");

        log.info("param : " + getParam("idEntity"));

        int idEntity;
        try {
            idEntity = Integer.parseInt(getParam("idEntity"));
        } catch (NumberFormatException exception) {
            log.info(exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "taskTypes.notExist");
            return;
        }

        try {
            this.taskTypesEntity = findById(idEntity);
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_WARN, "taskTypes.notExist");
        }

    }

    /**
     * Fill the list with Task Entities
     */
    public void findAllTaskTypesEntities() {
        log.info("TaskTypesBean => method : findAllTaskTypesEntities()");

        taskTypesEntities = findAll();
    }


    /**
     * Save entity form
     */
    public void saveEntity() {
        log.info("TaskTypesBean => method : saveEntity()");

        log.info("TaskTypesEntity = : " + taskTypesEntity);


        save(taskTypesEntity);
        findAllEntities();

    }

    /**
     * Update Entity form
     */
    public void updateEntity() {
        log.info("TaskTypesBean => method : updateEntity()");

        log.info("taskTypesEntity = : " + this.taskTypesEntity);

        update(this.taskTypesEntity);

        findAllEntities();
    }

    /**
     * Find All TaskTypess and filter
     */
    public void findAllEntities() {
        log.info("TaskTypesBean => method : findAllEntities()");

        this.taskTypesEntities = findAll();
    }

    /**
     * Save TaskType Entity
     *
     * @param entity TaskTypesEntity
     */
    protected void save(TaskTypesEntity entity) {
        log.info("TaskTypesBean => method : save(TaskTypesEntity entity)");

        try {
            accessControlBean.checkPermission("addTaskTypes");
        } catch (AccessDeniedException exception) {
            log.info(exception.getMessage());
            accessControlBean.hasNotPermission();
            return;
        }

        try {
            validateTaskType(entity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.save(em, entity);
            tx.commit();
            log.info("Persist ok");
            getFacesMessage(FacesMessage.SEVERITY_INFO, "taskTypes.saved");
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
     * Find Tasktype by ID
     *
     * @param id        TaskTypesEntity
     * @return TaskTypesEntity
     */
    protected TaskTypesEntity findById(int id) {
        log.info("TaskTypesBean => method : findById(int id)");

        if (id == 0) {
            log.error("TaskType ID is null");
            throw new EntityNotFoundException(
                    "L ID du type de tache est incorrect",
                    ErrorCodes.TASKTYPE_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Optional<TaskTypesEntity> optionalTasksEntity;
        try {
            optionalTasksEntity = Optional.ofNullable(dao.findById(em, id));
        } finally {
            em.clear();
            em.close();
        }
        return optionalTasksEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun type de tâche avec l'ID " + id + " n a ete trouve dans la BDD",
                        ErrorCodes.TASKTYPE_NOT_FOUND
                ));
    }

    /**
     * Find Tasktype by LABEL
     *
     * @param label             TaskTypesEntity
     * @return TaskTypesEntity
     */
    public TaskTypesEntity findByLabel(String label) {
        log.info("TaskTypesBean => method : findByLabel(String label)");

        if (label == null) {
            log.error("TaskType label is null");
            throw new EntityNotFoundException(
                    "Le Label du type de tache est incorrect",
                    ErrorCodes.TASKTYPE_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Optional<TaskTypesEntity> optionalTasksEntity;
        try {
            optionalTasksEntity = dao.findByLabel(em, label);
        } finally {
            em.clear();
            em.close();
        }
        return optionalTasksEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun type de tâche avec le label " + label + " n a ete trouve dans la BDD",
                        ErrorCodes.TASKTYPE_NOT_FOUND
                ));
    }


    /**
     * Update TasksEntity
     *
     * @param entity            TasksEntity
     */
    protected void update(TaskTypesEntity entity) {
        log.info("TaskTypesBean => method : update(TaskTypesEntity entity)");

        try {
            accessControlBean.checkPermission("updateTaskTypes");
        } catch (AccessDeniedException exception) {
            log.info(exception.getMessage());
            accessControlBean.hasNotPermission();
            return;
        }

        try {
            validateTaskType(entity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            return;
        }

        try {
            findById(entity.getId());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            getFacesMessage(FacesMessage.SEVERITY_ERROR, "taskTypes.notExist");
            return;
        }

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.update(em, entity);
            tx.commit();
            log.info("Update ok");
            getFacesMessage(FacesMessage.SEVERITY_INFO, "taskTypes.updated");
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
     * Find All task types Entities
     *
     * @return List             TaskTypesEntity
     */
    protected List<TaskTypesEntity> findAll() {
        log.info("TaskTypesBean => method : findAll()");

        return dao.findAll();
    }

    /**
     * Validate TaskTypes !
     *
     * @param entity            TaskTypesEntity
     */
    private void validateTaskType(TaskTypesEntity entity) {
        log.info("TaskTypesBean => method : validateTaskType(TaskTypesEntity entity)");

        List<String> errors = TaskTypesValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("TaskType is not valide {}", entity);
            throw new InvalidEntityException("Le type de tache n est pas valide", ErrorCodes.TASKTYPE_NOT_VALID, errors);
        }
    }
}
