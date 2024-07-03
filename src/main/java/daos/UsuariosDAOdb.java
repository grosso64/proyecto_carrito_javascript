package daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Modelos.admin;
import Modelos.usuarios;
import factories.ConnectionFactory;
import factories.connectionfactoryadmin;


public class UsuariosDAOdb {

    public List<usuarios> listAll() {
        List<usuarios> listado = new ArrayList<>();
        String sql = "select * from usuarios";

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuarios usuario = new usuarios();
                usuario.setId(rs.getLong("id"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setClave(rs.getString("clave"));
                usuario.setSaldo(rs.getDouble("saldo")); 
                listado.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listado;
    }

    public usuarios validarUsuario(String nombreUsuario, String clave) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, clave);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuarios usuario = new usuarios();
                    usuario.setId(rs.getLong("id"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setClave(rs.getString("clave"));
                    usuario.setSaldo(rs.getDouble("saldo"));
                    System.out.println("Usuario encontrado en la base de datos: " + usuario.getUsuario());
                    return usuario;
                   
                }else {
                    System.out.println("Usuario no encontrado en la base de datos.");
                    return null;
                }
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        return null;
    }
public List<admin> listAll1() {
    List<admin> listadoadmin = new ArrayList<>();
    String sql = "select * from admin";

    try (Connection conn = connectionfactoryadmin.getConnectionadmin()) {
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
        	admin adminn = new admin();
        	adminn.setIdadmin(rs.getLong("idadmin"));
        	adminn.setUsuarioadmin(rs.getString("usuarioadmin"));
        	adminn.setClaveadmin(rs.getString("claveadmin"));
            listadoadmin.add(adminn);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listadoadmin;
}

public admin validarAdmin(String usuario, String clave) {
    String sql = "SELECT * FROM admin WHERE usuarioadmin = ? AND claveadmin = ?";
    
    try (Connection conn = connectionfactoryadmin.getConnectionadmin();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, usuario);
        ps.setString(2, clave);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
            	admin adminn = new admin();
            	adminn.setIdadmin(rs.getLong("idadmin"));
            	adminn.setUsuarioadmin(rs.getString("usuarioadmin"));
            	adminn.setClaveadmin(rs.getString("claveadmin"));
                return adminn;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; 
}
public void actualizarSaldo(usuarios usuario) {
    String sql = "UPDATE usuarios SET saldo = ? WHERE LOWER(usuario) = LOWER(?)";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setDouble(1, usuario.getSaldo());
        ps.setString(2, usuario.getUsuario().toLowerCase());  

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Saldo actualizado correctamente para el usuario: " + usuario.getUsuario());
        } else {
            System.out.println("No se pudo actualizar el saldo para el usuario: " + usuario.getUsuario());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public boolean existeUsuario(String nombreUsuario) {
    String sql = "SELECT * FROM usuarios WHERE LOWER(usuario) = LOWER(?)";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, nombreUsuario);

        try (ResultSet rs = ps.executeQuery()) {
            boolean existe = rs.next();
            System.out.println("Usuario encontrado en la base de datos: " + (existe ? nombreUsuario : "null"));
            return existe;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
public usuarios obtenerUsuarioPorNombre(String nombreUsuario) {
    String sql = "SELECT * FROM usuarios WHERE LOWER(usuario) = LOWER(?)";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, nombreUsuario);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                usuarios usuario = new usuarios();
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setSaldo(rs.getDouble("saldo"));
                
                return usuario;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
public Double getDoubleParameter(HttpServletRequest request, String paramName) {
    try {
        return Double.parseDouble(request.getParameter(paramName));
    } catch (NumberFormatException | NullPointerException e) {
        return null;
    }
}
}


