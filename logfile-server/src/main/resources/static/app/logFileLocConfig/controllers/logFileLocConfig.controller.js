var module = angular.module("logFileLocConfig", ["services"]);
module.controller("logFileLocConfigController", ["$scope", "logFileLocationService", function($scope, logFileLocationService){
			
	$scope.add = function() {
		console.log("Adding "+$scope.addLocationModal.logLocation);
		
		var logLocation = {path: $scope.addLocationModal.logLocation, hash:""};
		logFileLocationService.insertLogFileLocation(logLocation);
		$scope.addLocationModal.logLocation = "";
		logFileLocationService.setFileLocations();
	};
	
	$scope.remove = function(i) {
		var logLocation =  $scope.logLocations[i];
		console.log("Removing "+logLocation.path);
	};

	$scope.addLocationModal = {logLocation : ""};
	$scope.logLocations = logFileLocationService.getFileLocations();
	logFileLocationService.setFileLocations();
	$scope.$on("logFileLocationUpdateEvent", function(event, data) {
		$scope.logLocations = data;
	});
}]);