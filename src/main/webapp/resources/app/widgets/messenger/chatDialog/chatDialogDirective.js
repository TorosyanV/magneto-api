/**
 * Created by Vazgen on 3/22/2016.
 */

(function () {
    "use strict";

    angular.module("messenger").directive("mgChatDialog", ["$log", function ($log) {

        return {
            templateUrl: "/resources/app/widgets/messenger/chatDialog/chatDialog.tpl.html",
            scope: {
                user: "=user",   //data sample ({"id":102,"email":"sit@odio.ca","name":"Abbot, Hoover"})
                removeDialog: "&removeDialog",
                messages: "=messages",
            },
            controller: function ($scope, $log, messengerService) {

              
                console.log('messages', $scope.messages);
                $scope.close = function () {

                    $scope.removeDialog();
                }
                messengerService.getMessages($scope.user.id, 1, 10).then(function (messages) {
                    console.log('messages', messages);
                    $scope.messages = messages;

                }, function () {
                });

                $scope.sendMessage = function (messageText) {

                    var message = {
                        "toUserId": $scope.user.id,
                        "text": messageText
                    };


                    messengerService.sendMessage(message);
                    $scope.messages.push({text: message.text, seen: false, mine: true, date: null});
                    $scope.messageText = "";

                    console.log(message);

                };


                function appendToMessages(message) {
                    //message={ text,date,seen,mine}
                    $scope.messages.push(message);
                }
            },

            link: function (scope, element, attrs) {
                element.find("input:text").focus();
                console.log(element);
                console.log('link');

            }
        };


    }])
})();