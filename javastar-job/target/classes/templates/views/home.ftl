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
    </style>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0"
      marginheight="0" scroll="no">
<form>
    <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
        <tr>
            <td width="100%" height="63" colspan="3" class="TitleColor">
                <asp:LinkButton ID="btnClear" runat="server" Text="清空当前缓存" OnClick="btnClear_Click"
                                style="float:right"/>
                <div>
                    选择服务器：
                    <asp:Literal ID="litConnectionList" runat="server">
                    </asp:Literal>
                </div>
                <div>
                    选择数据库：
                    <asp:Literal ID="ltimap" runat="server">
                    </asp:Literal>
                </div>
            </td>
        </tr>
        <tr>
            <td id="frmTitle" name="frmTitle" valign="middle" align="center" width="width: 10%"
                style="border-right: 1px solid #000000">
            <#--<iframe name="BoardTitle" style="height: 100%; visibility: inherit; width: 280px; z-index: 2"-->
            <#--scrolling="auto" frameborder="0" src="Left.aspx?c=<%=CurrentConnection %>&d=<%=CurrentDatabase %>"></iframe>-->
                <table id="tbLeft" style="margin-top: 0;width:100%;  " class="left" border="0" cellpadding="2"
                       cellspacing="1">
                    <tr>
                        <td colspan="2">
                            <input type="text" placeholder="输入表名或备注信息" onkeyup="search(this.value)"
                                   style="padding: 2px 6px; margin: 4px 0 4px 20px; width: 80%"/>
                        </td>
                    </tr>
                </table>


            </td>
            <td height="100%" style="width: 10pt" width="10" title="关闭/打开左栏" class="navPoint">
                <table border="0" cellpadding="0" cellspacing="0" width="11" height="100%" align="right">
                    <tr>
                        <td valign="middle" align="right" class="middleCss">
                            <img border="0" src="../static/images/close.gif" id="menuimg" alt="隐藏左栏"
                                 style="cursor: pointer"
                                 width="11" height="76"/>
                        </td>
                    </tr>
                </table>
            </td>
            <td style="width: 90%" id="tbRight">
                <a name="TOP"></a>
                <div style="margin: 10px;display:inline-block"><a href="javascript:void(0)" id="dynamicModel">CreateDynamicModel</a>
                </div>
                <div style="margin: 10px;display:inline-block"><a href="javascript:void(0)" id="exportComment">导出备注</a>
                </div>
                <div id="divDynamicModel" style="display: none; margin: 10px">
                    <textarea id="txtSql" placeholder="输入您的查询语句，根据语句的返回结果产生实体类型"></textarea>
                    <input value="生成" type="button" id="btnCreateDynamicModel"/>
                </div>
                <script type="text/js-render" id="tableTemplate">
                     <a name="{{:dtName.tABLE_NAME}}"></a>
                <table class="Table-00" cellspacing="1" cellpadding="0">
                    <thead>
                    <tr>
                        <td class="td-00" colspan="9">
                            <div class="buttons">
                                <a class="btnSelect">[Select]</a>
                                <a class="btnInsert">[insert]</a>
                                <a class="btnUpdate">[update]</a>
                                <a class="btnModelCalss">[model class]</a>
                                <a class="btnModelSet">[model set]</a>
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
                        <td class="td-00">注释
                        </td>
                        <td width="100px" class="td-00">类型
                        </td>
                        <td width="70px" class="td-00">默认值
                        </td>
                        <td width="30px" class="td-00">自增标识
                        </td>
                        <td width="30px" class="td-00">主键
                        </td>
                        <td width="30px" class="td-00">空值
                        </td>
                    </tr>
                   {{for dtInfo}}
                            <tr class="content">
                                <td width="220px" class="td-01 c-name">
                                    {{:field}}
                                </td>
                                <td class="td-01 c-remark">
                                   {{:comment}}
                                </td>
                                <td width="100px" class="td-01 c-type">
                                   {{:type}}
                                </td>
                                <td width="70px" class="td-01 c-default">
                                  {{:default}}
                                </td>
                                <td width="30px" class="td-01">
                                    <img src="../static/images/{{:extra=='auto_increment'?'right':'wrong'}}.gif" />
                                </td>
                                <td width="30px" class="td-01">
                                    <#--<input type="hidden" class="c-id" value='<%#Eval("Keyno") %>'/>-->
                                    <img src="../static/images/{{:key=='PRI'?'right':'wrong'}}.gif" />
                                </td>
                                <td width="30px" class="td-01">
                                    <img src="../static/images/{{:shownull=='YES'?'right':'wrong'}}.gif" />
                                </td>
                            </tr>
                    {{/for}}
                    </tbody>
                </table>


                </script>

                <script type="text/js-render" id="userTemplate">
                    <tr>
                        <td width="1px" valign="top">
                            <img src="../static/images/topItem_col.gif"/>
                        </td>
                        <td class="TableNameTxt">
                            <a class="a"   target="mainFrame">
                            {{:dtName.tABLE_NAME}}
                            <br/>
                            <span style="font-size: 12px">  {{:dtName.tABLE_COMMENT}}</span>
                            </a>
                        </td>
                    </tr>



                </script>
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

                    $.get("/getTableList", {key: ''}, function (data) {
                        var obj = JSON.parse(data);
                        for (var i = 0; i < obj.length; i++) {
                            var tableInfos = obj[i].dtInfo;
                            for (var j = 0; j < tableInfos.length; j++) {
                                /* jsrender中特殊支付无法识别 可提前转换 */
                                obj[i].dtInfo[j].shownull = obj[i].dtInfo[j].null;
                            }
                        }
                        $("#tbLeft").append($("#userTemplate").render(obj));
                        $("#tbRight").append($("#tableTemplate").render(obj));

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
