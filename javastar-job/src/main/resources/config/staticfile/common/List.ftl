@{
    Layout = "~/Views/Shared/_Layout.cshtml";
}
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <h3 class="page-title"></h3>
            <ul class="breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="/home/">首页</a>
                    <i class="icon-angle-right"></i>
                </li>
                <li>
                    <a href="/store/category">应用分类</a>
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
                        <a id="btnAdd" href="/store/category/detail" class="icon-plus"></a>
                        <a href="javascript:;" class="reload"></a>
                        <a href="javascript:;" class="remove"></a>
                    </div>
                </div>
                <div class="portlet-body table-responsive">
                    <div class="form-inline">
                        <input type="text" id="txtKeyword" class="m-wrap medium">&nbsp;<input id="btnSearch" class="btn blue" type="button" value="确定" />
                    </div>
                    <table class="table table-striped" id="gvResult"></table>
                    <div id="Pager"></div>
                </div>
            </div>
        </div>
    </div>

    <!--/span-->
</div>

@section scripts{
    <script type="text/js-render" id="roleTemplate">
        <tr>
            <td style="background-color:{{:ColorInfo}};"></td>
            <td>{{:Name}}</td>
            <td><img src="{{:IconUrl}}" alt="" style="height:50px;" class="plus-cursor" data-image="{{:IconUrl}}" data-title="" data-caption="" /></td>
            @*<td><img src="{{:LogoUrl}}" alt="" style="height:50px;" class="plus-cursor" data-image="{{:LogoUrl}}" data-title="" data-caption="" /></td>*@
            <td>{{:Ar}}</td>
            <td>{{:IsDelete}}</td>
            <td>
                <span class="label label-info"><a href="/store/category/detail?id={{:Id}}" onclick="">编辑</a></span>
            </td>
        </tr>
    </script>
    <script type="text/javascript">

        var categoryObject = {
            option: {
                pageIndex: 1,
                pageSize: 15,
                pageCount: 0,
                count: 0,
                loading: '<tr><td colspan="6"><div><img src="/content/images/loading.gif" alt="Loading" /></div></td></tr>',
                listHeader: '<thead>' +
                    '<tr role="row">' +
                        '<th >配色</th>' +
                    '<th >名字</th>' +
                    '<th >分类图标</th>' +
                    /*'<th >广告图</th>' +*/
                    '<th >权重</th>' +
                    '<th >是否有效</th>' +
                    '<th >操作</th>' +
                    '</tr>' +
                    '</thead>',
            },
            bindMethod: function () {
                $("#btnSearch").click(function () {
                    categoryObject.option.pageIndex = 1;
                    categoryObject.loadData();
                });

            },
            remove: function (id) {
                //  if (confirm('确认 改变角色状态吗？')) {
                $.post("/Store/Category/ChangeStatues", { id: id }, function (data) {
                    if (data.Code == 0) {
                        //      categoryObject.loadData();
                    } else {
                        alert('操作失败!');
                    }
                });
                //  }
            },
            changeAr: function (id, obj) {
                $.post("/Store/Category/ChangeAr", { id: id, ar: obj }, function (data) {
                    if (data.Code == 0) {
                        //     applicationObject.loadData();
                    }
                });

            },
            loadData: function () {
                var keyword = $("#txtKeyword").val();
                $("#gvResult").html(categoryObject.option.loading);
                $.getJSON("/Store/Category/GetPageList", { pageIndex: categoryObject.option.pageIndex, pageSize: categoryObject.option.pageSize }, function (data) {
                    var obj = data, content = [];
                    $("#gvResult").html(categoryObject.option.listHeader);
                    categoryObject.option.count = obj.Count;
                    for (var i = 0; i < obj.Items.length; i++) {
                        obj.Items[i].IsDelete = '<label class="checkbox"><input type="checkbox"' + (obj.Items[i].IsDelete ? '' : ' checked="checked"') + ' onclick="categoryObject.remove(' + obj.Items[i].Id + ')" /></label>';
                        obj.Items[i].Ar = '<span class="isAr" data-id="' + obj.Items[i].Id + '" id="ar' + obj.Items[i].Id + '">' + obj.Items[i].Ar + '</span>';

                    }
                    $("#gvResult").append($("#roleTemplate").render(obj.Items));
                    $.each($('.isAr'), function () {
                        new Medium({
                            element: document.getElementById(this.id),
                            mode: Medium.inlineMode,
                            maxLength: 25,
                            placeholder: '...'
                        });
                    });
                    $('.isAr').on('blur', function () {
                        categoryObject.changeAr($(this).attr('data-id'), $(this).html());
                    });
                    App.handleUniform();
                    //pager
                    var pager = new pagination.init('Pager');
                    pager.itemCount = categoryObject.option.count;
                    pager.size = categoryObject.option.pageSize;
                    pager.index = categoryObject.option.pageIndex;
                    pager.onclick = function (indexs) {
                        categoryObject.option.pageIndex = indexs;
                        categoryObject.loadData();
                    };
                    pager.render();

                    var elements = document.querySelectorAll('.plus-cursor');
                    Intense(elements);
                });
            }
        };

        $(function () {

            categoryObject.loadData();
        });
    </script>
}