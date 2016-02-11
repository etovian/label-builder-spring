'use strict';

angular.module('labelbuilderApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('lookupGroup', {
                parent: 'entity',
                url: '/lookupGroups',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'LookupGroups'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/lookupGroup/lookupGroups.html',
                        controller: 'LookupGroupController'
                    }
                },
                resolve: {
                }
            })
            .state('lookupGroup.detail', {
                parent: 'entity',
                url: '/lookupGroup/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'LookupGroup'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/lookupGroup/lookupGroup-detail.html',
                        controller: 'LookupGroupDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'LookupGroup', function($stateParams, LookupGroup) {
                        return LookupGroup.get({id : $stateParams.id});
                    }]
                }
            })
            .state('lookupGroup.new', {
                parent: 'lookupGroup',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/lookupGroup/lookupGroup-dialog.html',
                        controller: 'LookupGroupDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    groupName: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('lookupGroup', null, { reload: true });
                    }, function() {
                        $state.go('lookupGroup');
                    })
                }]
            })
            .state('lookupGroup.edit', {
                parent: 'lookupGroup',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/lookupGroup/lookupGroup-dialog.html',
                        controller: 'LookupGroupDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['LookupGroup', function(LookupGroup) {
                                return LookupGroup.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('lookupGroup', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('lookupGroup.delete', {
                parent: 'lookupGroup',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/lookupGroup/lookupGroup-delete-dialog.html',
                        controller: 'LookupGroupDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['LookupGroup', function(LookupGroup) {
                                return LookupGroup.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('lookupGroup', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
