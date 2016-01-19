<%-- Created by rayn on 05/23 2015 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body class="skin-red layout-top-nav fixed">
<header class="main-header">
  <nav class="navbar navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <a href="<%=basePath%>/index" class="navbar-brand">电影订票系统</a>
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
          <i class="fa fa-bars"></i>
        </button>
      </div>

      <div class="collapse navbar-collapse" id="navbar-collapse">
        <ul class="nav navbar-nav">
          <c:choose>
            <c:when test="${sessionScope.current_user.admin}">
              <li><a href="<%=basePath%>/admin/users">用户管理</a></li>
              <li><a href="<%=basePath%>/admin/films">影片管理</a></li>
              <li><a href="<%=basePath%>/admin/halls">影厅管理</a></li>
              <li><a href="<%=basePath%>/admin/orders">所有订单</a></li>
            </c:when>
            <c:otherwise>
              <li><a href="<%=basePath%>/index">最近电影 <span class="sr-only">(current)</span></a></li>
              <c:if test="${sessionScope.current_user != null}">
                <li><a href="<%=basePath%>/orders/self">我的订单</a></li>
              </c:if>
            </c:otherwise>
          </c:choose>
        </ul>
        <ul class="nav navbar-nav navbar-right">
        <c:choose>
          <c:when test="${sessionScope.current_user == null}">
            <li><a href="<%=basePath%>/login">登录</a></li>
          </c:when>
          <c:otherwise>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"  role="button"
                 aria-haspopup="true" aria-expanded="false"> <i class="fa fa-user"></i>
                  ${sessionScope.current_user.username} <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li role="separator" class="divider"></li>
                <li><a href="<%=basePath%>/logout">注销</a></li>
                <li><a data-toggle="modal" data-target="#modifyModal" >修改信息</a></li>
              </ul>
            </li>
          </c:otherwise>
        </c:choose>
        </ul>
      </div>
    </div>
  </nav>
</header>

<!-- Modal -->
<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改密码</h4>
      </div>
      <div class="modal-body">
        <%@ include file="../admin/form/modify_form.jsp"%>       <!--引入了一个modify form-->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary " onclick="modifyUser()">确认修改</button> <!--midifyuser 脚本在footer中-->
      </div>
    </div>
  </div>
</div>