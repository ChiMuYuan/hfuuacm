<%--
  Created by IntelliJ IDEA.
  User: ChiMu
  Date: 2018/9/5
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="/user" method="post">
    账号:<input type="text" name="uname" id=""> <br/>
    密码:<input type="password" name="upwd">
    <input type="submit" name="提交" id="">
  </form>
  ${user.name}
  </body>
</html>
