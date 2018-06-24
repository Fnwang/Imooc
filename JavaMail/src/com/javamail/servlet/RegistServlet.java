package com.javamail.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamail.service.UserService;
import com.javamail.service.impl.UserServiceImpl;
import com.javamail.utils.UUIDUtil;
import com.javamail.vo.UserVO;

/**
 * Servlet implementation class UserServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		//��ȡ����
		String username=request.getParameter("yourname");
		String password=request.getParameter("yourpass");
		String nickname=request.getParameter("nickname");
		String email=request.getParameter("youremail");
		
		//��װ����
		UserVO user=new UserVO();
		user.setUid(UUIDUtil.getUUID());
		user.setUsername(username);
		user.setNickname(nickname);
		user.setPassword(password);
		user.setEmail(email);
		user.setState("0");//0-δ����  1-�Ѽ���
		user.setCode(UUIDUtil.getUUID()+UUIDUtil.getUUID());
		
		//��������
		UserService userService=new UserServiceImpl();
		userService.regist(user);
		
		//ҳ����ת
		request.setAttribute("msg", "����ע��ɹ�����ȥ���伤�");
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
