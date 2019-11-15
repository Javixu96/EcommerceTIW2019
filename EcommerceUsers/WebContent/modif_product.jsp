<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="es.uc3m.ecommerce.model.*"%>
<%@ page import="org.apache.commons.codec.binary.StringUtils" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>    
<!DOCTYPE html>
<html lang="en">
<head>
<title>Producto</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="styles/modif_product.css">
<link rel="stylesheet" type="text/css" href="styles/product_responsive.css">

</head>

<body>

<div class="super_container">
	
	<!-- Header -->
	<%@ include file="header.jsp" %>

	<!-- Single Product -->
	<form action="modify_product.html" enctype="multipart/form-data" method="post">
	<div class="single_product">
		<div class="container">
			<div class="row">				
				<%Product product=(Product)request.getAttribute("product"); %>
				<!-- Image -->
				<div class="col-lg-6 order-lg-2 order-1">
					<div class="image_selected"><img src="<% StringBuilder sb = new StringBuilder();
									sb.append("data:image/png;base64,");
									sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(product.getProductPicture(), false)));
									out.print(sb.toString()); %>">
					</div>
					<h4 style="margin-top:20px;" class="input_title">Imagen</h4>
					<input type="file" id="fileToUpLoad" name ="fileToUpLoad"  class="contact_form_name input_field inputImgUpLoad">
				</div>

				<!-- Description -->
				<div class="col-lg-6 order-3">
					<div>
							<div class="clearfix row_container catego" style="z-index: 1000;">
								
							
								<div class="row_item">
									<h4>Categoría</h4>
									<ul class="product_size">
										<li>
											<div class="size_mark_container" style="width:400px;left:-100px;text-align:left;">
												<% out.println(product.getCategoryBean().getCategory().getCategoryName()); %>											</div>	
										</li>
									</ul>
								</div>
								<div class="row_item" style="clear:left;">
									<h4>Subcategoría</h4>
									<ul class="product_subcateg">
										<li>
											
											<div class="size_mark_container"style="width:400px;left: 0px;">
											
												<input required="required" style="text-align:left;padding-left:30px;" type="text" name="subcategory" id="selected_subcateg"  value = "<% out.println(product.getCategoryBean().getCategoryName()); %>">
											
											</div>
											
											<div class="size_dropdown_button"><i class="fas fa-chevron-down"></i></div>
											
											<ul class="color_size">
											<%int cont=0; %>
												 <%
												 
                                                	Boolean checked;
                                            		for(Category c: product.getCategoryBean().getCategory().getCategories()){
                                            			cont++;
                                            			//checked = (c.getCategoryName().equals(productToModify.getCategoryBean().getCategoryName())) ? "checked" : "";
                                            			if (c.getCategoryName().equals(product.getCategoryBean().getCategoryName())){
                                            				checked = true;
                                            			} else {
                                            				checked = false;
                                            			}
												%>
												<li><input type="button"  class="size_mark" id=<%="subcateg"+cont%> value="<%=c.getCategoryName()%>" <%= (checked)?"checked":"" %> ></li>										
												<%
                                            		}
												%>
											</ul>
										</li>
									</ul>
								</div>		
													
							</div>
			
							
							<div class="product_name">
							<h4>Nombre de producto</h4>
							<input name="name" type="text" class="profile_form input_field" placeholder="Nombre de producto" required="required" value =<% out.println(product.getProductName()); %>>
							</div>
						
							<div class="product_price">
							<h4>Descripci&oacute;n</h4>
							</div>
							<div class="contact_form_inputs d-flex flex-md-row flex-column justify-content-between align-items-between">
								<textarea name="shortDesc" style="padding-bottom: 10px;padding-top:10px" class="input_field" rows="2" placeholder="Introduce la descripción del producto"><% out.println(product.getShortDesc()); %></textarea>
							</div>
						
							<div class="product_price">
							<h4>Descripci&oacute;n detallada</h4>
							</div>
							<div class="contact_form_inputs d-flex flex-md-row flex-column justify-content-between align-items-between">
								<textarea name="longDesc" style="padding-bottom: 10px;padding-top:10px" class="input_field" rows="2" placeholder="Introduce la descripción detallada del producto"><% out.println(product.getLongDesc()); %></textarea>
							</div>
						
							<div class="product_price">
								<h5>Precio (&euro;)</h5>
								<input name="price" type="text" style="margin-top: 5px;" class="profile_form input_field" placeholder="Precio" required="required"value=<% out.println(product.getPrice()); %>>
							</div>
							
							<div class="product_stock">
								<h5 style="margin-top: 40px;">Stock</h5>
								<div class="product_quantity">
									<span>Cantidad: </span>
									<input required="required" name = "stock" id="quantity_input" type="text" pattern="[0-999]*" value=<% out.println(product.getStock()); %>>
									<div class="quantity_buttons">
										<div id="quantity_inc_button" class="quantity_inc quantity_control">
											<i class="fas fa-chevron-up"></i>
										</div>
										<div id="quantity_dec_button" class="quantity_dec quantity_control">
											<i class="fas fa-chevron-down"></i>
										</div>
									</div>
								</div>
							</div>
						
							<% HttpSession mySession = request.getSession(true); %>
							<% mySession.setAttribute("productInModify",product); %>
							<div style="clear:left; margin-top:100px;" class="button_container">
								<button type="submit" class="button cart_button">Guardar cambios</button>
							</div>	
					</div>
				</div>

			</div>
		</div>
	</div>
	</form>

	<!-- Recently Viewed -->

	<div class="viewed">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="viewed_title_container">
						<h3 class="viewed_title">Vistos recientemente</h3>
						<div class="viewed_nav_container">
							<div class="viewed_nav viewed_prev"><i class="fas fa-chevron-left"></i></div>
							<div class="viewed_nav viewed_next"><i class="fas fa-chevron-right"></i></div>
						</div>
					</div>

					<div class="viewed_slider_container">
						
						<!-- Recently Viewed Slider -->

						<div class="owl-carousel owl-theme viewed_slider">
							
							<!-- Recently Viewed Item -->
							<div class="owl-item">
								<div class="viewed_item discount d-flex flex-column align-items-center justify-content-center text-center">
									<div class="viewed_image"><img src="images/view_1.jpg" alt=""></div>
									<div class="viewed_content text-center">
										<div class="viewed_price">$225<span>$300</span></div>
										<div class="viewed_name"><a href="#">Beoplay H7</a></div>
									</div>
									<ul class="item_marks">
										<li class="item_mark item_discount">-25%</li>
										<li class="item_mark item_new">new</li>
									</ul>
								</div>
							</div>

							<!-- Recently Viewed Item -->
							<div class="owl-item">
								<div class="viewed_item d-flex flex-column align-items-center justify-content-center text-center">
									<div class="viewed_image"><img src="images/view_2.jpg" alt=""></div>
									<div class="viewed_content text-center">
										<div class="viewed_price">$379</div>
										<div class="viewed_name"><a href="#">LUNA Smartphone</a></div>
									</div>
									<ul class="item_marks">
										<li class="item_mark item_discount">-25%</li>
										<li class="item_mark item_new">new</li>
									</ul>
								</div>
							</div>

							<!-- Recently Viewed Item -->
							<div class="owl-item">
								<div class="viewed_item d-flex flex-column align-items-center justify-content-center text-center">
									<div class="viewed_image"><img src="images/view_3.jpg" alt=""></div>
									<div class="viewed_content text-center">
										<div class="viewed_price">$225</div>
										<div class="viewed_name"><a href="#">Samsung J730F...</a></div>
									</div>
									<ul class="item_marks">
										<li class="item_mark item_discount">-25%</li>
										<li class="item_mark item_new">new</li>
									</ul>
								</div>
							</div>

							<!-- Recently Viewed Item -->
							<div class="owl-item">
								<div class="viewed_item is_new d-flex flex-column align-items-center justify-content-center text-center">
									<div class="viewed_image"><img src="images/view_4.jpg" alt=""></div>
									<div class="viewed_content text-center">
										<div class="viewed_price">$379</div>
										<div class="viewed_name"><a href="#">Huawei MediaPad...</a></div>
									</div>
									<ul class="item_marks">
										<li class="item_mark item_discount">-25%</li>
										<li class="item_mark item_new">new</li>
									</ul>
								</div>
							</div>

							<!-- Recently Viewed Item -->
							<div class="owl-item">
								<div class="viewed_item discount d-flex flex-column align-items-center justify-content-center text-center">
									<div class="viewed_image"><img src="images/view_5.jpg" alt=""></div>
									<div class="viewed_content text-center">
										<div class="viewed_price">$225<span>$300</span></div>
										<div class="viewed_name"><a href="#">Sony PS4 Slim</a></div>
									</div>
									<ul class="item_marks">
										<li class="item_mark item_discount">-25%</li>
										<li class="item_mark item_new">new</li>
									</ul>
								</div>
							</div>

							<!-- Recently Viewed Item -->
							<div class="owl-item">
								<div class="viewed_item d-flex flex-column align-items-center justify-content-center text-center">
									<div class="viewed_image"><img src="images/view_6.jpg" alt=""></div>
									<div class="viewed_content text-center">
										<div class="viewed_price">$375</div>
										<div class="viewed_name"><a href="#">Speedlink...</a></div>
									</div>
									<ul class="item_marks">
										<li class="item_mark item_discount">-25%</li>
										<li class="item_mark item_new">new</li>
									</ul>
								</div>
							</div>
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
							<div class="newsletter_title">Sign up for Newsletter</div>
							<div class="newsletter_text"><p>...and receive %20 coupon for first shopping.</p></div>
						</div>
						<div class="newsletter_content clearfix">
							<form action="#" class="newsletter_form">
								<input type="email" class="newsletter_input" required="required" placeholder="Enter your email address">
								<button class="newsletter_button">Subscribe</button>
							</form>
							<div class="newsletter_unsubscribe_link"><a href="#">unsubscribe</a></div>
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
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="js/product_custom.js"></script>
</body>

</html>