package com.magneto.data.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.magneto.data.entity.UserGeolocationEntity;

@Repository("geoRepository")
public class GeoRepositoryImpl implements GeoRepository {

	@PersistenceContext
	protected EntityManager em;

	@Cacheable("usersLocation")
	public List<UserGeolocationEntity> loadAllUsersLocation() {
		// todo logic

		Query query = em.createQuery("SELECT l FROM UserGeolocationEntity l");

		// org.hibernate.Query hquery = query.unwrap(org.hibernate.Query.class);
		// hquery.setCacheable(true);
		// query.setHint("org.hibernate.cacheable", Boolean.TRUE);
		// query.setCacheable(true);
		List<UserGeolocationEntity> list = query.getResultList();
		return list;

	}

}
