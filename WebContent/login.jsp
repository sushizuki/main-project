<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty param.language}">
  <fmt:setLocale value="${param.language}" scope="session"/>
</c:if>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Sushizuki</title>
<meta name="description" content="Sushizuki" />
<meta name="viewport" content="width=device-width, user-scalable=no" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/open-sans-family.css" />
<link rel="stylesheet" href="css/main-red.css" />
</head>
<body>
	<!-- Top bar-->
	<div class="topbar-wrapper">
		<div class="container">
			<!-- Login and User area -->
			<div class="menuextras">
				<div class="extras">
					<ul>
						<li class="shopping-cart-items"><i
							class="glyphicon glyphicon-shopping-cart icon-white"></i> <a
							href="shopping-cart"> <b> <span id="items-in-cart">
										<c:out value="${order.items.size() }"></c:out>
								</span> <fmt:message key="mainMenu.itemsMessage"/>
							</b>
						</a></li>
						<c:choose>
							<c:when test="${user.name != null}">
								<li><c:out value="${user.name}" /></li>
								<li><a href="user?action=doLogout"><i
										class="glyphicon glyphicon-off icon-white"> </i> <fmt:message key="mainMenu.logout"/></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="login"><fmt:message key="mainMenu.login"/></a></li>
							</c:otherwise>
						</c:choose>
						<li>
					        <form>
					            <select id="language" name="language" onchange="submit()">
					                <option value=""><fmt:message key="mainMenu.language"/></option>
					                <option value="pt-BR" ${language == 'pt-BR' ? 'selected' : ''}>Português BR</option>
					                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
					            </select>
					        </form>
						</li>
					</ul>
				</div>
			</div>
			<!-- End Login and User area -->

			<!--Navigation bar -->
			<nav id="mainmenu" class="mainmenu">
				<ul>
					<!-- Image Logo -->
					<li class="logo-wrapper"><a href="/sushizuki"> <img
							src="img/sushi/sushizuki-logo.png" alt="Sushizuki">
					</a></li>
					<li><a href="/sushizuki"><fmt:message key="mainMenu.home"/></a></li>
					<li class="active"><a href="menu"><fmt:message key="mainMenu.menu"/></a></li>
					<li><a href="contact.jsp"><fmt:message key="mainMenu.contacts"/></a></li>
				</ul>
			</nav>
			<!-- End Navigation bar -->
		</div>
		<!-- End container  -->
	</div>
	<!-- End top bar  -->

	<!-- Main Content  -->
	<div class="content container">
		<div class="row">
			<h2><fmt:message key="title.login"/></h2>
		</div>
		<div class="row">
			<div class="col-md-4 col-sm-6 col-xs-12">
				<c:if test="${param.error == 1}">
					<div class="alert alert-danger alert-dismissable" role="alert">
						<strong><fmt:message key="message.error"/></strong> <fmt:message key="message.alert"/>
					</div>
				</c:if>
				<div class="basic-login">
					<form role="form" role="form"
						action="User?action=doLogin&<c:out value='redir=${param.redir}' default="redir=menu"/>"
						method="post">
						<div class="form-group">
							<label for="login-username"><i class="icon-user"></i> <b>Email</b></label>
							<input class="form-control validate" name="email"
								id="login-username" type="text" placeholder="">
						</div>
						<div class="form-group">
							<label for="login-password"><i class="icon-lock"></i> <b><fmt:message key="loginForm.password"/></b></label>
							<input class="form-control validate" name="password"
								id="login-password" type="password" placeholder="">
						</div>
						<div class="form-group">
							<input type="hidden" name="redir" value="${param.redir }">
							<a href="page-password-reset.html" class="forgot-password"><fmt:message key="loginForm.forgot"/></a>
							<br /> <a href="page-register.html"><fmt:message key="loginForm.register"/></a>
							<button type="submit" id="btn-login" class="btn pull-right"><fmt:message key="mainMenu.login"/></button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<div class="footer">
		<div class="container">
			<div class="row">
				<div class="col-footer col-md-4 col-sm-4 col-xs-12">
					<h3><fmt:message key="footer.navigation"/></h3>
					<ul class="no-list-style footer-navigate-section">
						<li><a href="index.jsp"><fmt:message key="mainMenu.home"/></a></li>
						<li><a href="menu"><fmt:message key="mainMenu.menu"/></a></li>
						<li><a href="contact.jsp"><fmt:message key="mainMenu.contacts"/></a></li>
					</ul>
				</div>

				<div class="col-footer col-md-4 col-sm-4 col-xs-12">
					<h3><fmt:message key="footer.contact"/></h3>
					<p class="contact-us-details">
						<b><fmt:message key="footer.phone"/>:</b> (61) 8636 8825<br /> <b>Email:</b> <a
							href="mailto:">sushizukiii@gmail.com</a>
					</p>
				</div>

				<div class="col-footer col-md-4 col-sm-4 col-xs-12">
					<h3><fmt:message key="footer.social"/></h3>
					<ul class="footer-stay-connected no-list-style">
						<li><a
							href="https://www.facebook.com/SushiZuki-165405287145692/?fref=ts"
							class="facebook"></a></li>
					</ul>
				</div>

			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="footer-copyright">&copy; 2016 Sushizuki.
						<fmt:message key="footer.city"/></div>
				</div>
			</div>
		</div>
	</div>
	<!-- End footer -->

	<!-- Javascripts -->
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
		//Check if input's content is a valid email
		function isEmail(email) {
			var pattern = new RegExp(/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/);
			return pattern.test(email);
		}

		function doCheck() {
			var allFilled = true;
			var email = "#login-username";
			$('.validate').each(function() {
				if ($(this).val() == '' || !isEmail($(email).val())) {
					allFilled = false;
					return false;
				}
			});
			$('button[type=submit]').prop('disabled', !allFilled);
		}

		$(document).ready(function() {
			$('input').keyup(doCheck).focusout(doCheck);
			if ($('#login-password').val().length === 0) {
				$('#btn-login').prop('disabled', true);
			}
		});
	</script>
</body>
</html>
