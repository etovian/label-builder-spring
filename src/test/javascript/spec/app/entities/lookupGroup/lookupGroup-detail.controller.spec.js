'use strict';

describe('Controller Tests', function() {

    describe('LookupGroup Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockLookupGroup, MockLookupValue;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockLookupGroup = jasmine.createSpy('MockLookupGroup');
            MockLookupValue = jasmine.createSpy('MockLookupValue');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'LookupGroup': MockLookupGroup,
                'LookupValue': MockLookupValue
            };
            createController = function() {
                $injector.get('$controller')("LookupGroupDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'labelbuilderApp:lookupGroupUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
