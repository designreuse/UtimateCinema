<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form class="form-horizontal" action="#" id="hallForm">
  <div class="form-group">
    <label  class="col-sm-2 control-label">影厅号</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="name" placeholder="" name="name">
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">行数</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="rowSize" placeholder="0" name="rowSize">
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">列数</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="columnSize" placeholder="0" name="columnSize">
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