(function() {

	'use strict';
	angular.module('magneto').controller(
			'guestDetailController',
			[ '$scope','$log', '$stateParams', 'guestService',
					function($scope, $log,$stateParams, guestService) {
						$scope.guestPage = "OK";
					
						var guestId = $stateParams.userId;
						guestService.getDetails(guestId).then(function(guestDetail) {
							$log.info('guestDetail',guestDetail);
							$scope.guest={id:guestDetail.id, name:guestDetail.name, location:guestDetail.location, distance:guestDetail.distance};
							$log.info("$scope.guest",$scope.guest);
						}, function() {
						});
					} ]);
})();