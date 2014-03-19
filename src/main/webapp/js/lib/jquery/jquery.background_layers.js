/**
 * @author Terry Wooton
 * @desc Adds a background layer to an element
 * @version 1.1
 * @example
 * $("#element").add_background("url('/test.gif') bottom left no-repeat");
 * @license free
 * @param background css
 *
 */
$(document).ready(function() {
  $.fn.add_layer = function(bg,params) {
    $(this).each(function() {
  
      s = $(this).extend({},params || {});      
      
      $last = ($(this).find('.add_background:last').length > 0 ? $(this).find('.add_background:last') : $(this));
  		$last.html('<div class="add_background"><div>'+$last.html()+'</div></div>');
  		$last = $(this).find('.add_background:last');
  		$last.css({'background':bg,'width':'100%','height':'100%'});

      $last = $(this).find('.add_background div:last');
            
      if(s.insideCss){
   		  $last.css(s.insideCss);
  		}
      if(s.insideClass)
  		  $last.addClass(s.insideClass);  		  
    });
  }      
});