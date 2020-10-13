angular.module("listaOperadora").controller(
		"listaOperadoraCtrl",
		function($scope, operadorasAPI) {
			$scope.app = "Lista Operadora";
			$scope.operadoras = [];
			operadoraEditar = [];

			/**
			 * Carrega todas as operadoras cadastradas.
			 */
			var carregarOperadoras = function() {
				operadorasAPI.getOperadoras().then(
						function successCallback(response) {
							$scope.operadoras = response.data;
						}, function errorCallback(response) {
							console.log(response);
						});
			};

			/**
			 * Abre a modal para edição da operadora
			 */
			$scope.carregarOperadoraEdit = function(operadora) {
				$scope.operadora = operadora;
				var element = angular.element('#operadoraModal');
				element.modal('show');
			};

			/**
			 * Limpa dados da modal
			 */
			$scope.limparModal = function(operadora) {
				delete $scope.operadora;
				$scope.operadoraForm.$setPristine();
				carregarOperadoras();
			};

			/**
			 * Insere ou atualiza operadora
			 */
			$scope.adicionarOperadora = function(operadora) {
				if (operadora.idOperadora == null) {
					operadorasAPI.saveOperadora(operadora).then(
							function successCallback(response) {
								delete $scope.operadora;
								$scope.operadoraForm.$setPristine();
								carregarOperadoras();
							}, function errorCallback(response) {
								console.log(response);
							});
				} else {
					operadorasAPI.updateOperadora(operadora).then(
							function successCallback(response) {
								delete $scope.operadora;
								$scope.operadoraForm.$setPristine();
								carregarOperadoras();
							}, function errorCallback(response) {
								console.log(response);
							});
				}
				var element = angular.element('#operadoraModal');
				element.modal('hide');
			}

			/**
			 * Exclui operadora selecionada
			 */
			$scope.apagarOperadora = function(operadoras, operadora) {
				
				Swal.fire({
		    		  title: 'Deseja deletar Operadora?',
		    		  text: "A base de dados será atualizada!",
		    		  icon: 'warning',
		    		  showCancelButton: true,
		    		  confirmButtonColor: '#3085d6',
		    		  cancelButtonColor: '#d33',
		    		  confirmButtonText: 'Sim, deletar?'
		    		}).then((result) => {
		    		  if (result.isConfirmed) {
		    			  operadorasAPI.deleteOperadora(operadora).then(
		  							function successCallback(response) {
		  								Swal.fire(
		      		    					  'Deletado!',
		      		    					  'Operadora deletado com sucesso!',
		      		    					  'success'
		      		    			  )
		  								delete $scope.operadora;
		  								$scope.operadoraForm.$setPristine();
		  								carregarOperadoras();
		  							}, function errorCallback(response) {
		  								console.log(response);
		  							});
		    			  
		    		  }
		    		})
			}

			/**
			 * Obtem a operadora selecionada
			 */
			$scope.isOperadoraSelecionada = function(operadoras) {
				return operadoras.some(function(operadora) {
					return operadora.selecionada;
				});
			}

			/**
			 * Ordena coluna por direcao
			 */
			$scope.ordenarPor = function(campo) {
				$scope.criterioDeOrdenacao = campo;
				$scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
			}

			/**
			 * chamada do metodo que retorna todas as operadoras cadastradas
			 */
			carregarOperadoras();
		});