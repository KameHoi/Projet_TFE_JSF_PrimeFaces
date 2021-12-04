package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.ContactTypesDao;
import be.atc.salesmanagercrm.entities.ContactTypesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Maximilien Zabbara
 */
public class ContactTypesDaoImpl extends EntityFinderImpl<ContactTypesEntity> implements ContactTypesDao {

    @Override
    public ContactTypesEntity findById(EntityManager em, int id) {
        return em.find(ContactTypesEntity.class, id);
    }

    @Override
    public List<ContactTypesEntity> findAll() {
        return this.findByNamedQuery("ContactTypes.findAll", new ContactTypesEntity());
    }
}
