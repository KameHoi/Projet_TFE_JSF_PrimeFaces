package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.NotesEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public interface NotesDao {

    /**
     * Save NotesEntity
     *
     * @param em     EntityManager
     * @param entity NotesEntity
     */
    void save(EntityManager em, NotesEntity entity);

    /**
     * Find NotesEntity By ID and By ID User
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return Optional<NotesEntity>
     */
    Optional<NotesEntity> findById(EntityManager em, int id, int idUser);

    /**
     * Find All notesEntities by ID User and By ID contact
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return List<NotesEntity>
     */
    List<NotesEntity> findNotesEntityByContactsByIdContacts(EntityManager em, int id, int idUser);

    /**
     * Find All notesEntities by ID User and By ID Company
     *
     * @param em     EntityManager
     * @param id     int
     * @param idUser int
     * @return List<NotesEntity>
     */
    List<NotesEntity> findNotesEntityByCompaniesByIdCompanies(EntityManager em, int id, int idUser);

    /**
     * Find All NotesEntity
     *
     * @param em     EntityManager
     * @param idUser int
     * @return List<NotesEntity>
     */
    List<NotesEntity> findAll(EntityManager em, int idUser);

    /**
     * Delete NotesEntity
     *
     * @param em     EntityManager
     * @param entity NotesEntity
     */
    void delete(EntityManager em, NotesEntity entity);

    /**
     * update NotesEntity
     *
     * @param em     EntityManager
     * @param entity NotesEntity
     */
    void update(EntityManager em, NotesEntity entity);

}
