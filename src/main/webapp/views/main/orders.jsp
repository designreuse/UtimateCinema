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
              <th>金额</th></tr>
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


<%@ include file="../templates/footer.jsp"%>
<script>
  var $alertRow = $('#alert-row');
  var $alertMsg = $('#alert-msg');

  var pageSize = 20;
  var $container = $('.list-table-body');
  var get_url = "<%=basePath%>/orders/get";

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
        $container.append('<tr><td colspan="6"><h2>无订单</h2></tr>');
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



  });
</script>
</body>
</html>