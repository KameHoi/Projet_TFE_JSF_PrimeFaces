package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.CompaniesContactsEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
public interface CompaniesContactsDao {

    /**
     * Update CompaniesContactsEntity
     *
     * @param em                      EntityManager
     * @param companiesContactsEntity CompaniesContactsEntity
     */
    void update(EntityManager em, CompaniesContactsEntity companiesContactsEntity);

    /**
     * Find CompaniesContactsEntity by id Contacts
     *
     * @param em         EntityManager
     * @param idContacts int
     * @return List<CompaniesContactsEntity>
     */
    List<CompaniesContactsEntity> findByIdContacts(EntityManager em, int idContacts);

    /**
     * Find CompaniesContactsEntity by id Company
     *
     * @param em        EntityManager
     * @param idCompany int
     * @return List<CompaniesContactsEntity>
     */
    List<CompaniesContactsEntity> findByIdCompany(EntityManager em, int idCompany);

    /**
     * Find CompaniesContactsEntity by its id
     *
     * @param em EntityManager
     * @param id int
     * @return Optional<CompaniesContactsEntity>
     */
    Optional<CompaniesContactsEntity> findById(EntityManager em, int id);

    /**
     * Delete CompaniesContactsEntity
     *
     * @param em                      EntityManager
     * @param companiesContactsEntity CompaniesContactsEntity
     */
    void delete(EntityManager em, CompaniesContactsEntity companiesContactsEntity);

}
