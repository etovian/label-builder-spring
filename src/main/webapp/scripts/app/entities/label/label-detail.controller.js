(function() {

}());

'use strict';

angular.module('labelbuilderApp')
    .controller('LabelDetailController', function ($scope, $rootScope, $stateParams, entity, Label) {

        var vm = angular.extend(this, {
            label: entity,
            load: function() {
                Label.get({id: id}, function(result) {
                    vm.label = result;
                });
            }
        });
    });
