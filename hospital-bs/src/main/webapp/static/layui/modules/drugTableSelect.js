layui.define(['tableSelect'], function (exports) {
    "use strict";

    var MOD_NAME = 'drugTableSelect',
        $ = layui.jquery,
        tableSelect = layui.tableSelect,
        form = layui.form;

    var drugTableSelect = function () {
        this.v = '1.1.0';
    };

    drugTableSelect.prototype.getContentShowHtml = function(){
        var tableBox =  '<div class="layui-fluid">' +
        '        <div class="layui-form layui-card-header layuiadmin-card-header-auto"' +
        '             lay-filter="tableSelectFilter">' +
        '        <div class="layui-row">' +
        '            <div class="layui-col-md6"><input style="display:inline-block;width:190px;height:30px;vertical-align:middle;margin-right:-1px;border: 1px solid #C9C9C9;" type="text" name="name" placeholder="长者姓名/药品名称" autocomplete="off" class="layui-input"><button class="layui-btn layui-btn-sm layui-btn-primary tableSelect_btn_search" lay-submit lay-filter="drugSearchBtn"><i class="layui-icon layui-icon-search"></i>搜索</button>'+
        '        </div>' +
        '        </div>' +
        '        </div>' +
        '    </div>';

        return tableBox;
    }

    var TPL_MAIN = ['<div class="layui-tableSelect layui-unselect' +
    ' layui-form-select" >',
        '<div class="layui-select-title" id="layui-select-title-tree">',
        '<input type="text" id="tableSelect-input-tree" placeholder="请选择" ' +
        'value="" class="layui-input layui-unselect" autocomplete="off">'
        ,'<i class="layui-edge"></i>','</div>',
        '</div>'];
    /**
     * 初始化表格选择器
     */
    drugTableSelect.prototype.render = function () {
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
                url: '../../inst/orgDrugs/list.jspx',
                cols: [[
                    {field:'admissionNo',title:'入院编号'},
                    {field:'elderName',title:'长者姓名'},
                    {field:'bedInfo',title:'床位信息'},
                    {field:'drugName',title:'药品名称'},
                    {field:'restAmount',title:'库存数量'},
                    {field:'startDate',title:'用药开始时间'},
                    {field:'endDate',title:'用药结束时间'}
                ]]
            },
            done: function (elemment, data) {
                if (data.data.length > 0){
                    elem.val(data.data[0].drugId);
                    elem.next().find("input").val(data.data[0].drugName);
                }

                if (typeof window[onSelected] === "function"){
                    window[onSelected].call(that, data.data[0]);
                }
            }
        });

        form.on('submit(drugSearchBtn)', function(data){
            var dataField = {};
            dataField.elderName = data.field.elderName;
            dataField.drugName = data.field.drugName;
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
    $(".llw-drug-table-select").each(function () {
        var drugTableSel = new drugTableSelect();
        drugTableSel.elem = this;
        drugTableSel.render();
    });

    exports(MOD_NAME, {});
})