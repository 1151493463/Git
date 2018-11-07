//@ sourceURL=common.js
// 设置按钮选中/非选中
function active($obj) {
	if($obj.hasClass("active")) {
		$obj.removeClass("active");
	} else {
		$obj.addClass("active");
	}
}
/*$.ajaxSetup( {    
    //设置ajax请求结束后的执行动作    
    complete : function(XMLHttpRequest, textStatus) {  
        // 通过XMLHttpRequest取得响应头，REDIRECT    
        var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");//若HEADER中含有REDIRECT说明后端想重定向  
        if (redirect == "REDIRECT") {  
            var win = window;    
            while (win != win.top){  
                win = win.top;  
            }  
          //将后端重定向的地址取出来,使用win.location.href去实现重定向的要求  
          win.location.href= XMLHttpRequest.getResponseHeader("CONTEXTPATH");    
        }  
    }    
});  */




