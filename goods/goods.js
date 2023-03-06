$(function(){
    var goods_detail = $('.goods_detail');
    var goods_d = false;
    var this_d;

    $('.goods_item').off().click(function(){
        if(goods_d == false){
            this_d=$(this);
            this_d.parent().append($('.goods_detail'));
            goods_detail.slideDown().css('display','flex');
            goods_d=true;
        }else{
            goods_detail.slideUp();
            goods_d=false;
        }
    });
});