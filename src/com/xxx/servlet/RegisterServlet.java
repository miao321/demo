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
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name="register",urlPatterns={"/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		String errMsg="";
		RequestDispatcher rd;
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String conPass=request.getParameter("conPass");
		
		if(username.length()==0||(password.length()==0)||!password.equals(conPass))
			errMsg+="注册失败，请检查用户名和密码非空，并确定密码一致";
		else{
			try{
				DbDao dd=new DbDao("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test","root","123456");
				ResultSet rs=dd.query("select password from user_table where username=?", username);
				if(rs.next()){
					errMsg+="用户名已经存在，请重新设置用户名";
				}else{
					boolean addUser=dd.insert("insert into user_table(username,password) values(?,?)",username,password);
					if(!addUser){
						errMsg+="注册用户出现错误";
					}
					
					//注册成功，转发到welcome.jsp
					HttpSession session=request.getSession(true);
					session.setAttribute("name", username);
					
					//获取转发对象
					rd=request.getRequestDispatcher("/welcome.jsp");
					
					//转发请求
					rd.forward(request, response);
				}
			}catch(Exception e){
				e.printStackTrace();
			
			}
		}
		
		//如果出错，转发到重新注册
		if(errMsg!=null&&!errMsg.equals("")){
			rd=request.getRequestDispatcher("/register.jsp");
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
