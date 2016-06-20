
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
							class="glyphicon glyphicon-shopping-cart icon-white"></i> <c:if
								test="${order.items.size() > 0}">
								<b><a href="shopping-cart"> <span id="items-in-cart">
											<c:out value="${order.items.size() }"></c:out>
									</span> <fmt:message key="mainMenu.itemsMessage"/>
								</a></b>
							</c:if></li>
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
					                <option value="pt-BR" ${language == 'pt-BR' ? 'selected' : ''}>Portugês BR</option>
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
	<div class="content">

		<!-- Homepage Slider -->
		<div class="homepage-slider">
			<div id="sequence" style="border-bottom: 2px solid #cfcfcf">
				<ul class="sequence-canvas">
					<!-- Slide 1 -->
					<li class="bg1">
						<h2 class="title">Sushi</h2>
						<h3 class="subtitle"><fmt:message key="sushi.flavors"/></h3>
					</li>
					<!-- End Slide 1 -->
					<!-- Slide 2 -->
					<li class="bg2">
						<h2 class="title">Sashimi</h2>
						<h3 class="subtitle"><fmt:message key="sashimi.flavors"/></h3>
					</li>
					<!-- End Slide 2 -->
					<!-- Slide 3 -->
					<li class="bg3">
						<h2 class="title">Temaki</h2>
						<h3 class="subtitle"><fmt:message key="temaki.flavors"/></h3>
					</li>
					<!-- End Slide 3 -->
					<!-- Slide 4 -->
					<li class="bg4">
						<h2 class="title">Niguiri</h2>
						<h3 class="subtitle"><fmt:message key="niguiri.flavors"/></h3>
					</li>
					<!-- End Slide 4 -->
				</ul>
				<!--Slide pagination -->
				<div class="sequence-pagination-wrapper">
					<ul class="sequence-pagination">
						<li>1</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
					</ul>
				</div>
				<!-- End Slide pagination -->
			</div>
		</div>
		<!-- End Homepage Slider -->

		<!-- Call to Action Bar -->
		<div class="container" style="height: 150px">
			<div class="row">
				<div class="calltoaction-wrapper">
					<h3><fmt:message key="banner.makeYourOrder"/></h3>
					<a href="menu" class="btn btn-orange"><fmt:message key="button.here"/></a>
				</div>
			</div>
		</div>
		<!-- End Call to Action Bar -->

	</div>
	<!-- End Content -->

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
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/main-menu.js"></script>
	<script src="js/template.js"></script>
</body>
</html>
