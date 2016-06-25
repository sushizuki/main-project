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
								</span> <fmt:message key="mainMenu.itemsMessage"/>
							</b>
						</a></li>
						<c:choose>
							<c:when test="${user.name != null}">
								<li><c:out value="${user.name}" /></li>
								<li><a href="user?action=doLogout"><i
										class="glyphicon glyphicon-off icon-white"> </i> <fmt:message key="mainMenu.logout"/></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="login"><fmt:message key="mainMenu.login"/></a></li>
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
					<li><a href="menu"><fmt:message key="mainMenu.menu"/></a></li>
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
			<h2><fmt:message key="title.collect"/></h2>
		</div>
		<div class="row">
			<form role="form" name="formCollect"
				action="Order?action=setCollectTime" method="post">
				<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="input-group">
						<p><fmt:message key="form.datetime"/></p>
						<br />
						<p class="alert-warning" style="padding: 10px;">
							<i class="glyphicon glyphicon-alert icon-dark"></i> <fmt:message key="message.weekend"/>
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
					<div><br><br>
						<p><fmt:message key="message.address"/><br>
							<br> <b>SMPW Quadra 26 Conjunto 6 Lote 5 casa D, <br>Condomínio
								Recanto da Primavera.
							</b>
						</p>
					</div>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="pull-right">
						<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d15347.079770327175!2d-47.967098!3d-15.921119!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x935a292f462aca7d%3A0x876f37ab266fe746!2sSmpw+Trecho+1+Q+26+Conjunto+6+7+-+SMPW+Q+26+-+N%C3%BAcleo+Bandeirante%2C+Bras%C3%ADlia+-+DF%2C+Brazil!5e0!3m2!1sen!2sbr!4v1465310032690" 
							width="600" height="450" frameborder="0" style="border:0" allowfullscreen>
						</iframe>
					</div>
				</div>
		</div>
		<!-- /.row -->
		<hr />
		<!-- Action Buttons -->
		<div class="row" style="text-align: right" style="background:red;">
			<button type="button" class="btn btn-primary"
				onclick="history.go(-1)"><fmt:message key="button.back"/></button>
			<button type="submit" class="btn btn-primary" disabled="disabled"><fmt:message key="button.next"/></button>
		</div>
		</form>
	</div>
	<!-- End Main Content -->

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