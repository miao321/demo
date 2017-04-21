<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<form id="login" method="post" action="register">
	用户名：<input type="text" name="username"><br/>
	密码：<input type="password" name="password"><br/>
	确认密码：<input type="password" name="conPass"><br/>
	<input type="submit" value="注册"><br/>
</form>
</body>
</html>