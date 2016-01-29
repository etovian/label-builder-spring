'use strict';

angular.module('labelbuilderApp').controller('LabelDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Label',
        function($scope, $stateParams, $uibModalInstance, entity, Label) {

        $scope.label = entity;
        $scope.load = function(id) {
            Label.get({id : id}, function(result) {
                $scope.label = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('labelbuilderApp:labelUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.label.id != null) {
                Label.update($scope.label, onSaveSuccess, onSaveError);
            } else {
                Label.save($scope.label, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCreated = {};

        $scope.datePickerForCreated.status = {
            opened: false
        };

        $scope.datePickerForCreatedOpen = function($event) {
            $scope.datePickerForCreated.status.opened = true;
        };
}]);
