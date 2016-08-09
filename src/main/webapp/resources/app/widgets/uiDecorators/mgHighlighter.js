/**
 * Created by Vazgen on 3/25/2016.
 */

(function () {
    "use strict";

    angular.module("widgets").directive("mgHighlightOnCreation", function () {
        return {

            link: function (scope, element, attrs) {
                var hClass = attrs["mgHighlightOnCreation"];
                if (hClass) {
                    element.addClass(hClass);
                }

                //highlight
            }
        };
    })
})();