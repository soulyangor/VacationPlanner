'use strict';

App.factory('OrganizationService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/x-www-form-urlencoded';

        return {
            fetchAllOrganizations: function () {
                return $http.get('/accountmanager/organizations')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching organizations');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            createOrganization: function (organization) {
                return $http.post('/accountmanager/organizations/item',
                        'name=' + organization.name, {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating organization');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            updateOrganization: function (organization) {
                return $http.put('/accountmanager/organizations/item',
                        "oldName=" + organization.id + "&newName=" + organization.name,
                        {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating organization');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteOrganization: function (orgName) {
                return $http({method: 'DELETE',
                    url: '/accountmanager/organizations/item/',
                    params: {"name": orgName},
                    headers: self.headers
                })
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while deleting organization');
                                    return $q.reject(errResponse);
                                }
                        );
            }

        };

    }]);