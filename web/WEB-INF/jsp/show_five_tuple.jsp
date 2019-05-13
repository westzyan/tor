<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 19-4-27
  Time: 下午3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示流量包五元组</title>
    <script type="text/javascript" src="${pageScope.request.ContextPath}/js/jquery-1.9.1.min.js"></script>
    <!-- <script type="text/javascript" src="back-endPage.js"></script> --> <!-- 后台分页 -->
    <script type="text/javascript" src="${pageScope.request.ContextPath}/js/front-endPage.js"></script><!-- 前台分页 -->

    <link rel="stylesheet" type="text/css" href="${pageScope.request.ContextPath}/css/table.css">
    <link rel="stylesheet" type="text/css" href="${pageScope.request.ContextPath}/css/init.css">
    <style>
        body {
            background-color: #ffffff;
        }
        input[type="text"] {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            width: 100%;
            height: -webkit-calc(3em + 2px);
            height: calc(3em + 2px);
            margin: 0 0 1em;
            padding: 1em;
            border: 1px solid #096dcc;
            border-radius: 1.5em;
            background: #fcfef7;
            resize: none;
            outline: none;
        }
        input[type="submit"] {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            width: 100%;
            height: -webkit-calc(3em + 2px);
            height: calc(3em + 2px);
            margin: 0 0 1em;
            padding: 1em;
            border: 1px solid #cccccc;
            border-radius: 1.5em;
            background: #fff;
            resize: none;
            outline: none;
        }
        input[type="text"][required]:focus {
            border-color: #ffffff;
        }
        input[type="text"][required]:focus + label[placeholder]:before {
            color: #ffffff;
        }
        input[type="text"][required]:focus + label[placeholder]:before,
        input[type="text"][required]:valid + label[placeholder]:before {
            -webkit-transition-duration: .2s;
            transition-duration: .2s;
            -webkit-transform: translate(0, -1.5em) scale(0.9, 0.9);
            -ms-transform: translate(0, -1.5em) scale(0.9, 0.9);
            transform: translate(0, -1.5em) scale(0.9, 0.9);
        }
        input[type="text"][required]:invalid + label[placeholder][alt]:before {
            content: attr(alt);
        }
        input[type="text"][required] + label[placeholder] {
            display: block;
            pointer-events: none;
            line-height: 2.3em;
            margin-top: -webkit-calc(-3em - 2px);
            margin-top: calc(-3em - 2px);
            margin-bottom: -webkit-calc((3em - 1em) + 2px);
            margin-bottom: calc((3em - 1em) + 2px);
        }
        input[type="text"][required] + label[placeholder]:before {
            content: attr(placeholder);
            display: inline-block;
            margin: 0 -webkit-calc(1em + 2px);
            margin: 0 calc(1em + 2px);
            padding: 0 2px;
            color: #898989;
            white-space: nowrap;
            -webkit-transition: 0.3s ease-in-out;
            transition: 0.3s ease-in-out;
            background-image: -webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#ffffff));
            background-image: -webkit-linear-gradient(top, #ffffff, #ffffff);
            background-image: linear-gradient(to bottom, #ffffff, #ffffff);
            -webkit-background-size: 100% 5px;
            background-size: 100% 5px;
            background-repeat: no-repeat;
            background-position: center;
        }
    </style>

</head>
<body>
<h2>查看数据包内容</h2>
<div style="width:400px;height:100px;margin: auto">
    <form action="/first/show_five_tuple" method="get">
        <input required='' type='text' name="filePath">
        <label alt='请输入文件路径' placeholder='名称'></label>
        <input required='' type='submit' value="查看">
    </form>
</div>

<br><br><br>

<div id="histroyBox"></div>
<script type="text/javascript">
    //前台分页的样子
    data = ${res.data}
    $('#histroyBox').CJJTable({
        'title':["时间戳","源IP","源端口","目的IP","目的端口","协议"],//thead中的标题 必填
        'body':["time","sourceIP","sourcePort","destinationIP","destinationPort","protocol"],//tbody td 取值的字段 必填
        'display':[1,1,1,1,1,1],//隐藏域，1显示，2隐藏 必填
        'pageNUmber':10,//每页显示的条数 选填
        'pageLength':data.length,//选填
        'url':data,//数据源 必填
        dbTrclick:function(e){//双击tr事件
            alert(e.find('.time').html())
        }
    });
    //后台分页的样子
    /*$('#histroyBox').CJJTable({
        'title':["装点","卸点","运输货物","下单日期"],//thead中的标题 必填
        'body':["contactName","contactMobliePhone","carrierName","taskNum","taskCustomerExpectPrice","taskCustomerBudgetFreight"],//tbody td 取值的字段 必填
        'display':[1,1,1,1,2,2],//隐藏域，1显示，2隐藏 必填
        'pageJson':{
            "taskId":528710,
            "pageSize":100,//ajax请求参数中的每页展示数量 选填
            "token":"yJUmunFeG3REqisYAmCfeA"
        },
        'url':'api/quoted/quotedList',//数据源 必填
        dbTrclick:function(that){ //双击tr事件
            alert(that.find('.contactName').html())
        }
    });*/
</script>
</body>
</html>
