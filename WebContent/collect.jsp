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
                   	<h1 class="page-header">Buscar no local</h1>
               	</div>
			</div>
		</div>
	</div>
  		<div class="container">
  			<form role="form" name="formCollect" action="Order?action=setCollectTime" method="post">
            	<div class="col-lg-6">
                	<div class="input-group">
                    	<p>Informe a data e horário para coleta do seu pedido.</p>
                    	<p class="alert-warning" style="padding:10px;">
                    		<i class="glyphicon glyphicon-alert icon-dark"></i> 
                    		Somente dias de Sexta-feira, Sábado ou Domingo!
                    	</p>
                    	<br>
                    	<div class="input-group">
                    		<div class="input-group bootstrap-timepicker timepicker">                    			
                    			<input id="datepicker" type="text" class="form-control" readonly="readonly" style="background:white; cursor:text;" name="date">
						        <span class="input-group-addon">
						        	<label for="datepicker"><i class="glyphicon glyphicon-calendar"></i></label>
						        </span>						        
                    		</div>
	                    	<div class="input-group bootstrap-timepicker timepicker">
					            <input id="timepicker" type="text" class="form-control input-small" name="time">
					            <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
					        </div>		                    
	                    </div>
                    </div>
                    <br>
                    <div>
                        <p>
                        	O endereço para busca no local é:<br><br>
                        	<b>SMPW Quadra 26 Conjunto 6 Lote 5 casa D, <br>Condomínio Recanto da Primavera.</b>
                        </p>
                    </div>          
      			</div> 
	      		<div class="col-lg-6 pull-left">
	             	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3836.769922896627!2d-47.96928648485721!3d-15.921114130326632!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x935a292f4880cdc1%3A0x856e76e030fa3e75!2sSmpw+Trecho+1+Q+26+Conjunto+6+7%2C+7+-+N%C3%BAcleo+Bandeirante%2C+Bras%C3%ADlia+-+DF!5e0!3m2!1sen!2sbr!4v1445826478412" 
	             	width="550" height="400" style="border:0;"></iframe>
	            </div>  
	            <br>
                <!-- Action Buttons -->
                <div class="col-lg-12">
	                 <button type="button" class="btn btn-primary" onclick="history.go(-1)">Voltar</button>	                                        
	                 <button type="submit" class="btn btn-primary" disabled="disabled">Continuar</button>   
                </div> 
			</form>               
         </div>
        <!-- /.container -->
	<br>
    <div class="mainmenu-wrapper"></div><br><br>
	<!-- Javascripts -->
	<script>
	function doCheck(){
	    var allFilled = true;
	    $('input[type=text]').each(function(){
	        if($(this).val() == '' ){
	            allFilled = false;
	            return false;
	        }
	    });
	    $('button[type=submit]').prop('disabled', !allFilled);
	}

	$(document).ready(function(){
	    $('input[type=text]').keyup(doCheck).focusout(doCheck);
	});
	</script>
	<script src="js/jquery.fitvids.js"></script>
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/main-menu.js"></script>
	<script src="js/template.js"></script>	

</body>
</html>