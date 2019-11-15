<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,java.util.ArrayList,es.uc3m.ecommerce.model.*" %>
<%@ page import="org.apache.commons.codec.binary.StringUtils" %>
<%@ page import="org.apache.commons.codec.binary.Base64;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="controlador?accion=catalogoSFF">Opcion guardar fotos en el sistema de ficheros</a>
<h3> Formulario para subir nueva foto</h3>
<form action="insertProduct.html" method="post" enctype="multipart/form-data">
	<!-- <input type="hidden" name="accion" value="insertarBBDD">  -->
    Nombre del producto:
    <input type="text" name="name">
    Descripcion corta:
    <input type="text" name="shortDes">
    Descripcion larga:
    <input type="text" name="longDes">
    Selecciona una imagen:
    <input type="file" name="fileToUpload" id="fileToUpload">
    <input type="submit" value="Upload Image" name="submit">
</form>
<h3> Formulario para hacer una busqueda por titulo </h3>
<form action="controlador" method="post" >
	<input type="hidden" name="accion" value="consultaBBDD">
    Que contenga en el titulo:
    <input type="text" name="palabra">
    <input type="submit" value="Buscar" name="submit">
</form>
</body>
</html>