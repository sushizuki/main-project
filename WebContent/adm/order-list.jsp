<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty param.language}">
  <fmt:setLocale value="${param.language}" scope="session"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Sushizuki">
		<title>Sushizuki</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css" />
		<link href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">
		<link href="../bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
		<link href="../css/lightbox.css" rel="stylesheet">
		<!-- Custom CSS -->
		<link href="../dist/css/sb-admin-2.css" rel="stylesheet">
		<!-- Custom Font -->
		<link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div id="wrapper">
	
			<!-- Navigation -->
			<nav class="navbar navbar-default navbar-static-top" role="navigation"
				style="margin-bottom: 0">
				<div class="navbar-header">
					<a class="navbar-brand" href="dashboard.jsp"><img
						src="../img/sushi/sushizuki-logo.png" style="width: 100px;"
						alt="Sushizuki" /></a>
				</div>
				<ul class="nav navbar-top-links navbar-right">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
							<c:out value="${user.name} " /> <i class="fa fa-caret-down"></i>
					</a>
						<ul class="dropdown-menu dropdown-user">
							<li class="divider"></li>
							<li><a href="../User?action=doLogout"><i
									class="fa fa-sign-out fa-fw"></i> <fmt:message key="mainMenu.logout"/></a></li>
						</ul></li>
					<!-- /.dropdown user actions -->
				</ul>
				<!-- /.navbar-top-links -->
	
				<!-- /.Sidebar menu -->
				<div class="navbar-default sidebar" role="navigation">
					<div class="sidebar-nav navbar-collapse">
						<ul class="nav" id="side-menu">
							<li><a href="Order?action=getOrderList"><i
									class="fa fa-shopping-cart fa-fw"></i> Pedidos</a></li>
							<li><a href="Product"><i class="fa fa-cutlery fa-fw"></i>
									Produtos</a></li>
						</ul>
					</div>
				</div>
				<!-- /.Sidebar menu -->
			</nav>
	
			<div id="page-wrapper">
	
				<!-- /.row Message -->
				<div class="row">
					<h1 class="page-header">Pedidos</h1>
					<div class="message" id="message">
						<c:if test="${message == 'sucess'}">
							<div class="alert alert-success alert-dismissable fade">
								<button type="button" class="close" data-dismiss="alert"
									aria-hidden="true">&times;</button>
								Realizado com sucesso!
							</div>
						</c:if>
						<c:if test="${message == 'failure'}">
							<div class="alert alert-danger alert-dismissable fade">
								<button type="button" class="close" data-dismiss="alert"
									aria-hidden="true">&times;</button>
								Algum erro ocorreu.
							</div>
						</c:if>
					</div>
				</div>
				<!-- /.row Message -->
	
				<!-- Content row -->
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading">Lista de pedidos.</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>Pedido</th>
											<th>Cliente</th>
											<th class="center">Horário</th>
											<th class="center">Status</th>
											<th class="center">Ação</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${orders}" var="order">
											<tr class="gradeA">
												<td class="center" style="width: 85px;">
													<c:out value="${order.id}" />
												</td>
												<td>
													<c:out value="${order.client.name}" />
												</td>
												<td class="center" style="width: 100px">
													<fmt:formatDate type="both" pattern="dd/MM/yyyy HH:mm"
														value="${order.receiving.dateTime.time}" />
												</td>
												<td class="center">
													<c:out value="${order.status}" />
												</td>
												<td> 
													<a class="btn btn-primary btn-outline btn-xs show-item"
														href="#" id="${order.id }" style="width: 100%">
														Ver detalhes </a>
												</td>
											</tr>
											<div style="display: none;" class="orderDetail"
												id="orderDetail-${order.id }">
												<div class="col-lg-12">
													<strong>Pedido: </strong>#
													<c:out value="${order.id}" />
													<br>
													<strong>Cliente: </strong>
													<c:out value="${order.client.name}" />
												</div>
												<div class="col-lg-4" style="padding-bottom: 50px;">
													<strong>Itens:</strong>
													<ul>
														<c:forEach items="${order.items}" var="item">
															<li>
																<c:out value="${item.value}" /> x 
																<c:out value="${item.key.name}" />
															</li>
														</c:forEach>
													</ul>
												</div>
												<div class="col-lg-4">
													<strong>Adicionais:</strong>
													<ul>
														<c:forEach items="${order.additionals}" var="additional">
															<li><c:out value="${additional.name}" /></li>
														</c:forEach>
													</ul>
												</div>
												<div class="col-lg-4">
													<strong>Pagamento:</strong>
													<c:out value="${order.payment.paymentType}" />
													<c:if test="${order.payment.change > 0.0}">
														<br>
														<strong>Troco para: </strong>
														<fmt:formatNumber
															value="${order.payment.change+order.totalPrice}"
															type="currency" currencySymbol="R$" />
													</c:if>
												</div>
												<div class="col-lg-4" style="padding-bottom: 50px;">
													<br>
													<strong>Recebimento: </strong><br>
													<c:choose>
														<c:when
															test="${order.receiving.getClass().name =='domain.Collect'}">
	                                            			Coleta
	                                            		</c:when>
														<c:when
															test="${order.receiving.getClass().name =='domain.Delivery'}">
		                                            		Entrega - ${order.receiving.address.address }
		                                          		</c:when>
														<c:otherwise>${order.receiving.getClass().name}</c:otherwise>
													</c:choose>
													<br><br> 
													<strong>Data e Hora: </strong><br>
													<fmt:formatDate type="both" pattern="dd/MM/yyyy HH:mm"
														value="${order.receiving.dateTime.time}" />
												</div>
											</div>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /. Content row -->
	
			</div>
			<!-- /#page-wrapper -->
	
		</div>
		<!-- /#wrapper -->
	
		<!-- Javascripts -->
		<script src="../js/jquery-1.9.1.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<!-- Metis Menu Plugin JavaScript -->
		<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>
		<!-- DataTables JavaScript -->
		<script src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
		<script src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
		<!-- Custom Theme JavaScript -->
		<script src="../dist/js/sb-admin-2.js"></script>
		<script>
		    $(document).ready(function() {	        
		        $('#dataTables-example').dataTable({
		        	  "columns": [
		        	    null,
		        	    null,
		        	    null,
		        	    { "orderable": false }, 
		        	    { "orderable": false } //actions
		        	  ]
		        	});
		
		      	//Showing items
		        $('.show-item').click(function(ev) {
		    		var list = "#orderDetail-"+$(this).attr('id');
		    		if($(list).css('display') == 'none'){
		    			$('.orderDetail').hide("fast");
		        		$(list).show("fast");
		        		$(this).text("Ocultar");
		    		} else {
		        		$(list).hide("fast");
		        		$(this).text("Ver detalhes");
		    		}
		    	});	
		    });
	    </script>
	</body>
</html>
