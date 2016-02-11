'use strict';

angular.module('labelbuilderApp')
    .factory('Die', function ($resource, DateUtils) {
        return $resource('api/dies/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.lastModified = DateUtils.convertLocaleDateFromServer(data.lastModified);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.lastModified = DateUtils.convertLocaleDateToServer(data.lastModified);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.lastModified = DateUtils.convertLocaleDateToServer(data.lastModified);
                    return angular.toJson(data);
                }
            }
        });
    });
