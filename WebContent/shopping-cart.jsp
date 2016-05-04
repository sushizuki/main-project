<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<title>Sushizuki</title>
		<meta name="description" content="Sushizuki" />
		<meta name="viewport" content="width=device-width, user-scalable=no" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/openSansFamily.css" />
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
						<li class="logo-wrapper">
							<a href="index.jsp">
								<img src="img/sushi/sushizuki-logo.png" alt="Sushizuki">
							</a>
						</li>
						<li>
							<a href="index">Home</a>
						</li>
						<li>
							<a href="menu">Cardápio</a>
						</li>
						<li>
							<a href="contact.jsp">Contatos</a>
						</li>
					</ul>
				</nav> <!-- End Navigation bar -->
			</div> <!-- End container  -->
		</div> <!-- End top bar  -->
	
		<!-- Main content -->
		<div class="content container" >
			<div class="row">
				<h2>Confirmação de Pedido</h2>
			</div>
	    	<div class="container">
	    		<div class="container-fluid">
					<form action="Order?action=addAdditionals" method="post">
						<div class="row">
							<div class="col-md-12 col-sm-12 items">
								<!-- Shopping Cart Items -->
								<div class="row items-heading"> <!-- Items heading -->
									<div class="col-md-1 col-sm-1"></div>
									<div class="col-md-1 col-sm-3 center"></div>
									<div class="col-md-6 col-sm-5"><h3>Descrição</h3></div>
									<div class="col-md-1 col-sm-1 center"><h3>Preço</h3></div>
									<div class="col-md-2 col-sm-1 center"><h3>Qtd.</h3></div>
									<div class="col-md-1 col-sm-1 center"><h3>Total</h3></div>
								</div>
									<c:forEach var="item" items="${order.items}">
										<!-- Shopping Cart Item -->
										<div class="row item-line"> <!-- Item (line) -->
											<!-- Shopping Cart Item Actions -->
											<div class="col-md-1 col-sm-1 col-xs-3 item-line-prop item-actions">
												<a href="#" class="btn btn-md btn-grey center" aria-hidden="true" style="background:none;border:none;">
													<i class="glyphicon glyphicon-remove"></i><br />Remover
												</a>
											</div>
											<!-- Image -->
											<div class="col-md-1 col-sm-3 col-xs-12 item-img item-line-prop">
												<img src="${item.key.imageURL}" class="center" alt="${item.key.name}">
											</div>
											<!-- Item Description & Features -->
											<div class="col-md-6 col-sm-5 col-xs-12 item-line-prop item-desc">
												<div class="shopping-cart title">${item.key.name }</div>
												<div class="feature">Descrição: <br><b>${item.key.description }</b></div>
												<c:if test="${item.key.extra != null}">
													<div class="feature">Extra: <b>${item.key.extra }</b></div>
												</c:if>
											</div>
											<!-- Price -->
											<div class="col-md-1 col-sm-1 col-xs-3 center item-line-prop item-price">		
												<fmt:setLocale value="pt-BR" />
												<fmt:formatNumber value="${item.key.price}" type="currency" currencySymbol="R$" />
											</div>
											<!-- Quantity -->
											<div class="col-md-2 col-sm-1 col-xs-3 center item-line-prop item-qtd">
												<input class="form-control input-sm input-micro center" type="text" value="${item.value}">
											</div>
											<!-- Total -->
											<div class="col-md-1 col-sm-1 col-xs-3 center item-line-prop item-total">		
												<fmt:setLocale value="pt-BR" />
												<fmt:formatNumber value="${item.key.price*item.value}" type="currency" currencySymbol="R$" />
											</div>
										</div> <!-- End item (line) -->
										<!-- End Shopping Cart Item -->	
									</c:forEach>						
								</div>
								<!-- End Shopping Cart Items -->
							</div>
						<hr />
						<div class="row">		
							<!-- Shopping Cart Totals -->
							<div class="col-md-10 col-sm-9 col-xs-9">
								<div class="col-md-10 col-sm-9 col-xs-6" style="text-align: right">
									<strong>Entrega</strong>
								</div>
								<div class="col-md-2 col-sm-3 col-xs-6">
									<strong>Grátis</strong>
								</div>
								<div class="col-md-10 col-sm-9 col-xs-6" style="text-align: right">
									<h3><strong>Total</strong></h3>
								</div>
								<div class="col-md-2 col-sm-3 col-xs-6">
									<h3><strong><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="R$" /></strong></h3>
								</div>
							</div>				
							<div class="col-md-2 col-sm-3 col-xs-3">
								<a href="Order?action=updateOrder" class="btn btn-grey pull-right"><i class="glyphicon glyphicon-refresh icon-white"></i> ATUALIZAR</a>
							</div>
						</div>
						<hr />
					    <c:if test="${user.name != null}">
							<!-- Additionals -->
							<div class="container">
								<div class="col-md-3  col-sm-3 col-xs-12">
									<div class="cart-promo-code">
										<h4><i class="glyphicon glyphicon-gift"></i> Gostaria de acrescentar:</h4>
										<div class="input-group">	
											<c:forEach var="additional" items="${additionals}">	
												<label><input type="checkbox" name="additional[]" value="${additional.id }"> ${additional.name }</label><br>
			                                </c:forEach>
										</div>
									</div>
								</div>
								<!-- Payment -->
								<div class="col-md-3 col-sm-3 col-xs-12">
									<div class="cart-promo-code">
										<h4><i class="glyphicon glyphicon-tag"></i> Pagamento:</h4>
										<div class="input-group">
							            	<div class="input-group">
								            	<label><input type="radio" id="pay-card" class="payment-option" name="payment" value="card">&nbsp;
								            		<i class="glyphicon glyphicon-credit-card"></i> Cartão
								            	</label>
							            	</div>	
							            	<div class="input-group">
								            	<label><input type="radio" id="pay-money" class="payment-option" checked="checked" name="payment" value="money">&nbsp;
								            		<i class="glyphicon glyphicon-usd"></i> Dinheiro
								            	</label>
								            	<div class="input-group" style="margin-top:15px;">
							            			<label for="change">Troco para: <br>	
		                                            </label>  
		                                        	<div class="input-group">                                          	
			                                            <span class="input-group-addon">R$</span>
			                                            <input type="text" name="change" id="change" class="form-control validate" style="width:80px;float:left;" maxlength="6" />
		                                            </div>
		                                        </div>	
							            	</div>  	
						            	</div>
						           	</div> 
					           	</div>
								
								<!-- Shipping Options -->
								<div class="col-md-6 col-sm-6 col-xs-12">
									<div class="cart-shippment-options">
										<h4><i class="glyphicon glyphicon-plane"></i> Entrega</h4>
										<div class="input-append">
											<select class="form-control input-sm" name="receiving">
												<option value="2">Buscar no local</option>
												<option value="1">Receber em casa (Somente Park Way, Quadras 26 a 29, Brasília/DF)</option>
											</select>
										</div>
									</div>
								</div>			
							</div>
						</c:if>
						<hr />
						<!-- Action Buttons -->
						<div class="container-fluid">
							<div class="row pull-right">
			            		<a href="menu" class="btn btn-grey">Adicionar mais itens</a>
			            		<c:choose>
								    <c:when test="${user.name != null}">
										<button type="submit" class="btn"><i class="glyphicon glyphicon-shopping-cart icon-white"></i> PROSSEGUIR</button>
								    </c:when>    
								    <c:otherwise>
								       	<a class="btn btn-primary" href="login?redir=shopping-cart"><i class="glyphicon glyphicon-shopping-cart icon-white"></i> Fazer Login</a>
							        </c:otherwise>
								</c:choose>							
							</div>
						</div>
						<hr />
					</form>
				</div>
			</div>
		</div>	<!-- End Main Content -->
    	
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
	<script>
	function doCheck(){
	    var allFilled = true;

		if($('#pay-money').prop("checked") == false){
			$('.validate').each(function(){
		        if($('change').val() < $('total').val() ){
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
        $('.payment-option').click(function(ev) {
    		var cliAddr = "#client-addr";
    		var newAddr = "#new-addr";
    		if($('#pay-money').prop("checked") == false){
        		$('#change').prop("readonly", true);
        		$('#change').val("");
    		} else {
        		$('#change').prop("readonly", false);
        		$('#change').val("");
        		$('#change').focus();
    		}
    	});

        $("#change").maskMoney();
	});
	</script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.sequence-min.js"></script>
	<script src="js/jquery.bxslider.js"></script>
	<script src="js/template.js"></script>

</body>
</html>