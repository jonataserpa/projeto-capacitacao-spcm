angular.module("listaOperadora").config(function($routeProvider) {
	$routeProvider.when("/operadoras", {
		controller : "listaOperadoraCtrl"
	})
});