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
						<!-- Image Logo -->
						<li class="logo-wrapper">
							<a href="/sushizuki">
								<img src="img/sushi/sushizuki-logo.png" alt="Sushizuki">
							</a>
						</li>
						<li><a href="/sushizuki">Home</a></li>
						<li><a href="menu">Cardápio</a></li>
						<li><a href="contact.jsp">Contatos</a></li>
					</ul>
				</nav> <!-- End Navigation bar -->
			</div> <!-- End container  -->
		</div> <!-- End top bar  -->

		<!-- Main Content  -->
		<div class="content container">
			<div class="row">
				<h2>Login</h2>
			</div>
			<div class="row">
				<div class="col-md-4 col-sm-6 col-xs-12">
					<c:if test="${param.err == 1}">
						<div class="alert alert-danger alert-dismissable" role="alert"><strong>Erro!</strong> E-mail ou senha inválidos!</div>
					</c:if>
					<div class="basic-login">
						<form role="form" role="form" action="User?action=doLogin&<c:out value='redir=${param.redir}' default="redir=menu"/>" method="post">
							<div class="form-group">
								<label for="login-username"><i class="icon-user"></i> <b>Email</b></label>
								<input class="form-control validate" name="email" id="login-username" type="text" placeholder="">
							</div>
							<div class="form-group">
								<label for="login-password"><i class="icon-lock"></i> <b>Senha</b></label>
								<input class="form-control validate" name="password" id="login-password" type="password" placeholder="">
							</div>
							<div class="form-group">
								<input type="hidden" name="redir" value="${param.redir }">
								<a href="page-password-reset.html" class="forgot-password">Esqueceu sua senha?</a><br/>
								<a href="page-register.html">Cadastre-se</a>
								<button type="submit" id="btn-login" class="btn pull-right">Login</button>
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
		<script>
			//Check if input's content is a valid email
			function isEmail(email) {
				var pattern = new RegExp(/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/);
		        return pattern.test(email);
			}

			function doCheck(){
			    var allFilled = true;
		        var email = "#login-username";
			    $('.validate').each(function(){
			        if($(this).val() == '' || !isEmail($(email).val()) ){
			            allFilled = false;
			            return false;
			        }
			    });
			    $('button[type=submit]').prop('disabled', !allFilled);
			}

			$(document).ready(function(){
			    $('input').keyup(doCheck).focusout(doCheck);
			    if($('#login-password').val().length === 0){
			    	$('#btn-login').prop('disabled', true);
				}				    
			});
		</script>
	</body>
</html>
