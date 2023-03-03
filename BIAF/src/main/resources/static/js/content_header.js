$(function(){
    var content_header_html = '';
    var conthref= location.href.split("/").reverse()[0].replace('#', '');
    
    // 각 페이지 헤더 메뉴
    if(conthref == "goods" || conthref == "info" || conthref == "logo_emot"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>BIAF</h1></div>';
        content_header_html+='<div class="content_menu">';
        content_header_html+='<ul><li><a id="info" class="content_menu_items" href="#">소개</a></li>';
        content_header_html+='<li><a id="goods" class="content_menu_items" href="#">굿즈</a></li>';
        content_header_html+='<li><a id="logo_emot" class="content_menu_items" href="#">로고&이모티콘</a></li></ul>';
    }
    if(conthref == "eventhall_info" || conthref == "navigation" || conthref == "opening_ceremony" || conthref == "exhibithion" || conthref == "incidental_event" || conthref=="contest"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>행사</h1></div>';
        content_header_html+='<div class="content_menu">';
        content_header_html+='<ul><li><a id="eventhall_info" class="content_menu_items" href="#">행사장</a></li>';
        content_header_html+='<li><a id="navigation" class="content_menu_items" href="#">찾아오시는길</a></li>';
        content_header_html+='<li><a id="opening_ceremony" class="content_menu_items" href="#">개막식</a></li>';
        content_header_html+='<li><a id="exhibition" class="content_menu_items" href="#">전시</a></li>';
        content_header_html+='<li><a id="incidental_event" class="content_menu_items" href="#">부대행사</a></li>';
        content_header_html+='<li><a id="contest" class="content_menu_items" href="#">공모전</a></li></ul>';
    }
    if(conthref == "ticket_reservation" || conthref == "schedule" || conthref == "ticket_information"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>예매</h1></div>';
        content_header_html+='<div class="content_menu">';
        content_header_html+='<ul><li><a id="ticket_reservation" class="content_menu_items" href="#">티켓예매</a></li>';
        content_header_html+='<li><a id="schedule" class="content_menu_items" href="#">상영시간표</a></li>';
        content_header_html+='<li><a id="ticket_information" class="content_menu_items" href="#">티켓안내</a></li></ul>';
    }
    if(conthref == "notice" || conthref == "qna" || conthref == "freeboard"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>커뮤니티</h1></div>';
        content_header_html+='<div class="content_menu">';
        content_header_html+='<ul><li><a id="notice" class="content_menu_items" href="#">공지사항</a></li>';
        content_header_html+='<li><a id="qna" class="content_menu_items" href="#">Q&A</a></li>';
        content_header_html+='<li><a id="freeboard" class="content_menu_items" href="#">자유게시판</a></li></ul>';
    }
    $('.cont_header').prepend(content_header_html);
    
    var addr = $('#'+ conthref);
    var menu_items = $(".content_menu_items");

    addr.css('color', '#1f73ff');
    addr.parent().css('border-bottom', '2px solid #1f73ff');

    menu_items.click(function(){
        console.log($(this));
        location.href="/ko/" + $(this).attr('id');
    })
});