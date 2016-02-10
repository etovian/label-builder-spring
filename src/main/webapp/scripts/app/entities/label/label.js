'use strict';

angular.module('labelbuilderApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('label', {
                parent: 'entity',
                url: '/labels',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Labels'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/label/labels.html',
                        controller: 'LabelController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                }
            })
            .state('label.detail', {
                parent: 'entity',
                url: '/label/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Label'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/label/label-detail.html',
                        controller: 'LabelDetailController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Label', function($stateParams, Label) {
                        return Label.get({id : $stateParams.id});
                    }],
                    constants: ['Constant', function(Constant) {
                        return Constant.query();
                    }]
                }
            })
            .state('label.new', {
                parent: 'label',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/label/label-dialog.html',
                        controller: 'LabelDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    productId: null,
                                    versionMajor: null,
                                    versionMinor: null,
                                    created: null,
                                    discussion: null,
                                    productName: null,
                                    contentCount: null,
                                    servingSize: null,
                                    deliveryForm: null,
                                    dosageConsistency: null,
                                    genericDescription: null,
                                    upc: null,
                                    warning: null,
                                    directions: null,
                                    refrigerated: null,
                                    isPeelOff: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('label', null, { reload: true });
                    }, function() {
                        $state.go('label');
                    })
                }]
            })
            .state('label.edit', {
                parent: 'label',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/label/label-dialog.html',
                        controller: 'LabelDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Label', function(Label) {
                                return Label.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('label', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('label.delete', {
                parent: 'label',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/label/label-delete-dialog.html',
                        controller: 'LabelDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Label', function(Label) {
                                return Label.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('label', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
