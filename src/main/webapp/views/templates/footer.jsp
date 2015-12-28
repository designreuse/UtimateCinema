<%-- Created by rayn on 05/14 2015 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="footer">
    <div class="container">
        <p class="text-muted">Copyright © 2015 Rayn</p>
    </div>
</div>
<script src="<c:url value='/static/bootstrap/js/jquery-1.11.3.min.js' />" ></script>
<script src="<c:url value='/static/bootstrap/js/bootstrap.min.js' />" ></script>
<script>
    $(document).ready(function() {
        $('.navbar-nav > li > a').each(function () {
            if ($($(this))[0].href == String(window.location)) {
                $(this).parent().addClass('active');
            } else {
                $(this).parent().removeClass('active');
            }
        });
    });
</script>