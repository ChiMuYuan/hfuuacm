<%--
  Created by IntelliJ IDEA.
  User: PearFL
  Date: 2018/9/5
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注销</title>
</head>
<body>
<%
    response.setHeader("refresh", "2; URL = index.jsp");  // 定时跳转
    session.invalidate(); // 注销 session
%>
<h3>您已成功推出本系统，两秒钟后跳转到主页面</h3>
<h3>如果没有自动跳转，请点击<a href="index.jsp">这里</a></h3>

</body>
</html>
