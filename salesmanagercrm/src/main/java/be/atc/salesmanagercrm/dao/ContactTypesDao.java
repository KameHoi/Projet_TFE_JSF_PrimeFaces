package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.ContactTypesEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Maximilien Zabbara
 */
public interface ContactTypesDao {

    /**
     * Find ContactTypesEntity by its id
     *
     * @param em EntityManager
     * @param id int
     * @return ContactTypesEntity
     */
    ContactTypesEntity findById(EntityManager em, int id);

    /**
     * Find all ContactTypesEntity
     *
     * @return List<ContactTypesEntity>
     */
    List<ContactTypesEntity> findAll();
}
