(function() {
    'use strict';

    function LabelController($state, ConfirmationService, Label) {

        var vm = angular.extend(this, {
            confirmDeleteSelectedLabel: function() {
                ConfirmationService.getConfirmation('Are you sure you wish to delete this label?')
                    .then(vm.deleteSelectedLabel);
            },
            createOrOpenByProductId: function() {
                console.log('howdy!');
            },
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
                        vm.selectedLabelSummary = row.entity;
                    });
                }
            },
            navigateToSelectedLabel: function() {
                $state.go('label.detail', {id: vm.selectedLabelSummary.id});
            },
            productId: null,
            search: function() {
                Label.search(vm.searchCriteria, vm.setGridData);
            },
            searchCriteria: {
                type: "active",
                approved: false
            },
            selectedLabelSummary: null,
            setGridData: function(data) {
                vm.gridOptions.data = data;
            }
        });

        vm.search();
    }

    var deps = [
        '$state',
        'ConfirmationService',
        'Label',
        LabelController
    ];
    angular.module('labelbuilderApp').controller('LabelController', deps);
}());
