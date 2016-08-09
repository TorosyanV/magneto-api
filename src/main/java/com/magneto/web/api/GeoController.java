package com.magneto.web.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magneto.dto.NearUserInfoShortDto;
import com.magneto.dto.User;
import com.magneto.location.Geolocation;
import com.magneto.service.user.UserService;
import com.magneto.web.viewmodel.GeolocationModel;
import com.magneto.web.viewmodel.NearUserInfoResponce;
import com.magneto.web.viewmodel.UserInfoResponce;

@RestController
@RequestMapping({ "/geo", "/api/geo" })
public class GeoController extends AuthorizedController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/location", method = RequestMethod.POST)
	public void setLocation(@RequestBody GeolocationModel location) {

		User user = super.getUser();
		Geolocation userLocation = new Geolocation();

		userLocation.setLongitude(location.getLongitude());
		userLocation.setLatitude(location.getLatitude());
		user.setGeolocation(userLocation);
		userService.refreshLocation(user);
	}

	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public GeolocationModel getLocation() {

		User user = super.getUser();

		Geolocation location = userService.getCurrentLocation(user);
		GeolocationModel model = new GeolocationModel();
		model.setLatitude(location.getLatitude());
		model.setLongitude(location.getLongitude());
		return model;
	}

	@RequestMapping(value = "/nearby/{meter}", method = RequestMethod.GET)
	public List<UserInfoResponce> getNearby(@PathVariable double meter) {

		User user = super.getUser();
		List<UserInfoResponce> model = new ArrayList<>();

		List<NearUserInfoShortDto> userInfos = userService.getNerby(user, meter);

		for (NearUserInfoShortDto u : userInfos) {
			
			
			
			NearUserInfoResponce sh = new NearUserInfoResponce();
			GeolocationModel location = new GeolocationModel();
			location.setLatitude(u.getLocation().getLatitude());
			location.setLongitude(u.getLocation().getLongitude());
			sh.setName(u.getName());
			sh.setSex(u.isSex());
			sh.setId(u.getId());
			sh.setLocation(location);
			sh.setDistane(u.getDistance());
			model.add(sh);
		}

		return model;

	}

}
