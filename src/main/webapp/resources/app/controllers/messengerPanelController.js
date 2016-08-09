/**
 * Created by vazgen on 3/22/16.
 */
(function() {

	'use strict';
	angular
			.module('magneto')
			.controller(
					'messengerPanelController',
					[
							'$scope',
							"$log",
							"soundService",
							"messengerService",
							function($scope, $log,soundService, messengerService) {
								$scope.pageName = "Home page angular";

								$scope.chatDialogs = [];
								$scope.isWebSockedConnected = false;
								var onMessage = function(message) {
									soundService.playIncomingMessage();
									console.log("Meesage coming",message);
									var messageFromUserId = message.fromUserId;
									for (var i = 0; i < $scope.chatDialogs.length; i++) {
										if ($scope.chatDialogs[i].user.id === messageFromUserId) {// currently
											// this
											// dialog
											// open
											$scope.$apply(function() {
												$scope.chatDialogs[i].messages
														.push(message);
												// or {text: message.text, seen:
												// false, mine: true, date:
												// null}
											});
											return;
										}
									}
								}
								// messengerService connection

								$scope.connect = function() {

									messengerService
											.connect(onMessage)
											.then(
													function(connectionStatus) {
														$scope.isWebSockedConnected = true;
														$log
																.info(connectionStatus
																		+ " in controller");

													},
													function(connectionStatus) {
														$scope.isWebSockedConnected = false;
														$log
																.info(connectionStatus
																		+ " in controller");
													});

								}
								$scope.connect();
								$scope.disconnect = function() {
									messengerService.disconnect();

								}

								$scope.sendName = function(txt) {

									messengerService.sendMessage(txt);
								}

								//

								// data init
								messengerService.getchaters().then(
										function(chaters) {
											$scope.users = chaters;
											$log.debug("chaters", chaters);
										}, function() {
										});

								// main functionality

								function addChatDialog(user) {
									var dialog = {
										user : user
									};
									$scope.chatDialogs.push(dialog);
								}

								$scope.removeDialog = function(dialog) {
									var idx = $scope.chatDialogs
											.indexOf(dialog);
									if (idx > -1)
										$scope.chatDialogs.splice(idx, 1);
								}

								$scope.$on('mg-open-chat-dialog', function(evt,
										data) {
									// data is user
									var user = angular.copy(data.user);
									console.log('data.user', data.user);
									addChatDialog(user);

								});
							} ]);
})();