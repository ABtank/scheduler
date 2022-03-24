angular.module('app', ['ngRoute', 'ngStorage']).controller('studentsController', function ($scope, $http, $localStorage) {
    $scope.buttonStudentSchedule = true;
    $scope.buttonLinkFromTeacher = false;

    $scope.showStudentSchedule = function () {
        $http({
            url: 'http://localhost:8189/sh/api/v1/students/lessons',
            method: 'GET',
            params: {
                user: $localStorage.summerUser
            }
        }).then(function (response) {
            $scope.buttonStudentSchedule = true;
            $scope.buttonLinkFromTeacher = false;
            console.log(response);
            $scope.studentSchedule = response.data;
        });
    };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/sh/api/v1/auth/login', $scope.user)
            .then(function (response) {
                console.log(response);
                if (response) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.accessToken;
                    $localStorage.summerUser = {email: $scope.user.email,
                                                token: response.data.accessToken};

                    if ($scope.user.email) {
                        $scope.user.email = null;
                    }
                    if ($scope.user.password) {
                        $scope.user.password = null;
                    }
                    $scope.showStudentSchedule();
                }
            }, function errorCallback(response) {
        });
    };

    $scope.followLinkFromTeacher = function () {
        $http({
            url: $scope.linkFromTeacher,    //    http://localhost:8189/sh/api/v1/students/lessons/2
            method: 'GET'
        }).then(function (response) {
            $scope.lesson = null;
            $scope.buttonStudentSchedule = false;
            $scope.buttonLinkFromTeacher = true;
                console.log(response);
            $scope.lesson = response.data;
        });
    };

    $scope.reserveLecture = function (lessonsId) {
        $http({
            url: 'http://localhost:8189/sh/api/v1/students/lessons/' + lessonsId,
            method: 'POST',
            params: {
                user: $localStorage.summerUser
            }
        }).then(function (response) {
            console.log(response);
            $scope.showStudentSchedule();
        });
    };



});