package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.TasksDao;
import be.atc.salesmanagercrm.entities.TasksEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public class TasksDaoImpl extends EntityFinderImpl<TasksEntity> implements TasksDao {
    @Override
    public void save(EntityManager em, TasksEntity entity) {
        em.persist(entity);
    }

    @Override
    public Optional<TasksEntity> findById(EntityManager em, int id, int idUser) {
        return em.createNamedQuery("Tasks.findById",
                        TasksEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<TasksEntity> findTasksEntityByContactsByIdContacts(EntityManager em, int id, int idUser) {

        return em.createNamedQuery("Tasks.findTasksEntityByContactsByIdContacts",
                TasksEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public List<TasksEntity> findTasksEntityByCompaniesByIdCompanies(EntityManager em, int id, int idUser) {

        return em.createNamedQuery("Tasks.findTasksEntityByCompaniesByIdCompanies",
                TasksEntity.class)
                .setParameter("id", id)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public List<TasksEntity> findAll(EntityManager em, int idUser) {

        return em.createNamedQuery("Tasks.findAll",
                TasksEntity.class)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    @Override
    public void delete(EntityManager em, TasksEntity entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public void update(EntityManager em, TasksEntity entity) {
        em.merge(entity);
    }

    @Override
    public List<TasksEntity> findTasksFinished(EntityManager em, int idUser) {
        return em.createNamedQuery("Tasks.findTasksFinished",
                TasksEntity.class)
                .setParameter("idUser", idUser)
                .getResultList();
    }
}
