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
<title>Sushizuki - Login</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/icomoon-social.css">
<link rel="stylesheet" href="css/openSansFamily.css">
<link rel="stylesheet" href="css/leaflet.css" />
<!--[if lte IE 8]>
		    <link rel="stylesheet" href="css/leaflet.ie.css" />
		<![endif]-->
<link rel="stylesheet" href="css/main-red.css">
<script	src="js/jquery-1.9.1.min.js"></script>
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
				</div>
			</div> 
			<nav id="mainmenu" class="mainmenu">
				<ul>
					<li class="logo-wrapper"><a href="index.jsp"><img
							src="img/sushi/sushizuki-logo.png" alt="Sushizuki"></a></li>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="menu">Cardápio</a></li>

					<li><a href="contact.html">Contatos</a></li>
				</ul>
			</nav>
		</div>
	</div>

	<!-- Page Title -->
	<div class="section section-breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>Login</h1>
				</div>
			</div>
		</div>
	</div>
	
	<div class="section-white">
		<div class="container">
		<c:if test="${param.err == 1}">
			<div class="alert alert-danger alert-dismissable" role="alert"><strong>Erro!</strong> E-mail ou senha inválidos!</div>
		</c:if>
			<div class="row">
				<div class="col-sm-5">
					<div class="basic-login">
						<form role="form" role="form" action="login?<c:out value='redir=${param.redir}'/>" method="post">
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
							<!--
								<label class="checkbox">
									<input type="checkbox"> Continuar conectado
								</label>
							-->
								<a href="page-password-reset.html" class="forgot-password">Esqueceu a senha?</a><br/>
								<a href="page-register.html">Cadastre-se</a>
								<button type="submit" class="btn pull-right" disabled="disabled">Login</button>
								<div class="clearfix"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
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
	});
	</script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.fitvids.js"></script>
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/main-menu.js"></script>
	<script src="js/template.js"></script>

</body>
</html>