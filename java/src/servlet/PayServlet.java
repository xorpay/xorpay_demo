package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import service.PayService;
import utils.HttpUtils;
import utils.MD5utils;

/**
 * Servlet implementation class PayServlet
 */
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * 	��ǰ̨���ܵĲ����ŵ�map�У�����HttpUtils�ύ����ͨ���ں�̨�����sign���������Ӱ�ȫ
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String post="";
		String name = request.getParameter("name");//��Ʒ����
		String pay_type = request.getParameter("pay_type");//֧�����ͣ�Ĭ��native
		String price = request.getParameter("price");//֧���۸�
		String order_id = request.getParameter("order_id");//����ID
		String order_uid = request.getParameter("order_uid");//�û�id
		String notify_url = request.getParameter("notify_url");//�ص���ַ  �ص���ַ����Ϊ�����ַ
		String aid = PayService.aid;
		String sign = MD5utils.generate(name + pay_type + price + order_id + notify_url + PayService.app_secret);
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("pay_type", pay_type);
		map.put("price", price);
		map.put("order_id", order_id);
		map.put("order_uid", order_uid);
		map.put("notify_url", notify_url);
		map.put("sign", sign);
		try {
			post = HttpUtils.post("https://xorpay.com/api/pay/"+aid, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(post!=null) {
			JSONObject json = JSONObject.fromObject(post);
			response.getWriter().write(json.toString());
		}
		
	}

}
