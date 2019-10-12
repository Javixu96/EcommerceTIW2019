<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Añadir producto</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/add_product_styles.css">
<link rel="stylesheet" type="text/css" href="styles/contact_responsive.css">

</head>

<body>

<div class="super_container">
	
	<!-- Header -->
	<%@ include file="header.jsp" %>

	<!-- Contact Form -->

	<div class="contact_form">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 offset-lg-1">
					<div class="contact_form_container">
						<div class="contact_form_title">Añadir producto</div>

						<form action="#" id="contact_form">
							
							<div class="row_container">
								<div class="row_item">
									<h4 class="input_title">Nombre</h4>
									<input type="text" id="register_name" class="contact_form_name input_field" placeholder="Introduce el nombre del producto" required="required" data-error="El nombre es un dato obligatorio.">
								</div>
								<div class="row_item">
									<h4 class="input_title">Imagen</h4>
									<input type="text" id="register_surname" class="contact_form_name input_field" placeholder="Introduce la URL de la imagen del producto" required="required" data-error="Los apellidos es un dato obligatorio.">
								</div>		
								<div style="flex: 0.2; margin: 0 30px;">
									<h4 class="input_title">Precio</h4>
									<input type="text" id="register_surname" class="contact_form_name input_field" placeholder="Precio" required="required" data-error="Los apellidos es un dato obligatorio.">
								</div>							
							</div>
							
							<h4 style="padding-bottom: 10px">Descripción</h4>
							<div class="contact_form_inputs d-flex flex-md-row flex-column justify-content-between align-items-between">
								<textarea rows="4" placeholder="Introduce la descripción del producto"></textarea>
							</div>
							
							
							<div class="row_container clearfix">
							
								<div class="row_item">
									<h4>Categoría</h4>
									<ul class="product_size">
										<li>
											
											<div class="size_mark_container"><div id="selected_categ" class="size_mark">
												Categoría</div></div>
											<div class="size_dropdown_button"><i class="fas fa-chevron-down"></i></div>

											<ul class="color_size">
												<li><div class="size_mark" id="categ1" >Hombre</div></li>
												<li><div class="size_mark" id="categ2" >Mujer</div></li>
												<li><div class="size_mark" id="categ3" >Niño</div></li>
											</ul>
										</li>
									</ul>
								</div>
								<div class="row_item">
									<h4>Subcategoría</h4>
									<ul class="product_subcateg">
										<li>
											
											<div class="size_mark_container"><div id="selected_subcateg" class="size_mark">
												Subcategoría
											</div></div>
											<div class="size_dropdown_button"><i class="fas fa-chevron-down"></i></div>

											<ul class="color_size">
												<li><div class="size_mark" id="subcateg1" >Abrigos</div></li>
												<li><div class="size_mark" id="subcateg2" >Camisas</div></li>
												<li><div class="size_mark" id="subcateg3" >Camisetas</div></li>
												<li><div class="size_mark" id="subcateg4" >Pantalones</div></li>
												<li><div class="size_mark" id="subcateg5" >Sudaderas</div></li>
											</ul>
										</li>
									</ul>
								</div>		
													
							</div>
							
							
							<div class="contact_form_button center_horizontal">
								<button type="submit" class="button contact_submit_button">Crear producto</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="panel"></div>
	</div>

	<!-- Newsletter -->

	<div class="newsletter">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="newsletter_container d-flex flex-lg-row flex-column align-items-lg-center align-items-center justify-content-lg-start justify-content-center">
						<div class="newsletter_title_container">
							<div class="newsletter_icon"><img src="images/send.png" alt=""></div>
							<div class="newsletter_title">Suscríbete a nuestra newsletter</div>
							<div class="newsletter_text"><p>...y obtén un cupón descuento del 20% para la 1ª compra.</p></div>
						</div>
						<div class="newsletter_content clearfix">
							<form action="#" class="newsletter_form">
								<input type="email" class="newsletter_input" required="required" placeholder="Introduce tu correo electrónico">
								<button class="newsletter_button">Suscribirse</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer --> 
	<%@ include file="footer.jsp" %>
	
</div>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="styles/bootstrap4/popper.js"></script>
<script src="styles/bootstrap4/bootstrap.min.js"></script>
<script src="plugins/greensock/TweenMax.min.js"></script>
<script src="plugins/greensock/TimelineMax.min.js"></script>
<script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="plugins/greensock/animation.gsap.min.js"></script>
<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCIwF204lFZg1y4kPSIhKaHEXMLYxxuMhA"></script>
<script src="js/add_product.js"></script>
</body>

</html>