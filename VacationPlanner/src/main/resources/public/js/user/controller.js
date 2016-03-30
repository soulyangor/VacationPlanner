'use strict';

App.controller('UserController', ['$scope', 'UserService',
    function ($scope, UserService) {
        var self = this;
        self.user = {login: '',
            name: '',
            lastname: '',
            orgname: '',
            role: null,
            password: '',
            confirm: '',
            active: false,
            id: null};
        self.users = [];
        self.curUserId = '';
        self.orderProp = 'login';

        self.currentPage = 0;
        self.pageSize = 2;

        self.setCurrentPage = function (currentPage) {
            self.currentPage = currentPage;
        };

        self.getNumberAsArray = function (num) {
            return new Array(num);
        };

        self.numberOfPages = function () {
            return Math.ceil(self.users.length / self.pageSize);
        };

        self.fetchAllUsers = function () {
            UserService.fetchAllUsers()
                    .then(
                            function (d) {
                                self.users = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Organizations');
                            }
                    );
        };

        self.fetchAllUsers();

        self.createUser = function (user) {
            UserService.createUser(user)
                    .then(
                            self.fetchAllUsers,
                            function (errResponse) {
                                console.error('Error while creating User.');
                            }
                    );
        };

        self.updateUser = function (user) {
            UserService.updateUser(user)
                    .then(
                            self.fetchAllUsers,
                            function (errResponse) {
                                console.error('Error while updating User.');
                            }
                    );
        };

        self.deleteUser = function (userId) {
            UserService.deleteUser(userId)
                    .then(
                            self.fetchAllUsers,
                            function (errResponse) {
                                console.error('Error while deleting User.');
                            }
                    );
        };

        self.changeOrder = function (order) {
            self.orderProp = order;
        };

        self.submit = function () {
            if (self.user.id === null) {
                console.log('Saving New User', self.user);
                self.createUser(self.user);
            } else {
                self.updateUser(self.user);
                console.log('User updated with login ', self.user.id);
                console.log('User updated to parameters ', self.user);
            }
            self.reset();
        };

        self.edit = function (u) {
            console.log('User to be edited', u);
            self.user.id = u.login;
            self.user.login = u.login;
            self.user.orgname = u.organizationName;
            self.user.role = u.roles[0];
            var commonName = u.commonName.split(' ');
            self.user.name = commonName[0];
            self.user.lastname = commonName[1];
            self.user.active = u.isActive;
        };

        self.showConfirmDialogThenDelete = function (userId) {
            self.curUserId = userId;
            $('#dialog-confirm').dialog('open');
        };

        self.showChangePasswordDialog = function (userId) {
            self.curUserId = userId;
            console.log('invopcation of the dialod show for ', self.curUserId);
            $("#changedLogin").val(userId);
            $('#dialog-change').dialog('open');
        };

        self.removeCurUser = function () {
            console.log('user to be deleted', self.curUserId);
            if (self.user.id === self.curUserId) {//clean form if the organization to be deleted is shown there.
                self.reset();
            }
            self.deleteUser(self.curUserId);
        };

        self.changePassword = function (pass) {
            console.log('change password of the user with login ', self.curUserId);
            UserService.changeUserPassword(self.curUserId, pass)
                    .then(
                            self.fetchAllUsers,
                            function (errResponse) {
                                console.error('Error while changing User password.');
                            }
                    );
            console.log('change password to ', pass);
        };

        self.reset = function () {
            self.user = {login: '',
                name: '',
                lastname: '',
                orgname: '',
                role: null,
                password: '',
                confirm: '',
                active: false,
                id: null};
            $scope.myForm.$setPristine(); //reset Form
        };

    }]);

var compareTo = function () {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function (scope, element, attributes, ngModel) {
            ngModel.$validators.compareTo = function (modelValue) {
                return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function () {
                ngModel.$validate();
            });
        }
    };
};

App.directive("compareTo", compareTo);
App.filter('startFrom', function () {
    return function (input, start) {
        return input.slice(start);
    };
});