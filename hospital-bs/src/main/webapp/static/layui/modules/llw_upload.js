layui.define(['laytpl','upload'], function(exports){
    var $ = layui.$
        ,layer = layui.layer
        ,upload = layui.upload
        ,options = {}
        ,laytpl = layui.laytpl
        ,thisUpload = function(){
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
        ,llw_upload = {
        config : {
        },
        renderObjs : {}//已经渲染的对象
    }
        ,Class = function(elementOptions){
        var that = this;
        that.config = elementOptions;
        that.render();
    };

    var INPUT_TAG = '<input type="hidden" class="inputText" name="{{d.name}}">'
        +'<button class="uploadBtn" style="display:none"></button>';
    var TPL_MAIN = ['<div class="layui-upload-img-div">'
    +'<img src="../../static/layui/modules/css/del.png" class="layui-upload-del-img">'
        ,'<div class="layui-upload-div"><img class="layui-upload-img" index="0"/>'
        ,'<div class="plus-icon">+</div></div>'
        ,'<p class="demoText"></p><div class="imgE" style="display:none;background: #fff;position: absolute; z-index: 1;width: 100%;height: 100%;top: 0px;opacity: 0;"></div></div>'
    ];
    Class.prototype.render = function(){
        var that = this;
        var reElem = $(laytpl(INPUT_TAG).render({
            name: that.config.name
        }));
        that.config.elem.append(reElem).append(TPL_MAIN.join(""));
        var uploadInst = upload.render({
            elem: $(".uploadBtn", that.config.elem)
            ,url: that.config.upload_url
            ,multiple: that.config.multiple
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                $(".inputText", that.config.elem).attr("preview","true");
                obj.preview(function(index, file, result){
                    curImg = that.loadImg(index);
                    if (!curImg){
                        return;
                    }
                    curImg.attr({'src':result,"isLoad":"true"}).show("normal").next().hide();
                    curImg.parent().prev().show();
                });
            }
            ,done: function(res,index){
                curImg = that.loadImg(index);
                if (!curImg){

                    return;

                }
                curImg.attr({'src':res.respData.src,"isLoad":"true"}).next().hide();
                curImg.parent().prev().attr("targetsrc",res.respData.src).show();
                var imgArr = [];
                $(".layui-upload-img[isLoad=true]",that.config.elem).each(function(){
                    imgArr.push($(this).attr("src"));
                });
                var inputText = $(".inputText",that.config.elem);
                inputText.val(imgArr.join(";"));
            }
            ,error: function(index){
                curImg = that.loadImg(index);
                if (!curImg){
                    return;
                }

                if(curImg.length > 0){
                    var demoText = curImg.closest(".layui-upload-img-div").find(".demoText");
                    demoText.html('<span style="color: #FF5722;">上传失败</span> ' +
                        '<a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function(){
                        if (that.config.multiple){
                            $(this).closest(".layui-upload-img-div").addClass("reupload-img-div");
                        }
                        $(".uploadBtn",that.config.elem).click();
                    });
                }
            }
        });

        that.initValue();
    }

    Class.prototype.loadImg = function(index){
        var that = this;
        var reuploadImgDiv = $(".reupload-img-div",that.config.elem);
        if (reuploadImgDiv.length > 0){
            reuploadImgDiv.removeClass("reupload-img-div");
            curImg = $(".layui-upload-img", reuploadImgDiv);
            curImg.attr("index", index);
            return curImg;
        }

        var curImg = $(".layui-upload-img[index="+index+"]", that.config.elem);
        if (that.config.llwSize && that.config.multiple && curImg.length == 0
            && $(".layui-add-img", that.config.elem).length >= that.config.llwSize){
            layer.msg('上传图片不能超过'+that.config.llwSize+"张！");
            return false;
        }

        if (!that.config.multiple){
            curImg = $(".layui-upload-img", that.config.elem);
        }
        else if (that.config.multiple && curImg.length == 0){
            that.config.elem.prepend(TPL_MAIN.join(""));
            curImg = $(".layui-upload-img", that.config.elem).first();
            curImg.addClass("layui-add-img");
        }

        curImg.attr("index", index);
        that.addEvent();

        return curImg;
    }

    Class.prototype.initValue = function(){
        var that = this;
        //初始化图片显示
        for (var i=0;i<that.config.imgArr.length;i++){
            var curImg = $(".layui-upload-img[index=0]", that.config.elem);
            if (!that.config.multiple){
                curImg.attr({"index":parseInt(Math.random()*1000),"first":"true"});
            }
            else if (that.config.multiple){
                that.config.elem.prepend(TPL_MAIN.join(""));
                curImg = $(".layui-upload-img", that.config.elem).first();
                curImg.addClass("layui-add-img");
            }
            curImg.attr({'src':that.config.imgArr[i],"isLoad":"true","index":parseInt(Math.random()*1000)})
                .show("normal").next().hide(); //图片链接（base64）
            curImg.parent().prev().show();
        }
        if(that.config.imgArr.length<=0){
            $(".imgE", that.config.elem).css("opacity","1")
        }else{
            $(".imgE", that.config.elem).css("opacity","0")
        }

        that.addEvent();
    }

    Class.prototype.addEvent = function(){
        var that = this;
        $(".layui-upload-div",that.config.elem).unbind("click").on("click", function(){
            if (that.config.llwSize && that.config.multiple
                && $(".layui-add-img", that.config.elem).length >= that.config.llwSize){
                layer.msg('上传图片不能超过'+that.config.llwSize+"张！");
                return false;
            }

            $(".uploadBtn",that.config.elem).click();
        });

        $(".layui-upload-del-img",that.config.elem).unbind("click").on("click", function(){
            that.addDelEvent(this);
        });
    };

    Class.prototype.addDelEvent = function(targetObj){
        var that = this;
        $(targetObj).hide();
        var curImg = $(".layui-upload-img",$(targetObj).next());
        curImg.hide("normal", function(){
//          $(targetObj).attr("src", "none");
        }).next().show().parent().siblings(".demoText").empty();

        if (curImg.attr("first") == "true" || !that.config.multiple){
            curImg.attr("index", 0);
        }
        else{
            $(targetObj).parent().remove();
        }
        var imgSrc = $(targetObj).attr("targetsrc");
        var inputVal = $(".inputText",that.config.elem).val();
        if(inputVal && inputVal.indexOf(imgSrc+";") != -1){
            if (inputVal.indexOf(imgSrc+";") != -1){
                inputVal = inputVal.replace(imgSrc+";", "");
            }else{
                inputVal = inputVal.replace(imgSrc, "");
            }
            $(".inputText",that.config.elem).val(inputVal);
        }
    }

    Class.prototype.val = function(){
        return that.config.elem.attr("url");
    }

    llw_upload.render = function(options){
        var inst = new Class(options);
        var targetId = options.elem.attr("id");
        if (!targetId || targetId == ""){
            var randomNum = parseInt(Math.random()*1000);
            targetId = "llw-upload-"+randomNum;
            options.elem.attr("id", targetId);
        }
        llw_upload.renderObjs[targetId] = options;
        options.elem.data("uploadObj", inst);
        return thisUpload.call(inst);
    };

    llw_upload.val = function(id, options){
        var renderObj = llw_upload.renderObjs[id];
        if (renderObj){
            renderObj.val();
        }
    }

    llw_upload.init = function(filter, settings){
        layui.$(".llw_upload").each(function(){
            var upload_url = $(this).attr("llw-url");
            var multiple = $(this).attr("llw-multiple");
            var llwValue = $(this).attr("llw-value");
            var llwSize = $(this).attr("llw-size");
            var name = $(this).attr("name");

            var elementOptions = {};
            elementOptions.upload_url = upload_url;
            elementOptions.multiple = multiple == "true"?true:false;
            elementOptions.elem = $(this);
            elementOptions.name = name;
            var imgArr = [];
            if (llwValue && llwValue != ""){
                imgArr = llwValue.split(";");
            }
            elementOptions.imgArr = imgArr;
            if (llwSize && /^[0-9]+$/.test(llwSize)){
                elementOptions.llwSize = llwSize;
            }

            llw_upload.render(elementOptions);
        });
    }

    llw_upload.initValue = function(target, value){
        if (!value){
            value = target.attr("llw-value");
        }
        if (!value){
            return;
        }

        var uploadObj = target.data("uploadObj");
        if (!uploadObj){
            return;
        }

        uploadObj.config.imgArr = value.split(";");
        uploadObj.initValue();
    }

    llw_upload.init(options);
    //对外暴露的接口
    exports('llw_upload', llw_upload);
});