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
    <link rel="stylesheet" href="${pageScope.request.ContextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageScope.request.ContextPath}/css/iden.css"/>
    <script type="text/javascript" src="${pageScope.request.ContextPath}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${pageScope.request.ContextPath}/js/front-endPage.js"></script><!-- 前台分页 -->
    <link rel="stylesheet" type="text/css" href="${pageScope.request.ContextPath}/css/table.css">
    <link rel="stylesheet" type="text/css" href="${pageScope.request.ContextPath}/css/init.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${pageScope.request.ContextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageScope.request.ContextPath}/css/admin.css">
    <script src="${pageScope.request.ContextPath}/js/pintuer.js"></script>

</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>流量判别</strong> <a href="/index">返回主页</a> </div>
</div>
<center>
    <div class="container flgure">
        <div class="content">
            <div class="basic">
                <div class="basicInfo select active">
                    <br>
                    <div class="table">
                        <c:if test="${res.data != null}">
                            <div class="thead">
                                    <%--<span>选择的特征:${select_features}</span>--%>
                                <p>流量总条数:${flow_number} </p>
                                        <p style="color: #ee3333">TOR流量条数：${flow_tor_number}</p>
                                <p>Tor流量精确判别结果</p>
                            </div>
                            <br><br><br><br><br>
                            <div id="labelBox"></div>

                        </c:if>


                        <c:if test="${res.status} == 0">
                            <font size="4" color="red">0000${res.msg}</font>
                        </c:if>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
    </div>

</center>


<script type="text/javascript">
    //前台分页的样子
    data = ${data}
        $('#labelBox').CJJTable({
            'title':["源IP","源端口","目的IP","目的端口","协议","标签"],//thead中的标题 必填
            'body':["srcIP","srcPort","dstIP","dstPort","protocol","label"],//tbody td 取值的字段 必填
            'display':[1,1,1,1,1,1],//隐藏域，1显示，2隐藏 必填
            'pageNUmber':10,//每页显示的条数 选填
            'pageLength':data.length,//选填
            'url':data,//数据源 必填
            dbTrclick:function(e){//双击tr事件
                alert(e.find('.time').html())
            }
        });
</script>
</body>


<script>
    //对应后台返回的提示
    if ('${res.status}' != '') {
        if ('${res.status}' == 1) {
            alert('${res.msg}');
        }

    }

</script>

</html>
