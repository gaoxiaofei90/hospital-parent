layui.define(['laytpl'], function(exports){
  var $ = layui.$
  ,layer = layui.layer
  ,options = {}
  ,laytpl = layui.laytpl
  ,thisNav = function(){
    var that = this
    ,options = that.config;
    
    return {
      rend: function(options){
        that.rend.call(that, options);
      }
      ,nextStep : function(){
      	that.nextStep.call(that, options);
      }
      ,config: options
    }
  }
  ,nav = {
  	config : {
  	},
  	renderObjs : {}//已经渲染的对象
  }
  ,Class = function(elementOptions){
    var that = this;
    that.config = $.extend(options, elementOptions);
    that.render();
  };
  
  
  
  var TPL_MAIN = ['<ul class="layui-timeline">'
      ,'{{#  layui.each(d.list, function(index, item){ }}'
      +'<li index="{{index}}" class="layui-line-item {{#  if(item.selected){ }}layui-timeline-item-sel{{#  } }}">'
      ,'<i class="layui-icon layui-timeline-icon" style="">{{item.num}}</i>'
      ,'<div class="layui-timeline-content layui-text">'
      ,'<h2 class="layui-timeline-title">{{item.text}}</h2>'
      ,'</div>'
      ,'{{#  if(index+1 != d.list.length){ }}<div class="line"><div class="line-sel"></div>{{#  } }}'
      ,'</li>{{#  }); }}'];
  
  Class.prototype.render = function(){
  	var that = this;
  	that.config.elem.children("ul").hide();
  	var timeline = that.config.elem.next();
  	var nextObjcls = timeline.attr("class");
  	if (nextObjcls && nextObjcls.indexOf("layui-timeline") != -1){
  		timeline.remove();
  	}
  	reElem = that.elem = $(laytpl(TPL_MAIN.join("")).render({
       list: that.config.list
      ,index: that.index //索引
    }));
    
    $(that.config.elem).append(reElem);
  }
  
  Class.prototype.nextStep = function(){
  	var that = this;
  	var targetObj = that.config.elem.next();
  	var selItem = $(".layui-timeline-item-sel",that.config.elem);
  	var selItemNext = selItem.next();
  	if (selItemNext.length == 0 || 
  		selItemNext.attr("class").indexOf("layui-line-item") == -1){
  			return;
  	}
  	$(".line-sel",selItem).animate({"margin-left":"50%"}, 200, function(){
  		$(".layui-timeline-icon",selItem).text("√");
  		// $(".layui-timeline-icon",selItem).css("padding", "2px 5px");
  	});
  	
  	selItem.removeClass("layui-timeline-item-sel");
  	selItemNext.addClass("layui-timeline-item-sel");
  	
  	var index = selItemNext.attr("index");
  	var showDiv = that.config.elem.next(".layui-step-content").children().eq(index);
  	showDiv.siblings().hide("slow", function(){
  		showDiv.show();
  	});
  }
  
  Class.prototype.prevStep = function(){
  	var that = this;
  	var selItem = $(".layui-timeline-item-sel",that.config.elem);
  	var selItemPrev = selItem.prev();
  	if (selItemPrev.length == 0 || 
  		selItemPrev.attr("class").indexOf("layui-line-item") == -1){
  			return;
  	}
  	$(".line-sel",selItemPrev).animate({"margin-left":"0"}, 200, function(){
  		var index = selItem.attr("index");
  		index = index?1:parseInt(index)-1;
  		$(".layui-timeline-icon",selItemPrev).text(index);
  	});
  	
  	selItem.removeClass("layui-timeline-item-sel");
  	selItemPrev.addClass("layui-timeline-item-sel");
  	
  	var index = selItemPrev.attr("index");
  	var showDiv = that.config.elem.next(".layui-step-content").children().eq(index);
  	showDiv.siblings().hide("slow", function(){
  		showDiv.show();
  	});
  }
  
  nav.render = function(options){
    var inst = new Class(options);
    var targetId = options.elem.attr("id");
    if (!targetId || targetId == ""){
    	var randomNum = parseInt(Math.random()*1000);
    	targetId = "layui-step-"+randomNum;
    	options.elem.attr("id", targetId);
    }
    nav.renderObjs[targetId] = inst;
    return thisNav.call(inst);
  };
  
  nav.nextStep = function(id, options){
  	var renderObj = nav.renderObjs[id];
  	if (renderObj){
  		renderObj.nextStep();
  	}
  }
  
  nav.prevStep = function(id, options){
  	var renderObj = nav.renderObjs[id];
  	if (renderObj){
  		renderObj.prevStep();
  	}
  }
  
  
  nav.init = function(filter, settings){
  	layui.$(".layui-step-header").each(function(){
  		var datas = [];
  		var that = $(this);
  		$("li", that).each(function(index,item){
  			var childItem = {};
  			childItem.selected = $(this).attr("selected") == "selected";
  			childItem.text = $(this).html();
  			childItem.num = $(this).attr("num");
  			if (childItem.selected){
  				var selectedItem = that.siblings(".layui-step-content").children().eq(index);
  				selectedItem.show().siblings().hide();
  			}
  			datas.push(childItem);
  		});
  		var elementOptions = {};
  		elementOptions.list = datas;
  		elementOptions.elem = $(this);
  		
  		nav.render(elementOptions);
  	});
  }
  
  layui.link('../../static/layui/modules/css/nav.css');//引入css
  nav.init(options);
  //对外暴露的接口
  exports('nav', nav);
});