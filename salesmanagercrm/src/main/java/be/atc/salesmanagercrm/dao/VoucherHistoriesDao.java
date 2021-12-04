package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.VoucherHistoriesEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Younes Arifi
 */
public interface VoucherHistoriesDao {

    /**
     * Save VouchersHistoriesEntity
     *
     * @param em     EntityManager
     * @param entity VoucherHistoriesEntity
     */
    void save(EntityManager em, VoucherHistoriesEntity entity);

    /**
     * Find All VouchersHistories by Id Voucher
     *
     * @param em        EntityManager
     * @param idVoucher int
     * @param idUser    int
     * @return List<VoucherHistoriesEntity>
     */
    List<VoucherHistoriesEntity> findAllByIdUserAndByIdVoucher(EntityManager em, int idVoucher, int idUser);
}
