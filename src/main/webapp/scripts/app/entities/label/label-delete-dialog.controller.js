'use strict';

angular.module('labelbuilderApp')
	.controller('LabelDeleteController', function($scope, $uibModalInstance, entity, Label) {

        $scope.label = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            //Label.delete({id: id},
            //    function () {
            //        $uibModalInstance.close(true);
            //    });
            Label.delete({id: id});
            $uibModalInstance.close(true);
        };

    });
