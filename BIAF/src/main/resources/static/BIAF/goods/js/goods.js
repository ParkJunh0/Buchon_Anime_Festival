$(function(){
    var goods_detail = $('.goods_detail');  // 상세페이지
    var cart_btn = $('.cart_btn');          // 장바구니버튼
    var pay_btn = $('.pay_btn');            // 결제버튼
    var modal = $('.modal');                // 모달 레이어
    var goods_d = false;                    // 창열림 상태
    var this_d;                             // 버튼 주소값

    $('.goods_item').off().on("click", function(){
        if(goods_d == false){
            this_d=$(this);
            this_d.parent().append($('.goods_detail'));
            goods_detail.clearQueue().slideDown().css('display','flex');
            goods_d=true;
        }else{
            goods_detail.clearQueue().slideUp();
            goods_d=false;
        }
    });
    cart_btn.on("click", function(){
        modal.append($('.cart_modal'));
        $('.cart_modal').fadeIn();
        modal.fadeIn().css('display', 'flex');
    });

    pay_btn.on("click", function(){

    });
    $('.close').on("click", function(e){
        e.preventDefault();
        modal.fadeOut();
        $('.cart_modal').fadeOut();
    });
});