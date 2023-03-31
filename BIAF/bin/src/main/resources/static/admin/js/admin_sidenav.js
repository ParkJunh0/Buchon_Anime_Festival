$(function(){
   var sidenav;

   sidenav = '<ul class="dep1">'
   sidenav += '<h2>관리자메뉴</h2>'
   sidenav += '<br>'
   sidenav += '<ul class="dep2">'
   sidenav += '<li><a href="/ko/admin/memberList">회원관리</a></li>'
   sidenav += '<li><a href="/ko/admin/">게시판관리</a></li>'
   sidenav += '<li><a href="/ko/admin/movie">영화등록관리</a></li>'
   sidenav += '<li><a href="/ko/admin/goods">굿즈관리</a></li>'
   sidenav += '<li><a href="/ko/admin/">배너관리</a></li>'
   sidenav += '<li><a href="/ko/admin/reservationadmin">예매관리</a></li>'
   sidenav += '</ul></ul>'

   $('#sidenav').prepend(sidenav);
});