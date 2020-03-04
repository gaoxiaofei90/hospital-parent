layui.define(['tableSelect','llw_treeSelect'], function (exports) {
    "use strict";
    var llw_treeSelect = layui.llw_treeSelect;

    var MOD_NAME = 'employTableSelect',
        $ = layui.jquery,
        tableSelect = layui.tableSelect,
        form = layui.form;

    var employTableSelect = function () {
        this.v = '1.1.0';
    };

    var TPL_MAIN = ['<div class="layui-tableSelect layui-unselect' +
    ' layui-form-select" >',
        '<div class="layui-select-title" id="layui-select-title-tree">',
        '<input type="text" id="tableSelect-input-tree" placeholder="请选择员工" ' +
        'value="" class="layui-input layui-unselect" autocomplete="off">'
        ,'<i class="layui-edge"></i>','</div>',
        '</div>'];

    var SEARCH_CONTAINER = '<div >\n' +
    '    <div class="layui-inline">\n' +
    '        <label class="layui-title-lable">所属部门</label>\n' +
    '        <div class="layui-select-mete" style="width: 110px;">\n' +
    '            <input type="text" name="departmentId" id="departmentId" placeholder="所属部门"\n' +
    '                   llw-tree-id="departmentId" llw-tree-pid="pid" llw-tree-label="name"\n' +
    '                   llw-tree-click="deptIdSelected"\n' +
    '                   class="llw_treeSelect" llw-tree-url="../../inst/orgNurseGroup/queryDeptList.jspx">\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="layui-inline">\n' +
    '        <label class="layui-title-lable">员工姓名</label>\n' +
    '        <div class="layui-select-mete" style="width: 110px;">\n' +
    '            <input type="text" name="name" id="name" placeholder="员工姓名" class="layui-input">\n' +
    '        </div>\n' +
    '    </div>\n' +
    '<div class="layui-inline">\n' +
    '                                    <button class="layui-btn layui-btn-normal" id="admissionSearch" lay-submit lay-filter="tableSelect_btn_search">\n' +
    '                                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询\n' +
    '                                    </button>\n' +
    '                                    <button class="layui-btn layui-btn-normal llw-reset">\n' +
    '                                        重置\n' +
    '                                    </button>' +
        '<button class="layui-btn layui-btn-normal tableSelect_btn_select" id="selBtn">选择</button>' +
    '                                </div>' +
    '    </div>';

    /**
     * 初始化表格选择器
     */
    employTableSelect.prototype.init = function () {
        var that = this;
        var elem = $(this.elem);
        var onSelected = elem.attr("onSelected");
        var selected= elem.attr("ts-selected");
        var value = elem.val();
        elem.hide();
        var nextCls = elem.next().attr("class");
        if (!nextCls || nextCls.indexOf("layui-tableSelect") == -1){
            elem.after(TPL_MAIN.join(""));
        }
        //设置回显的值
        elem.next().attr("ts-selected",selected);
        if(value){
            elem.next().find("input").val(value);
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
            content : SEARCH_CONTAINER,
            checkedKey: 'employId',
            searchKey:'name',
            searchPlaceholder:'请输入员工姓名',
            table: {
                url: '../../inst/orgEmployee/choose/list.jspx',
                cols: [[
                    { type:'checkbox', width: 50 },
                    { field: 'employeeNo', title: '工号', width: 110 },
                    { field: 'name', title: '员工姓名', width: 120 },
                    { field: 'departmentLabel', title: '所属部门', width: 150 },
                    { field: 'postLabel', title: '岗位名称', width: 170 }
                ]]
            },
            done: function (elemment, data) {
                var selectedId = [];
                if (data.data.length > 0){
                    var selectedName = [];
                    layui.each(data.data, function (index, item) {
                        selectedId.push(item.employId);
                        selectedName.push(item.name);
                    });
                    if (typeof that.callback === "function"){
                        that.callback.call(that,selectedId,selectedName);
                    }
                    else {
                        elem.val(selectedId.join(","));
                        elem.next().find("input").val(selectedName.join(","));
                    }
                }

                if (typeof window[onSelected] === "function"){
                    window[onSelected].call(that, selectedId.join(","));
                }
            },
            success : function () {
                llw_treeSelect.init();
                layui.util.reset();
            }
        });
    }

    var tableSelectCss = ".layui-card-header .layui-icon{position:relative;left:0px;" +
        "margin-top: 5px;top: 0px;}.layui-form-selected .layui-edge{margin-top:-9px;-webkit-transform:rotate(180deg);transform:rotate(180deg);}" ;
    var $head = $('head'),
        tableSelectStyle = $head.find('style[tableSelect]');
    if (tableSelectStyle.length === 0) {
        $head.append($('<style type="text/css" tableSelect>').append(tableSelectCss));
    }

    var obj = {
        render:function(target, callback){
            var employTableSel = new employTableSelect();
            employTableSel.elem = target;
            employTableSel.callback = callback;
            employTableSel.init();
        }
    };
    exports(MOD_NAME, obj);
})