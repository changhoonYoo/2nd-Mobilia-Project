/**
 *  modify.js
 */
function open_delwindow(){
	
	
	var _left = Math.ceil(( window.screen.width - 600 )/2);
    var _top = Math.ceil(( window.screen.height - 500 )/2); 
	$url="modify_del.net"
	window.open($url, "회원삭제 정보", "width=600px, height=500px, left = "+_left+", top = "+_top);
}

