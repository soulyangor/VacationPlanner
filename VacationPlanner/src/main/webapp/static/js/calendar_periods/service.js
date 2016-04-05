'use strict';

App.factory('PeriodService', ['$http', '$q', function ($http, $q) {

        self.headers = {};
        self.headers["Content-Type"] = 'application/json';

        return {
            fetchAllPeriods: function () {
                return $http.get('http://localhost:8085/VacationPlanner/calendar_periods')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching periods');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            createPeriod: function (period) {
                return $http.post('http://localhost:8085/VacationPlanner/calendar_periods/item',
                        JSON.stringify(period))
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating period');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            updatePeriod: function (period) {
                return $http.put('http://localhost:8085/VacationPlanner/calendar_periods/item',
                        JSON.stringify(period))
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating period');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deletePeriod: function (period) {
                return $http({method: 'DELETE',
                    url: '/VacationPlanner/calendar_periods/item/',
                    data: JSON.stringify(period),
                    headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while deleting period');
                                    return $q.reject(errResponse);
                                }
                        );
            }

        };
    }]);


