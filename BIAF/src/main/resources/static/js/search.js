$(function(){
var btn_search=$("#search_btn");
var search_bar=$("#search");
var search_stat=false;

btn_search.click(function(e){
    e.preventDefault();
    if(search_stat == false){
        search_bar.slideDown();
        $(".search_btn").slideDown();
        search_stat = true;

    }else{
        search_bar.slideUp();
        $(".search_btn").slideUp();
        search_stat = false;
    }
});
});
function searchform(search){
    location.href="/ko/search?"+ search;
}