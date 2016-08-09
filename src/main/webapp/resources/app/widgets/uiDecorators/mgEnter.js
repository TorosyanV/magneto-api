/**
 * Created by Vazgen on 08-Apr-16.
 */
/**
 * Created by Vazgen on 08-Apr-16.
 */
(function () {
    angular.module('widgets').directive('mgEnter', [function () {
        return function (scope, element, attrs) {
            element.bind("keydown keypress", function (event) {
                if (event.which === 13) {
                    scope.$apply(function () {
                        scope.$eval(attrs.mgEnter);
                    });

                    event.preventDefault();
                }
            });
        };
    }]);
}());



