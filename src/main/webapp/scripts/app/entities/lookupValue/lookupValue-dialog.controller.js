'use strict';

angular.module('labelbuilderApp').controller('LookupValueDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'LookupValue', 'LookupGroup',
        function($scope, $stateParams, $uibModalInstance, entity, LookupValue, LookupGroup) {

        $scope.lookupValue = entity;
        $scope.lookupgroups = LookupGroup.query();
        $scope.load = function(id) {
            LookupValue.get({id : id}, function(result) {
                $scope.lookupValue = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('labelbuilderApp:lookupValueUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.lookupValue.id != null) {
                LookupValue.update($scope.lookupValue, onSaveSuccess, onSaveError);
            } else {
                LookupValue.save($scope.lookupValue, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
