
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
								</span> itens
							</b>
						</a></li>
						<c:choose>
							<c:when test="${user.name != null}">
								<li><c:out value="${user.name}" /></li>
								<li><a href="user?action=doLogout"><i
										class="glyphicon glyphicon-off icon-white"> </i> Sair</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="login">Login</a></li>
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
					<li class="active"><a href="/sushizuki"><fmt:message key="mainMenu.home"/></a></li>
					<li><a href="menu"><fmt:message key="mainMenu.menu"/></a></li>
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
			<h1><fmt:message key="mainMenu.contacts"/></h1>
		</div>

		<div class="row">

			<!-- Facebook -->
			<div class="col-md-4 col-sm 4 col-xs-12">
				<div class="fb-page"
					data-href="https://www.facebook.com/SushiZuki-165405287145692/?fref=ts"
					data-small-header="true" data-adapt-container-width="true"
					data-hide-cover="false" data-show-facepile="true"
					data-show-posts="false"></div>
				<!-- Title -->
				<div class="facebook">
					<br>
					<h3>Facebook</h3>
				</div>
				<!-- Facebook Description-->
				<div class="description">
					<p><fmt:message key="facebook.message"/></p>
				</div>
			</div>

			<!-- Email -->
			<div class="col-md-4 col-sm 4 col-xs-12">
				<!-- Email Image -->
				<div class="image">
					<img src="img/email.png" alt="Email">
				</div>
				<!-- Email Title -->
				<div class="email">
					<h3>Email</h3>
				</div>
				<!-- Email Description-->
				<div class="description">
					<p>
						<fmt:message key="question.message"/><br>
					</p>
					<p>
						<a href="mailto:">sushizukiii@gmail.com</a>
					</p>
				</div>
			</div>
			<!-- End Email -->

			<!-- Phone -->
			<div class="col-md-4 col-sm 4 col-xs-12">
				<!-- Phone Image -->
				<div class="image">
					<img src="img/whatsapp.png" alt="Telefone">
				</div>
				<!-- Phone Title -->
				<div class="phone">
					<h3><fmt:message key="footer.phone"/></h3>
				</div>
				<!-- Phone Description-->
				<div class="description">
					<p>
						<fmt:message key="footer.phoneandwhatsapp"/><br>
					</p>
					<p>(61) 8636-8825</p>
				</div>
			</div>
			<!-- End Phone -->

		</div>
		<!-- End row -->
	</div>
	<!-- End Main Content -->

	<!-- Footer -->
	<div class="footer">
		<div class="container">
			<div class="row">
				<div class="col-footer col-md-4 col-sm-4 col-xs-12">
					<h3><fmt:message key="footer.navigation"/></h3>
					<ul class="no-list-style footer-navigate-section">
						<li><a href="index.jsp"><fmt:message key="mainMenu.home"/></a></li>
						<li><a href="menu"><fmt:message key="mainMenu.menu"/></a></li>
						<li><a href="contact.jsp"><fmt:message key="footer.contact"/></a></a></li>
					</ul>
				</div>

				<div class="col-footer col-md-4 col-sm-4 col-xs-12">
					<h3><fmt:message key="footer.contact"/></h3>
					<p class="contact-us-details">
						<b><fmt:message key="footer.phone"/></b> (61) 8636 8825<br /> <b>Email:</b> <a
							href="mailto:">sushizukiii@gmail.com</a>
					</p>
				</div>

				<div class="col-footer col-md-4 col-sm-4 col-xs-12">
					<h3>Social</h3>
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
	<script src="js/jquery.sequence-min.js"></script>
	<!-- Slider -->
	<script src="js/template.js"></script>
</body>
</html>