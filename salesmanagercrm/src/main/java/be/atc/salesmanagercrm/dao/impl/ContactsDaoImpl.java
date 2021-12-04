package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.ContactsDao;
import be.atc.salesmanagercrm.entities.ContactsEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
public class ContactsDaoImpl extends EntityFinderImpl<ContactsEntity> implements ContactsDao {

    @Override
    public void add(EntityManager em, ContactsEntity contactsEntity) {
        em.persist(contactsEntity);
    }

    @Override
    public Optional<ContactsEntity> findById(EntityManager em, int id) {
        return em.createNamedQuery("Contacts.findById",
                        ContactsEntity.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<ContactsEntity> findByIdContactAndByIdUser(EntityManager em, int id, int idUser) {
        return em.createNamedQuery("Contacts.findByIdContactAndByIdUser",
                        ContactsEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<ContactsEntity> findContactsEntityByIdUser(EntityManager em, int idUser) {
        return em.createNamedQuery("Contacts.findContactsEntityByIdUser",
                        ContactsEntity.class)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public List<ContactsEntity> findAllContactsEntityByIdUser(EntityManager em, int idUser) {
        return em.createNamedQuery("Contacts.findAllContactsEntityByIdUser",
                        ContactsEntity.class)
                .setParameter("idUser", idUser)
                .getResultList();
    }


    @Override
    public List<ContactsEntity> findAll() {
        return this.findByNamedQuery("Contacts.findAll", new ContactsEntity());
    }

    @Override
    public void update(EntityManager em, ContactsEntity contactsEntity) {
        em.merge(contactsEntity);
    }

    @Override
    public Long countActiveContacts(EntityManager em, int idUser) {

        Object activeContacts = em.createNamedQuery("Contacts.countActiveContacts",
                        ContactsEntity.class)
                .setParameter("idUser", idUser)
                .getSingleResult();

        return (Long) activeContacts;
    }

    @Override
    public Long countAllContacts(EntityManager em, int idUser) {

        Object activeContacts = em.createNamedQuery("Contacts.countAllContacts",
                        ContactsEntity.class)
                .setParameter("idUser", idUser)
                .getSingleResult();

        return (Long) activeContacts;
    }
}
