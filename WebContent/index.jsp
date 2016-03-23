<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<title>Sushizuki</title>
		<meta name="description" content="Sushizuki" />
		<meta name="viewport" content="width=device-width, user-scalable=no" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/openSansFamily.css" />
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
							<li class="shopping-cart-items">
								<i class="glyphicon glyphicon-shopping-cart icon-white"></i>
								<a href="shopping-cart">
									<b>
										<span id="items-in-cart">
											<c:out value="${order.items.size() }"></c:out>
										</span> itens
									</b>
								</a>
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
										<a href="login">Login</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div> <!-- End Login and User area -->

				<!--Navigation bar -->
				<nav id="mainmenu" class="mainmenu">
					<ul>
						<li class="logo-wrapper">
							<a href="index.jsp">
								<img src="img/sushi/sushizuki-logo.png" alt="Sushizuki">
							</a>
						</li>
						<li class="active">
							<a href="index">Home</a>
						</li>
						<li>
							<a href="menu">Cardápio</a>
						</li>
						<li>
							<a href="contact.jsp">Contatos</a>
						</li>
					</ul>
				</nav> <!-- End Navigation bar -->
			</div> <!-- End container  -->
		</div> <!-- End top bar  -->
		
		<!-- Main Content  -->
		<div class="content">
		
			<!-- Homepage Slider -->
			<div class="homepage-slider">
				<div id="sequence"  style="border-bottom: 2px solid #cfcfcf">
					<ul class="sequence-canvas">
						<!-- Slide 1 -->
						<li class="bg1">
							<h2 class="title">Sushi</h2> 
							<h3 class="subtitle">De Salmão, Kani e muito mais!</h3>
						</li>
						<!-- End Slide 1 -->
						<!-- Slide 2 -->
						<li class="bg2">
							<h2 class="title">Sashimi</h2>
							<h3 class="subtitle">De Salmão</h3>
						</li>
						<!-- End Slide 2 -->
						<!-- Slide 3 -->
						<li class="bg3">
							<h2 class="title">Temaki</h2>
							<h3 class="subtitle">De Salmão e Kani</h3>
						</li>
						<!-- End Slide 3 -->
						<!-- Slide 4 -->
						<li class="bg4">
							<h2 class="title">Niguiri</h2>
							<h3 class="subtitle">De Salmão e Skin</h3>
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
					</div> <!-- End Slide pagination -->
				</div>
			</div> <!-- End Homepage Slider -->
	
			<!-- Call to Action Bar -->
			<div class="container" style="height: 150px">
				<div class="row">
					<div class="calltoaction-wrapper">
						<h3>Faça seu pedido agora!</h3>
						<a href="menu" class="btn btn-orange">aqui!</a>
					</div>
				</div>
			</div>	<!-- End Call to Action Bar -->
		
		</div> <!-- End Content -->
		
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
	        				<b>Telefone:</b> (61) 8636 8825<br/>
	        				<b>Email:</b> <a href="mailto:">sushizukiii@gmail.com</a>
	        			</p>
		    		</div>
					
					<div class="col-footer col-md-4 col-sm-4 col-xs-12">
		    			<h3>Social</h3>
		    			<ul class="footer-stay-connected no-list-style">
		    				<li><a href="https://www.facebook.com/SushiZuki-165405287145692/?fref=ts" class="facebook"></a></li>
		    			</ul>
		    		</div>
					
		    	</div>
		    	<div class="row">
		    		<div class="col-md-12">
		    			<div class="footer-copyright">&copy; 2016 Sushizuki. Brasília/DF.</div>
		    		</div>
		    	</div>
		    </div>
	    </div><!-- End footer -->
		
		<!-- Javascripts -->
		<script src="js/jquery-1.9.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.sequence-min.js"></script> <!-- Slider -->
		<script src="js/template.js"></script>
	</body>
</html>
