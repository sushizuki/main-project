<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<!-- timepicker -->
<link rel="stylesheet" type="text/css" href="css/jquery.timepicker.css" />
<script	src="js/jquery-1.9.1.min.js"></script>
<script	src="js/jquery-ui.min.js"></script>
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
					<li class="logo-wrapper"><a href="index.jsp">
						<img src="img/sushi/sushizuki-logo.png" alt="Sushizuki"></a>
					</li>
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
                   	<h1 class="page-header">Entrega em domicílio</h1>
               	</div>
			</div>
		</div>
	</div>
    <div class="container">
  		<form role="form" name="formDelivery" action="Order?action=setDeliveryAddressTime" method="post" enctype="multipart/form-data">
          	<div class="col-lg-6">	          		
	    		<p>Informe o endereço para entrega do seu pedido.</p>
	          	
	           	<div class="col-lg-6 pull-left">	           		
	                <div class="input-group">
		            	<label><input type="radio" id="new-addr" name="address" value="new"> Em outro endereço.</label>
		            	<div class="input-group">
		            		<label>CEP: 
		            			<input type="text" data-mask="00000-000"  class="form-control new-addrInput cep" readonly="readonly" value="" id="cep" name="newCep">
	            			</label>
	            			<label>Endereço:
		            			<input type="text" class="form-control new-addrInput" readonly="readonly" value="" name="newAddress">
		            		</label>
		            		<label>Complemento:
		            			<input type="text" class="form-control new-addrInput" readonly="readonly" value="" name="newComplement">
	            			</label>
		            	</div>  
	            	</div>
	            	<br>
	            	<p id="info-addr" class="alert-warning" style="padding:10px; display:none;">
		          		<i class="glyphicon glyphicon-alert icon-dark"></i> 
		          		Somente Quadras 26, 27, 28 e 29 do Park Way!
		          	</p>
	           	</div> 
	           	<div class="col-lg-6">
	                <div class="input-group">
		            	<label><input type="radio" checked="checked" id="client-addr" name="address" value="client"> No endereço cadastrado.</label>
		            	<div class="input-group">
		            		<input type="hidden" name="clientAddrId" value="1${user.address.id}" class="validate">
		            		<div>
		            			<p>${user.address.id}Endereço do cliente</p>
	            			</div>
		            	</div>  
	            	</div>
	           	</div> 
           	</div>
           	<div class="col-lg-6">
				<div class="input-group">
				   	<p>Informe a data e horário para a entrega do seu pedido.</p>
					   	<p class="alert-warning" style="padding:10px;">
						<i class="glyphicon glyphicon-alert icon-dark"></i> 
						Somente dias de Sexta-feira, Sábado ou Domingo!
					</p>
					<br>
					<div class="input-group">
						<div class="input-group bootstrap-timepicker timepicker">                    			
							<span class="input-group-addon">
							 	<label for="datepicker"><i class="glyphicon glyphicon-calendar"></i></label>
							</span>
							<input id="datepicker" type="text" class="form-control validate" readonly="readonly" style="background:white; cursor:text;" name="date">
						</div>
			        	<div class="input-group bootstrap-timepicker timepicker">
					    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
					    	<input id="timepicker" type="text" class="form-control input-small validate" name="time">
					    </div>		                    
				    </div>
	        	</div> 	
        	</div>
            <!-- Action Buttons -->
            <div class="col-lg-12" style="text-align: right" style="background:red;">
            	<br><br>
              	<button type="button" class="btn btn-primary" onclick="history.go(-1)">Voltar</button>	                                        
              	<button type="submit" class="btn btn-primary" disabled="disabled">Continuar</button>   
            </div>                     
		</form>               
    </div>
    <!-- /.container -->
	<br><br>
    <div class="mainmenu-wrapper"></div><br><br>
	<!-- Javascripts -->
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
			$('input[type=text]').each(function(){
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