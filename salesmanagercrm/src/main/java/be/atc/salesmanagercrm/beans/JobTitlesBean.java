package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.JobTitlesDao;
import be.atc.salesmanagercrm.dao.impl.JobTitlesDaoImpl;
import be.atc.salesmanagercrm.entities.JobTitlesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.utils.JsfUtils;
import be.atc.salesmanagercrm.validators.JobTitlesValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Maximilien Zabbara
 */
@Slf4j
@Named(value = "jobtitlesBean")
@ViewScoped
public class JobTitlesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 7299153922194330761L;
    @Getter
    @Setter
    private JobTitlesDao jobTitlesDao = new JobTitlesDaoImpl();

    @Getter
    @Setter
    private JobTitlesEntity jobTitlesEntity = new JobTitlesEntity();

    @Getter
    @Setter
    private List<JobTitlesEntity> jobTitlesEntityList;

    @Getter
    @Setter
    private JobTitlesEntity selectedJobTitlesEntity;

    @Getter
    @Setter
    private List<JobTitlesEntity> jobTitlesEntitiesFiltered;


    /**
     * Public method that call addJobTitle
     */
    public void saveJobTitles() {
        log.info("JobTitlesBean => method : saveJobTitles()");

        addJobTitle(jobTitlesEntity);

        findAllJobTitles();
    }

    /**
     * Save job title
     *
     * @param jobTitlesEntity JobTitlesEntity
     */
    protected void addJobTitle(JobTitlesEntity jobTitlesEntity) {
        log.info("JobTitlesBean => method : addJobTitle()");

        try {
            validateJobTitles(jobTitlesEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        FacesMessage facesMessage;
        CheckEntities checkEntities = new CheckEntities();

        try {
            checkEntities.checkJobTitlesLabel(jobTitlesEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "jobTitles.labelExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            jobTitlesDao.add(em, jobTitlesEntity);
            tx.commit();
            log.info("Persist ok");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "jobTitles.save"), null);
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
     * Find job title by id
     *
     * @param id JobTitles
     * @return JobTitlesEntity
     */
    public JobTitlesEntity findById(int id) {
        log.info("JobTitlesBean => method : findById()");

        FacesMessage facesMessage;

        if (id == 0) {
            log.error("Job_Titles ID is null");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "jobTitles.notExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return null;
        }

        EntityManager em = EMF.getEM();
        Optional<JobTitlesEntity> optionalJobTitlesEntity;

        try {
            optionalJobTitlesEntity = Optional.ofNullable(jobTitlesDao.findById(em, id));
        } finally {
            em.clear();
            em.close();
        }
        return optionalJobTitlesEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun intitulé de poste avec l ID " + id + " n'a ete trouve dans la DB",
                        ErrorCodes.JOBTITLES_NOT_FOUND
                ));
    }

    /**
     * Method that call findAll and fill jobTitlesEntityList
     */
    public void findAllJobTitles() {
        log.info("JobTitlesBean => method : findAllJobTitles()");

        jobTitlesEntityList = findAll();
    }


    /**
     * Find all jobtitles entities
     *
     * @return List of JobTitles Entity
     */
    protected List<JobTitlesEntity> findAll() {
        log.info("JobTitlesBean => method : findAll()");

        EntityManager em = EMF.getEM();
        List<JobTitlesEntity> jobTitlesEntities = jobTitlesDao.findAll();

        em.clear();
        em.close();

        return jobTitlesEntities;
    }

    /**
     * Public method that call update
     */
    public void updateJobTitles() {
        log.info("JobTitlesBean => method : updateJobTitles()");

        update(jobTitlesEntity);
        findAllJobTitles();
    }

    /**
     * Update JobTitles
     *
     * @param jobTitlesEntity JobTitlesEntity
     */
    protected void update(JobTitlesEntity jobTitlesEntity) {
        log.info("JobTitlesBean => method : update()");

        try {
            validateJobTitles(jobTitlesEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        FacesMessage facesMessage;

        try {
            findById(jobTitlesEntity.getId());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "jobTitles.notExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        CheckEntities checkEntities = new CheckEntities();

        try {
            checkEntities.checkJobTitlesLabel(jobTitlesEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "jobTitles.labelExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            jobTitlesDao.update(em, jobTitlesEntity);
            tx.commit();
            log.info("Update ok");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "jobTitles.updated"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Update echec");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } finally {
            em.clear();
            em.clear();
        }

    }

    /**
     * Validate JobTitles !
     *
     * @param entity JobTitles
     */
    private void validateJobTitles(JobTitlesEntity entity) {
        log.info("JobTitlesBean => method : validateJobTitles()");
        List<String> errors = JobTitlesValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("Job title is not valid {}", entity);
            throw new InvalidEntityException("L'intitulé du poste n'est pas valide", ErrorCodes.JOBTITLES_NOT_VALID, errors);
        }
    }

    /**
     * Auto complete for JobTitlesEntity
     *
     * @param search String
     * @return list of JobTitlesEntity
     */
    public List<JobTitlesEntity> completeJobTitles(String search) {
        log.info("JobTitlesBean => method : completeJobTitles()");

        String searchLowerCase = search.toLowerCase();

        List<JobTitlesEntity> jobTitlesEntitiesDropdown = jobTitlesDao.findAll();

        return jobTitlesEntitiesDropdown.stream().filter(t -> t.getLabel().toLowerCase().contains(searchLowerCase)).collect(Collectors.toList());
    }

    /**
     * Method to show modal in update jobTitles
     */
    public void showModalUpdate() {
        log.info("JobTitlesBean => method : showModalUpdate()");
        try {
            int idJobTitle = Integer.parseInt(getParam("id"));
            jobTitlesEntity = findById(idJobTitle);
        } catch (NumberFormatException exception) {
            log.warn(exception.getMessage());
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    /**
     * Method to show modal in create jobtitles
     */
    public void showModalCreate() {
        log.info("JobtitlesBean => method : showModalCreate()");
        jobTitlesEntity = new JobTitlesEntity();
    }


}
