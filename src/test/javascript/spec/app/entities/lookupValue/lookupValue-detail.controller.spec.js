'use strict';

describe('Controller Tests', function() {

    describe('LookupValue Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockLookupValue, MockLookupGroup;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockLookupValue = jasmine.createSpy('MockLookupValue');
            MockLookupGroup = jasmine.createSpy('MockLookupGroup');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'LookupValue': MockLookupValue,
                'LookupGroup': MockLookupGroup
            };
            createController = function() {
                $injector.get('$controller')("LookupValueDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'labelbuilderApp:lookupValueUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
