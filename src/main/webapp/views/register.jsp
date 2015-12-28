<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>注册</title>

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
  <form class="form-signin form-horizontal" id="registerForm">
    <div class="form-signin-heading"><h2>注册</h2></div>
    <div class="form-group">
      <label for="inputUsername" class="control-label col-sm-3">用户名</label>
      <div class="col-sm-9">
        <input type="text" id="inputUsername" class="form-control" name="username"
               required autofocus>
      </div>
    </div>
    <div class="form-group">
      <label for="inputPassword" class="control-label col-sm-3">密码</label>
      <div class="col-sm-9">
        <input type="password" id="inputPassword" class="form-control" name="password"
               required>
      </div>
    </div>
    <div class="form-group">
      <label for="inputPassword2" class="control-label col-sm-3">确认密码</label>
      <div class="col-sm-9">
        <input type="password" id="inputPassword2" class="form-control" name="password2"
               required>
      </div>
    </div>
    <div class="form-group">
      <label for="inputEmail" class="control-label col-sm-3">邮箱</label>
      <div class="col-sm-9">
        <input type="email" id="inputEmail" class="form-control" name="email"
               required>
      </div>
    </div>
    <div class="form-group">
      <label for="inputPhone" class="control-label col-sm-3">手机</label>
      <div class="col-sm-9">
        <input type="text" id="inputPhone" class="form-control" name="phone"
               required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-3">性别</label>
      <div class="col-sm-9">
        <label class="radio-inline">
          <input type="radio" name="sex" id="inputSex1" value="1"> 男
        </label>
        <label class="radio-inline">
          <input type="radio" name="sex" id="inputSex2" value="0"> 女
        </label>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-3 col-sm-9">
        <button type="submit" class="btn btn-primary ajax-submit">提交</button>
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
      var pwd1 = $('#inputPassword').val();
      var pwd2 = $('#inputPassword2').val();
      if (pwd1 != pwd2) {
        $('#alert-text').html("两次密码输入不统一");
        $('#response').fadeIn();
        return false;
      }
      $.ajax({
        url: "<%=request.getContextPath()%>/register",
        type: "post",
        dataType: "json",
        data: $("#registerForm").serialize(),
        success: function(response) {
          if (response.ret == "fail") {
            $('#alert-text').html(response.error);
            $('#response').fadeIn();
          }
        }
      });
      return false;
    });
  });
</script>
</body>
</html>