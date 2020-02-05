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

<div id="bodyContaner">
    <div class="container-fluid">
        <div class="row-fluid" style="margin-top:25px;">
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
                    <li><a href="#">详情</a></li>
                </ul>
            </div>
            <h3 class="page-title"></h3>
        </div>

        <div class="row-fluid">
            <div class="span12">
                <div class="portlet box grey">
                    <div class="portlet-title">
                        <div class="caption"><i class="icon-cogs"></i>详情</div>
                        <div class="tools">
                            <a href="javascript:;" class="collapse"></a>
                            <a href="#portlet-config" data-toggle="modal" class="config"></a>
                            <a href="javascript:;" class="reload"></a>
                            <a href="javascript:;" class="remove"></a>
                        </div>
                    </div>
                    <div class="portlet-body table-responsive">
                        <div class="row-fluid sortable ui-sortable">
                            <div class="box-content">

                                <div class="form-horizontal">


                                    #DetailPageModel#
                                    <div class="control-group">
                                        <label class="control-label" for="typeahead"></label>
                                        <div class="controls">
                                            <button type="button" id="btnSave" class="btn blue"><i class="icon-ok"></i>提交
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="none">
        <form id="frmAttachment" action="/file/upload" enctype="multipart/form-data"
              method="post" name="frmAttachment" target="uploadTarget">
            <input type="file" style="position: absolute; left: -999em; top: -999em;" id="attachFile"
                   name="attachFile"/>
            <iframe id="uploadTarget" name="uploadTarget"
                    style="position: absolute; left: -999em; top: -999em;"></iframe>
        </form>
    </div>
</div>
<#include '../tags/footer.ftl'/>
<script src="../static/scripts/ueditor/ueditor.config.js"></script>
<script src="../static/scripts/ueditor/ueditor.all.min.js"></script>
<link type="text/css" href="../static/content/jquery.datetimepicker.css" rel="stylesheet" />
<script src="../static/scripts/jquery.datetimepicker.js"></script>
<script type="text/javascript">


    var uploadAttachment = {
        position: 0,
        attachments: [],
        checkImageURL: function () {
            return (url.match(/\.(jpeg|jpg|gif|png|bmp)$/i) != null);
        },
        up: function () {
            if ($("#attachFile").val().length < 4) {
                alert('请选择上传文件!');
                return;
            }
            $frmAttachment = $("#frmAttachment");
            $frmAttachment.submit();
        },
        complete: function () {
            document.getElementById("frmAttachment").reset();
            var result = $.parseJSON($("#uploadTarget").contents().find("pre").first().text());

            if (uploadAttachment.position == 0) {
                $("#imgLogo").attr("src", result.Url);
            }
            else {
                $("#imgIcon").attr("src", result.Url);
            }

        },

        init: function () {
            $("#uploadTarget").on("load", function () {
                uploadAttachment.complete();
            });
            var $input = $('#attachFile');
            var ie = !-[1,];
            if (ie) {
                $input.click(function (event) {
                    setTimeout(function () {
                        uploadAttachment.up();
                    }, 0);
                });
            } else {
                $input.change(uploadAttachment.up);
            }
        }
    };

    $(document).ready(function () {
        #EditorContent#
        uploadAttachment.init();
        $("#btnUpLogo").click(function () {
            uploadAttachment.position = 0;
            $("#attachFile").click();
        });
        $("#btnIcon").click(function () {
            uploadAttachment.position = 1;
            $("#attachFile").click();
        });

        $("#btnSave").click(function () {
            var data = {};
                 #DetailScripts#
                $.post("/#TableClass#/save", {
                    datas: JSON.stringify(data)
                }, function (data) {
                    if (data.code === 0) {
                        alert('操作成功!');
                        window.location.href = '/#TableClassToLower#/list';
                    } else {
                        alert('操作失败!' + data.message);
                    }
                });

        });

    });
</script>
</body>
</html>