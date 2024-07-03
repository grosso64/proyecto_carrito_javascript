package Controladores;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.admin;
import Modelos.articulos;
import Modelos.usuarios;
import daos.articulosDAO;

import factories.daoFactory;


@WebServlet("/abmArtController")
public class abmArtController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private articulosDAO articuloDAO;
    

    public abmArtController() {
        daoFactory dFactory = new daoFactory();
        this.articuloDAO = dFactory.getArticulosDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        HttpSession session = request.getSession();
        usuarios usuarioValidado = (usuarios) session.getAttribute("usuario");
        admin adminValidado = (admin) session.getAttribute("admin");

        if (usuarioValidado == null && adminValidado == null) {
            // Si no hay usuario ni admin autenticado, redirige a la página de login
            response.sendRedirect(request.getContextPath() + "/index/login.jsp");
            return;
        }
        
        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("index");

        switch (accion) {
            case "nuevo" -> getNuevo(request, response);
            case "editar" -> getEditar(request, response);
            case "eliminar" -> getEliminar(request, response);
            case "agregararticulos" -> getAgregararticulos(request, response);
            default -> response.sendError(404);
        }
       
    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("index");

        switch (accion) {
            case "insert" -> postNuevo(request, response);
            case "delete" -> postDelete(request, response);
            case "update" -> postUpdate(request, response);
            case "updatestock" -> postUpdatestock(request, response);
            default -> response.sendError(404);
        }
        
    }

    private void getNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/articulos/nuevo.jsp").forward(request, response);
    }

    private void getEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 String sId = request.getParameter("id");
         if (sId != null && !sId.isEmpty()) {
             long id = Long.parseLong(sId);
             articulos articulo = articuloDAO.findById(id);
             if (articulo != null) {
                 request.setAttribute("articulo", articulo);
                 request.getRequestDispatcher("/articulos/editar.jsp").forward(request, response);
             } else {
                 response.sendError(HttpServletResponse.SC_NOT_FOUND, "Artículo no encontrado");
             }
         } else {
             response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de artículo no proporcionado");
         }
         }

    private void getEliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/articulos/eliminar.jsp").forward(request, response);
    }
    
    
    private void getAgregararticulos(HttpServletRequest request, HttpServletResponse response)
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
        request.getRequestDispatcher("/articulos/agregararticulos.jsp").forward(request, response);
    }
    
    
    
    
    private void postNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String precioParam = request.getParameter("precio");
        double dPrecio = Double.parseDouble(request.getParameter("precio"));
        String stockParam = request.getParameter("stock");
        int iStock = Integer.parseInt(request.getParameter("stock"));
        double precio = 0.0;  
        int stock = 0;  

       
        if ( precioParam != null &&  !precioParam.trim().isEmpty()) {
        	
	        if(dPrecio>=1) {	
	            try {
	                precio = Double.parseDouble(precioParam.trim());
	            } catch (NumberFormatException e) {
	                
	                e.printStackTrace();
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de precio no válido");
	                return;  
	            }
	        }    
        
        }
            
       
        if (precioParam != null &&  !precioParam.trim().isEmpty()) {
        	
        	if(iStock>=1) {
	            try {
	                stock = Integer.parseInt(stockParam.trim());
	            } catch (NumberFormatException e) {
	         
	                e.printStackTrace();
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de stock no válido");
	                return;  
	            }
        	}
        }
        
        articulos articulo = new articulos(nombre, descripcion, precio, stock);

      
        daoFactory dFactory = new daoFactory();
        articulosDAO articuloDAO = dFactory.getArticulosDAO();
        articuloDAO.insert(articulo);

       
        response.sendRedirect(request.getContextPath() + "/articulos/agregararticulos.jsp");
    }

    private void postDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 long id = Long.parseLong(request.getParameter("id"));

    	
    	    daoFactory dFactory = new daoFactory();
    	    articulosDAO articuloDAO = dFactory.getArticulosDAO();
    	    articuloDAO.delete(id);

    	   
    	    response.sendRedirect(request.getContextPath() + "/articulos/agregararticulos.jsp");
    }

    private void postUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        long id = Long.parseLong(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock")); 
        
        if(id>=1 && precio>=1 && stock >=1) {
        	articulos articulo = new articulos();
        	articulo.setId(id);
        	articulo.setNombre(nombre);
        	articulo.setDescripcion(descripcion);
        	articulo.setPrecio(precio);
        	articulo.setStock(stock); 

      
        	daoFactory dFactory = new daoFactory();
        	articulosDAO articuloDAO = dFactory.getArticulosDAO();
        	articuloDAO.update(articulo);
        }else {
        	System.out.println("Error: id, precio o stock negativos");
        }
       
        response.sendRedirect(request.getContextPath() + "/articulos/agregararticulos.jsp");
    }
    private void postUpdatestock(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("id"));

        int stock = Integer.parseInt(request.getParameter("stock")); 
        
        if(id>=1 && stock>=1) {
      
        	articulos articulo = new articulos();
        	articulo.setId(id);
        	articulo.setStock(stock); 

       
        	daoFactory dFactory = new daoFactory();
        	articulosDAO articuloDAO = dFactory.getArticulosDAO();
        	articuloDAO.updatestock(articulo);
        }else {
        	System.out.println("Error: id o stock negativos");
        }

    
        response.sendRedirect(request.getContextPath() + "/articulos/agregararticulos.jsp");
    }



    }



