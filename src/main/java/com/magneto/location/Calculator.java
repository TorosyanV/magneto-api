package com.magneto.location;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//http://www.movable-type.co.uk/scripts/latlong.html
public class Calculator {

	public class DistancePoint {

		public DistancePoint(double distance, Geolocation location) {
			super();
			this.distance = distance;
			this.location = location;
		}

		private double distance;

		public double getDistance() {
			return distance;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}

		public Geolocation getLocation() {
			return location;
		}

		public void setLocation(Geolocation location) {
			this.location = location;
		}

		private Geolocation location;
	}

	// Radius of the earth in metres
	final static int R = 6371000;

	public static double getDistance(Geolocation loc1, Geolocation loc2) {
		if (loc1 == null || loc2 == null)
			throw new IllegalArgumentException("Can't calculate distance on null location");
		double dLat = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
		double dLng = Math.toRadians(loc2.getLongitude() - loc1.getLongitude());
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(loc1.getLatitude()))
				* Math.cos(Math.toRadians(loc2.getLatitude()));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = R * c;
		System.out.println("distance->" + dist);
		return dist;
	}

	
	

	

}
