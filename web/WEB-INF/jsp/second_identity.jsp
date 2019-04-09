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
</head>
<body>
<center>
    <div class="container flgure">
        <div class="content">
            <div class="basic">
                <div class="basicInfo select active">
                    <div class="thead">
                        <form action="/second/identity" method="post">
                            <span><input type="file"><input type=submit id="a" value="判别"></span>
                            <span>训练文件路径<input type="textfield" name="trainFilePath" style="height: 35px; width: 80%;"><br>
                                  测试文件路径<input type="textfield" name="testFilePath" style="height: 35px; width: 80%;"><br>

                            特征筛选方法选择
                                <select name="feature">
                                    <option value=""></option>
                                    <option value="CfsSubsetEval+BestFirst">CfsSubsetEval+BestFirst</option>
                                    <option value="Infogain+Ranker">Infogain+Ranker</option>
                                </select><br>
                                分类算法选择
                                <select name="algorithm">
                                    <option value=""></option>
                                    <option value="J48(C4.5)">J48(C4.5)        </option>
                                    <option value="RandomForest">RandomForest        </option>
                                </select>
									  <input type=submit value="分类" style="height: 35px; width: 10%;">
                            </span>
                        </form>
                    </div>

                    <div class="table">
                        <c:if test="${res.data != null}">
                            <div class="thead">
                                <span>选择的特征:${res.data.allRow}</span>
                                <span>流量总条数:${res.data.allRow}</span>
                                <p>Tor流量精确判别结果</p>
                            </div>
                            <table border="1" class="tab_css_1" width="80%">
                                <tr>
                                    <th>流ID</th>
                                    <th>totalFiatNumeric</th>
                                    <th>totalBiatNumeric</th>
                                    <th>minFiatNumeric</th>
                                    <th>minBiatNumeric</th>
                                    <th>flowDuration</th>
                                    <th>标签</th>
                                </tr>
                                <c:forEach items="${res.data.list}" var="flow">
                                    <tr class="tr_css" align="center">
                                        <td>${flow.id}</td>
                                        <td>${flow.totalFiatNumeric}</td>
                                        <td>${flow.totalBiatNumeric}</td>
                                        <td>${flow.minFiatNumeric}</td>
                                        <td>${flow.minBiatNumeric}</td>
                                        <td>${flow.flowDuration}</td>
                                        <td>${flow.lable}</td>
                                            <%-- <td>
                                    <s:a href="itemPreUpdate.action?itemid=%{#item.iid}">修改</s:a>
                                    </td> --%>

                                    </tr>
                                </c:forEach>
                            </table>

                            <%--<c:forEach items="${res.data}" var="res.data">--%>
                            <tr>
                                <td colspan="6" align="center" bgcolor="#5BA8DE">
                                    共${res.data.allRow}条记录 &nbsp; &nbsp;&nbsp;&nbsp;
                                    共${res.data.totalPage}页 &nbsp;&nbsp;&nbsp;
                                    当前第${res.data.currentPage}页<br>
                                    <form action="/second/identity_by_page" method="post">
												 <span>跳转到第<input type="text" name="page"
                                                                  style="height: 35px; width: 10%;" id="page">页
												 <input type=submit value="跳转" style="height: 35px; width: 10%;"></span>
                                            <%--每页显示<input type="text" name="pageSize" value="${res.data.pageSize}"--%>
                                            <%--style="height: 35px; width: 10%;" id="pageSize">条--%>
                                    </form>

                                    <c:choose>
                                        <c:when test="${res.data.currentPage == 1}">
                                            第一页 &nbsp;&nbsp;上一页&nbsp;&nbsp;
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/second/identity_by_page?page=1" id="firstPage">第一页</a>&nbsp;&nbsp;
                                            <a href="/second/identity_by_page?page=${res.data.currentPage-1}" id="previousPage">上一页</a>&nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${res.data.currentPage != res.data.totalPage}">
                                            <a href="/second/identity_by_page?page=${res.data.currentPage + 1}" id="nextPage">下一页</a>&nbsp;&nbsp;
                                            <a href="/second/identity_by_page?page=${res.data.totalPage}" id="lastPage">最后一页</a>
                                            <%--/first/identity_by_page?page=${res.data.totalPage}&pageSize=pageSize--%>
                                            <%--/first/identity_by_page?page=${res.data.currentPage + 1}&pageSize=pageSize--%>
                                            <%--/first/identity_by_page?page=${res.data.currentPage-1}&pageSize=pageSize--%>
                                            <%--/first/identity_by_page?page=1&pageSize=--%>
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;下一页 &nbsp;&nbsp;最后一页
                                        </c:otherwise>
                                    </c:choose>

                                </td>
                            </tr>
                            <%--</c:forEach>--%>
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
</body>


<script>
    //对应后台返回的提示
    if ('${res.status}' != '') {
        if ('${res.status}' == 1) {
            alert('${res.msg}');
        }

    }

</script>

<%--<script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js" type="text/javascript"--%>
<%--charset="utf-8"></script>--%>
<%--<script type="text/javascript">--%>
<%--$(document).ready(function(){--%>
<%--//点击链接的时候调用--%>
<%--$("#lastPage").click(function(){--%>

<%--//得到input的值--%>
<%--var pageSize = $("#pageSize").val();--%>

<%--//得到id的值--%>
<%--var page = ${res.data.totalPage};--%>

<%--//设置linkToCart的href的值--%>
<%--$("#lastPage").attr("href","/first/identity_by_page?page=" + page + "&pageSize = " + pageSize);--%>
<%--});--%>

<%--$("#nextPage").click(function(){--%>

<%--//得到input的值--%>
<%--var pageSize = $("#pageSize").val();--%>

<%--//得到id的值--%>
<%--var page = ${res.data.currentPage + 1};--%>

<%--//设置linkToCart的href的值--%>
<%--$("#nextPage").attr("href","/first/identity_by_page?page=" + page + "&pageSize = " + pageSize);--%>
<%--});--%>

<%--$("#previousPage").click(function(){--%>

<%--//得到input的值--%>
<%--var pageSize = $("#pageSize").val();--%>

<%--//得到id的值--%>
<%--var page = ${res.data.currentPage - 1};--%>

<%--//设置linkToCart的href的值--%>
<%--$("#previousPage").attr("href","/first/identity_by_page?page=" + page + "&pageSize = " + pageSize);--%>
<%--});--%>

<%--$("#firstPage").click(function(){--%>

<%--//得到input的值--%>
<%--var pageSize = $("#pageSize").val();--%>
<%----%>
<%--//设置linkToCart的href的值--%>
<%--$("#firstPage").attr("href","/first/identity_by_page?page=1"+ "&pageSize = " + pageSize);--%>
<%--});--%>
<%--});--%>
<%--</script>--%>
</html>
