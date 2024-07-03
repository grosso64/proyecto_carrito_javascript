<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="Modelos.usuarios" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Saldo</title>
</head>
<body>


    <h2>Saldo Actual: $<c:out value="${usuario.saldo}" /></h2>
   <form action="${pageContext.request.contextPath}/enviarSaldo" method="post">
       <input type="hidden" name="accion" value="enviarSaldo"/>
        <p>Enviar Saldo a otro Usuario</p>
        <label for="destinoUsuario">Usuario Destino:</label>
        <input type="text" name="destinoUsuario" required>

        <label for="monto">Monto a Enviar:</label>
        <input type="number" name="monto" required>

        <input type="submit" value="Enviar Saldo">
    </form>
 <form action="${pageContext.request.contextPath}/enviarSaldo" method="post">
         <input type="hidden" name="accion" value="agregarSaldo"/>
        <p>Agregar Saldo a tu Cuenta</p>
        <label for="montoAgregar">Monto a Agregar:</label>
        <input type="number" name="montoAgregar" required>

        <input type="submit" value="Agregar Saldo">
    </form>

    <div style="color: red;">
        <c:out value="${mensajeError}" />
    </div>
</body>
 
    <p>
        
        
        <input type="button" value="Volver" onclick="redirectTo('/carrito/inicioCliente/iniciocli.jsp')"/>
        
   </p>
	 <script>
        function redirectTo(destination) {
            window.location.href = destination;
        }
    </script>

</body>
</html>