$(document).ready(function () {
    var errorMessage= $('.errorMessage').val();
    if (errorMessage != '') {
        Swal.fire({
            text: errorMessage,
            icon: "error" 
        });
    }
    bindDomEvent();
});

function bindDomEvent() {
    $(".custom-file-input").on("change", function(){
        var fileName = $(this).val().split("\\").pop(); //이미지 파일명
        var fileExt = fileName.substring(fileName.lastIndexOf(".") + 1); // 확장자 추출
        fileExt = fileExt.toLowerCase(); //소문자 변환
        if (fileExt != "jpg" && fileExt != "jpeg" && fileExt != "gif" && fileExt != "png" && fileExt != "bmp") {
            Swal.fire({
                text: "이미지 파일만 등록이 가능합니다."

            }); //파일첨부 시 이미지 파일인지 검사한다.
            return;
        }
        $(this).siblings(".custom-file-label").html(fileName); //라벨 태그 안의 내용을 jquery의 .html()을 이용하여 파일명을 입력해준다.
    });
}