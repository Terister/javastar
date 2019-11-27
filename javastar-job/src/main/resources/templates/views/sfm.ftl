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
                            <tr class="content" id="columns_{{:#index}}">
                                <td width="100px" class="td-01 c-name">
                                    {{:field}}
                                </td>
                                <td width="100px" class="td-01 c-type">
                                   {{:type}}
                                </td>
                                 <td width="30px" class="td-01 c-name">
                                      <img src="../static/images/right.gif" value="true" data="isShow" key="{{:field}}" />
                                </td>
                                   <td width="30px" class="td-01 c-name">
                                        <img src="../static/images/wrong.gif" value="false" data="isSelect" key="{{:field}}"  />
                                </td>
                                <td width="30px" class="td-01 c-name">
                                      <img src="../static/images/wrong.gif" value="false"  data="isPic" key="{{:field}}" />
                                </td>
                               <td width="30px" class="td-01 c-name">
                                       <img src="../static/images/wrong.gif" value="false"  data="isText" key="{{:field}}" />
                                </td>
                                <td width="30px" class="td-01 c-name">
                                         <img src="../static/images/wrong.gif" value="false"  data="isTextArea" key="{{:field}}" />
                                </td>
                                    <td width="30px" class="td-01 c-name">
                                        <img src="../static/images/wrong.gif" value="false"  data="isLink" key="{{:field}}" />
                                </td>
                                    <td width="30px" class="td-01 c-name">
                                         <img src="../static/images/wrong.gif" value="false" data="isEdit" key="{{:field}}"  />
                                </td>
                                    <td width="30px" class="td-01 c-name">
                                      <img src="../static/images/wrong.gif" value="false"  data="isPri" key="{{:field}}" />
                                </td>
                                    <td width="30px" class="td-01 c-name">
                                      <img src="../static/images/wrong.gif" value="false"  data="isNull" key="{{:field}}" />
                                </td>
                            </tr>
                    {{/for}}
                    </tbody>
                </table>



                </script>
                <script type="text/javascript">

                    var count = 0;
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
                            count = 0;
                            for (var i = 0; i < obj.length; i++) {

                                var tableInfos = obj[i].dtInfo;
                                for (var j = 0; j < tableInfos.length; j++) {
                                    /* jsrender中特殊支付无法识别 可提前转换 */
                                    obj[i].dtInfo[j].shownull = obj[i].dtInfo[j].null;
                                    count++;
                                }
                            }
                            $("#divContent").html($("#tableTemplate").render(obj));

                            for (var i = 0; i < count; i++)
                            {

                                $("#columns_" + i + " td").each(function (index) {
                                    var that = $(this);
                                    if (index > 1) {

                                        $(this).on("click",function () {

                                            var va = $(that).find("img").attr("value");
                                            var sr = $(that).find("img").attr("src");
                                            if (va === "true") {
                                                $(that).find("img").attr("value", "false");
                                                $(that).find("img").attr("src", "../static/images/wrong.gif");
                                            }
                                            if (va === "false") {
                                                $(that).find("img").attr("value", "true");
                                                $(that).find("img").attr("src", "../static/images/right.gif");
                                            }
                                        });

                                    }

                                });

                            }

                        });
                    }

                    $("#selTable").bind("change", function () {
                        showSel();
                    });


                    $("#btnCreate").bind("click", {}, function () {

                        var op = [];
                        for (var i = 0; i < count; i++)
                        {
                            var datas = {};
                            $("#columns_" + i + " td").each(function (index) {
                                var that = $(this);
                                if (index > 1) {
                                    var va = $(that).find("img").attr("value");
                                    var sr = $(that).find("img").attr("src");

                                    datas.key = $(that).find("img").attr("key");
                                    if ("isShow" == $(that).find("img").attr("data")) {
                                        datas.isShow = $(that).find("img").attr("value")
                                    }
                                    if ("isSelect" == $(that).find("img").attr("data")) {
                                        datas.isSelect = $(that).find("img").attr("value")
                                    }
                                    if ("isPic" == $(that).find("img").attr("data")) {
                                        datas.isPic = $(that).find("img").attr("value")
                                    }
                                    if ("isText" == $(that).find("img").attr("data")) {
                                        datas.isText = $(that).find("img").attr("value")
                                    }
                                    if ("isTextArea" == $(that).find("img").attr("data")) {
                                        datas.isTextArea = $(that).find("img").attr("value")
                                    }
                                    if ("isLink" == $(that).find("img").attr("data")) {
                                        datas.isLink = $(that).find("img").attr("value")
                                    }
                                    if ("isShow" == $(that).find("img").attr("data")) {
                                        datas.isShow = $(that).find("img").attr("value")
                                    }
                                    if ("isEdit" == $(that).find("img").attr("data")) {
                                        datas.isEdit = $(that).find("img").attr("value")
                                    }
                                    if ("isPri" == $(that).find("img").attr("data")) {
                                        datas.isPri = $(that).find("img").attr("value")
                                    }
                                    if ("isNull" == $(that).find("img").attr("data")) {
                                        datas.isNull = $(that).find("img").attr("value")
                                    }
                                }

                            });
                            op.push(JSON.stringify(datas));
                        }

                        $.get("/html", {
                            table: $("#selTable").find("option:selected").val(),
                            datas: "[" + op.join(",") + "]"
                        }, function (data) {
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