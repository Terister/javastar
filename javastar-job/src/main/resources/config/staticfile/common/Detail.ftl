﻿<#include '../tags/header.ftl'/>
<div id="bodyContaner"></div>
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
                                <div class="control-group">
                                    <label class="control-label" for="typeahead">分类编号： </label>
                                    <div class="controls">
                                        <input id="txtId" type="text" class="m-wrap medium typeahead"
                                               value="" data-provide="typeahead"
                                               data-items="4" readonly="readonly"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="typeahead">父级分类： </label>
                                    <div class="controls">
                                        <select id="selCategory" class="m-wrap medium">
                                            <option value="0">顶级分类</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="typeahead">分类名称： </label>
                                    <div class="controls">
                                        <input id="txtName" type="text" class="span6  medium typeahead"
                                               value="" data-provide="typeahead"
                                        data-items="4" />
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="typeahead">英文名称： </label>
                                    <div class="controls">
                                        <input id="txtEnglishName" type="text" class="span6  medium typeahead"
                                               value="" data-provide="typeahead"
                                        data-items="4" />
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="typeahead">分类颜色： </label>
                                    <div class="controls">
                                        <input id="txtColor" type="text" class="m-wrap  medium typeahead"
                                               style="background-color:;"
                                        value="" data-provide="typeahead"
                                        data-items="4" />
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="typeahead">广告图： </label>
                                    <div class="controls">
                                        <button type="button" id="btnUpLogo" class="btn  green"><i class="icon-ok"></i>上传Logo
                                        </button>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="typeahead"> </label>
                                    <div class="controls">
                                        <img src="" id="imgLogo"
                                        style="height:120px;" />
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="typeahead">分类图标： </label>
                                    <div class="controls">
                                        <button type="button" id="btnIcon" class="btn  green"><i class="icon-ok"></i>上传Icon
                                        </button>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="typeahead"> </label>
                                    <div class="controls">
                                        <img src="" id="imgIcon"
                                        style="width:80px;" />
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="typeahead">描述： </label>
                                    <div class="controls">
                                        <textarea id="txtDescribe" class="span6  medium "></textarea>
                                    </div>
                                </div>

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
    <input type="file" style="position: absolute; left: -999em; top: -999em;" id="attachFile" name="attachFile"/>
    <iframe id="uploadTarget" name="uploadTarget" style="position: absolute; left: -999em; top: -999em;"></iframe>
    </form>
</div>

<#include '../tags/footer.ftl'/>

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
            uploadAttachment.init();
            $("#btnUpLogo").click(function () {
                uploadAttachment.position = 0;
                $("#attachFile").click();
            });
            $("#btnIcon").click(function () {
                uploadAttachment.position = 1;
                $("#attachFile").click();
            });

         /*   if (parseInt(parentId) > 0) {
                $("#selCategory").val(parentId);
            }*/
            $('#txtColor').on('keyup', function () {
                $(this).css("background-color", $(this).val());
            });

            $("#btnSave").click(function () {
                var id = $("#txtId").val();
                 #DetailScripts#
                var data={};
                data.id=$("#txtId").val();
                data.id=$("#txtId").val($("#selCategory").find("option:selected").val());
                data.logo = $("#imgLogo").attr("src");
                $.post("/#TableClass#/save", {
                    datas:data
                }, function (data) {
                    if (data === 0) {
                        alert('操作成功!');
                        window.location.href = '/#TableClass#List';
                    } else {
                        alert('操作失败!');
                    }
                });

            });

        });
    </script>