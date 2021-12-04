package be.atc.salesmanagercrm.dao;

import be.atc.salesmanagercrm.entities.ConversationsEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface ConversationsDao {
    /**
     * @param em EntityManager
     * @param id it
     * @return ConversationsEntity
     */
    ConversationsEntity findById(EntityManager em, int id);

    /**
     * @param em                  EntityManager
     * @param conversationsEntity ConversationsEntity
     */
    void add(EntityManager em, ConversationsEntity conversationsEntity);

    /**
     * @param em EntityManager
     * @return List<ConversationsEntity>
     */
    List<ConversationsEntity> findAll(EntityManager em);

    /**
     * @param em EntityManager
     * @return List<ConversationsEntity>
     */
    List<ConversationsEntity> findAllConv(EntityManager em);

    /**
     * @param em EntityManager
     * @return List<ConversationsEntity>
     */
    List<ConversationsEntity> find10Last(EntityManager em);

    /**
     * @param em     EntityManager
     * @param entity ConversationsEntity
     */
    void update(EntityManager em, ConversationsEntity entity);


}
