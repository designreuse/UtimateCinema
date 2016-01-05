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
          <div class="col-sm-4 text-center">
            <c:choose>
              <c:when test="${film.poster == null}">
                <div style="height: 50px;">
                  <h2>无封面</h2>
                </div>
              </c:when>
              <c:otherwise>
                <img src="<s:url action="posterAction"><s:param name="id">${film.id}</s:param></s:url>"
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
          <table class="table table-bordered list-table with-border" style="vertical-align: middle">
            <thead>
            <tr><th>开始时间</th><th>结束时间</th><th>影厅</th><th>价钱</th><th>操作</th></tr>
            </thead>
            <tbody>
            <c:choose>
              <c:when test="${saleSet.size() == 0}">
                <tr><td colspan="5"><h3>没有放映场次</h3></td></tr>
              </c:when>
              <c:otherwise>
                <c:forEach var="sale" items="${saleSet}">
                  <tr id="sale${sale.id}">
                    <td><fmt:formatDate value="${sale.startTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td><fmt:formatDate value="${sale.endTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>${sale.cinemaHall.name}</td>
                    <td>${sale.money}</td>
                    <td><a href="<%=basePath%>/sale/seat?saleId=${sale.id}" class="btn btn-primary btn-sm">选座</a></td>
                  </tr>
                </c:forEach>
              </c:otherwise>
            </c:choose>
            </tbody>

          </table>
          <table class="table table-bordered list-table with-border" style="vertical-align: middle">
            <thead>
            <tr><th style="background: #3C8DBC">评论详情</th></tr>
            </thead>
            <tbody>
            <c:choose>
              <c:when test="${commentSet.size() == 0}">
                <tr></tr>
              </c:when>
              <c:otherwise>
                <c:forEach var="comment" items="${commentSet}">
                  <tr id="comment${comment.id}">
                  <th class="bs-example">
                    <div class="media">
                      <div class="media-left">
                        <a href="#">
                          <img class="media-object" data-src="holder.js/64x64" alt="64x64" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA2NCA2NCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+PCEtLQpTb3VyY2UgVVJMOiBob2xkZXIuanMvNjR4NjQKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNTIwMWMxMGQ5MCB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1MjAxYzEwZDkwIj48cmVjdCB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSIxNC41IiB5PSIzNi41Ij42NHg2NDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" data-holder-rendered="true" style="width: 64px; height: 64px;">
                          <h5>***</h5>
                        </a>
                      </div>
                      <div class="media-body">
                        <h4 class="media-heading" style="text-align: left">
                          <fmt:formatDate value="${comment.postTime}" pattern="yyyy-MM-dd"/>
                        </h4>
                        <h5 style="text-align: left">
                          ${comment.content}
                        </h5>
                      </div>
                    </div>
                  </th>
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

<%@ include file="../templates/footer.jsp"%>
<script>
  $(document).ready(function() {
  });
</script>
</body>
</html>