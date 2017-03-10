var module = angular.module("logView", ["services"]);

module.controller("logViewController", ["$scope", "$routeParams", "logService", 
                                        function($scope, $routeParams, logService) {
	$scope.logContent = logService.retrieve($routeParams.logHash);
	
	
	console.log("Reading from logfile with hash: "+$routeParams.logHash)
}]);