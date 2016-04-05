'use strict';

App.controller('PeriodController', ['$scope', 'PeriodService',
    function ($scope, PeriodService) {
        var self = this;
        self.period = {id: null, name: '', startday: null, endday: null};
        self.currentperiod = {id: null, name: '', startday: null, endday: null};
        self.periods = [];

        self.fetchAllPeriods = function () {
            PeriodService.fetchAllPeriods()
                    .then(
                            function (d) {
                                self.periods = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Periods');
                            }
                    );
        };

        self.fetchAllPeriods();

        self.createPeriod = function (period) {
            PeriodService.createPeriod(period)
                    .then(
                            self.fetchAllPeriods,
                            function (errResponse) {
                                console.error('Error while creating Period.');
                            }
                    );
        };

        self.updatePeriod = function (period) {
            PeriodService.updatePeriod(period)
                    .then(
                            self.fetchAllPeriods,
                            function (errResponse) {
                                console.error('Error while updating Period.');
                            }
                    );
        };

        self.deletePeriod = function (period) {
            PeriodService.deletePeriod(period)
                    .then(
                            self.fetchAllPeriods,
                            function (errResponse) {
                                console.error('Error while deleting Period.');
                            }
                    );
        };

        self.submit = function () {
            if (self.period.id === null) {
                console.log('Saving New Period', self.period);
                self.createPeriod(self.period);
            } else {
                self.updatePeriod(self.period);
                console.log('Period updated with id ', self.period.id);
                console.log('Period updated to name ', self.period.name);
            }
            self.reset();
        };

        self.edit = function (period) {
            console.log('Period name to be edited', period.name);
            self.period.id = period.id;
            self.period.name = period.name;
            self.period.startday = new Date(period.startday);
            self.period.endday = new Date(period.endday);
            $scope.myForm.$setDirty();
        };

        self.reset = function () {
            self.period = {id: null, name: '', startday: null, endday: null};
            $scope.myForm.$setPristine(); //reset Form
        };

    }]);