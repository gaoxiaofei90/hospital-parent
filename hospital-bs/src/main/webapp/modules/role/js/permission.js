layui.config({
    base: '../../static/layui/' //静态资源所在路径
}).extend({
    index: 'modules/index' //主入口模块
}).use(['index'], function() {
    var $ = layui.$,
        form = layui.form;
    var roleId = layui.util.getUrlParam("roleId");
    var name = layui.util.getUrlParam("name");
    $("#roleId").html(roleId);
    $("#roleIdParam").val(roleId);
    $("#name").html(decodeURIComponent(name));
    //动态设置表单的请求url
    $("#permissionTree0").attr('llw-tree-url',"../../medical/sysRole/permissionAssign.jspx?modules=0&roleId=" + roleId)
    $("#permissionTree1").attr('llw-tree-url',"../../medical/sysRole/permissionAssign.jspx?modules=1&roleId=" + roleId)
    layui.config({
        base: '../../static/layui/' //静态资源所在路径
    }).extend({
        treeUl: 'modules/treeUl'
    }).use(['treeUl'],function(){
        var tree = layui.tree;
    });
})

function getPermissionArr() {
    var permissionIdArr = [];
    layui.$("#permissionTree1").find("input[type=checkbox]").each(function () {
        if (this.checked){
            permissionIdArr.push(this.value);
        }
    });
    layui.$("#permissionTree0").find("input[type=checkbox]").each(function () {
        if (this.checked){
            permissionIdArr.push(this.value);
        }
    });
    return permissionIdArr;
}