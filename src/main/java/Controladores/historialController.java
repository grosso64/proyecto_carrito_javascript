package Controladores;

import java.io.IOException;

import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.carrito;
import Modelos.usuarios;
import daos.carritoDAO;
import factories.daoFactory;

@WebServlet("/historialController")
public class historialController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private carritoDAO CarritoDAO;

    public historialController() {
        daoFactory dFactory = new daoFactory();
        this.CarritoDAO = dFactory.getCarritoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("index");

        switch (accion) {
            case "verhistorial" -> getVerhistorial(request, response);

            default -> response.sendError(404);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void getVerhistorial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        usuarios usuarioAutenticado = (usuarios) session.getAttribute("usuario");

        if (usuarioAutenticado != null) {
            long userId = usuarioAutenticado.getId(); 

            var historialCarrito = CarritoDAO.listUserId(userId);

            if (historialCarrito != null) {
                for (carrito carrito : historialCarrito) {
                    System.out.println("ID: " + carrito.getId() + ", id_usuario: " + carrito.getIdUsuario()
                            + ", ID_PRODUCTO: " + carrito.getID_PRODUCTO() + ", nombre_producto: "
                            + carrito.getNombre_producto() + ", cantidad: " + carrito.getCantidad() + ", precio: "
                            + carrito.getPrecio());
                }
            } else {
                System.out.println("Error al recuperar el historial del usuario con ID: " + userId);
            }

            request.setAttribute("carritoList", historialCarrito);
            request.getRequestDispatcher("/historial/historial.jsp").forward(request, response);
        } else {
          
            response.sendError(401, "Usuario no autenticado");
        }
    }
}
