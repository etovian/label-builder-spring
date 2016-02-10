'use strict';

angular.module('labelbuilderApp').controller('ConstantDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Constant',
        function($scope, $stateParams, $uibModalInstance, entity, Constant) {

        $scope.constant = entity;
        $scope.load = function(id) {
            Constant.get({id : id}, function(result) {
                $scope.constant = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('labelbuilderApp:constantUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.constant.id != null) {
                Constant.update($scope.constant, onSaveSuccess, onSaveError);
            } else {
                Constant.save($scope.constant, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
