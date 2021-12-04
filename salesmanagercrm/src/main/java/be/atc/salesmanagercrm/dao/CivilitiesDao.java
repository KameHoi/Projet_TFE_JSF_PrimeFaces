package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.CivilitiesEntity;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * @author Maximilien Zabbara
 */
public interface CivilitiesDao {

    /**
     * Find all CivilitiesEntity
     *
     * @return List<CivilitiesEntity>
     */
    List<CivilitiesEntity> findAll();

    /**
     * Find CivilitiesEntity by its id
     *
     * @param em EntityManager
     * @param id int
     * @return CivilitiesEntity
     */
    CivilitiesEntity findById(EntityManager em, int id);
}
