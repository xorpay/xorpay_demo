<?php

# 签名函数
function sign($data_arr) {
    return md5(join('',$data_arr));
};

$sign = sign(array($_POST['aoid'], $_POST['order_id'], $_POST['pay_price'], $_POST['pay_time'], 'your app secret'));

# 对比签名
if($sign == $_POST['sign']) {
    # 签名验证成功，更新数据

    echo 'ok';
} else {
    # 签名验证错误

    header("HTTP/1.0 405 Method Not Allowed");
    exit();
};

?>
