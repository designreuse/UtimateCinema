<%-- Created by rayn on 05/14 2015 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="footer">
    <div class="container">
        <p class="text-muted">Copyright © 2015</p>
    </div>
</div>
<script src="<c:url value='/static/bootstrap/js/jquery-1.11.3.min.js' />"></script>
<script src="<c:url value='/static/bootstrap/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/static/AdminLTE/js/app.min.js' />"></script>
<script src="<c:url value='/static/plugin/slimScroll/jquery.slimscroll.min.js' />"></script>
<script>
    $(document).ready(function () {
        $('.navbar-nav > li > a').each(function () {
            if ($($(this))[0].href == String(window.location)) {
                $(this).parent().addClass('active');
            } else {
                $(this).parent().removeClass('active');
            }
        });
    });
    function modifyUser() {
        var pwd1 = $('#modifyPassword').val();
        var pwd2 = $('#modifyPassword2').val();
        if (pwd1 != pwd2) {
            $('#alert-text').html("两次密码输入不统一");
            $('#response').fadeIn();
            return false;
        }
        $.ajax({
            url: "<%=request.getContextPath()%>/register/modify",
            type: "post",
            dataType: "json",
            data: $("#modifyForm").serialize(),
            success: function (response) {
                if (response.ret == "fail") {
                    $('#alert-text').html(response.error);
                    $('#response').fadeIn();
                } else {
                    window.location.href = response.url;
                }
            }
        });
        return false;
    }
</script>