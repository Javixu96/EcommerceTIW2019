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
    <title>Usuarios - Ecommerce</title>
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
                                <h3 class="mb-2">Lista de usuarios</h3>
                                <p class="pageheader-text">texto para mostrar, ahora mismo escondido con css</p>
                            </div>
                        </div>
                    </div>
                   
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="card">
                                <div class="card-body">
                                    <form>
                                        <input class="form-control form-control-lg" type="search" placeholder="Introducir nombre a buscar..." aria-label="Search">
                                        <button class="btn btn-primary search-btn" type="submit">Buscar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                             <!-- ============================================================== -->
                                <!-- tarjeta vendedor / -->
                                <!-- ============================================================== -->
                            <div class="card">
                                <div class="card-body">
                                    <div class="row align-items-center">
                                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                            <div class="user-avatar float-xl-left pr-4 float-none">
                                                <img src="assets/images/avatar-1.jpg" alt="User Avatar" class="rounded-circle user-avatar-xl">
                                            </div>
                                            <div class="pl-xl-3">
                                                <div class="m-b-0">
                                                    <div class="user-avatar-name d-inline-block">
                                                        <h2 class="font-24 m-b-10">Vendedor 1 </h2>
                                                        <p class="mb-2"><i class="fa fa-at mr-2  text-primary"></i> [Aquí la dirección de correo electrónico] </p>
                                                        <p class="mb-2"><i class="fa fa-map-marker-alt mr-2  text-primary"></i> [Aquí la dirección de envío] </p>
                                                      
                                                    </div>
                                                    <!-- <div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-12"> -->
                                                        <div class="float-xl-right float-none mt-xl-0 mt-4">
                                                            <a href="#" class="btn btn-rounded btn-primary "><i class="fa fa-fw fa-list-ol"></i> Ver lista de productos</a>
                                                            <a href="#" class="btn btn-rounded btn-secondary"><i class="fa fa-fw fa-envelope"></i> Enviar mensaje</a>
                                                            <a href="#" class="btn btn-rounded btn-info"><i class="fa fa-fw fa-edit"></i> Modificar usuario</a>
                                                            <a href="#" class="btn btn-rounded btn-danger"><i class="fa fa-fw fa-trash-alt"></i> Eliminar usuario</a>
                                                        </div>
                                                    <!-- </div> -->
                                                </div>
                                                <div class="user-avatar-address">
                                                    <div class="mt-3">
                                                        <div class="table-responsive">
                                                            <table class="table">
                                                                <thead class="bg-light">
                                                                    <!-- <tr>
                                                                        <td class="border-0" colspan="9"><a href="#" class="btn btn-outline-light float-right">View Details</a></td>
                                                                    </tr> -->
                                                                    <tr class="border-0">
                                                                        <th class="border-0">#</th>
                                                                        <th class="border-0">Image</th>
                                                                        <th class="border-0">Product Name</th>
                                                                        <th class="border-0">Product Id</th>
                                                                        <th class="border-0">Quantity</th>
                                                                        <th class="border-0">Price</th>
                                                                        <th class="border-0">Order Time</th>
                                                                        <th class="border-0">Customer</th>
                                                                        <th class="border-0">Status</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>1</td>
                                                                        <td>
                                                                            <div class="m-r-10"><img src="assets/images/product-pic.jpg" alt="user" class="rounded" width="45"></div>
                                                                        </td>
                                                                        <td>Product #1 </td>
                                                                        <td>id000001 </td>
                                                                        <td>20</td>
                                                                        <td>$80.00</td>
                                                                        <td>27-08-2018 01:22:12</td>
                                                                        <td>Patricia J. King </td>
                                                                        <td><span class="badge-dot badge-brand mr-1"></span>InTransit </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>2</td>
                                                                        <td>
                                                                            <div class="m-r-10"><img src="assets/images/product-pic-2.jpg" alt="user" class="rounded" width="45"></div>
                                                                        </td>
                                                                        <td>Product #2 </td>
                                                                        <td>id000002 </td>
                                                                        <td>12</td>
                                                                        <td>$180.00</td>
                                                                        <td>25-08-2018 21:12:56</td>
                                                                        <td>Rachel J. Wicker </td>
                                                                        <td><span class="badge-dot badge-success mr-1"></span>Delivered </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>3</td>
                                                                        <td>
                                                                            <div class="m-r-10"><img src="assets/images/product-pic-3.jpg" alt="user" class="rounded" width="45"></div>
                                                                        </td>
                                                                        <td>Product #3 </td>
                                                                        <td>id000003 </td>
                                                                        <td>23</td>
                                                                        <td>$820.00</td>
                                                                        <td>24-08-2018 14:12:77</td>
                                                                        <td>Michael K. Ledford </td>
                                                                        <td><span class="badge-dot badge-success mr-1"></span>Delivered </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>4</td>
                                                                        <td>
                                                                            <div class="m-r-10"><img src="assets/images/product-pic-4.jpg" alt="user" class="rounded" width="45"></div>
                                                                        </td>
                                                                        <td>Product #4 </td>
                                                                        <td>id000004 </td>
                                                                        <td>34</td>
                                                                        <td>$340.00</td>
                                                                        <td>23-08-2018 09:12:35</td>
                                                                        <td>Michael K. Ledford </td>
                                                                        <td><span class="badge-dot badge-success mr-1"></span>Delivered </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                        </div>                                    
                                    </div>
                                </div>
                            </div>
                            
                            <!-- ============================================================== -->
                            <!-- end card influencer one -->
                            <!-- ============================================================== -->
                                
                          
                            <div class="card">
                                <div class="card-body">
                                    <div class="row align-items-center">
                                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                            <div class="user-avatar float-xl-left pr-4 float-none">
                                                <img src="assets/images/avatar-1.jpg" alt="User Avatar" class="rounded-circle user-avatar-xl">
                                            </div>
                                            <div class="pl-xl-3">
                                                <div class="m-b-0">
                                                    <div class="user-avatar-name d-inline-block">
                                                        <h2 class="font-24 m-b-10">Usuario 1 </h2>
                                                        <p class="mb-2"><i class="fa fa-at mr-2  text-primary"></i> [Aquí la dirección de correo electrónico] </p>
                                                        <p class="mb-2"><i class="fa fa-map-marker-alt mr-2  text-primary"></i> [Aquí la dirección de envío] </p>
                                                       
                                                    </div>
                                                    <!-- <div class="col-xl-3 col-lg-12 col-md-12 col-sm-12 col-12"> -->
                                                        <div class="float-xl-right float-none mt-xl-0 mt-4">
                                                            <a href="#" class="btn btn-rounded btn-primary "><i class="fa fa-fw fa-list-ol"></i> Ver lista de productos</a>
                                                            <a href="#" class="btn btn-rounded btn-secondary"><i class="fa fa-fw fa-envelope"></i> Enviar mensaje</a>
                                                            <a href="#" class="btn btn-rounded btn-info"><i class="fa fa-fw fa-edit"></i> Modificar usuario</a>
                                                            <a href="#" class="btn btn-rounded btn-danger"><i class="fa fa-fw fa-trash-alt"></i> Eliminar usuario</a>
                                                        </div>
                                                    <!-- </div> -->
                                                </div>
                                                <div class="user-avatar-address">
                                                    <div class="mt-3">
                                                        <div class="table-responsive">
                                                            <table class="table">
                                                                <thead class="bg-light">
                                                                    <!-- <tr>
                                                                        <td class="border-0" colspan="9"><a href="#" class="btn btn-outline-light float-right">View Details</a></td>
                                                                    </tr> -->
                                                                    <tr class="border-0">
                                                                        <th class="border-0">#</th>
                                                                        <th class="border-0">Image</th>
                                                                        <th class="border-0">Product Name</th>
                                                                        <th class="border-0">Product Id</th>
                                                                        <th class="border-0">Quantity</th>
                                                                        <th class="border-0">Price</th>
                                                                        <th class="border-0">Order Time</th>
                                                                        <th class="border-0">Customer</th>
                                                                        <th class="border-0">Status</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>1</td>
                                                                        <td>
                                                                            <div class="m-r-10"><img src="assets/images/product-pic.jpg" alt="user" class="rounded" width="45"></div>
                                                                        </td>
                                                                        <td>Product #1 </td>
                                                                        <td>id000001 </td>
                                                                        <td>20</td>
                                                                        <td>$80.00</td>
                                                                        <td>27-08-2018 01:22:12</td>
                                                                        <td>Patricia J. King </td>
                                                                        <td><span class="badge-dot badge-brand mr-1"></span>InTransit </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>2</td>
                                                                        <td>
                                                                            <div class="m-r-10"><img src="assets/images/product-pic-2.jpg" alt="user" class="rounded" width="45"></div>
                                                                        </td>
                                                                        <td>Product #2 </td>
                                                                        <td>id000002 </td>
                                                                        <td>12</td>
                                                                        <td>$180.00</td>
                                                                        <td>25-08-2018 21:12:56</td>
                                                                        <td>Rachel J. Wicker </td>
                                                                        <td><span class="badge-dot badge-success mr-1"></span>Delivered </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>3</td>
                                                                        <td>
                                                                            <div class="m-r-10"><img src="assets/images/product-pic-3.jpg" alt="user" class="rounded" width="45"></div>
                                                                        </td>
                                                                        <td>Product #3 </td>
                                                                        <td>id000003 </td>
                                                                        <td>23</td>
                                                                        <td>$820.00</td>
                                                                        <td>24-08-2018 14:12:77</td>
                                                                        <td>Michael K. Ledford </td>
                                                                        <td><span class="badge-dot badge-success mr-1"></span>Delivered </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>4</td>
                                                                        <td>
                                                                            <div class="m-r-10"><img src="assets/images/product-pic-4.jpg" alt="user" class="rounded" width="45"></div>
                                                                        </td>
                                                                        <td>Product #4 </td>
                                                                        <td>id000004 </td>
                                                                        <td>34</td>
                                                                        <td>$340.00</td>
                                                                        <td>23-08-2018 09:12:35</td>
                                                                        <td>Michael K. Ledford </td>
                                                                        <td><span class="badge-dot badge-success mr-1"></span>Delivered </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                        </div>                                    
                                    </div>
                                </div>
                            </div>
                            
                            <!-- ============================================================== -->
                            <!-- end card influencer one -->
                            <!-- ============================================================== -->
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