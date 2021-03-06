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
<link rel="stylesheet" href="css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-timepicker.css" />
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
											class="glyphicon glyphicon-off icon-white"> </i> Sair</a></li>
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
				<h2><fmt:message key="title.delivery"/></h2>
			</div>
			<form role="form" name="formDelivery"
				action="Order?action=setDeliveryDetails" method="post">
				<div class="row">
					<div class="col-md-6">
						<p><fmt:message key="title.formAddress"/></p>
						<br />
						<div class="col-md-6">
							<div class="input-group">
								<label><input type="radio" checked="checked"
									id="client-addr" name="address" value="client"> <i
									class="glyphicon glyphicon-home"></i> <fmt:message key="address.registered"/> </label>
								<div class="input-group">
									<input type="hidden" name="clientAddrId"
										value="1${user.address.id}" class="validate">
									<div>
										<p>${user.address}</p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-12 col-xs-12 pull-left">
							<div class="input-group">
								<label><input type="radio" id="new-addr" name="address"
									value="new"> <i class="glyphicon glyphicon-home"></i> 
									<fmt:message key="address.other"/> </label>
								<div class="">
									<label><fmt:message key="address.cep"/> <input type="text" data-mask="00000-000"
										readonly="readonly" value="" id="cep" name="newCep" size="9"
										class="form-control new-addrInput cep validateNewAddr">
									</label><br /> <label><fmt:message key="address.address"/> <input type="text"
										readonly="readonly" value="" name="newAddress"
										class="form-control new-addrInput validateNewAddr">
									</label><br /> <label><fmt:message key="address.complement"/> <input type="text"
										readonly="readonly" value="" name="newComplement"
										class="form-control new-addrInput">
									</label>
								</div>
							</div>
							<br>
							<p id="info-addr" class="alert-warning"
								style="padding: 10px; display: none;">
								<i class="glyphicon glyphicon-alert icon-dark"></i> 
								<fmt:message key="description.address"/>
							</p>
						</div>
					</div>
					<div class="col-md-6">
						<div class="input-group">
							<p><fmt:message key="title.formdate"/>.</p>
							<br />
							<p class="alert-warning" style="padding: 10px;">
								<i class="glyphicon glyphicon-alert icon-dark"></i> 
								<fmt:message key="description.date"/>
							</p>
							<br>
							<div class="input-group">
								<div class="input-group bootstrap-timepicker timepicker">
									<span class="input-group-addon"> <label for="datepicker"><i
											class="glyphicon glyphicon-calendar"></i></label>
									</span> <input id="datepicker" type="text"
										class="form-control validate" readonly="readonly"
										style="background: white; cursor: text;" name="date">
								</div>
								<div class="input-group bootstrap-timepicker timepicker">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-time"></i></span> <input id="timepicker"
										type="text" class="form-control input-small validate"
										name="time">
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr />
				<!-- Action Buttons -->
				<div class="row" style="text-align: right" style="background:red;">
					<button type="button" class="btn btn-primary"
						onclick="history.go(-1)"><fmt:message key="action.goBack"/></button>
					<button type="submit" class="btn btn-primary" disabled="disabled"><fmt:message key="action.confirm"/></button>
				</div>
			</form>
		</div>
		<!-- /.container -->

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
	<!-- End footer -->

	<!-- Javascripts -->
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.timepicker.js"></script>
	<script src="js/jquery.mask.js"></script>
	<script>
		  $(function() {
		    $('#datepicker').datepicker({ 
		        minDate: 1, 
		        maxDate: "+1M +10D",
		        dateFormat: 'dd/mm/yy',                
		        language: 'pt-BR',
		        beforeShowDay: function(date){ return [(date.getDay() >= 5 || date.getDay() == 0),""]} 
		    });
		    $('#timepicker').timepicker({ 'scrollDefault': 'now','timeFormat': 'H:i' });
		  });
		</script>
	<script>
		function doCheck(){
		    var allFilled = true;
	
			if($('#new-addr').prop("checked") == false){
				$('.validate').each(function(){
			        if($(this).val() == '' ){
			            allFilled = false;
			            return false;
			        }
			    });
			} else {
				$('.validateNewAddr').each(function(){
			        if($(this).val() == '' ){
			            allFilled = false;
			            return false;
			        }
			    });
			}
		    $('button[type=submit]').prop('disabled', !allFilled);
		}
	
		$(document).ready(function(){
	
		    $('input').keyup(doCheck).focusout(doCheck);
	
			//Selecting items
	        $('input[type=radio]').click(function(ev) {
	    		var cliAddr = "#client-addr";
	    		var newAddr = "#new-addr";
	    		if($(newAddr).prop("checked") == false){
	        		$('.new-addrInput').prop("readonly", true);
	        		$('#info-addr').hide("fast");
	    		} else {
	        		$('.new-addrInput').prop("readonly", false);
	        		$('#info-addr').show("fast");
	        		$('#cep').focus();
	    		}
	    	});
	
	        $('.cep').mask('00000-000');
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