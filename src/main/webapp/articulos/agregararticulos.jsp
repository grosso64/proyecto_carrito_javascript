<%@page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>


    <h1>Agregar Artículo</h1>
  <form action="${pageContext.request.contextPath}/abmArtController" method="post">

        <input type="hidden" name="accion" value="insert"/>
        <p>Nombre <input type="text" name="nombre" /></p>
        <p>Descripción <input type="text" name="descripcion" /></p>
        <p>Precio <input type="text" name="precio" /></p>
         <p>stock <input type="text" name="stock" /></p>
        <input type="submit" value="Agregar Artículo"/>
    </form>
    
<h1>Editar Artículo</h1>
<form action="${pageContext.request.contextPath}/abmArtController" method="post">

    <input type="hidden" name="accion" value="update"/>
    <p>ID  <input type="text" name="id" placeholder="ingresa el id del articulo " /></p>
    <p>Nombre <input type="text" name="nombre" /></p>
    <p>Descripción <input type="text" name="descripcion" /></p>
    <p>Precio <input type="text" name="precio" /></p>
    <p>stock <input type="text" name="stock" /></p>
    <input type="submit" value="Editar Artículo"/>
</form>

  
    
    <h1>Eliminar Artículo</h1>
<form action="${pageContext.request.contextPath}/abmArtController" method="post">

    <p>ID <input type="text" name="id" placeholder="ingresa el id del articulo "/></p>
    <input type="hidden" name="accion" value="delete"/>
    <input type="submit" value="Eliminar Artículo"/>
</form>
<h1>Editar stock</h1>
<form action="${pageContext.request.contextPath}/abmArtController" method="post">
  
    <input type="hidden" name="accion" value="updatestock"/>
    <p>ID <input type="text" name="id" placeholder="ingresa el id del articulo "/></p>
    <p>stock <input type="text" name="stock" /></p>
    <input type="submit" value="Editar stock"/>
</form>


   <h1>lista de los articulos</h1>
    <table border="1">
        <thead>
            <tr>
                <th>id</th>
                
                <th>nombre</th>
                <th>descripcion</th>
                <th>precio</th>
                <th>stock</th>
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


    <a href="${pageContext.request.contextPath}/abmArtController?accion=agregararticulos&id=${articulo.id}">Ver</a>
    
 <p>
        
        
        <input type="button" value="Volver" style="color: red;" onclick="redirectTo('/carrito/index/login.jsp')"/>
        
   </p>
	<script>
        function redirectTo(destination) {
            window.location.href = destination;
        }
    </script>
    
</body>
</html>