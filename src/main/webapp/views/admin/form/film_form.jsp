<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="filmForm" class="form-horizontal" enctype="multipart/form-data">
  <div class="form-group">
    <label for="filmName" class="control-label col-sm-2">影片名称</label>
    <div class="col-sm-9">
      <input type="text" id="filmName" class="form-control" name="filmName">
    </div>
  </div>

  <div class="form-group">
    <label for="director" class="control-label col-sm-2">导演</label>
    <div class="col-sm-9">
      <input type="text" id="director" class="form-control" name="director">
    </div>
  </div>

  <div class="form-group">
    <label for="actors" class="control-label col-sm-2">演员</label>
    <div class="col-sm-9">
      <input type="text" id="actors" class="form-control" name="actors"
             data-role="tagsinput" width="100%">
    </div>
  </div>

  <div class="form-group">
    <label for="language" class="control-label col-sm-2">语言</label>
    <div class="col-sm-9">
      <input type="text" id="language" class="form-control" name="language">
    </div>
  </div>

  <div class="form-group">
    <label for="length" class="control-label col-sm-2">电影长度</label>
    <div class="col-sm-9">
      <input type="text" id="length" class="form-control" name="length">
    </div>
  </div>

  <div class="form-group">
    <label for="premiereDate" class="control-label col-sm-2">上映日期</label>
    <div class="col-sm-9">
      <input type="date" id="premiereDate" class="form-control" name="premiereDate">
    </div>
  </div>

  <div class="form-group">
    <label for="poster" class="control-label col-sm-2">影片海报</label>
    <div class="col-sm-9">
      <input type="file" id="poster" class="form-control" name="poster">
    </div>
  </div>

  <div class="form-group">
    <label for="intro" class="control-label col-sm-2">影片详情</label>
    <div class="col-sm-9">
      <textarea id="intro" class="form-control" name="intro" rows="4" ></textarea>
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
