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

    <title>Sushizuki - Cadastrar Produto</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

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

<script type="text/javascript"> 
 function validation(){ 
	 if(document.formProduct.name.value==""){
		 alert( "Preencha o campo NOME!" );
 		 return false;
 	 }
	 if(document.formProduct.description.value==""){
		 alert( "Preencha o campo DESCRI��O!" );
 		 return false;
	 }
	 if(document.formProduct.price.value==""){
		 alert( "Preencha o PRE�O!" );
 		 return false;
	 }
	 if(isNaN(document.formProduct.price.value)){
		 alert( "Digite apenas n�meros!" );
		 return false;
	 }
 document.formProduct.submit();
}
 
function confirmation (){
	decision = confirm("Todos os campos ser�o apagados");
	if(decision)
		document.formProduct.reset();	
}
</script>
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
                                            <input type="file" name="img" accept="image/jpeg; image/png">
                                        </div>
                                         <div class="form-group">
                                        	<label>Categoria:</label>
                                        	<select name="category" class="form-control">
												<option value="1">Sushi</option>
												<option value="2">Uramaki</option>
												<option value="3">Sashimi</option>
												<option value="4">Temaki</option>
												<option value="5">Niguiri</option>
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

    <!-- jQuery -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>
    
    <!-- Mask Money -->
    <script src="../js/jquery.maskMoney.min.js"></script>
	
	<script>
    $(document).ready(function() {
        
      	$(".alert").addClass("in")
      	
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
