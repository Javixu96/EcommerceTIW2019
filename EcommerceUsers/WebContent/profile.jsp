<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Perfil</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/profile_styles.css">
<link rel="stylesheet" type="text/css" href="styles/profile_responsive.css">

</head>

<body>

<div class="super_container">
	
	<!-- Header -->
	<%@ include file="header.jsp" %>

	<!-- Contact Info -->

	<!-- Contact Form -->

	<div class="contact_form">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 offset-lg-1">
					<div class="contact_form_container">
					
					<!-- Perfil -->
						<div class="contact_form_title profile_line">Datos personales</div>

						<form action="./index.jsp" id="contact_form">
							<div class="contact_form_inputs">
								<div class="div_profile_photo">
									<img class="profile_photo" src="images/photo_profile.png" alt="">
								</div>
								<div class="div_profile_left">
									<div class="profile_div_name">
										<h4 class="profile_name">Nombre </h4>
									</div>
									<div class="profile_div_name">
										<h4 class="profile_name">Apellidos</h4>
									</div>	
									<div class="profile_div_name">
										<h4 class="profile_name">Email</h4>
									</div>
									<div class="profile_div_name">
										<h4 class="profile_name">Telefóno</h4>
									</div>
									<div class="profile_div_name">
										<h4 class="profile_name">Dirección de envío</h4>
									</div>
									<div class="profile_div_name">
										<h4 class="profile_name">Nº de productos comprados</h4>
									</div>
									<div class="profile_div_name">
										<h4 class="profile_name">Nº de productos vendidos</h4>
									</div>
								</div>	
								<div class="div_profile_right">
									<input type="text" id="contact_form_name" class="profile_form input_field" placeholder="Nombre" required="required" data-error="Campo obligatorio."value="Alberto">
									<input type="text" id="contact_form_surname" class="profile_form input_field" placeholder="Apellidos" required="required" data-error="Campo obligatorio."value="García">
									<input type="text" id="contact_form_email" class="profile_form input_field" placeholder="Nº de teléfono" value="AlbertoG@gmail.com">
									<input type="text" id="contact_form_phone" class="profile_form input_field" placeholder="Correo electrónico" data-error="Campo obligatorio." value="623785928">
									<input type="text" id="contact_form_direction" class="profile_form input_field" placeholder="Dirección de envío" required="required" data-error="Campo obligatorio."value="Calle real 54 1ºB, Colmenarejo,Madrid 28270">
									<div class="profile_form">
										<h5 class="product_number"><b>35</b></h5>
									</div>
									<div class="profile_form">
										<h5 class="product_number"><b>17&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 <a href="./product_list_seller.jsp">Acceder a mi tienda</a></b></h5>
									</div>
								</div>	
							</div>						
						
							<div class="contact_form_button">
								<button type="submit" class="button contact_submit_button">Guardar cambios</button>
							</div>
						</form>
						
					<!-- Contrasena -->
					<div class="contact_form_title profile_line">Contraseña</div>
						<form action="#">
							<div class="contact_form_inputs">
								<div class="div_profile_left">
									<div class="profile_div_name">
										<h4 class="profile_name">Contraseña anterior</h4>
									</div>
									<div class="profile_div_name">
										<h4 class="profile_name">Contraseña nueva</h4>
									</div>	
									<div class="profile_div_name">
										<h4 class="profile_name">Confirmar la contraseña</h4>
									</div>
								</div>	
								<div class="div_profile_right">
								<input type="text" id="contact_form_oldpassword" class="profile_form input_field" placeholder="Contraseña anterior" required="required" data-error="Name is required.">
								<input type="text" id="contact_form_newpassword" class="profile_form input_field" placeholder="Contraseña nueva" required="required" data-error="surname is required.">
								<input type="text" id="contact_form_repeatpassword" class="profile_form input_field" placeholder="Repetir la contraseña nueva" required="required" data-error="email is required.">
								</div>	
							</div>						
							
							<div class="contact_form_button">
								<button type="submit" class="button contact_submit_button">Guardar cambios</button>
							</div>
						</form>
					</div>
					<!-- delete account -->
					<div class="contact_form_title profile_line red">Eliminar cuenta</div>	
						<div class="profile_delete">
							<p>Una vez eliminada la cuenta, no se puede retroceder, por favor con mucho cuidado.</p>
						</div>
						<div class="profile_check">
							<input type="checkbox" id="profile_checkbox"  required="required" data-error="Checkbox is required.">
							<h4 class="profile_delete_text">Quiero eliminar mi cuenta</h4>
						</div>
						<div class="contact_form_button">
							<button type="submit" class="profile_delete_button">Elimina tu cuenta</button>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		<div class="panel"></div>
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