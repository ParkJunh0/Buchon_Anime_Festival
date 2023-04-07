$(function(){
	var modal = $('.modal');

	$('.del1').click(function(){
		modal.append($('.modal1'));
		$('.modal1').fadeIn().css('display', 'flex');
		modal.fadeIn().css('display', 'flex');
		
	});
	$('.close').click(function(){
	    modal.fadeOut();
	    $('.modal1').fadeOut();
	});
});