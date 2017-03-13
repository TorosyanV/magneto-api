package com.magneto.service.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magneto.data.entity.UserEntity;
import com.magneto.data.entity.UserGeolocationEntity;
import com.magneto.data.repository.GeoRepository;
import com.magneto.data.repository.UserRepository;
import com.magneto.dto.NearUserInfoShortDto;
import com.magneto.dto.RegistrationResult;
import com.magneto.dto.User;
import com.magneto.dto.UserDetailsDto;
import com.magneto.dto.UserInfoDto;
import com.magneto.location.Calculator;
import com.magneto.location.Geolocated;
import com.magneto.location.Geolocation;
import com.magneto.web.viewmodel.RegistrationRequest;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private GeoRepository geoRepository;

	@Autowired
	private UserRepository userRepository;

	public UserEntity findById(int id) {
		return userRepository.findById(id);
	}

	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public RegistrationResult createUserIfNotExist(RegistrationRequest registrationForm) {
		System.out.println("createUserIfNotExistcreateUserIfNotExistcreateUserIfNotExistcreateUserIfNotExist");
		RegistrationResult result;

		UserEntity user = new UserEntity();
		user.setEmail(registrationForm.getEmail());
		user.setFirstName(registrationForm.getName());
		user.setLastName("Last name");
		user.setPassword(registrationForm.getPassword());
		user.setState("Active");
		result = userRepository.createIfNotExist(user);
		return result;
	}

	@Override
	public void refreshLocation(Geolocated<Integer> located) {

		UserGeolocationEntity userLocation = new UserGeolocationEntity();
		userLocation.setLatitude(located.getGeoLocation().getLatitude());
		userLocation.setLongitude(located.getGeoLocation().getLongitude());

		userRepository.refreshLocation(located.getKey(), userLocation);


	}

	@Override
	public Geolocation getCurrentLocation(com.magneto.dto.User user) {
		return userRepository.getCurrentLocation(user.getId());
	}

	private List<UserInfoDto> loadAllUsersShortInfo() {
		List<UserInfoDto> userInfoList = new ArrayList<>();

		List<UserGeolocationEntity> locations = geoRepository.loadAllUsersLocation();
		for (UserGeolocationEntity loc : locations) {

			UserInfoDto dto = new UserInfoDto();
			Geolocation userLocation = new Geolocation();
			userLocation.setLatitude(loc.getLatitude());
			userLocation.setLongitude(loc.getLongitude());

			dto.setName(loc.getUser().getFirstName());
			dto.setSex(loc.getUser().isSex());
			dto.setId(loc.getUser().getId());
			dto.setLocation(userLocation);
			userInfoList.add(dto);
		}
		System.out.println("loadAllUsersShortInfo, COUNT: " + userInfoList.size());
		return userInfoList;
	}

	@Override
	public List<NearUserInfoShortDto> getNerby(User user, double meter) {
		Geolocation currentUserLocation = getCurrentLocation(user);
		List<UserInfoDto> allUserInfoList = loadAllUsersShortInfo();
		List<NearUserInfoShortDto> nearbyUsers = new ArrayList<>();
		for (UserInfoDto u : allUserInfoList) {

			if (u.getId() == user.getId())
				continue;

			double distance = Calculator.getDistance(currentUserLocation, u.getLocation());
			if (meter > distance) {
				NearUserInfoShortDto dto = new NearUserInfoShortDto();

				dto.setName(u.getName());
				dto.setSex(u.isSex());
				dto.setId(u.getId());
				dto.setLocation(u.getLocation());
				dto.setDistance(distance);
				nearbyUsers.add(dto);
			}

		}

		System.out.println("getNerby, COUNT: " + nearbyUsers.size());
		Collections.sort(nearbyUsers, new DistancePointComparator());
		return nearbyUsers;
	}

	class DistancePointComparator implements Comparator<NearUserInfoShortDto> {

		@Override
		public int compare(NearUserInfoShortDto l1, NearUserInfoShortDto l2) {
			if (l1.getDistance() < l2.getDistance()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	@Override
	public UserDetailsDto getUserDetail(int userId) {
		UserDetailsDto dtoResult = new UserDetailsDto();
		UserEntity user = userRepository.findById(userId);
		dtoResult.setId(userId);
		Geolocation location = new Geolocation();
		location.setLongitude(user.getCurrentLocation().getLongitude());
		location.setLatitude(user.getCurrentLocation().getLatitude());
		dtoResult.setLocation(location);
		
		dtoResult.setName(user.getFirstName()+user.getLastName());
		dtoResult.setSex(user.isSex());

		return dtoResult;
	}

}