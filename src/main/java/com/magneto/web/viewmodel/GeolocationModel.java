package com.magneto.web.viewmodel;

public class GeolocationModel {

	private Double longitude;
	private Double latitude;

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String toString() {

		return "longitude:" + longitude + ",latitude:" + latitude;
	}

}
