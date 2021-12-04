package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.CitiesDao;
import be.atc.salesmanagercrm.entities.CitiesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;

public class CitiesDaoImpl extends EntityFinderImpl<CitiesEntity> implements CitiesDao {

    @Override
    public List<CitiesEntity> findAll() {
        return this.findByNamedQuery("Cities.findAll", new CitiesEntity());
    }

    @Override
    public CitiesEntity findById(EntityManager em, int id) {
        return em.find(CitiesEntity.class, id);
    }
}
