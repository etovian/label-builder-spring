(function() {

    'use strict';

    function LabelDetailController($scope, $rootScope, $stateParams, entity, Label, constants) {

        var vm = angular.extend(this, {
            label: entity,
            constants: constants
        });

        //TODO: not exactly sure why this is necessary, but removing it breaks a test
        var unsubscribe = $rootScope.$on('labelbuilderApp:labelUpdate', function(event, result) {
            vm.label = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
    var deps = [
        '$scope',
        '$rootScope',
        '$stateParams',
        'entity',
        'Label',
        'constants',
        LabelDetailController
    ];

    angular.module('labelbuilderApp').controller('LabelDetailController', deps);

}());

//angular.module('labelbuilderApp')
//    .controller('LabelDetailController', function ($scope, $rootScope, $stateParams, entity, Label) {
//
//        var vm = angular.extend(this, {
//            label: entity
//        });
//
//        //TODO: not exactly sure why this is necessary, but removing it breaks a test
//        var unsubscribe = $rootScope.$on('labelbuilderApp:labelUpdate', function(event, result) {
//            $scope.label = result;
//        });
//        $scope.$on('$destroy', unsubscribe);
//
//    });
