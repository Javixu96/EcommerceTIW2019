<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="es.uc3m.ecommerce.model.Appuser, java.util.*"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List,java.util.ArrayList,org.apache.commons.codec.binary.StringUtils,org.apache.commons.codec.binary.Base64" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Mensajes</title>
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

	<div class="cart_section" style="padding-bottom:70px;padding-top:50px;">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 offset-lg-1">
					<div class="cart_container">			
						
						<!-- lista -->
						<div class="cart_items">
							<ul class="cart_list" style="height:100px;">
								<li class="cart_item clearfix" style="display:flex;height:100px;">
									<div class="cart_item_image" style="flex: 0.15;height:50%;">
									<form action="./readBrowserMessage.html" method="post">																
											<div class="product_setting" style="margin-top:20px;">
												<button type="submit" class="setting_button">
													<i class="fa fa-arrow-left" aria-hidden="true">
													</i>
												</button>
											</div>
											</form>		
									</div>									
									
									<div style="flex: 0.85" class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
										<%  if(session.getAttribute("sender")!=null){
											Appuser sender= (Appuser)session.getAttribute("sender");
										%>
										<div style="flex: 0.3" class="cart_item_name cart_info_col">
											<div class="cart_item_title" style="font-size:25px;"><% out.println(sender.getUserName()+" "+sender.getUserSurnames()); %></div>
											<div class="cart_item_text"></div>
										</div>																														
									</div>
										<%} %>
								</li>
							</ul>
						</div>
						
						<ul class="cart_list" style="height:400px;">
						
						<% if(request.getAttribute("listaMensaje")!=null){
						   List<String> msgList = (List<String>)request.getAttribute("listaMensaje"); 
						   if(!msgList.isEmpty()){
							  System.out.println("Aaaaaaaaaaaaaaaaaaaa");
						   for (String msg: msgList){   %>
						
								<li class="clearfix" style="display:flex;border:1px solid grey;border-radius:5px;margin-top:20px;height:8%;width:70%;margin-left:40px;padding-left:15px;">							
								<% out.println(msg); %>
								</li>
					
							<%} 
						   }
						} %>
						
						</ul>
						
						<!-- Order Total -->
						<form action="./sendMessageToSeller.html">
						
							<input name="message" class="order_total" style="padding-left:20px;" required=required>

							<div class="cart_buttons">
								<button type="submit" class="button cart_button_checkout">Enviar mensaje</button>
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