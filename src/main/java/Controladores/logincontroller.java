package Controladores;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.admin;
import Modelos.usuarios;
import daos.UsuariosDAOdb;


@WebServlet("/logincontroller")
public class logincontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public logincontroller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest1(request, response);
        request.getRequestDispatcher("/index/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest1(request, response);
    }

    protected void processRequest1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
    
 


        if ("Iniciar Sesión".equals(accion)) {
            if (autenticarUsuario(request)) {
                HttpSession session = request.getSession();
                usuarios usuarioValidado = (usuarios) session.getAttribute("usuario");
               
                request.setAttribute("usuario", usuarioValidado);

                request.getRequestDispatcher("/inicioCliente/iniciocli.jsp").forward(request, response);
                
            } 
            else if (autenticarUsuarioadmin(request)) {
                response.sendRedirect(request.getContextPath() + "/articulos/agregararticulos.jsp");
                
            }else {
                request.setAttribute("mensajeError", "Error de autenticación. Verifica tus credenciales.");
                request.getRequestDispatcher("/index/login.jsp").forward(request, response);
            }
        
        

     
        }
    }

    private boolean autenticarUsuario(HttpServletRequest request) {
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        UsuariosDAOdb usuariosDAO = new UsuariosDAOdb();
        usuarios usuarioValidado = usuariosDAO.validarUsuario(usuario, clave);

        if (usuarioValidado != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioValidado);
            return true;
        } else {
            return false;
        }
    }
    private boolean autenticarUsuarioadmin(HttpServletRequest request) {
        String usuarioadmin = request.getParameter("usuario");
        String claveadmin = request.getParameter("clave");

        UsuariosDAOdb usuariosadminDAO = new UsuariosDAOdb();
        admin usuarioadminValidado = usuariosadminDAO.validarAdmin(usuarioadmin, claveadmin);

        return usuarioadminValidado != null;
    }
}