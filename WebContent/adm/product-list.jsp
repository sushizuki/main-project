<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<link href="../css/bootstrap.min.css" rel="stylesheet">
		<link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
		<link href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">
		<link href="../bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
		<link href="../css/lightbox.css" rel="stylesheet">
		<link href="../dist/css/sb-admin-2.css" rel="stylesheet">
		<link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="dashboard.jsp"><img
					src="../img/sushi/sushizuki-logo.png" style="width: 100px;"
					alt="Sushizuki" /></a>
			</div>
			<!-- /.navbar-header -->


			<ul class="nav navbar-top-links navbar-right">
				<li>
				<form>
					 <select id="language" name="language" onchange="submit()">
					        <option value=""><fmt:message key="mainMenu.language"/></option>
					        <option value="pt-BR" ${language == 'pt-BR' ? 'selected' : ''}>Português BR</option>
					        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
					 </select>
				</form>
				</li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<c:out value="${user.name} " /><i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li class="divider"></li>
						<li><a href="../User?action=doLogout"><i
								class="fa fa-sign-out fa-fw"></i><fmt:message key="mainMenu.logout"/></a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="Order?action=getOrderList"><i
								class="fa fa-shopping-cart fa-fw"></i><fmt:message key="menu.orders"/></a></li>
						<li><a href="Product"><i class="fa fa-cutlery fa-fw"></i><fmt:message key="menu.products"/></a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<h1 class="page-header"><fmt:message key="menu.products"/></h1>
				<div class="message" id="message">
					<c:if test="${message == 'sucess'}">
						<div class="alert alert-success alert-dismissable fade">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<fmt:message key="message.confirmation"/>
						</div>
					</c:if>
					<c:if test="${message == 'failure'}">
						<div class="alert alert-danger alert-dismissable fade">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<fmt:message key="message.error"/>
						</div>
					</c:if>
				</div>
				<div class="form-group">
					<a class="btn btn-primary" href="Product?action=newProduct"><fmt:message key="product.new"/></a>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading"><fmt:message key="list.products"/></div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th style="display: none"><fmt:message key="product.id"/></th>
										<th style="width: 150px;"><fmt:message key="product.name"/></th>
										<th style="width: 350px;"><fmt:message key="product.description"/></th>
										<th class="center" style="width: 100px;"><fmt:message key="product.price"/></th>
										<th class="center" style="width: 150px;"><fmt:message key="product.category"/></th>
										<th class="center"><fmt:message key="product.image"/></th>
										<th class="center" style="width: 150px;"><fmt:message key="product.action"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${products}" var="product">
										<tr class="gradeA">
											<td style="display: none"><c:out value="${product.id}" /></td>
											<td><c:out value="${product.name}" /></td>
											<td><c:out value="${product.description}" /></td>
											<td class="center"><fmt:formatNumber
													value="${product.price}" type="currency"
													currencySymbol="R$" /></td>
											<td class="center"><c:out value="${product.category}" /></td>
											<td class="center"><c:if
													test="${product.imageURL != null}">
													<a href="../${product.imageURL}" data-lightbox="image-1"
														data-title="${product.name}"
														class="btn btn-outline btn-primary btn-xs"><fmt:message key="action.see"/></a>
												</c:if></td>
											<td class="center"><a
												class="btn btn-outline btn-primary btn-xs"
												href="Product?action=getProduct&id=<c:out value="${product.id}"/>">
												<fmt:message key="action.edit"/></a>
												<a class="btn btn-outline btn-primary btn-xs"
												href="Product?action=deleteProduct&id=<c:out value="${product.id}"/>"
												data-confirm="Tem certeza que deseja excluir?"><fmt:message key="action.delete"/></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
				<!-- /.row -->
			</div>
			<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->
	</div>

	<script src="../bower_components/jquery/dist/jquery.min.js"></script>
	<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>
	<script src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<script src="../js/lightbox.js"></script>
	<script src="../js/jquery.maskMoney.min.js"></script>
	<script src="../dist/js/sb-admin-2.js"></script>
	<script>
	    $(document).ready(function() {	        
	        $('#dataTables-example').dataTable( {
	        	  "columns": [
	        	    null,
	        	    null,
	        	    { "orderable": false }, //description
	        	    null,
	        	    null,
	        	    { "orderable": false }, //image
	        	    { "orderable": false } //actions
	        	  ]
	        	} );
	
	        $('a[data-confirm]').click(function(ev) {
	    		var href = $(this).attr('href');
	    		if (!$('#dataConfirmModal').length) {    			
	    			$('body').append('<div class="modal fade" id="dataConfirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title" id="myModalLabel">Confirmar</h4></div><div class="modal-body"></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button><a class="btn btn-primary" id="dataConfirmOK">OK</a></div></div></div></div>');
	    		} 
	    		$('#dataConfirmModal').find('.modal-body').text($(this).attr('data-confirm'));
	    		$('#dataConfirmOK').attr('href', href);
	    		$('#dataConfirmModal').modal({show:true});
	    		return false;
	    	});	
	    });
    </script>
</body>

</html>
