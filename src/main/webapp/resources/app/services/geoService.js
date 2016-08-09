/**
 * Created by Vazgen on 6/26/2015.
 */
angular.module('magneto').factory('geoService', function ($http, $q) {
    return {
        getCurrentLocation: function () {
            var deferred = $q.defer();
            $http({ method: 'GET', url: serviceBase + 'api/geo/getcurrent'}).
                success(
                function (data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function (data, status, headers, config) {
                    deferred.reject(status);

                });
            return deferred.promise;
        },
        setCurrent: function (location) {
            var deferred = $q.defer();
            $http({ method: 'POST', url: '/geo/location',data: location}).
                success(
                function (data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function (data, status, headers, config) {
                    console.log('error',data);
                    deferred.reject(status);
                });
            return deferred.promise;
        },
        getNearestUsers: function () {
            var deferred = $q.defer();
            $http({ method: 'GET', cache:true, url:  '/geo/nearby/2000000' }).
                success(
                function (data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function (data, status, headers, config) {
                    deferred.reject(status);
                });
            return deferred.promise;
        }
    };
});