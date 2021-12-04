package be.atc.salesmanagercrm.dao.impl;

import be.atc.salesmanagercrm.dao.ConversationsDao;
import be.atc.salesmanagercrm.entities.ConversationsEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Larché Marie-Élise
 */
public class ConversationsDaoImpl implements ConversationsDao {


    @Override
    public ConversationsEntity findById(EntityManager em, int id) {
        return em.find(ConversationsEntity.class, id);
    }


    public void add(EntityManager em, ConversationsEntity conversationsEntity) {
        em.persist(conversationsEntity);
    }

    @Override
    public List<ConversationsEntity> findAll(EntityManager em) {
        return em.createNamedQuery("Conversations.findAll",
                ConversationsEntity.class)
                .getResultList();
    }

    @Override
    public List<ConversationsEntity> findAllConv(EntityManager em) {
        return em.createNamedQuery("Conversations.findAllConv",
                ConversationsEntity.class)
                .getResultList();
    }

    @Override
    public List<ConversationsEntity> find10Last(EntityManager em) {
        return em.createNamedQuery("Conversations.findAll",
                ConversationsEntity.class)
                .setMaxResults(50)
                .getResultList();
    }

    @Override
    public void update(EntityManager em, ConversationsEntity entity) {
        em.merge(entity);
    }


}
