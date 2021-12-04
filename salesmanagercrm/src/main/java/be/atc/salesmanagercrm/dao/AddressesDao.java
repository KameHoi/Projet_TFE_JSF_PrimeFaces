package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.AddressesEntity;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
public interface AddressesDao {

    /**
     * Find AddressEntity by id Company
     *
     * @param em EntityManager
     * @param id int
     * @return Optional<AddressesEntity>
     */
    Optional<AddressesEntity> findByIdCompanies(EntityManager em, int id);

    /**
     * Find AddressEntity by id Contacts
     *
     * @param em EntityManager
     * @param id int
     * @return Optional<AddressesEntity>
     */
    Optional<AddressesEntity> findByIdContacts(EntityManager em, int id);

    /**
     * Update AddressEntity
     *
     * @param em              EntityManager
     * @param addressesEntity AddressesEntity
     */
    void update(EntityManager em, AddressesEntity addressesEntity);
}
