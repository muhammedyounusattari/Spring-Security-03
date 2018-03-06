<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<html>
<head>
<script src="webjars/jquery/jquery.min.js"></script>
<script src="webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Login Page</title>
<link rel="stylesheet" href="webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<h1>Welcome <c:out value="${name }"></c:out></h1>
<a href="./logoutUrl">Click here to logout</a>
</body>
</html>