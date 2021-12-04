package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.RolesDao;
import be.atc.salesmanagercrm.dao.impl.RolesDaoImpl;
import be.atc.salesmanagercrm.entities.RolesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.utils.JsfUtils;
import be.atc.salesmanagercrm.validators.RolesValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;


/**
 * @author Larché Marie-Élise
 */
@Slf4j
@Named(value = "rolesBean")
@SessionScoped
public class RolesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -2338626292552177485L;

    @Getter
    @Setter
    private RolesDao dao = new RolesDaoImpl();
    @Getter
    @Setter
    private RolesEntity rolesEntity = new RolesEntity();
    @Getter
    @Setter
    private List<RolesEntity> rolesEntityList;
    @Getter
    @Setter
    private RolesEntity roleForDialog;

    @Getter
    @Setter
    private RolesEntity selectedRoleEntity;

    @Getter
    @Setter
    private List<RolesEntity> rolesEntitiesFiltered;
    @Getter
    @Setter
    private List<RolesEntity> rolesEntities;
    @Inject
    private UsersBean usersBean;

    @Getter
    @Setter
    private RolesEntity rolesEntityNew = new RolesEntity();

    @Getter
    @Setter
    private Subject currentUser;
    @Getter
    @Setter
    private Session session;

    /**
     * call modal
     *
     * @param idRole int
     */
    public void initialiseDialogRoleId(Integer idRole) {
        if (idRole == null || idRole < 1) {
            return;
        }
        roleForDialog = getDao().findById(EMF.getEM(), idRole);
        rolesEntity = dao.findById(EMF.getEM(), idRole);
    }

    /**
     * set roleEntityNew to new
     */
    public void createNewRoleEntity() {
        rolesEntityNew = new RolesEntity();
    }

    /**
     * find a role with an id
     *
     * @param em EntityManager
     * @param id oint
     * @return RolesEntity
     */
    public RolesEntity findById(EntityManager em, int id) {
        return dao.findById(em, id);
    }

    /**
     * call findAllActive
     */
    public void findAllActiveRoles() {
        rolesEntityList = findAllActive();
        log.info(String.valueOf(rolesEntityList));
    }

    /**
     * find all active roles
     *
     * @return List<RolesEntity>
     */
    protected List<RolesEntity> findAllActive() {
        log.info("begin findallactive");
        EntityManager em = EMF.getEM();
        List<RolesEntity> rolesEntities = dao.findAllRolesActive(em);

        em.clear();
        em.close();

        return rolesEntities;
    }

    /**
     * call findAll()
     */
    public void findAllRoles() {
        log.info("bgin findallrole " + usersBean.getUsersEntity().getId());

        rolesEntityList = findAll();
        log.info(String.valueOf(rolesEntityList));
        log.info("Tu as l'autorisation Test.");
    }

    /**
     * find all roles order by active
     *
     * @return List<RolesEntity>
     */
    protected List<RolesEntity> findAll() {
        log.info("begin findall");
        EntityManager em = EMF.getEM();
        List<RolesEntity> rolesEntities = dao.findAllRoles(em);

        em.clear();
        em.close();

        return rolesEntities;
    }

    /**
     * find a role with a label
     *
     * @param label String
     * @return RolesEntity
     */
    public RolesEntity findByLabel(String label) {
        if (label == null) {
            log.info("label null in rolebean");
            return null;
        }

        EntityManager em = EMF.getEM();

        try {
            return dao.findByLabel(em, label);
        } catch (EntityNotFoundException exception) {
            log.info("nothing in roles bean");
            throw new EntityNotFoundException("aucun role avec le label " + label + " n'a été trouvé en db", ErrorCodes.ROLES_NOT_FOUND);
        } finally {
            em.clear();
            em.close();
        }

    }

    /**
     * call addRoles
     */
    public void addEntity() {
        addRoles(this.rolesEntityNew);
        rolesEntityList = findAll();
    }

    /**
     * create a role
     *
     * @param rolesEntity RolesEntity
     */
    public void addRoles(RolesEntity rolesEntity) {
        FacesMessage msg;

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        this.currentUser = SecurityUtils.getSubject();
        log.info(String.valueOf(currentUser));
        this.session = currentUser.getSession();
        if (this.currentUser.isPermitted("addRoles")) {

            try {
                validateRoles(rolesEntity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
                return;
            }

            CheckEntities checkEntities = new CheckEntities();
            try {
                checkEntities.checkRoleByLabel(rolesEntity);
            } catch (InvalidEntityException exception) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "roles.labelExist"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
//            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
                return;
            }
            rolesEntity.setActive(true);

            EntityManager em = EMF.getEM();
            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();
                dao.register(em, rolesEntity);
                tx.commit();
                log.info("Persist ok");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "role.createOk"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception ex) {
                if (tx != null && tx.isActive()) tx.rollback();
                log.info("Persist echec");
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } finally {
                em.clear();
                em.clear();
            }
        } else {
            log.info("Sorry, you aren't permissions.");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "accessDenied.label"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    /**
     * call updateRoles();
     */
    public void updateEntity() {
        updateRole(this.rolesEntity);
        rolesEntityList = findAll();
    }


    /**
     * update a role
     *
     * @param rolesEntity RolesEntity
     */
    protected void updateRole(RolesEntity rolesEntity) {
        FacesMessage msg;

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        this.currentUser = SecurityUtils.getSubject();
        log.info(String.valueOf(currentUser));
        this.session = currentUser.getSession();
        if (this.currentUser.isPermitted("updateRolesPermissions")) {

            try {
                validateRoles(rolesEntity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
                return;
            }

            CheckEntities checkEntities = new CheckEntities();
            try {
                checkEntities.checkRoleByLabel(rolesEntity);
            } catch (InvalidEntityException exception) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "roles.labelExist"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
                return;
            }
            rolesEntity.setActive(true);

            EntityManager em = EMF.getEM();
            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();
                dao.update(em, rolesEntity);
                tx.commit();
                log.info("Persist ok");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "role.updateOk"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception ex) {
                if (tx != null && tx.isActive()) tx.rollback();
                log.info("Persist echec");
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } finally {
                em.clear();
                em.clear();
            }

        } else {
            log.info("Sorry, you aren't permissions.");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "accessDenied.label"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * delete a role
     *
     * @param id int
     */
    public void delete(int id) {
        FacesMessage msg;
        log.info(String.valueOf(id));

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        this.currentUser = SecurityUtils.getSubject();
        log.info(String.valueOf(currentUser));
        this.session = currentUser.getSession();
        if (this.currentUser.isPermitted("deleteRoles")) {
            EntityManager em = EMF.getEM();
            RolesEntity rolesEntity1 = dao.findById(em, id);

            if (rolesEntity1.getId() == 1) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "roleAdmin"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }

            List<RolesEntity> rolesEntityList2 = dao.findForDeleteSafe(em, rolesEntity1.getId());
            log.info(String.valueOf(rolesEntityList2));

            if (dao.findForDeleteSafe(em, rolesEntity1.getId()) == null || dao.findForDeleteSafe(em, rolesEntity1.getId()).size() == 0) {
                rolesEntity1.setActive(false);

                EntityTransaction tx = null;
                try {
                    tx = em.getTransaction();
                    tx.begin();
                    dao.update(em, rolesEntity1);
                    tx.commit();
                    log.info("Delete ok");
                    rolesEntityList = findAll();
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "role.deleted"), null);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } catch (Exception ex) {
                    if (tx != null && tx.isActive()) tx.rollback();
                    log.error("Delete Error");
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } finally {
                    em.clear();
                    em.close();
                }
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "role.activeUserHaveTheRole"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            log.info("Sorry, you aren't permissions.");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "accessDenied.label"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * active a role delete
     *
     * @param id int
     */
    public void activate(int id) {
        FacesMessage msg;
        log.info(String.valueOf(id));

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        this.currentUser = SecurityUtils.getSubject();
        log.info(String.valueOf(currentUser));
        this.session = currentUser.getSession();
        if (this.currentUser.isPermitted("deleteRoles")) {

            EntityManager em = EMF.getEM();
            RolesEntity rolesEntity1 = dao.findById(em, id);

            rolesEntity1.setActive(true);

            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();
                dao.update(em, rolesEntity1);
                tx.commit();
                log.info("Delete ok");
                rolesEntityList = findAll();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "role.activate"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception ex) {
                if (tx != null && tx.isActive()) tx.rollback();
                log.error("Delete Error");
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } finally {
                em.clear();
                em.close();
            }

        } else {
            log.info("Sorry, you aren't permissions.");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "accessDenied.label"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * verifications for role
     *
     * @param entity RolesEntity
     */
    private void validateRoles(RolesEntity entity) {
        List<String> errors = RolesValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("Register role is not valide {}", entity);
            throw new InvalidEntityException("L'ajou de role n est pas valide", ErrorCodes.USER_NOT_VALID, errors);
        }
    }


}
