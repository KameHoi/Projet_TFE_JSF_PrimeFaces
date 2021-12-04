package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.VoucherStatusEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public interface VoucherStatusDao {

    /**
     * Find VoucherStatus By ID
     *
     * @param em EntityManager
     * @param id int
     * @return VoucherStatusEntity
     */
    VoucherStatusEntity findById(EntityManager em, int id);

    /**
     * Find All Voucher Status
     *
     * @return List<VoucherStatusEntity>
     */
    List<VoucherStatusEntity> findAll();

    /**
     * Find VoucherStatus By Label
     *
     * @param em    EntityManager
     * @param label String
     * @return Optional<VoucherStatusEntity>
     */
    Optional<VoucherStatusEntity> findByLabel(EntityManager em, String label);
}
