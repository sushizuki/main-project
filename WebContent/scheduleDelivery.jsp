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
					<li><a href="menu.jsp">Cardápio</a></li>

					<li><a href="contatos.html">Contatos</a></li>
				</ul>
			</nav>
		</div>
	</div>
	
	<div id="page-wrapper">
	            <div class="row">
	                <div class="col-lg-12">
	                    <h1 class="page-header">Informações de busca e pagamento</h1>
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
	                                    <form role="form" name="formScheduleCollect" action="scheduleCollect.jsp" method="post" enctype="multipart/form-data">
	                                        <div class="form-group">
	                                            <label><i>Os dias para entrega são: sexta-feira, sábado e domingo. Entre às 11:00 e 00:00</i></label><br>
	                                        </div>
	                                        <div class="form-group">
	                                        	<label>Data da entrega:</label>
	                                        	<input type="date">
	                                        </div>
	                                        <div>
	                                        	<label>Horário da entrega:</label>
	                                        	<input type="time">
	                                        </div>
	                                        <div>
	                                        	<br><label>Forma de pagamento:</label><br>
	                                        	<label>O pagamento só é efetuado na hora da entrega do pedido</label><br>
	                                        	<form action="">
	                                        	<input type="radio" name="payment" value="card"> Cartão de crédito/débito<br>
	                                        	<input type="radio" name="payment" value="money"> Dinheiro<br>
	                                        	<label>Troco para: R$</label>
	                                        	<input type="number">
	                                        	</form>	 
	                                        </div>
                                        <a href="confirmation.jsp"><button type="button" class="btn btn-primary">Voltar</button></a>	                                        
                                        <button type="submit" class="btn btn-primary">Confirmar</button> 
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