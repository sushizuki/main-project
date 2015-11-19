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
							href="page-shopping-cart.html"><b>3 items</b></a></li>
						<li></li>
						<li><a href="login.jsp">Login</a></li>
					</ul>
				</div>
			</div>
			<nav id="mainmenu" class="mainmenu">
				<ul>
					<li class="logo-wrapper"><a href="index.jsp"><img
							src="img/sushi/sushizuki-logo.png" alt="Sushizuki"></a></li>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="menu.jsp">Card�pio</a></li>

					<li><a href="contatos.html">Contatos</a></li>
				</ul>
			</nav>
		</div>
	</div>
	
	<div id="page-wrapper">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h1 class="page-header">Buscar no local</h1>
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
	            <!-- /.row -->
	            <div class="row">
	                <div class="col-lg-12">
	                    <div class="panel panel-default">
	                        <div class="panel-body">
	                            <div class="row">
	                                <div class="col-lg-6">
	                                    <form role="form" name="formCollect" action="collect.jsp" method="post" enctype="multipart/form-data">
	                                        <div class="form-group">
	                                            <label>O endere�o para busca no local �:</label><br>
	                                            <label><b><i>SMPW Quadra 26 Conjunto 6 Lote 5 casa D - Condom�nio Recanto da Primavera</i></b></label>
	                                        </div>
	                                        <div class="form-group">
	                                        	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3836.769922896627!2d-47.96928648485721!3d-15.921114130326632!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x935a292f4880cdc1%3A0x856e76e030fa3e75!2sSmpw+Trecho+1+Q+26+Conjunto+6+7%2C+7+-+N%C3%BAcleo+Bandeirante%2C+Bras%C3%ADlia+-+DF!5e0!3m2!1sen!2sbr!4v1445826478412" 
	                                        	width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
	                                        </div>
                                        <a href="confirmation.jsp"><button type="button" class="btn btn-primary">Voltar</button></a>	                                        
                                        <a href="scheduleCollect.jsp"><button type="button" class="btn btn-primary">Continuar</button></a>    
 									</form>
                                </div>                  
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->


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