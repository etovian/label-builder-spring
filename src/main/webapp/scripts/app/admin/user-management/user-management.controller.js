'use strict';

angular.module('labelbuilderApp')
    .controller('UserManagementController', function ($scope, Principal, User, ParseLinks) {
        $scope.users = [];
        $scope.authorities = ["ROLE_USER", "ROLE_ADMIN"];

		Principal.identity().then(function(account) {
            $scope.currentAccount = account;
        });
        $scope.page = 1;
        $scope.search = function () {
            User.query({page: $scope.page - 1, size: 20}, function (result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.totalItems = headers('X-Total-Count');
                $scope.users = result;
            });
        };

        $scope.loadPage = function (page) {
            $scope.page = page;
            $scope.search();
        };
        $scope.search();

        $scope.setActive = function (user, isActivated) {
            user.activated = isActivated;
            User.update(user, function () {
                $scope.search();
                $scope.clear();
            });
        };

        $scope.clear = function () {
            $scope.user = {
                id: null, login: null, firstName: null, lastName: null, email: null,
                activated: null, langKey: null, createdBy: null, createdDate: null,
                lastModifiedBy: null, lastModifiedDate: null, resetDate: null,
                resetKey: null, authorities: null
            };
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
