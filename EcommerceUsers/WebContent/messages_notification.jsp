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
						<% if (request.getAttribute("listaSender")!=null && request.getAttribute("listaMensaje")!=null){ %>
						<% List<Appuser> senderList = (List<Appuser>)request.getAttribute("listaSender"); %>
						<% List<String> msgList = (List<String>)request.getAttribute("listaMensaje"); %>
						<%  int x=0;
							for (Appuser user: senderList){ 
						 %>
							<div class="cart_items">
								<ul class="cart_list" style="height:100px;">
								
									<li class="cart_item clearfix" style="display:flex;height:100px;padding-left:5px;">

										<div class="order_total_title" style ="padding-left:30px;"><b><% out.println(user.getUserName()); %></b></div>		
										<div style="flex: 1" class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
											<div style="flex: 0.9" class="cart_item_name cart_info_col">
												<div class="cart_item_title"><%out.println(msgList.get(x)); %></div>							
											</div>	
											<form action="./showMsg1to1.html" method="post">
											<% HttpSession ses = request.getSession();
											   ses.setAttribute("sender"+x,user);%>
											<input type="text" name="contadorMsg"  style="width:0.1px;height:0.1px;visibility:hidden"value="<%= x %>">																	
											<div class="product_setting" style="margin-top:0px;">
												<button type="submit" class="setting_button">
													<i class="fa fa-comment" aria-hidden="true">
													</i>
												</button>
											</div>
											</form>																													
										</div>	
									</li>
								</ul>
							</div>
						<%	x++;
							}
						}%>
						
						
						<!-- Order Total -->
						<div class="order_total">
							<div class="order_total_content text-md-right" style="text-align: left !important;padding-left:30px;">
								<div class="order_total_title"><b>No hay mas mensajes</b></div>
							</div>
						</div>
						<form action="./profile.jsp">
						<div class="cart_buttons">
							<button type="submit" class="button cart_button_checkout">Volver</button>
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