<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ include file="../templates/header.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/styles/common.css' />" />
<link rel="stylesheet" href="<c:url value='/static/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css' />" />
</head>
<%@ include file="../templates/navbar.jsp" %>

<div class="container">
  <div class="row">
    <div class="col-sm-12">
      <div class="box box-primary">
        <div class="box-header">
        </div>
        <div class="box-body">
          <div class="col-sm-4 text-center">
            <c:choose>
              <c:when test="${film.poster == null}">
                <div style="height: 50px;">
                  <h2>无封面</h2>
                </div>
              </c:when>
              <c:otherwise>
                <img src="<s:url action="posterAction"><s:param name="id">${filmId}</s:param></s:url>"
                     style="max-width: 100%; max-height: 280px;">
              </c:otherwise>
            </c:choose>

          </div>
          <div class="col-sm-8">
            <table class="table">
              <tr><td width="20%">电影名</td><td>${film.filmName}</td></tr>
              <tr><td>导演</td><td>${film.director}</td></tr>
              <tr><td>主要演员</td><td>${film.actors}</td></tr>
              <tr><td>语言</td><td>${film.language}</td></tr>
              <tr><td>片长</td><td>${film.length}</td></tr>
              <tr><td>首映日期</td><td>${film.premiereDate}</td></tr>
              <tr><td>详情</td><td>${film.intro}</td></tr>
            </table>
          </div>
        </div>
        <div class="box-footer with-border no-padding">
          <div class="list-box-controls">
            <button class="btn btn-default btn-sm to_delete_item" data-toggle="modal" data-target="#delModal">
              <i class="fa fa-trash-o"></i> 删除
            </button>
            <button class="btn btn-default btn-sm to_edit_item" data-toggle="modal" data-target="#addModal">
              <i class="fa fa-pencil-square-o"></i> 修改</button>
            <button class="btn btn-default btn-sm to_add_item" data-toggle="modal" data-target="#addModal">
              <i class="fa fa-pencil-square-o"></i> 添加</button>
          </div>
          <table class="table table-bordered list-table with-border">
            <thead>
            <tr><th>选择</th><th>开始时间</th><th>结束时间</th><th>价钱</th><th>影厅</th></tr>
            </thead>
            <tbody>
            <c:choose>
              <c:when test="${saleSet.size() == 0}">
                <tr><td colspan="5"><h3>没有放映场次</h3></td></tr>
              </c:when>
              <c:otherwise>
                <c:forEach var="sale" items="${saleSet}">
                  <tr id="sale${sale.id}">
                    <td><input type="radio" name="sale" value="${sale.id}"></td>
                    <td><fmt:formatDate value="${sale.startTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td><fmt:formatDate value="${sale.endTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>${sale.money}</td>
                    <td>${sale.cinemaHall.name}</td>
                  </tr>
                </c:forEach>
              </c:otherwise>
            </c:choose>
            </tbody>

          </table>
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
        <p style="font-size: 1.4em;">是否确定删除当前选定放映场次！</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger btn-flat delete_item">确定删除</button>
        <button type="button" class="closeModal btn btn-default btn-flat" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加放映</h4>
      </div>
      <div class="modal-body">
        <%@ include file="form/sale_form.jsp" %>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary add-or-edit-item">提交</button>
      </div>
    </div>
  </div>
</div>

<%@ include file="../templates/footer.jsp"%>
<script src="<c:url value="/static/plugin/bootstrap-datetimepicker/js/moment.js"/>"></script>
<script src="<c:url value="/static/plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"/>">
</script>
<script>
  var $alertRow = $('#alert-row');
  var $alertMsg = $('#alert-msg');

  var check_id = 'input[name="sale"]:checked';
  var one_url = "<%=basePath%>/sales/one";
  var del_url = "<%=basePath%>/sales/del";
  var edit_url = "<%=basePath%>/sales/edit";

  function delete_item(url, checked) {
    var data = { 'id': checked };
    $.post(url, data).success(function (response) {
      $('.closeModal').click();
      if (response.ret == 'ok') {
        $('#sale' + checked).fadeOut(1200, function(){ $(this).remove(); });
      } else {
        $alertMsg.html(response.error);
        $alertRow.fadeIn();
      }
    });
  }
  function add_or_edit_item(url, opt, id) {
    var formData = new FormData($('#saleForm')[0]);
    formData.append("opt", opt);
    formData.append("filmId", ${film.id});
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
        if (response.ret == "ok") {
          $('#addModal').modal('hide');
          //get_list($container, get_url, 1, pageSize);
          location.reload();
        } else {
          $('#response-text').html('上传失败');
          $('#response').fadeIn();
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

    $('#startTime, #endTime').datetimepicker({
      sideBySide: true,
      format: 'YYYY-MM-DD HH:mm'
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
    $('.to_add_item').click(function() {
      $('.add-or-edit-item').data("opt", "add");
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