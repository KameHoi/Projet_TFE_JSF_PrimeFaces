package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.BranchActivitiesDao;
import be.atc.salesmanagercrm.dao.impl.BranchActivitiesDaoImpl;
import be.atc.salesmanagercrm.entities.BranchActivitiesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.utils.JsfUtils;
import be.atc.salesmanagercrm.validators.BranchActivitiesValidator;
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
@Named(value = "branchActivitiesBean")
@ViewScoped
public class BranchActivitiesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 8716685401909061214L;
    @Getter
    @Setter
    private BranchActivitiesDao branchActivitiesDao = new BranchActivitiesDaoImpl();

    @Getter
    @Setter
    private BranchActivitiesEntity branchActivitiesEntity = new BranchActivitiesEntity();

    @Getter
    @Setter
    private List<BranchActivitiesEntity> branchActivitiesEntityList;

    @Getter
    @Setter
    private BranchActivitiesEntity selectedBranchActivitiesEntity;

    @Getter
    @Setter
    private List<BranchActivitiesEntity> branchActivitiesEntitiesFiltered;

    /**
     * Public method that call either addBranchActivities if sendType=add or updateBranchActivitiesLabel if sendType=edit
     */
    public void saveBranchActivities() {

        addBranchActivities(branchActivitiesEntity);
        findAllBranchActivities();
    }


    /**
     * Save branch activities title
     *
     * @param branchActivitiesEntity BranchActitivities
     */
    protected void addBranchActivities(BranchActivitiesEntity branchActivitiesEntity) {

        log.info("BranchActivitiesBean => method : addBranchActivities()");

        try {
            validateBranchActivities(branchActivitiesEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        FacesMessage facesMessage;
        CheckEntities checkEntities = new CheckEntities();

        try {
            checkEntities.checkBranchActivitiesLabel(branchActivitiesEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "branchActivities.labelExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            branchActivitiesDao.add(em, branchActivitiesEntity);
            tx.commit();
            log.info("Persist ok");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "branchActivities.save"), null);
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
     * public method that call findAll
     */
    public void findAllBranchActivities() {
        log.info("BranchActivitiesBean => method : findAllBranchActivities()");

        branchActivitiesEntityList = findAll();
    }

    /**
     * Find all Branch activities
     *
     * @return List of BranchActivities
     */
    protected List<BranchActivitiesEntity> findAll() {
        log.info("BranchActivitiesBean => method : findAll()");

        EntityManager em = EMF.getEM();
        List<BranchActivitiesEntity> branchActivitiesEntities = branchActivitiesDao.findAll();
        em.clear();
        em.close();
        return branchActivitiesEntities;
    }

    /**
     * Find all BranchActivities entities
     *
     * @return List BranchActivitiesEntity
     */
    public List<BranchActivitiesEntity> findBranchActvivitiesList() {
        log.info("BranchActivitiesBean => method : findBranchActvivitiesList()");

        EntityManager em = EMF.getEM();

        List<BranchActivitiesEntity> branchActivitiesEntities = branchActivitiesDao.findAll();

        em.clear();
        em.close();

        return branchActivitiesEntities;
    }

    /**
     * Find Branch Activity by ID
     *
     * @param id BranchActivities
     * @return BranchActivitiesEntity
     */
    public BranchActivitiesEntity findById(int id) {
        log.info("BranchActivitiesBean => method : findById()");

        FacesMessage facesMessage;

        if (id == 0) {
            log.error("Branch_Activities ID is null");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "branchActivities.error"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return null;
        }

        EntityManager em = EMF.getEM();
        Optional<BranchActivitiesEntity> optionalBranchActivitiesEntity;
        try {
            optionalBranchActivitiesEntity = Optional.ofNullable(branchActivitiesDao.findById(em, id));
        } finally {
            em.clear();
            em.close();
        }

        return optionalBranchActivitiesEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun secteur d'activié avec l ID " + id + " n'a été trouve dans la DB",
                        ErrorCodes.BRANCHACTIVITIES_NOT_FOUND
                ));
    }

    /**
     * Public method that call updateBranchActivitiesLabel
     */
    public void updateBranchActivities() {
        log.info("BranchActivitiesBean => method : updateBranchActivities()");
        updateBranchActivitiesLabel(branchActivitiesEntity);
        findAllBranchActivities();
    }

    /**
     * Update BranchActivities
     *
     * @param branchActivitiesEntity BranchActivities
     */
    protected void updateBranchActivitiesLabel(BranchActivitiesEntity branchActivitiesEntity) {
        log.info("BranchActivitiesBean => method : updateBranchActivitiesLabel()");

        try {
            validateBranchActivities(branchActivitiesEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        FacesMessage facesMessage;

        try {
            findById(branchActivitiesEntity.getId());
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "branchActivities.error"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        CheckEntities checkEntities = new CheckEntities();

        try {
            checkEntities.checkBranchActivitiesLabel(branchActivitiesEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "branchActivities.labelExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            branchActivitiesDao.update(em, branchActivitiesEntity);
            tx.commit();
            log.info("Update ok");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "branchActivities.updated"), null);
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
     * Auto complete for branchActivivites
     *
     * @param search String
     * @return list of BranchActivitiesEntity
     */
    public List<BranchActivitiesEntity> completeBranchActivities(String search) {
        log.info("BranchActivitiesBean => method : completeBranchActivities()");

        String searchLowerCase = search.toLowerCase();

        List<BranchActivitiesEntity> companiesEntitiesDropDown = findBranchActvivitiesList();

        return companiesEntitiesDropDown.stream().filter(t -> t.getLabel().toLowerCase().contains(searchLowerCase)).collect(Collectors.toList());

    }

    /**
     * Method to show modal in create branchActivities
     */
    public void showModalCreate() {
        log.info("BranchActivitiesBean => method : showModalCreate()");
        branchActivitiesEntity = new BranchActivitiesEntity();
    }

    /**
     * Method to show modal in update branchActivities
     */
    public void showModalUpdate() {
        log.info("BranchActivitiesBean => method : showModalUpdate()");
        try {
            int idBranchActivities = Integer.parseInt(getParam("id"));
            branchActivitiesEntity = findById(idBranchActivities);
        } catch (NumberFormatException exception) {
            log.warn(exception.getMessage());
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    /**
     * Sort BranchActivities by group in form
     *
     * @param entityGroup BranchActivitiesEntity
     * @return label of entitygroup
     */

    public char getBranchActivitiesEntityGroup(BranchActivitiesEntity entityGroup) {
        log.info("BranchActivitiesBean => method : getBranchActivitiesEntityGroup()");
        return entityGroup.getLabel().charAt(0);
    }


    /**
     * Validate BranchActivities !
     *
     * @param entity BranchActivities
     */
    private void validateBranchActivities(BranchActivitiesEntity entity) {
        log.info("BranchActivitiesBean => method : validateBranchActivities()");
        List<String> errors = BranchActivitiesValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("BranchActivities is not valid {}", entity);
            throw new InvalidEntityException("Le secteur d'activité n'est pas valide", ErrorCodes.BRANCHACTIVITIESLABEL_NOT_VALID, errors);
        }
    }
}
