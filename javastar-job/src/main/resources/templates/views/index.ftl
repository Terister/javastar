<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>数据库文档</title>
    <link href="../static/css/css.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/javascript" src="../static/js/jquery-2.1.3.js"></script>
    <script language="JavaScript" type="text/javascript" src="../static/js/json2.js"></script>
    <script language="JavaScript" type="text/javascript" src="../static/js/jsrender.js"></script>
    <style type="text/css">
        .TitleColor a {
            display: inline-block;
            margin-right: 10px;
        }

        label {
            width: 110px;
            display: inline-block;
            text-align: right;
            margin-right: 15px;
        }
    </style>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0"
      marginheight="0" scroll="no" style="padding-top: 1px;">
<form style="margin-top: 5px;">
    <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%" style="margin-top: 5px;">
        <tr>
            <td width="100%" height="63" colspan="3" class="TitleColor">
                <asp:LinkButton ID="btnClear" runat="server" Text="清空当前缓存" OnClick="btnClear_Click"
                                style="float:right"/>

            </td>
        </tr>
        <tr>

            <td style="width: 90%" id="tbRight">
                <a name="TOP"></a>
                <div style="margin: 10px;display:inline-block"><a href="javascript:void(0)"
                                                                  id="dynamicModel">创建JAVA项目</a>
                </div>

                <div style="margin: 10px;display:inline-block"><a href="/home" target="_blank"
                                                                  id="exportComment">查看表信息</a>
                </div>
                <div id="divDynamicModel" style="display: none; margin: 10px">
                    <div id="txtSql">
                        <div style="margin-right: 20px;">


                            <label for="GroupId">
                                GroupId:</label><input
                                type="text" id="GroupId" value="com.wangke.framework"
                                style="border: #698CC3 solid 0px; border-bottom: #6699ff solid 1px;"></div>
                        <div style="margin-right: 20px;">


                            <label for="ArtifactID">
                                ArtifactID:</label><input
                                type="text" id="ArtifactID" value="demo"
                                style="border: #698CC3 solid 0px; border-bottom: #6699ff solid 1px;"></div>
                        <div style="margin-right: 20px;"><label for="basePath">
                            输出文件路径:</label><input
                                type="text" id="basePath" value="/Users/wolf/Root/Codes"
                                style="border: #698CC3 solid 0px; border-bottom: #6699ff solid 1px;"></div>
                        <div style="margin-right: 20px;"><label for="ckHasHtml">生成静态资源:</label><input
                                type="checkbox"
                                id="ckHasHtml">
                        </div>
                        <div style="margin-right: 20px;"><label for="ckHasCross">启用跨域配置:</label><input
                                type="checkbox"
                                id="ckHasCross">
                        </div>
                        <div style="margin-right: 20px;"><label for="ckHasTest">启用单元测试:</label><input
                                type="checkbox"
                                id="ckHasTest">
                        </div>
                        <div style="margin-right: 20px;"><label for="ckHasSwagger">启用Swagger:</label><input
                                type="checkbox" id="ckHasSwagger"></div>
                    </div>
                    <input value="生成" type="button" id="btnCreateDynamicModel" style="margin-top: 5px;"/>
                    <input value="取消" type="button" id="btnCancel" style="margin-top: 5px; margin-left: 10px;"/>
                </div>
                <div style="margin: 10px;display:inline-block"><a href="/fm"
                                                                  id="exportComment">创建静态资源</a>
                </div>


                <script type="text/javascript">
                    function search(key) {

                        $(".left tr:gt(0)").each(function () {
                            if ($(this).text().toLowerCase().indexOf(key.toLowerCase()) >= 0) {
                                $(this).show();
                            } else {
                                $(this).hide();
                            }
                        });
                    }

                    $("#dynamicModel").bind("click", {}, function () {
                        $("#divDynamicModel").show();
                    });

                    $("#btnCancel").bind("click", {}, function () {
                        $("#divDynamicModel").hide();
                    });

                    $("#btnCreateDynamicModel").bind("click", {}, function () {

                        var data={};
                        data.groupId=$("#GroupId").val();
                        data.artifactId=$("#ArtifactID").val();
                        data.basePath=$("#basePath").val();
                        data.hasTest=$('#ckHasTest').is(':checked')?1:0;
                        data.hasHtml=$('#ckHasHtml').is(':checked')?1:0;
                        data.hasCross=$('#ckHasCross').is(':checked')?1:0;
                        data.hasSwagger=$('#ckHasSwagger').is(':checked')?1:0;

                        $.get("/create", {data: JSON.stringify(data)}, function (data) {
                            if (data === "0") {
                                alert('创建成功！');

                            } else {
                                alert('操作失败！');
                            }
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
<script language="javascript" type="text/javascript">
    // JavaScript Document
    var Temp_iii = 0;
    $("#menuimg").click(function () {
        $("#frmTitle").toggle();
        if (Temp_iii == 0) {
            $("#frmTitle").hide();
            $("#menuimg").attr("src", "../static/images/open.gif");
            $("#menuimg").attr("alt", "显示左栏");
            Temp_iii = 1;
        }
        else {

            $("#frmTitle").show();
            $("#menuimg").attr("src", "../static/images/close.gif");
            $("#menuimg").attr("alt", "隐藏左栏");
            Temp_iii = 0;
        }
    });

    $("#menuimg").mouseover(function () {
        if ($("#menuimg").attr("src") == "../static/images/close.gif")
            $("#menuimg").attr("src", "../static/images/close_on.gif");
        if ($("#menuimg").attr("src") == "../static/images/open.gif")
            $("#menuimg").attr("src", "../static/images/open_on.gif");
    });
    $("#menuimg").mouseout(function () {
        if ($("#menuimg").attr("src") == "../static/images/close_on.gif")
            $("#menuimg").attr("src", "../static/images/close.gif");
        if ($("#menuimg").attr("src") == "../static/images/open_on.gif")
            $("#menuimg").attr("src", "../static/images/open.gif");
    });

</script>
