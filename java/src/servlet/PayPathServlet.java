package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.CreateQRCode;

/**
 * Servlet implementation class PayPathServlet
 */
public class PayPathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayPathServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /**
     * 	������ά�벢����ά�뷵�ص�ǰ��
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String qr = request.getParameter("qr");//��ά�������
		String path =request.getServletContext().getRealPath("/");//Ҫ����ͼƬ�ĵ�ַ
		String s = "";
		if(!qr.equals("")) {
			s = CreateQRCode.getPic(qr,path);//ͼƬ����
		}
		response.getWriter().write(s);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
