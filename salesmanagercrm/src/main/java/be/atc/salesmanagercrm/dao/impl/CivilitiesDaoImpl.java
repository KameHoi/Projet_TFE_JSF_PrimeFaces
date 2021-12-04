package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.CivilitiesDao;
import be.atc.salesmanagercrm.entities.CivilitiesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * @author Maximilien Zabbara
 */
public class CivilitiesDaoImpl extends EntityFinderImpl<CivilitiesEntity> implements CivilitiesDao {

    @Override
    public List<CivilitiesEntity> findAll() {
        return this.findByNamedQuery("Civilities.findAll", new CivilitiesEntity());
    }

    @Override
    public CivilitiesEntity findById(EntityManager em, int id) {
        return em.find(CivilitiesEntity.class, id);
    }
}
