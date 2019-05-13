<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 19-4-11
  Time: 下午5:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="${pageScope.request.ContextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageScope.request.ContextPath}/css/admin.css">
    <script src="${pageScope.request.ContextPath}/js/jquery.js"></script>
    <script src="${pageScope.request.ContextPath}/js/pintuer.js"></script>

</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>流量包捕获</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="/pcap/capture">
            <div  style="padding-left:20px ;padding-bottom: 20px;">
                <label>【捕获条件】</label>
            </div>
            <ul class="list-inline"  style="padding-left: 40px;  padding-bottom: 25px;">
                <li>网卡名称<a href="/pcap/load_NIC">点击加载</a>:</li>
                <li style="padding-right: 200px;">
                    <div class="field" >
                        <select name="nicName">
                            <option value=""></option>
                            <%--<c:if test="${not empty nic}">--%>
                                <c:forEach items="${nic}" var="nic_name">
                                    <option value ="${nic_name}">${nic_name}</option>
                                </c:forEach>
                            <%--</c:if>--%>
                        </select>
                    </div>
                </li>
                <li>数据包数目：</li>
                <li style="padding-right: 200px;">
                    <div class="field" >
                        <input type="text" class="input" name="count" value="" />
                    </div>
                </li>
            </ul>
            <ul class="list-inline"  style="padding-left: 40px;  padding-bottom: 25px;">
                <li>host:</li>
                <li style="padding-right: 200px;"><div class="field" >
                    <input type="text" class="input" name="host" value="" />
                </div>
                </li>
                <li>port：</li>
                <li style="padding-right: 200px;"><div class="field" >
                    <input type="text" class="input" name="port" value="" />
                </div>
                </li>
            </ul>
            <ul class="list-inline"  style="padding-left: 40px;  padding-bottom: 25px;">
                <li>协议:</li>
                <li style="padding-right: 200px;"><div class="field" >
                    <select name="protocol">
                        <option value=""></option>
                        <option value="tcp">tcp</option>
                        <option value="udp">udp</option>
                        <option value="icmp">icmp</option>
                        <option value="ip">ip</option>
                        <option value="arp">arp</option>
                    </select>
                </div>
                </li>
                <li>文件路径：</li>
                <li><div class="field">
                    <input type="text" class="input" name="fileName" value="" />
                </div>
                </li>
            </ul>
            <div class="field">
                <button class="button bg-main icon-check-square-o" type="submit" style="margin-left: 500px;"> 提交</button>
            </div>

    </form></div>
</div>
<div class="body-content">
    ${msg}
</div>

</body></html>
