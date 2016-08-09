/**
 * Created by vazgen on 3/17/16.
 */
(function () {
    "use strict";
    angular.module('magneto').factory('userConnectionService', function ($http, $q) {
        return {
            getConnectionStatus: function (userId) {
                var deferred = $q.defer();
                $http({method: "GET",cache:true, url: "/connection/status/" + userId}).
                    success(
                    function (data, status, headers, config) {
                        deferred.resolve(data);
                    }).
                    error(function (data, status, headers, config) {
                        deferred.reject(status);
                    });
                return deferred.promise;
            },
            getConnectedUsers: function () {
                var deferred = $q.defer();
                $http({method: "GET", cache:true, url: "/connection/connected"}).
                    success(
                    function (data, status, headers, config) {
                        deferred.resolve(data);
                    }).
                    error(function (data, status, headers, config) {
                        deferred.reject(status);
                    });
                return deferred.promise;
            },
            getReceivedConnections: function () {
                var deferred = $q.defer();
                $http({method: "GET", cache:true, url: "/connection/received"}).
                    success(
                    function (data, status, headers, config) {
                        deferred.resolve(data);
                    }).
                    error(function (data, status, headers, config) {
                        deferred.reject(status);
                    });
                return deferred.promise;
            },
            getSentConnections: function () {
                var deferred = $q.defer();
                $http({method: "GET",cache:true, url: "/connection/sent"}).
                    success(
                    function (data, status, headers, config) {
                        deferred.resolve(data);
                    }).
                    error(function (data, status, headers, config) {
                        deferred.reject(status);
                    });
                return deferred.promise;
            },
            sendConnectionRequest: function (userId) {
                var deferred = $q.defer();
                $http({method: "POST", url: "/connection/sendrequest", data:{"userId":userId}}).
                    success(
                    function (data, status, headers, config) {
                        deferred.resolve(data);
                    }).
                    error(function (data, status, headers, config) {
                        deferred.reject(status);
                    });
                return deferred.promise;
            },
            acceptConnectionRequest: function (userId) {
                var deferred = $q.defer();
                $http({method: "POST", url: "/connection/acceptrequest", data:{"userId":userId}}).
                    success(
                    function (data, status, headers, config) {
                        deferred.resolve(data);
                    }).
                    error(function (data, status, headers, config) {
                        deferred.reject(status);
                    });
                return deferred.promise;
            },
            rejectConnectionRequest: function (userId) {
                var deferred = $q.defer();
                $http({method: "POST", url: "/connection/rejectrequest", data:{"userId":userId}}).
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
})();

