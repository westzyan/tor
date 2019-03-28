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
    <title>Title</title>
</head>
<frameset frameborder="0" rows="60,*">
    <frame src="top.jsp" noresize="noresize" scrolling="no"/>
    <frameset frameborder="0" cols="210,*">
        <frame src="left.jsp" noresize="noresize" scrolling="no"/>
        <frame src="right.jsp" noresize="noresize" scrolling="yes"
               name="main"/>
    </frameset>
</frameset>
</html>
