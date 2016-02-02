(function() {

	'use strict';

	var deps = ['$uibModalInstance', FontSizesController];
	angular.module('nutracorp.directives').controller('FontSizesController', deps);

	function FontSizesController($uibModalInstance) {
		var vm = this;
		angular.extend(vm, {
			getFontSizes: function() {
				return null; //TODO: wire up a service for this
			}
		});
	}

})();
