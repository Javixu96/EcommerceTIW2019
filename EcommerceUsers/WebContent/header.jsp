<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="es.uc3m.ecommerce.model.*, java.util.*"
    pageEncoding="ISO-8859-1"%>    
<%@ page import="java.util.List,java.util.ArrayList,org.apache.commons.codec.binary.StringUtils,org.apache.commons.codec.binary.Base64;" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="plugins/slick-1.8.0/slick.css">
<link rel="stylesheet" type="text/css" href="styles/header.css">
<script type="text/javascript">
		function logout(){   
			System.out.println("Script");

		}
</script>
</head>
<body>

<!-- Header -->
	
	<header class="header">
		<!-- Top Bar -->

		<div class="top_bar">
			<div class="container">
				<div class="row">
					<div class="col d-flex flex-row">
						<div class="top_bar_contact_item"><div class="top_bar_icon"><img src="images/phone.png" alt=""></div>+34 91 654 43 21</div>
						<div class="top_bar_contact_item"><div class="top_bar_icon"><img src="images/mail.png" alt=""></div><a href="mailto:fastsales@gmail.com">info@ecommerce.com</a></div>
						<div class="top_bar_content ml-auto">
							<div class="top_bar_menu">
								<ul class="standard_dropdown top_bar_dropdown">
									<li>
										<a href="#">Espa&ntilde;ol<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li><a href="#">Espa&ntilde;ol</a></li>
											<li><a href="#">Ingl&eacutes</a></li>
											<li><a href="#">Franc&eacute</a></li>
										</ul>
									</li>
									<li>
										<a href="#">EUR Euro<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li><a href="#">EUR Euro</a></li>
											<li><a href="#">$ US dollar</a></li>
										</ul>
									</li>
								</ul>
							</div>
							<div class="top_bar_user">
								<% if(session.getAttribute("user") != null) {
								%>
								<div class="user_icon"><img src="https://cdn1.iconfinder.com/data/icons/navigation-elements/512/user-login-man-human-body-mobile-person-512.png" alt=""></div>
								<div><a href="./loggingout.html">Logout</a></div>
								<% } else { %>
								<div><a href="./register.jsp">Register</a></div>
								<div><a href="./login.jsp">Sign in</a></div>
								<%} %>

							</div>
						</div>
					</div>
				</div>
			</div>		
		</div>

		<!-- Header Main -->

		<div class="header_main">
			<div class="container">
				<div class="row">

					<!-- Logo -->
					<div class="col-lg-3 col-sm-3 col-3 order-1">
						<div class="logo_container">
							<div class="logo"><a href="#">Ecommerce</a></div>
						</div>
					</div>
					<%
					    ServletContext servletContext = request.getServletContext();
						List<List<Category>> categories = (List<List<Category>>) servletContext.getAttribute("categoryTree");
					
					%>
	
					<!-- Search -->
					<div class="col-lg-6 col-12 order-lg-2 order-3 text-lg-left text-right">
						<div class="header_search">
							<div class="header_search_content">
								<div class="header_search_form_container">
									<form action="#" class="header_search_form clearfix">
										<input type="search" required="required" class="header_search_input" placeholder="Busca un producto...">
										<div class="custom_dropdown">
											<div class="custom_dropdown_list">
												<span class="custom_dropdown_placeholder clc">Todas las categor&iacuteas</span>
												<i class="fas fa-chevron-down"></i>
												<ul class="custom_list clc">
													<li><a class="clc" href="#">Todas las categor&iacuteas</a></li>	
												<% for (List<Category> cList : categories){ %>
													<li><a class="clc" href="#"> <%= cList.get(0).getCategory().getCategoryName()%></a></li>
												<% } %>	
												
													<!-- <li><a class="clc" href="#">Todas las categor&iacuteas</a></li>
													<li><a class="clc" href="#">Mujer</a></li>
													<li><a class="clc" href="#">Hombre</a></li>
													<li><a class="clc" href="#">Ni&ntilde;o</a></li> -->
												</ul>
											</div>
										</div>
										<button type="submit" class="header_search_button trans_300" value="Submit"><img src="images/search.png" alt=""></button>
									</form>
								</div>
							</div>
						</div>
					</div>

					<!-- Wishlist -->
					<div class="col-lg-3 col-9 order-lg-3 order-2 text-lg-left text-right">
						<div class="wishlist_cart d-flex flex-row align-items-center justify-content-end">
							<div class="wishlist d-flex flex-row align-items-center justify-content-end">
								<div class="wishlist_icon"><img src="images/heart.png" alt=""></div>
								<div class="wishlist_content">
									<div class="wishlist_text"><a href="./wishlist.html">Wishlist</a></div>
									<div class="wishlist_count">5</div>
								</div>
							</div>

							<!-- Cart -->
							<div class="cart">
								<div class="cart_container d-flex flex-row align-items-center justify-content-end">
									<div class="cart_icon">
										<img src="images/cart.png" alt="">
										<div class="cart_count"><span>1</span></div>
									</div>
									<div class="cart_content">
										<div class="cart_text"><a href="./cart.html">Carrito</a></div>
										<% if(session.getAttribute("user") == null) { %>
											<div class="cart_price"></div>
										<% } else if(session.getAttribute("cartTotal") != null){ %>
											<div class="cart_price"><%=session.getAttribute("cartTotal")%>&euro;</div>
										<% } else { %>
											<div class="cart_price">0.0&euro;</div>
										<% } %>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Main Navigation -->

		<nav class="main_nav">
			<div class="container">
				<div class="row">
					<div class="col">
						
						<div class="main_nav_content d-flex flex-row">

							<!-- Categories Menu -->

							<div class="cat_menu_container">
								<div class="cat_menu_title d-flex flex-row align-items-center justify-content-start">
									<div class="cat_burger"><span></span><span></span><span></span></div>
									<div class="cat_menu_text">Categor&iacuteas</div>
								</div>
								
								<ul class="cat_menu">
									<% for (List<Category> cList : categories){ %>
									<li class="hassubs">
										<a href="#"><%= cList.get(0).getCategory().getCategoryName()%><i class="fas fa-chevron-right"></i></a>
										<ul>
											<% for (Category categoryChild : cList) { %>
											<li><a href="#" > <%= categoryChild.getCategoryName() %> <i class="fas fa-chevron-right"></i></a></li>
											
											<% } %>
										</ul>
									</li>
									<% } %>	
									
								</ul>
							</div>

							<!-- Main Nav Menu -->

							<div class="main_nav_menu ml-auto">
								<ul class="standard_dropdown main_nav_dropdown">
									<li><a href="./index.html">Home<i class="fas fa-chevron-down"></i></a></li>
									<li><a href="./shop.html">Tienda<i class="fas fa-chevron-down"></i></a></li>
									<li><a href="./profile.html">Perfil<i class="fas fa-chevron-down"></i></a></li>
								</ul>
							</div>

							<!-- Menu Trigger -->

							<div class="menu_trigger_container ml-auto">
								<div class="menu_trigger d-flex flex-row align-items-center justify-content-end">
									<div class="menu_burger">
										<div class="menu_trigger_text">menu</div>
										<div class="cat_burger menu_burger_inner"><span></span><span></span><span></span></div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</nav>
	</header>

</body>
</html>