'use strict';

App.controller('PostController', ['$scope', 'PostService',
    function ($scope, PostService) {
        var self = this;
        self.post = {id: null, name: ''};
        self.posts = [];

        self.fetchAllPosts = function () {
            PostService.fetchAllPosts()
                    .then(
                            function (d) {
                                self.posts = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Posts');
                            }
                    );
        };

        self.fetchAllPosts();

    }]);