<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../templates/header.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/styles/common.css' />" />
</head>

<%@ include file="../templates/navbar.jsp" %>

<div class="container">
    <div class="row" id="alert-row" style="display: none;">
        <div class="col-sm-12">
            <div class="alert alert-danger alert-dismissible" >
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-ban"></i> 错误</h4>
                <p id="alert-msg"></p>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary list-box">
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-list"></i> 用户管理</h3>
                    <div class="box-tools">
                        <button class="btn btn-default btn-sm to_refresh"><i class="fa fa-refresh"></i> &nbsp; 刷新列表</button>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <div class="list-box-controls">
                        <button class="btn btn-default btn-sm to_delete_item" data-toggle="modal" data-target="#delModal">
                            <i class="fa fa-trash-o"></i> 删除
                        </button>
                        <button class="btn btn-default btn-sm to_edit_item" data-toggle="modal" data-target="#addModal">
                            <i class="fa fa-pencil-square-o"></i> 修改</button>
                        <div class="pull-right">
                            <div class="btn-group">
                                <button class="btn btn-default btn-sm prev-page"><i class="fa fa-chevron-left"></i></button>
                                <button class="btn btn-default btn-sm next-page"><i class="fa fa-chevron-right"></i></button>
                            </div>
                        </div>
                    </div>
                    <table class="table table-bordered table-hover table-striped list-table">
                        <thead>
                        <tr><th>选择</th><th>姓名</th><th>权限</th><th>邮箱</th><th>手机</th><th>性别</th><th>注册时间</th></tr>
                        </thead>
                        <tbody class="list-table-body"></tbody>
                    </table>
                </div>
                <div class="overlay">
                    <i class="fa fa-refresh fa-spin"></i>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="delModal" style="margin-top: 50px;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">警告</h4>
            </div>
            <div class="modal-body">
                <p style="font-size: 1.4em;">是否确定删除当前选定用户！</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger btn-flat delete_item">确定删除</button>
                <button type="button" class="closeModal btn btn-default btn-flat" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加影片</h4>
            </div>
            <div class="modal-body">
                <%@ include file="form/user_form.jsp" %>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary add-or-edit-item">提交</button>
            </div>
        </div>
    </div>
</div>

<%@ include file="../templates/footer.jsp"%>
<script>
    var $alertRow = $('#alert-row');
    var $alertMsg = $('#alert-msg');

    var pageSize = ${pageSize};
    var $container = $('.list-table-body');

    var check_id = 'input[name="user"]:checked';
    var get_url = "<%=basePath%>/users/get";
    var one_url = "<%=basePath%>/users/one";
    var del_url = "<%=basePath%>/users/del";
    var edit_url = "<%=basePath%>/users/edit";


    function generate_item(user) {
        var ret = "<tr id='user" + user.id + "'>";
        ret += "<td><label><input type='radio' name='user' value='" + user.id + "'></label></td>";
        ret += "<td><b>" + user.username + "</b></td>";
        if (user.admin) ret += "<td><label class='label label-danger'>管理员</label></td>";
        else            ret += "<td><label class='label label-default'>用户</label></td>";
        ret += "<td>" + user.email + "</td>";
        ret += "<td>" + user.phone + "</td>";
        if (user.sex)   ret += "<td>男</td>";
        else            ret += "<td>女</td>";
        ret += "<td>" + user.registerTime + "</td>";
        ret += "</tr>";
        return ret;
    }
    function get_list($container, url, page, pageSize) {
        var $overlay = $('.list-box > div.overlay');
        var data = {
            "page": page,
            "pageSize": pageSize
        };
        var $prev = $('.prev-page');
        var $next = $('.next-page');

        $overlay.fadeIn(300);
        $.post(url, data).success(function (response) {
            var totalPage = response.totalPage;
            var page = response.page;
            var items = response.items;
            var length = items.length;
            $container.empty();
            if (length == 0) {
                $container.append('<tr><td colspan="6"><h2>无用户</h2></tr>');
            } else {
                for (var i = 0; i < length; ++i) {
                    $container.append(generate_item(items[i]));
                }
            }
            $container.data('page', page);
            $overlay.fadeOut(300);

            if (page == 1) {
                $prev.hide();
            } else {
                $prev.show();
                $prev.val(page - 1);
            }
            if (page == totalPage) {
                $next.hide();
            } else {
                $next.show();
                $next.val(page + 1);
            }
        }).error(function() {
            $container.empty();
            $container.append('<tr><td colspan="6"><h2>加载失败</h2></tr>');
            $overlay.fadeOut(300);
            $prev.hide();
            $next.hide();
        });
    }
    function delete_item(url, checked) {
        var data = { 'id': checked };
        $.post(url, data).success(function (response) {
            $('.closeModal').click();
            if (response.ret == 'ok') {
                $('#user' + checked).fadeOut(1200, function(){ $(this).remove(); });
            } else {
                $alertMsg.html(response.error);
                $alertRow.fadeIn();
            }
        });
    }
    function add_or_edit_item(url, opt, id) {
        var formData = new FormData($('#filmForm')[0]);
        formData.append("opt", opt);
        if (opt == "edit") {
            formData.append("id", id);
        }
        $.ajax({
            url: url,
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                var $response = $('#response');
                if (response.ret == "ok") {
                    $('#addModal').modal('hide');
                    get_list($container, get_url, 1, pageSize);
                } else {
                    $('#response-text').html('上传失败');
                    $response.fadeIn();
                }
            },
            error: function() {
                $('#addModal').modal('hide');
                $alertMsg.html("上传失败");
                $alertRow.fadeIn();
            }
        });
    }
    function get_one(url, id) {
        var data = { "id" : id };
        var ret = null;
        $.post(url, data, function(response) {
            ret = response.item;
        }).success(function() {
            if (ret) {
                for (var key in ret) {
                    var $input = $('#' + key);
                    if (key == "actors") {
                        $input.tagsinput('removeAll');
                        $input.tagsinput('add', ret[key]);
                    } else {
                        $input.val(ret[key]);
                    }
                }
            }
        });
    }

    $(document).ready(function() {

        get_list($container, get_url, 1, pageSize);

        $('button.to_refresh').click(function() {
            get_list($container, get_url, 1, pageSize);
        });

        $('.prev-page').click(function () {
            var page = parseInt($(this).val());
            get_list($container, get_url, page, pageSize);
        });

        $('.next-page').click(function () {
            var page = parseInt($(this).val());
            get_list($container, get_url, page, pageSize);
        });

        $('.to_delete_item').click(function () {
            var $check = $(check_id);
            if ($check.length <= 0) {
                return false;
            }
        });

        $('.delete_item').click(function () {
            var check = $(check_id).val();
            delete_item(del_url, check);
        });

        $('.to_edit_item').click(function() {
            var $check = $(check_id);
            if ($check.length <= 0) {
                return false;
            }
            var id = $check.val();
            get_one(one_url, id);
            $('.add-or-edit-item').data("opt", "edit").data("id", id);
        });

        $('.add-or-edit-item').click(function() {

            add_or_edit_item(edit_url, $(this).data("opt"), $(this).data("id"));
            return false;
        });

    });
</script>
</body>
</html>