searchVisible = 0;
transparent = true;
$(document).ready(function(){
    $("[rel='tooltip']").tooltip();
    $("#wizard").bootstrapWizard({
        "tabClass": "nav nav-pills",
        "nextSelector": ".btn-next",
        "previousSelector": ".btn-previous",
         onInit : function(tab, navigation,index){
           //contar tabs e preencher
           var $total = navigation.find("li").length;
           $width = 100 / $total;
           $display_width = $(document).width();
           if($display_width < 400 && $total > 3) {
               $width = 50;
           }
           navigation.find("li").css("width",$width + "%");
        }, 
        onTabShow: function(tab, navigation, index) {
            var $total = navigation.find("li").length;
            var $current = index + 1;
            var wizard = navigation.closest(".wizard-card");
            // na ultima tab, troca botao de proximo por concluir
            if($current >= $total) {
                $(wizard).find(".btn-next").hide();
                $(wizard).find(".btn-finish").show();
            } else {
                $(wizard).find(".btn-next").show();
                $(wizard).find(".btn-finish").hide();
            }
        }
    });
    $height = $(document).height();
    $(".set-full-height").css("height",$height);
});
(function($){
	$(document).ready(function(){
		$("ul.dropdown-menu [data-toggle=dropdown]").on("click", function(event) {
			event.preventDefault(); 
			event.stopPropagation(); 
			$(this).parent().siblings().removeClass("open");
			$(this).parent().toggleClass("open");
		});
	});
})(jQuery);