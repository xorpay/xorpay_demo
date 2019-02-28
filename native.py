#!/usr/bin/env python
#coding: utf8

import requests
import json
import hashlib
import urllib


def sign(*p):
    return hashlib.md5(u''.join(p).encode('utf8')).hexdigest().lower()


def pay():
    pay_data = {
        'name': u'内容订阅一年期',
        'pay_type': 'native',
        'price': '50.00',
        'order_id': 'demo-3',
        'notify_url': 'http://abc.com/xorpay_notify',
    }
    pay_data['sign'] = sign(
        pay_data['name'],
        pay_data['pay_type'],
        pay_data['price'],
        pay_data['order_id'],
        pay_data['notify_url'],
        'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'  #app secret, 这里需要修改为自己的
    )
    print '\n请求数据: ', pay_data
    resp = requests.post('https://xorpay.com/api/pay/999', data=pay_data) #aid 记得改为自己的
    return json.loads(resp.text)


def query(aoid):
    resp = requests.get('https://xorpay.com/api/query/' + aoid)
    return json.loads(resp.text)


if __name__ == '__main__':
    resp = pay()
    print '\nNATIVE接口响应: ', resp
    print '\n订单状态查询: ', query(resp['aoid'])
