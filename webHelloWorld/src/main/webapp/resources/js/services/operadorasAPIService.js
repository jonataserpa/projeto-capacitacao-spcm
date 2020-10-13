angular.module("listaOperadora").factory(
		"operadorasAPI",
		function($http, config) {

			var _getOperadoras = function() {
				return $http.post(config.baseUrl
						+ "services/operadoras/getAllOperadoras");
			}
			var _saveOperadora = function(operadora) {
				return $http.post(config.baseUrl
						+ "services/operadoras/insertOperadora", operadora)
			}
			var _updateOperadora = function(operadora) {
				return $http.post(config.baseUrl
						+ "services/operadoras/updateOperadora", operadora)
			}
			var _deleteOperadora = function(operadora) {
				return $http.post(config.baseUrl
						+ "services/operadoras/deleteOperadora/"
						+ operadora.idOperadora)
			}

			return {
				getOperadoras : _getOperadoras,
				saveOperadora : _saveOperadora,
				updateOperadora : _updateOperadora,
				deleteOperadora : _deleteOperadora
			};
		});