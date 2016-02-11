'use strict';

angular.module('labelbuilderApp')
	.controller('LookupValueDeleteController', function($scope, $uibModalInstance, entity, LookupValue) {

        $scope.lookupValue = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            LookupValue.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
