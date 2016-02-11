'use strict';

angular.module('labelbuilderApp')
    .controller('LookupGroupDetailController', function ($scope, $rootScope, $stateParams, entity, LookupGroup, LookupValue) {
        $scope.lookupGroup = entity;
        $scope.load = function (id) {
            LookupGroup.get({id: id}, function(result) {
                $scope.lookupGroup = result;
            });
        };
        var unsubscribe = $rootScope.$on('labelbuilderApp:lookupGroupUpdate', function(event, result) {
            $scope.lookupGroup = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
