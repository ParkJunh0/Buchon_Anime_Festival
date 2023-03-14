$(function(){
    $('.header').load("header.html");   
    $("#footer").load("footer.html");
    $('.quickmenu').load("quickmenu.html", function(){
	    // 가장 상위 메뉴들 id를 변수선언
	    var biaf_menu=$("#biaf_menu");					
	    var event_menu=$("#event_menu");
	    var res_menu=$("#reservation_menu");
	    var commu_menu=$("#community_menu");
	    
		// 하위 메뉴들 id를 변수선언
	    var BIAF_menu=$("#BIAF_menu");
	    var Event_menu=$("#Event_menu");
	    var Res_menu=$("#Reservation_menu");
	    var Commu_menu=$("#Community_menu");
	    
	    // 메뉴들 공통클래스 변수 선언
	    var menu_item_s=$(".menu_item_s");
	
		console.log(biaf_menu);
	    // 헤더 메뉴 마우스 올리면/내리면 세부메뉴 활성화/비활성화
	    biaf_menu.hover(function(){
	        BIAF_menu.clearQueue().stop().fadeIn(400);
	    },function(){
	        BIAF_menu.clearQueue().stop().fadeOut(400);
	    });
	    event_menu.hover(function(){
	        Event_menu.clearQueue().stop().fadeIn(500);
	    },function(){
	        Event_menu.clearQueue().stop().fadeOut(500);
	    });
	    res_menu.hover(function(){
	        Res_menu.clearQueue().stop().fadeIn(500);
	    },function(){
	        Res_menu.clearQueue().stop().fadeOut(500);
	    });
	    commu_menu.hover(function(){
	        Commu_menu.clearQueue().stop().fadeIn(500);
	    },function(){
	        Commu_menu.clearQueue().stop().fadeOut(500);
	    });
	
	    // 헤더메뉴 클릭시 주소이동
	    $('.menu_item>a').click(function(){
	        if($(this).children().text() == "BIAF"){
	            location.href="/ko/info";
	        }
	        if($(this).children().text() == "행사"){
	            location.href="/ko/eventhall_info";
	        }
	        if($(this).children().text() == "예매"){
	            location.href="/ko/schedule";
	        }
	        if($(this).children().text() == "커뮤니티"){
	            location.href="/ko/notice";
	        }
	    });
	    
	    // 세부 메뉴 클릭시 주소이동
	    $('.menu_item_s>a').click(function(){
	        location.href="/ko/" + $(this).parent().attr('id').replace(/_$/, '');
	    });
	    $('.sub_nav').click(function(){
	        location.href="/ko/" + $(this).attr('id').replace(/_$/, '');
	    });
    });
});