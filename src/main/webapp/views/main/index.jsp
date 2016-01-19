<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ include file="../templates/header.jsp"%>                  <!--引入header，里面定义了一些bootstrap头文件-->
<link rel="stylesheet" href="<c:url value='/static/styles/common.css' />" />
</head>

<%@ include file="../templates/navbar.jsp" %>                  <!--引入导航栏-->

<div class="container">
<div class="row">
    <div class="col-sm-12">
        <h2>最近电影</h2>
    </div>
</div>

<div class="row">
<c:forEach var="film" items="${filmList}">                 <!--循环放置海报-->
    <div class="col-sm-4 col-md-3 col-lg-2">
        <div class="thumbnail">
            <img src="<s:url action="posterAction"><s:param name="id">${film.id}</s:param></s:url>">
            <div class="caption">
                <h4>${film.filmName}</h4>
                <p>
                    <a href="<%=basePath%>/film/sales?filmId=${film.id}"
                       class="btn btn-primary" role="button">订票</a>      <!--重新url，参数filmid-->
                </p>
            </div>
        </div>
    </div>
</c:forEach>

</div>
</div>
<%@ include file="../templates/footer.jsp"%>             <!--引入footer-->
</body>
</html>