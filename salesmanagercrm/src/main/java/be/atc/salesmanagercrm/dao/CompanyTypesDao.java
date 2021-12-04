package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.CompanyTypesEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
public interface CompanyTypesDao {

    /**
     * Find all CompanyTypesEntity
     *
     * @return List<CompanyTypesEntity>
     */
    List<CompanyTypesEntity> findAll();

    /**
     * Find CompanyTypesEntity by its id
     *
     * @param em EntityManager
     * @param id int
     * @return CompanyTypesEntity
     */
    CompanyTypesEntity findById(EntityManager em, int id);

    /**
     * Find EntityManager by label
     *
     * @param em    EntityManager
     * @param label String
     * @return Optional<CompanyTypesEntity>
     */
    Optional<CompanyTypesEntity> findByLabel(EntityManager em, String label);
}
