/**
 * Created by vazgen on 3/17/16.
 */
(function() {
	"use strict";
	angular.module('magneto').factory('guestService', function($http, $q) {
		return {
			getDetails : function(userId) {
				var deferred = $q.defer();
				$http({
					method : "GET",
					url : "/geust/detail/" + userId
				}).success(function(data, status, headers, config) {
					deferred.resolve(data);
				}).error(function(data, status, headers, config) {
					deferred.reject(status);
				});
				return deferred.promise;
			},
			getConnectedUsers : function() {
				var deferred = $q.defer();
				$http({
					method : "GET",
					url : "/connection/connected"
				}).success(function(data, status, headers, config) {
					deferred.resolve(data);
				}).error(function(data, status, headers, config) {
					deferred.reject(status);
				});
				return deferred.promise;
			},

		};
	});
})();
