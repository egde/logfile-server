angular.module("services").factory(
		"logFileLocationService",
		["$http", "$scope",
		function($http, $scope) {
			var fileLocations = [];
			var error;
			
			return {
				getFileLocations: function() {
					return fileLocations;
				},
				setFileLocations : function() {
					$http.get("/api/logFileLocations").then(
						function(response) {
							fileLocations = response.data;
							$scope.apply();
						},
						function(myError) {
							error = myError;
						}
					);
				},
				insertLogFileLocation : function(logFileLocation) {
					$http.post("/api/logFileLocations", logFileLocation).then(
						function(response) {
							console.log("Added with Hash:"
									+ response.data.hash)
						}, 
						function(myError) {
							console.log("An Error occured " + myError);
							error = myError;
						}
					);
				}
			}	
		}
		]
	);
