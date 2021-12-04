package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.AddressesDao;
import be.atc.salesmanagercrm.dao.impl.AddressesDaoImpl;
import be.atc.salesmanagercrm.entities.AddressesEntity;
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
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
@Slf4j
@Named(value = "addressesBean")
@ViewScoped
public class AddressesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 5671519499945929696L;
    @Getter
    @Setter
    private AddressesDao addressesDao = new AddressesDaoImpl();

    @Getter
    @Setter
    private AddressesEntity addressesEntity = new AddressesEntity();

    @Inject
    private CompaniesBean companiesBean;
    @Inject
    private ContactsBean contactsBean;


    /**
     * Return addressEntity by id companies if exist else return new AddressesEntity
     */
    public void getAddressByIdCompany() {
        log.info("AddressesBean => method : getAddressByIdCompany()");

        try {
            addressesEntity = findByIdCompanies(companiesBean.getCompaniesEntity().getId());
        } catch (EntityNotFoundException exception) {
            addressesEntity = new AddressesEntity();
        }

    }


    /**
     * Return addressEntity by id contacts if exist else return new AddressesEntity
     */
    public void getAddressByIdContacts() {
        log.info("AddressesBean => method : getAddressByIdContacts()");

        try {
            addressesEntity = findByIdContacts(contactsBean.getContactsEntity().getId());
        } catch (EntityNotFoundException exception) {
            addressesEntity = new AddressesEntity();
        }
    }


    /**
     * Find address by ID Company
     *
     * @param id id
     * @return AddressesEntity
     */
    protected AddressesEntity findByIdCompanies(int id) {

        log.info("AddressesBean => method : findByIdCompanies()");
        FacesMessage facesMessage;

        if (id == 0) {
            log.error("ID Companies is null");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "addressesNotFound"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return null;
        }

        EntityManager em = EMF.getEM();

        Optional<AddressesEntity> optionalAddressesEntity;
        try {
            optionalAddressesEntity = addressesDao.findByIdCompanies(em, id);
        } finally {
            em.clear();
            em.close();
        }

        return optionalAddressesEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune adresse avec l'ID " + id + " n'a été trouve dans la DB",
                        ErrorCodes.ADDRESSES_NOT_FOUND
                ));

    }


    /**
     * Find address by ID Contacts
     *
     * @param id id
     * @return AddressesEntity
     */
    protected AddressesEntity findByIdContacts(int id) {

        log.info("AddressesBean => method : findByIdContacts()");
        FacesMessage facesMessage;

        if (id == 0) {
            log.error("ID Contacts is null");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "addressesNotFound"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return null;
        }

        EntityManager em = EMF.getEM();
        Optional<AddressesEntity> optionalAddressesEntity;

        try {
            optionalAddressesEntity = addressesDao.findByIdContacts(em, id);
        } finally {
            em.clear();
            em.close();
        }

        return optionalAddressesEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune adresse avec l'ID du contact " + id + " n'a été trouve dans la DB",
                        ErrorCodes.ADDRESSES_NOT_FOUND
                ));

    }


    /**
     * Method that call updateAddress
     */
    public void updateEntity() {
        log.info("AddressesBean => method : updateEntity()");

        if (addressesEntity.getCitiesByIdCities() != null) {
            updateAddress(this.addressesEntity);
        } else {
            addressesEntity = new AddressesEntity();
        }

    }

    /**
     * Update Address entity
     *
     * @param addressesEntity AddressesEntity
     */
    protected void updateAddress(AddressesEntity addressesEntity) {
        log.info("AddressesBean => method : updateAddress()");

        FacesMessage facesMessage;

        EntityManager em = EMF.getEM();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            addressesDao.update(em, addressesEntity);
            tx.commit();
            log.info("Update ok");
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

}
