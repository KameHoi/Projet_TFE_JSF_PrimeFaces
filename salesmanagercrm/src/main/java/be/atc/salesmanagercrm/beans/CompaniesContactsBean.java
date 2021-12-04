package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.CompaniesContactsDao;
import be.atc.salesmanagercrm.dao.impl.CompaniesContactsDaoImpl;
import be.atc.salesmanagercrm.entities.CompaniesContactsEntity;
import be.atc.salesmanagercrm.entities.CompaniesEntity;
import be.atc.salesmanagercrm.exceptions.EntityNotFoundException;
import be.atc.salesmanagercrm.exceptions.ErrorCodes;
import be.atc.salesmanagercrm.utils.EMF;
import be.atc.salesmanagercrm.utils.JsfUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
@Slf4j
@Named(value = "companiesContactsBean")
@ViewScoped
public class CompaniesContactsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -1631508572484407774L;

    @Getter
    @Setter
    private CompaniesContactsEntity companiesContactsEntity = new CompaniesContactsEntity();

    @Getter
    @Setter
    private List<CompaniesContactsEntity> companiesContactsEntityList;

    @Getter
    @Setter
    private CompaniesContactsDao companiesContactsDao = new CompaniesContactsDaoImpl();

    @Getter
    @Setter
    private List<CompaniesEntity> selectedCompaniesContacts = new ArrayList<>();

    @Getter
    @Setter
    private List<CompaniesEntity> selectedCompaniesContactsTemp = new ArrayList<>();

    @Getter
    @Setter
    private CompaniesEntity companiesEntity = new CompaniesEntity();

    @Inject
    private ContactsBean contactsBean;

    @Inject
    private CompaniesBean companiesBean;


    /**
     * Save companies and contacts in CompaniesContacts
     */
    protected void createCompaniesContacts() {

        log.info("CompaniesContactsBean => method : createCompaniesContacts()");

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            for (CompaniesEntity c : selectedCompaniesContacts) {
                CompaniesContactsEntity companiesContactsEntity1 = new CompaniesContactsEntity();
                companiesContactsEntity1.setContactsByIdContacts(contactsBean.getContactsEntity());
                companiesContactsEntity1.setCompaniesByIdCompanies(c);
                companiesContactsDao.update(em, companiesContactsEntity1);
            }
            tx.commit();
            log.info("Persist ok");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Persist failed");
        } finally {
            em.clear();
            em.clear();
        }

    }

    /**
     * Fill companies
     */
    public void fillSelectedCompaniesContacts() {
        log.info("CompaniesContactsBean => method : fillSelectedCompaniesContacts()");

        if (contactsBean.getContactsEntity() == null) {
            return;
        }
        List<CompaniesContactsEntity> companiesContactsEntityList = findByIdContacts(contactsBean.getContactsEntity().getId());

        for (CompaniesContactsEntity c : companiesContactsEntityList
        ) {
            this.selectedCompaniesContacts.add(c.getCompaniesByIdCompanies());
            this.selectedCompaniesContactsTemp.add(c.getCompaniesByIdCompanies());
        }
    }

    /**
     * Find companiesContacts by id contact
     *
     * @param id id
     * @return CompaniesContactsEntity
     */
    protected List<CompaniesContactsEntity> findByIdContacts(int id) {
        log.info("CompaniesContactsBean => method : findByIdContacts()");
        FacesMessage facesMessage;

        if (id == 0) {
            log.error("Contact ID is null");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "contact.error"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return null;
        }

        EntityManager em = EMF.getEM();
        try {
            return companiesContactsDao.findByIdContacts(em, id);
        } catch (Exception ex) {
            log.info("Nothing");
            throw new EntityNotFoundException(
                    "Aucun contact avec l ID " + id + " n'a été trouvé dans la DB",
                    ErrorCodes.CONTACT_NOT_FOUND
            );
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * delete CompaniesContact by id
     *
     * @param id CompaniesContact
     */
    protected void delete(int id) {

        log.info("CompaniesContactsBean => method : delete()");

        FacesMessage msg;

        if (id == 0) {
            log.error("CompaniesContacts ID is null");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "contact.error"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        CompaniesContactsEntity companiesContactsEntity;

        try {
            companiesContactsEntity = findById(id);
        } catch (EntityNotFoundException exception) {
            log.warn("Code ERREUR " + exception.getErrorCodes().getCode() + " - " + exception.getMessage());
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "note.notExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

        EntityManager em = EMF.getEM();
        em.getTransaction();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            companiesContactsDao.delete(em, companiesContactsEntity);
            tx.commit();
            log.info("Delete ok");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.error("Delete Error");
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * Find CompaniesContacts by ID
     *
     * @param id CompaniesContacts
     * @return CompaniesContact Entity
     */
    protected CompaniesContactsEntity findById(int id) {
        log.info("CompaniesContact => method : findById()");

        FacesMessage msg;

        if (id == 0) {
            log.error("CompaniesContact ID is null");
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "note.notExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            throw new EntityNotFoundException(
                    "L ID de la note est incorrect",
                    ErrorCodes.COMPANY_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Optional<CompaniesContactsEntity> optionalCompaniesContactsEntity;
        try {
            optionalCompaniesContactsEntity = companiesContactsDao.findById(em, id);
        } finally {
            em.clear();
            em.close();
        }
        return optionalCompaniesContactsEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune Note avec l ID " + id + " n a ete trouvee dans la BDD",
                        ErrorCodes.CONTACT_NOT_FOUND
                ));
    }

    /**
     * public method for findByIdCompany
     */
    public void getFindByIdCompany() {
        log.info("CompaniesContactsBean => method : getFindByIdCompany()");

        log.info("ID company" + companiesBean.getCompaniesEntity().getId());
        this.companiesContactsEntityList = findByIdCompany(companiesBean.getCompaniesEntity());
    }

    /**
     * Return list of CompaniesContacts by company id
     *
     * @param companiesEntity CompaniesEntity
     * @return companiesContactsEntities
     */
    protected List<CompaniesContactsEntity> findByIdCompany(CompaniesEntity companiesEntity) {

        log.info("CompaniesContactsBean => method : findByIdCompany()");

        FacesMessage facesMessage;

        if (companiesContactsEntity == null) {
            log.error("Companies entity is null");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "company.error"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return null;
        }

        EntityManager em = EMF.getEM();

        List<CompaniesContactsEntity> companiesContactsEntities = companiesContactsDao.findByIdCompany(em, companiesEntity.getId());

        em.clear();
        em.close();

        return companiesContactsEntities;
    }
}
