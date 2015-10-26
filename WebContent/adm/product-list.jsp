<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sushizuki</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="../bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
    
    <!-- Lightbox -->
    <link href="../css/lightbox.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="dashboard.jsp"><img src="../img/sushi/sushizuki-logo.png" style="width:100px;" alt="Sushizuki" /></a>
            </div>
            <!-- /.navbar-header -->

           
            <ul class="nav navbar-top-links navbar-right">
                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> Administrator name <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Sair</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="index.html"><i class="fa fa-shopping-cart fa-fw"></i> Pedidos</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-envelope fa-fw"></i> Mensagens</a>
                        </li>
                        <li>
                            <a href="Product"><i class="fa fa-cutlery fa-fw"></i> Produtos</a>
                        </li>
                        <li>
                            <a href="forms.html"><i class="fa fa-gear fa-fw"></i> Administrador</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">       
            <div class="row">
	            <div class="col-lg-12">
	            	<div class="message" id="message">
	            		 <c:if test="${message == 'sucess'}">
				        	<div class="alert alert-success alert-dismissable fade">
				                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				                Realizado com sucesso!
				            </div>
			            </c:if>
			        	<c:if test="${message == 'failure'}">            
				            <div class="alert alert-danger alert-dismissable fade">
				                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				                Algum erro ocorreu.
				            </div>
			            </c:if>
	            	</div>		           
                    <h1 class="page-header">Produtos</h1>   
                    <div class="form-group">                 
                    	<a class="btn btn-primary" href="Product?action=new">Cadastrar novo produto</a>  
                    </div>                  
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Lista de produtos cadastrados
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                        	<th style="display: none">ID</th>
                                            <th>Nome</th>
                                            <th>Descrição</th>
                                            <th class="center">Preço</th>
                                            <th class="center">Categoria</th>
                                            <th class="center">Imagem</th>
                                            <th class="center">Ações</th>
                                        </tr>
                                    </thead>
                                    <tbody>                                    	
                                    <c:forEach items="${products}" var="product">                                    	
                                        <tr class="gradeA">
                                        	<td style="display: none"><c:out value="${product.id}" /></td>
                                            <td><c:out value="${product.name}" /></td>
                                            <td><c:out value="${product.description}" /></td>
                                            <td class="center"><c:out value="${product.price}" /></td>
                                            <td class="center"><c:out value="${product.category}" /></td>
                                            <td class="center">
                                            	<c:if test="${product.imgUrl != null}">
	                                            	<a href="../${product.imgUrl}" data-lightbox="image-1" data-title="${product.name}" class="btn btn-outline btn-primary btn-xs">
														Ver
													</a>
												</c:if>
                                            </td>
                                            <td class="center">
                               					<a class="btn btn-outline btn-primary btn-xs" href="Product?action=update&id=<c:out value="${product.id}"/>">Editar</a>
												<a class="btn btn-outline btn-primary btn-xs" href="Product?action=delete&id=<c:out value="${product.id}"/>" data-confirm="Tem certeza que deseja excluir?">Excluir</a>                                                	
                                            </td>
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
	            </div>
	            <!-- /.row -->
	        </div>
	        <!-- /#page-wrapper -->
	
	    </div>
	    <!-- /#wrapper -->
    </div>

    <!-- jQuery -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Lightbox -->
    <script src="../js/lightbox.js"></script>
    
    <!-- Mask Money -->
    <script src="../js/jquery.maskMoney.min.js"></script>
    
    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        
        $('#dataTables-example').dataTable( {
        	  "columns": [
        	    null,
        	    null,
        	    { "orderable": false }, //description
        	    null,
        	    null,
        	    { "orderable": false }, //img
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

      	$(".alert").addClass("in")

    });
    </script>

</body>

</html>
