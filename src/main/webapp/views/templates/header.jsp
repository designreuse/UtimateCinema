<%-- Created by rayn on 12/26 2015 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${title}</title>
  <link rel="stylesheet" href="<c:url value='/static/bootstrap/css/bootstrap.min.css' />" />
  <link rel="stylesheet" href="<c:url value='/static/plugin/Font-Awesome/css/font-awesome.min.css' />" />
  <link rel="stylesheet" href="<c:url value='/static/AdminLTE/css/AdminLTE.min.css' />" />
  <link rel="stylesheet" href="<c:url value='/static/AdminLTE/css/skins/_all-skins.min.css' />" />

  <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.6.2/html5shiv.js"></script>
  <![endif]-->