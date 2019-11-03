$(function (parameters) {

    $("#exportComment").click(function () {
        var tsql = "";
        $(".Table-00").each(function () {
            var tableName = $.trim($(this).find(".t-name").text());
            tsql += "------开始导出备注：" + tableName + "\r\n";
            var tableRemark = $.trim($(this).find(".t-remark").text());
            tsql += "EXEC dbo.sp_add_comment @table = '" + tableName + "',@column = '',@value = '" + tableRemark + "';" + "\r\n";;
            $(this).find(".content").each(function () {
                var row = $(this);
                var columnName = $.trim(row.find(".c-name").text());
                var columnRemark = $.trim(row.find(".c-remark").text());
                tsql += "EXEC dbo.sp_add_comment @table = '" + tableName + "',@column = '" + columnName + "',@value = '" + columnRemark + "';" + "\r\n";;
            });
            tsql += "go\r\n"
        });
        showText(tsql);
    });

    $(".Table-00 thead").dblclick(function () {
        var table = $(this).parents("table");

        var tableName = $.trim(table.find(".t-name").text());
        //var columnName = $.trim(row.find(".c-name").text());
        var columnRemark = $.trim(table.find(".t-remark").text());
        var text = prompt("请输入备注", columnRemark);
        addComment(table.find(".t-remark"), tableName, '', text);
    });

    $(".c-remark").dblclick(function () {
        var row = $(this).parent();
        var table = $(this).parents("table");

        var tableName = $.trim(table.find(".t-name").text());
        var columnName = $.trim(row.find(".c-name").text());
        var columnRemark = $.trim(row.find(".c-remark").text());
        var text = prompt("请输入备注", columnRemark);
        addComment(row.find(".c-remark"), tableName, columnName, text);
    });

    $(".btnInsert").click(function () {
        var table = getInfoList($(this));
        var code = 'INSERT INTO ' + table.name + '(';
        $.each(table.list, function (idx, item) { code += '[' + item.name + '],'; });
        code = code.substring(0, code.length - 1);
        code += ')\r\nVALUES(';
        $.each(table.list, function (idx, item) { code += '#' + item.name + '#,'; });
        code = code.substring(0, code.length - 1);
        code += ')';
        showText(code);
    });

    $(".btnUpdate").click(function () {
        var table = getInfoList($(this));
        var code = 'UPDATE ' + table.name + '  SET ';
        var where = "";
        $.each(table.list, function (idx, item) {
            if (item.id == 0)
                code += '[' + item.name + ']=ISNULL(#' + item.name + '#,[' + item.name + ']),';
            else where += '[' + item.name + ']=#' + item.name + '# and ';
        });
        code = code.substring(0, code.length - 1);
        where = where.substring(0, where.length - 4);
        code += '\r\nWHERE ' + where;

        showText(code);
    });

    $(".btnModelCalss").click(function () {
        var table = getInfoList($(this));
        var code = '';
        var where = "";
        $.each(table.list, function (idx, item) {
            code += '/// &lt;summary&gt;\r\n';
            code += '/// ' + item.remark + '\r\n';
            code += '/// &lt;/summary&gt;\r\n';
            code += 'public ' + getType(item.type) + ' ' + item.name + '{ get; set; }\r\n';
        });
        showText(code);
    });

    $(".btnModelSet").click(function () {
        var table = getInfoList($(this));
        var code = 'var model = new ' + table.name + ' {';
        $.each(table.list, function (idx, item) {
            code += '\r\n    ' + item.name + ' = model.' + item.name + ',';
        });
        code = code.substring(0, code.length - 1);
        code += "\r\n}";

        showText(code);
        //layer.open({
        //    type: 1,
        //    title: false,
        //    area: ['70%', '90%'],
        //    closeBtn: 1,
        //    shadeClose: true,
        //    skin: 'yourclass',
        //    content: code
        //});
        //$(".layui-layer.layui-anim.layui-layer-page.yourclass").css({ left: 20, top: 10, fontSize: '12px' });
        //$(".layui-layer.layui-anim.layui-layer-page.yourclass .layui-layer-content").css({ height: '100%' })
    });

    $(".btnSelect").click(function () {
        var table = getInfoList($(this));
        var code = 'SELECT ';
        var len = 0;
        $.each(table.list, function (idx, item) {
            len += item.name.length + 5;
            if (len >= 125) {
                code += '\r\n    '
                len = 0;
            };
            code += 't.[' + item.name + '],';
        });
        code = code.substring(0, code.length - 1);
        code += '\r\nFROM ' + table.name + ' as t WITH(NOLOCK)';
        showText(code);
    });

    $('.t-name').click(function () {
        selectedMe(this);
    });

    $('#dynamicModel').click(function () {
        $('#divDynamicModel').toggle();
    });

    $('#btnCreateDynamicModel').click(function () {
        var param = {
            ApiNo: 1000,
            c: getQueryString('c'),
            d: getQueryString('d'),
            sql: encodeURIComponent($('#txtSql').val())
        };
        $.post("/Api.aspx", param, function (rst) {
            try {
                var list = $.parseJSON(rst);
                var code = '';
                $.each(list, function (idx, item) {
                    code += '/// &lt;summary&gt;\r\n';
                    code += '/// \r\n';
                    code += '/// &lt;/summary&gt;\r\n';
                    code += 'public ' + getType(item.DataType.toLowerCase()) + ' ' + item.Name + '{ get; set; }\r\n';
                });
                showText(code);
            } catch (e) {
                showText('出错了:' + rst);
            }
        });
    });

    function addComment(column, tableName, columnName, columnRemark) {
        if (columnRemark) {
            var param = {
                ApiNo: 1001,
                c: getQueryString('c'),
                d: getQueryString('d'),
                tableName: tableName,
                columnName: columnName,
                columnRemark: columnRemark,
            };
            $.post("/Api.aspx", param, function (rst) {
                try {
                    if (rst) {
                        column.text(columnRemark)
                    }
                } catch (e) {
                    alert("更改失败");
                }
            });
        }
    }

    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return r[2]; return null;
    }

    function getInfoList(btn) {
        var list = [];
        var tbs = btn.parents("table");
        tbs.find("tr.content").each(function (idx, item) {
            var tr = $(item);
            var name = $.trim(tr.find(".c-name").text());
            var type = $.trim(tr.find(".c-type").text());
            var dft = $.trim(tr.find(".c-default").text());
            var id = $.trim(tr.find(".c-id").val());
            var remark = $.trim(tr.find(".c-remark").text());
            list.push({ name: name, type: type, dft: dft, id: id, remark: remark });
        });
        var tName = tbs.find(".t-name").text();
        var tRemark = tbs.find(".t-remark").text();
        return { name: tName, remark: tRemark, list: list };
    }
    function getType(dbType) {
        if (dbType.indexOf('decimal') >= 0 || dbType.indexOf('numeric') >= 0 || dbType.indexOf('money') >= 0) return 'decimal';
        if (dbType.indexOf('real') >= 0) return 'float';
        if (dbType.indexOf('bit') >= 0) return 'bool';
        if (dbType.indexOf('boolean') >= 0) return 'bool';
        if (dbType.indexOf('bigint') >= 0) return 'Uid';
        if (dbType.indexOf('binyint') >= 0) return 'int';
        if (dbType.indexOf('byte') >= 0) return 'byte';
        if (dbType.indexOf('datetime') >= 0 || dbType.indexOf('timestamp') >= 0 || dbType.indexOf('smalldatetime') >= 0) return 'DateTime';
        if (dbType.indexOf('int') >= 0) return 'int';
        return 'string';
    }
    function showText(text) {
        var content = '<textarea style="width:100%;height:100%;padding:10px" ondblclick="selectedMe(this)">' + text + '</textarea>';
        layer.open({
            type: 1,
            title: '双击全选代码',
            area: ['60%', '70%'],
            closeBtn: 1,
            shadeClose: true,
            skin: 'yourclass',
            content: content
        });
        $(".layui-layer.layui-anim.layui-layer-page.yourclass").css({ left: '10%', top: '10%', fontSize: '12px' });
        $(".layui-layer.layui-anim.layui-layer-page.yourclass .layui-layer-content").css({ height: '100%' })
    }
});

function selectedMe(dom) {
    if (document.body.createTextRange) {
        var range = document.body.createTextRange();
        range.moveToElementText(dom);
        range.select();
    } else if (window.getSelection) {
        var selection = window.getSelection();
        var range = document.createRange();
        range.selectNodeContents(dom);
        selection.removeAllRanges();
        selection.addRange(range);
    }
}