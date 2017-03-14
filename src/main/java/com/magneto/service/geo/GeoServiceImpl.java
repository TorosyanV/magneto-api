package com.magneto.service.geo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magneto.web.model.GeolocationModel;

@Service("geoService")
@Transactional
public class GeoServiceImpl implements GeoService {

	@Override
	public void setUserLocation(GeolocationModel location) {
		// TODO Auto-generated method stub

	}

}
