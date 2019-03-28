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
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/StudentInfo.css"/>
</head>
<body>
<center>
    <div class="container flgure">
        <div class="content">
            <div class="basic">
                <div class="basicInfo select active">
                    <div class="thead">
                        <form action="/first/identity" method="post">
                            <span><input type="file"><input type=submit id="a" value="判别"></span>
                            <span><input type="textfield" name="filePath" style="height: 35px; width: 80%;">
									  <input type=submit value="判别" style="height: 35px; width: 10%;"></span>
                        </form>
                    </div>

                    <div class="table">
                        <c:if test="${res.data != null}">
                            <div class="thead">
                                <span>流量总条数:${res.data.allRow}</span>
                                <p>Tor流量初步判别结果</p>
                            </div>
                            <table border="1" class="tab_css_1" width="80%">
                                <tr>
                                    <th>序号</th>
                                    <th>源IP</th>
                                    <th>源端口</th>
                                    <th>目的IP</th>
                                    <th>目的端口</th>
                                    <th>协议</th>
                                    <th>是否为Tor</th>
                                </tr>
                                <c:forEach items="${res.data}" var="traffic">
                                    <tr class="tr_css" align="center">
                                        <td>${traffic.id}</td>
                                        <td>${traffic.sourceIP}</td>
                                        <td>${traffic.sourcePort}</td>
                                        <td>${traffic.destinationIP}</td>
                                        <td>${traffic.destinationPort}</td>
                                        <td>${traffic.protocol}</td>
                                        <td>${traffic.tor}</td>
                                            <%-- <td>
                                    <s:a href="itemPreUpdate.action?itemid=%{#item.iid}">修改</s:a>
                                    </td> --%>

                                    </tr>
                                </c:forEach>
                            </table>

                            <c:forEach items="${res.data}" var="pageBean">
                                <tr>
                                    <td colspan="6" align="center" bgcolor="#5BA8DE">
                                        共${pageBean.allRow}条记录 &nbsp; &nbsp;&nbsp;&nbsp;
                                        共${pageBean.totalPage}页 &nbsp;&nbsp;&nbsp;
                                        当前第${pageBean.currentPage}页<br>
                                        <form action="/first/identity_by_page" method="post">
												 <span>跳转到第<input type="text" name="page"
                                                                  style="height: 35px; width: 10%;">页
												 <input type=submit value="跳转" style="height: 35px; width: 10%;"></span>
                                        </form>

                                        <c:if test="${pageBean.currentPage} == 1">第一页 &nbsp;&nbsp;上一页&nbsp;&nbsp;</c:if>
                                        <!-- currentPage为当前页 -->
                                        <c:if test="${pageBean.currentPage} != 1">
                                            <a href="/first/identity_by_page?page=1">第一页</a>&nbsp;&nbsp;
                                            <a href="/first/identity_by_page?page=${pageBean.currentPage-1}">上一页</a>&nbsp;&nbsp;
                                        </c:if>


                                        <c:if test="${pageBean.currentPage} != ${pageBean.totalPage}">
                                            <a href="/first/identity_by_page?page=${pageBean.currentPage + 1}">下一页</a>&nbsp;&nbsp;
                                            <a href="/first/identity_by_page?page=${pageBean.totalPage}">最后一页</a>
                                        </c:if>
                                        <c:if test="${pageBean.currentPage} == ${pageBean.totalPage}">
                                            &nbsp;&nbsp;下一页 &nbsp;&nbsp;最后一页
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>


                        <c:if test="${res.status} == 0">
                            <font size="4" color="red">0000${res.msg}</font>
                        </c:if>

                        <c:if test="${not empty res}">
                        <div>状态${res.status}信息${res.msg}</div>
                        </div>
                    </c:if>
                    <c:if test="${ empty res}">
                        对不起，请先<a href="/user/login">登录</a>
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
</body>


<script>
    //对应后台返回的提示
    if ('${res.status}' != '') {
        if ('${res.status}' == 0) {
            alert('登录成功,即将跳转至用户详情页！')
        }else if ('${res.status}' == 1) {
            alert('${res.msg}');
        }

    }
</script>
</html>
