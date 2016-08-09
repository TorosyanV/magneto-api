package com.magneto.data.repository;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.magneto.data.entity.UserEntity;
import com.magneto.data.entity.UserGeolocationEntity;
import com.magneto.dto.RegistrationResult;
import com.magneto.location.Geolocation;

@Repository("userRepository")
// @NamedQuery(name = "findEmployeesAboveSal", query = "SELECT e FROM Employee e
// WHERE e.department = :dept AND e.salary > :sal")
public class UserRepositoryImpl extends AbstractDao<Integer, UserEntity> implements UserRepository {

	// @PersistenceContext
	// private EntityManager em;

	// private SessionFactory sessionFactory;

	public UserEntity findById(int id) {
		return em.find(UserEntity.class, id);
	}

	public UserEntity findByEmail(String email) {
		TypedQuery<UserEntity> query = em.createQuery("SELECT u  FROM UserEntity AS u where u.email=:em",
				UserEntity.class);
		query.setParameter("em", email);
		UserEntity user = query.getSingleResult();
		return user;
	}

	public RegistrationResult createIfNotExist(UserEntity user) {
		System.out.println("aaa" + user);
		em.persist(user);
		return RegistrationResult.SUCCESS;
	}

	@Override
	public void refreshLocation(int userId, UserGeolocationEntity location) {

		UserEntity user = em.find(UserEntity.class, userId);
		if (user.getCurrentLocation() == null) {
			user.setCurrentLocation(location);
			location.setUser(user);
		} else {
			UserGeolocationEntity loc = user.getCurrentLocation();
			loc.setLongitude(location.getLongitude());
			loc.setLatitude(location.getLatitude());
			user.setCurrentLocation(loc);
		}
		em.merge(user);

	}

	@Override
	public Geolocation getCurrentLocation(int userId) {
		Geolocation loc = new Geolocation();
		UserGeolocationEntity location = em.find(UserGeolocationEntity.class, userId);
		if (location != null) {
			loc.setLatitude(location.getLatitude());
			loc.setLongitude(location.getLongitude());
		}
		return loc;
	}

}