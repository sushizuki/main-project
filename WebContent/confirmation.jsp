<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Sushizuki</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/openSansFamily.css">
<link rel="stylesheet" href="css/leaflet.css" />
<!--[if lte IE 8]>
    <link rel="stylesheet" href="css/leaflet.ie.css" />
<![endif]-->
<link rel="stylesheet" href="css/main-red.css">
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script	src="js/jquery-1.9.1.min.js"></script>
<script	src="js/jquery-ui.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.timepicker.js"></script>
<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>
<body>
	<!--[if lt IE 7]>
        <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
    <![endif]-->

	<!-- Navigation & Logo-->
	<div class="mainmenu-wrapper">
		<div class="container">
			<div class="menuextras">
				<div class="extras">
					<ul>
						<li class="shopping-cart-items"><i
							class="glyphicon glyphicon-shopping-cart icon-white"></i> <a
							href="page-shopping-cart.html"><b>${order.items.size()} itens</b></a>
						</li>
						<c:choose>
						    <c:when test="${user.name != null}">
								<li>
						        	<c:out value="${user.name}" />
						        </li>
						        <li>
						        	<a href="user?action=doLogout"><i class="glyphicon glyphicon-off icon-white"> </i> Sair</a>
								</li>
						    </c:when>    
						    <c:otherwise>
						    	<li>
						        	<a href="login?redir=menu">Login</a>
					        	</li>
						    </c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
			<nav id="mainmenu" class="mainmenu">
				<ul>
					<li class="logo-wrapper"><a href="index.jsp"><img
							src="img/sushi/sushizuki-logo.png" alt="Sushizuki"></a></li>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="menu">Cardápio</a></li>
					<li><a href="contatos.html">Contatos</a></li>
				</ul>
			</nav>
		</div>
	</div>
	
	<!-- Page Title -->
		<div class="section section-breadcrumbs" >
			<div class="container">
				<div class="row">
					<div class="col-md-12">
                    	<h1 class="page-header">Confirmação</h1>
                	</div>
				</div>
			</div>
		</div>
  		<div class="container">
  			<p>Confira os detalhes do seu pedido: </p>
  			<form role="form" action="Order?action=saveOrder" method="post">
            	<div class="col-lg-6">
                	 <div>
                	 	<table class="shopping-cart" >
                	 		<tr>
                	 			<td style="vertical-align: top"><strong>Cliente: </strong><br>
									<c:out value="${order.client.name}" />
								</td>
                	 			<td colspan="3" style="vertical-align: top"><strong>Endereço: </strong><br>
									<c:out value="${order.receiving.address}" />
								</td>
                	 		</tr>
                	 		<tr>
                	 			<td style="vertical-align: top"><strong>Horário:</strong> <br>
									<fmt:formatDate type="both" pattern="dd/MM/yyyy HH:mm" value="${order.receiving.time.time}" />
								</td>
                	 			<td colspan="3" style="vertical-align: top"><strong>Pagamento: </strong><br>
                	 				<c:out value="${order.payment.paymentType}" /><br>
                	 				<c:if test="${order.payment.change != null}">
                	 					Troco para: <fmt:formatNumber value="${order.payment.change+order.totalPrice}" type="currency" currencySymbol="R$" />
               	 					</c:if>
                	 			</td>
                	 		</tr>
                	 			<!-- Shopping Cart Items -->
								<c:forEach var="item" items="${order.items}">
									<!-- Shopping Cart Item -->
									<tr style="vertical-align: top">
										<!-- Shopping Cart Item Image -->
										<td style="overflow: hidden;" class="image"><img src="${item.key.imageURL}" alt="${item.key.name}"></td>
										<!-- Shopping Cart Item Description & Features -->
										<td style="width:400px;">
											<div class="shopping-cart title">${item.key.name }</div>
											<div class="feature">Descrição: <br><b>${item.key.description }</b></div>
											<c:if test="${item.key.extra != null}">
												<div class="feature">Extra: <b>${item.key.extra }</b></div>
											</c:if>
										</td>
										<!-- Shopping Cart Item Quantity -->
										<td style="text-align: center;">
											<fmt:formatNumber value="${item.key.price}" type="currency" currencySymbol="R$" /><br>X <c:out value="${item.value}" />
										</td>
										<!-- Shopping Cart Item Price -->
										<td class="price">		
											<fmt:formatNumber value="${item.key.price * item.value}" type="currency" currencySymbol="R$" />
										</td>
										<!-- Shopping Cart Item Actions -->
									</tr>
									<!-- End Shopping Cart Item -->	
								</c:forEach>					
							<!-- End Shopping Cart Items -->	
								<tr>
									<td style="text-align: right; vertical-align: top;">Adicionais: </td>
									<td colspan="3">
										<c:forEach var="additional" items="${order.additionals}">
											<c:out value="${additional.name}" /><br>
										</c:forEach>
									</td>
								</tr>
								<tr class="cart-totals">
									<td colspan="2"></td>
									<td><b>TOTAL</b></td>
									<td><b><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="R$" /></b></td>
								</tr>
							</table>
                	 </div>         
      			</div> 
                <!-- Action Buttons -->
                <div class="col-lg-12">
	                 <button type="button" class="btn btn-primary" onclick="history.go(-1)">Voltar</button>	                                        
	                 <button type="submit" class="btn btn-primary">Confirmar Pedido</button>   
                </div> 
			</form>               
         </div>
        <!-- /.container -->
	<br>
    <div class="mainmenu-wrapper"></div><br><br>
	<!-- Javascripts -->
	<script src="js/jquery.fitvids.js"></script>
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/main-menu.js"></script>
	<script src="js/template.js"></script>	
</body>
</html>