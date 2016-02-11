'use strict';

angular.module('labelbuilderApp')
    .factory('LookupValue', function ($resource, DateUtils) {
        return $resource('api/lookupValues/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
