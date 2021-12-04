package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.AddressesDao;
import be.atc.salesmanagercrm.entities.AddressesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
@Slf4j
public class AddressesDaoImpl extends EntityFinderImpl<AddressesEntity> implements AddressesDao {

    @Override
    public Optional<AddressesEntity> findByIdCompanies(EntityManager em, int id) {
        return em.createNamedQuery("Addresses.findByIdCompanies",
                        AddressesEntity.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<AddressesEntity> findByIdContacts(EntityManager em, int id) {
        return em.createNamedQuery("Addresses.findByIdContacts",
                        AddressesEntity.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public void update(EntityManager em, AddressesEntity addressesEntity) {
        em.merge(addressesEntity);
    }
}
