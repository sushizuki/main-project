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
						<li>Nome usu�rio</li>
					</ul>
				</div>
			</div>
			<nav id="mainmenu" class="mainmenu">
				<ul>
					<li class="logo-wrapper"><a href="index.jsp"><img
							src="img/sushi/sushizuki-logo.png" alt="Sushizuki"></a></li>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="menu.jsp">Card�pio</a></li>
					<li><a href="contact.jsp">Contatos</a></li>
				</ul>
			</nav>
		</div>
	</div>
	
		<div id="page-wrapper">
	            <div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>Entrega em domic�lio</h1>
						</div>
					</div>
				</div>
	            

	            <!-- /.row -->
	            	<div class="section-white">
						<div class="container">
							<div class="row">
								<div class="col-sm-8">
	                                    <form role="form" name="formDelivery" action="delivery.jsp" method="post" enctype="multipart/form-data">
	                                        <div>
	                                        	<table style="width:100%">
	                                        	<tr>
	                                        		<form action="">
	                                        		<td colspan="2"><input type="radio" name="local" value="registered"><b> Endere�o cadastrado</b></td>
	                                        		<td colspan="2"><input type="radio" name="local" value="other"><b> Outro endere�o</b></td>
	                                        		</form>
	                                        	</tr>
	                                        	<tr></tr><tr></tr>
	                                        	<tr>
	                                        		<td><label>Endere�o</label></td>
	                                        		<td><input name="address"/></td>
	                                        		<td><label>Endere�o</label></td>
	                                        		<td><input name="address"/></td>
	                                        	</tr>
	                                        	</table>
	                                        </div>
                                        <br><a href="confirmation.jsp"><button type="button" class="btn btn-primary">Voltar</button></a>	                                        
                                        <a href="scheduleDelivery.jsp"><button type="button" class="btn btn-primary">Continuar</button></a>    
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
                
    	<!-- Footer -->
	    <div class="footer">
	    	<div class="container">
		    	<div class="row">
		    		<div class="col-footer col-md-3 col-xs-6">
		    			<h3>Navega��o</h3>
		    			<ul class="no-list-style footer-navigate-section">
		    				<li><a href="index.jsp">Home</a></li>
		    				<li><a href="menu.jsp">Card�pio</a></li>
		    				<li><a href="contact.jsp">Contato</a></li>
		    			</ul>
		    		</div>
		    		
		    		<div class="col-footer col-md-4 col-xs-6">
		    			<h3>Contato</h3>
		    			<p class="contact-us-details">
	        				<b>Telefone:</b> (61) 8636 8825<br/>
	        				<b>Email:</b> <a href="mailto:">sushizukiii@gmail.com</a>
	        			</p>
		    		</div>
					
					<div class="col-footer col-md-2 col-xs-6">
		    			<h3>Social</h3>
		    			<ul class="footer-stay-connected no-list-style">
		    				<li><a href="https://www.facebook.com/SushiZuki-165405287145692/?fref=ts" class="facebook"></a></li>
		    			</ul>
		    		</div>
					
		    	</div>
		    	<div class="row">
		    		<div class="col-md-12">
		    			<div class="footer-copyright">&copy; 2015 Sushizuki. Bras�lia/DF.</div>
		    		</div>
		    	</div>
		    </div>
	    </div>

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