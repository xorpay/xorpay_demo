<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<title>支付</title>
<script src="./js/jquery-1.4.4.min.js"></script>
</head>
<body>
<h1 align="center">微信扫描下列二维码支付</h1>

<img  style="width:300px;height:300px;display:block;margin:0 auto;">
<h2 align="center"></h2>
</body>
<script>
var aoid = '<%=request.getParameter("aoid")%>';

$(function(){
	 var qr = '<%=request.getParameter("qr")%>';
	 $.get("PayPathServlet",{qr:qr},function(data){
		$("img").attr("src",'${pageContext.request.contextPath }/'+data);
	 });
	 setInterval(getsta,1000);
});
/* not_exist 订单不存在
new 订单未支付
payed 订单已支付，未通知成功
fee_error 手续费扣除失败
success 订单已支付，通知成功
expire 订单过期 */
function getsta(){
		$.get("GetOrdStaServlet", {aoid:aoid},function(data){
			console.log(data);
			 if(data.status=="new"){
				$("h2").html("订单未支付！");
				$("h2").css("color","red");
			}
			if(data.status=="payed"){
				$("h2").html("订单已支付！")
			}
			if(data.status=="success"){
				alert("支付成功！");
				window.location.href="pay.jsp";
			} 
		},"json");
}
</script>
</html>