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

				.when('/runInformationDelete', {
					templateUrl: 'confirmationDialog.htm',
					controller: 'RunInformationDeleteConfirmationController'
				})

				.otherwise({redirectTo: '/'});
	}]);

app.controller('DashboardController', function ($scope, $http) {
	$http.get('/api/dashboard/currentActiveRun')
			.then(function (res) {
				$scope.currentActiveRun = res.data;
			});
	$http.get('/api/runInformation/all')
			.then(function (res) {
				$scope.runInformations = res.data;
			});

	$scope.setNextTemplateRun = function () {
		if ($scope.nextTemplateRun) {
			$http.post('/api/dashboard/currentActiveRun/' + $scope.nextTemplateRun.id)
					.then(function (res) {
						$scope.currentActiveRun = res.data;
					});
		} else {
			console.log('FAIL')
		}
	};

	$scope.getRunInformationString = function (info) {
		return info.game + ' (' + info.category + ')';
	}
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
app.controller('RunInformationDeleteConfirmationController', function ($scope, $http, $location) {
	var runInformationId = $location.search().id;
	$scope.header = 'Run Löschen';

	$http.get('/api/runInformation/id/' + runInformationId)
			.then(function (res) {
				$scope.runInformation = res.data;
				$scope.message = 'Den Run zu ' + $scope.runInformation.game + ' von '
						+ $scope.runInformation.runnerName + ' wirklich löschen?';
			});

	$scope.confirm = function () {
		$http.get('/api/runInformation/delete/' + runInformationId)
				.then(function (res) {
					$location.path('/runInformationOverview');
				});
	}
});