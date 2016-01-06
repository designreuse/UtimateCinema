<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form class="form-horizontal" action="#" id="saleForm">
  <div class="form-group">
    <label  class="col-sm-2 control-label">开始时间</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="startTime" name="startTime">
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">结束时间</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="endTime" name="endTime">
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">价钱</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="money" name="money">
    </div>
  </div>

  <div class="form-group">
    <label  class="col-sm-2 control-label">影厅</label>
    <div class="col-sm-10">
      <select class="form-control" name="hallId" id="hallId">
        <c:forEach var="hall" items="${hallList}">
          <option value="${hall.id}">${hall.name}</option>
        </c:forEach>
      </select>
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