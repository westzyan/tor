<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 19-4-9
  Time: 下午4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0079)http://www.17sucai.com/preview/643218/2019-02-28/earthquake1.0/saveProject.html -->
<html lang="zh-cn"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="${pageScope.request.ContextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageScope.request.ContextPath}/css/admin.css">
    <script src="${pageScope.request.ContextPath}/js/jquery.js"></script>
    <script src="${pageScope.request.ContextPath}/js/pintuer.js"></script>
</head>
<body>
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder">流量包列表</strong></div>
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li> <a class="button border-main icon-plus-square-o" href=""> 流量包数目：${size}</a> </li>
            </ul>
        </div>
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <form method="post" action="/pcap/insert">
                    文件包导入：请输入文件路径
                    <input type="textfield" name="filePath" style="height: 20px; width: 60%;">
                    <input type="submit" name="upload" value="导入">
                </form>

            </ul>
        </div>

        <volist name="list" id="vo">

        </volist><table class="table table-hover text-center">

        <tbody>
        <tr>
            <%--<th>ID</th>--%>
            <th>流量包ID</th>
            <th>流量包名</th>
            <th>绝对路径</th>
            <th>大小</th>
            <th>sha1值</th>
            <th>是否处理</th>
            <th>导入日期</th>
            <th>处理时间</th>
            <th>操作</th>
        </tr>
        <c:if test="${res.data != null}">
            <c:forEach items="${res.data}" var="pcap">
            <tr>
                <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="id[]" value="${pcap.id}">${pcap.id}</td>
                <%--<td>${pcap.id}</td>--%>
                <td><font color="#00CC99">${pcap.fileName}</font></td>
                <td>${pcap.filePath}</td>
                <td>${pcap.fileSize}M</td>
                <td>${pcap.sha1Value}</td>
                <td>${pcap.isHandled}</td>
                <td>${pcap.createTime}</td>
                <td>${pcap.updateTime}</td>
                <td><div> <a class="button border-main" href="/first/show_five_tuple?filePath=${pcap.filePath}" style="padding: 2px 8px;"><span class="icon-edit"></span>查看</a>
                    <a class="button border-red" href="/pcap/delete_by_id?id=${pcap.id}" style="padding: 2px 8px;" onclick="return confirm('确定将此记录删除?')"><span class="icon-trash-o"></span> 删除</a> </div></td>
            </tr>
            </c:forEach>
        </c:if>
        <c:if test="${res.data == null}">
            <tr>
                <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="id[]" value="">1</td>
                <td>暂无数据包！</td>
                </tr>
        </c:if>
        <tr><td colspan="1" style="text-align:left; padding:10px 0;padding-left:20px;"><input type="checkbox" id="checkall">全选 </td>
            <td><a class="button border-red" href="javascript:void(0)" style="padding: 2px 8px;" onclick="DelSelect()">
                <span class="icon-trash-o"></span> 批量删除</a></td>
            <td>
            </td>
        </tr>
        <tr>
            <td colspan="8">
                <div class="pagelist">
                    <a href="">上一页</a>
                    <span class="current">1</span>
                    <a href="">2</a>
                    <a href="">3</a>
                    <a href="">下一页</a>
                    <a href="">尾页</a>
                </div>
            </td>
        </tr>
        </tbody></table>
    </div>

<script type="text/javascript">

    //搜索
    function changesearch(){

    }

    //单个删除
    function del(id,mid,iscid){
        if(confirm("您确定要删除吗?")){

        }
    }

    //全选
    $("#checkall").click(function(){
        $("input[name='id[]']").each(function(){
            if (this.checked) {
                this.checked = false;
            }
            else {
                this.checked = true;
            }
        });
    })

    //批量删除
    function DelSelect(){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){
            var t=confirm("您确认要删除选中的内容吗？");
            if (t==false) return false;
            var cks=document.getElementsByName("id[]");
            var str="";
            //拼接所有的图书id
            for(var i=0;i<cks.length;i++){
                if(cks[i].checked){
                    str+=+cks[i].value+",";
                }
            }
            //去掉字符串末尾的‘&'
            str=str.substring(0, str.length-1);
            alert(str)
            location.href="/pcap/delete_by_ids?ids="+str;
            <%--location.href="/pcap/"--%>
        }
        else{
            alert("请选择您要删除的内容!");
            return false;
        }
    }

    if ('${res.status}' != '') {
        if ('${res.status}' == 1) {
            alert('${res.msg}');
        }
    }

</script>


</body></html>