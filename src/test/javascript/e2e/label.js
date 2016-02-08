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
            element.all(by.id('confirmation-modal-confirm')).first().click();
        }

        function filterByProductId(productId) {
            clearFilters();
            getGridFilters().first().sendKeys(productId);
        }

        function getGridFilters() {
            var gridFilters = element.all(by.css('.ui-grid-filter-input'));
            return gridFilters;
        }

        function getFirstLabelProductId() {
            var firstProductIdCell = element.all(by.css('.ui-grid-row .ui-grid-cell .ui-grid-cell-contents')).first();
            var productId = firstProductIdCell.getText();
            return productId;
        }

        function hasClass(element, cls) {
            return element.getAttribute('class').then(function (classes) {
                return classes.split(' ').indexOf(cls) !== -1;
            });
        };

        it("should load labels page", function() {
            expect(element.all(by.css('h1')).first().getText()).toMatch(/Create or open a label:/);
        });

        it("should navigate to a label by item code (productId)", function() {
            var productId = getFirstLabelProductId();
            element(by.model('vm.productId')).sendKeys(productId);
            element(by.id('btn-create-or-open')).click();
            element(by.id('confirmation-modal-confirm')).click();
            expect(browser.getLocationAbsUrl()).toMatch(/label/);
            //browser.get('/#/labels');
        });

        it("should filter grid by item code (productId)", function() {
            var firstProductIdCell = element.all(by.css('.ui-grid-row .ui-grid-cell .ui-grid-cell-contents')).first();
            var productId = firstProductIdCell.getText();
            filterByProductId(productId);
            var gridRows = element.all(by.css('.ui-grid-row'));
            expect(gridRows.count()).toBe(1);
        });

        it("should filter grid by description (productName)", function() {
            var searchTerm = 'phyto';
            var gridFilters = getGridFilters();
            var descriptionFilter = getGridFilters().get(1);
            descriptionFilter.sendKeys(searchTerm);
            var descriptionCellIndex = 1;
            element.all(by.css('.ui-grid-row')).then(function(rows) {
                rows.forEach(function(row) {
                    row.all(by.css('.ui-grid-cell-contents')).then(function(cells) {
                        cells[descriptionCellIndex].getText().then(function(text) {
                            var containsSearchTerm = text.toLowerCase().indexOf(searchTerm.toLowerCase()) != -1;
                            expect(containsSearchTerm).toBe(true);
                        });
                    })
                });
            });

        });

        it("should navigate to a label selected in the grid", function() {
            var firstDescriptionCell = element.all(by.css('.ui-grid-row .ui-grid-cell .ui-grid-cell-contents')).get(1);
            var description = firstDescriptionCell.getText();
            firstDescriptionCell.click();
            element(by.id('open-label')).click();
            var h2 = element.all(by.css('h2')).first();
            expect(h2.getText()).toEqual(description);
        });

        it("should delete a label", function() {
            addTestLabel();
            browser.get('/#/labels');
            deleteTestLabel();
            var rowCount = element.all(by.css('.ui-grid-row')).count();
            expect(rowCount).toEqual(0);
        });

        it("should abort deleting a label", function() {
            var startingRowCount = element.all(by.css('.ui-grid-row')).count();
            var firstProductIdCell = element.all(by.css('.ui-grid-row .ui-grid-cell .ui-grid-cell-contents')).first();
            firstProductIdCell.click();
            element(by.id('confirm-delete-label')).click();
            element(by.id('confirmation-modal-cancel')).click();
            var endingRowCount = element.all(by.css('.ui-grid-row')).count();
            expect(startingRowCount).toEqual(endingRowCount);
        });

        it("should fail to create or open a label without a productId value", function() {
            var productIdInput = element(by.model('vm.productId'));
            productIdInput.sendKeys('ABCD');
            expect(hasClass(productIdInput, 'ng-invalid')).toBe(false);
            productIdInput.clear();
            element(by.id('btn-create-or-open')).click();
            expect(hasClass(productIdInput, 'ng-invalid')).toBe(true);
        });
    });
}());
