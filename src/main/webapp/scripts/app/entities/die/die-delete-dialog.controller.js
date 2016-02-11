'use strict';

angular.module('labelbuilderApp')
	.controller('DieDeleteController', function($scope, $uibModalInstance, entity, Die) {

        $scope.die = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Die.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
