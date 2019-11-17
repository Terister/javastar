<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>芝华士 - CHIAVA</title>
<#include 'tags/header.ftl'/>
</head>

<body class="page-header-fixed">
<#--@Html.Partial("_Header")-->
<!-- BEGIN HEADER -->

<div class="header navbar navbar-inverse navbar-fixed-top">

    <!-- BEGIN TOP NAVIGATION BAR -->

    <div class="navbar-inner">

        <div class="container-fluid">

            <!-- BEGIN LOGO -->

            <a class="brand" href="/">

                <img src="../static/Content/media/image/m-logo.png" alt="logo"/>
            </a>

            <!-- END LOGO -->
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->

            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

                <img src="../static/Content/media/image/menu-toggler.png" alt=""/>
            </a>

            <!-- END RESPONSIVE MENU TOGGLER -->
            <!-- BEGIN TOP NAVIGATION MENU -->

            <ul class="nav pull-right">

                <!-- BEGIN NOTIFICATION DROPDOWN -->

                <!-- END TODO DROPDOWN -->
                <!-- BEGIN USER LOGIN DROPDOWN -->

                <li class="dropdown user">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <img alt="" src="../static/Content/media/image/avatar.png"
                             style="height:29px; vertical-align:middle;"/>

                        <span class="username">admin</span>

                        <i class="icon-angle-down"></i>
                    </a>

                    <ul class="dropdown-menu">

                        <li><a href="/api/admin/changepwd"><i class="icon-user"></i> 修改密码</a></li>


                        <li><a href="/api/logout"><i class="icon-key"></i> 退出</a></li>
                    </ul>
                </li>

                <!-- END USER LOGIN DROPDOWN -->
            </ul>

            <!-- END TOP NAVIGATION MENU -->
        </div>
    </div>

    <!-- END TOP NAVIGATION BAR -->
</div>

<!-- END HEADER -->
<div class="page-container">
<#--@Html.Partial("_Left")-->
<#include 'tags/left.ftl' />


    <div class="page-content">
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN STYLE CUSTOMIZER -->

                    <div class="color-panel hidden-phone">

                        <div class="color-mode-icons icon-color"></div>

                        <div class="color-mode-icons icon-color-close"></div>

                        <div class="color-mode">

                            <p>THEME COLOR</p>

                            <ul class="inline">

                                <li class="color-black current color-default" data-style="default"></li>

                                <li class="color-blue" data-style="blue"></li>

                                <li class="color-brown" data-style="brown"></li>

                                <li class="color-purple" data-style="purple"></li>

                                <li class="color-grey" data-style="grey"></li>

                                <li class="color-white color-light" data-style="light"></li>
                            </ul>

                            <label>

                                <span>Layout</span>

                                <select class="layout-option m-wrap small">

                                    <option value="fluid" selected>Fluid</option>

                                    <option value="boxed">Boxed</option>
                                </select>
                            </label>

                            <label>

                                <span>Header</span>

                                <select class="header-option m-wrap small">

                                    <option value="fixed" selected>Fixed</option>

                                    <option value="default">Default</option>
                                </select>
                            </label>

                            <label>

                                <span>Sidebar</span>

                                <select class="sidebar-option m-wrap small">

                                    <option value="fixed">Fixed</option>

                                    <option value="default" selected>Default</option>
                                </select>
                            </label>

                            <label>

                                <span>Footer</span>

                                <select class="footer-option m-wrap small">

                                    <option value="fixed">Fixed</option>

                                    <option value="default" selected>Default</option>
                                </select>
                            </label>
                        </div>
                    </div>

                    <!-- END BEGIN STYLE CUSTOMIZER -->
                    <h3 class="page-title">

                        芝华士
                        <small>产品中心</small>
                    </h3>
                </div>
            </div>
            <!-- END PAGE HEADER-->
        </div>
    <#--@RenderBody()-->
        <!--body begin-->

        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title"></h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">首页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">季后赛列表</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="form-inline">

                        <input type="text" value="" id="txtTime" class="m-wrap medium">&nbsp;
                        <input id="btnEnd" class="btn blue" type="button" value="设置活动结束时间"/>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="portlet box grey">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-cogs"></i>列表</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a id="btnAdd" href="/store/application/detail" class="icon-plus"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body table-responsive">
                            <div class="form-inline">

                            </div>
                            <table class="table table-striped tablesorter" id="gvResult"></table>
                            <div id="Pager"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!--/span-->
        </div>

        <!--body end-->
    </div>
</div>

<#include 'tags/footer.ftl'/>

<script type="text/js-render" id="roleTemplate">
        <tr>
             <td>{{:id}}</td>
            <td>{{:category}}</td>

             <td>{{:homeicon}}</td>
             <td>{{:homename}}</td>

             <td>{{:homecount}}</td>

            <td>{{:opponenticon }}</td>
            <td>{{:opponentname}}</td>

            <td>{{:opponentscount}}</td>
        </tr>


</script>
<script type="text/javascript">


    var applicationObject = {
                option: {
                    pageIndex: 1,
                    pageSize: 20,
                    pageCount: 0,
                    count: 0,
                    loading: '<tr><td colspan="6"><div><img src="../static/Content/images/loading.gif" alt="Loading" /></div></td></tr>',
                    listHeader: '<thead>' +
                    '<tr role="row">' +
                    '<th >ID</th>' +
                    '<th >分类</th>' +


                    '<th >主场ICON</th>' +
                    '<th >主场</th>' +

                    '<th >主场比分</th>' +


                    '<th >客场ICON</th>' +
                    '<th >客场</th>' +

                    '<th >客场比分</th>' +

                    '</tr>' +
                    '</thead><tbody id="gvBody"></tbody>',
                },
                showCategory: function (cid) {
                    return $("#selCategory").find("option[value=" + cid + "]").text();
                },
                changeItem: function (id, type, value) {
                    $.post("/api/activity/changeItem", {id: id, type: type, data: value}, function (data) {

                        //  alert(JSON.parse(data).message);

                    });
                },

                loadData: function () {
                    var keyword = $("#txtKeyword").val();
                    applicationObject.option.pageSize = $('#selPageSize').val();
                    $("#gvResult").html(applicationObject.option.loading);
                    $.getJSON("/api/activity/list", {}, function (data) {
                        var obj = data, content = [];
                        $("#gvResult").html(applicationObject.option.listHeader);

                        for (var i = 0; i < obj.data.length; i++) {


                            obj.data[i].ar = '<text type="input" class="isAr" data-id="' + obj.data[i].id + '" id="ar' + obj.data[i].id + '">' + obj.data[i].ar + '</text>';
                            obj.data[i].category = '<text type="input"  class="isCate" data-id="' + obj.data[i].id + '" id="cate' + obj.data[i].id + '">' + obj.data[i].category + '</text>';

                            obj.data[i].homecount = '<text type="input"  class="isHomeCount" data-id="' + obj.data[i].id + '" id="homecount' + obj.data[i].id + '">' + obj.data[i].homecount + '</text>';
                            obj.data[i].homeenname = '<text type="input"  class="isHomeEnName" data-id="' + obj.data[i].id + '" id="homeenname' + obj.data[i].id + '">' + obj.data[i].homeenname + '</text>';
                            obj.data[i].homeicon = '<span type="input"  class="isHomeIncon" data-id="' + obj.data[i].id + '" id="homicon' + obj.data[i].id + '">' + obj.data[i].homeicon + '</span>';
                            obj.data[i].homename = '<text type="input"  class="isHomeName" data-id="' + obj.data[i].id + '" id="homename' + obj.data[i].id + '">' + obj.data[i].homename + '</text>';

                            obj.data[i].opponentenname = '<text type="input"  class="IsOpEnName" data-id="' + obj.data[i].id + '" id="openname' + obj.data[i].id + '">' + obj.data[i].opponentenname + '</text>';
                            obj.data[i].opponenticon = '<span type="input"  class="IsOpIcon" data-id="' + obj.data[i].id + '" id="opicon' + obj.data[i].id + '">' + obj.data[i].opponenticon + '</span>';
                            obj.data[i].opponentname = '<text type="input"  class="IsOpName" data-id="' + obj.data[i].id + '" id="opname' + obj.data[i].id + '">' + obj.data[i].opponentname + '</text>';
                            obj.data[i].opponentscount = '<text type="input"  class="IsOpCount" data-id="' + obj.data[i].id + '" id="opcount' + obj.data[i].id + '">' + obj.data[i].opponentscount + '</text>';
                        }
                        $("#gvBody").append($("#roleTemplate").render(obj.data));

                        $.each($('.isAr,.isCate,.isHomeCount,.isHomeEnName,.isHomeName,.IsOpEnName,.IsOpName,.IsOpCount,.isHomeIncon,.IsOpIcon'), function () {
                            new Medium({
                                element: document.getElementById(this.id),
                                mode: Medium.inlineMode,
                                maxLength: 25,
                                placeholder: '...'
                            });
                        });
                        $('.isAr').on('blur', function () {
                            applicationObject.changeItem($(this).attr('data-id'), 1, $(this).html());
                        });
                        $('.isCate').on('blur', function () {
                            applicationObject.changeItem($(this).attr('data-id'), 2, $(this).html());
                        });

                        $('.isHomeCount').on('blur', function () {
                            applicationObject.changeItem($(this).attr('data-id'), 3, $(this).html());
                        });
                        $('.isHomeEnName').on('blur', function () {
                            applicationObject.changeItem($(this).attr('data-id'), 4, $(this).html());
                        });
                        $('.isHomeName').on('blur', function () {
                            applicationObject.changeItem($(this).attr('data-id'), 5, $(this).html());
                        });


                        $('.IsOpCount').on('blur', function () {
                            applicationObject.changeItem($(this).attr('data-id'), 6, $(this).html());
                        });
                        $('.IsOpEnName').on('blur', function () {
                            applicationObject.changeItem($(this).attr('data-id'), 7, $(this).html());
                        });
                        $('.IsOpName').on('blur', function () {
                            applicationObject.changeItem($(this).attr('data-id'), 8, $(this).html());
                        });


                        $('.isHomeIncon').on('blur', function () {
                            applicationObject.changeItem($(this).attr('data-id'), 9, $(this).html());
                        });
                        $('.IsOpIcon').on('blur', function () {
                            applicationObject.changeItem($(this).attr('data-id'), 10, $(this).html());
                        });


//                $('.isHomeIncon,.IsOpIcon').on('click', function () {
//                    alert('弹出框');
//                });


                        App.handleUniform();

                    });
                }
            }
            ;

    $(function () {

        $("#btnEnd").click(function () {
            var time = $("#txtTime").val();
            if (time.length > 5)
                $.post("/api/activity/changeRacetime", {time: time}, function (data) {
                    var obj = $.parseJSON(data);
                    if (obj.code == 0) {
                        alert('操作完成');
                    } else {
                        alert('操作失败' + data.message);
                    }

                });
        });
        applicationObject.loadData();

    });
</script>
</body>
</html>