(function () {

    'use strict';

    function CanvasToolbarDirectiveController ($aside, Label) {
        var templatePath = 'scripts/components/nutracorp/directives/canvas/';
        var vm = this;
        angular.extend(vm, {

            getMailLink: function () {
                var subjectParam = '';
                if (vm.nutraLabel) {
                    subjectParam = 'Subject=Label%20' + vm.nutraLabel.LabelId;
                }
                //TODO: make this email link dynamic
                return 'mailto:mike.green@stgconsulting.com?' + subjectParam;
            },
            openEditInformationModal: function () {
                var opts = {
                    templateUrl: templatePath + 'edit.information.html',
                    controller: 'EditInformationController',
                    controllerAs: 'vm',
                    placement: 'right',
                    size: 'sm',
                    resolve: {
                        labelEntity: function() {
                            return vm.nutraLabel;
                        }
                    }
                };

                $aside.open(opts);
            },
            openFontColorsModal: function () {
                var opts = {
                    templateUrl: templatePath + 'font.colors.html',
                    controller: 'FontColorsController',
                    controllerAs: 'vm',
                    placement: 'right',
                    size: 'md'
                };

                $aside.open(opts);
            },
            openFontSizesModal: function () {
                var opts = {
                    templateUrl: templatePath + 'font.sizes.html',
                    controller: 'FontSizesController',
                    controllerAs: 'vm',
                    placement: 'right',
                    size: 'md'
                };

                $aside.open(opts);
            },
            update: function() {
                Label.update(vm.nutraLabel);
            },
            toggleRotation: function () {
                if (vm.goRenderedLabel.angle === 0) {
                    vm.goRenderedLabel.angle = 270;
                } else {
                    vm.goRenderedLabel.angle = 0;
                }
            }
        });
    }

    function CanvasToolbarDirective () {

        var controller = [
            '$aside',
            'Label',
            CanvasToolbarDirectiveController
        ];

        return {
            scope: {
                nutraLabel: '=',
                goRenderedLabel: '=',
                goDiagram: '='
            },
            templateUrl: 'scripts/components/nutracorp/directives/canvas/canvas.toolbar.html',
            controller: controller,
            controllerAs: 'vm',
            bindToController: true,
            replace: true
        };
    }

    angular.module('nutracorp.directives').directive('canvasToolbar', [CanvasToolbarDirective]);

}());
