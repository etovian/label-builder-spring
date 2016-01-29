'use strict';

angular.module('labelbuilderApp')
    .controller('LabelController', function ($scope, $state, Label) {

        $scope.labels = [];
        $scope.loadAll = function() {
            Label.query(function(result) {
               $scope.labels = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.label = {
                productId: null,
                versionMajor: null,
                versionMinor: null,
                created: null,
                discussion: null,
                productName: null,
                contentCount: null,
                servingSize: null,
                deliveryForm: null,
                dosageConsistency: null,
                genericDescription: null,
                upc: null,
                warning: null,
                directions: null,
                refrigerated: null,
                isPeelOff: null,
                id: null
            };
        };
    });
