layui.define(['util','laytpl'], function (exports) {
    "use strict";
    var MOD_NAME = 'yfnavTree',
        $ = layui.jquery,
        laytpl = layui.laytpl,
        form = layui.form;
    var navTreeCls = function () {
        this.v = '1.0.0';
    };
    navTreeCls.prototype.render = function () {
        var that = this;
        var OBJ_CONTAI = ['<div class="yfnavtree"></div>']
        this.elem.append(laytpl(OBJ_CONTAI.join('')).render(this.options));
        this.Container=this.elem;
        this.getTop($('.yfnavtree',this.Container));
    };
    var OBJ =['<div>','{{#  layui.each(d, function(index, item){ }}',
        '<div class="navHtml"><div class="navTitleRow"><div class="title" data-id="{{item.orgId}}"  data-childcount="{{item.childCount}} ">{{item.name}}</div>',
        '<i class="layui-icon layui-icon-delete" data-event="del" data-id="{{item.orgId}}" data-childcount="{{item.childCount}}" ></i>',
        '<i class="layui-icon layui-icon-add-1" data-event="add" data-id="{{item.orgId}}" data-childcount="{{item.childCount}}" ></i>',
        '{{# if(item.childCount > 0){ }}',
        '<i class="layui-icon layui-icon-next" data-event="next" data-id="{{item.orgId}}" data-childcount="{{item.childCount}}" ></i>',
        '{{# }  }}',
        '{{# if(item.childCount == 0){ }}',
        '<i style="display: none;" class="layui-icon layui-icon-next" data-event="next" data-id="{{item.orgId}}" data-childcount="{{item.childCount}}" ></i>',
        '{{# }  }}',
        '</div>',
        '<div class="listContent" style="padding-left: 20px"></div></div>',
        '{{#  }); }}',
        //'{{# if(d.length === 0){ }}<div>无数据</div>{{# } }}','</div>'
    ]

    navTreeCls.prototype.getDatas = function(obj,id,$icon){
        //第一次进来需要自动获取titile
        var that = this;
        var surl=that.options.url+"?pid="+id;
        layui.util.ajax({
            url : surl,
            success : function(res) {
                //如果没数据，或者是个空数组
                if (res.respData) {
                    if(that.options.type==1){
                        that.createObj(obj,res.respData,$icon.data("clickLast"))
                        //如果没有数据，隐藏layui-icon-next
                        if(res.respData.length < 1){
                            $icon.hide();
                        }else{
                            $icon.show();
                        }
                    }
                }
            }
        });
    };

    /**
     * 先获取顶级节点
     * @param obj
     */
    navTreeCls.prototype.getTop = function(obj){
        var that = this;
        layui.util.ajax({
            url : that.options.url,
            success : function(res) {
                if (res.respData) {
                    that.createObj(obj,res.respData)
                    $(".title",obj).trigger('click');
                    if(res.respData[0].childCount > 0){
                        //获取一级节点数据
                        $(".layui-icon-next",obj).trigger('click');
                        //that.getDatas($(".listContent",obj),res.respData[0].orgId);
                    }
                }
            }
        });
    };

    navTreeCls.prototype.createObj=function (obj,list,clickLast) {
        var that = this;
        var objs= $(laytpl(OBJ.join('')).render(list))
        $(".title",objs).on("click",function(){
            that.itemSelect($(this));
        })
        $(".layui-icon",objs).on("click",function(){
            var event = $(this).data("event");
            if("del" == event){
                that.toolClicked($(this),event);
            }else if("add" == event){
                that.toolClicked($(this),event);
            }else if("next" == event){
                if($(this).hasClass("xl")){
                    $(this).removeClass("xl")
                    $(this).parent().siblings(".listContent").slideUp()
                }else{
                    $(this).addClass("xl")
                    $(this).parent().siblings(".listContent").slideDown()
                    that.getDatas($(this).parent().siblings(".listContent"),$(this).data("id"),$(this));
                }
            }
        })
        obj.html("");
        obj.append(objs);

        if(clickLast && list.length > 0){
            obj.children().children(".navHtml:last-child").children().find(".title").trigger('click');
        }
    }


    navTreeCls.prototype.itemSelect=function (target) {
        var that=this;
        that.options.orgId=$(target).data("id")
        if (typeof this.options.onSelected == "function"){
            this.options.onSelected.call(this,this.options);
        }
    }

    navTreeCls.prototype.toolClicked=function (target,event) {
        var that=this;
        that.options.orgId=$(target).data("id")
        if (typeof this.options.onEvent == "function"){
            var callback = function(){
                //返回成功后刷新
                if(event == 'del'){
                    //找父节点 点击两下
                    $($(target).parents(".navHtml")[1]).children(".navTitleRow").find(".title").trigger('click');
                    var parentNext = $($(target).parents(".navHtml")[1]).children(".navTitleRow").find(".layui-icon-next");
                    parentNext.trigger('click');
                    parentNext.trigger('click');
                }else if(event == 'add'){
                    $(target).siblings(".layui-icon-next").removeClass("xl")
                    //点一下
                    $(target).siblings(".layui-icon-next").data("clickLast","1");
                    $(target).siblings(".layui-icon-next").trigger('click');
                }
            };
            this.options.onEvent.call(this,this.options,event,callback);
        }
    }
    var navTreeCss = ['.yfnavtree .navTitleRow{    cursor: pointer;position:relative;padding-right: 20px;min-height: 30px;line-height: 30px;}',
        '.yfnavtree .layui-icon-next{position:absolute;right:0px;top:0px;}',
        '.yfnavtree .layui-icon-add-1{position:absolute;right:20px;top:0px;}',
        '.yfnavtree .layui-icon-delete{position:absolute;right:40px;top:0px;}',
        '.yfnavtree .xl{transform: rotate(90deg);}',
        '.yfnavtree .listContnt{max-height: 150px;overflow-y: auto;}'
    ].join('');
    var $head = $('head'),
        navTreeStyle = $head.find('style[tableSelect]');
    if (navTreeStyle.length === 0) {
        $head.append($('<style type="text/css" navTree>').append(navTreeCss));
    }
    var yfnavTree = {};
    yfnavTree.render = function(options){
        var navTreeOptions = {
            elem : null,//操作DOM对象
            title : "",
            url :  "",
            type:1,
            onSelected : function () {},
        };
        $.extend(navTreeOptions, options);
        var yfnavTreeObj = new navTreeCls();
        yfnavTreeObj.elem = $(navTreeOptions.elem);
        yfnavTreeObj.options = navTreeOptions;
        yfnavTreeObj.render();
        return yfnavTree;
    }
    exports(MOD_NAME, yfnavTree);
})