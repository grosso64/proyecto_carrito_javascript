package Controladores;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.articulos;
import Modelos.carrito;
import Modelos.usuarios;
import daos.UsuariosDAOdb;
import daos.articulosDAO;

import daos.carritoDAOdb;

import factories.daoFactory;

@WebServlet("/CarritoController")
public class CarritoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private articulosDAO articuloDAO;
  


    private List<carrito> carritoList = new ArrayList<>();
    
    double total;

    public CarritoController() {
        daoFactory dFactory = new daoFactory();
        this.articuloDAO = dFactory.getArticulosDAO();
    
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("index");

        switch (accion) {
            case "verarticulos" -> getVerarticulos(request, response);
            case "verpedido" -> getVerpedido(request, response);
            default -> response.sendError(404);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("index");

        switch (accion) {
            case "select" -> postSeleccionarArticulo(request, response);
            case "finalizarlista" -> finalizarLista(request, response);
            default -> response.sendError(404);
        }
    }

    private void getVerpedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
        request.setAttribute("carritoList", carritoList);
        request.getRequestDispatcher("/carrito/carrito.jsp").forward(request, response);
    }

    private void getVerarticulos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var listadoArticulos = articuloDAO.listAll();

        if (listadoArticulos != null) {
            System.out.println("Lista de artículos recuperada con éxito: " + listadoArticulos.size() + " elementos");
            for (articulos articulo : listadoArticulos) {
                System.out.println("ID: " + articulo.getId() + ", Nombre: " + articulo.getNombre() + ", Descripción: " + articulo.getDescripcion() + ", Precio: " + articulo.getPrecio());
            }
        } else {
            System.out.println("Error al recuperar la lista de artículos.");
        }

        request.setAttribute("listado", listadoArticulos);
        request.getRequestDispatcher("/carrito/carrito.jsp").forward(request, response);
        
        
    }
    private void finalizarLista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        System.out.println("Finalizando lista..."); 
        
      
        
        
        HttpSession session = request.getSession();
        usuarios usuarioValidado = (usuarios) session.getAttribute("usuario");
        long idUsuario = usuarioValidado.getId();
        UsuariosDAOdb usuariosDAO = new UsuariosDAOdb();
        usuarios usuario = usuariosDAO.obtenerUsuarioPorNombre(usuarioValidado.getUsuario());
        double saldoUsuario = usuario.getSaldo();
        
        for (carrito carrito : carritoList) {
        	
            System.out.println("ID: " + carrito.getId() + ", ID_PRODUCTO: " + carrito.getID_PRODUCTO()
                    + ", nombre_producto: " + carrito.getNombre_producto() + ", cantidad: " + carrito.getCantidad()
                    + ", precio: " + carrito.getPrecio());
            
            articulos articulo = articuloDAO.findById(carrito.getID_PRODUCTO());
            int stock = articulo.getStock();;
            int cantidad = carrito.getCantidad(); 
            int stockarestar= stock-cantidad;   
            articulo.setStock(stockarestar);
            total = total + carrito.getPrecio();
            if (saldoUsuario >= total) {
            
            usuario.setSaldo(saldoUsuario - total);
            usuariosDAO.actualizarSaldo(usuario);
            System.out.println("total: $" + total);
            
            daoFactory dFactory = new daoFactory();
            articulosDAO articuloDAO = dFactory.getArticulosDAO();
            articuloDAO.updatestock(articulo);
        
        
        carritoDAOdb carritoDAO = new carritoDAOdb();
        carritoDAO.guardarCarritoEnBD(carritoList, idUsuario);
        carritoList.clear();
        request.setAttribute("mensajeTotal",( "Total de la compra: $" + total));
        response.sendRedirect(request.getContextPath() + "/CarritoController?accion=verpedido");
        total=0;
        }else {
        	 request.setAttribute("mensajeTotal", "saldo insuficiente");
        	 carritoList.clear();
             request.getRequestDispatcher("/carrito/carrito.jsp").forward(request, response);
        }
    }
    }
 

	private void postSeleccionarArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	   HttpSession session = request.getSession();
           usuarios usuarioValidado = (usuarios) session.getAttribute("usuario");
           long idUsuario = usuarioValidado.getId();
        String sId = request.getParameter("id");
        String sCantidad = request.getParameter("cantidad");

        if ((sId != null && !sId.isEmpty()) || (sCantidad != null && !sCantidad.isEmpty())) {
            long id = (sId != null && !sId.isEmpty()) ? Long.parseLong(sId) : 0;
            int cantidad = (sCantidad != null && !sCantidad.isEmpty()) ? Integer.parseInt(sCantidad) : 1;

            articulos articulo = articuloDAO.findById(id);
            
           
            
            if (articulo != null) {
                if (cantidad <= articulo.getStock()) {
                	
                    carritoList.add(new carrito(idUsuario, articulo.getId(), articulo.getNombre(), cantidad, (articulo.getPrecio()*cantidad)));
                 
                } else {
                    System.out.println("Error: La cantidad seleccionada excede el stock disponible");
                    request.getRequestDispatcher("/carrito/carrito.jsp").forward(request, response);
                    
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/CarritoController?accion=verpedido");
    }
}
    