<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>B&uacute;squeda avanzada</title>
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
						<div class="contact_form_title">B&uacute;squeda avanzada</div>
						<div align="right"> <a href="javascript:history.back()" style="color: #0e8ce4; font-size: 16px">Atr&aacute;s</a></div>
						<form action="advanced_search.html" method="post">
							<div class="row_container">
								<div class="row_item">
									<h4 class="input_title"> Texto a buscar </h4>
									<input type="text" id="advanced_query" name="advanced_query" class="contact_form_name input_field" placeholder="Introduce tu nombre" required="required" data-error="El nombre es un dato obligatorio.">
								</div>					
							</div>
							<div class="row_container">
								<div class="row_item">
									<h4 class="input_title"> ¿En qu&eacute; campo quieres buscar? </h4>
									<input type="radio"  name="search_field" value="product_name"  required="required" data-error="Debes elegir una opción"> Nombre del producto<br>
									<input type="radio"  name="search_field" value="product_description"  required="required" data-error="Debes elegir una opción"> Descripci&oacute;n del producto<br>
									<input type="radio"  name="search_field" value="product_category"  required="required" data-error="Debes elegir una opción"> Categor&iacute;a del producto<br>
									<input type="radio"  name="search_field" value="product_all"  required="required" data-error="Debes elegir una opción"> Voy a tener suerte...<br>
								</div>
								
								<div class="row_item">
									<h4 style="padding-bottom: 10px"> Rango de precio </h4>
									<input type="text" name="min_price" class="contact_form_name input_field" placeholder="Precio M&iacute;nimo">
									<input type="text" name="max_price" class="contact_form_name input_field" placeholder=" Precio M&aacute;ximo">
								</div>
							</div>
							<div class="contact_form_button center_horizontal">
								<button type="submit" class="button contact_submit_button">Buscar</button>								
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