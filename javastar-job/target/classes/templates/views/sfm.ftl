<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>静态资源创建</title>
    <link href="../static/css/css.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/javascript" src="../static/js/jquery-2.1.3.js"></script>
    <script language="JavaScript" type="text/javascript" src="../static/js/json2.js"></script>
    <script language="JavaScript" type="text/javascript" src="../static/js/jsrender.js"></script>
    <style type="text/css">
        .TitleColor a {
            display: inline-block;
            margin-right: 10px;
        }
    </style>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0"
      marginheight="0" scroll="no">
<form>
    <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
        <tr>
            <td width="100%" height="63" colspan="3" class="TitleColor">
                <a href="/index" id="aaa">《《 返回</a>
            </td>


        </tr>
        <tr>

            <td style="width: 98%; margin-top: 10px;">

                <div style="margin: 10px;display:inline-block">
                    <select id="selTable">
                    </select>
                    <input type="button" id="btnCreate" value="创建文件" style="margin-left: 10px;">
                </div>
                <div style="margin: 10px;" id="divContent">
                </div>

                <script type="text/js-render" id="tableTemplate">
                     <a name="{{:dtName.tABLE_NAME}}"></a>
                <table class="Table-00" cellspacing="1" cellpadding="0">
                    <thead>
                    <tr>
                        <td class="td-00" colspan="12">
                            <div class="buttons">

                            </div>
                            <div style="float: left">
                                <span class="t-name">{{:dtName.tABLE_NAME}}</span>
                                <span class="t-remark">{{:dtName.tABLE_COMMENT}}</span>
                            </div>
                        </td>
                    </tr>
                    </thead>
                    <tbody id="t{{:dtName.tABLE_NAME}}">
                    <tr>
                        <td width="220px" class="td-00">代码
                        </td>
                        <td width="40px" class="td-00">类型
                        </td>
                        <td width="30px" class="td-00">列显示
                        </td>
                         <td width="30px" class="td-00">下啦框
                        </td>
                        <td width="30px" class="td-00">图片
                        </td>
                          <td width="30px" class="td-00">文本
                        </td>
                          <td width="30px" class="td-00">富文本
                        </td>
                          <td width="30px" class="td-00">连接
                        </td>
                         <td width="30px" class="td-00">可编辑
                        </td>
                        <td width="30px" class="td-00">主键
                        </td>
                        <td width="30px" class="td-00">空值
                        </td>
                    </tr>
                   {{for dtInfo}}
                            <tr class="content" id="columns">
                                <td width="100px" class="td-01 c-name">
                                    {{:field}}
                                </td>
                                <td width="100px" class="td-01 c-type">
                                   {{:type}}
                                </td>
                                 <td width="30px" class="td-01 c-name">
                                <input type="txt" id="{{:field}}_isShow" value="false" style="display:none;"/>
                                      <img src="../static/images/right.gif" value="true" />
                                </td>
                                   <td width="30px" class="td-01 c-name">
                                        <img src="../static/images/wrong.gif" value="false" />
                                </td>
                                <td width="30px" class="td-01 c-name">
                                      <img src="../static/images/wrong.gif" value="false" />
                                </td>
                               <td width="30px" class="td-01 c-name">
                                       <img src="../static/images/wrong.gif" value="false" />
                                </td>
                                <td width="30px" class="td-01 c-name">
                                         <img src="../static/images/wrong.gif" value="false" />
                                </td>
                                    <td width="30px" class="td-01 c-name">
                                        <img src="../static/images/wrong.gif" value="false" />
                                </td>
                                    <td width="30px" class="td-01 c-name">
                                         <img src="../static/images/wrong.gif" value="false" />
                                </td>
                                    <td width="30px" class="td-01 c-name">
                                      <img src="../static/images/right.gif" value="false" />
                                </td>
                                    <td width="30px" class="td-01 c-name">
                                      <img src="../static/images/wrong.gif" value="false" />
                                </td>
                            </tr>
                    {{/for}}
                    </tbody>
                </table>

                </script>
                <script type="text/javascript">

                   $( function () {
                       $("#columns td").each(function (index) {
                           $("#columns td").eq(index).on("click",function () {
                               alert('123');
                           });
                       });
                    });

                    $.get("/getTableList", {key: ''}, function (data) {
                        var obj = JSON.parse(data);
                        var content = [];
                        for (var i = 0; i < obj.length; i++) {
                            content.push("<option value=\"" + obj[i].dtName.tABLE_NAME + "\">" + obj[i].dtName.tABLE_NAME + "</option>")
                        }
                        $("#selTable").html(content.join(''));
                        showSel();
                    });

                    function showSel() {
                        $.get("/getTable", {key: $("#selTable").find("option:selected").val()}, function (data) {
                            var obj = JSON.parse(data);
                            for (var i = 0; i < obj.length; i++) {
                                var tableInfos = obj[i].dtInfo;
                                for (var j = 0; j < tableInfos.length; j++) {
                                    /* jsrender中特殊支付无法识别 可提前转换 */
                                    obj[i].dtInfo[j].shownull = obj[i].dtInfo[j].null;
                                }
                            }
                            $("#divContent").html($("#tableTemplate").render(obj));

                        });
                    }

                    $("#selTable").bind("change", function () {
                        showSel();
                    });



                    $("#btnCreate").bind("click", {}, function () {

                        //var op = [];
                        // $("#columns td").each(function (index) {
                        //     op.push(index + "-")
                        // });
                        //alert(op.join(''));

                        $.get("/html", {table: $("#selTable").find("option:selected").val()}, function (data) {
                            alert(data);
                        });

                    });


                </script>

            </td>
        </tr>
        <tr>
            <td colspan="3" height="20">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                        <td height="1" bgcolor="#000000"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>
</html>