package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.VouchersEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public interface VouchersDao {

    /**
     * Save VouchersEntity
     *
     * @param em     EntityManager
     * @param entity VouchersEntity
     */
    void save(EntityManager em, VouchersEntity entity);

    /**
     * Find VouchersEntity by ID and By ID User
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return Optional<VouchersEntity>
     */
    Optional<VouchersEntity> findById(EntityManager em, int id, int idUser);

    /**
     * Find VouchersEntity By ID Vouchers and By Contact ID
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return List<VouchersEntity>
     */
    List<VouchersEntity> findVouchersEntityByContactsByIdContacts(EntityManager em, int id, int idUser);

    /**
     * Find VouchersEntity By ID Vouchers and By Company ID
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return List<VouchersEntity>
     */
    List<VouchersEntity> findVouchersEntityByCompaniesByIdCompanies(EntityManager em, int id, int idUser);

    /**
     * Find All VouchersEntities By Id User
     *
     * @param em     EntityManager
     * @param idUser int
     * @return List<VouchersEntity>
     */
    List<VouchersEntity> findAll(EntityManager em, int idUser);

    /**
     * Update VouchersEntity
     *
     * @param em     EntityManager
     * @param entity VouchersEntity
     */
    void update(EntityManager em, VouchersEntity entity);

    /**
     * Count Active Vouchers By Status
     *
     * @param em            EntityManager
     * @param idUser        int
     * @param voucherStatus String
     * @return Long
     */
    Long countVouchersActiveStatus(EntityManager em, int idUser, String voucherStatus);

    /**
     * Count All Active Vouchers
     *
     * @param em     EntityManager
     * @param idUser int
     * @return Long
     */
    Long countActiveVouchers(EntityManager em, int idUser);
}
