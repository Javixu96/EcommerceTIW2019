<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="es.uc3m.ecommerce.model.*, java.util.*"
    pageEncoding="ISO-8859-1"%>    
<%@ page import="java.util.List,java.util.ArrayList,org.apache.commons.codec.binary.StringUtils,org.apache.commons.codec.binary.Base64" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Shop</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="plugins/jquery-ui-1.12.1.custom/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="styles/shop_styles.css">
<link rel="stylesheet" type="text/css" href="styles/shop_responsive.css">

</head>

<body>
<div class="super_container">
	
	<!-- Header -->
	<%@ include file="header.jsp" %>
	
	<!-- Home -->
	<!-- Lo dejo comentado por si lo necesitamos para mensajes, pero de momento lo dejo así para solucionar un poco el scroll cuando buscas o filtras -->
<!-- 	<div class="home">
		<div class="home_background parallax-window" data-parallax="scroll" data-image-src="images/shop_background.jpg"></div>
		<div class="home_overlay"></div>
		<div class="home_content d-flex flex-column align-items-center justify-content-center">
			<h2 class="home_title">ï¿½Compra online las 24 horas del dï¿½a!</h2>
		</div>
	</div> -->

	<!-- Shop -->

	<div class="shop">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<!-- Shop Sidebar -->
					<%
						List<List<Category>> categoryTree = (request.getAttribute("categoryTree") != null) 
						? (List<List<Category>>) request.getAttribute("categoryTree") 
						: (List<List<Category>>) request.getServletContext().getAttribute("categoryTree");
						String searchQuery = request.getAttribute("searchQuery") != null ? (String) request.getAttribute("searchQuery") : "all";
					
					%>
					<div class="shop_sidebar">
						 <div class="sidebar_section">
							<div class="sidebar_title">Categorias</div>
							<ul class="sidebar_categories">
							<% for (List<Category> cList : categoryTree){ %>
								<li><a href="<c:url value="search.html">
				            			<c:param name="searchQuery" value="<%=searchQuery%>"/>
						           	 	<c:param name="searchCategory" value="<%= Integer.toString(cList.get(0).getCategory().getCategoryId())%>"/>
				            	 	</c:url>"> <%= cList.get(0).getCategory().getCategoryName()%></a></li>
									<ul class="sidebar_subcategories">
									<% for (Category categoryChild : cList) { %>
										<li><a href="<c:url value="search.html">
				            			<c:param name="searchQuery" value="<%=searchQuery%>"/>
						           	 	<c:param name="searchCategory" value="<%= Integer.toString(categoryChild.getCategoryId())%>"/>
				            	 	</c:url>" > <%= categoryChild.getCategoryName() %></a></li>
									<% } %>	
									</ul>
							<% } %>		
							</ul>
						</div> 
					</div>
				</div>

				<div class="col-lg-9">
					<!-- Shop Content -->
					<div class="shop_content">
						<div class="shop_bar clearfix">
						 <jsp:useBean id="allProducts" scope="request" type="java.util.List<es.uc3m.ecommerce.model.Product>"></jsp:useBean>
							<div class="shop_product_count"><span><%= allProducts.size() %></span> Productos encontrados</div>
							<div class="shop_sorting">
								<span>Ordenar por:</span>
								<ul>
									<li>
										<span class="sorting_text">Destacados<i class="fas fa-chevron-down"></i></span>
										<ul>
											<li class="shop_sorting_button" data-isotope-option='{ "sortBy": "original-order" }'>Destacados</li>
											<li class="shop_sorting_button" data-isotope-option='{ "sortBy": "name" }'>Nombre</li>
											<li class="shop_sorting_button"data-isotope-option='{ "sortBy": "price" }'>Precio</li>
										</ul>
									</li>
								</ul>
							</div>
						</div>
						
						<div class="product_grid">
							<div class="product_grid_border"></div>
							<% for(Product product: allProducts) { %>
								<div class="product_item is_new">
								<div class="product_border"></div>
									<div class="product_image d-flex flex-column align-items-center justify-content-center">
										<img style="height: 50px;" src="<% StringBuilder sb = new StringBuilder();
										sb.append("data:image/png;base64,");
										sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(product.getProductPicture(), false))); 
										out.print(sb.toString()); %>">
									</div>
									<div class="product_content">
									    <div class="product_price"><%=product.getPrice() %>&euro;</div>
										<div class="product_name"><%=product.getProductName() %></div>
										<form action="product.html" method="get">
											<input type="hidden" name="productId" id="productId" value="<%=product.getProductId()%>">
											<button type="submit" class="button cart_button">Ver producto</button>
										</form>						
									</div>
								<div class="product_fav" style="visibility: hidden;"><i class="fas fa-heart"></i></div>
								</div>		
							<% } %>
						</div>

						<!-- Shop Page Navigation -->

						<div class="shop_page_nav d-flex flex-row">
							<div class="page_prev d-flex flex-column align-items-center justify-content-center"><i class="fas fa-chevron-left"></i></div>
							<div class="page_nav align-items-center justify-content-center">
								<ul style="display: flex; flex-direction: row'">
									<li><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">...</a></li>
									<li><a href="#">21</a></li>
								</ul>
							</div>
							<div class="page_next d-flex flex-column align-items-center justify-content-center"><i class="fas fa-chevron-right"></i></div>
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
function seeProduct(p){  
	System.out.println("Selecting a product to diplay full screen");
	request.setAttribute("showProduct", p);
	location.href = "./product.html";
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
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="js/shop_custom.js"></script>
</body>

</html>