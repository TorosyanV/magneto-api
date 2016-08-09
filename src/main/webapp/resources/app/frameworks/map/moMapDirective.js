/**
 * Created by Vazgen on 8/2/2015.
 */
var l_map = {};
var l_circle = {};
var l_n_circle = {};
(function() {
	'use strict';
	angular
			.module('moMap')
			.directive(
					'moMap',
					function() {
						return {
							restrict : 'EA',
							replace : true,
							scope : {
								locationCurrent : '=',
								markers : '=',
								zoom : '='

							},
							template : '<div></div>',
							link : function(scope, element, attrs) {
								if (!!scope.zoom)
									setZoom(20);
								// map initilization
								var longitude = scope.locationCurrent.longitude;
								var latitude = scope.locationCurrent.latitude;
								var availableCircleArea;
								// watchers
								scope.$watch('zoom', function(newValue,
										oldValue) {
									if (newValue != oldValue)
										setZoom(newValue);
								});

								var myLatLng = new google.maps.LatLng(
										longitude, latitude);
								// https://developers.google.com/maps/documentation/javascript/3.22/reference#MapTypeStyleFeatureType
								var styles = [ {
									stylers : [ {
										hue : "#031323"
									}, {
										saturation : -20
									}
									// {color:"#031323"}
									]
								}, {
									featureType : "road",
									elementType : "geometry",
									stylers : [ {
										lightness : 100
									}, {
										visibility : "simplified"
									}

									]
								}, {
									featureType : "water",
									elementType : "geometry",
									stylers : [ {
										lightness : 20
									}, {
										visibility : "on"
									}, {
										color : "#1B4269"
									} ]
								}, {
									featureType : "road",
									elementType : "labels",
									stylers : [ {
										visibility : "off"
									} ]
								} ];

								var customMapType = new google.maps.StyledMapType(
										styles

										, {
											name : 'Magneto'
										});
								var customMapTypeId = 'custom_style';

								var myMap = new google.maps.Map(document
										.getElementById(attrs.id), {
									zoom : 12,
									center : {
										lat : 40.674,
										lng : -73.946
									}, // Brooklyn.
									mapTypeControlOptions : {
										mapTypeIds : [
												google.maps.MapTypeId.ROADMAP,
												customMapTypeId ]
									}
								});
								l_map = myMap;
								myMap.mapTypes.set(customMapTypeId,
										customMapType);
								myMap.setMapTypeId(customMapTypeId);

								// set circle

								// handlers
								scope.$on('mo-map-add-markers', function(evt,
										markers) {
									// console.log('adding markers...',
									// markers);
									appendMarkers(markers);

								});

								scope
										.$on(
												'mo-map-loaction-changed',
												function(evt, newlocation) {
													console.log('newlocation',
															newlocation);
													changeActiveView(newlocation);
													if (!!availableCircleArea)
														availableCircleArea
																.setMap(null);

													availableCircleArea = new google.maps.Circle(
															{
																map : myMap,
																strokePosition : google.maps.StrokePosition.OUTSIDE,
																strokeWeight : 40,
																strokeOpacity : 0.6,
																radius : 4000,

																strokeColor : "#75b9e7",
																center : new google.maps.LatLng(
																		newlocation.latitude,
																		newlocation.longitude),
																fillColor : 'green',
																fillOpacity : 0.1,
																editable : false
															});

													l_circle = availableCircleArea;
													// circle.setMap(myMap);
													// circle is the
													// google.maps.Circle-instance
													google.maps.event
															.addListener(
																	availableCircleArea,
																	'mouseover',
																	function() {

																		console
																				.log(this);
																		this
																				.getMap()
																				.getDiv()
																				.setAttribute(
																						'title',
																						'title');
																	});

													google.maps.event
															.addListener(
																	availableCircleArea,
																	'mouseout',
																	function() {
																		this
																				.getMap()
																				.getDiv()
																				.removeAttribute(
																						'title');
																	});

												});

								// functions
								function appendMarkers(markers) {
									for (var i = 0; i < markers.length; i++) {

										if (i > 0
												&& markers[i].position.latitude == markers[i - 1].position.latitude
												&& markers[i - 1].position.longitude) {
											markers[i].position.latitude += 0.0000100;
											markers[i].position.longitude += 0.0000100;
										}

										var pos = new google.maps.LatLng(
												markers[i].position.latitude,
												markers[i].position.longitude);
										var title = markers[i].title;
										var marker = new google.maps.Marker(
												{
													additionalData : markers[i].additionalData,
													position : pos,
													animation : google.maps.Animation.DROP,
													map : myMap,
													title : title,
													icon : markers[i].icon,
												});

										// /event bindings
										google.maps.event
												.addListener(
														marker,
														'mouseover',
														function() {
															var additionalData = this.additionalData;
															raisingMarkerMouseOver(additionalData);
														});
										google.maps.event
												.addListener(
														marker,
														'mouseout',
														function() {
															var additionalData = this.additionalData;
															raisingMarkerMouseOut(additionalData);
														});
										google.maps.event
												.addListener(
														marker,
														'click',
														function() {
															var additionalData = this.additionalData;
															raisingMarkerClick(additionalData);
														});
										// console.log('adding marker:',
										// marker);
										marker.setMap(myMap);
									}
								}

								function changeActiveView(location) {

									myMap.setCenter(new google.maps.LatLng(
											location.latitude,
											location.longitude));
								}
								function setZoom(zoomLevel) {
									if (!!myMap)
										myMap.setZoom(zoomLevel);
								}
								function raisingMarkerMouseOver(additionalData) {
									scope.$emit('mo-map-marker-mouse-over',
											additionalData);
								}

								function raisingMarkerMouseOut(additionalData) {
									scope.$emit('mo-map-marker-mouse-out',
											additionalData);
								}

								function raisingMarkerClick(additionalData) {
									scope.$emit('mo-map-marker-click',
											additionalData);
								}

							}
						};
					});
})();
