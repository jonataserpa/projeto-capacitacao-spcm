(function() {
	"use strict";
	angular.module("ngMaskCurrency", []);
})();
(function() {
	"use strict";
	angular.module("ngMaskCurrency")
		.directive("format", ["$filter", function ($filter) {
	  		return {
		        require: "?ngModel",
		        link: function (scope, elem, attrs, ctrl) {
		            if (!ctrl) return;
		            ctrl.$formatters.unshift(function (a) {
		            	var value = $filter(attrs.format)(ctrl.$modelValue);
		            	if (value != undefined) {
		            		if (value.indexOf("R$") > -1) {
		            			value = value.replace("R$", "").trim();
		            		} else {
		            			value = value.replace("$", "").trim();
		            			value = value.replace(".", ",");
		            		}
			            	var valueAux = value.replace(",","");  
			                valueAux = valueAux.replace(".","");  
			                if (parseInt(valueAux) == 0) {
			                	value = "0,00";
			                }
		            	}
		            	return value;
		            });
		            ctrl.$parsers.unshift(function (viewValue) {
		            	if (viewValue.length <= 3) {
		                    viewValue = "0,00" + viewValue;
		                }
		                var value = viewValue;
		                value = value.replace(/\D/g,"");
		                value = value.replace(/(\d{2})$/,",$1");
		                value = value.replace(/(\d+)(\d{3},\d{2})$/g,"$1.$2");
		                var valueAux = viewValue.replace(",","");  
		                valueAux = valueAux.replace(".","");
		                if (parseInt(valueAux) == 0) {
		                	ctrl.$viewValue = 0;
		                	ctrl.$setViewValue();
		                	elem.val("0,00");
		                	return "0,00";
		                }
		                var qtdLoop = (value.length-3)/3;
		                var count = 0;
		                while (qtdLoop > count)
		                {
		                    count++;
		                    value = value.replace(/(\d+)(\d{3}.*)/,"$1.$2");
		                }
		                var plainNumber = value.replace(/^(0)(\d)/g,"$2");
		                elem.val(plainNumber);
		                return plainNumber;
		            });
		        }
		    };
	}]);
})();