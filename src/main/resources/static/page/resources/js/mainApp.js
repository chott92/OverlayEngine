var app = angular.module('overlayEngineApp', ['ngRoute']);
app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'dashboard.htm',
                controller: 'DashboardController'
            })

            .when('/runInformationOverview', {
                templateUrl: 'runInformationOverview.htm',
                controller: 'RunInformationOverviewController'
            })

            .otherwise({redirectTo: '/'});
    }]);

app.controller('DashboardController', function ($scope) {

});

app.controller('RunInformationOverviewController', function ($scope, $http) {
    $scope.msg = 'Loading data...';
    $http.get('/api/runInformation/all')
        .then(function (res) {
            $scope.runInformations = res.data;
            $scope.msg = '';
        });
});