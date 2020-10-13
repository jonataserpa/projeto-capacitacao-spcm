(function() {
	"use strict";
	angular.module("ngMaskDocuments", []);
})();
(function() {
	"use strict";
	angular.module("ngMaskDocuments")
	.filter("cnpj", function() {
		return function(input) {
			var str = input + "";
			str = str.replace(/\D/g, "");
			str = str.replace(/^(\d{2})(\d)/, "$1.$2");
			str = str.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");
			str = str.replace(/\.(\d{3})(\d)/, ".$1/$2");
			str = str.replace(/(\d{4})(\d)/, "$1-$2");
			return str;
		};
	})
	.filter("cpf", function() {
		return function(input) {
			var str = input + "";
			str = str.replace(/\D/g, "");
			str = str.replace(/(\d{3})(\d)/, "$1.$2");
			str = str.replace(/(\d{3})(\d)/, "$1.$2");
			str = str.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
			return str;
		};
	})
	.filter("cnpjOrCpf", function(cnpjFilter, cpfFilter) {
		return function(input) {
			if (input != undefined) {
				if (input.length == 14) {
					return cnpjFilter(input);
				} else if (input.length == 11) {
					return cpfFilter(input);
				}
			}
			return "";
		};
	})
	.filter("phone", function() {
		return function(input) {
			var str = input + "";
			str = str.replace(/\D/g, "");
			if (str.length === 11) {
				str = str.replace(/^(\d{2})(\d{5})(\d{4})/, "($1) $2-$3");
			} else {
				str = str.replace(/^(\d{2})(\d{4})(\d{4})/, "($1) $2-$3");
			}
			return str;
		};
	})
	.filter("zipcode", function() {
		return function(input) {
			var str = input + "";
			str = str.replace(/\D/g, "");
			str = str.replace(/^(\d{2})(\d{3})(\d)/, "$1.$2-$3");
			return str;
		};
	});
})();