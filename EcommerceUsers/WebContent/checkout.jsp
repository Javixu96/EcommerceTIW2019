<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Checkout</title>
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
						

						<form action="sendOrderMessage.html" id="address_payment_form">
							<div class="contact_form_title">Informacion del pedido</div>
					
							<div class="contact_form_inputs d-flex flex-md-row flex-column justify-content-between align-items-between">
								Cantidad a pagar: 100&euro;
							</div>
							
							<div class="contact_form_title">Informacion de envio</div>
						
							<h4 style="padding-bottom: 10px"> Direccion de envio </h4>
							<div class="contact_form_inputs d-flex flex-md-row flex-column justify-content-between align-items-between">
								<input id="deliveryAddress" name="deliveryAddress" class="contact_form_name input_field" placeholder="Introduce tu dirección de envío" required="required" data-error="El email es un dato obligatorio.">
							</div>
							
							<div class="contact_form_title">Informacion de pago</div>
							
							<h4 style="padding-bottom: 10px"> Numero de tarjeta </h4>
							<div class="contact_form_inputs d-flex flex-md-row flex-column justify-content-between align-items-between">
								<input id="card" name="card" class="contact_form_name input_field" placeholder="Introduce tu número de tarjeta" required="required" data-error="El email es un dato obligatorio.">
							</div>
							
							<div class="row_container">
								<div class="row_item">
									<h4 class="input_title"> Caducidad </h4>
									<input type="text" name="expiration" id="expiration" class="contact_form_name input_field" placeholder="(MM/AA)" required="required" data-error="El nombre es un dato obligatorio.">
								</div>
								<div class="row_item">
									<h4 class="input_title"> CVV </h4>
									<input type="text" name="cvv" id="cvv" class="contact_form_name input_field" placeholder="123" required="required" data-error="Los apellidos es un dato obligatorio.">
								</div>							
							</div>
							<div class="contact_form_button center_horizontal">
								<button type="submit" class="button contact_submit_button">Finalizar pedido</button>
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
<script src="js/contact_custom.js"></script>
</body>

</html>