'use strict';

angular.module('labelbuilderApp').controller('DieDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Die',
        function($scope, $stateParams, $uibModalInstance, entity, Die) {

        $scope.die = entity;
        $scope.load = function(id) {
            Die.get({id : id}, function(result) {
                $scope.die = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('labelbuilderApp:dieUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.die.id != null) {
                Die.update($scope.die, onSaveSuccess, onSaveError);
            } else {
                Die.save($scope.die, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForLastModified = {};

        $scope.datePickerForLastModified.status = {
            opened: false
        };

        $scope.datePickerForLastModifiedOpen = function($event) {
            $scope.datePickerForLastModified.status.opened = true;
        };
}]);
