<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Sushizuki">
    <title>Sushizuki - Cadastrar Produto</title>
    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
                        <i class="fa fa-user fa-fw"></i> <c:out value="${user.name} " /> <i class="fa fa-caret-down"></i>
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
                            <a href="Order?action=getOrderList"><i class="fa fa-shopping-cart fa-fw"></i> Pedidos</a>
                        </li>
                        <li>
                            <a href="Product"><i class="fa fa-cutlery fa-fw"></i> Produtos</a>
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
	                <div class="message">
		            		 <c:if test="${message == 'sucess'}">
					        	<div class="alert alert-success alert-dismissable">
					                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					                Realizado com sucesso!
					            </div>
				            </c:if>
				        	<c:if test="${message == 'failure'}">            
					            <div class="alert alert-danger alert-dismissable">
					                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					                Algum erro ocorreu.
					            </div>
				            </c:if>
		            	</div>
                    <h1 class="page-header">Produto</h1>
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
                                    <form role="form" name="formProduct" action="Product" method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                        	<input type="hidden" name="id" readonly value="<c:out value="${product.id}" />" />
                                            <label>Nome:</label>
                                            <input class="form-control" name="name" placeholder="Nome do produto" 
                                            	value="<c:out value="${product.name}" />" />
                                        </div>
                                        <div class="form-group">
                                            <label>Descrição:</label>
                                            <textarea class="form-control" name="description" rows="3" 
                                            placeholder="Breve descrição para o produto"><c:out value="${product.description}" /></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Preço:</label>
	                                        <div class="input-group">
	                                            <span class="input-group-addon">R$</span>
	                                            <input type="text" name="price" id="price" class="form-control" style="width:80px;" maxlength="6"
	                                            value="<c:out value="${product.price}" />" />
	                                        </div>
	                                    </div>
                                        <div class="form-group">
                                            <label>Imagem:</label>
			            				 	<c:if test="${not empty product.imageURL}">
			                                    <div class="well well-sm" style="width: 120px">
							                        <img src="../${product.imageURL}" data-lightbox="image-1" data-title="${product.name}" style="width:100px;" />
							                    </div>
		                                    </c:if>
                                            <input type="file" class="btn btn-default" name="img" accept="image/jpeg; image/png">
                                        </div>
                                         <div class="form-group">
                                        	<label>Categoria:</label>
                                        	<select name="category" class="form-control">
                                        	<c:forEach items="${categories}" var="category" varStatus="theCount"> 
												<option <c:if test="${product.category == category}">selected</c:if> value="${theCount.count}"><c:out value="${category}" /></option>
											</c:forEach>
											</select>
                                        </div>                                                                      
                                        <button type="button" class="btn btn-primary" onclick="validation()">Enviar</button>
                                        <button type="button" class="btn btn-primary" onclick="confirmation()">Limpar</button>                                       
                                    </form>
                                </div>                                
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    <p>
                    	<a class="btn btn-outline btn-primary" href="Product">Voltar</a>
                    </p>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Javascripts -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <script src="../dist/js/sb-admin-2.js"></script>    
    <script src="../js/jquery.maskMoney.min.js"></script>	
	<script>
    $(document).ready(function() {
        
      	$(".alert").addClass("in");      	
      	$("#price").maskMoney();
      	$('form').validate({
      	    rules: {
      	        name: {
      	            minlength: 3,
      	            maxlength: 45,
      	            required: true
      	        },
      	        description: {
      	            minlength: 3,
      	            maxlength: 150,
      	            required: false
      	        },
      	        price: {
    	            required: true
    	        },
      	    },
      	  errorPlacement: function(error, element) {
              error.insertAfter('.form-group'); 
          }, 
          highlight: function(element) {
              $(element).closest('.form-group').addClass('has-error');
          },
          unhighlight: function(element) {
              $(element).closest('.form-group').removeClass('has-error');
          }
      	});

    });
    </script>	
</body>
</html>
