layui.define(['tableSelect'], function (exports) {
    "use strict";

    var MOD_NAME = 'peopleTableSelect',
        $ = layui.jquery,
        tableSelect = layui.tableSelect,
        form = layui.form;

    var peopleTableSelect = function () {
        this.v = '1.1.0';
    };

    peopleTableSelect.prototype.getContentShowHtml = function(){
        var tableBox =  '<div class="layui-fluid">' +
        '        <div class="layui-form layui-card-header layuiadmin-card-header-auto"' +
        '             lay-filter="tableSelectFilter">' +
        '        <div class="layui-row">' +
        '            <div class="layui-col-md6" style="padding-top: 3px;">' +
            '                <label class="layui-form-label layui-form-title" ' +
            'style="width: 50px">护理等级</label>' +
            '                <div class="layui-input-block layui-input-title">' +
            '                    <select id="careLevel" name="nurseLevel" llw-select-key="id" '
              +'llw-select-label="name" llw-select-url="../../inst/orgConfig/nurseLevelList.jspx">' +
            '                    </select>' +
            '                </div>' +
            '            </div>' +
        '            <div class="layui-col-md6"><input style="display:inline-block;width:190px;height:30px;vertical-align:middle;margin-right:-1px;border: 1px solid #C9C9C9;" type="text" name="name" placeholder="入院编号/长者姓名" autocomplete="off" class="layui-input"><button class="layui-btn layui-btn-sm layui-btn-primary tableSelect_btn_search" lay-submit lay-filter="peopleSearchBtn"><i class="layui-icon layui-icon-search"></i>搜索</button>'+
        '        </div>' +
        '        </div>' +
        '        </div>' +
        '    </div>';

        return tableBox;
    }

    var TPL_MAIN = ['<div class="layui-tableSelect layui-unselect' +
    ' layui-form-select" >',
        '<div class="layui-select-title" id="layui-select-title-tree">',
        '<input type="text" name="elderName" lay-verify="required" id="tableSelect-input-tree" autocomplete="off" placeholder="请选择" ' +
        'value="" class="layui-input layui-unselect">'
        ,'<i class="layui-edge"></i>','</div>',
        '</div>'];
    /**
     * 初始化表格选择器
     */
    peopleTableSelect.prototype.render = function () {
        var that = this;
        var elem = $(this.elem);
        var relId = elem.attr("llw-rel-id");
        var onSelected = elem.attr("onSelected");
        elem.hide();
        var nextCls = elem.next().attr("class");
        if (!nextCls || nextCls.indexOf("layui-tableSelect") == -1){
            elem.after(TPL_MAIN.join(""));
        }

        $(".layui-select-title", elem.next()).click(function(){
            if ($(this).parent().hasClass("layui-form-selected")){
                $(this).parent().removeClass("layui-form-selected");
            }
            else{
                $(this).parent().addClass("layui-form-selected");
            }
        });
        $(document.body).bind("click", function(e){
            if ($(e.target).closest(".layui-form-selected").length == 0){
                elem.next().removeClass("layui-form-selected");
            }
        });

        var tableSelObj = tableSelect.render({
            elem:elem.next(),
            content : that.getContentShowHtml(),
            table: {
                url: '../../inst/orgAdmission/choose/list.jspx?admissionStatus=3',
                cols: [[
                    { field: 'admissionNo', title: '入院编号', width: 150 },
                    { field: 'elderName', title: '姓名', width: 80 },
                    { field: 'sexLabel', title: '性别', width: 60 },
                    { field: 'age', title: '年龄', width: 60 },
                    { field: 'nurseLevelLabel', title: '护理等级', width: 80 },
                    { field: 'bedInfo', title: '床位信息', width: 175 },
                ]]
            },
            done: function (elemment, data) {
                if (data.data.length > 0){
                    elem.val(data.data[0].admissionId);
                    elem.next().find("input").val(data.data[0].elderName);
                }

                if (typeof window[onSelected] === "function"){
                    window[onSelected].call(that, data.data[0]);
                }
            }
        });

        form.on('submit(peopleSearchBtn)', function(data){
            var dataField = {};
            dataField.keywords = data.field.name;
            dataField.nurseLevel = data.field.nurseLevel;
            tableSelObj.table.reload({
                where: dataField, page: {curr: 1}
            });
            return false;
        });
    }

    var tableSelectCss = ".layui-card-header .layui-icon{position:relative;left:0px;" +
        "margin-top: 5px;top: 0px;}.layui-form-selected .layui-edge{margin-top:-9px;-webkit-transform:rotate(180deg);transform:rotate(180deg);}" ;
    var $head = $('head'),
        tableSelectStyle = $head.find('style[tableSelect]');
    if (tableSelectStyle.length === 0) {
        $head.append($('<style type="text/css" tableSelect>').append(tableSelectCss));
    }

    //自动完成渲染
    $(".llw-people-table-select").each(function () {
        var peopleTableSel = new peopleTableSelect();
        peopleTableSel.elem = this;
        peopleTableSel.render();
    });

    exports(MOD_NAME, {});
})