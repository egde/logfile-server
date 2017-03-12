var module = angular.module("navigation", ["services"]);
module.controller("navigationController", ["$scope", "logFileLocationService", 
                                           function($scope, logFileLocationService) {
	
	logFileLocationService.setFileLocations();
	$scope.$on("logFileLocationUpdateEvent", function(event, data) {
		$scope.logLocations = data;
	});
	
}]);