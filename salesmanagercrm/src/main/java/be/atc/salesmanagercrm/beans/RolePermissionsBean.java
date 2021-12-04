package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.PermissionsDao;
import be.atc.salesmanagercrm.dao.RolesDao;
import be.atc.salesmanagercrm.dao.RolesPermissionsDao;
import be.atc.salesmanagercrm.dao.impl.PermissionsDaoImpl;
import be.atc.salesmanagercrm.dao.impl.RolesDaoImpl;
import be.atc.salesmanagercrm.dao.impl.RolesPermissionsDaoImpl;
import be.atc.salesmanagercrm.entities.PermissionsEntity;
import be.atc.salesmanagercrm.entities.RolesEntity;
import be.atc.salesmanagercrm.entities.RolesPermissionsEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.utils.JsfUtils;
import be.atc.salesmanagercrm.validators.PermissionsValidator;
import be.atc.salesmanagercrm.validators.RolesPermissionsValidator;
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
import org.primefaces.component.selectoneradio.SelectOneRadio;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * @author Larché Marie-Élise
 */
@Slf4j
@Named(value = "rolesPermissionsBean")
@SessionScoped
public class RolePermissionsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -2338626292552177485L;

    @Getter
    @Setter
    private RolesPermissionsDao dao = new RolesPermissionsDaoImpl();
    @Getter
    @Setter
    private RolesPermissionsEntity rolesPermissionsEntity = new RolesPermissionsEntity();
    @Getter
    @Setter
    private List<RolesPermissionsEntity> rolesPermissionsEntityList;
    @Getter
    @Setter
    private RolesPermissionsEntity rolePermissionsForDialog;
    @Inject
    private PermissionsBean permissionsBean;
    @Getter
    @Setter
    private RolesEntity rolesEntity;
    @Getter
    @Setter
    private PermissionsEntity permissionsEntity;
    @Getter
    @Setter
    private List<Integer> permissionsEntities = new ArrayList<>();
    @Getter
    @Setter
    private PermissionsDao pdao = new PermissionsDaoImpl();
    @Getter
    @Setter
    private RolesDao rdao = new RolesDaoImpl();
    @Getter
    @Setter
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    @Getter
    @Setter
    private List<PermissionsEntity> permissionsEntitiesRole = new ArrayList<>();
    @Getter
    @Setter
    private boolean all;
    @Getter
    @Setter
    private boolean delete;

    @Getter
    @Setter
    private Subject currentUser;
    @Getter
    @Setter
    private Session session;


    /**
     * @param event AjaxBehaviorEvent
     */
    public void selectOrUnselectAllPermissions(AjaxBehaviorEvent event) {
        log.info("RolePermissionsBean : Method => selectAllPermission()");

        SelectOneRadio test = (SelectOneRadio) event.getSource();
        String value = (String) test.getValue();

        if (value.equalsIgnoreCase("select")) {
            permissionsEntitiesRole.addAll(permissionsBean.getPermissionsEntityList());
        }
        if (value.equalsIgnoreCase("unselect")) {
            permissionsEntitiesRole = new ArrayList<>();
        }
    }

    /**
     * @param em EntityManager
     * @param id int
     * @return RolesPermissionsEntity
     */
    public RolesPermissionsEntity findById(EntityManager em, int id) {
        return dao.findById(em, id);
    }


    /**
     * @return List<RolesPermissionsEntity>
     */
    protected List<RolesPermissionsEntity> findAll() {
        log.info("begin findall");
        EntityManager em = EMF.getEM();
        List<RolesPermissionsEntity> rolesPermissionsEntities = dao.findAllRolesPermissions(em);

        em.clear();
        em.close();

        return rolesPermissionsEntities;
    }

    /**
     * is call for the first ID role : admin
     */
    public void findPermissionWhithIdRole() {

        findAllPermissionsIWithdRole(1);
        for (RolesPermissionsEntity rp : rolesPermissionsEntityList) {
            permissionsEntitiesRole.add(rp.getPermissionsByIdPermissions());
        }

    }

    public void createNewEntitiy() {
        this.rolesPermissionsEntity = new RolesPermissionsEntity();
    }

    public void findPermissionWhithIdRoleList() {

        permissionsEntitiesRole = new ArrayList<>();
        findAllPermissionsIWithdRole(rolesPermissionsEntity.getRolesByIdRoles().getId());
        for (RolesPermissionsEntity rp : rolesPermissionsEntityList) {
            permissionsEntitiesRole.add(rp.getPermissionsByIdPermissions());
        }

        log.info("PermissionsEntitiesRole : " + permissionsEntitiesRole.size());

        log.info("list des permissions : " + permissionsBean.getPermissionsEntityList().size());
    }

    /**
     * find all permissions by role
     *
     * @param idRole int
     */
    public void findAllPermissionsIWithdRole(int idRole) {
        log.info("bgin findallrole");
        rolesPermissionsEntityList = findAllWithIdRole(idRole);
        log.info(String.valueOf(rolesPermissionsEntityList));
    }

    protected List<RolesPermissionsEntity> findAllWithIdRole(int idRole) {
        log.info("begin findall");
        EntityManager em = EMF.getEM();
        List<RolesPermissionsEntity> rolesPermissionsEntities = dao.findAllRolesPermissionsWithIdRole(em, idRole);

        em.clear();
        em.close();

        return rolesPermissionsEntities;
    }


    /**
     * va definir si on doit ajouter ou modifier. Si un combo existe, on update. Si un combo avec le role n'esxsiste pas déjà, on va ajouter
     */
    public void manageRolesPermissions() {
        //verifier si un combo existe
        FacesMessage msg;


        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        this.currentUser = SecurityUtils.getSubject();
        log.info(String.valueOf(currentUser));
        this.session = currentUser.getSession();
        if (this.currentUser.isPermitted("updateRolesPermissions") && this.currentUser.isPermitted("addRolesPermissions") && this.currentUser.isPermitted("deleteRolesPermissions")) {

            log.info("log is not checked");
            EntityManager em = EMF.getEM();

            RolesEntity roleEntityId = rdao.findById(em, rolesPermissionsEntity.getRolesByIdRoles().getId());

            try {
                validateRolesPermissions(rolesPermissionsEntity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
                return;
            }

            try {
                validateRoles(rolesPermissionsEntity.getRolesByIdRoles());
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
                return;
            }

            try {
                validatePermissions(permissionsEntitiesRole);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
                return;
            }

            try {
                for (PermissionsEntity p : permissionsEntitiesRole) {
                    permissionsBean.findByLabel(p.getLabel());
                }
            } catch (EntityNotFoundException exception) {
                log.warn("code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }

            List<RolesPermissionsEntity> list = dao.findPermissionWithRole(em, rolesPermissionsEntity.getRolesByIdRoles().getId());

            if (list.size() != 0) {
                deleteRolesPermissions(rolesPermissionsEntity.getRolesByIdRoles().getId());
            }

            boolean com = false;
            for (PermissionsEntity permissionsEntity : permissionsEntitiesRole) {
                String idRoles = String.valueOf(rolesPermissionsEntity.getRolesByIdRoles().getId());
                int idRole = Integer.parseInt(idRoles);

                RolesPermissionsEntity rolesPermissionsEntity = new RolesPermissionsEntity();

                PermissionsEntity permissionsEntityId = pdao.findById(em, permissionsEntity.getId());
                log.info(String.valueOf(dao.getComboRolePerm(em, idRole, permissionsEntity.getId())));

                if (dao.getComboRolePerm(em, idRole, permissionsEntity.getId()) == null) {

                    rolesPermissionsEntity.setPermissionsByIdPermissions(permissionsEntityId);
                    rolesPermissionsEntity.setRolesByIdRoles(roleEntityId);

                    EntityTransaction tx = null;
                    try {
                        tx = em.getTransaction();
                        tx.begin();
                        dao.addRolePermissions(em, rolesPermissionsEntity);
                        tx.commit();
                        log.info("Persist ok");
                        com = true;

                    } catch (Exception ex) {
                        if (tx != null && tx.isActive()) tx.rollback();
                        log.info("Persist echec");
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "errorOccured"), null);
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    } finally {
                        em.clear();
                        em.clear();
                    }
                }

                log.info(String.valueOf(rolesPermissionsEntity));
            }
            if (com) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "permission.update"), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            log.info("Sorry, you aren't permissions.");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "accessDenied.label"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }


    private void deleteRolesPermissions(int idRole) {
        findAllPermissionsIWithdRole(idRole);

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            List<RolesPermissionsEntity> r = dao.findPermissionWithRole(em, idRole);
            log.info(String.valueOf(r));
            dao.deleteRolesPermissions(em, idRole);
            //   em.remove(r);

            tx.commit();
            log.info("Persist ok");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Persist echec");
        } finally {
            em.clear();
            em.clear();
        }
    }


    private void validateRolesPermissions(RolesPermissionsEntity entity) {
        List<String> errors = RolesPermissionsValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("Register role is not valide {}", entity);
            throw new InvalidEntityException("L'ajou de role n est pas valide", ErrorCodes.USER_NOT_VALID, errors);
        }
    }

    private void validateRoles(RolesEntity entity) {
        List<String> errors = RolesValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("Register role is not valide {}", entity);
            throw new InvalidEntityException("L'ajou de role n est pas valide", ErrorCodes.USER_NOT_VALID, errors);
        }
    }

    private void validatePermissions(List<PermissionsEntity> entities) {
        List<String> errors = PermissionsValidator.validate(entities);
        if (!errors.isEmpty()) {
            log.error("Register role is not valide {}", entities);
            throw new InvalidEntityException("L'ajou de role n est pas valide", ErrorCodes.USER_NOT_VALID, errors);
        }
    }

    public void initialiseDialogRolePermissionsId(Integer idRolePermission) {
        if (idRolePermission == null || idRolePermission < 1) {
            return;
        }
        rolePermissionsForDialog = getDao().findById(EMF.getEM(), idRolePermission);
        rolesPermissionsEntity = dao.findById(EMF.getEM(), idRolePermission);
    }

    public List<RolesPermissionsEntity> fndRolePermByIdRole(EntityManager em, int id) {
        return dao.findPermissionWithRole(em, id);
    }


    public void findAllRolesPermissions() {
        log.info("bgin findallrole");
        rolesPermissionsEntityList = findAll();
        log.info(String.valueOf(rolesPermissionsEntityList));
    }

}
