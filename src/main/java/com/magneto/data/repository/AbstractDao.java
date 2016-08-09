package com.magneto.data.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) em.find(persistentClass.getClass(), key);
	}

	public void persist(T entity) {
		em.persist(entity);
	}

	public void delete(T entity) {
		em.remove(entity);
	}

}