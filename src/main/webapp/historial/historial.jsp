<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Historial</title>
</head>
<body>

	<h2>Historial</h2>
    <table border="1">
        <thead>
            <tr>
                <th>n°pedido</th>
                <th>id_usuario</th>
                <th>ID_PRODUCTO</th>
                <th>nombre_producto</th>
                <th>cantidad</th>
                <th>precio</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="carrito" items="${carritoList}">
                <tr>
                    <td><c:out value="${carrito.id }" /></td>
                    <td><c:out value="${carrito.idUsuario }" /></td>
                    <td><c:out value="${carrito.ID_PRODUCTO }" /></td>
                    <td><c:out value="${carrito.nombre_producto }" /></td>
                    <td><c:out value="${carrito.cantidad }" /></td>
                    <td><c:out value="${carrito.precio }" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
      <a href="${pageContext.request.contextPath}/historialController?accion=verhistorial&id=${item.ID}">Ver</a>
    
    
    <p>
        
        
        <input type="button" value="Volver" style="color: red;" onclick="redirectTo('/carrito/inicioCliente/iniciocli.jsp')"/>
        
   </p>
	 <script>
        function redirectTo(destination) {
            window.location.href = destination;
        }
    </script>
</body>
</html>