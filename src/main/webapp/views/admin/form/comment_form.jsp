<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--评论表单-->
<form id="commentForm" class="form-horizontal" enctype="multipart/form-data">
  <div class="form-group">
    <!-- label的for属性必须和textarea的属性一样-->
    <label for="commentName" class="control-label col-sm-2">评价内容</label>
    <div class="col-sm-9">
      <!--name属性必须和Action中的属性名称一致-->
      <textarea id="commentName" class="form-control" name="commentName" rows="4" ></textarea>
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