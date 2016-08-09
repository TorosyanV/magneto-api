package com.magneto.location;

public interface Geolocated<T> {

	void setGeolocation(Geolocation location);

	Geolocation getGeoLocation();

	T getKey();
}
