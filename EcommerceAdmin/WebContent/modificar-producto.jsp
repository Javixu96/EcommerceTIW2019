<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
 
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link href="assets/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/libs/css/style.css">
    <link rel="stylesheet" href="assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <title>Modificar producto</title>
</head>

<body>
    <div class="dashboard-main-wrapper">
        <!-- ============================================================== -->
        <!-- navbar -->
        <!-- ============================================================== -->
        <%@ include file="header.jsp" %>
        
        <!-- ============================================================== -->
        <!-- end navbar -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- left sidebar -->
        <!-- ============================================================== -->
        <%@ include file="menu-lateral.jsp" %>
        
        <!-- ============================================================== -->
        <!-- end left sidebar -->
        <!-- ============================================================== -->
      
        <div class="dashboard-wrapper">
            <div class="influence-finder">
                <div class="container-fluid dashboard-content">
                   
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="page-header">
                                <h3 class="mb-2">Modificar producto</h3>
                                <p class="pageheader-text">texto para mostrar, ahora mismo escondido con css</p>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xl-8 col-lg-8 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <!-- <h5 class="card-header">Basic Form</h5> -->
                                <div class="card-body">
                                    <form action="#" id="basicform" data-parsley-validate="">
                                        <!-- foto, precio, cantidad en almacén -->
                                        <div class="form-group">
                                            <label for="inputUserName">Nombre</label>
                                            <input id="inputUserName" type="text" name="name" data-parsley-trigger="change" required="" placeholder="" autocomplete="off" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="inputUserName">Descripción corta</label>
                                            <input id="inputUserName" type="text" name="name" data-parsley-trigger="change" required="" placeholder="" autocomplete="off" class="form-control">
                                        </div>
                                        <div class="form-group">
                                                <label for="exampleFormControlTextarea1">Descripción larga</label>
                                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                                            </div>
                                        <div class="form-group">
                                            <label for="inputEmail">Categoría</label>
                                            <div>
                                                <label class="custom-control custom-radio custom-control-inline">
                                                <input type="radio" name="radio-inline" checked="" class="custom-control-input"><span class="custom-control-label">Option 1</span>
                                                </label>
                                                <label class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" name="radio-inline" class="custom-control-input"><span class="custom-control-label">Option 2</span>
                                                </label>
                                                <label class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" name="radio-inline" class="custom-control-input"><span class="custom-control-label">Option 3</span>
                                                </label>
                                                <label class="custom-control custom-radio custom-control-inline">
                                                <input type="radio" name="radio-inline" checked="" class="custom-control-input"><span class="custom-control-label">Option 1</span>
                                                </label>
                                                <label class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" name="radio-inline" class="custom-control-input"><span class="custom-control-label">Option 2</span>
                                                </label>
                                                <label class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" name="radio-inline" class="custom-control-input"><span class="custom-control-label">Option 3</span>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                                <label for="inputEmail">Subcategoría</label>
                                                <div>
                                                    <label class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" name="radio-inline" checked="" class="custom-control-input"><span class="custom-control-label">Option 1</span>
                                                    </label>
                                                    <label class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" name="radio-inline" class="custom-control-input"><span class="custom-control-label">Option 2</span>
                                                    </label>
                                                    <label class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" name="radio-inline" class="custom-control-input"><span class="custom-control-label">Option 3</span>
                                                    </label>
                                                </div>
                                            </div>
                                        <div class="form-group">
                                            <label for="inputUserName">Precio</label>
                                            <div class="input-group input-group-sm mb-3">
                                                <div class="input-group-prepend"><span class="input-group-text">€</span></div>
                                                <input id="inputUserName" type="email" name="name" data-parsley-trigger="change" required="" placeholder="" autocomplete="off" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputUserName">Cantidad en almacén</label>
                                            <div class="input-group input-group-sm mb-3">
                                                <div class="input-group-prepend"><span class="input-group-text">#</span></div>
                                                <input id="inputUserName" type="email" name="name" data-parsley-trigger="change" required="" placeholder="" autocomplete="off" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6 pb-2 pb-sm-4 pb-lg-0 pr-0">
                                                <!-- <label class="be-checkbox custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input"><span class="custom-control-label">Remember me</span>
                                                </label> -->
                                            </div>
                                            <div class="col-sm-6 pl-0">
                                                <p class="text-right">
                                                    <button type="submit" class="btn btn-space btn-primary">Actualizar</button>
                                                    <button type="submit" class="btn btn-space btn-danger">Eliminar</button>
                                                    <button class="btn btn-space btn-secondary">Cancelar</button>
                                                </p>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>




                </div>
            </div>
            <!-- ============================================================== -->
            <!-- end wrapper  -->
            <!-- ============================================================== -->
        </div>
            <!-- ============================================================== -->
            <!-- end main wrapper  -->
            <!-- ============================================================== -->
            <!-- Optional JavaScript -->
            <!-- jquery 3.3.1 -->
            <script src="assets/vendor/jquery/jquery-3.3.1.min.js"></script>
            <!-- bootstap bundle js -->
            <script src="assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
            <!-- slimscroll js -->
            <script src="assets/vendor/slimscroll/jquery.slimscroll.js"></script>
            <!-- main js -->
            <script src="assets/libs/js/main-js.js"></script>
</body>
 
</html>