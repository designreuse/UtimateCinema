<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="filmForm" class="form-horizontal" >
  <div class="form-group">
    <label for="username" class="control-label col-sm-3">用户名</label>
    <div class="col-sm-8">
      <input type="text" id="username" class="form-control" name="username"
             required autofocus>
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword" class="control-label col-sm-3">密码</label>
    <div class="col-sm-8">
      <input type="password" id="inputPassword" class="form-control" name="password"
             required>
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword2" class="control-label col-sm-3">确认密码</label>
    <div class="col-sm-8">
      <input type="password" id="inputPassword2" class="form-control" name="password2"
             required>
    </div>
  </div>
  <div class="form-group">
    <label for="email" class="control-label col-sm-3">邮箱</label>
    <div class="col-sm-8">
      <input type="email" id="email" class="form-control" name="email"
             required>
    </div>
  </div>
  <div class="form-group">
    <label for="phone" class="control-label col-sm-3">手机</label>
    <div class="col-sm-8">
      <input type="text" id="phone" class="form-control" name="phone"
             required>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-3">性别</label>
    <div class="col-sm-8">
      <label class="radio-inline">
        <input type="radio" name="sex" id="inputSex1" value="1"> 男
      </label>
      <label class="radio-inline">
        <input type="radio" name="sex" id="inputSex2" value="0"> 女
      </label>
    </div>
  </div>

  <div class="col-sm-12" style="display: none;" id="response">
    <div class="alert alert-danger alert-dismissible fade in" role="alert" >
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
      <p id="response-text"></p>
    </div>
  </div>
  <div class="clearfix"></div>

</form>
