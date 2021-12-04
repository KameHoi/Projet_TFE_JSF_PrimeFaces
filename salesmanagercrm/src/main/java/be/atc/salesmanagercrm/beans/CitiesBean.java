package be.atc.salesmanagercrm.beans;

import be.atc.salesmanagercrm.dao.CitiesDao;
import be.atc.salesmanagercrm.dao.impl.CitiesDaoImpl;
import be.atc.salesmanagercrm.entities.CitiesEntity;
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
import java.util.stream.Collectors;

/**
 * @author Maximilien Zabbara
 */
@Slf4j
@Named(value = "citiesBean")
@ViewScoped
public class CitiesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 2558862204507586311L;
    @Getter
    @Setter
    private CitiesDao citiesDao = new CitiesDaoImpl();

    @Getter
    @Setter
    private CitiesEntity citiesEntity = new CitiesEntity();

    /**
     * Find all Cities entities
     *
     * @return List BranchActivitiesEntity
     */
    public List<CitiesEntity> findCitiesEntityList() {
        log.info("CitiesBean => method : findCitiesEntityList()");

        EntityManager em = EMF.getEM();

        List<CitiesEntity> citiesEntities = citiesDao.findAll();

        em.clear();
        em.close();

        return citiesEntities;
    }

    /**
     * Find City by ID
     *
     * @param id CitiesEntity
     * @return CitiesEntity
     */
    public CitiesEntity findById(int id) {
        log.info("CitiesBean => method : findById()");

        FacesMessage facesMessage;

        if (id == 0) {
            log.error("Cities ID is null");
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "CitiesNotExist"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            throw new EntityNotFoundException(
                    "Aucune ville avec l'ID " + id + " n a été trouve dans la DB",
                    ErrorCodes.CITIES_NOT_FOUND
            );
        }

        EntityManager em = EMF.getEM();
        Optional<CitiesEntity> optionalCitiesEntity;
        try {
            optionalCitiesEntity = Optional.ofNullable(citiesDao.findById(em, id));
        } finally {
            em.clear();
            em.close();
        }
        return optionalCitiesEntity.orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune ville avec l'ID " + id + " n a été trouve dans la DB",
                        ErrorCodes.CITIES_NOT_FOUND
                ));
    }

    /**
     * Sort Cities by group in form
     *
     * @param entityGroup CitiesEntity
     * @return label of entitygroup
     */
    public char getCitiesEntityGroup(CitiesEntity entityGroup) {
        log.info("CitiesBean => method : getCitiesEntityGroup()");
        return entityGroup.getLabel().charAt(0);
    }


    /**
     * Auto Complete form cities entity
     *
     * @param query String
     * @return List Cities Entities
     */
    public List<CitiesEntity> completeCitiesEntityContains(String query) {
        log.info("CitiesBean => method : completeCitiesEntityContains()");
        String queryLowerCase = query.toLowerCase();

        List<CitiesEntity> citiesEntityListForm = findCitiesEntityList();
        String space = " ";
        return citiesEntityListForm.stream().filter(t -> (t.getLabel().concat(space.concat(t.getPostalCode())).toLowerCase().contains(queryLowerCase)) || (t.getPostalCode().concat(space.concat(t.getLabel())).toLowerCase().contains(queryLowerCase))).collect(Collectors.toList());
    }


}
