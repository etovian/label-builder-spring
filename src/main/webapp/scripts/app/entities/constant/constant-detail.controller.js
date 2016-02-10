'use strict';

angular.module('labelbuilderApp')
    .controller('ConstantDetailController', function ($scope, $rootScope, $stateParams, entity, Constant) {
        $scope.constant = entity;
        $scope.load = function (id) {
            Constant.get({id: id}, function(result) {
                $scope.constant = result;
            });
        };
        var unsubscribe = $rootScope.$on('labelbuilderApp:constantUpdate', function(event, result) {
            $scope.constant = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
