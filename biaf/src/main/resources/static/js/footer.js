$(function(){
    // 클릭시 주소 이동
    // 태그들의 id값으로 특정 주소로 보냄
    $('.footer_item').click(function(){
        location.href="/ko/" + $(this).attr('id').replace(/_$/, '');
    });
});