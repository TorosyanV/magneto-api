/**
 * Created by vazgen on 3/17/16.
 */
(function() {
	"use strict";
	angular.module('magneto').factory('soundService', function($http, $q) {
		var soundFileIncomeMessage = "";

		return {
			playIncomingMessage : function() {
				var  audio = new Audio('../resources/sounds/mallert.mp3');
				audio.play();
				console.log('play audio');
			}

		};
	});
})();
