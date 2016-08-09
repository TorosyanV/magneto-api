/**
 * Created by Vazgen on 08-Apr-16.
 */
(function () {
    angular.module('widgets').directive('mgScrollable', ["$timeout", function ($timeout) {
        return {
            restrict: 'A',
            scope: {
                scrollTo: '=',
                scrollOnChange: '=',
                scrollUseAnimation: '=',
                scrollAnimationSpeed: "="
            },
            link: function (scope, element, attrs) {
                scope.$watch('scrollOnChange', function (newVal, oldVal) {
                    if (newVal != oldVal) {
                        if (scope.scrollUseAnimation) {
                            $timeout(function () {
                                element.animate({scrollTop: scope.scrollTo}, scope.scrollAnimationSpeed);
                            });
                            console.log('will scroll to with animation', scope.scrollTo);
                        } else {
                            console.log('will scroll to', scope.scrollTo);
                            $timeout(function () {
                                element.scrollTop(scope.scrollTo);
                            });
                        }
                    }
                }, true);
            }
        };
    }]);
}());



