<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<title>支付DEMO</title>
</head>
<script src="./js/jquery-1.4.4.min.js"></script>
   <style>
        .area {
            width: 200px;
            margin: auto auto;
        }
        .item {
            display: block;
            width: 100%;
            line-height: 20px;
            padding: 5px;
            border-radius: 5px;
            margin: 10px;
        }
      </style>
<body>
  <div class="area">
  <h1 align="center">支付测试</h1>
            <form action="" method="post" id="api_form">
            <input type="hidden" name="aid" id="aid">
                商品名称：<input class="item" type="text" name="name" id="api_name" placeholder="name">
                支付类型 ：  <input class="item" type="text" name="pay_type" id="api_pay_type" placeholder="pay_type" value="native">
         金额：       <input class="item" type="text" name="price" id="api_price" placeholder="price">
     自定义订单编号：        <input class="item" type="text" name="order_id" id="api_order_id" placeholder="order_id">
        用户ID        <input class="item" type="text" name="order_uid" id="api_order_uid" placeholder="order_uid">
       回调地址         <input class="item" type="text" name="notify_url" id="api_notify_url" placeholder="notify_url" value="https://xorpay.com/main">
                <br>
                <input class="item" type="button" onclick="pay()" value="第二步: 支 付" style="width:107%;">
            </form>
        </div>
        <script>
            function pay(){
            	  var data = $("#api_form").serialize();     
            	   $.ajax({ 
            	    type:'post',  
            	    url:"PayServlet", 
            	    data:data,  
            	    dataType:'json', 
            	    success:function(data){  
            	    	//状态详情请看文档
            	      if(data.status == "ok"){
            	    	window.location.href="payDemo.jsp?qr="+data.info.qr+"&aoid="+data.aoid;
            	     }else if(data.status == "order_expire"){
            	    	 alert("订单已过期！");
            	     }else{
            	    	 alert("订单异常！");
            	     } 
            	    },
            	    error:function(data){ 
            	     alert("请求失败");
            	    }
            	   })
            	    
            }
        </script>
</body>
</html>