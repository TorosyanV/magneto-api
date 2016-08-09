/**
 * Created by vazgen on 3/22/16.
 */
/**
 * Created by Vazgen on 3/22/2016.
 */

(function () {
    "use strict";

    angular.module("messenger").directive("mgMessengerPanelUserItem",function(){

        return {
            templateUrl:"/resources/app/widgets/messenger/panel/messengerPanelUserItem.tpl.html",
            scope:{
                user:"=user",   //data sample ({"id":102,"email":"sit@odio.ca","name":"Abbot, Hoover"})
                minimized:"=minimized"
            },
            controller:function($scope){
                console.log('messenger');
                console.log('$scope',$scope.user);
                $scope.openMessageDialog=function(user){

                    $scope.$emit('mg-open-chat-dialog', {user:$scope.user});
                }
            },

            link:function(scope, element, attrs){

            }
        };


    })
})();