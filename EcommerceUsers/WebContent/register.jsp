<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Registro</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/register_styles.css">
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
						<div class="contact_form_title">Registro</div>

						<form action="#" id="contact_form">
							
							<div class="row_container">
								<div class="row_item">
									<h4 class="input_title"> Nombre </h4>
									<input type="text" id="register_name" class="contact_form_name input_field" placeholder="Introduce tu nombre" required="required" data-error="El nombre es un dato obligatorio.">
								</div>
								<div class="row_item">
									<h4 class="input_title"> Apellido </h4>
									<input type="text" id="register_surname" class="contact_form_name input_field" placeholder="Introduce tus apellidos" required="required" data-error="Los apellidos es un dato obligatorio.">
								</div>							
							</div>
							
							<div class="row_container">
								<div class="row_item">
									<h4 style="padding-bottom: 10px"> Teléfono </h4>
									<input type="text" id="register_phone" class="contact_form_name input_field" placeholder="Introduce tu teléfono" required="required" data-error="El teléfono es un dato obligatorio.">
								</div>
								<div class="row_item">
									<h4 style="padding-bottom: 10px"> Dirección de envío </h4>
									<input type="text" id="register_address" class="contact_form_name input_field" placeholder="Introduce tu dirección de envio" required="required" data-error="La dirección de envío es un dato obligatorio.">	
								</div>							
							</div>
							
						
							<h4 style="padding-bottom: 10px"> Correo electrónico </h4>
							<div class="contact_form_inputs d-flex flex-md-row flex-column justify-content-between align-items-between">
								<input type="email" id="register_email" class="contact_form_name input_field" placeholder="Introduce tu email" required="required" data-error="El email es un dato obligatorio.">
							</div>
							<h4 style="padding-bottom: 10px"> Contraseña </h4>
							<div class="contact_form_inputs d-flex flex-md-row flex-column justify-content-between align-items-between">
								<input type="password" id="register_password" class="contact_form_name input_field" placeholder="Introduce tu contraseña" required="required" data-error="La contraseña es un dato obligatorio.">
							</div>
							
							<div class="contact_form_button center_horizontal">
								<button type="submit" class="button contact_submit_button">Crear cuenta</button>
							</div>
						</form>
						<div class="contact_form_button center_horizontal" style="padding-top: 20px; padding-bottom: 20px" >
							<a href="./login.jsp" style="color: #0e8ce4; font-size: 16px">Ya tengo una cuenta</a>
						</div>
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
<script src="js/contact_custom.js"></script>
</body>

</html>