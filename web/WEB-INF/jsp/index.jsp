<%--
Created by IntelliJ IDEA.
User: ubuntu2
Date: 19-3-27
Time: 下午4:41
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>匿名流量判别系统</title>
</head>
<frameset frameborder="0" rows="60,*">
    <frame src="${pageScope.request.ContextPath}/top" noresize="noresize" scrolling="no"/>
    <frameset frameborder="0" cols="210,*">
        <frame src="${pageScope.request.ContextPath}/left" noresize="noresize" scrolling="no"/>
        <frame src="${pageScope.request.ContextPath}/right" noresize="noresize" scrolling="yes"
               name="main"/>
    </frameset>
</frameset>
</html>
