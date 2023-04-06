$(document).ready(function(){
    var goods_detail = $('.goods_detail');  // 상세페이지
    var cart_btn = $('.cart_btn');          // 장바구니버튼
    var pay_btn = $('.pay_btn');            // 결제버튼
    var modal = $('.modal');                // 모달 레이어
    var goods_d = false;                    // 창열림 상태
    var sells = $(".sells");                // 판매중인 상품 영역
    var solds = $(".solds");                // 품절 상품 영역
    var this_d;                             // 버튼 주소값
    var goodsId = $("#goodsId");            // 굿즈 아이디
    var goodsimg=$('.goodsimg');            // 굿즈 상세페이지 이미지
    var goodsNm = $('.goodsNm');            // 굿즈 상세페이지 굿즈이름
    var goodsprice = $('.goodsprice');      // 굿즈 상세페이지 가격
    var goodspricep = $('.goodspricep');    // 굿즈 상세페이지 배송비
    var goodsd = $('.goodsdescription');    // 굿즈 상세페이지 설명
    var goodsquantity = $('.quantity');     // 굿즈 상세페이지 수량
    var totalprice = $('.totalprice');      // 굿즈 상세페이지 총합
    var token = $("meta[name='_csrf']").attr("content");        // 토큰
    var header = $("meta[name='_csrf_header']").attr("content");// 헤더
    var totalPrice;                         // AJAX 데이터 총합 계산용
    var url;                                // AJAX 주소용
    var paramData;                          // AJAX 데이터들 저장 변수
    var param;                              // AJAX 데이터 json 타입 변환용

    // 굿즈 상세페이지 불러오기
    function gdetailon(this_){
        this_d=this_;
        
        transtext(this_);
        this_d.parent().append($('.goods_detail'));
        calculateToalPrice();
        goods_detail.clearQueue().slideDown().css('display','flex');
        goods_d=true;
    }
    // 수량 증가시 총합 증가
	goodsquantity.change(function() {
        if($(this).val() > 0)
			calculateToalPrice();
        else
            alert('수량은 1개이상 선택해주세요')
	});
	function calculateToalPrice() {
		totalPrice = goodsprice.text() * goodsquantity.val();
		totalprice.html(totalPrice + '원');
	}
    // 상품 상태 정렬 변경
    $("#select_SellStat").change(function(){
        switch($(this).val()){
            case 'sells':
                sells.css('display', 'block');
                solds.css('display', 'none');
            break
            case 'solds':
                sells.css('display', 'none');
                solds.css('display', 'block');
            break
            default:
                sells.css('display', 'block');
                solds.css('display', 'block');
            break
        }
    });
    // 굿즈 상세페이지 내용 채워넣기
    function transtext(this_t){
        this_= this_t.children('.goods_item_description').children('p');
        
        goodsId.val(this_t.children('#goodsId').val());
        goodsimg.attr('src', this_t.children('.goods_item_header').children('img').attr('src'));
        goodsNm.text(this_.children('p > b').text());
        goodsprice.text(this_.children('.goods_price > strong').text());
        goodspricep.text('10000');
        goodsd.text(this_.parent().children('.goods_item_detail_description').text());
    }
        function addCart() {

			$.ajax({
				url : url,
				type : "POST",
				contentType : "application/json",
				data : param,
				beforeSend : function(xhr) {
					/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
					xhr.setRequestHeader(header, token);
				},
				dataType : "json",
				cache : false,
				success : function(result, status) {
				},

				error : function(jqXHR, status, error) {
					if (jqXHR.status == '401') {
						alert('로그인 후 이용해주세요');
						location.href = '/ko/login';
					} else {
						// alert(jqXHR.responseText);
                        alert('로그인 후 이용해주세요');
                        location.href = '/ko/login';
					}
				}
			});
    }
    // 굿즈 상품 클릭시
    $('.goods_item').off().on("click", function () {
        var this_a = $(this);
        if (this_a.attr('class').replace('goods_item ', '') == 'sells') {
            if (goods_d == false) {   // 닫혀있으면
                gdetailon(this_a);
            } else {                  // 열려있으면
                // 현재 굿즈가 아닌걸 클릭하면 내용 변경
                if (this_d.children('.goods_item_description').children('p').children('b').text() != $(this).children('.goods_item_description').children('p').children('b').text()) {
                    goods_detail.clearQueue().slideUp(10, function () {
                        gdetailon(this_a);
                    });
                    goods_d = false;

                } else {  // 현재 굿즈면 닫음
                    goods_detail.clearQueue().slideUp();
                    goods_d = false;
                }
            }
        }else{
            alert('품절된 상품입니다.');
        }
    });

    // 장바구니 버튼 클릭
    cart_btn.on("click", function(){
        url = "/ko/cart";
			
        paramData = {
            goodsId : goodsId.val(),
            count : goodsquantity.val()
        };
        param = JSON.stringify(paramData);

        addCart();

        modal.append($('.cart_modal'));
        $('.cart_modal').fadeIn();
        modal.fadeIn().css('display', 'flex');
        // 페이지 스크롤 제거
        document.body.style.overflow = 'hidden';
    });
    // 구매 버튼 클릭
    pay_btn.on("click", function(){
        url = "/ko/order";
        paramData = {
            goodsId: goodsId.val(),
            count: goodsquantity.val()
        };
        param = JSON.stringify(paramData);
        addCart();
    });

    // 모달 계속 쇼핑 버튼 클릭
    $('.close').on("click", function(e){
        e.preventDefault();
        modal.fadeOut();
        $('.cart_modal').fadeOut();
        // 페이지 스크롤 되살리기
        document.body.style.removeProperty('overflow');
    });
    $('.cart_go').on("click", function(){
    	location.href = '/ko/cart';
    });
});