'use strict';

angular.module('labelbuilderApp')
    .controller('LookupValueController', function ($scope, $state, LookupValue) {

        $scope.lookupValues = [];
        $scope.loadAll = function() {
            LookupValue.query(function(result) {
               $scope.lookupValues = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.lookupValue = {
                code: null,
                displayValue: null,
                ordinalValue: null,
                id: null
            };
        };
    });
