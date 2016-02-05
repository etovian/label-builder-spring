(function() {

	'use strict';

	var deps = [
		'$uibModal',
        'labelEntity',
		EditInformationController
	];
	angular.module('nutracorp.directives').controller('EditInformationController', deps);

	function EditInformationController($uibModal, labelEntity) {

		var vm = this;
		angular.extend(vm, {
			getSelectedLabel: function() {
                return null; //TODO: wire up a service for this
			},
            label: labelEntity
		});
	}

})();
