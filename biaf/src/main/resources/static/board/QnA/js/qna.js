$(document).ready(function(){ 

var title_head;

$('.num_head').off().on("click", function(){
title_head=$(this).next(".title_head");
title_head.clearQueue().slideUp();
title_head.clearQueue().slideDown().css('display','flex');
	});
});