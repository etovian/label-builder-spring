'use strict';

angular.module('labelbuilderApp')
    .controller('LabelDetailController', function ($scope, $rootScope, $stateParams, entity, Label) {
        $scope.label = entity;
        $scope.load = function (id) {
            Label.get({id: id}, function(result) {
                $scope.label = result;
            });
        };
        var unsubscribe = $rootScope.$on('labelbuilderApp:labelUpdate', function(event, result) {
            $scope.label = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
