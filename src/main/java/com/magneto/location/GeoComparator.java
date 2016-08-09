package com.magneto.location;

import java.util.Comparator;

public class GeoComparator implements Comparator<Geolocation> {
	@Override
	public int compare(Geolocation l1, Geolocation l2) {
		if (l1.getLongitude() < l2.getLongitude()) {
			return 1;
		} else {
			return -1;
		}
	}
}
