(function() {

    'use strict';

    describe("Label Search Page", function() {

        beforeAll(function () {
            browser.get('/');
            browser.driver.wait(protractor.until.elementIsVisible(element(by.css('h1'))));

            element(by.id('account-menu')).click();
            element(by.css('[ui-sref="login"]')).click();

            element(by.model('username')).sendKeys('admin');
            element(by.model('password')).sendKeys('admin');
            element(by.css('button[type=submit]')).click();
        });

        beforeEach(function() {
            clearFilters();
            element(by.id('entity-menu')).click();
            element(by.id('label-entity-menu')).click();
        });

        var testLabel = {
            productId: '8765309', //aka item code
            productName: 'Nutraceutical\'s finest', //aka description
            versionMajor: '1',
            versionMinor: '0'
        };

        function addTestLabel() {
            element(by.model('vm.productId')).sendKeys(testLabel.productId);
            element(by.id('btn-create-or-open')).click();
            element(by.id('confirmation-modal-confirm')).click();
        }

        function clearFilters() {
            getGridFilters().each(function(filter) {
                filter.clear();
            });
        }

        function deleteTestLabel() {
            filterByProductId(testLabel.productId);
            element.all(by.css('.ui-grid-row .ui-grid-cell')).first().click();
            element(by.id('confirm-delete-label')).click();
            element(by.id('confirmation-modal-confirm')).click();
        }

        function filterByProductId(productId) {
            clearFilters();
            getGridFilters().first().sendKeys(productId);
        }

        function getGridFilters() {
            var gridFilters = element.all(by.css('.ui-grid-filter-input'));
            return gridFilters;
        }

        it("should load labels page", function() {
            expect(element.all(by.css('h1')).first().getText()).toMatch(/Create or open a label:/);
        });

        it("should navigate to a label by item code (productId)", function() {
            addTestLabel();
            browser.get('/#/labels');
            filterByProductId(testLabel.productId);
            var gridRows = element.all(by.css('.ui-grid-row'));
            expect(gridRows.count()).toBe(1);
            deleteTestLabel();
            //TODO: first, add a label. filter by the new label.productId, then delete the label
        });

        it("should create a label for a new item code (productId)", function() {
            //TODO: be sure to delete the newly-added label after this test
        });

        it("should filter grid by item code (productId)", function() {
            //TODO: first, add a label. filter by the new label.productId, then delete the label
        });

        it("should filter grid by description (productName)", function() {
            //TODO: first, add a label. filter by the new label.productId, then delete the label
        });

        it("should navigate to a label selected in the grid", function() {
            //TODO: first, add a label. filter by the new label.productId, then delete the label
        });

        it("should delete a label", function() {
            //TODO: first, add a label, then delete the label
        });

        it("should display active labels only", function() {

        });

        it("should display deleted labels only", function() {

        });

        it("should display approved labels", function() {

        });
    });
}());
