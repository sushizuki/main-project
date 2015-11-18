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
						<li>Nome usuário</li>
					</ul>
				</div>
			</div>
			<nav id="mainmenu" class="mainmenu">
				<ul>
					<li class="logo-wrapper"><a href="index.jsp"><img
							src="img/sushi/sushizuki-logo.png" alt="Sushizuki"></a></li>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="menu.jsp">Cardápio</a></li>
					<li><a href="contact.jsp">Contatos</a></li>
				</ul>
			</nav>
		</div>
	</div>
	
	<div id="page-wrapper">
	            <div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>Confirmação do pedido</h1>
						</div>
					</div>
				</div>
	            

	            <!-- /.row -->
	            	<div class="section-white">
						<div class="container">
							<div class="row">
								<div class="col-sm-8">
	                                    <form role="form" name="formConfirmation" action="confirmation.jsp" method="post" enctype="multipart/form-data">
	                                        <div class="form-group">
	                                            <label>Seu pedido é:</label>
	                                            <label class="form-control" name="confirmation" rows="5" ></label>
	                                        </div>
	                                        <div class="form-group">
	                                        	<label>Gostaria de acrescentar:</label><br>
	                                        	<input type="checkbox" name="additional" value="shoyu"> Sachê de molho shoyu<br>
												<input type="checkbox" name="additional" value="wasabi"> Wasabi<br>
												<input type="checkbox" name="additional" value="hashi"> Hashi<br>
												<input type="checkbox" name="additional" value="gengibre"> Gengibre
	                                        </div>
                                        <a href="menu.jsp"><button type="button" class="btn btn-primary">Voltar</button></a>                                      
                                        <a href="collect.jsp"><button type="button" class="btn btn-primary">Buscar no local</button></a>
                                        <a href="delivery.jsp"><button type="button" class="btn btn-primary">Entregar em domicílio</button></a>    
                                        
                                          
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
		    			<h3>Navegação</h3>
		    			<ul class="no-list-style footer-navigate-section">
		    				<li><a href="index.jsp">Home</a></li>
		    				<li><a href="menu.jsp">Cardápio</a></li>
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
		    			<div class="footer-copyright">&copy; 2015 Sushizuki. Brasília/DF.</div>
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