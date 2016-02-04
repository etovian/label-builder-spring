(function() {

    'use strict';

    function ConfirmationService($aside) {

        return {
            getConfirmation: function(message) {
                var options = {
                    bindToController: true,
                    controller: 'ConfirmationModalController',
                    controllerAs: 'vm',
                    placement: 'bottom',
                    resolve: {
                        message: function() {
                            return message;
                        }
                    },
                    size: 'modal-sm',
                    templateUrl: 'scripts/components/nutracorp/directives/confirmation/confirmation.modal.directive.html'
                };
                return $aside.open(options).result;
            }
        };
    };

    var deps = [
        '$aside',
        ConfirmationService
    ];

    angular.module('nutracorp.directives').factory('ConfirmationService', deps)
}());
