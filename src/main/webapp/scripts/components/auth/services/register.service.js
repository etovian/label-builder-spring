'use strict';

angular.module('labelbuilderApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


