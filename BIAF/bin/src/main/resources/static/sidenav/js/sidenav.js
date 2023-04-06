$(function(){
   var sidenav;
   var conthref= location.href.split("/")[4].split("?")[0].replace(/#$/, '');	// 주소를 "/"를 기준으로 나누어 그중 5번째 배열값에 가장 마지막에 있는 "#"제거($가 마지막을 의미)

   if(conthref == "admin"){
   sidenav = '<ul class="dep1">'
   sidenav += '<h2>관리자메뉴</h2>'
   sidenav += '<br>'
   sidenav += '<ul class="dep2">'
   sidenav += '<li><a href="/ko/admin/memberList">회원관리</a></li>'
   sidenav += '<li><a href="/ko/admin/movie">영화등록관리</a></li>'
   sidenav += '<li><a href="/ko/admin/goods">굿즈관리</a></li>'
   sidenav += '<li><a href="/ko/admin/">배너관리</a></li>'
   sidenav += '<li><a href="/ko/admin/reservationadmin">예매관리</a></li>'
   sidenav += '</ul></ul>'

   $('#sidenav').prepend(sidenav);
   }
   if(conthref == "mypage" || conthref == "cart" || conthref == "orders" || conthref=="memberout" || conthref=="memberout1"){
   sidenav = '<ul class="dep1">'
   sidenav += '<h2>마이페이지</h2>'
   sidenav += '<br><ul class="dep2">'
   sidenav += '<li><a href="/ko/mypage/{memberId}">회원정보</a></li>'
   sidenav += '<li><a href="/ko/cart">장바구니</a></li>'
   sidenav += '<li><a href="/ko/orders">상품구매내역</a></li>'
   sidenav += '<li><a href="#">티켓예매/취소</a></li>'
   sidenav += '<li><a href="/ko/memberout">회원탈퇴</a></li>'
   sidenav += '</ul></ul>'

   $('#sidenav').prepend(sidenav);
   }
});