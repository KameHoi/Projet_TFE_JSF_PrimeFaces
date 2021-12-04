package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.ContactTypesDao;
import be.atc.salesmanagercrm.dao.impl.ContactTypesDaoImpl;
import be.atc.salesmanagercrm.entities.ContactTypesEntity;
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
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
@Slf4j
@Named(value = "contactTypesBean")
@ViewScoped
public class ContactTypesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 6138026438225503423L;
    @Getter
    @Setter
    private ContactTypesDao contactTypesDao = new ContactTypesDaoImpl();

    @Getter
    @Setter
    private List<ContactTypesEntity> contactTypesEntities;

    /**
     * Find ContactType by ID
     *
     * @param id ContactTypes
     * @return ContactTypesEntity
     */
    public ContactTypesEntity findById(int id) {
        log.info("ContactTypesBean => method : findById()");

        FacesMessage facesMessage;

        if (id == 0) {
            log.error("CompanyTypes ID is null");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "companyTypes.NotExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return null;
        }

        EntityManager em = EMF.getEM();
        Optional<ContactTypesEntity> optionalContactTypesEntity;
        try {
            optionalContactTypesEntity = Optional.ofNullable(contactTypesDao.findById(em, id));
        } finally {
            em.clear();
            em.close();
        }

        return optionalContactTypesEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun type de contact avec l ID " + id + " n'a été trouve dans la DB",
                        ErrorCodes.CONTACTTYPE_NOT_FOUND
                ));
    }

    /**
     * Public method that call findAll
     */
    public void findAllContactTypes() {
        log.info("ContactTypesBean => method : findAllContactTypes()");

        contactTypesEntities = findAll();
    }

    /**
     * Find all ContactTypes entities
     *
     * @return List of Contact Types
     */
    protected List<ContactTypesEntity> findAll() {
        log.info("ContactTypesBean => method : findAll()");

        EntityManager em = EMF.getEM();

        List<ContactTypesEntity> contactTypesEntities = contactTypesDao.findAll();

        em.clear();
        em.close();

        return contactTypesEntities;
    }


}
