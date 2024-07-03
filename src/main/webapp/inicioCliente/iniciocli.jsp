<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
<h1>carrito</h1>
         <input type="button" value="carrito" onclick="redirectTo('/carrito/carrito/carrito.jsp')"/>
        <h1>agregar/enviar saldo</h1>
         <input type="button" value="saldo" onclick="redirectTo('/carrito/cargasaldo/cargarsaldo.jsp')"/>
        <h1>historial de ventas</h1>
        <input type="button" value="historial" onclick="redirectTo('/carrito/historial/historial.jsp')"/>
        <p>
        <input type="button" style="color: red;" value="Volver al inicio" onclick="redirectTo('/carrito/index/login.jsp')"/>
        </p>
 <script>
        function redirectTo(destination) {
            window.location.href = destination;
        }
    </script>
</body>
</html>