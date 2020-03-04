<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>医保远程查床监管平台 - 主页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="shortcut icon" href="${basePath}/static/imgs/bitbug_favicon.ico" >
    <link rel="stylesheet" href="${basePath}/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${basePath}/static/css/admin.css" media="all">
    <style>

        .layui-head-portrait{width: 30px;height: 30px;border-radius:50%;overflow: hidden;margin-top: 5px;}
        .layui-head-portrait img{width: 100%;height: 100%;display: block;}
        .layui-logo div{float: left;}
        .logo-admin{max-width: 80px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
        .layadmin-pagetabs{top: 0;}
        .layui-layout-admin .layui-body{top: 40px;}
        .layui-layout-admin .layui-logo{height: 40px;line-height: 40px;}
        .layui-head-logout{width: 14px;height: 14px;border-radius:50%;overflow: hidden;margin-top: 13px;margin-left: 10px;}
        .layui-head-logout img{width: 100%;height: 100%;display: block;}
        .layui-side-menu .layui-nav{margin-top: 40px;}
        .layui-fluid{padding: 10px;}
        .layui-nav-tree .layui-this, .layui-nav-tree .layui-this>a, .layui-nav-tree .layui-nav-child dd.layui-this, .layui-nav-tree .layui-nav-child dd.layui-this a{background:#4570fd !important;}
        .layui-this{background:#4570fd;}
        .layui-side-menu .layui-nav .layui-nav-item .layui-this a:hover{color: #FFFFFF;}
        .layui-admin-run div{float: left;}
        #layui-nav-kun{text-align: center;}
        #layui-nav-kun a{display: block;}
        #layui-nav-kun a:hover{background: #F2F2F2;color: #000000;}
        /*.layui-side-menu .layui-nav .layui-nav-item .layui-this > a:hover{color: #FFFFFF;}*/
        /*.layui-nav-tree .layui-this a:hover{color: #FFFFFF;}*/
        .layui-side-menu .layui-nav {
            background: #38425d!important;
        }
        .layui-nav-itemed>.layui-nav-child {
            display: block;
            padding: 0;
            background-color: rgba(0, 0, 0, .2)!important;
        }
        .layui-nav-tree .layui-nav-child a {
            height: 40px;
            line-height: 40px;
            color: #000;
            color: rgba(255, 255, 255, .7);
        }
        .layui-nav-itemed>a, .layui-nav-tree .layui-nav-title a, .layui-nav-tree .layui-nav-title a:hover {
            color: #FFFFFF!important;
        }
        .layui-nav .layui-nav-item a {
            display: block;
            padding: 0 20px;
            color: #bfc8e2;
            color: rgba(255, 255, 255, .7);
            transition: all .3s;
            -webkit-transition: all .3s;
        }
        .layui-nav .layui-nav-mored, .layui-nav-itemed>a .layui-nav-more {
            margin-top: -9px;
            border-style: dashed dashed solid;
            border-color: transparent transparent #fff;
        }
        .layui-nav .layui-nav-more {
            content: '';
            width: 0;
            height: 0;
            border-style: solid dashed dashed;
            border-color: #fff transparent transparent;
            overflow: hidden;
            cursor: pointer;
            transition: all .2s;
            -webkit-transition: all .2s;
            position: absolute;
            top: 50%;
            right: 3px;
            margin-top: -3px;
            border-width: 6px;
            border-top-color: rgba(255,255,255,.7);
        }
    </style>

</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu" style="background: #2f3b55!important;overflow: visible;">

            <div id="flexible_left" class="layui-nav-item layadmin-flexible" lay-unselect style="position: absolute;top:50%;right:-18px;margin-top:-40px;z-index:99999;background: rgba(0,0,0,.15);text-align: center;border-bottom-right-radius: 50%;border-top-right-radius:50%;">
                <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩" style="display: block;padding: 10px 2px;cursor: pointer;">
                    <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                </a>
            </div>

            <div class="layui-side-scroll" style="overflow-x: hidden;width: 100%;">

                <div class="layui-side-scroll" style="">

                    <div class="layui-logo" lay-href="console.html">
                    <!-- 
                         <img style="margin-right: 5px;margin-top: -5px;" src="${basePath}/static/imgs/logo-red.png"/>
                     -->
                         <span id="instName"><h2><b>管理后台</b></h2></span>
						 
                    </div>

                    <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
                        <c:forEach items="${menu}" var="menuVo">
                            <c:if test="${menuVo.type == 1}">
                                <li data-name="component" class="layui-nav-item">
                                    <a href="javascript:;" lay-tips="${menuVo.name}" lay-direction="2">
                                        <i class="layui-icon icon iconfont ${menuVo.icon}"></i>
                                        <cite>${menuVo.name}</cite>
                                    </a>
                                    <c:forEach items="${menuVo.list}" var="childMenuVo">
                                        <c:if test="${childMenuVo.type == 2}">
                                            <dl class="layui-nav-child">
                                                <dd data-name="grid">
                                                    <a lay-href="${childMenuVo.url}">${childMenuVo.name}</a>
                                                    <c:forEach items="${childMenuVo.list}" var="twoChildMenuVo">
                                                        <c:if test="${twoChildMenuVo.type == 2}">
                                                            <dl class="layui-nav-child">
                                                                <dd data-name="list"><a lay-href="${twoChildMenuVo.url}">${twoChildMenuVo.name}</a></dd>
                                                            </dl>
                                                        </c:if>
                                                    </c:forEach>
                                                </dd>
                                            </dl>
                                        </c:if>
                                    </c:forEach>
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

        <!-- 页面标签 -->

        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage" style="margin-right:-40px; right: 180px;border-right:1px solid #f6f6f6;z-index: 999999;background: #FFFFFF;"></div>

            <div style="position: absolute;right: 0;width: 150px;z-index: 999999;background: #FFFFFF;height: 40px;" class="layui-admin-run">
                <div style="width: 34px;position: relative;text-align: center;margin-left: 10px; display: none;">
                    <a lay-href="../modules/message/index.html" layadmin-event="message" lay-text="消息中心" style="display: block;">
                        <i class="layui-icon layui-icon-notice" style="color: #000000;"></i>
                        <span class="layui-badge-dot" style="position: absolute;top: 10px;right: 5px;"></span>
                    </a>
                </div>

                <div lay-unselect id="layui-unselect" style="float: right;">

                    <div class="layui-head-portrait" style="margin-left: 10px;">
                        <c:if test="${not empty sysUser.photo}">
                            <a><img src='<c:out value="${sysUser.photo}"></c:out>'></a>
                        </c:if>
                        <c:if test="${empty sysUser.photo}">
                            <a><img src='${pageContext.request.contextPath}/static/imgs/userIcon.png'></a>
                        </c:if>
                    </div>
                    <div style="margin-left: 10px;width: 80px;">
                        <a><div class="logo-admin" style="color: #000;"><c:out value="${username}"></c:out></div></a>
                    </div>

                    <dl class="layui-nav-child" id="layui-nav-kun" style="top: 40px;border: 1px solid #EEEEEE;">
                        <%--<dd><a lay-href="../modules/user/info.html"><i class="layui-icon layui-icon-username"></i>&nbsp;&nbsp;基本资料</a></dd>--%>
                        <dd><a lay-href="../modules/changePassword/index.html"><i class="layui-icon layui-icon-password"></i>&nbsp;&nbsp;修改密码</a></dd>
<%--                        <dd><a lay-href="../modules/changePassword/index.html"><i class="layui-icon layui-icon-set "></i>&nbsp;&nbsp;安全管控</a></dd>--%>
                        <hr style="margin:5px 0;">
                        <dd layadmin-event="logout" style="text-align: center;"><a><i class="layui-icon layui-icon-close"></i>&nbsp;&nbsp;退出登录</a></dd>
                    </dl>
                </div>
            </div>

            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader" style="margin-right: 140px;">
                    <li lay-id="console.html" lay-attr="console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="${basePath}/modules/console/index.html" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<script type="text/javascript" src="${basePath}/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '${basePath}/static/layui/' //静态资源所在路径
    }).extend({
        index: 'modules/index' //主入口模块
    }).use(['index','admin'],function(){
        var $ = layui.$
        layui.util.ajax({
            url: "${basePath}/common/allDict.jspx",
            data: {},
            success: function(res) {
                window.inst_dict = res.respData;
            }
        });

        //是否显示机构查询的下拉框
        var hasSubOrg = '${hasSubOrg}';
        if('true' == hasSubOrg){
            window.inst_isShowOrg = true;
        }else{
            window.inst_isShowOrg = false;
        }

        layui.util.ajax({
            url: "${basePath}/common/allPermissions.jspx",
            data: {},
            success: function(res) {
                var permissionArr = [];
                for (var i=0;i<res.respData.length;i++){
                    var permissionValue = res.respData[i].permissionValue;
                    if (!permissionValue || permissionValue == ""){
                        continue;
                    }
                    else if (permissionValue.indexOf(";") != -1){
                        var childPermissions = permissionValue.split(";");
                        for (var j=0;j<childPermissions.length;j++){
                            permissionArr.push(childPermissions[j]);
                        }
                    }
                    else{
                        permissionArr.push(permissionValue);
                    }
                }
                window.inst_permissionArr = permissionArr;
            }
        });

        $("#layui-unselect").mouseover(function(){
            $("#layui-nav-kun").show();
            $(this).css("background","#EEEEEE");
        })
        $("#layui-unselect").mouseout(function(){
            $("#layui-nav-kun").hide();
            $(this).css("background","#FFFFFF");
        })

    });
    function getDictName(dictCode, dictValue){
        if (window.dict && window.dict[dictCode]){
            var dictMap = window.dict[dictCode];
            for (var i=0;i<dictMap.length;i++){
                var item = dictMap[i];
                if (item.fieldValue == dictValue){
                    return item.valueComment;
                }
            }
        }
        return "";
    }
    function addTab(data) {
        $("#hidden_menu").children().filter("a").remove();
        var linkData = "<a lay-href='" + data.url +"'> "+ data.title+ "</a>";
        var link = $(linkData);
        $("#hidden_menu").append(link);
        $("#hidden_menu a").click();
    }
</script>
</body>
</html>


