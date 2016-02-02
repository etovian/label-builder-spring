(function() {

	'use strict';

	var deps = [
		'$uibModal',
		FontColorsController
	];
	angular.module('nutracorp.directives').controller('FontColorsController', deps);

	function FontColorsController($uibModal) {

		var vm = this;
		angular.extend(vm, {
			getFontColors: function() {
                return null; //TODO: wire up a service for this
			}
		});
	}

})();
