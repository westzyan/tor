<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
<%--    <link rel="stylesheet" href="${pageScope.request.ContextPath}/css/common.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageScope.request.ContextPath}/css/iden.css"/>--%>

    <link rel="stylesheet" href="${pageScope.request.ContextPath}/css/world.css" />

</head>
<body>

    <div id="chartdiv"></div>
    <script src="${pageScope.request.ContextPath}/js/world/core.js"></script>
    <script src="${pageScope.request.ContextPath}/js/world/maps.js"></script>
    <script src="${pageScope.request.ContextPath}/js/world/themes/animated.js"></script>
    <script src="${pageScope.request.ContextPath}/js/world/geodata/worldLow.js"></script>
    <script src="${pageScope.request.ContextPath}/js/world/index.js"></script>
    <script>
        polygonSeries.data=${res};
    </script>


    <div class="container flgure">
        <div class="content">
            <div class="basic">
                <div class="basicInfo select active">
                    <div class="thead">
                        <form action="/toIdentity" method="post">
                            <span><input type="file"><input type=submit id="a" value="判别"></span>
                            <span><input type="textfield" name="filePath" style="height: 35px; width: 80%;">
                                      <input type=submit value="判别" style="height: 35px; width: 10%;"></span>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>


</html>
