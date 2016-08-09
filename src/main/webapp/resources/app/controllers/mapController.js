(function() {
	'use strict';
	angular
			.module('magneto')
			.controller(
					'mapController',
					[
							'$scope',
							"$rootScope",
							"$log",
							'$stateParams',
							'$state',
							'geoService',
							'messengerService',
							"userConnectionService",
							"genderIcon",
							function($scope, $rootScope, $log, $stateParams,
									$state, geoService, messengerService,
									userConnectionService, genderIcon) {
//								debugger;
								console.log($stateParams.viewtype);
								console.info('$stateParams.viewtype',
										$stateParams.viewtype);
								$scope.isMapView = $stateParams.viewtype=="map";
								$scope.isSexFilterOpened = false;
								$scope.nearbyUserList = [];
								$scope.infoUser = null;
								$scope.isInfowWindowFixed = false;
								$scope.mapZoomLevel = 20;
								$scope.setActiveViewMap = function() {
									$state.go("map", {
										"viewtype" : "map"
									});

								};
								$scope.setActiveViewList = function() {
									$state.go("map", {
										"viewtype" : "list"
									});

								};
								$scope.collapseSexFilter = function() {
									$scope.isSexFilterOpened = !$scope.isSexFilterOpened;
								};
								userConnectionService
										.getSentConnections()
										.then(
												function(sentConnections) {
													$log
															.debug(
																	"getSentConnections:Pending->",
																	sentConnections);

												}, function() {
												});

								userConnectionService
										.getReceivedConnections()
										.then(
												function(receivedConnections) {
													$log
															.debug(
																	"getReceivedConnections:Pending->",
																	receivedConnections);
												}, function() {
												});

								userConnectionService.getConnectedUsers().then(
										function(connectedUsers) {
											$log.debug("getConnectedUsers->",
													connectedUsers);
										}, function() {
										});

								// /
								// send message todo move from here it is simple
								// test

								//
								console.log('homeController');
								getZoneUsers();
								// map init
								$scope.location = {
									longitude : 0,
									latitude : 0
								};
								$scope.markers = [];

								$scope.pageName = "Map";

								// init current user location
								refreshLocation();
								$scope.$watch('location', function(newVal,
										oldVal) {
									if (newVal == oldVal)
										return;
									changeCurrentLocation(newVal);
									$log.info('changed');
								}, true);

								function addMarkersToMap(markers) {
									$scope.$broadcast('mo-map-add-markers',
											markers);
								}

								function getZoneUsers() {
									geoService
											.getNearestUsers()
											.then(
													function(userList) {
														$scope.nearbyUserList = userList;
														// bindUsersToMap(userList);
														$scope.mapZoomLevel = 13;
													},
													function(error) {
														console
																.log('error getting nearest users');
													});
								}

								$scope.$watch("nearbyUserList", function() {
									bindUsersToMap($scope.nearbyUserList);
									bindUsersToListView($scope.nearbyUserList);

									$log.debug('$scope.nearbyUserList',
											$scope.nearbyUserList);
								});

								function bindUsersToListView(userList) {
									$log.info("Bind to list view");
								}

								function bindUsersToMap(userList) {
									$scope.nearestUsers = userList;
									var markers = [];
									angular
											.forEach(
													$scope.nearestUsers,
													function(user, key) {

														var markerAdditionalData = {
															type : 'USER_MARKER',
															data : {
																id : user.id,
																sex : user.sex,
																name : user.name,
																age : user.age,
																imageUrl : user.imageUrl,
																userId : user.userId
															}
														};

														var marker = {
															additionalData : markerAdditionalData,
															position : {
																longitude : user.location.longitude,
																latitude : user.location.latitude
															},
															title : user.name
														};

														marker.icon = user.sex == true ? genderIcon.boy
																: genderIcon.girl;
														markers.push(marker);
													});
									addMarkersToMap(markers);
									$log.info('getting nearest users success');
								}

								$scope.openMessageWindowForInfoUser = function(
										user) {
									$log.info("mg-open-chat-dialog", user);
									$rootScope.$broadcast(
											'mg-open-chat-dialog', {
												user : user
											});
								}

								$scope.showUserInfoWindow = function(user) {
									/* $scope.isInfowWindowFixed = false; */
									$scope.infoUser = user;
								};
								/*
								 * $scope.deleteInfoUser = function () { if
								 * (!$scope.isInfowWindowFixed) $scope.infoUser =
								 * null;
								 *  };
								 */

								$scope.fixInfoWindow = function() {
									$scope.isInfowWindowFixed = true;
								}
								function changeCurrentLocation(location) {
									$scope
											.$broadcast(
													'mo-map-loaction-changed',
													location);
								}

								$scope.$on('mo-map-marker-click', function(evt,
										additionalData) {
									if (additionalData.type == "USER_MARKER") {
										var userInfo = {
											name : additionalData.data.name,
											sex : additionalData.data.sex,
											age : additionalData.data.age,
											id : additionalData.data.id
										};
									}

									$scope.$apply(function() {
										$scope.isInfowWindowFixed = true;
										$scope.infoUser = userInfo;
									});

									// show modal window

									console.log('mo-map-marker-click...');
								});
								function addMarkerToMap(marker) {
									var markers = [];
									markers.push(marker);
									$scope.$broadcast('mo-map-add-markers',
											markers);
								}

								function refreshLocation() {
									navigator.geolocation
											.getCurrentPosition(geoSuccess);

								}

								function geoSuccess(position) {
									console.log('position', position);
									$scope.location.latitude = position.coords.latitude;
									$scope.location.longitude = position.coords.longitude;
									$scope.$apply();// $scope.locale is object
									// angular can't handle
									// change

									// test code
									var markerAdditionalData = {
										type : 'CURRENT_USER',
										data : {}

									};
									var pos = {
										longitude : $scope.location.longitude,
										latitude : $scope.location.latitude
									};
									var marker = {
										additionalData : markerAdditionalData,
										position : pos,
										icon : "http://icons.iconarchive.com/icons/icons-land/vista-map-markers/24/Map-Marker-Marker-Outside-Chartreuse-icon.png",
										title : "it is me"
									};
									addMarkerToMap(marker);

									geoService.setCurrent(pos).then(function() {
										getZoneUsers();

									}, function(error) {
										console.log(error);
									});

								}

							} ]);
})();