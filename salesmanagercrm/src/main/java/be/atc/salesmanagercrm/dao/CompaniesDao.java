package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.CompaniesEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
public interface CompaniesDao {

    /**
     * Add CompaniesEntity
     *
     * @param em              EntityManager
     * @param companiesEntity CompaniesEntity
     */
    void add(EntityManager em, CompaniesEntity companiesEntity);

    /**
     * Find CompaniesEntity by its id
     *
     * @param em EntityManager
     * @param id int
     * @return Optional<CompaniesEntity>
     */
    Optional<CompaniesEntity> findById(EntityManager em, int id);

    /**
     * Update CompaniesEntity
     *
     * @param em              EntityManager
     * @param companiesEntity CompaniesEntity
     */
    void update(EntityManager em, CompaniesEntity companiesEntity);

    /**
     * Find CompaniesEntity by its id and idUser
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return Optional<CompaniesEntity>
     */
    Optional<CompaniesEntity> findByIdCompanyAndByIdUser(EntityManager em, int id, int idUser);

    /**
     * Find CompaniesEntity by idUser
     *
     * @param em     EntityManager
     * @param idUser int
     * @return List<CompaniesEntity>
     */
    List<CompaniesEntity> findCompaniesEntityByIdUser(EntityManager em, int idUser);

    /**
     * Count active CompaniesEntity
     *
     * @param em     EntityManager
     * @param idUser int
     * @return Long
     */
    Long countActiveCompanies(EntityManager em, int idUser);

    /**
     * Count all CompaniesEntity
     *
     * @param em     EntityManager
     * @param idUser int
     * @return Long
     */
    Long countAllCompanies(EntityManager em, int idUser);
}
