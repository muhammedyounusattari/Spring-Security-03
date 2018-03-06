<!DOCTYPE html>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> --%>

<html>
<head>
<script src="webjars/jquery/jquery.min.js"></script>
<script src="webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Login Page</title>
<link rel="stylesheet" href="webjars/bootstrap/css/bootstrap.min.css" />
</head>
<style>
div#box {
	border: 5px solid black;
	margin-top: 159px;
	padding-top: 27px;
	padding-left: 35px;
}
</style>

<body class="container">

	<div id="box">
		<form action="./app" method="post" class="form-horizontal">

			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				<font color="red">  
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
				</font>
			</c:if>

			<div class="form-group">
				<label for="inputEmail3" class="col-sm-1 control-label">Username</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputEmail3" name="username"
						placeholder="Username">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-1 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="inputPassword3" name="password"
						placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<div class="checkbox">
						<label> <input type="checkbox"> Remember me
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<button type="submit" class="btn btn-default">Sign in</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>