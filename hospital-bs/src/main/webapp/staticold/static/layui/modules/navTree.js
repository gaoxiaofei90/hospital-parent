layui.define(['util','laytpl'], function (exports) {
    "use strict";

    var MOD_NAME = 'navTree',
        $ = layui.jquery,
        laytpl = layui.laytpl,
        form = layui.form;

    var navTreeCls = function () {
        this.v = '1.0.0';
    };

    navTreeCls.prototype.render = function () {
        var that = this;
        this.createTableContainer();
        this.createTableContent();
   };

    var TABLE_CONTAINER_TPL = '<table cellspacing="0" cellpadding="0" ' +
    'border="0" style="margin: 0px 0px;" class="layui-table" >' +
    '<thead><tr><th data-key="1-0-0" class="">' +
    '<div class="layui-table-cell laytable-cell-1-0-0">' +
    '<span>{{d.title}}</span></div></th></tr></thead>' +
    '<tbody class="table-content"></tbody></table>';

    var TR_TPL = '<tr data-key="{{d.treeid}}" ' +
        'data-id="{{d.id}}" class="">' +
        '<td data-field="name" class="">' +
        '<div class="layui-table-cell laytable-cell-1-0-0"><i class="nav-icon {{d.icon}}"></i>' +
        '<span style="margin-left: 25px;">{{d.name}}</span></div>{{# if(d.hasChild){}}<i class="layui-edge layui-icon layui-icon-next"></i>{{#}}}</td></tr>';

    navTreeCls.prototype.createTableContainer = function(){
        this.elem.append(laytpl(TABLE_CONTAINER_TPL).render(this.options));
        this.TableContainer = this.elem.find("tbody");
    };

    navTreeCls.prototype.createTableContent = function(){
        var that = this;
        layui.util.ajax({
            url : that.options.url,
            success : function(res) {
                if (res.respData) {
                    var treeObj = new layui.util.TreeWapper(res.respData,
                        that.options.paramid, that.options.paramParentId).toTree();
                    that.createTable(treeObj, 0, 0+"_");
                }
            }
        });
    };

    navTreeCls.prototype.createTable = function(treeObj,layer, pid){
        var that = this;
        for (var i in treeObj){
            var item = treeObj[i];
            item.treeid = item[that.options.paramid]+"_";
            item.hasChild = item.children && item.children.length > 0;
            item.treeid = pid + item.treeid;
            item.id = item[that.options.paramid];
            if (item.assetType == 2){
                item.icon = "icon-building";
            }
            else if (item.assetType == 3){
                item.icon = "icon-unit";
            }
            else if (item.assetType == 4){
                item.icon = "icon-floor";
            }
            var trObj = $(laytpl(TR_TPL).render(item));
            trObj.data("itemDatas", item);
            trObj.on("click", function(){
                that.addEvents($(this));
            });
            that.TableContainer.append(trObj);
            $("td[data-field=name]", trObj).css("padding-left",layer*30);

            if (item.hasChild){
                that.createTable(item.children, layer+1, item.treeid);
            }

        }
    };

    navTreeCls.prototype.addEvents = function(target){
        var that = this;
        var curSelected = $(".tr_selected", that.TableContainer);
        //之前显示的隐藏
        var selDataKey = curSelected.attr("data-key");
        $("tr[data-key^='"+selDataKey+"']", that.TableContainer).not(curSelected).slideDown();
        if (target.hasClass("tr_selected")){
            target.removeClass("tr_selected");
            return;
        }

        target.addClass("tr_selected").siblings(".tr_selected")
            .removeClass("tr_selected");
        selDataKey = target.attr("data-key");
        $("tr[data-key^='"+selDataKey+"']", that.TableContainer).not(target).slideUp();
        var itemData = target.data("itemDatas");
        if (typeof this.options.onSelected == "function"){
            this.options.onSelected.call(this, itemData);
        }
    };

    //自动完成渲染
    // $(".llw-people-table-select").each(function () {
    //     var peopleTableSel = new navTree();
    //     peopleTableSel.elem = this;
    //     peopleTableSel.render();
    // });

    var navTreeCss = ".layui-table td{cursor: pointer;}.layui-table tr.tr_selected{background: #20aaf2;color: white;}.nav-icon{margin-right: 5px;width: 16px;height: 16px;top: 4px;" +
        "position: absolute;background-size: 16px;}" +
        ".icon-building{background-image:url(../../static/imgs/building.png);}" +
        ".icon-floor{background-image:url(../../static/imgs/floor.png);" +
        "background-size: 14px;width: 14px;height: 14px;top: 6px;}" +
        ".icon-unit{background-image:url(../../static/imgs/unit.png);}" +
        ".layui-table tr.tr_selected:hover{background: #20aaf2;color:#ff9800;}tr.tr_selected .layui-edge{-webkit-transform:rotate(90deg);transform:rotate(90deg);margin-top: 2px;}.tr_selected td{border:1px solid #20aaf2!important;}" +
        "td .layui-edge {position: absolute;right: 8px;" +
        "font-size:12px;top: 5px;width:16px;height:16px;}" ;
    var $head = $('head'),
        navTreeStyle = $head.find('style[tableSelect]');
    if (navTreeStyle.length === 0) {
        $head.append($('<style type="text/css" navTree>').append(navTreeCss));
    }

    var navTree = {};
    navTree.render = function(options){
        var navTreeOptions = {
            elem : null,//操作DOM对象
            title : "",
            url :  "",
            paramid : "id",
            paramParentId : "pid",
            onSelected : function () {}
        };
        $.extend(navTreeOptions, options);
        var navTreeObj = new navTreeCls();
        navTreeObj.elem = $(navTreeOptions.elem);
        navTreeObj.options = navTreeOptions;
        navTreeObj.render();
        return navTree;
    }

    exports(MOD_NAME, navTree);
})