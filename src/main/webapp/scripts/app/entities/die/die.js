'use strict';

angular.module('labelbuilderApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('die', {
                parent: 'entity',
                url: '/dies',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Dies'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/die/dies.html',
                        controller: 'DieController'
                    }
                },
                resolve: {
                }
            })
            .state('die.detail', {
                parent: 'entity',
                url: '/die/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Die'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/die/die-detail.html',
                        controller: 'DieDetailController'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Die', function($stateParams, Die) {
                        return Die.get({id : $stateParams.id});
                    }]
                }
            })
            .state('die.new', {
                parent: 'die',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/die/die-dialog.html',
                        controller: 'DieDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    brandCode: null,
                                    dieName: null,
                                    overallWidth: null,
                                    overallHeight: null,
                                    bpRotation: null,
                                    fpPanelImageWidth: null,
                                    fpImageHeight: null,
                                    hasBorder: null,
                                    borderWidth: null,
                                    borderTopInset: null,
                                    borderLeftInset: null,
                                    borderRightInset: null,
                                    bpMarginTop: null,
                                    bpMarginLeft: null,
                                    bpMarginRight: null,
                                    supFactAps: null,
                                    supFactDv: null,
                                    nutFactDv: null,
                                    mfgInfoFontSize: null,
                                    useByFontSize: null,
                                    lastModified: null,
                                    fpTopBumpHeight: null,
                                    supFactApsDataInset: null,
                                    mfgInfoDisplayFormat: null,
                                    textAlignment: null,
                                    upcFontSize: null,
                                    hasMiniFactsBox: null,
                                    nutFactsAps: null,
                                    peelOffPanelRotation: null,
                                    peelOffMarginTop: null,
                                    peelOffMarginLeft: null,
                                    peelOffMarginRight: null,
                                    peelOffMarginBottom: null,
                                    allowPeelOff: null,
                                    bpDividerMargin: null,
                                    columnDividerMargin: null,
                                    subNutritionalSeparators: null,
                                    calorieComparisonLowWidth: null,
                                    calorieComparisonHighWidth: null,
                                    calorieComparisonLtWidth: null,
                                    bpMarginBottom: null,
                                    peelOffGlueAllowance: null,
                                    dataOnly: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('die', null, { reload: true });
                    }, function() {
                        $state.go('die');
                    })
                }]
            })
            .state('die.edit', {
                parent: 'die',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/die/die-dialog.html',
                        controller: 'DieDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Die', function(Die) {
                                return Die.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('die', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('die.delete', {
                parent: 'die',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/die/die-delete-dialog.html',
                        controller: 'DieDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Die', function(Die) {
                                return Die.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('die', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
