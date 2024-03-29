$(function(){
    $('.header').load("header.html", function(){
		headermenu();
	});   
    $("#footer").load("footer.html", function(){
		footermenu();
	});
    $('.quickmenu').load("quickmenu.html", function(){
	    quickmenu();
    });
	function headermenu(){
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

		// 헤더 검색 변수들 선언
		var btn_search=$("#search_btn"); // 검색 버튼 변수선언
		var search_bar=$("#search_");	// 검색 바 영역 변수선언
		var search_stat=false;			// 검색 바 온/오프 확인용 변수

	
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

		btn_search.click(function(e){
			e.preventDefault();			// 클릭된 곳의 이벤트 제거
			if(search_stat == false){	// 검색 바가 나와있지 않으면 나오게 함
				search_bar.slideDown();
				$(".search_btn").slideDown();
				search_stat = true;

			}else{						// 검색 바가 나와있으면 들어가게 함
				search_bar.slideUp();
				$(".search_btn").slideUp();
				search_stat = false;
			}
	});
	function searchform(search){ 	// 검색버튼 클릭시 검색값 주소를 통해 전송 임시
		location.href="/ko/search?"+ search;
	}
	    
	}
	function footermenu(){
		// 클릭시 주소 이동
    	// 태그들의 id값으로 특정 주소로 보냄
    	$('.footer_item').click(function(){
        	location.href="/ko/" + $(this).attr('id').replace(/_$/, '');
    	});
	}
	function quickmenu(){
		$('.sub_nav').click(function(){
	        location.href="/ko/" + $(this).attr('id').replace(/_$/, '');
	    });
	}
	var moviehref;
	textchange($('.movielist > ul > li:first-child'));

	function textchange(thist){
		$('.movieNm_title').text(thist.children('.movie').text());
		$('.moviegrade').text(thist.children('div').text());
		$('.movieimg').attr('src', thist.children('.movieimgUrl').val());
		$('.moviegrade').css('background-color', thist.children('.gradecolor').css('background-color'));
		moviehref=thist.children('.movielink').val();
	}

	$('.movielist').on('mousewheel', function(e) {
			if (e.originalEvent.wheelDelta >= 120) {
				this.scrollTop -= 50;
			} else if (e.originalEvent.wheelDelta <= -120) {
				this.scrollTop += 50;
			}
			return false;
	});
	$('.movies').on('click', function(){
		textchange($(this));
	});

	$('.moviedetail > a').on('click', function(){
		if(moviehref != null){
			location.href="/ko/reservation/detail/"+moviehref;
		}
	});

	
});