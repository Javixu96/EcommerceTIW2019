<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="es.uc3m.ecommerce.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Cart</title>
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
						<div class="cart_title">Mis pedidos</div>
						
						<jsp:useBean id="productPurchased" scope="request" type="java.util.List<es.uc3m.ecommerce.model.Product>"/>
						<jsp:useBean id="purchase" scope="request" type="java.util.List<es.uc3m.ecommerce.model.Purchas>"/>
						
						<div class="cart_items">
							<ul class="cart_list">
								<% int i; %>
								<% for(i = 0; i<productPurchased.size(); i++) { %>
								<li class="cart_item clearfix" style="display:flex">
									<div class="cart_item_image" style="flex: 0.15"><img  src="<% StringBuilder sb = new StringBuilder();
									sb.append("data:image/png;base64,");
									sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(productPurchased.get(i).getProductPicture(), false)));
									out.print(sb.toString()); %>">
									</div>
									
									
									<div style="flex: 0.85" class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
										<div style="flex: 0.1" class="cart_item_name cart_info_col">
											<div class="cart_item_title">ID</div>
											<div class="cart_item_text"><% out.println(productPurchased.get(i).getProductId()); %></div>
										</div>
										
										<div style="flex: 0.3" class="cart_item_name cart_info_col">
											<div class="cart_item_title">Producto</div>
											<div class="cart_item_text"><%= productPurchased.get(i).getProductName() %></div>
										</div>
					
										<div style="flex: 0.15" class="cart_item_price cart_info_col">
											<div class="cart_item_title">Precio</div>
											<div class="cart_item_text"><%= productPurchased.get(i).getPrice() %>&euro;</div>
										</div>
										
										<div style="flex: 0.15" class="cart_item_price cart_info_col">
											<div class="cart_item_title">Cantidad</div>
											<div class="cart_item_text"><%= purchase.get(i).getProductQuantity() %></div>
										</div>
										
										<div style="flex: 0.15" class="cart_item_price cart_info_col">
											<div class="cart_item_title">Subtotal</div>
											<div class="cart_item_text">
											<% 
													int quantity = purchase.get(i).getProductQuantity();
													int price = productPurchased.get(i).getPrice(); 
													out.println(price*quantity);
												%>
												&euro;
											</div>
										</div>
									</div>
									<% } %>
							</ul>
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