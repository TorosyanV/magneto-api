package com.magneto.data.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.magneto.data.dataobject.ChaterDetails;
import com.magneto.data.dataobject.Message;
import com.magneto.data.entity.ConversationEntity;
import com.magneto.util.Pagination;

/**
 * Created by vazgen on 2/23/16.
 */
@Repository("messengerRepository")
public class MessengerRepositoryImpl implements MessengerRepository {
	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<ChaterDetails> getChaters(int userId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ChaterDetails> criteria = builder.createQuery(ChaterDetails.class);
		Root<ConversationEntity> conversation = criteria.from(ConversationEntity.class);

		criteria.multiselect(conversation.get("date"),
				builder.selectCase()
						.when(builder.equal(conversation.get("userOne").get("id"), userId), conversation.get("userTwo"))
						.otherwise(conversation.get("userOne")))
				.distinct(true)
				.where(builder.or(builder.equal(conversation.get("userOne"), userId),
						builder.equal(conversation.get("userTwo"), userId)))
				.orderBy(builder.asc(conversation.get("date")));
		TypedQuery query = em.createQuery(criteria);
		List<ChaterDetails> chaterDetails = query.getResultList();
		return chaterDetails;
	}

	@Override
	public List<Message> getMessages(int userOne, int userTwo,Pagination pagination) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
		Root<ConversationEntity> conversation = criteria.from(ConversationEntity.class);
		criteria.multiselect(conversation)
				.where(builder.or(
						builder.and(builder.equal(conversation.get("userOne"), userOne),
								builder.equal(conversation.get("userTwo"), userTwo)),
						builder.and(builder.equal(conversation.get("userOne"), userTwo),
								builder.equal(conversation.get("userTwo"), userOne))))
				.orderBy(builder.desc(conversation.get("id")));
		TypedQuery query = em.createQuery(criteria);
		query
			.setFirstResult(pagination.getPageNumber()-1)
			.setMaxResults(pagination.getPageSize());
		
		
		List<Message> messages = query.getResultList();
		return messages;

	}
}
