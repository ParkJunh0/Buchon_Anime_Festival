$(function(){
    // 클릭시 주소 이동
    $('.footer_item').click(function(){
        location.href="/ko/" + $(this).attr('id').replace(/_$/, '');
    });
});