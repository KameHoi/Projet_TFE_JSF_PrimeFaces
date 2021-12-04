package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.CompanyTypesDao;
import be.atc.salesmanagercrm.entities.CompanyTypesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
public class CompanyTypesDaoImpl extends EntityFinderImpl<CompanyTypesEntity> implements CompanyTypesDao {

    @Override
    public List<CompanyTypesEntity> findAll() {
        return this.findByNamedQuery("CompanyTypes.findAll", new CompanyTypesEntity());
    }

    @Override
    public CompanyTypesEntity findById(EntityManager em, int id) {
        return em.find(CompanyTypesEntity.class, id);
    }

    @Override
    public Optional<CompanyTypesEntity> findByLabel(EntityManager em, String label) {
        return em.createNamedQuery("CompanyTypes.findByLabel",
                        CompanyTypesEntity.class)
                .setParameter("label", label)
                .getResultList()
                .stream()
                .findFirst();
    }
}
