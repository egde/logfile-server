angular.module("services").factory(
		"logFileLocationService",
		["$http", "$rootScope",
		function($http, $rootScope) {
			var fileLocations = [];
			var error;
			
			function setFileLocations() {
				$http.get("/api/logFileLocations").then(
					function(response) {
						fileLocations = response.data;
						$rootScope.$broadcast("logFileLocationUpdateEvent", fileLocations);
					},
					function(myError) {
						error = myError;
					}
				);
			}
			
			return {
				getFileLocations: function() {
					return fileLocations;
				},
				setFileLocations : setFileLocations,
				insertLogFileLocation : function(logFileLocation) {
					$http.post("/api/logFileLocations", logFileLocation).then(
						function(response) {
							setFileLocations();
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
