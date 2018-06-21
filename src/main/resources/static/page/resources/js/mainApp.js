var app = angular.module('overlayEngineApp', ['ngRoute']);
app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'dashboard.htm'
            })

            .when('/runInformationOverview', {
                templateUrl: 'runInformationOverview.htm'
            })

            .otherwise({redirectTo: '/'});
    }]);