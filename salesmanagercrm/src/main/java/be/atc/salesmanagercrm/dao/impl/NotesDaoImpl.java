package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.NotesDao;
import be.atc.salesmanagercrm.entities.NotesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public class NotesDaoImpl extends EntityFinderImpl<NotesEntity> implements NotesDao {
    @Override
    public void save(EntityManager em, NotesEntity entity) {
        em.persist(entity);
    }

    @Override
    public Optional<NotesEntity> findById(EntityManager em, int id, int idUser) {
        return em.createNamedQuery("Notes.findById",
                        NotesEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<NotesEntity> findNotesEntityByContactsByIdContacts(EntityManager em, int id, int idUser) {

        return em.createNamedQuery("Notes.findNotesEntityByContactsByIdContacts",
                        NotesEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public List<NotesEntity> findNotesEntityByCompaniesByIdCompanies(EntityManager em, int id, int idUser) {

        return em.createNamedQuery("Notes.findNotesEntityByCompaniesByIdCompanies",
                        NotesEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public List<NotesEntity> findAll(EntityManager em, int idUser) {
        return em.createNamedQuery("Notes.findAll",
                        NotesEntity.class)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public void delete(EntityManager em, NotesEntity entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public void update(EntityManager em, NotesEntity entity) {
        em.merge(entity);
    }
}
