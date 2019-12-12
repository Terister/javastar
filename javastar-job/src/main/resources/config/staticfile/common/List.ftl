<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>wolf</title>
<#include '../tags/header.ftl'/>
</head>

<body class="page-header-fixed">
<!-- BEGIN HEADER -->

<div class="header navbar navbar-inverse navbar-fixed-top">

    <!-- BEGIN TOP NAVIGATION BAR -->

    <div class="navbar-inner">

        <div class="container-fluid">

            <!-- BEGIN LOGO -->

            <a class="brand" href="/">

                <img src="../static/content/media/image/m-logo.png" alt="logo"/>
            </a>

            <!-- END LOGO -->
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->

            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

                <img src="../static/content/media/image/menu-toggler.png" alt=""/>
            </a>

            <!-- END RESPONSIVE MENU TOGGLER -->
            <!-- BEGIN TOP NAVIGATION MENU -->

            <ul class="nav pull-right">

                <!-- BEGIN NOTIFICATION DROPDOWN -->

                <!-- END TODO DROPDOWN -->
                <!-- BEGIN USER LOGIN DROPDOWN -->

                <li class="dropdown user">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <img alt="" src="../static/content/media/image/avatar.png"
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
<#include '../tags/left.ftl' />
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
        <div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <h3 class="page-title"></h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="/#HomeLink#">首页</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li>
                    <a href="#ListLink#">列表</a>
                    <i class="icon-angle-right"></i>
                </li>
            </ul>
        </div>
    </div>

    <div class="row-fluid">
        <div class="span12">
            <div class="portlet box grey">
                <div class="portlet-title">
                    <div class="caption"><i class="icon-cogs"></i>列表</div>
                    <div class="tools">
                        <a href="javascript:;" class="collapse"></a>
                        <a id="btnAdd" href="/#DetailLink#" class="icon-plus"></a>
                        <a href="javascript:;" class="reload"></a>
                        <a href="javascript:;" class="remove"></a>
                    </div>
                </div>
                <div class="portlet-body table-responsive">
                    <div class="form-inline">
                        <input type="text" id="txtKeyword" class="m-wrap medium">&nbsp;<input id="btnSearch"
                                                                                              class="btn blue"
                                                                                              type="button" value="确定"/>
                    </div>
                    <table class="table table-striped" id="gvResult"></table>
                    <div id="Pager"></div>
                </div>
            </div>
        </div>
    </div>

    <!--/span-->
</div>
    </div>
</div>

<#include '../tags/footer.ftl'/>

    <script type="text/js-render" id="#TableClassInStance#Template">
        #TemplateRender#


    </script>
    <script type="text/javascript">

        var #TableClassInStance#Object = {
            option: {
                pageIndex: 1,
                pageSize: 15,
                pageCount: 0,
                count: 0,
                loading: '<tr><td colspan="6"><div><img src="../static/content/images/loading.gif" alt="Loading" /></div></td></tr>',
              #ListHeader#
            },
            bindMethod: function () {
                $("#btnSearch").click(function () {
                #TableClassInStance#Object.option.pageIndex = 1;
                    #TableClassInStance#Object.loadData();
                });

            },
            remove: function (id) {
                if (confirm('确认 改变角色状态吗？')) {
                    $.post("/#TableClass#/changeStatues", {id: id}, function (data) {
                        if (data.Code == 0) {
                             #TableClassInStance#Object.loadData();
                        } else {
                            alert('操作失败!');
                        }
                    });
                }
            },
            changeAr: function (id, obj) {
                $.post("/#TableClass#/changeAr", {id: id, ar: obj}, function (data) {
                    if (data.Code == 0) {
                        #TableClassInStance#Object.loadData();
                    }
                });

            },
            loadData: function () {
                var keyword = $("#txtKeyword").val();
                $("#gvResult").html(#TableClassInStance#Object.option.loading
            )
                ;
                $.getJSON("/#TableClass#/getPageList", {pageIndex: #TableClassInStance#Object.option.pageIndex, pageSize
            : #TableClassInStance#Object.option.pageSize,key:""
            },

                function (data) {
                    var obj = data.data, content = [];
                    $("#gvResult").html(#TableClassInStance#Object.option.listHeader);
                    #TableClassInStance#Object.option.count = obj.count;
                    for (var i = 0; i < obj.items.length; i++) {

                        #ColumnsList#

                    }
                    $("#gvResult").append($("##TableClassInStance#Template").render(obj.items));
                    $.each($('.isAr'), function () {
                        new Medium({
                            element: document.getElementById(this.id),
                            mode: Medium.inlineMode,
                            maxLength: 25,
                            placeholder: '...'
                        });
                    });
                    $('.isAr').on('blur', function () {
                    #TableClassInStance#Object.changeAr($(this).attr('data-id'), $(this).html());
                    });
                    App.handleUniform();
                    <!--pager-->
                    var pager = new pagination.init('Pager');
                    pager.itemCount = #TableClassInStance#Object.option.count;
                    pager.size = #TableClassInStance#Object.option.pageSize;
                    pager.index = #TableClassInStance#Object.option.pageIndex;
                    pager.onclick = function (indexs) {
                    #TableClassInStance#Object.option.pageIndex = indexs;
                        #TableClassInStance#Object.loadData();
                    };
                    pager.render();

                    var elements = document.querySelectorAll('.plus-cursor');
                    Intense(elements);
                }

            )
                ;
            }
        };

        $(function () {

        #TableClassInStance#Object.loadData();
        });
    </script>
</body>
</html>