'use strict';

App.factory('UserService', ['$http', '$q', '$document', function ($http, $q, $document) {

        self.csrfHeaderName = $document[0].querySelector("meta[name='_csrf_header']").getAttribute('content');
        self.csrf = $document[0].querySelector("meta[name='_csrf']").getAttribute('content');
        self.headers = {};
        self.headers[self.csrfHeaderName] = self.csrf;
        self.headers["Content-Type"] = 'application/x-www-form-urlencoded';

        return {
            fetchAllUsers: function () {
                return $http.get('/accountmanager/users')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching users');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            createUser: function (user) {
                return $http.post('/accountmanager/users/item',
                        'login=' + user.login +
                        '&shortName=' + user.name +
                        '&commonName=' + user.name + ' ' + user.lastname +
                        '&organizationName=' + user.orgname +
                        '&role=' + user.role +
                        '&password=' + user.password +
                        '&isActive=' + user.active, {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating user');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            updateUser: function (user) {
                return $http.put('/accountmanager/users/item',
                        'login=' + user.login +
                        '&shortName=' + user.name +
                        '&commonName=' + user.name + ' ' + user.lastname +
                        '&organizationName=' + user.orgname +
                        '&role=' + user.role +
                        '&isActive=' + user.active, {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating user');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            changeUserPassword: function (userId, password) {
                return $http.put('/accountmanager/users/pass',
                        'login=' + userId +
                        '&password=' + password, {headers: self.headers})
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating user');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteUser: function (userId) {
                return $http({method: 'DELETE',
                    url: '/accountmanager/users/item/',
                    params: {"login": userId},
                    headers: self.headers
                })
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while deleting user');
                                    return $q.reject(errResponse);
                                }
                        );
            }

        };

    }]);