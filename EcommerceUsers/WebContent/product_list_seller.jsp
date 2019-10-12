<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Lista de productos</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/cart_styles.css">
<link rel="stylesheet" type="text/css" href="styles/cart_responsive.css">

</head>

<body>

<div class="super_container">
	
	<!-- Header -->
	<%@ include file="header.jsp" %>

	<!-- Cart -->

	<div class="cart_section">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 offset-lg-1">
					<div class="cart_container">
						<div class="cart_title">Mi tienda</div>
						
						
						<!-- lista vacia -->
						<div class="cart_items">
							<h4>Parece que no tiene ning�n producto en venta.</h4>
							<div class="contact_form_button">
								<button type="submit" class="button contact_submit_button">Mi producto a la venta!</button>
							</div>	
						</div>
						
						<!-- lista -->
						<div class="cart_items">
							<ul class="cart_list">
								<li class="cart_item clearfix" style="display:flex">
									<div class="cart_item_image" style="flex: 0.15"><img src="images/shopping_cart.jpg" alt=""></div>
									<div style="flex: 0.85" class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
										<div style="flex: 0.25" class="cart_item_name cart_info_col">
											<div class="cart_item_title">Producto</div>
											<div class="cart_item_text">Pantalon basico</div>
										</div>
										<div style="flex: 0.20" class="cart_item_color cart_info_col">
											<div class="cart_item_title">Colores</div>
											<div class="cart_item_text"><span style="background-color:#999999;"></span></div>
											<div class="cart_item_text"><span style="background-color:black;"></span></div>
											<div class="cart_item_text"><span style="background-color:brown;"></span></div>
										</div>
										<div style="flex: 0.1" class="cart_item_color cart_info_col">
											<div class="cart_item_title">Tallas</div>
											<div class="cart_item_text">M</div>
											<div class="cart_item_text">L</div>
										</div>
										<div style="flex: 0.1" class="cart_item_quantity cart_info_col">
											<div class="cart_item_title">Stock</div>
											
											<div class="cart_item_text">48</div>
										</div>	
										<div style="flex: 0.1" class="cart_item_price cart_info_col">
											<div class="cart_item_title">Precio</div>
											<div class="cart_item_text">19.99&euro;</div>
										</div>
										<div class="product_setting">
											<i class="fas fa-cog">
										</i>
										</div>
										<div class="product_trash">
											<i class="fas fa-trash">
										</i>
										</div>
										
									</div>
								</li>
								
								<li class="cart_item clearfix" style="display:flex">
									<div class="cart_item_image" style="flex: 0.15"><img src="images/shopping_cart.jpg" alt=""></div>
									<div style="flex: 0.85" class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
										<div style="flex: 0.25" class="cart_item_name cart_info_col">
											<div class="cart_item_title">Producto</div>
											<div class="cart_item_text">Otro producto</div>
										</div>
										<div style="flex: 0.20" class="cart_item_color cart_info_col">
											<div class="cart_item_title">Colores</div>
											<div class="cart_item_text"><span style="background-color:#000;"></span></div>
										</div>
										<div style="flex: 0.1" class="cart_item_color cart_info_col">
											<div class="cart_item_title">Tallas</div>
											<div class="cart_item_text">L</div>
										</div>
										<div style="flex: 0.1" class="cart_item_quantity cart_info_col">
											<div class="cart_item_title">Stock</div>
											
											<div class="cart_item_text">23</div>
										</div>	
										<div style="flex: 0.1" class="cart_item_price cart_info_col">
											<div class="cart_item_title">Precio</div>
											<div class="cart_item_text">19.99&euro;</div>
										</div>
										<div class="product_trash">
											<i class="fas fa-cog">
										</i>
										</div>
										<div class="product_trash">
											<i class="fas fa-trash">
										</i>
										</div>
									</div>
								</li>
							</ul>
						</div>
						
						<!-- Order Total -->
						<div class="order_total">
							<div class="order_total_content text-md-right">
								<div class="order_total_title">N� de productos:</div>
								<div class="order_total_amount"><b>2</b></div>
								<div class="order_total_amount">       </div>
								<div class="order_total_title">Valor total:</div>
								<div class="order_total_amount">850&euro;</div>
								
							</div>
						</div>

						<div class="cart_buttons">
							<button type="button" class="button cart_button_checkout">Guardar cambios</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Newsletter -->

	<div class="newsletter">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="newsletter_container d-flex flex-lg-row flex-column align-items-lg-center align-items-center justify-content-lg-start justify-content-center">
						<div class="newsletter_title_container">
							<div class="newsletter_icon"><img src="images/send.png" alt=""></div>
							<div class="newsletter_title">Suscr�bete a nuestra newsletter</div>
							<div class="newsletter_text"><p>...y obt�n un cup�n descuento del 20% para la 1� compra.</p></div>
						</div>
						<div class="newsletter_content clearfix">
							<form action="#" class="newsletter_form">
								<input type="email" class="newsletter_input" required="required" placeholder="Introduce tu correo electr�nico">
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
<script src="js/cart_custom.js"></script>
</body>

</html>