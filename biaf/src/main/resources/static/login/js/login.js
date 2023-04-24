function checkEmail() {
         var reg = /\s/g;
         var token = $("meta[name='_csrf']").attr("content");
         var header = $("meta[name='_csrf_header']").attr("content");
         var email = $("#email").val()
         var code = $("#code").val()
         if (email.match(reg)) {
            //공백이 있을 경우
            return;
         }
         if ($("#email").val().trim() == '') {
            Swal.fire({
                     icon : 'error',
                     title : '입력 오류',
                     text : $("#email").attr("placeholder") + " 항목을 입력하세요.",
                     footer : '<a href="">Why do I have this issue?</a>'
                  });
            return;
         }
         $.ajax({
            url : '/email/checkEmail', //Controller에서 요청 받을 주소
            type : 'post', //POST 방식으로 전달
            dataType : 'json',
            data : {
               'email' : email,
               'code' : code,
               'action' : 'create'
            },
            success : function(check, aJaxtatus) { //컨트롤러에서 넘어온 cnt값을 받는다 
               console.log("check : " + check.result)
               
               if (check.result == "Success") {
               alert("이메일 인증 성공");
                  console.log("성공");
                  $("#email").prop('readonly', true);
                  $("#email_hidden").val("S");
                  hiddenCheck();
                  
               } else {
                  console.log("실패");
               }
            },
            error : function(xhr, ajaxOptions, thrownError) {
               console.log(xhr)
               console.log(xhr.responseText)
               console.log(ajaxOptions)
               console.log(thrownError)
               Swal.fire({
                     icon : 'error',
                     title : 'Error',
                     text : "데이터 수신 에러",
                     footer : '<a href="">Why do I have this issue?</a>'
                  });
            },
            beforeSend : function(xhr) {
               if (token && header) {
                  xhr.setRequestHeader(header, token); // 헤드의 csrf meta태그를 읽어 CSRF 토큰 함께 전송
               } else {
                  Swal.fire({
                     icon : 'error',
                     title : 'csrf Error',
                     text : "csrf 토큰 에러",
                     footer : '<a href="">Why do I have this issue?</a>'
                  });
               }
            }
         });
      }
      
      
      function kakaopost() {
			new daum.Postcode({
				oncomplete : function(data) {
					document.querySelector("#postcode").value = data.zonecode;
					document.querySelector("#address").value = data.address;
				}
			}).open();
		}
		
		function sendEmail() {
         var reg = /\s/g;
         var token = $("meta[name='_csrf']").attr("content");
         var header = $("meta[name='_csrf_header']").attr("content");
         var email = $("#email").val()
         if (email.match(reg)) {
            //공백이 있을 경우
            return;
         }
         if ($("#email").val().trim() == '') {
            Swal.fire({
                     icon : 'error',
                     title : '입력 오류',
                     text : $("#email").attr("placeholder") + " 항목을 입력하세요.",
                     footer : '<a href="">Why do I have this issue?</a>'
                  });
            return;
         }
         $.ajax({
            url : '/email/sendEmail', //Controller에서 요청 받을 주소
            type : 'post', //POST 방식으로 전달
            dataType : 'json',
            data : {
               'email' : email,
               'action' : 'create'
            },
            success : function(check, aJaxtatus) { //컨트롤러에서 넘어온 cnt값을 받는다 
               console.log("check : " + check.result)
               Swal.fire({
                     icon : 'check',
                     title : 'Success',
                     text : "이메일 발송 성공",
                     footer : '<a href="">Why do I have this issue?</a>'
                  });
               if (check.result == "Success") {
                  console.log("성공");
                  $(".btn-email-start").css({
                     "display" : "none"
                  });
                  $(".input-email-code").css({
                     "display" : "inline-block"
                  });
                  $(".btn-email-end").css({
                     "display" : "inline-block"
                  });
               } else {
                  Swal.fire({
                     icon : 'error',
                     title : '이메일 오류',
                     text : "이메일 형식이 잘못되었거나 가입된 이메일입니다.",
                     footer : '<a href="">Why do I have this issue?</a>'
                  });
                  console.log("실패");
               }
            },
            error : function(xhr, ajaxOptions, thrownError) {
               console.log(xhr)
               console.log(xhr.responseText)
               console.log(ajaxOptions)
               console.log(thrownError)
               Swal.fire({
                     icon : 'error',
                     title : 'Error',
                     text : "데이터 수신 에러",
                     footer : '<a href="">Why do I have this issue?</a>'
                  });
            },
            beforeSend : function(xhr) {
               if (token && header) {
                  xhr.setRequestHeader(header, token); // 헤드의 csrf meta태그를 읽어 CSRF 토큰 함께 전송
               } else {
                  Swal.fire({
                     icon : 'error',
                     title : 'csrf Error',
                     text : "csrf 토큰 에러",
                     footer : '<a href="">Why do I have this issue?</a>'
                  });
               }
            },
         });
      }
      
      function hiddenCheck(){
      var email_hidden = $("#email_hidden").attr("value");
      if(email_hidden == "S"){
      	$("#btn-signup").prop('disabled',false);
      	}
      	}
      	
      	function passConfirm() {
	/* 비밀번호, 비밀번호 확인 입력창에 입력된 값을 비교해서 같다면 비밀번호 일치, 그렇지 않으면 불일치 라는 텍스트 출력.*/
	/* document : 현재 문서를 의미함. 작성되고 있는 문서를 뜻함. */
	/* getElementByID('아이디') : 아이디에 적힌 값을 가진 id의 value를 get을 해서 password 변수 넣기 */
		var password = document.getElementById('pw');					//비밀번호 
		var passwordConfirm = document.getElementById('pwConfirm');	//비밀번호 확인 값
		var confrimMsg = document.getElementById('confirmMsg');				//확인 메세지
		var correctColor = "#00ff00";	//맞았을 때 출력되는 색깔.
		var wrongColor ="#ff0000";	//틀렸을 때 출력되는 색깔
		
		if(password.value == passwordConfirm.value){//password 변수의 값과 passwordConfirm 변수의 값과 동일하다.
			confirmMsg.style.color = correctColor;/* span 태그의 ID(confirmMsg) 사용  */
			confirmMsg.innerHTML ="비밀번호 일치";/* innerHTML : HTML 내부에 추가적인 내용을 넣을 때 사용하는 것. */
		}else{
			confirmMsg.style.color = wrongColor;
			confirmMsg.innerHTML ="비밀번호 불일치";
		}
	}
	
	
	
	
	function check_pw(){
            var pw = document.getElementById('pw').value;
            var SC = ["!","@","#","$","%"];
            var check_SC = 0;
 
            if(pw.length < 6 || pw.length>16){
                window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
                document.getElementById('pw').value='';
            }
            for(var i=0;i<SC.length;i++){
                if(pw.indexOf(SC[i]) != -1){
                    check_SC = 1;
                }
            }
            if(check_SC == 0){
                window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
                document.getElementById('pw').value='';
            }
            if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
                if(document.getElementById('pw').value==document.getElementById('pw2').value){
                    document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                    document.getElementById('check').style.color='blue';
                }
                else{
                    document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                    document.getElementById('check').style.color='red';
                }
            }
        }
		