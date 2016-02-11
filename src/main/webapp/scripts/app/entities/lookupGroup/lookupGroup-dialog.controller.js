'use strict';

angular.module('labelbuilderApp').controller('LookupGroupDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'LookupGroup', 'LookupValue',
        function($scope, $stateParams, $uibModalInstance, entity, LookupGroup, LookupValue) {

        $scope.lookupGroup = entity;
        $scope.lookupvalues = LookupValue.query();
        $scope.load = function(id) {
            LookupGroup.get({id : id}, function(result) {
                $scope.lookupGroup = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('labelbuilderApp:lookupGroupUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.lookupGroup.id != null) {
                LookupGroup.update($scope.lookupGroup, onSaveSuccess, onSaveError);
            } else {
                LookupGroup.save($scope.lookupGroup, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
