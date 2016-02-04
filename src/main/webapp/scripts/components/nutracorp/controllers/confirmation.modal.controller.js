(function() {
    'use strict';

    function ConfirmationModalController($uibModalInstance, message) {

        var vm = angular.extend(this, {
            message: message,
            cancel: function() {
                $uibModalInstance.dismiss();
            },
            confirm: function() {
                $uibModalInstance.close();
            }
        });

    }
    var deps = ['$uibModalInstance', 'message', ConfirmationModalController];
    angular.module('nutracorp.directives').controller('ConfirmationModalController', deps);
}());
