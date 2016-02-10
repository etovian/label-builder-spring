'use strict';

angular.module('labelbuilderApp')
    .controller('ConstantController', function ($scope, $state, Constant) {

        var vm = angular.extend(this, {
            constant: {
                constant_name: null,
                constant_value: null,
                description: null
            },
            constants: [],
            clear: function() {
                vm.constant = {
                    constant_name: null,
                    constant_value: null,
                    description: null
                };
            },
            loadAll: function() {
                Constant.query(function(result) {
                    vm.constants = result;
                });
            },
            refresh: function() {
                vm.loadAll();
                vm.clear();
            }
        });

        vm.loadAll();
        //$scope.constants = [];
        //$scope.loadAll = function() {
        //    Constant.query(function(result) {
        //       $scope.constants = result;
        //    });
        //};
        //$scope.loadAll();
        //
        //
        //$scope.refresh = function () {
        //    $scope.loadAll();
        //    $scope.clear();
        //};
        //
        //$scope.clear = function () {
        //    $scope.constant = {
        //        constant_name: null,
        //        constant_value: null,
        //        description: null,
        //        id: null
        //    };
        //};
    });
