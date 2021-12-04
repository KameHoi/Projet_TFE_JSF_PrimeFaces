package be.atc.salesmanagercrm.beans;


import be.atc.salesmanagercrm.dao.ConversationsDao;
import be.atc.salesmanagercrm.dao.UsersDao;
import be.atc.salesmanagercrm.dao.impl.ConversationsDaoImpl;
import be.atc.salesmanagercrm.dao.impl.UsersDaoImpl;
import be.atc.salesmanagercrm.entities.ConversationsEntity;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.exceptions.InvalidEntityException;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.utils.JsfUtils;
import be.atc.salesmanagercrm.validators.ConversationsValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author Larché Marie-Élise
 */
@Slf4j
@Named(value = "conversationsBean")
@ViewScoped
public class ConversationsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 829261844584069844L;
    @Getter
    @Setter
    private ConversationsEntity conversationsEntity = new ConversationsEntity();

    @Getter
    @Setter
    private ConversationsDao dao = new ConversationsDaoImpl();
    @Getter
    @Setter
    private UsersDao userDao = new UsersDaoImpl();
    @Getter
    @Setter
    private List<ConversationsEntity> conversationsEntityList;
    @Getter
    @Setter
    private ConversationsEntity conversationsForDialog;


    @Getter
    @Setter
    private List<ConversationsEntity> conversationsEntities;
    @Getter
    @Setter
    private ConversationsEntity selectedConversationsEntity;
    @Getter
    @Setter
    private List<ConversationsEntity> conversationsEntitiesFiltered;
    @Getter
    @Setter
    private boolean paramType = true;

    @Inject
    private UsersBean usersBean;

    @Getter
    @Setter
    private Subject currentUser;
    @Getter
    @Setter
    private Session session;


    /**
     * call add();
     */
    public void addEntity() {
        add(this.conversationsEntity);
        get10convers();
        this.conversationsEntity = new ConversationsEntity();

    }

    /**
     * create a message
     *
     * @param conversationsEntity ConversationsEntity
     */
    public void add(ConversationsEntity conversationsEntity) {
        FacesMessage msg;
        log.info(String.valueOf(usersBean.getUsersEntity().getId()));
        log.info("user entity: " + usersBean.getUsersEntity().getUsername());
        try {
            validateConversations(conversationsEntity);
        } catch (InvalidEntityException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
            return;
        }

        log.info(conversationsEntity.getMessage());
        String message = conversationsEntity.getMessage();
        message = message.replaceAll("\\<[^>]*>", "");
        log.info(message);

        conversationsEntity.setMessage(message);
        conversationsEntity.setCreationDate(LocalDateTime.now());
        conversationsEntity.setActive(true);
        log.info(String.valueOf(usersBean.getUsersEntity()));
        log.info(usersBean.getUsersEntity().getUsername());
        conversationsEntity.setUsersByIdUsers(usersBean.getUsersEntity());

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            dao.add(em, conversationsEntity);
            tx.commit();
            log.info("Persist ok");
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "addMessageOk"), null);
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
    }


    /**
     * delete a role
     *
     * @param entity ConversationsEntity
     */
    public void delete(ConversationsEntity entity) {
        FacesMessage msg;


        try {
                validateConvers(conversationsEntity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
                return;
            }

            entity.setActive(false);

            EntityManager em = EMF.getEM();
            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();
                dao.update(em, entity);
                tx.commit();
                log.info("Persist ok");
                conversationsEntityList = findAllConv();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "deleteMessageOk"), null);
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


    }

    /**
     * active a role
     *
     * @param entity ConversationsEntity
     */
    public void activate(ConversationsEntity entity) {
        FacesMessage msg;

            log.info("conversationListActive");
            try {
                validateConvers(conversationsEntity);
            } catch (InvalidEntityException exception) {
                log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage() + " : " + exception.getErrors().toString());
                return;
            }

            entity.setActive(true);

            EntityManager em = EMF.getEM();
            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();
                dao.update(em, entity);
                tx.commit();
                log.info("Persist ok");
                conversationsEntityList = findAllConv();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "activateMessage"), null);
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

    }


    /**
     * call find10convers
     */
    public void get10convers() {
        conversationsEntityList = find10convers();

//        Collections.reverse(conversationsEntityList);

    }

    /**
     * find 50 last conversations
     *
     * @return List<ConversationsEntity>
     */
    private List<ConversationsEntity> find10convers() {
        log.info("begin findall");
        EntityManager em = EMF.getEM();
        List<ConversationsEntity> conversationsEntities = dao.find10Last(em);

        em.clear();
        em.close();

        return conversationsEntities;
    }

    /**
     * find all conversations
     */
    public void findAllConversations() {
        log.info("bgin findallrole");
        conversationsEntityList = findAllConv();

    }

    /**
     * @return List<ConversationsEntity>
     */
    protected List<ConversationsEntity> findAll() {
        log.info("begin findall");
        EntityManager em = EMF.getEM();
        List<ConversationsEntity> conversationsEntities = dao.findAll(em);

        em.clear();
        em.close();

        return conversationsEntities;
    }

    /**
     * @return List<ConversationsEntity>
     */
    protected List<ConversationsEntity> findAllConv() {
        log.info("begin findall");
        EntityManager em = EMF.getEM();
        List<ConversationsEntity> conversationsEntities = dao.findAllConv(em);

        em.clear();
        em.close();

        return conversationsEntities;
    }

    /**
     * @param entity ConversationsEntity
     */
    private void validateConversations(ConversationsEntity entity) {
        List<String> errors = ConversationsValidator.validate(entity);
        if (!errors.isEmpty()) {
            log.error("Conversation is not valide {}", entity);
            throw new InvalidEntityException("L'ajout du message n'est pas valide", ErrorCodes.CONVERSATION_NOT_VALID, errors);
        }
    }

    /**
     * @param entity ConversationsEntity
     */
    private void validateConvers(ConversationsEntity entity) {
        List<String> errors = ConversationsValidator.validateEntity(entity);
        if (!errors.isEmpty()) {
            log.error("Conversation is not valide {}", entity);
            throw new InvalidEntityException("L'ajout du message n'est pas valide", ErrorCodes.CONVERSATION_NOT_VALID, errors);
        }
    }


    public void initialiseDialogConversId(Integer idConvers) {
        if (idConvers == null || idConvers < 1) {
            return;
        }
        conversationsForDialog = getDao().findById(EMF.getEM(), idConvers);
        conversationsEntity = dao.findById(EMF.getEM(), idConvers);
    }


}
