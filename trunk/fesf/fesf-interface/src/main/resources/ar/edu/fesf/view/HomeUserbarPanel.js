$(document).ready( function () {
		
		if($.browser.msie){
		$('.menu_wrap').css({'background-color' : '#b8e1fc','position' : 'absolute','width' : '100%'});
		}
		
		$('.menu_wrap').css({'opacity' : '0.3'});
	
		
		$('.menu_wrap').hover ( function () {
			
			$(this).animate({'margin-top' : '0px','opacity' : '1'});
			$('.menu_buttons img').animate({'opacity' : '.5'});
	
	
		}, function () {
			
			$(this).animate({'margin-top' : '-30px','opacity' : '0.3'});
	
		});
		
		$('.menu_buttons img').hover ( function () {
					
			$(this).animate({'opacity' : '1'});
			
		}, function () {
			
			$(this).animate({'opacity' : '0.5'});
			
		});
		
	});