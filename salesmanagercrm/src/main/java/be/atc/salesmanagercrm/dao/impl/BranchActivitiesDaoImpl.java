package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.BranchActivitiesDao;
import be.atc.salesmanagercrm.entities.BranchActivitiesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * @author Maximilien Zabbara
 */
@Slf4j
public class BranchActivitiesDaoImpl extends EntityFinderImpl<BranchActivitiesEntity> implements BranchActivitiesDao {

    @Override
    public void add(EntityManager em, BranchActivitiesEntity branchActivitiesEntity) {
        em.persist(branchActivitiesEntity);
    }

    @Override
    public List<BranchActivitiesEntity> findAll() {
        return this.findByNamedQuery("BranchActivities.findAll", new BranchActivitiesEntity());
    }

    @Override
    public void update(EntityManager em, BranchActivitiesEntity branchActivitiesEntity) {
        em.merge(branchActivitiesEntity);
    }

    @Override
    public BranchActivitiesEntity findById(EntityManager em, int id) {
        return em.find(BranchActivitiesEntity.class, id);
    }

    @Override
    public List<BranchActivitiesEntity> findByLabel(EntityManager em, String label) {
        return em.createNamedQuery("BranchActivities.findByLabel",
                        BranchActivitiesEntity.class)
                .setParameter("label", label)
                .getResultList();
    }
}
