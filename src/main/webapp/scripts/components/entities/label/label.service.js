'use strict';

angular.module('labelbuilderApp')
    .factory('Label', function ($resource, DateUtils) {
        return $resource('api/labels/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'search': {
                method: 'GET',
                isArray: true,
                params: {
                    type: '@type',
                    active: '@active'
                }
            },
            'getByProductId': {
                url: 'api/labels/productId/:productId',
                method: 'GET',
                isArray: false
            },
            'createByProductId': {
                url: 'api/labels/productId/',
                method: 'POST',
                isArray: false
            },
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.created = DateUtils.convertLocaleDateFromServer(data.created);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.created = DateUtils.convertLocaleDateToServer(data.created);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.created = DateUtils.convertLocaleDateToServer(data.created);
                    return angular.toJson(data);
                }
            }
        });
    });
