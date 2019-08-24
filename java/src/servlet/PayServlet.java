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
	 * 	将前台接受的参数放到map中，采用HttpUtils提交表单，通过在后台计算出sign，这样更加安全
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String post="";
		String name = request.getParameter("name");//商品名称
		String pay_type = request.getParameter("pay_type");//支付类型，默认native
		String price = request.getParameter("price");//支付价格
		String order_id = request.getParameter("order_id");//订单ID
		String order_uid = request.getParameter("order_uid");//用户id
		String notify_url = request.getParameter("notify_url");//回调地址  回调地址必须为网络地址
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
