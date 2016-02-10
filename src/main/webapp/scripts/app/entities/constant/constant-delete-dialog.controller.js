'use strict';

angular.module('labelbuilderApp')
	.controller('ConstantDeleteController', function($scope, $uibModalInstance, entity, Constant) {

        $scope.constant = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Constant.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
