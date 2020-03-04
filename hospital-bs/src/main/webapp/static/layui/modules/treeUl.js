layui.define(['jquery','form'], function(exports){
    "use strict";

    var $ = layui.$
        ,laytpl = layui.laytpl
        ,form = layui.form
        ,hint = layui.hint();

    var enterSkin = 'layui-tree-enter', TreeUl = function(options){
        this.options = options;
    };

    //图标
    var icon = {
        arrow: ['&#xe623;', '&#xe625;'] //箭头
        ,checkbox: ['&#xe626;', '&#xe627;'] //复选框
        ,radio: ['&#xe62b;', '&#xe62a;'] //单选框
        ,branch: ['&#xe621;', '&#xe621;'] //父节点
        ,leaf: '&#xe621;' //叶节点
//  ,leaf: '&#xe621;' //叶节点
    };

    //初始化
    TreeUl.prototype.init = function(elem){
        var that = this;
        var contentDivId = elem.attr("llw-content-div");
        if (contentDivId){
            that.options.contentDivHtml = $("#"+contentDivId).html();
        }
        elem.wrap('<form class="layui-form" action="" lay-filter="tree-ul-form">');
        elem.addClass('layui-box layui-tree layui-treeUl'); //添加tree样式
        if(that.options.skin){
            elem.addClass('layui-tree-skin-'+ that.options.skin);
        }
        that.treeUl(elem);
        that.on(elem);
    };

    //树节点解析
    TreeUl.prototype.treeUl = function(elem, children){
        var that = this, options = that.options,
            llwUrl = elem.attr("llw-tree-url");
        var nodes = children || options.nodes;
        if (llwUrl){
            layui.util.ajax({
                url : llwUrl,
                success : function(res){
                    if(res.respData) {
                        //重新组装数据
                        var tree = new layui.util.TreeWapper(res.respData,'permissionId','pid').toTree();
                        that.createTreeUl(elem, tree);
                    }
                }
            })
        }
        else{
            that.createTreeUl(elem, children);
        }
    };

    TreeUl.prototype.createTreeUl = function(elem, children){
        var that = this, options = that.options,
            nodes = children || options.nodes;
        layui.each(nodes, function(index, item){
            var hasChild = item.children && item.children.length > 0 && item.type != 3;
            var ul = $('<ul class="'+ (item.spread ? "layui-show" : "") +'"></ul>');
            var li = $(['<li '+ (item.spread ? 'data-spread="'+ item.spread +'"' : '') +'>'
                //展开箭头
                ,function(){
                    return (hasChild && item.type < 2)? '<i class="layui-icon layui-tree-spread">'+ (
                        item.spread ? icon.arrow[1] : icon.arrow[0]
                    ) +'</i>' : '';
                }()

                //复选框/单选框
                ,function(){
                    var checkedStr = item.checked == "1"?"checked":"";
                    return options.check ?
                        '<div class="check-container"><input class="layui-form-checkbox tree-li-checkbox" type="checkbox" '
                        +'name="list['+item.permissionId+']" value="'+item.permissionId+'" title='+item.name+' '+checkedStr+' lay-skin="primary"></div>' : '';
                }()

                //内容
                ,function(){
                    var checkboxArr = [];
                    if (item.type == 2 && item.children){
                        checkboxArr.push("<div class='item-checkbox-container'>")
                        for (var i=0;i<item.children.length;i++){
                            var citem = item.children[i];
                            var checkedStr = citem.checked == "1"?"checked":"";
                            checkboxArr.push('<input class="tree-li-checkbox" type="checkbox" '
                                +checkedStr+' title="'+citem.name+'" value="'+citem.permissionId+'" lay-skin="primary">');
                        }
                        checkboxArr.push("</div>")
                    }
                    return checkboxArr.join("");
                }()

                ,'</li>'].join(''));

            if (item.type == 2){
                li.addClass("tree-li-dashed");
            }

            //如果有子节点，则递归继续生成树
            if(hasChild && item.type != 2){
                li.append(ul);
                that.treeUl(ul, item.children);
            }

            elem.append(li);

            //触发点击节点回调
            typeof options.click === 'function' && that.click(li, item);

            //伸展节点
            that.spread(li, item);
            layui.form.render("checkbox");

            $('.check-container', li).on("click", function(){
                var checkbox = $(this).children(".tree-li-checkbox")
                    ,checked = checkbox[0].checked;//数据
                var checkcount=0;
                var allcount=$(this).parent().parent().children(".tree-li-dashed").length;
                layui.$(".tree-li-checkbox", $(this).parent("li")).each(function(i, item){
                    item.checked = checked;
                    if (checked){
                        $(item).siblings(".layui-form-checkbox").addClass("layui-form-checked");
                        $(item).siblings(".layui-form-checkbox").children(".layui-icon.layui-icon-ok").attr("background-color","#5FB878")
                    }
                    else{
                        $(item).siblings(".layui-form-checkbox").removeClass("layui-form-checked");
                        $(item).siblings(".layui-form-checkbox").children(".layui-icon.layui-icon-ok").attr("style","")
                    }
                });
                $(this).parent().parent().children(".tree-li-dashed").each(function(i,item){
                    if ($(this).children(".check-container").children(".tree-li-checkbox")[0].checked){
                        checkcount++;
                    }
                })
                if(allcount===checkcount){
                    $(this).parent('li').parent().parent('li').children(".check-container").find(".layui-icon.layui-icon-ok").css("background-color","#5FB878")
                    $(this).parent().parent().siblings(".check-container").children(".tree-li-checkbox").prop('checked',true);
                }
                else if(checkcount===0){
                    // checkbox[0].checked=false;
                    $(this).parent().parent().siblings(".check-container").children(".tree-li-checkbox").prop('checked',false);
                    $(this).parent().parent().siblings(".check-container").children(".tree-li-checkbox").siblings(".layui-form-checkbox").removeClass("layui-form-checked");
                    $(this).parent().parent().siblings(".check-container").children(".tree-li-checkbox").siblings(".layui-form-checkbox").children(".layui-icon.layui-icon-ok").attr("style","");

                }else{
                    $(this).parent().parent().siblings(".check-container").children(".tree-li-checkbox").prop('checked',true);
                    $(this).parent('li').parent().parent('li').children(".check-container").find(".layui-icon.layui-icon-ok").css("background-color","gray")
                }
            });
            $('.layui-form-checkbox','.item-checkbox-container').on("click", function(){
                var checkbox =  $(this).siblings(".tree-li-checkbox:checked")
                var ojs= $(this).parents(".item-checkbox-container").siblings(".check-container").children(".tree-li-checkbox");
                if(checkbox.length>0)
                {
                    ojs.prop('checked',true);
                    ojs.siblings(".layui-form-checkbox").addClass("layui-form-checked");
                }
                else{
                    ojs.prop('checked',false);
                    ojs.siblings(".layui-form-checkbox").removeClass("layui-form-checked");
                }
                var checkcount=0;
                var pojs=$(this).parents(".item-checkbox-container").siblings(".check-container");
                var allcount=pojs.parent().parent().children(".tree-li-dashed").length;
                pojs.parent().parent().children(".tree-li-dashed").each(function(i,item){
                    if ($(this).children(".check-container").children(".tree-li-checkbox")[0].checked){
                        checkcount++;
                    }
                })
                if(allcount===checkcount){
                    pojs.parent('li').parent().parent('li').children(".check-container").find(".layui-icon.layui-icon-ok").css("background-color","#5FB878")

                    pojs.parent().parent().siblings(".check-container").children(".tree-li-checkbox").prop("checked", true);
                }
                else if(checkcount===0){
                    // checkbox[0].checked=false;
                    pojs.parent().parent().siblings(".check-container").children(".tree-li-checkbox").prop("checked", false);
                    pojs.parent().parent().siblings(".check-container").children(".tree-li-checkbox").siblings(".layui-form-checkbox").removeClass("layui-form-checked");
                    pojs.parent().parent().siblings(".check-container").children(".tree-li-checkbox").siblings(".layui-form-checkbox").children(".layui-icon.layui-icon-ok").attr("style","");
                }else{
                    pojs.parent('li').parent().parent('li').children(".check-container").find(".layui-icon.layui-icon-ok").css("background-color","gray")
                    pojs.parent().parent().siblings(".check-container").children(".tree-li-checkbox").prop("checked", true);
                }

            })
            //拖拽节点
            options.drag && that.drag(li, item);
        });
    };

    //点击节点回调
    TreeUl.prototype.click = function(elem, item){
        var that = this, options = that.options;

        elem.children('a').on('click', function(e){
            layui.stope(e);
            options.click(item)
        });
    };

    //伸展节点
    TreeUl.prototype.spread = function(elem, item){
        var that = this, options = that.options;
        var arrow = elem.children('.layui-tree-spread')
        var ul = elem.children('ul'), a = elem.children('a');

        //执行伸展
        var open = function(){
            if(elem.data('spread')){
                elem.data('spread', null)
                ul.removeClass('layui-show');
                arrow.html(icon.arrow[0]);
                a.find('.layui-icon').html(icon.branch[0]);
            } else {
                elem.data('spread', true);
                ul.addClass('layui-show');
                arrow.html(icon.arrow[1]);
                a.find('.layui-icon').html(icon.branch[1]);
            }
        };

        //如果没有子节点，则不执行
        if(!ul[0]) return;

        arrow.on('click', open);
        a.on('dblclick', open);
    }

    //通用事件
    TreeUl.prototype.on = function(elem){
        var that = this, options = that.options;
        var dragStr = 'layui-tree-drag';

        //屏蔽选中文字
        elem.find('i').on('selectstart', function(e){
            return false
        });

        //拖拽
        if(options.drag){
            $(document).on('mousemove', function(e){
                var move = that.move;
                if(move.from){
                    var to = move.to, treeMove = $('<div class="layui-box '+ dragStr +'"></div>');
                    e.preventDefault();
                    $('.' + dragStr)[0] || $('body').append(treeMove);
                    var dragElem = $('.' + dragStr)[0] ? $('.' + dragStr) : treeMove;
                    (dragElem).addClass('layui-show').html(move.from.elem.children('a').html());
                    dragElem.css({
                        left: e.pageX + 10
                        ,top: e.pageY + 10
                    })
                }
            }).on('mouseup', function(){
                var move = that.move;
                if(move.from){
                    move.from.elem.children('a').removeClass(enterSkin);
                    move.to && move.to.elem.children('a').removeClass(enterSkin);
                    that.move = {};
                    $('.' + dragStr).remove();
                }
            });
        }
    };

    //拖拽节点
    TreeUl.prototype.move = {};
    TreeUl.prototype.drag = function(elem, item){
        var that = this, options = that.options;
        var a = elem.children('a'), mouseenter = function(){
            var othis = $(this), move = that.move;
            if(move.from){
                move.to = {
                    item: item
                    ,elem: elem
                };
                othis.addClass(enterSkin);
            }
        };
        a.on('mousedown', function(){
            var move = that.move
            move.from = {
                item: item
                ,elem: elem
            };
        });
        a.on('mouseenter', mouseenter).on('mousemove', mouseenter)
            .on('mouseleave', function(){
                var othis = $(this), move = that.move;
                if(move.from){
                    delete move.to;
                    othis.removeClass(enterSkin);
                }
            });
    };

    //暴露接口
    exports('treeUl', function(options){
        var treeUl = new TreeUl(options = options || {});
        var elem = $(options.elem);
        if(!elem[0]){
            return hint.error('layui.treeUl 没有找到'+ options.elem +'元素');
        }
        treeUl.init(elem);
    });


    $("ul[llw-tree-url]").each(function(){

        var clickEvent = $(this).attr("llw-tree-click");
        layui.treeUl({elem: this,check:true, click: function(node){
                if (clickEvent){
                    eval(clickEvent+"(node);");
                }
            }
        });
    });
});
