(function() {

}());

'use strict';

angular.module('labelbuilderApp')
    .controller('LabelDetailController', function ($scope, $rootScope, $stateParams, entity, Label) {

        var vm = angular.extend(this, {
            label: entity
        });

        //TODO: not exactly sure why this is necessary, but removing it breaks a test
        var unsubscribe = $rootScope.$on('labelbuilderApp:labelUpdate', function(event, result) {
            $scope.label = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
