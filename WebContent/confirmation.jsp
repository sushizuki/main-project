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
											class="glyphicon glyphicon-off icon-white"> </i><fmt:message key="mainMenu.logout"/></a></li>
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
		<!-- Main content -->
		<div class="content container">
			<div class="row">
				<h2><fmt:message key="title.confirmation"/></h2>
			</div>
  			<div class="container">
	  			<p><fmt:message key="description.confirmation"/> </p>
	  			<form role="form" action="Order?action=saveOrder" method="post">
               	 	<hr />
	            	<div class="row">
                	 	<div class="col-md-2 col-sm-12 col-xs-12">
               	 			<span><strong><fmt:message key="client.name"/> </strong><br>
								<c:out value="${order.client.name}" />
							</span>
						</div>
						<div class="col-md-4 col-sm-12 col-xs-12">
               	 			<span><strong><fmt:message key="receiving.address"/> </strong><br>
								<c:out value="${order.receiving.address}" />
							</span>
                	 	</div>
               	 	</div>
               	 	<div class="row">
               	 		<div class="col-md-2 col-sm-6 col-xs-6">
               	 			<span><strong><fmt:message key="receiving.time"/></strong> <br>
									<fmt:formatDate type="both" pattern="dd/MM/yyyy HH:mm" value="${order.receiving.dateTime.time}" />
							</span>
                	 	</div>                	 		
               	 		<div class="col-md-3 col-sm-6 col-xs-6">
               	 			<span><strong><fmt:message key="order.payment"/> </strong><br>
                	 				<c:out value="${order.payment.paymentType}" /><br>
                	 				<c:if test="${order.payment.change != null}">
                	 					<fmt:message key="order.change"/> <fmt:formatNumber value="${order.payment.change+order.totalPrice}" type="currency" currencySymbol="R$" />
               	 					</c:if>
               	 			</span>
                	 	</div>
               	 	</div>
               	 	<hr />
					<!-- Shopping Cart Items -->							
					<c:forEach var="item" items="${order.items}">
						<!-- Shopping Cart Item -->
						<div class="row item-line"> <!-- Item (line) -->
							<!-- Image -->
							<div class="col-md-1 col-sm-3 col-xs-12 item-img item-line-prop">
								<img src="${item.key.imageURL}" class="center" alt="${item.key.name}">
							</div>
							<!-- Item Description & Features -->
							<div class="col-md-6 col-sm-5 col-xs-12 item-line-prop item-desc">
								<div class="shopping-cart title">${item.key.name }</div>
								<div class="feature"> <fmt:message key="item.description"/> <br><b>${item.key.description }</b></div>
								<c:if test="${item.key.extra != null}">
									<div class="feature"> <fmt:message key="item.extra"/><b>${item.key.extra }</b></div>
								</c:if>
							</div>
							<!-- Price -->
							<div class="col-md-1 col-sm-1 col-xs-3 center item-line-prop item-price">		
								<fmt:setLocale value="pt-BR" />
								<fmt:formatNumber value="${item.key.price}" type="currency" currencySymbol="R$" />
							</div>
							<!-- Quantity -->
							<div class="col-md-2 col-sm-1 col-xs-3 center item-line-prop item-qtd">
								<span>x ${item.value}</span>
							</div>
							<!-- Total -->
							<div class="col-md-1 col-sm-1 col-xs-3 center item-line-prop item-total">		
								<fmt:setLocale value="pt-BR" />
								<fmt:formatNumber value="${item.key.price*item.value}" type="currency" currencySymbol="R$" />
							</div>
						</div> <!-- End item (line) -->
						<!-- End Shopping Cart Item -->	
					</c:forEach>						
					<!-- End Shopping Cart Items -->
               	 	<hr />
                	 <div class="row">
                	 	<div class="col-md-4 col-sm-6 col-xs-6">
               	 			<strong><fmt:message key="additional.confirmation"/> </strong><br>
              	 				<ul>
								<c:forEach var="additional" items="${order.additionals}">
									<li><c:out value="${additional.name}" /></li>
								</c:forEach>
							</ul>
						</div>
						<div class="col-md-4 col-sm-6 col-xs-6">
              	 				<h3>
               	 				<strong><fmt:message key="total.confirmation"/></strong>
								<fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="R$" />
							</h3>
                	 	</div>
               	 	</div>
               	 	<hr />
	                <!-- Action Buttons -->
	                <div class="row pull-right">
		                 <button type="button" class="btn btn-primary" onclick="history.go(-1)"><fmt:message key="action.goBack"/></button>	                                        
		                 <button type="submit" class="btn btn-primary"><fmt:message key="action.confirm"/></button>   
	                </div> 
				</form>               
	         </div> <!-- /.container -->
         </div><!--End Main Content -->

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
	</body>
</html>