<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <form action="${pageContext.request.contextPath}/logincontroller" method="post">
        <p>Usuario</p>
        <input placeholder="ingresa su usuario" type="text" name="usuario" required>

        <p>Contraseña</p>
        <input placeholder="ingresa su clave" type="password" name="clave" required>
        
        
        <p>

        
        <input type="submit" name="accion" value="Iniciar Sesión">
        
        </p>
        

    <div style="color: red;">
            <%= request.getAttribute("mensajeError") %>
        </div>
    </form>
</html>