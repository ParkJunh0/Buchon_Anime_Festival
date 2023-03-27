$(function(){
    var goods_detail = $('.goods_detail');  // 상세페이지
    var cart_btn = $('.cart_btn');          // 장바구니버튼
    var pay_btn = $('.pay_btn');            // 결제버튼
    var modal = $('.modal');                // 모달 레이어
    var goods_d = false;                    // 창열림 상태
    var this_d;                             // 버튼 주소값
    var goodsimg=$('.goodsimg');            // 굿즈 상세페이지 이미지
    var goodsNm = $('.goodsNm');            // 굿즈 상세페이지 굿즈이름
    var goodsprice = $('.goodsprice');      // 굿즈 상세페이지 가격
    var goodspricep = $('.goodspricep');    // 굿즈 상세페이지 배송비
    var goodsd = $('.goodsdescription');    // 굿즈 상세페이지 설명

    // 굿즈 상세페이지 불러오기
    function gdetailon(this_){
        this_d=this_;
        
        transtext(this_);
        this_d.parent().append($('.goods_detail'));
        goods_detail.clearQueue().slideDown().css('display','flex');
        goods_d=true;
    }
    // 굿즈 상세페이지 내용 채워넣기
    function transtext(this_t){
        this_= this_t.children('.goods_item_description').children('p');

        goodsimg.attr('src', this_t.children('.goods_item_header').children('img').attr('src'));
        goodsNm.text(this_.children('p > b').text());
        goodsprice.text(this_.children('.goods_price > strong').text());
        goodspricep.text('10000');
        goodsd.text(this_.parent().children('.goods_item_detail_description').text());
    }
    // 굿즈 상품 클릭시
    $('.goods_item').off().on("click", function(){
        var this_a = $(this);
        if(goods_d == false){   // 닫혀있으면
            gdetailon(this_a);
        }else{                  // 열려있으면
            // 현재 굿즈가 아닌걸 클릭하면 내용 변경
            if(this_d.children('.goods_item_description').children('p').children('b').text() != $(this).children('.goods_item_description').children('p').children('b').text()){
                goods_detail.clearQueue().slideUp(function(){
                    gdetailon(this_a);
                });
                goods_d=false;
                
            }else{  // 현재 굿즈면 닫음
                goods_detail.clearQueue().slideUp();
                goods_d=false;
            }
        }
    });
    // 장바구니 버튼 클릭
    cart_btn.on("click", function(){
        modal.append($('.cart_modal'));
        $('.cart_modal').fadeIn();
        modal.fadeIn().css('display', 'flex');
        // 페이지 스크롤 제거
        document.body.style.overflow = 'hidden';
    });
    // 구매 버튼 클릭
    pay_btn.on("click", function(){

    });
    // 모달 계속 쇼핑 버튼 클릭
    $('.close').on("click", function(e){
        e.preventDefault();
        modal.fadeOut();
        $('.cart_modal').fadeOut();
        // 페이지 스크롤 되살리기
        document.body.style.removeProperty('overflow');
    });
});