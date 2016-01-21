 'use strict';

angular.module('labelbuilderApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-labelbuilderApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-labelbuilderApp-params')});
                }
                return response;
            }
        };
    });
