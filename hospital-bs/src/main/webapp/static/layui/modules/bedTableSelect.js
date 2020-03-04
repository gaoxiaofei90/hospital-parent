layui.define(['tableSelect'], function (exports) {
    "use strict";

    var MOD_NAME = 'bedTableSelect',
        $ = layui.jquery,
        tableSelect = layui.tableSelect,
        form = layui.form;

    var bedTableSelect = function () {
        this.v = '1.1.0';
    };

    bedTableSelect.prototype.getContentShowHtml = function(){
        var tableBox = '<div class="layui-fluid">\n' +
            '        <div class="layui-form layui-card-header layuiadmin-card-header-auto"\n' +
            '             lay-filter="tableSelectFilter">\n' +
            '        <div class="layui-row">\n' +
            '            <div class="layui-col-md2" style="margin-left: 5px;">\n' +
            '                <select name="building" lay-filter="building" id="building" placeholder="楼栋"\n' +
            '                        llw-select-key="assetId" llw-select-label="name"\n' +
            '                        llw-select-url="../../inst/orgConsult/queryAssetList.jspx?assetType=2">\n' +
            '                </select>\n' +
            '            </div>\n' +
            '            <div class="layui-col-md2" style="margin-left: 8px;">\n' +
            '                <select name="unit" id="unit" placeholder="单元" llw-rel-id="building" llw-rel-param="assetId" llw-select-key="assetId" llw-select-label="name"\n' +
            '                        llw-select-url="../../inst/orgConsult/queryAssetList.jspx?assetType=3">\n' +
            '                </select>\n' +
            '            </div>\n' +
            '            <div class="layui-col-md2" style="margin-left: 8px;">\n' +
            '                <select name="floor" placeholder="楼层" llw-rel-id="unit" llw-rel-param="assetId"\n' +
            '                        llw-select-key="assetId" llw-select-label="name"\n' +
            '                        llw-select-url="../../inst/orgConsult/queryAssetList.jspx?assetType=4">\n' +
            '                </select>\n' +
            '            </div>\n' +
            '            <div class="layui-col-md4" style="padding-left: 5px;">\n' +
            '                <label class="layui-form-label layui-form-title" style="width: 55px;">房间类型 </label>\n' +
            '                <div class="layui-input-block layui-input-title">\n' +
            '                    <select id="roomLevel" name="roomLevel" llw-select-key="roomTypeId" llw-select-label="roomTypeName"'+ 
            'llw-select-url="../../inst/orgRoomType/getAllRoomTypelist.jspx">' +
            '                    </select>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="layui-col-md1">\n' +
            '                <button class="layui-btn layui-btn-normal layui-btn-sm" id="bedSearchBtn" lay-submit lay-filter="bedSearchBtn" style="margin-top: -8px;">\n' +
            '                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询\n' +
            '                </button>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '        </div>\n' +
            '    </div>';

        return tableBox;
    }

    var TPL_MAIN = ['<div class="layui-tableSelect layui-unselect' +
    ' layui-form-select" >',
        '<div class="layui-select-title" id="layui-select-title-tree">',
        '<input type="text" autocomplete="off" name="bedName" lay-verify="required"  id="tableSelect-input-tree" placeholder="请选择" ' +
        'value="" class="layui-input layui-unselect">'
        ,'<i class="layui-edge"></i>','</div>',
        '</div>'];
    /**
     * 初始化表格选择器
     */
    bedTableSelect.prototype.render = function () {
        var that = this;
        var elem = $(this.elem);
        var relId = elem.attr("llw-rel-id");
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
                url: function () {
                    var url = '../../inst/orgAsset/bedList.jspx';
                    if(relId){
                        var roomType = $('#'+relId).val();
                        if (roomType && roomType != ""){
                            url = url+"?roomType="+roomType;
                        }
                    }
                    return url;
                },
                cols: [[
                    { field: 'bedInfo', title: '床位信息', width: "40%" },
                    { field: 'roomTypeName', title: '房间类型', width: "20%" },
                    { field: 'roomLevelLabel', title: '床位数', width: "15%" },
                    { field: 'bedPrice', title: '床位费', width: "25%" }
                ]]
            },
            done: function (elemment, data) {
                if (data.data.length > 0){
                    elem.val(data.data[0].assetId);
                    elem.data("bedInfo",data.data[0]);
                    elem.next().find("input").val(data.data[0].bedInfo);
                    if (relId){
                        var select = 'dd[lay-value=' + data.data[0].roomType + ']';
                        $('#'+relId).siblings("div.layui-form-select")
                            .find('dl').find(select).click();
                    }

                    elem.trigger("change");
                }
            },
            success : function (target) {
                var roomType = $('#'+relId).val();
                if (roomType && roomType != ""){
//                  var select = 'dd[lay-value=' + roomType + ']';
//                  $("#roomLevel", $(target)).siblings("div.layui-form-select")
//                      .find('dl').find(select).click();
					form.val("tableSelectFilter",{roomLevel:roomType});
                }
            }
        });

        form.on('submit(bedSearchBtn)', function(data){
            var building = data.field.building;
            var unit = data.field.unit;
            var floor = data.field.floor;
            var roomLevel = data.field.roomLevel;
            var dataField = {};
            if (roomLevel && roomLevel != ""){
                dataField.roomType = roomLevel;
            }
            if (floor && floor != ""){
                dataField.pid = floor;
            }
            else if (unit && unit != ""){
                dataField.pid = unit;
            }
            else if (building && building != ""){
                dataField.pid = building;
            }

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
    $(".llw-bed-table-select").each(function () {
        var bedTableSel = new bedTableSelect();
        bedTableSel.elem = this;
        bedTableSel.render();
    });

    exports(MOD_NAME, {});
})