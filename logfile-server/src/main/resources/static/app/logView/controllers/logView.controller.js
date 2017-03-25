var module = angular.module("logView", ["services"]);

module.controller("logViewController", ["$scope", "$routeParams", "logService", 
                                        function($scope, $routeParams, logService) {
	this.stompCLient = null;
	
	function showLine(logFileEntry) {
		if ($scope.logContent) {
			$scope.logContent = $scope.logContent +"\n"+ logFileEntry.logLine;
		} else {
			$scope.logContent = logFileEntry.logLine;
		}
		$scope.$apply();
		$("#logFileContent").scrollTop($("#logFileContent")[0].scrollHeight);
	};
	
	this.connect = function() {
	    var socket = new SockJS('/api/ws');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	    	var hash = $routeParams.logHash;
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/streamLogFile/'+hash, function (logFileEntry) {
	            showLine(JSON.parse(logFileEntry.body));
	        });
	        stompClient.send("/api/ws/startStreaming", {}, JSON.stringify(
	        		{
	        			'hash': hash,
	        			'stompSessionId' : socket._generateSessionId()
	        		}
	        	)
	        );
        });
	};
	
	this.connect();
	
	console.log("Reading from logfile with hash: "+$routeParams.logHash)
}]);