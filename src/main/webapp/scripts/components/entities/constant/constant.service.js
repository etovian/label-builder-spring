'use strict';

angular.module('labelbuilderApp')
    .factory('Constant', function ($resource, DateUtils) {
        return $resource('api/constants/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            map: {
                url: 'api/constants/map'
            },
            'update': { method:'PUT' }
        });
    });
