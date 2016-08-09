/**
 * Created by Vazgen on 3/22/2016.
 */

(function () {
    "use strict";

    angular.module("messenger").directive("mgMessengerPanel", function () {

        return {
            templateUrl: "/resources/app/widgets/messenger/panel/messengerPanel.tpl.html",
            scope: {
                users: "=users",   //data sample ({"id":102,"email":"sit@odio.ca","name":"Abbot, Hoover"})
                minimized: "=minimized"
            },
            controller: function ($scope) {
                console.log('messenger');
                console.log('$scope', $scope.users);

                $scope.collapse = function () {
                    $scope.minimized = !$scope.minimized;
                }
            },

            link: function (scope, element, attrs) {

            }
        };


    })
})();