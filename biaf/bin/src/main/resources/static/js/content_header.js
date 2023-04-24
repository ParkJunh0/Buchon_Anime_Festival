$(function(){
    var content_header_html = ''; 						// 태그 넣을 변수 선언 및 초기화
    var conthref= location.href.split("/")[4].split("?")[0].replace(/#$/, '');	// 주소를 "/"를 기준으로 나누어 그중 5번째 배열값에 가장 마지막에 있는 "#"제거($가 마지막을 의미)
    
    // 각 페이지 헤더 메뉴
    // BIAF페이지 메뉴
    if(conthref == "goods" || conthref == "info" || conthref == "logo_emot"|| conthref == "logo_emot2"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>BIAF</h1></div>';
        content_header_html+='<div class="content_menu">';
        content_header_html+='<ul><li><a id="info" class="content_menu_items" href="#">소개</a></li>';
        content_header_html+='<li><a id="goods" class="content_menu_items" href="#">굿즈</a></li>';
        content_header_html+='<li><a id="logo_emot" class="content_menu_items" href="#">로고&이모티콘</a></li></ul>';
    }
    // 행사페이지 메뉴
    if(conthref == "eventhall_info" || conthref == "navigation" || conthref == "opening_ceremony" || conthref == "exhibition" || conthref == "incidental_event" || conthref == "incidental_event2" || conthref=="contest" || conthref=="contest2"){
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
    // 예매페이지 메뉴
    if(conthref == "ticket_reservation" || conthref == "schedule" || conthref == "ticket_information" || conthref == "reservation"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>예매</h1></div>';
        content_header_html+='<div class="content_menu"><ul>';
        content_header_html+='<li><a id="schedule" class="content_menu_items" href="#">현재 상영작</a></li>';
        content_header_html+='<li><a id="ticket_information" class="content_menu_items" href="#">티켓안내</a></li>';
        content_header_html+='</ul>';
    }
    // 게시판페이지 메뉴
    if(conthref == "notice" || conthref == "qna" || conthref == "freeboard"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>커뮤니티</h1></div>';
        content_header_html+='<div class="content_menu">';
        content_header_html+='<ul><li><a id="notice" class="content_menu_items" href="#">공지사항</a></li>';
        content_header_html+='<li><a id="qna" class="content_menu_items" href="#">Q&A</a></li>';
        content_header_html+='<li><a id="freeboard" class="content_menu_items" href="#">자유게시판</a></li></ul>';
    }
    // 로그인 부분
    if(conthref == "login" || conthref == "signup" || conthref == "idfind" || conthref == "terms" || conthref == "pwfind" || conthref == "join"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        if(conthref == "login"){
        	content_header_html+='<h1>로그인</h1></div>';
        }
        if(conthref == "signup" || conthref == "join" || conthref == "terms"){
        	content_header_html+='<h1>회원가입</h1></div>';
        }
        if(conthref == "idfind" || conthref == "pwfind"){
        	content_header_html+='<h1>아이디/비밀번호</h1></div>';
        }
        content_header_html+='<div class="content_menu">';
        content_header_html+='<ul><li><a id="login" class="content_menu_items" href="#">로그인</a></li>';
        content_header_html+='<li><a id="terms" class="content_menu_items" href="#">회원가입</a></li>';
        content_header_html+='<li><a id="idfind" class="content_menu_items" href="#">아이디찾기</a></li>';
        content_header_html+='<li><a id="pwfind" class="content_menu_items" href="#">비밀번호찾기</a></li></ul>';
    }
    // 어드민 부분
    if(conthref == "admin"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>관리자페이지</h1></div>';
    }
    // 마이페이지 부분
    if(conthref == "mypage" || conthref == "myedit" || conthref == "cart" || conthref == "orders" || conthref=="memberout" || conthref=="memberout1"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>마이페이지</h1></div>';
    }
    // 저작권/약관 부분
    if(conthref == "policy"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>이용약관/저작권</h1></div>';
    }
    // 개인정보 부분
    if(conthref == "pri_sta"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>개인정보취급방침</h1></div>';
    }
    // 이메일 수집 부분
    if(conthref == "email_rejection"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>이메일무단수집동거부</h1></div>';
    }
    // 검색페이지 부분
    if(conthref == "search"){
        content_header_html='<div class="content_header" style="background-image:url(/image/bg_visual1.jpg);">';
        content_header_html+='<h1>검색결과</h1></div>';
    }
    // 페이지 메뉴 입력 받은 값 cont_header 클래스의 가장 앞에 삽입
    $('.cont_header').prepend(content_header_html);
    
    var addr = $('#'+ conthref);				// 현재 주소와 같은 id값의 태그 변수에 저장
    var menu_items = $(".content_menu_items");	// 위에서 넣어줬던 content_menu_items 클래스 변수에 저장

	if(conthref == "terms" || conthref == "join" || conthref == "signup"){
		$('#terms').css('color', '#1f73ff');
		$('#terms').parent().css('border-bottom', '2px solid #1f73ff');
	}else if(conthref == "contest2"){
		$('#contest').css('color', '#1f73ff');
 		$('#contest').parent().css('border-bottom', '2px solid #1f73ff');
	}else{
		addr.css('color', '#1f73ff');
	 	addr.parent().css('border-bottom', '2px solid #1f73ff');
	}
	
    menu_items.click(function(){
        console.log($(this));
        location.href="/ko/" + $(this).attr('id');
    })
});