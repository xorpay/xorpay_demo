const crypto = require('crypto');
const axios = require('axios');

const aid = '';         //xorpay 用户 aid，在后台查看
const secret = '';      //xorpay 用户 secret，在后台查看

const md5 = (str, encoding = 'utf8') => crypto.createHash('md5').update(str, encoding).digest('hex');

let pay_data = {
  'name': '内容订阅一年期',
  'pay_type': 'native',
  'price': '100.00',
  'order_id': 'demo-00004',
  'notify_url': 'http://abc.com/xorpay_notify',
};

//签名
pay_data['sign'] = md5(pay_data['name'] + pay_data['pay_type'] + pay_data['price'] + pay_data['order_id'] + pay_data['notify_url'] + secret);
console.log(pay_data);

query_string = Object.entries(pay_data).map(([k, v]) => `${encodeURIComponent(k)}=${encodeURIComponent(v)}`).join('&');

let url = 'https://xorpay.com/api/pay/' + aid + '?' + query_string;
console.log(url);

axios.post(url).then(r => console.log(r.data)).catch(err => console.log(err));
