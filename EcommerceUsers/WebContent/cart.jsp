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
						<div class="cart_title">Carrito</div>
						<% if(session.getAttribute("cartList") == null || ((List) session.getAttribute("cartList")).size() == 0) { %>
							<span style="color: black;"> No tienes productos en el carro </span>
						<% } else { %>
						<div class="cart_items">
							<ul class="cart_list" id="cart_list">
								<jsp:useBean id="cartList" type="java.util.List<es.uc3m.ecommerce.model.Product>" scope="session" />
								<jsp:useBean id="cartQuantities" type="java.util.List<Integer>" scope="session" />
								<% int i = 0; %>
								<% for(i = 0; i < cartList.size(); i ++) { %>
									<% session.setAttribute("item", i); %>
									<li id="cart_item_<%=i%>" class="cart_item clearfix" style="display:flex">
										<div class="cart_item_image" style="flex: 0.15"><img src="images/shopping_cart.jpg" alt=""></div>
										<div style="flex: 0.85" class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
												<div style="flex: 0.25" class="cart_item_name cart_info_col">
													<div class="cart_item_title">Producto</div>
													<div class="cart_item_text"><%=cartList.get(i).getProductName() %></div>
												</div>
												<div style="flex: 0.1" class="cart_item_quantity cart_info_col">
													<div class="cart_item_title">Cantidad</div>	
													<div class="cart_item_text">
														<input readonly type="text" class="cart_item_text" id="quantity_input_<%=i%>" name="quantity_input_<%=i%>" value="<%=cartQuantities.get(i)%>"></input>									
														<a href="<c:url value="edit_cart.html">
															<c:param name="productId" value="<%=String.valueOf(cartList.get(i).getProductId())%>" />
						           	 						<c:param name="action" value="3"/>
						           	 						<c:param name="operation" value="1"/>
					            	 						</c:url>" class="btn btn-rounded btn-info">+</a>
				            	 						<a href="<c:url value="edit_cart.html">
															<c:param name="productId" value="<%=String.valueOf(cartList.get(i).getProductId())%>" />
						           	 						<c:param name="action" value="3"/>
						           	 						<c:param name="operation" value="0"/>
					            	 						</c:url>" class="btn btn-rounded btn-info">-</a>
					            	 				</div>
												</div>
											
											<!--  
											<script>
											document.getElementById("quantity_input_" + ${cartList}.indexOf(${cartList}.get(i))).onchange = function() {myFunction()};
																			
											function myFunction() {
												
											var quantity = document.getElementById("quantity_input_" + ${cartList}.indexOf(${cartList}.get(i)));
											var total = quantity.value;
											${cartQuantities}.set(${cartList}.indexOf(${cartList}.get(i)), total);
											session.setAttribute("cartQuantities", cartQuantities);
											}
											</script>
											-->	
											<div style="flex: 0.1" class="cart_item_price cart_info_col">
												<div class="cart_item_title">Precio</div>
												<div class="cart_item_text"><%=cartList.get(i).getPrice()%>&euro;</div>
											</div>
											<div style="flex: 0.1" class="cart_item_total cart_info_col">	
												<div class="cart_item_text">
													<div class="cart_item_title"></div>	
													<div class="cart_item_text">									
														<a href="<c:url value="remove_from_cart.html">
															<c:param name="productId" value="<%=String.valueOf(cartList.get(i).getProductId())%>" />
						           	 						<c:param name="action" value="1"/>
					            	 						</c:url>" class="btn btn-rounded btn-info">Eliminar</a>
					            	 				</div>
												</div>
											</div>
										</div>
									</li>
								<% } %>			
							</ul>
						</div>
					</div>
						<!-- Order Total -->
						
						<div class="order_total">
							<div class="order_total_content text-md-right">
								<div id="div_cart_total" class="order_total_title">Total: </div>
								<div id="order_total_amount" class="order_total_amount"><%=session.getAttribute("cartTotal") %>&euro;</div>
							</div>
						</div> 
						<form action="checkout.jsp">
						<div class="cart_buttons">
							<button type="submit" class="button cart_button_checkout"> Confirmar compra</button>	
						</div>
						</form>						
					<% } %>		
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
<script type="text/javascript">
submitForms = function(){
	var f;
	for(f = 0; f < cartList.size(); f ++){
		document.getElementById("checkout_form_" + f).submit();
	}  
}
</script>
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