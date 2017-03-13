package com.magneto.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.magneto.dto.User;
import com.magneto.dto.UserDetailsDto;
import com.magneto.location.Calculator;
import com.magneto.service.user.UserService;
import com.magneto.web.viewmodel.GeolocationModel;
import com.magneto.web.viewmodel.GuestDetailResponce;

@RestController
@RequestMapping({ "/geust", "/api/guest" })
public class GuestController extends AuthorizedController {
	@Autowired
	private UserService userService;

	@GetMapping("/detail/{userId}")
	public GuestDetailResponce getLocation(@PathVariable int userId) {

		User user = super.getUser();
		GuestDetailResponce responce = new GuestDetailResponce();

		UserDetailsDto userDetail = userService.getUserDetail(userId);

		responce.setId(userDetail.getId());
		responce.setName(userDetail.getName());
		responce.setSex(userDetail.isSex());
		GeolocationModel location = new GeolocationModel();
		location.setLongitude(userDetail.getLocation().getLongitude());
		location.setLatitude(userDetail.getLocation().getLongitude());
		responce.setLocation(location);
		responce.setDistance(Calculator.getDistance(user.getGeoLocation(), userDetail.getLocation()));
		return responce;
	}
}
