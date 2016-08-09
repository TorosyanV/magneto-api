/**
 * Created by vazgen on 3/26/16.
 */

(function () {
    "use strict";

    angular.module('widgets').directive('mgAccordion', function () {
        return {

            template: '  <div ng-transclude> </div>',
            restrict: 'E',
            transclude:true,
            replace:true,
            scope:true,
            link: function (scope, element, attrs) {

                scope.$watch(attrs.opened, function(value){

                    if(value == true)
                        $(element).find('mg-accordion-body').show();
                    else
                        $(element).find('mg-accordion-body').hide();
                });

            }
        };
    });
})();