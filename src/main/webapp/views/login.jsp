<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>登录</title>

    <link rel="stylesheet" href="<c:url value='/static/bootstrap/css/bootstrap.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/static/styles/start.css' />" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container">
    <form class="form-signin form-horizontal" id="loginForm">
        <div class="form-signin-heading">
            <h2>登录系统</h2>
        </div>
        <div class="form-group">
            <label for="inputUsername" class="control-label col-sm-3">用户名</label>
            <div class="col-sm-8">
                <input type="text" id="inputUsername" class="form-control" name="user.username"
                       required autofocus>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="control-label col-sm-3">密码</label>
            <div class="col-sm-8">
                <input type="password" id="inputPassword" class="form-control" name="user.password"
                       required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-8">
                <div class="checkbox">
                    <label><input type="checkbox" name="rememberMe" value="1"> 记住我</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <button type="submit" class="btn btn-primary ajax-submit">提交</button>
                <a class="btn btn-default" href="<%=basePath%>/register">去注册</a>
            </div>
        </div>
        <div class="col-sm-12" style="display: none;" id="response">
            <div class="alert alert-danger alert-dismissible fade in" role="alert" >
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <p id="alert-text"></p>
            </div>
        </div>
        <div class="clearfix"></div>
    </form>
</div>

<script src="<c:url value='/static/bootstrap/js/jquery-1.11.3.min.js' />" ></script>
<script src="<c:url value='/static/bootstrap/js/bootstrap.min.js' />" ></script>
<script>
    $(document).ready(function() {
        $('.ajax-submit').click(function() {
            $.ajax({
                url: "<%=basePath%>/login/verify",
                type: "post",
                dataType: "json",
                data: $("#loginForm").serialize(),
                success: function(response) {
                    if (response.ret == "fail") {
                        $('#alert-text').html(response.error);
                        $('#response').fadeIn();
                    } else if (response.ret == "ok") {
                        window.location.href = response.url;
                    }
                }
            });
            return false;
        });
    });
</script>
</body>
</html>