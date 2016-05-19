<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
									</span> itens
								</a></b>
							</c:if></li>
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
					<li><a href="/sushizuki">Home</a></li>
					<li class="active"><a href="menu">Cardápio</a></li>
					<li><a href="contact.jsp">Contatos</a></li>
				</ul>
			</nav>
			<!-- End Navigation bar -->

		</div>
		<!-- End container  -->
	</div>
	<!-- End top bar  -->

	<!-- E-shop section -->
	<div class="eshop-section section">
		<div class="container">
			<div class="row">
				<h2 class="center menu-title">Cardápio</h2>
			</div>
			<div class="row">
				<form action="Order?action=newOrder" method="post">
					<div class="col-md-12 col-xs-12">
						<c:forEach items="${products}" var="product" varStatus="status">
							<div class="col-md-3 col-sm-4">
								<div class="shop-item">
									<c:choose>
										<c:when test="${order.items[status.index] }">
											<div class="ribbon-wrapper" id="product-box-${product.id }">
												<div class="price-ribbon ribbon-yellow">Selecionado</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="ribbon-wrapper" style="display: none;"
												id="product-box-${product.id }">
												<div class="price-ribbon ribbon-yellow">Selecionado</div>
											</div>
										</c:otherwise>
									</c:choose>
									<input type="checkbox" name="products[]" value="${product.id}"
										id="product-${product.id }"
										style="float: left; display: none;" />
									<div class="shop-item-image">
										<img src="${product.imageURL}" alt="${product.name}">
									</div>
									<div class="title">
										<h3>
											<c:out value="${product.name}" />
										</h3>
									</div>
									<div class="price">
										<fmt:setLocale value="pt-BR" />
										<fmt:formatNumber value="${product.price}" type="currency"
											currencySymbol="R$" />
									</div>
									<div class="description">
										<p>
											<c:out value="${product.description}" />
										</p>
									</div>
									<div class="actions">
										<button type="button" class="btn btn-small btn-add"
											id="${product.id}">
											<i class="glyphicon glyphicon-shopping-cart icon-white"></i><span>
												Adicionar</span>
										</button>
										<br />
										<br />
										<div id="product-qtd-div-${product.id }"
											style="visibility: hidden;">
											<label for="product-qtd-${product.id }"> Quantidade:
											</label> <input type="number" name="prod_quantity[]"
												id="product-qtd-${product.id }" min="1" max="100"
												style="width: 50px" />
										</div>
									</div>
									<!-- End actions -->
								</div>
								<!-- End grid column -->
							</div>
							<!-- End shop item -->
						</c:forEach>
					</div>
					<!-- End row -->
					<hr />
					<div class="row-fluid">
						<div class="text-center col-md-12 col-sm-12 col-xs-12">
							<button type="SUBMIT" class="btn btn-primary btn-lg"
								style="font-variant: small-caps; font-size: 13pt;" disabled>
								<i class="glyphicon glyphicon-ok-sign icon-white"></i> <span>
									Prosseguir com pedido</span>
							</button>
						</div>
					</div>
				</form>
			</div>
			<!-- End all items Menu -->
		</div>
		<!-- End container -->
	</div>
	<!-- End e-shop section -->

	<!-- Footer -->
	<div class="footer">
		<div class="container">
			<div class="row">
				<div class="col-footer col-md-4 col-sm-4 col-xs-12">
					<h3>Navegação</h3>
					<ul class="no-list-style footer-navigate-section">
						<li><a href="index.jsp">Home</a></li>
						<li><a href="menu">Cardápio</a></li>
						<li><a href="contact.jsp">Contato</a></li>
					</ul>
				</div>

				<div class="col-footer col-md-4 col-sm-4 col-xs-12">
					<h3>Contato</h3>
					<p class="contact-us-details">
						<b>Telefone:</b> (61) 8636 8825<br /> <b>Email:</b> <a
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
						Brasília/DF.</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End footer -->

	<!-- Javascripts -->
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {

			//Selecting items
			$('.btn-add').click(function(ev) {
				var prod = "#product-" + $(this).attr('id');
				var prodRibbon = "#product-box-" + $(this).attr('id');
				var prodQtd = "#product-qtd-" + $(this).attr('id');
				var prodDivQtd = "#product-qtd-div-" + $(this).attr('id');
				if ($(prod).prop("checked") == false) {
					$(prod).prop("checked", true);
					$('span', this).text(" Remover");
					$(prodRibbon).show("fast");
					$(prodDivQtd).css("visibility", "visible");
					$(prodQtd).prop("value", 1);
					$(prodQtd).focus();
				} else {
					$(prod).prop("checked", false);
					$('span', this).text(" Adicionar");
					$(prodRibbon).hide("fast");
					$(prodDivQtd).css("visibility", "hidden");
				}
				checkout();
			});

			function checkout() {

				var arr = $.map($('input:checkbox:checked'), function(e, i) {
					return +e.value;
				});

				if (arr.length === 0) {

					$('button:SUBMIT').prop('disabled', true);
				} else if (arr.length > 0) {
					$('button:SUBMIT').prop('disabled', false);
				}
				$('#items-in-cart').text(arr.length);
			}
		});
	</script>
</body>
</html>
