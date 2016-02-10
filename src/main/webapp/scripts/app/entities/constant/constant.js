'use strict';

angular.module('labelbuilderApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('constant', {
                parent: 'entity',
                url: '/constants',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Constants'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/constant/constants.html',
                        controller: 'ConstantController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                }
            })
            .state('constant.detail', {
                parent: 'entity',
                url: '/constant/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Constant'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/constant/constant-detail.html',
                        controller: 'ConstantDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Constant', function($stateParams, Constant) {
                        return Constant.get({id : $stateParams.id});
                    }]
                }
            })
            .state('constant.new', {
                parent: 'constant',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/constant/constant-dialog.html',
                        controller: 'ConstantDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    constant_name: null,
                                    constant_value: null,
                                    description: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('constant', null, { reload: true });
                    }, function() {
                        $state.go('constant');
                    })
                }]
            })
            .state('constant.edit', {
                parent: 'constant',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/constant/constant-dialog.html',
                        controller: 'ConstantDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Constant', function(Constant) {
                                return Constant.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('constant', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('constant.delete', {
                parent: 'constant',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/constant/constant-delete-dialog.html',
                        controller: 'ConstantDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Constant', function(Constant) {
                                return Constant.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('constant', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
