package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.VoucherHistoriesDao;
import be.atc.salesmanagercrm.entities.VoucherHistoriesEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Younes Arifi
 */
public class VoucherHistoriesDaoImpl implements VoucherHistoriesDao {
    @Override
    public void save(EntityManager em, VoucherHistoriesEntity entity) {
        em.persist(entity);
    }

    @Override
    public List<VoucherHistoriesEntity> findAllByIdUserAndByIdVoucher(EntityManager em, int idVoucher, int idUser) {
        return em.createNamedQuery("VoucherHistories.findAllByIdUserAndByIdVoucher",
                        VoucherHistoriesEntity.class)
                .setParameter("idUser", idUser)
                .setParameter("idVoucher", idVoucher)
                .getResultList();
    }
}
