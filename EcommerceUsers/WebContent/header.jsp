<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="es.uc3m.ecommerce.model.*, java.util.*"
    pageEncoding="ISO-8859-1"%>    
<%@ page import="java.util.List,java.util.ArrayList,org.apache.commons.codec.binary.StringUtils,org.apache.commons.codec.binary.Base64;" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
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
						<div class="top_bar_contact_item"><div class="top_bar_icon"><img src="images/mail.png" alt=""></div><a href="mailto:info@ecommerce.com">info@ecommerce.com</a></div>
						<div class="top_bar_content ml-auto">
							<div class="top_bar_menu" style="visibility: hidden">
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
								<ul class="standard_dropdown top_bar_dropdown">
									<% if(session.getAttribute("user") != null) {%>									
									<li>
										<a href="loggingout.html">Cerrar Sesi&oacute;n</a>
									</li>
									<% } else { %>
									<li>
										<a href="#">Comenzar<i class="fas fa-chevron-down"></i></a>
										<ul>
											<li><a href="register.jsp">Registro</a></li>
											<li><a href="login.jsp">Iniciar Sesi&oacute;n</a></li>
										</ul>
									</li>
									<%} %>
								</ul>
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
						String searchQueryInput = request.getAttribute("searchQuery") != null ? (String) request.getAttribute("searchQuery") : "";
					%>
	
					<!-- Search -->
					<div class="col-lg-6 col-12 order-lg-2 order-3 text-lg-left text-right">
						<div class="header_search">
							<div class="header_search_content">
								<div class="header_search_form_container">
									<form action="search.html" class="header_search_form clearfix">
										<input type="search" name="searchQuery" value="<%=searchQueryInput%>" class="header_search_input" placeholder="Busca un producto...">
										<div class="custom_dropdown" >
											<div class="custom_dropdown_list">
												<span class="custom_dropdown_placeholder clc"><a href="advanced_search.jsp"> B&uacute;squeda avanzada</a></span>
												<ul class="custom_list clc" style="visibility: hidden" >
													<li><a class="clc" href="#">Todas las categor&iacuteas</a></li>	
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
									<div class="wishlist_text"><a href="<c:url value="wishlist.html">
									    <c:param name="action" value="0"/>
			     						<c:param name="productId" value="0"/>
			    	 					</c:url>">Wishlist</a>
			    	 				</div>
									<% if(session.getAttribute("user") == null) { %>
											<div class="wishlist_count"></div>
										<% } else if(session.getAttribute("wishlistTotal") != null){ %>
											<div class="wishlist_count"><%=session.getAttribute("wishlistTotal")%></div>
										<% } else { %>
											<div class="wishlist_count">0</div>
										<% } %>
								</div>
							</div>

							<!-- Cart -->
							<div class="cart">
								<div class="cart_container d-flex flex-row align-items-center justify-content-end">
									<div class="cart_icon">
										<img src="images/cart.png" alt="">
										<div class="cart_count"><span>#</span></div>
									</div>
									<div class="cart_content">
										<div class="cart_text"><a href="<c:url value="cart.html">
										    <c:param name="action" value="0"/>
				     						<c:param name="productId" value="0"/>
				    	 					</c:url>">Carrito</a></div>
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
								<div class="cat_menu_title d-flex flex-row align-items-center justify-content-start" >
									<div class="cat_burger"><span></span><span></span><span></span></div>
									<div class="cat_menu_text">Categor&iacuteas</div>
								</div>
								<%
									List<Category> categories = (request.getAttribute("categoryTree") != null) 
											? (List<Category>) request.getAttribute("categoryTree") 
											: (List<Category>) request.getServletContext().getAttribute("categoryTree");
								%>
								
								<ul class="cat_menu" >
									<% for (Category parent : categories){ %>
									<li class="hassubs">
										<a href="<c:url value="search.html">
					            			<c:param name="searchQuery" value="all"/>
							           	 	<c:param name="searchCategory" value="<%= Integer.toString(parent.getCategoryId())%>"/>
					            	 	</c:url>"><%= parent.getCategoryName()%><i class="fas fa-chevron-right"></i></a>
										<ul>
											<% for (Category categoryChild : parent.getCategories()) { %>
											<li>
												<a href="<c:url value="search.html">
							            			<c:param name="searchQuery" value="all"/>
									           	 	<c:param name="searchCategory" value="<%= Integer.toString(categoryChild.getCategoryId())%>"/>
							            	 	</c:url>"> <%= categoryChild.getCategoryName() %> <i class="fas fa-chevron-right"></i></a></li>
											
											<% } %>
										</ul>
									</li>
									<% } %>	
								</ul>
							</div>

							<!-- Main Nav Menu -->

							<div class="main_nav_menu ml-auto">
								<ul class="standard_dropdown main_nav_dropdown">
									<li><a href="./index.jsp">Home<i class="fas fa-chevron-down"></i></a></li>
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