package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.CitiesEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface CitiesDao {

    /**
     * Find all CitiesEntity
     *
     * @return List<CitiesEntity>
     */
    List<CitiesEntity> findAll();

    /**
     * Find CitiesEntity by its id
     *
     * @param em EntityManager
     * @param id int
     * @return CitiesEntity
     */
    CitiesEntity findById(EntityManager em, int id);
}
