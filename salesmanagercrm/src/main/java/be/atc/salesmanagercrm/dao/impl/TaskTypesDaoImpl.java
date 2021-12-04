package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.TaskTypesDao;
import be.atc.salesmanagercrm.entities.TaskTypesEntity;
import be.atc.salesmanagercrm.utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Younes Arifi
 */
public class TaskTypesDaoImpl extends EntityFinderImpl<TaskTypesEntity> implements TaskTypesDao {
    @Override
    public void save(EntityManager em, TaskTypesEntity entity) {
        em.persist(entity);
    }

    @Override
    public TaskTypesEntity findById(EntityManager em, int id) {
        return em.find(TaskTypesEntity.class, id);
    }

    @Override
    public List<TaskTypesEntity> findAll() {
        return this.findByNamedQuery("TaskTypes.findAll", new TaskTypesEntity());
    }

    @Override
    public void update(EntityManager em, TaskTypesEntity entity) {
        em.merge(entity);
    }

    @Override
    public Optional<TaskTypesEntity> findByLabel(EntityManager em, String label) {
        return em.createNamedQuery("TaskTypes.findTaskTypesEntityByLabel",
                        TaskTypesEntity.class)
                .setParameter("label", label)
                .getResultList()
                .stream()
                .findFirst();
    }
}
