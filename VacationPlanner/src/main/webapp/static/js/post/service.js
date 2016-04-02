'use strict';

App.factory('PostService', ['$http', '$q', function ($http, $q) {

        return {
            fetchAllPosts: function () {
                return $http.get('http://localhost:8085/VacationPlanner/posts')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching posts');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            createPost: function (post) {
                return $http.post('http://localhost:8085/VacationPlanner/posts/item', post)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while creating post');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            updatePost: function (post) {
                return $http.put('http://localhost:8085/VacationPlanner/posts/item', post)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while updating post');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deletePost: function (id) {
                return $http.delete('http://localhost:8085/VacationPlanner/posts/item' + id)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while deleting post');
                                    return $q.reject(errResponse);
                                }
                        );
            }

        };

    }]);


