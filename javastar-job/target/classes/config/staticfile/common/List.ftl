<#include '../tags/header.ftl'/>
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
                <li><a href="#">列表</a></li>
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
            : #TableClassInStance#Object.option.pageSize
            },

                function (data) {
                    var obj = data, content = [];
                    $("#gvResult").html(#TableClassInStance#Object.option.listHeader
                )
                    ;
                    #TableClassInStance#Object.option.count = obj.Count;
                    for (var i = 0; i < obj.data.length; i++) {

                        #ColumnsList#

                    }
                    $("#gvResult").append($("##TableClassInStance#Template").render(obj.data));
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