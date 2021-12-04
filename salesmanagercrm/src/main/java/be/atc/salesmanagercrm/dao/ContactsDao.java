package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.ContactsEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Maximilien Zabbara
 */
public interface ContactsDao {

    /**
     * Add ContactsEntity
     *
     * @param em             EntityManager
     * @param contactsEntity ContactsEntity
     */
    void add(EntityManager em, ContactsEntity contactsEntity);

    /**
     * Find ContactsEntity by its id
     *
     * @param em EntityManager
     * @param id int
     * @return Optional<ContactsEntity>
     */
    Optional<ContactsEntity> findById(EntityManager em, int id);

    /**
     * Find ContactsEntity by its id and id User
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return Optional<ContactsEntity>
     */
    Optional<ContactsEntity> findByIdContactAndByIdUser(EntityManager em, int id, int idUser);

    /**
     * Find ContactsEntity by id UserEntity
     *
     * @param em     EntityManager
     * @param idUser int
     * @return List<ContactsEntity>
     */
    List<ContactsEntity> findContactsEntityByIdUser(EntityManager em, int idUser);

    /**
     * Find all ContactsEntity by id UserEntity
     *
     * @param em     EntityManager
     * @param idUser int
     * @return List<ContactsEntity>
     */
    List<ContactsEntity> findAllContactsEntityByIdUser(EntityManager em, int idUser);

    /**
     * Find all ContactsEntity
     *
     * @return List<ContactsEntity>
     */
    List<ContactsEntity> findAll();

    /**
     * Update ContactsEntity
     *
     * @param em             EntityManager
     * @param contactsEntity ContactsEntity
     */
    void update(EntityManager em, ContactsEntity contactsEntity);

    /**
     * Count active ContactsEntity
     *
     * @param em     EntityManager
     * @param idUser int
     * @return Long
     */
    Long countActiveContacts(EntityManager em, int idUser);

    /**
     * Count all ContactsEntity
     *
     * @param em     EntityManager
     * @param idUser int
     * @return Long
     */
    Long countAllContacts(EntityManager em, int idUser);
}
