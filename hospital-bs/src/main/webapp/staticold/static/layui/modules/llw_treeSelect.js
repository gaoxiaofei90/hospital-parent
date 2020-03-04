layui.define(['laytpl','tree'], function(exports){
    var $ = layui.$
        ,layer = layui.layer
        ,treeSelect = layui.treeSelect
        ,options = {}
        ,laytpl = layui.laytpl
        ,thisTreeSelect = function(){
            var that = this
                ,options = that.config;

            return {
                rend: function(options){
                    that.rend.call(that, options);
                }
                ,val : function(){
                    return that.val.call(that, options);
                }
                ,config: options
            }
    }
        ,llw_treeSelect = {
        config : {
        },
        renderObjs : {}//已经渲染的对象
    }
        ,Class = function(elementOptions){
        var that = this;
        that.config = elementOptions;
        that.render();
    };

    var TPL_MAIN = ['<div class="layui-treeSelect layui-unselect' +
    ' layui-form-select" id="layui-treeSelect-tree">',
    '<div class="layui-select-title" id="layui-select-title-tree">',
        '<input type="text" id="treeSelect-input-tree" autocomplete="off" placeholder="{{d.placeholder}}" ' +
        'value="" class="layui-input layui-unselect">'
        ,'<i class="layui-edge"></i>','</div>',
        '<div class="layui-anim1 layui-anim-upbit" style="">',
        '<ul class="layui-tree-container" style="" llw-tree-id="{{d.treeId}}"' +
        ' llw-tree-pid="{{d.treePid}}" llw-tree-label="{{d.treeLabel}}" llw-tree-url="{{d.treeUrl}}"></ul>',
        '</div>','</div>'];

    Class.prototype.render = function(){
        var that = this;
        var treeTargetObj = $(laytpl(TPL_MAIN.join("")).render(
            {placeholder:this.config.placeholder,treeUrl:this.config.treeUrl
                ,treeId:this.config.treeId,treePid:this.config.treePid
                ,treeLabel:this.config.treeLabel}));
        this.config.elem.hide().after(treeTargetObj);
        layui.tree({elem: treeTargetObj.find(".layui-tree-container"),click: function(node){
                $(".treeNodeSelected", treeTargetObj).removeClass("treeNodeSelected");
                if (node.target){
                    node.target.addClass("treeNodeSelected");
                }
                if (typeof window[that.config.treeClick] == "function"){
                    window[that.config.treeClick].call(that,node);
                }
                $("#treeSelect-input-tree", treeTargetObj).val(node[that.config.treeLabel]);
                that.config.elem.val(node[that.config.treeId]);
                that.config.elem.next().removeClass("layui-tree-selected");
            }
        });

        $(".layui-select-title", treeTargetObj).click(function(){
            if ($(this).parent().hasClass("layui-tree-selected")){
                $(this).parent().removeClass("layui-tree-selected");
            }
            else{
                $(this).parent().addClass("layui-tree-selected");
            }
        });
        $(document.body).bind("click", function(e){
            if ($(e.target).closest(".layui-tree-selected").length == 0){
                that.config.elem.next().removeClass("layui-tree-selected");
            }
        });
    }

    llw_treeSelect.render = function(options){
        var inst = new Class(options);
        return thisTreeSelect.call(inst);
    };

    llw_treeSelect.init = function(filter, settings){
        var isShowOrg = parent.inst_isShowOrg || parent.parent.inst_isShowOrg ||
            parent.parent.parent.inst_isShowOrg;
        layui.$(".llw_treeSelect").each(function(){
            var llwOrgBox = $(this).closest("[llw-org]");
            if (llwOrgBox.length > 0 && !isShowOrg){
                return;
            }
            llwOrgBox.css("display", "inline-block");
            var treeSelect_url = $(this).attr("llw-tree-url");
            var treeSelect_id = $(this).attr("llw-tree-id");
            var treeSelect_pid = $(this).attr("llw-tree-pid");
            var treeSelect_name = $(this).attr("llw-tree-label");

            var name = $(this).attr("name");
            var placeholder = $(this).attr("placeholder");

            var elementOptions = {};
            elementOptions.elem = $(this);
            elementOptions.name = name;
            elementOptions.treeUrl = treeSelect_url;
            elementOptions.treeId = treeSelect_id;
            elementOptions.treePid = treeSelect_pid;
            elementOptions.treeLabel = treeSelect_name;
            elementOptions.treeClick = $(this).attr("llw-tree-click");

            elementOptions.placeholder = placeholder;

            llw_treeSelect.render(elementOptions);
        });
    }

    var treeSelectCss = ".layui-treeSelect .layui-anim-upbit{display:none;} .layui-anim-upbit{position:absolute;left:0;top:42px;padding:5px 0;z-index:9999;border:1px solid #d2d2d2;overflow-y:auto;" +
        "background-color:#fff;border-radius:2px;box-shadow:0 2px 4px rgba(0,0,0,.12);box-sizing:border-box}.layui-card-header .layui-icon{position:relative;left:0px;" +
        "margin-top: 5px;top: 0px;}.layui-tree-selected .layui-edge{margin-top:-9px;-webkit-transform:rotate(180deg);transform:rotate(180deg);}.layui-tree-container a:hover{color:#ff9800;}" +
        ".layui-tree-selected .layui-anim-upbit{display:block;width:100%;}.treeNodeSelected{color:#ff9800;}";
    $head = $('head'),
    treeSelectStyle = $head.find('style[treeSelect]');
    if (treeSelectStyle.length === 0) {
        $head.append($('<style type="text/css" treeSelect>').append(treeSelectCss));
    }

    llw_treeSelect.init(options);
    //对外暴露的接口
    exports('llw_treeSelect', llw_treeSelect);
});