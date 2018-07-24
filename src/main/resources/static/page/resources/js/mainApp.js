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

				.when('/runInformation', {
					templateUrl: 'runInformation.htm',
					controller: 'RunInformationController'
				})

				.when('/runInformationEdit', {
					templateUrl: 'runInformation.htm',
					controller: 'RunInformationEditController'
				})

				.otherwise({redirectTo: '/'});
	}]);

app.controller('DashboardController', function ($scope) {

});

app.controller('RunInformationOverviewController', function ($scope, $http, $location) {
	$scope.msg = 'Loading data...';
	$scope.test = 'hello world';
	$http.get('/api/runInformation/all')
			.then(function (res) {
				$scope.runInformations = res.data;
				$scope.msg = '';
			});
	$scope.editInformation = function (informationId) {
		console.log(informationId)
		$location.path('/runInformationEdit?id=' + informationId)
	}
});

app.controller('RunInformationController', function ($scope, $http, $location) {
	$scope.runInformationMode = 'Create';
	$scope.runInformation = {};
	$scope.saveInformation = function () {
		$http.post('/api/runInformation/save', $scope.runInformation)
				.then(function (res) {
					$location.path('/runInformationOverview');
				});
	};
});

app.controller('RunInformationEditController', function ($scope, $http, $location) {
	$scope.runInformationMode = 'Create';
	var runInformationId = $location.search().id;

	$http.get('/api/runInformation/id/' + runInformationId)
			.then(function (res) {
				$scope.runInformation = res.data;
				console.log($scope.runInformation);
			});

	$scope.saveInformation = function () {
		$http.post('/api/runInformation/save', $scope.runInformation)
				.then(function (res) {
					$location.path('/runInformationOverview');
				});
	};
});