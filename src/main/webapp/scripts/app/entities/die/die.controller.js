'use strict';

angular.module('labelbuilderApp')
    .controller('DieController', function ($scope, $state, Die) {

        $scope.dies = [];
        $scope.loadAll = function() {
            Die.query(function(result) {
               $scope.dies = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.die = {
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
        };
    });
