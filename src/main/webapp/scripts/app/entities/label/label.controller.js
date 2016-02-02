'use strict';

angular.module('labelbuilderApp')
    .controller('LabelController', function ($scope, $state, Label) {

        var vm = angular.extend(this, {
            greeting: 'Howdy!',
            gridOptions: {
                columnDefs: [ //https://github.com/angular-ui/ui-grid/wiki/Defining-columns
                    {
                        displayName: 'Item Code',
                        field: 'productId'
                    }, {
                        displayName: 'Description',
                        field: 'productName',
                        width: 200,
                        minWidth: 100
                    }, {
                        displayName: 'Brand',
                        field: 'brandCode'
                    }, {
                        displayName: 'Version',
                        field: 'versionMajor',
                        width: 75
                    }, {
                        displayName: 'Status',
                        field: 'status'
                    }, {
                        displayName: 'Owner',
                        field: 'owner'
                    }, {
                        displayName: 'Modified',
                        field: 'lastModified'
                    }, {
                        displayName: 'By',
                        field: 'modifier'
                    }, {
                        displayName: 'Launch',
                        field: 'launched'
                    }
                ],
                data: [],
                enableFiltering: true,
                enableRowSelection: true,
                enableRowHeaderSelection: false,
                multiSelect: false,
                noUnselect: true,
                onRegisterApi: function(gridApi) {
                    vm.gridApi = gridApi;
                    gridApi.selection.on.rowSelectionChanged(null, function(row) {
                        console.log(row.entity);
                        $state.go('label.detail', {id: row.entity.id});
                    });
                }
            },
            labels: [],
            loadAll: function() {
                Label.query(function(result) {
                    vm.gridOptions.data = result;
                })
            },
            refresh: function() {
                vm.loadAll();

            }
        });

        vm.loadAll();
    });
