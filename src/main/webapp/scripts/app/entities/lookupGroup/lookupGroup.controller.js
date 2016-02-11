'use strict';

angular.module('labelbuilderApp')
    .controller('LookupGroupController', function ($scope, $state, LookupGroup) {

        $scope.lookupGroups = [];
        $scope.loadAll = function() {
            LookupGroup.query(function(result) {
               $scope.lookupGroups = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.lookupGroup = {
                groupName: null,
                id: null
            };
        };
    });
