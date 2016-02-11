'use strict';

angular.module('labelbuilderApp')
    .controller('LookupValueDetailController', function ($scope, $rootScope, $stateParams, entity, LookupValue, LookupGroup) {
        $scope.lookupValue = entity;
        $scope.load = function (id) {
            LookupValue.get({id: id}, function(result) {
                $scope.lookupValue = result;
            });
        };
        var unsubscribe = $rootScope.$on('labelbuilderApp:lookupValueUpdate', function(event, result) {
            $scope.lookupValue = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
