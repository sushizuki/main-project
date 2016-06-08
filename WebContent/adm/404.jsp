<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
									class="fa fa-sign-out fa-fw"></i> Sair</a></li>
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
					<h1 class="page-header">404 :/</h1>
				</div>
				<!-- /.row Message -->
	
				<!-- Content row -->
				<div class="row">					
					<p>Algum erro ocorreu ao processar sua solicitação.</p>
					<a href="#" onclick="history.go(-1)">Voltar</a>
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
	</body>
</html>
