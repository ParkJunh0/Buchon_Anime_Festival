function abc(){
    window.open("popup.html", "a", "width=50, height=50, left=5");
}
$(function(){
	var modal = $('.modal');

    content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
    content_header_html+='<h1>BIAF</h1></div>';
    content_header_html+='<div class="content_menu">';
    content_header_html+='<ul><li><a id="info" class="content_menu_items" href="#">소개</a></li>';
    content_header_html+='<li><a id="goods" class="content_menu_items" href="#">굿즈</a></li>';
    content_header_html+='<li><a id="logo_emot" class="content_menu_items" href="#">로고&이모티콘</a></li></ul>';
	
	$('.cont_header').prepend(content_header_html);
	
	// 모달 예시 모달 (활성화시)
	$('.modal_on').on("click", function(e){
		e.preventDefault();
		modal.append($('.modal_ex'));
		modal.fadeIn().css('display', 'flex');
		$('.modal_ex').fadeIn().css('display', 'flex');
	});
	$('.modal_on2').on("click", function(e){
		e.preventDefault();
		modal.append($('.modal_ex2'));
		modal.fadeIn().css('display', 'flex');
		$('.modal_ex2').fadeIn().css('display', 'flex');
	});
	$('.close').on("click", function(e){
		e.preventDefault();
		modal.fadeOut();
		$('.modal_ex').fadeOut();
		$('.modal_ex2').fadeOut();
	});
});