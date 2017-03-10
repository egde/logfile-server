var module = angular.module("navigation", ["services"]);
module.controller("navigationController", ["$scope", "logFileLocationService", 
                                           function($scope, logFileLocationService) {
	
	logFileLocationService.setFileLocations();
	$scope.logLocations = logFileLocationService.getFileLocations();
	
}]);