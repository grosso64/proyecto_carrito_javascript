package Controladores;

import java.io.IOException;

import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.usuarios;
import daos.UsuariosDAOdb;


@WebServlet("/enviarSaldo")
public class enviarSaldo extends HttpServlet {
 private static final long serialVersionUID = 1L;
 
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     String accion = request.getParameter("accion");
     accion = Optional.ofNullable(accion).orElse("index");

     switch (accion) {
         case "nuevoSaldo" -> getNuevoSaldo(request, response);
         default -> response.sendError(404);
     }
 }
 private void getNuevoSaldo(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     request.getRequestDispatcher("/saldo/nuevoSaldo.jsp").forward(request, response);
 }
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
	 String accion = request.getParameter("accion");
     accion = Optional.ofNullable(accion).orElse("index");

     switch (accion) {
         case "enviarSaldo" -> postEnviarSaldo(request, response);
         case "agregarSaldo" -> postAgregarSaldo(request, response);
         default -> response.sendError(404);
     }
 }
 private void postEnviarSaldo(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        HttpSession session = request.getSession();
	        usuarios usuarioValidado = (usuarios) session.getAttribute("usuario");

	        if (usuarioValidado == null) {
	            request.setAttribute("mensajeError", "Usuario no autenticado");
	            request.getRequestDispatcher("/cargasaldo/login.jsp").forward(request, response);
	            return;
	        }

	        String destinoUsuario = request.getParameter("destinoUsuario");

	        UsuariosDAOdb usuariosDAO = new UsuariosDAOdb(); 
	        Double monto = usuariosDAO.getDoubleParameter(request, "monto");

	        if (destinoUsuario == null || monto == null || monto <= 0) {
	            request.setAttribute("mensajeError", "monto no valido");
	            request.getRequestDispatcher("/cargasaldo/cargarsaldo.jsp").forward(request, response);
	            return;
	        }
	        if (usuarioValidado.getUsuario().equals(destinoUsuario)) {
	            request.setAttribute("mensajeError", "No sé puede enviar saldo a tu misma cuenta");
	            request.getRequestDispatcher("/cargasaldo/cargarsaldo.jsp").forward(request, response);
	            return;
	        }
	        if (!usuariosDAO.existeUsuario(destinoUsuario)) {
	            request.setAttribute("mensajeError", "El usuario destino no existe");
	            request.getRequestDispatcher("/cargasaldo/cargarsaldo.jsp").forward(request, response);
	            return;
	        }

	        usuarios usuarioDestino = usuariosDAO.obtenerUsuarioPorNombre(destinoUsuario);

	
	        if (usuarioValidado.getSaldo() < monto) {
	            request.setAttribute("mensajeError", "Saldo insuficiente");
	            request.getRequestDispatcher("/cargasaldo/cargarsaldo.jsp").forward(request, response);
	            return;
	        }

	        usuarioValidado.setSaldo(usuarioValidado.getSaldo() - monto);
	        usuarioDestino.setSaldo(usuarioDestino.getSaldo() + monto);

	        usuariosDAO.actualizarSaldo(usuarioValidado);
	        usuariosDAO.actualizarSaldo(usuarioDestino);

	        request.setAttribute("mensaje", "Saldo enviado correctamente a " + destinoUsuario);
	        request.getRequestDispatcher("/cargasaldo/cargarsaldo.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.getRequestDispatcher("/cargasaldo/cargarsaldo.jsp").forward(request, response);
	    }
	}

 private void postAgregarSaldo(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    usuarios usuarioValidado = (usuarios) session.getAttribute("usuario");
	    Double montoAgregar = null;

	    if (usuarioValidado != null) {
	        try {
	            montoAgregar = Double.parseDouble(request.getParameter("montoAgregar"));

	            if (montoAgregar > 0) {
	                usuarioValidado.setSaldo(usuarioValidado.getSaldo() + montoAgregar);

	                UsuariosDAOdb usuariosDAO = new UsuariosDAOdb();
	                usuariosDAO.actualizarSaldo(usuarioValidado);

	                request.setAttribute("mensaje", "Saldo agregado correctamente");
	                request.getRequestDispatcher("/cargasaldo/cargarsaldo.jsp").forward(request, response);
	                return;
	            } else {
	                request.setAttribute("mensajeError", "Monto no válido");
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            request.setAttribute("mensajeError", "El monto ingresado no es válido");
	        }
	    } else {
	        request.setAttribute("mensajeError", "Usuario no autenticado");
	    }

	    request.getRequestDispatcher("/cargasaldo/cargarsaldo.jsp").forward(request, response);
	}
}

