<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="es.uc3m.ecommerce.model.Product, java.util.*"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List,java.util.ArrayList,org.apache.commons.codec.binary.StringUtils,org.apache.commons.codec.binary.Base64" %>
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
						<div class="cart_title">Mi notificaciones</div>
						
						<!--
						<div class="cart_items" style="margin-top:20px;">				
							<div class="contact_form_button ">
								<a href="add_product.jsp"><button class="button contact_submit_button">Añadir producto</button></a>
							</div>	
						</div>
						-->
						
						<!-- lista -->
						<div class="cart_items">
							<ul class="cart_list">
							<% int counter=0; %>
							<% List<Product> productList = (List<Product>)request.getAttribute("allProducts"); %>
							<% for (Product product: productList){ %>
								<li class="cart_item clearfix" style="display:flex">
									<div class="cart_item_image" style="flex: 0.15"><img  src="<% StringBuilder sb = new StringBuilder();
									sb.append("data:image/png;base64,");
									sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(product.getProductPicture(), false)));
									out.print(sb.toString()); %>">
									</div>
									
									
									<div style="flex: 0.85" class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
										<div style="flex: 0.1" class="cart_item_name cart_info_col">
											<div class="cart_item_title">User ID</div>
											<div class="cart_item_text"><% out.println(product.getProductId()); %></div>
										</div>
										
										<div style="flex: 0.3" class="cart_item_name cart_info_col">
											<div class="cart_item_title">Producto</div>
											<div class="cart_item_text"><% out.println(product.getProductName()); %></div>
										</div>
										
										<div style="flex: 0.3" class="cart_item_name cart_info_col">
											<div class="cart_item_title">Categoría</div>
											<div class="cart_item_text"><%out.println(product.getCategoryBean().getCategoryName());%></div>
										</div>
										
										<div style="flex: 0.15" class="cart_item_quantity cart_info_col">
											<div class="cart_item_title">Stock</div>
											
											<div class="cart_item_text"><% out.println(product.getStock()); %></div>
										</div>	
										<div style="flex: 0.15" class="cart_item_price cart_info_col">
											<div class="cart_item_title">Precio</div>
											<div class="cart_item_text"><% out.println(product.getPrice()); %>&euro;</div>
										</div>
										
										<form action="./modif_product.html" method="post">
										<% HttpSession mySession = request.getSession(true); %>
										<% mySession.setAttribute("productToModify"+counter,product); %>	
										<input type="text" name="contadorModi"  style="width:0.1px;height:0.1px;visibility:hidden"value="<%=counter%>">							
											<div class="product_setting">
											 <button type="submit" class="setting_button">
												<i class="fas fa-cog">
												</i>
											</button>
											</div>
										</form>
										
										<form action="./deleteProduct.html" method="post">
										<% mySession.setAttribute("productToDelete"+counter,product); %>
										<input type="text" name="contadorBorr"  style="width:0.1px;height:0.1px;visibility:hidden"value="<%=counter%>">																	
										<div class="product_trash">
										 	<button type="submit" class="setting_button">
												<i class="fas fa-trash">
												</i>
											</button>
										</div>
										</form>
										<% counter++; %>	
										
									</div>
									<% } %>
								</li>
							</ul>
						</div>
						
						<!-- Order Total -->
						<div class="order_total">
							<div class="order_total_content text-md-right">
								<div class="order_total_title">Valor total:</div>
								<div class="order_total_amount">850&euro;</div>
							</div>
						</div>
						<form action="./profile.jsp">
						<div class="cart_buttons">
							<button type="submit" class="button cart_button_checkout">Guardar cambios</button>
						</div>
						</form>
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
<script src="js/cart_custom.js"></script>
</body>

</html>