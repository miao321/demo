package com.xxx.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxx.dao.DbDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="login",urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String errMsg="";
		RequestDispatcher rd;
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		try{
			DbDao dd=new DbDao("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test","root","123456");
			
			//查询结果集
			ResultSet rs=dd.query("select password from user_table where username=?", username);
			if(rs.next()){
				//用户名和密码匹配
				if(rs.getString("password").equals(password)){
					//获取session对象
					HttpSession session=request.getSession(true);
					session.setAttribute("name", username);
					
					//获取转发对象
					rd=request.getRequestDispatcher("/welcome.jsp");
					
					//转发请求
					rd.forward(request, response);
				}else{
					errMsg+="您的用户名密码不匹配，请重新输入";
				}
				
			}else{
				errMsg+="您的用户名不存在，请先注册";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//如果出错，转发到重新登陆
		if(errMsg!=null&&!errMsg.equals("")){
			rd=request.getRequestDispatcher("/login.jsp");
			request.setAttribute("err", errMsg);
			rd.forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
