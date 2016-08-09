(function () {
    'use strict';
    angular.module('magneto.routing', ['ui.router']);
    angular.module('magneto.routing').config(
        ['$locationProvider', '$stateProvider',
            function ($locationProvider, $stateProvider) {

                // enable html5Mode for pushstate ('#'-less URLs)
                $locationProvider.html5Mode(true);
                // $urlRouterProvider.otherwise('/');
                $stateProvider.
                    state('home', {
                        url: '/',
                        templateUrl: 'resources/app/views/home.html',
                        controller: 'homeController'
                    })
                    .state('map', {
                        url: '/map?viewtype',
                        templateUrl: 'resources/app/views/map.html',
                        controller: 'mapController'
                    })
                     .state('guestDetail', {
                        url: '/guest/:userId',
                        templateUrl: 'resources/app/views/guestDetail.html',
                        controller: 'guestDetailController'
                    })
            }]);

})();