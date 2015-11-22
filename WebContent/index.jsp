<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<li class="shopping-cart-items"><i
							class="glyphicon glyphicon-shopping-cart icon-white"></i> <a
							href="shopping-cart"><b><span id="items-in-cart"><c:out value="${order.items.size() }"></c:out></span> itens</b></a></li>
						<li></li>
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
					<li class="active"><a href="index.jsp">Home</a></li>
					<li><a href="menu">Cardápio</a></li>

					<li><a href="contatos.html">Contatos</a></li>
				</ul>
			</nav>
		</div>
	</div>

	<!-- Homepage Slider -->
	<div class="homepage-slider">
		<div id="sequence"  style="border-bottom: 1px solid #cfcfcf">
			<ul class="sequence-canvas">
				<!-- Slide 1 -->
				<li class="bg1">
					<!-- Slide Title -->
					<h2 class="title">Sushi</h2> <!-- Slide Text -->
					<h3 class="subtitle">De Salmão, Kani e muito mais</h3> <!-- Slide Image -->
				</li>
				<!-- End Slide 1 -->
				<!-- Slide 2 -->
				<li class="bg2">
					<!-- Slide Title -->
					<h2 class="title">Sashimi</h2> <!-- Slide Text -->
					<h3 class="subtitle">De Salmão</h3> <!-- Slide Image -->
				</li>
				<!-- End Slide 2 -->
				<!-- Slide 3 -->
				<li class="bg3">
					<!-- Slide Title -->
					<h2 class="title">Temaki</h2> <!-- Slide Text -->
					<h3 class="subtitle">De Salmão e Kani</h3> <!-- Slide Image -->
				</li>
				<!-- End Slide 3 -->
				<!-- Slide 4 -->
				<li class="bg4">
					<!-- Slide Title -->
					<h2 class="title">Niguiri</h2> <!-- Slide Text -->
					<h3 class="subtitle">De Salmão e Skin</h3> <!-- Slide Image -->
				</li>
				<!-- End Slide 4 -->
			</ul>
				
			<div class="sequence-pagination-wrapper">
				<ul class="sequence-pagination">
					<li>1</li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- End Homepage Slider -->

	<!-- Call to Action Bar -->
	<div class="section bg-orange" style="position:fixed; bottom:0; width:100%;">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="calltoaction-wrapper">
						<h3>Faça seu pedido agora!</h3>
						<a href="menu" class="btn btn-orange">aqui!</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Call to Action Bar -->

	<!-- Services
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-6">
					<div class="service-wrapper">
						<img src="img/service-icon/diamond.png" alt="Service 1">
						<h3>Entrega no parkway</h3>
						<p>Praesent rhoncus mauris ac sollicitudin vehicula. Nam
							fringilla turpis turpis, at posuere turpis aliquet sit amet
							condimentum</p>
						<a href="#" class="btn">Read more</a>
					</div>
				</div>
				<div class="col-md-4 col-sm-6">
					<div class="service-wrapper">
						<img src="img/service-icon/ruler.png" alt="Service 2">
						<h3>Sextas, sábados e domingos</h3>
						<p>Suspendisse eget libero mi. Fusce ligula orci, vulputate
							nec elit ultrices, ornare faucibus orci. Aenean lectus sapien,
							vehicula</p>
						<a href="#" class="btn">Read more</a>
					</div>
				</div>
				<div class="col-md-4 col-sm-6">
					<div class="service-wrapper">
						<img src="img/service-icon/box.png" alt="Service 3">
						<h3>Dinossauro</h3>
						<p>Phasellus posuere et nisl ac commodo. Nulla facilisi. Sed
							tincidunt bibendum cursus. Aenean vulputate aliquam risus rutrum
							scelerisque</p>
						<a href="#" class="btn">Read more</a>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<!-- End Services -->



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