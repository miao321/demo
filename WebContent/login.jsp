<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>Login</title>
</head>
<body>
<!-- 输出错误提示信息 -->
<span style="color:red;font-weight:bold">
	<%
		if(request.getAttribute("err")!=null){
			out.println(request.getAttribute("err")+"<br/>");
		}
	%>
</span>

请输入用户名和密码
<form id="login" method="post" action="login">
	用户名：<input type="text" name="username"><br/>
	密码：<input type="password" name="password"><br/>
	<input type="submit" value="登录"><br/>
</form>
<a href="register.jsp">注册用户</a>
</body>
</html>