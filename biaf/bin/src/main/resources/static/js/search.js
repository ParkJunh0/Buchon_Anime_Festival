$(function(){
var btn_search=$("#search_btn"); // 검색 버튼 변수선언
var search_bar=$("#search");	// 검색 바 영역 변수선언
var search_stat=false;			// 검색 바 온/오프 확인용 변수

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
});
function searchform(search){ 	// 검색버튼 클릭시 검색값 주소를 통해 전송 임시
    location.href="/ko/search?"+ search;
}