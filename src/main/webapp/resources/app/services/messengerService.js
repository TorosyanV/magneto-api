/**
 * Created by Vazgen on 6/26/2015.
 */




angular.module('magneto').factory('messengerService', function ($http, $q, $log) {
    var _isConnectedPromies = $q.defer();
    var _isConnected = false;

    var stompClient = null;



    return {
        setConnected: function () {
            $log.info('connected', connected);
        },
        connect:function(_subscribe){
            _isConnectedPromies = $q.defer();
            if(!_isConnected){
                var socket = new SockJS('/wsMessenger');

                stompClient = Stomp.over(socket);
                //  debugger;
                stompClient.connect({},
                    function (frame) {
                        var suffix = frame.headers['user-name'];
                        console.log('Connected: ' + frame);
                        _isConnected=true;
                        _isConnectedPromies.resolve(_isConnected);
                        stompClient.subscribe("/user/queue/message", function (msg) {
                            $log.info('msg.body',msg.body);
                            _subscribe(JSON.parse(msg.body));
                            //this.showGreeting(JSON.parse(greeting.body).content);
                        });
                    }, function (error) {
                        _isConnected=false;
                        $log.error(error.headers.message);
                        _isConnectedPromies.reject(_isConnected);

                    }
                );
            }
            else{
                _isConnectedPromies.resolve(_isConnected);
            }
            return _isConnectedPromies.promise;



        },

        showGreeting: function (message) {
            $log.info("showGreeting", message);
        },
        sendMessage: function (message) {
            stompClient.send("/app/wsMessenger", {}, JSON.stringify(message));
        },
        disconnect: function () {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            _isConnected=false;
            $log.info("Disconnected");
        },
        sendMessageToUser: function (message) {
            var deferred = $q.defer();
            $http({method: 'POST', url: '/messenger/sendtouser', data: message}).
                success(
                function (data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function (data, status, headers, config) {
                    deferred.reject(status);

                });
            return deferred.promise;
        },
        getchaters: function () {
            var deferred = $q.defer();
            $http({method: 'GET',cache:true, url: '/messenger/getchaters'}).
                success(
                function (data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function (data, status, headers, config) {
                    deferred.reject(status);

                });
            return deferred.promise;
        },
        getMessages: function (userTwo, pageNumber, pageSize) {
            var deferred = $q.defer();
            $http({method: 'GET', cache:true, url: "/messenger/messages/" + userTwo + "/" + pageNumber + "/" + pageSize}).
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