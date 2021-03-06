<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Login - Code Quiz - Test Your Programming Knowledge!</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<style type="text/css">
body {
	background:
		url("${pageContext.request.contextPath}/images/background.jpg");
}
.user-icon {
	top: 110px;
	/* Positioning fix for slide-in, got lazy to think up of simpler method. */
	background: rgba(65, 72, 72, 0.75)
		url('${pageContext.request.contextPath}/images/user-icon.png')
		no-repeat center;
}
.pass-icon {
	top: 163px;
	background: rgba(65, 72, 72, 0.75)
		url('${pageContext.request.contextPath}/images/pass-icon.png')
		no-repeat center;
}
</style>
</head>
<body>
	<div class='logo'>
		<a href='${pageContext.request.contextPath}'><img height=90%
			width=90% src="${pageContext.request.contextPath}/images/logo.png" /></a>
	</div>
	<div id='navbar'>
		<ul>
			<li><a href='${pageContext.request.contextPath}'><span>Home</span></a></li>
			<li class='active'><a
				href='${pageContext.request.contextPath}/login'><span>Login</span></a></li>
			<li><a href='${pageContext.request.contextPath}/register'><span>Register</span></a></li>
			<li><a href='${pageContext.request.contextPath}/userPanel'><span>My
						Results</span></a></li>
			<li><a href='mailto:berksoysal@gmail.com'><span>Feedback</span></a></li>
			<li><a href='https://ca.linkedin.com/in/berksoysal'
				target="_blank"><span>Contact us</span></a></li>
		</ul>
	</div>

	<!--  If user is logged in hide the register panel-->
	<c:if test='${not empty sessionScope.user}'>

		<a href='${pageContext.request.contextPath}/userPanel'>
			<div class="button nameuser">Logged as, ${sessionScope.user}</div>
		</a>

		<a href='${pageContext.request.contextPath}/logout'>
			<div class="button logout">Logout</div>
		</a>

		<div id="wrapper">

			<form name="login-form" class="login-form">
				<div class="header" style="height: 60px;">
					<img style="float: left;" height="30" width="30"
						src="${pageContext.request.contextPath}/images/warn.png" />
					<h1 style="float: right;">You're already logged in,
						${sessionScope.user} !</h1>
				</div>

			</form>

		</div>

	</c:if>

	<!--  If user is not logged in -->

	<c:if test='${empty sessionScope.user}'>

		<a href='${pageContext.request.contextPath}/register'><div
				class="button nameuser"
				style="width: 500px; position: relative; left: 30%; top: 30px">
				If you don`t have an account, please click here to register</div></a>

		<div id="wrapper">

			<form name="login-form" class="login-form" action="checkLogin"
				method="post">

				<div class="header">
					<h1>Login</h1>
					
					<h3><c:out value="${sessionScope.errorMessage}"/></h3>
					<h4><c:out value="${sessionScope.tryAgain}"/></h4>
					<span></span>
				</div>

				<div class="content">
					<input name="username" type="text" class="input username"
						placeholder="Username" required />
					<div class="user-icon"></div>
					<input name="password" type="password" class="input password"
						placeholder="Password" required />
					<div class="pass-icon"></div>
				</div>

				<div class="footer">
					<input type="submit" name="submit" value="Login" class="button" />

				</div>

			</form>

		</div>
		
			




		<div class="gradient"></div>
	</c:if>

</body>
</html>