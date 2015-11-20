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
<link rel="stylesheet" href="css/icomoon-social.css">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600,800' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/leaflet.css" />
<!--[if lte IE 8]>
		    <link rel="stylesheet" href="css/leaflet.ie.css" />
		<![endif]-->
<link rel="stylesheet" href="css/main-red.css">

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
							href="page-shopping-cart.html"><b>${order.items.size()} itens</b></a></li>
						<li></li>
						<li><a href="login.jsp">Login</a></li>
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
						<h2>Confirmação de Pedido</h2>
					</div>
				</div>
			</div>
		</div>        
        <div>
	    	<div class="container">
				<form action="Order?action=addAdditionals" method="post">
					<div class="row">
						<div class="col-md-12">
							<!-- Shopping Cart Items -->
							<table class="shopping-cart">
								<c:forEach var="item" items="${order.items}">
									<!-- Shopping Cart Item -->
									<tr style="vertical-align: top">
										<!-- Shopping Cart Item Image -->
										<td class="image"><img src="${item.key.imgUrl}" alt="${item.key.name}"></td>
										<!-- Shopping Cart Item Description & Features -->
										<td>
											<div class="shopping-cart title">${item.key.name }</div>
											<div class="feature">Descrição: <br><b>${item.key.description }</b></div>
											<c:if test="${item.key.extra != null}">
												<div class="feature">Extra: <b>${item.key.extra }</b></div>
											</c:if>
										</td>
										<!-- Shopping Cart Item Quantity -->
										<td class="quantity">
											<input class="form-control input-sm input-micro" type="text" value="${item.value}">
										</td>
										<!-- Shopping Cart Item Price -->
										<td class="price">		
											<fmt:formatNumber value="${item.key.price}" type="currency" currencySymbol="R$" /></td>
										<!-- Shopping Cart Item Actions -->
										<td class="actions">
											<a href="#" class="btn btn-xs btn-grey"><i class="glyphicon glyphicon-trash"></i></a>
										</td>
									</tr>
									<!-- End Shopping Cart Item -->	
								</c:forEach>						
							</table>
							<!-- End Shopping Cart Items -->
						</div>
					</div>
					<div class="row">
						<!-- Shopping Cart Totals -->
						<div class="col-md-12">
							<div style="width: 60%">
								<table class="cart-totals">
									<tr>
										<td><b>Entrega</b></td>
										<td style="width:20%;">Grátis</td>
									</tr>
									<tr class="cart-grand-total">
										<td><b>Total</b></td>
										<td><b><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="R$" /></b></td>
									</tr>
								</table>
								<div>
									<a href="Order?action=updateOrder" class="btn btn-grey pull-right"><i class="glyphicon glyphicon-refresh icon-white"></i> ATUALIZAR</a>
								</div>							
							</div>
						</div>
						<div class="col-md-12">
							<hr>
						</div>
						<div>
							<hr>
							<!-- Additionals -->
							<div class="col-md-3  col-md-offset-0 col-sm-6 col-sm-offset-6">
								<div class="cart-promo-code">
									<h4><i class="glyphicon glyphicon-gift"></i> Gostaria de acrescentar:</h4>
									<div class="input-group">	
										<c:forEach var="additional" items="${additionals}">	
											<label><input type="checkbox" name="additional[]" value="${additional.id }"> ${additional.name }</label><br>
		                                </c:forEach>
									</div>
								</div>
							</div>
							
							<!-- Shipment Options -->
							<div class="col-md-5 col-md-offset-0 col-sm-6 col-sm-offset-6">
								<div class="cart-shippment-options">
									<h4><i class="glyphicon glyphicon-plane"></i> Entrega</h4>
									<div class="input-append">
										<select class="form-control input-sm" name="receiving">
											<option value="1">Receber em casa (Somente Park Way, Quadras 26 a 29, Brasília/DF)</option>
											<option value="2">Buscar no local</option>
										</select>
									</div>
								</div>
							</div>				
						</div>
					</div>
					<hr>
					<!-- Action Buttons -->
					<div class="pull-right">
	            		<button type="button" class="btn btn-grey" onclick="history.go(-1)">Adicionar mais itens</button>			
						<button type="submit" class="btn"><i class="glyphicon glyphicon-shopping-cart icon-white"></i> PROSSEGUIR</button>
					</div>
				</form>
				<br><br><br><br>
			</div>
		</div>	
    <div class="mainmenu-wrapper"></div><br><br>
	<!-- Javascripts -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="js/jquery-1.9.1.min.js"><\/script>')
	</script>
	<script src="js/bootstrap.min.js"></script>
	<script src="http://cdn.leafletjs.com/leaflet-0.5.1/leaflet.js"></script>
	<script src="js/jquery.fitvids.js"></script>
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/main-menu.js"></script>
	<script src="js/template.js"></script>

</body>
</html>