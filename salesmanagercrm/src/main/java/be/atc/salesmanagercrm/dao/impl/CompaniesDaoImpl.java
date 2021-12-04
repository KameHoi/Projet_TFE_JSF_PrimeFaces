package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.CompaniesDao;
import be.atc.salesmanagercrm.entities.CompaniesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
public class CompaniesDaoImpl extends EntityFinderImpl<CompaniesEntity> implements CompaniesDao {

    @Override
    public void add(EntityManager em, CompaniesEntity companiesEntity) {
        em.persist(companiesEntity);
    }

    @Override
    public Optional<CompaniesEntity> findById(EntityManager em, int id) {
        return em.createNamedQuery("Companies.findById",
                        CompaniesEntity.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public void update(EntityManager em, CompaniesEntity companiesEntity) {
        em.merge(companiesEntity);
    }


    @Override
    public Optional<CompaniesEntity> findByIdCompanyAndByIdUser(EntityManager em, int id, int idUser) {
        return em.createNamedQuery("Companies.findByIdCompanyAndByIdUser",
                        CompaniesEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<CompaniesEntity> findCompaniesEntityByIdUser(EntityManager em, int idUser) {

        return em.createNamedQuery("Companies.findCompaniesEntityByIdUser",
                        CompaniesEntity.class)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public Long countActiveCompanies(EntityManager em, int idUser) {

        Object activeCompanies = em.createNamedQuery("Companies.countActiveCompanies",
                        CompaniesEntity.class)
                .setParameter("idUser", idUser)
                .getSingleResult();

        return (Long) activeCompanies;

    }

    @Override
    public Long countAllCompanies(EntityManager em, int idUser) {

        Object activeCompanies = em.createNamedQuery("Companies.countAllCompanies",
                        CompaniesEntity.class)
                .setParameter("idUser", idUser)
                .getSingleResult();

        return (Long) activeCompanies;
    }
}
