package com.magneto.data.repository;

import com.magneto.data.entity.ConversationEntity;
import com.magneto.data.entity.ConversationReplyEntity;
import com.magneto.data.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by JW on 2/18/16.
 */
@Repository("conversationRepository")
public class ConversationRepositoryImpl implements ConversationRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void sendMessage(int user1, int user2, String text) {

        UserEntity userOne = em.find(UserEntity.class, user1);
        UserEntity userTwo = em.find(UserEntity.class, user2);
        ConversationReplyEntity conversationReplyEntity = new ConversationReplyEntity();
        conversationReplyEntity.setReplyText(text);
        conversationReplyEntity.setUser(userOne);

        ConversationEntity conversationEntity = new ConversationEntity();
        conversationEntity.setUserOne(userOne);
        conversationEntity.setUserTwo(userTwo);
        conversationEntity.setConversationReply(conversationReplyEntity);
        conversationReplyEntity.setConversation(conversationEntity);

        em.persist(conversationEntity);
    }
}
