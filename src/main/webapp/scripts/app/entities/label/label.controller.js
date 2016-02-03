'use strict';

angular.module('labelbuilderApp')
    .controller('LabelController', function ($scope, $state, Label) {

        var vm = angular.extend(this, {
            deleteSelectedLabel: function() {
                Label.delete({id: vm.selectedLabelSummary.id})
                    .$promise.then(vm.search);

            },
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
                        //$state.go('label.detail', {id: row.entity.id});
                        vm.selectedLabelSummary = row.entity;
                    });
                }
            },
            labels: [],
            navigateToSelectedLabel: function() {
                $state.go('label.detail', {id: vm.selectedLabelSummary.id});
            },
            search: function() {
                Label.search(vm.searchCriteria, function(result) {
                    vm.gridOptions.data = result;
                })
            },
            refresh: function() {
                vm.search();

            },
            searchCriteria: {
                type: "active",
                approved: false
            },
            selectedLabelSummary: null
        });

        vm.search();
    });
