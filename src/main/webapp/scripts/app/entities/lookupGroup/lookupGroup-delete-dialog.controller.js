'use strict';

angular.module('labelbuilderApp')
	.controller('LookupGroupDeleteController', function($scope, $uibModalInstance, entity, LookupGroup) {

        $scope.lookupGroup = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            LookupGroup.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
