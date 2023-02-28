$(function(){
    var biaf_menu=$("#biaf_menu");
    var event_menu=$("#event_menu");
    var res_menu=$("#reservation_menu");
    var commu_menu=$("#community_menu");

    var BIAF_menu=$("#BIAF_menu");
    var Event_menu=$("#Event_menu");
    var Res_menu=$("#Reservation_menu");
    var Commu_menu=$("#Community_menu");
    
    var menu_item_s=$(".menu_item_s");
    var menu1_stat=$(".menu_popup");
    var menu2_stat=$(".menu_popup");
    var menu3_stat=$(".menu_popup");
    var menu4_stat=$(".menu_popup");

    biaf_menu.hover(function(){
        BIAF_menu.stop().fadeIn(400);
    },function(){
        BIAF_menu.stop().fadeOut(400);
    });
    event_menu.hover(function(){
        Event_menu.stop().fadeIn(500);
    },function(){
        Event_menu.stop().fadeOut(500);
    });
    res_menu.hover(function(){
        Res_menu.stop().fadeIn(500);
    },function(){
        Res_menu.stop().fadeOut(500);
    });
    commu_menu.hover(function(){
        Commu_menu.stop().fadeIn(500);
    },function(){
        Commu_menu.stop().fadeOut(500);
    });
});