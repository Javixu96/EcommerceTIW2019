<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="es.uc3m.ecommerce.model.*"%>   
<%@ page import="org.apache.commons.codec.binary.StringUtils" %>
<%@ page import="org.apache.commons.codec.binary.Base64;" %>    
  <%--
<%@page import="servlet.BDServlet"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
--%>
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
						
						<%Appuser user=(Appuser)request.getAttribute("user"); %>

						<form method="POST" action="modifyUser.html" id="contact_form" enctype="multipart/form-data" autocomplete="off">
							<div class="contact_form_inputs" style="margin-bottom:5px;">
								<div class="div_profile_photo">
									<img class="profile_photo" src="<% StringBuilder sb = new StringBuilder();
									sb.append("data:image/png;base64,");
									sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(user.getUserPicture(), false)));
									out.print(sb.toString()); %>">
									<h4 style="margin-top:20px;" class="input_title">Imagen</h4>
									<input type="file" id="fileToUpLoad" name ="fileToUpLoad"  class="contact_form_name input_field inputImgUpLoad">
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
										<h4 class="profile_name">Dirección de envío</h4>
									</div>
									<div class="profile_dive_name" style="margin-left:30px;margin-top:20px;">
										 <h5><b><a href="./product_list_seller.jsp">Acceder a mi tienda</a></b></h5>
									</div>
								</div>	
								<div class="div_profile_right">


									<input type="text" name ="name" id="contact_form_name" class="profile_form input_field" placeholder="Nombre" required="required" data-error="Campo obligatorio." value=<% out.println(user.getUserName()); %>>
									<input type="text" name ="surnames" id="contact_form_surname" class="profile_form input_field" placeholder="Apellidos" required="required" data-error="Campo obligatorio."value=<% out.println(user.getUserSurnames()); %>>
									<input type="text" name ="email" id="contact_form_email" class="profile_form input_field" placeholder="Nº de teléfono"required="required"  value=<% out.println(user.getEmail()); %>>
									<input type="text" name ="direction" id="contact_form_direction" class="profile_form input_field" placeholder="Dirección de envío" required="required" data-error="Campo obligatorio."value=<% out.println(user.getPostalAddress()); %>>
								</div>	
							</div>						
						
							<div class="contact_form_button">
								<button type="submit" value="sent" class="button contact_submit_button">Guardar cambios</button>
							</div>
						
						
					<!-- Contrasena -->
					<div class="contact_form_title profile_line">Contraseña</div>
					
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
								<% if(request.getAttribute("invalidCredentialsError") !=null) {%>
									<span style="color: red;"> Contraseña incorrecta </span>
								<%} %>
								<input type="text" name="oldPassword" id="contact_form_oldpassword" class="profile_form input_field" placeholder="Contraseña anterior">
								<input type="text" name="newPassword" id="contact_form_newpassword" class="profile_form input_field" placeholder="Contraseña nueva" >
								<% if(request.getAttribute("invalidRepeatError") !=null) {%>
									<span style="color: red;"> No coincide las contraseñas </span>
								<%} %>
								<input type="text" name="newPwRepeat" id="contact_form_repeatpassword" class="profile_form input_field" placeholder="Repetir la contraseña nueva">
								</div>	
							</div>						
							
							<div class="contact_form_button">
								<button type="submit" class="button contact_submit_button">Guardar cambios</button>
							</div>
						</form>
					</div>
					<!-- delete account -->
					<div class="contact_form_title profile_line red">Eliminar cuenta</div>	
						<form action="deleteUser.html">
							<div class="profile_delete">
								<p>Una vez eliminada la cuenta, no se puede retroceder.</p>
							</div>
							<div class="profile_check">
								<input type="checkbox" id="profile_checkbox"  required="required" data-error="Checkbox is required.">
								<h4 class="profile_delete_text">Quiero eliminar mi cuenta</h4>
							</div>
						
							<div class="contact_form_button">
								<button type="submit" class="profile_delete_button">Elimina tu cuenta</button>
							</div>
						</form>
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