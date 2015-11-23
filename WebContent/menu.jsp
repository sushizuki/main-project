<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%  %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
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
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600,800'
	rel='stylesheet' type='text/css'>

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
						<c:choose>
						    <c:when test="${order.items.size()>0 }">								
								<li class="shopping-cart-items"><i
									class="glyphicon glyphicon-shopping-cart icon-white"></i> <a
									href="shopping-cart"><b><span id="items-in-cart"><c:out value="${order.items.size() }"></c:out></span> </b></a></li>
								<li></li>
						    </c:when>    
						    <c:otherwise>
						    	<li class="shopping-cart-items"><i
									class="glyphicon glyphicon-shopping-cart icon-white"></i> <a
									href="#"><b><span id="items-in-cart"><c:out value="${order.items.size() }"></c:out></span> </b></a></li>
								<li></li>
						    </c:otherwise>
						</c:choose>
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
					<li class="active"><a href="menu">Cardápio</a></li>

					<li><a href="contatos.html">Contatos</a></li>
				</ul>
			</nav>
		</div>
	</div>

	<div class="eshop-section section">
	    	<div class="container">
	    		<div class="row">	    	
	    			<div class="col-md-12">
						<h2>Cardápio</h2>
					</div>
				</div>
				<form action="Order?action=newOrder" method="post">
					<div class="row">
						<c:forEach items="${products}" var="product" varStatus="status">
							<div class="col-md-3 col-sm-6">
								<div class="shop-item">
									<c:choose>
							    		<c:when test="${order.items[status.index] }">
											<div class="ribbon-wrapper" id="product-box-${product.id }">
											    <div class="price-ribbon ribbon-yellow"> Selecionado </div>
											</div>
										 </c:when>    
							    		 <c:otherwise>
											<div class="ribbon-wrapper" style="display: none;" id="product-box-${product.id }">
											    <div class="price-ribbon ribbon-yellow"> Selecionado </div>
											</div>
										</c:otherwise>
									</c:choose>
									<input type="checkbox" name="products[]" value="${product.id}" id="product-${product.id }" style="float:left;display:none;">
									<div class="shop-item-image">
										<img src="${product.imgUrl}" alt="${product.name}">
									</div>
									<div class="title">
										<h3><c:out value="${product.name}"/></h3>
									</div>
									<div class="price">
										<fmt:formatNumber value="${product.price}" type="currency" currencySymbol="R$" />
									</div>
									<div class="description">
										<p><c:out value="${product.description}" /></p>
									</div>
									<div class="actions">
										<button type="button" class="btn btn-small btn-add" id="${product.id}"><i class="glyphicon glyphicon-shopping-cart icon-white"></i><span> Adicionar</span></button>
										<br><br>
										<div id="product-qtd-div-${product.id }" style="display:none;">
											<label for="product-qtd-${product.id }">
												Quantidade:
											</label>									
		  									<input type="number" name="prod_quantity[]" id="product-qtd-${product.id }" min="1" max="100" style="width: 50px">
	  									</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<hr>
					<div class="row pull-right">
						<button type="SUBMIT" class="btn btn-primary" style="font-variant: small-caps; font-size:13pt;" disabled>
							<i class="glyphicon glyphicon-ok-sign icon-white"></i>
							<span> Prosseguir com pedido</span>
						</button>
					</div>
				</form>
			</div>
	    </div>

	<br><br>
    <div class="mainmenu-wrapper"></div><br><br>
	<!-- Javascripts -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="js/jquery-1.9.1.min.js"><\/script>')
	</script>
	<script>
	$(document).ready(function() {

		//Selecting items
        $('.btn-add').click(function(ev) {
    		var prod = "#product-"+$(this).attr('id');
    		var prodRibbon = "#product-box-"+$(this).attr('id');
    		var prodQtd = "#product-qtd-"+$(this).attr('id');
    		var prodDivQtd = "#product-qtd-div-"+$(this).attr('id');
    		if($(prod).prop("checked") == false){
        		$(prod).prop("checked", true);
        		$('span', this).text(" Remover");
        		$(prodRibbon).show("fast");
        		$(prodDivQtd).show("fast");
        		$(prodQtd).prop("value", 1);
        		$(prodQtd).focus();
    		} else {
    			$(prod).prop("checked", false);
        		$('span', this).text(" Adicionar");
        		$(prodRibbon).hide("fast");
        		$(prodDivQtd).hide("fast");
    		}
            checkout();
    	});

        function checkout() {

            var arr = $.map($('input:checkbox:checked'), function(e, i) {
                return +e.value;
            });

            if(arr.length === 0){
                
            	$('button:SUBMIT').prop('disabled', true);
            } else if(arr.length > 0){
            	$('button:SUBMIT').prop('disabled', false);
            }
        	$('#items-in-cart').text(arr.length);
        }

    });
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