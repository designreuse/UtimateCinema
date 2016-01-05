<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form class="form-modify form-horizontal" id="modifyForm">
    <div class="form-group">
        <label for="inputUsername" class="control-label col-sm-3">用户名</label>
        <div class="col-sm-8">
            <input type="text" id="inputUsername" class="form-control" name="username" value="${sessionScope.current_user.username}" readonly
                   required autofocus>
        </div>
    </div>
    <div class="form-group">
        <label for="modifyPassword" class="control-label col-sm-3">密码</label>
        <div class="col-sm-8">
            <input type="password" id="modifyPassword" class="form-control" name="password" value="${sessionScope.current_user.password}"
                   required>
        </div>
    </div>
    <div class="form-group">
        <label for="modifyPassword2" class="control-label col-sm-3">确认密码</label>
        <div class="col-sm-8">
            <input type="password" id="modifyPassword2" class="form-control" name="password2" value="${sessionScope.current_user.password}"
                   required>
        </div>
    </div>
    <div class="form-group">
        <label for="modifyEmail" class="control-label col-sm-3">邮箱</label>
        <div class="col-sm-8">
            <input type="email" id="modifyEmail" class="form-control" name="email" value="${sessionScope.current_user.email}"
                   required>
        </div>
    </div>
    <div class="form-group">
        <label for="modifyPhone" class="control-label col-sm-3">手机</label>
        <div class="col-sm-8">
            <input type="text" id="modifyPhone" class="form-control" name="phone" value="${sessionScope.current_user.phone}"
                   required>
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