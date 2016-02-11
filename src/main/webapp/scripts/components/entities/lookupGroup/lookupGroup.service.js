'use strict';

angular.module('labelbuilderApp')
    .factory('LookupGroup', function ($resource, DateUtils) {
        return $resource('api/lookupGroups/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'map': {
                url: 'api/lookupGroups/map'
            },
            'update': { method:'PUT' }
        });
    });
