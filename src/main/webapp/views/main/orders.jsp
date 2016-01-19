<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../templates/header.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/styles/common.css' />" />
</head>

<%@ include file="../templates/navbar.jsp" %>

<div class="container">
  <div class="row">
    <div class="col-sm-12">
      <div class="box box-primary list-box">
        <div class="box-header with-border">
          <h3 class="box-title"><i class="fa fa-list"></i> 我的订单</h3>
          <div class="box-tools">
            <button class="btn btn-default btn-sm to_refresh"><i class="fa fa-refresh"></i> &nbsp; 刷新列表</button>
          </div>
        </div>
        <div class="box-body no-padding">
          <table class="table table-bordered table-hover table-striped list-table">
            <thead>
            <tr><th>影名</th><th>放映时间</th><th>订单时间</th><th>影厅</th><th>座位</th>
              <th>金额</th><th>操作</th></tr>
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

<!-- Modal -->
<div class="modal fade" id="commentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">评价</h4>
      </div>
      <div class="modal-body">
        <%@ include file="../admin/form/comment_form.jsp" %>         <!--include评论表-->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary " onclick="add_comment()">提交</button>
      </div>
    </div>
  </div>
</div>
<%@ include file="../templates/footer.jsp"%>
<script>
  var $alertRow = $('#alert-row');
  var $alertMsg = $('#alert-msg');

  var pageSize = 20;
  var $container = $('.list-table-body');
  var get_url = "<%=basePath%>/orders/get";
  var add_comment_url = "<%=basePath%>/orders/addComment";
  var filmId;
  var orderId;
  //设置评价订单的电影id
  function setFilmId(id1,id2){
    filmId = id1;
    orderId = id2;
  }



  function generate_item(order) {
    var ret = "<tr id='order" + order.id + "'>";
    ret += "<td><b>" + order.cinemaSale.film.filmName + "</b></td>";
    ret += "<td><b>" + order.cinemaSale.startTime + "</b></td>";
    ret += "<td>" + order.orderTime + "</td>";
    ret += "<td>" + order.cinemaSale.cinemaHall.name + "</td>";
    ret += "<td>";
    for (var i = 0; i < order.seats.length; ++i) {
      var seat = order.seats[i];
      ret += "<label class='label bg-green'>" + seat.rowNumber + " 排" + seat.colNumber + " 座</label>";
    }
    ret += "</td>";
    ret += "<td>" + order.cinemaSale.money * order.seats.length + "</td>";
    <!-- 添加评价按钮-->
    ret += "<td><button  id='bt"+order.id+"' class='btn btn-primary btn-flat btn-sm to_intro is-comment' data-toggle='modal' data-target='#commentModal'" +
    "onclick='setFilmId("+order.cinemaSale.film.id+","+order.id+")'>评价</button></td>";
    ret += "</tr>";

    return ret;
  }

  function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
      month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
      strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
  }


  //判断评价按钮是否隐藏
  function hideOrShow(order){
    //如果未评论，并且当前时间大于订单结束时间，则显示按钮
    if(!order.comment && checkEndTime(order.orderTime)){
      $("#bt"+(order.id)).show();
    }
    else{
      $("#bt"+(order.id)).hide();
    }
  }

  //比较时间大小
  function checkEndTime(orderTime){
    var ednTime = orderTime;
    var end=new Date(ednTime.replace("-", "/").replace("-", "/"));
    var nowTime = getNowFormatDate();
    var now=new Date(nowTime.replace("-", "/").replace("-", "/"));
    //当前时间小于订单结束时间
    if(now < end){
      return false;
    }
    return true;
  }


  //增加评论
  function add_comment() {
    //把表单格式转换
    var formData = new FormData($('#commentForm')[0]);
    //增加参数filmId
    formData.append("filmId", filmId);
    formData.append("orderId", orderId);
    $.ajax({
      url: add_comment_url,                  //ajax 异步刷新
      type: "post",
      data: formData,
      processData: false,
      contentType: false,
      success: function(response) {
        if (response.ret == "ok") {
          $('#commentModal').modal('hide');
          get_list($container, get_url, 1, pageSize);    //列表重取
        } else{
          $('#response-text').html('上传失败');
          $('#response').fadeIn();
        }
      },
      error: function() {
        $('#commentModal').modal('hide');
        $alertMsg.html("上传失败");
        $alertRow.fadeIn();
      }
    });
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
        $container.append('<tr><td colspan="6"><h2>无订单</h2></tr>');
      } else {
        for (var i = 0; i < length; ++i) {
          $container.append(generate_item(items[i]));
          hideOrShow(items[i]);
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

  $(document).ready(function() {

    get_list($container, get_url, 1, pageSize);                //获取列表

    $('button.to_refresh').click(function() {                 //刷新，列表重取
      get_list($container, get_url, 1, pageSize);
    });

    $('.prev-page').click(function () {                        //前一页
      var page = parseInt($(this).val());
      get_list($container, get_url, page, pageSize);
    });

    $('.next-page').click(function () {                        //后一页
      var page = parseInt($(this).val());
      get_list($container, get_url, page, pageSize);
    });



  });
</script>
</body>
</html>