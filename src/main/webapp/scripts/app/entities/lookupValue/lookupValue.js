'use strict';

angular.module('labelbuilderApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('lookupValue', {
                parent: 'entity',
                url: '/lookupValues',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'LookupValues'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/lookupValue/lookupValues.html',
                        controller: 'LookupValueController'
                    }
                },
                resolve: {
                }
            })
            .state('lookupValue.detail', {
                parent: 'entity',
                url: '/lookupValue/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'LookupValue'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/lookupValue/lookupValue-detail.html',
                        controller: 'LookupValueDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'LookupValue', function($stateParams, LookupValue) {
                        return LookupValue.get({id : $stateParams.id});
                    }]
                }
            })
            .state('lookupValue.new', {
                parent: 'lookupValue',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/lookupValue/lookupValue-dialog.html',
                        controller: 'LookupValueDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    code: null,
                                    displayValue: null,
                                    ordinalValue: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('lookupValue', null, { reload: true });
                    }, function() {
                        $state.go('lookupValue');
                    })
                }]
            })
            .state('lookupValue.edit', {
                parent: 'lookupValue',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/lookupValue/lookupValue-dialog.html',
                        controller: 'LookupValueDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['LookupValue', function(LookupValue) {
                                return LookupValue.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('lookupValue', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('lookupValue.delete', {
                parent: 'lookupValue',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/lookupValue/lookupValue-delete-dialog.html',
                        controller: 'LookupValueDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['LookupValue', function(LookupValue) {
                                return LookupValue.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('lookupValue', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
