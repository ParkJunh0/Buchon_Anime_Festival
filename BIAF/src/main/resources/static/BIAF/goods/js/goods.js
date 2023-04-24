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
    var goods_stocknum = $('.goods_stocknum'); // 굿즈 상세페이지 재고
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
        else{
            document.body.style.overflow = 'hidden';

            swal('수량 오류','수량은 1개이상 선택해주세요',"warning").then(function(result){
                document.body.style.removeProperty('overflow');
            });
        }
	});
	function calculateToalPrice() {
		totalPrice = goodsprice.text() * goodsquantity.val();
		totalprice.html(totalPrice + '원');
	}
    // 상품 상태 정렬 변경
    $("#select_SellStat").change(function(){
        goods_detail.clearQueue().slideUp(5);
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
        goods_stocknum.text("재고: "+ this_t.children('.test1').val());
    }
    function addCart(typess) {

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
                    if(typess == 1){
                        document.body.style.overflow = 'hidden';
        
                        Swal.fire({
                            title:"구매완료",
                            text :"구매가 완료되었습니다.",
                            icon:"success",
                            confirmButtonColor: '#1f50f1',
                        }).then((result)=>{
                            if(result.isConfirmed) {
                                document.body.style.removeProperty('overflow');
                                location.href="/ko/goods";
                            }
                        });
                    }else if(typess = 2){
                        // 페이지 스크롤 제거
                        document.body.style.overflow = 'hidden';
                        Swal.fire({
                            title:"장바구니 등록완료",
                            text : "장바구니로 가시겠습니까?",
                            icon: "success",
                            showCancelButton: true,
                            confirmButtonColor: '#1f50f1',
                            cancelButtonColor: '#7e7e7e',
                            confirmButtonText: "장바구니로 이동",
                            cancelButtonText: "계속쇼핑",
                            confirmButtonClass: 'btn-success',
                            cancelButtonClass: 'btn-canncel', 
                            reverseButtons: true // 버튼 순서 거꾸로
                        }).then((result)=> {
                            if(result.isConfirmed) {
                                document.body.style.removeProperty('overflow');
                                location.href = '/ko/cart';
                            }else{
                                document.body.style.removeProperty('overflow');
                                location.href="/ko/goods";
                            }
                        });
                    }else{
                        Swal.fire({
                            title: "처리완료", 
                            text: "근데 뭘한거죠?",
                            icon: "quetion",
                            confirmButtonColor: '#1f50f1',
                        })
                    }
				},

				error : function(jqXHR, status, error) {
					if (jqXHR.status == '401') {
						Swal.fire({
                            title:"비로그인",
                            text :"로그인 후 이용해주세요",
                            icon:"error",
                            confirmButtonColor: '#1f50f1',
                        }).then(function(result){
                            if(result) {
                                location.href = '/ko/login';
                            }
                        });
					}else if (jqXHR.responseText == '300'){
                        Swal.fire({
                            title: "수량에러", 
                            text: "수량이 맞지 않습니다", 
                            icon:"warning",
                            confirmButtonColor: '#1f50f1',
                        });
                    }else if(jqXHR.responseText == "user가 아닙니다"){
                        Swal.fire({
                            title: "권한에러", 
                            text: jqXHR.responseText, 
                            icon: "error",
                            confirmButtonColor: '#1f50f1',
                        });
                    }else {
						Swal.fire({
                            title: '비로그인',
                            text: '로그인 후 이용해주세요',
                            icon: 'error',
                            confirmButtonColor: '#1f50f1',
                        }).then(function(result){
                            if(result) {
                                location.href = '/ko/login';
                            }
                        });
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
            Swal.fire({
                title: '없어요',
                text: '품절된 상품입니다.', 
                icon: 'error',
                confirmButtonColor: '#1f50f1',
            });
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

        addCart(2);

    });
    // 구매 버튼 클릭
    pay_btn.on("click", function(){
        url = "/ko/order";
        paramData = {
            goodsId: goodsId.val(),
            count: goodsquantity.val()
        };
        param = JSON.stringify(paramData);
        addCart(1);
    });
});