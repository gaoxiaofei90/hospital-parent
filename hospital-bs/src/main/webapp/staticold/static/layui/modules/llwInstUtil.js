layui.define(['form','jquery','treeSelect','upload','util'], function (exports) { //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var jQuery = layui.jquery,
        $ = jQuery,
        form = layui.form,
        treeSelect=layui.treeSelect,
        upload=layui.upload,
        _MOD = 'llwInstUtil',
        llwInstUtil = {
            orgDepartmentTreeUrl:'../../inst/orgDepartment/list.jspx',
            orgPostSelectUrl:'../../inst/orgPost/list.jspx',
            sysRoleSelectUrl:'../../inst/sysRole/list.jspx',
            sysDictUrl:'../../inst/sysDict/list.jspx',
            //绑定元素,请求地址,成功后回调
            treeSelectRender:function(elem,url,fn,id){
                treeSelect.render({
                    elem: elem,
                    data: url,
                    type: 'get',
                    placeholder: '请选择',
                    click:function(d){
                        $(elem).val(d.current[id]);
                        $(elem).click();},
                    search: false,
                    success: function (d) {
                        if(fn){
                            fn(d);
                        }

                        //                选中节点，根据id筛选
                        //                 treeSelect.checkNode('departmentTree', departmentId);

                        //                获取zTree对象，可以调用zTree方法
                        //                var treeObj = treeSelect.zTree('tree');
                        //                console.log(treeObj);

                        //                刷新树结构
                        //                treeSelect.refresh();
                    }
                });
            },
            upload:function(btn,picShow,picUrl,picTag){
                //图片上传
                upload.render({
                    elem: btn //绑定元素 #xxx
                    ,url: '../../upload.jspx?bucketName='+picTag //上传接口
                    ,before:function(){
                        layer.load(1);
                    }
                    ,done: function(res){
                        if(res.respCode==0){
                            var src=res.respData.src;
                            $(picShow).attr("src",src);
                            $(picUrl).val(src);
                        }
                        layer.closeAll('loading'); //关闭loading
                    }
                    ,error: function(){
                        //请求异常回调
                    }
                });
            },
            getName:function(url,key,pk){
                var value=pk;
                layui.util.ajax({
                    url:url,
                    data:{pk:pk},
                    async:false,
                    success:function(resp){
                        value=resp.respData.key;
                    }
                });
                return value;
            },
            getDictName:function(dictCode,dictValue){
                if(dictValue){
                    dictValue = dictValue.toString();
                }
                var dictMap = parent.inst_dict || parent.parent.inst_dict || parent.parent.parent.inst_dict;
                var dictArr = dictMap[dictCode];
                for(var i = 0, len = dictArr.length; i < len; i++) {
                    if(dictArr[i].fieldValue == dictValue) {
                        return dictArr[i].valueComment;
                    }
                }
            }
        };
    exports(_MOD, llwInstUtil);
});