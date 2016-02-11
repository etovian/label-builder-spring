'use strict';

angular.module('labelbuilderApp')
    .controller('DieDetailController', function ($scope, $rootScope, $stateParams, entity, Die) {
        $scope.die = entity;
        $scope.load = function (id) {
            Die.get({id: id}, function(result) {
                $scope.die = result;
            });
        };
        var unsubscribe = $rootScope.$on('labelbuilderApp:dieUpdate', function(event, result) {
            $scope.die = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
