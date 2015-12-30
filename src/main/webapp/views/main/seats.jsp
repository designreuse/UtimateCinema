<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ include file="../templates/header.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/styles/common.css' />" />
</head>
<%@ include file="../templates/navbar.jsp" %>

<div class="container">
  <div class="row">
    <div class="col-sm-12">
      <div class="box box-primary">
        <div class="box-header">
        </div>
        <div class="box-body">
          <div class="col-sm-3 text-center">
            <c:choose>
              <c:when test="${film.poster == null}">
                <div style="height: 30px;">
                  <h2>无封面</h2>
                </div>
              </c:when>
              <c:otherwise>
                <img src="<s:url action="posterAction"><s:param name="id">${film.id}</s:param></s:url>"
                     style="max-width: 100%; max-height: 210px;">
              </c:otherwise>
            </c:choose>
            <table class="table" style="margin-top: 20px;text-align: left">
              <tr><td width="40%">电影名</td><td>${film.filmName}</td></tr>
              <tr><td>片长</td><td>${film.length}分钟</td></tr>
              <tr><td>影厅</td><td>${sale.cinemaHall.name}</td></tr>
              <tr><td>场次</td><td><fmt:formatDate value="${sale.startTime}" pattern="yyyy-MM-dd HH:mm"/> 开始</td></tr>
              <tr><td>价钱</td><td>${sale.money}元/座</td></tr>
            </table>
          </div>
          <div class="col-sm-9 seat-table">
            <div class="col-sm-12 seat-tip">
              <span class="seat-item active"></span><span>可选座位</span>
              <span class="seat-item disabled"></span><span>已售座位</span>
              <span class="seat-item selected"></span><span>已选座位</span>
            </div>
            <div class="col-sm-12 seat-content">
              <c:forEach begin="1" end="${sale.cinemaHall.rowSize}" varStatus="status1">
                <p class="seat-row" id="seat-row${status1.count}">
                  <span class="row-num">
                    <span class="label label-default row-num">${status1.count}</span>
                  </span>
                  <c:forEach begin="1" end="${sale.cinemaHall.columnSize}" varStatus="status2">
                    <a href="javascript:;" class="seat-item active"
                       data-row="${status1.count}" data-col="${status2.count}"></a>
                  </c:forEach>
                </p>
              </c:forEach>
            </div>
            <div class="col-sm-12 order-content">
              <div class="col-sm-8">
                <table class="table">
                  <tr><td width="20%">已选座位</td>
                    <td id="seat-choose"></td>
                  </tr><tr><td>总价</td>
                    <td id="order-money"></td>
                  </tr>
                </table>
              </div>
              <div class="col-sm-4" style="height: 100px;vertical-align: middle;">
                <a class="btn btn-danger btn-lg" data-toggle="modal"
                   data-target="#orderModal">提交订单</a>
              </div>
            </div>
          </div>
        </div>
        <div class="box-footer with-border no-padding">
        </div>
      </div>

    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="orderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body">
        是否确定购票，成功后不可退票！
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-danger" id="submitOrder">确认</button>
      </div>
    </div>
  </div>
</div>

<%@ include file="../templates/footer.jsp"%>
<script>
  var $seat_choose = $('#seat-choose');
  var $order_money = $('#order-money');
  var saleId = ${sale.id};
  var single_money = ${sale.money};

  function makeDisabled() {
    var url = "<%=basePath%>/sale/seat/get";
    var data = { "saleId": saleId };
    $.post(url, data, function(response) {
      if (response.ret == "ok") {
        var items = response.items;
        for (var i = 0; i < items.length; ++i) {
          var row = items[i]['rowNumber'];
          var col = items[i]['colNumber'];
          $('.seat-content').children('#seat-row' + row)
                  .first().find('.seat-item').each(function() {
                    var this_row = $(this).data("row");
                    var this_col = $(this).data("col");
                    if (this_row == row && this_col == col) {
                      $(this).addClass("disabled");
                    }
                  });
        }
      }
    });
  }

  function addSeatTicket(row, col) {
    var html = "<label class='label bg-green seat-ticket' data-row='" + row + "' "
            + "data-col='" + col + "'>" + row + " 排 " + col + " 座</label>";
    var original = $seat_choose.html();
    $seat_choose.html(original + html);
  }
  function removeSeatTicket(row, col) {
    $('.seat-ticket').filter(function() {
      var this_row = $(this).data("row");
      var this_col = $(this).data("col");
      if (this_row == row && this_col == col) {
        $(this).remove();
      }
    });
  }

  function calTotalMoney() {
    var $selected = $('.seat-content .selected');
    var total = single_money * $selected.length;
    $order_money.html("<span>" + total + " </span>元");

  }

  function listenSeatClick() {
    $('.seat-content .seat-item').on('click', function() {
      if ($(this).hasClass("disabled")) {
        return false;
      }
      var $selected = $('.seat-content .selected');
      var has = $(this).hasClass("selected");
      if ($selected.length >= 4 && !has) {
        alert("最多只能选四个座位");
        return false;
      }
      var row = $(this).data("row");
      var col = $(this).data("col");
      if (has) {
        $(this).removeClass("selected");
        removeSeatTicket(row, col);
      } else {
        $(this).addClass("selected");
        addSeatTicket(row, col);
      }
      calTotalMoney();
    });
  }

  function listenSubmitOrder() {
    $('#submitOrder').on("click", function() {
      var url = "<%=basePath%>/order/add";
      var $selected = $('.seat-content .selected');
      var seats = [];
      $selected.each(function(i) {
        seats.push({
          "row" : $(this).data("row"),
          "col" : $(this).data("col")
        });
      });
      var data = {
        "saleId": saleId,
        "seats": seats
      };
      $.post(url, { "request": JSON.stringify(data) }, function(response) {
        if (response.ret == "ok") {
          $('#orderModal').modal('hide');

        }
      });
    });
  }

  $(document).ready(function() {

    makeDisabled();
    calTotalMoney();
    listenSeatClick();
    listenSubmitOrder();

  });
</script>
</body>
</html>