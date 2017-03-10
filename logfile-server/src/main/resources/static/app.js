var mainApplicationName="simpleLogFileApp";

var mainApplicationModule = angular.module(mainApplicationName, 
		['ngRoute', 'logFileLocConfig','services', "navigation", "logView"]);

mainApplicationModule.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl: "app/index/views/index.view.html"
	})
	.when("/about", {
		templateUrl: "app/about/views/about.view.html"
	})
	.when("/logFileLocConfig", {
		templateUrl: "app/logFileLocConfig/views/logFileLocConfig.view.html"
	})
	.when("/logView/:logHash", {
		templateUrl: "app/logView/views/logView.view.html"
	})
	.otherwise({
		redirectTo: "/"
	});
}]);

mainApplicationModule.directive("navigation", function() {
	return {
		 restrict : "E",
		 templateUrl : "app/navigation/views/navigation.view.html"
	}
});

angular.element(document).ready(function() {
	angular.bootstrap(document, [mainApplicationName]);
});