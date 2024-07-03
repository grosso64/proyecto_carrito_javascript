<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Carrito de Compras</title>
</head>
<body>
    <h1>Seleccionar ID del producto de la lista y apretar "agregar al carrito"</h1>

    <h2>Lista de Artículos</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Stock</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="articulo" items="${listado}">
                <tr>
                    <td><c:out value="${articulo.id }" /></td>
                    <td><c:out value="${articulo.nombre }" /></td>
                    <td><c:out value="${articulo.descripcion }" /></td>
                    <td><c:out value="${articulo.precio }" /></td>
                    <td><c:out value="${articulo.stock }" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
      <a href="${pageContext.request.contextPath}/CarritoController?accion=verarticulos&id=${articulo.id}">Ver</a>
    

   <h2>Carrito</h2>
<table border="1">
    <thead>
        <tr>
            <th>Nª Pedido</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th>Precio</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${carritoList}">
            <tr>
                <td><c:out value="${item.id }" /></td>
                <td><c:out value="${item.nombre_producto }" /></td>
                <td><c:out value="${item.cantidad }" /></td>
                <td><c:out value="${item.precio }" /></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/CarritoController?accion=verpedido">Ver Pedido</a>
   
 

    <form action="${pageContext.request.contextPath}/CarritoController" method="post">
    <input type="hidden" name="accion" value="select" />
    <p>ID <input type="text" name="id" placeholder="Ingresar el ID del artículo" required/></p>
    <p>Cantidad <input type="text" name="cantidad" placeholder="Cantidad" required/></p>
    <p>
    <input type="submit" value="Seleccionar" />
    </p>
	</form>
	


<form action="${pageContext.request.contextPath}/CarritoController" method="post">
    <input type="hidden" name="accion" value="finalizarlista" />
   	  	
    <input type="submit" value="Finalizar Lista" />
    
     <div style="color: red;">
            <%= request.getAttribute("mensajeTotal") %>
    </div>	
      	
</form>


		
	<p>
        <input type="button" style="color: red;" value="Volver" onclick="redirectTo('/carrito/inicioCliente/iniciocli.jsp')"/>
   </p>
	 <script>
        function redirectTo(destination) {
            window.location.href = destination;
        }
    </script>
</body>
</html>