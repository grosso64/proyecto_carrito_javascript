package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Modelos.articulos;
import Modelos.carrito;

import factories.ConnectionFactory2;
import factories.ConnectionFactoryCarrito;




public class carritoDAOdb implements carritoDAO  {


	public List<carrito> listAll() {
	    List<carrito> listadocarrito = new ArrayList<carrito>();
	    String sql = "select * from  Carrito";
	    try (Connection conn = ConnectionFactoryCarrito.getConnection2()) {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            carrito carritoo = new carrito();
	            carritoo.setId(rs.getLong("id"));
	            carritoo.setIdUsuario(rs.getLong("id_usuario"));
	            carritoo.setID_PRODUCTO(rs.getLong("ID_PRODUCTO"));
	            carritoo.setNombre_producto(rs.getString("nombre_producto"));
	            carritoo.setCantidad(rs.getInt("cantidad"));
	            carritoo.setPrecio(rs.getDouble("precio"));
	            listadocarrito.add(carritoo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return listadocarrito;
	}
    public carrito findById(long id) {
    	
    	carrito carritoo = null;
    	try (Connection conn = ConnectionFactoryCarrito.getConnection2()) {
    		
    		String query = "select * from Carrito where id = ?";
    		PreparedStatement ps = conn.prepareStatement(query);
    		
    		ps.setLong(1, id);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			carritoo = new carrito();
    			
    			
                  carritoo.setId(rs.getLong("id"));
                  carritoo.setID_PRODUCTO(rs.getLong("ID_PRODUCTO"));
                  carritoo.setNombre_producto(rs.getString("nombre_producto"));
                  carritoo.setCantidad(rs.getInt("cantidad;"));
      			carritoo.setPrecio(rs.getDouble("precio"));
    			
    		}
    		
    		
    	} catch (SQLException e) {

    		e.printStackTrace();
    	}

    	
    	
    	
    	
    	
    	// TODO Auto-generated method stub
    	return carritoo;
    }

    public void insert(carrito carritoo)  {
        try (Connection conn = ConnectionFactory2.getConnection1()) {
            String sql = "INSERT INTO Carrito (ID_PRODUCTO, nombre_producto, cantidad, precio) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, carritoo.getID_PRODUCTO());
                ps.setString(2, carritoo.getNombre_producto());
                ps.setInt(3, carritoo.getCantidad());
                ps.setDouble(4, carritoo.getPrecio());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void guardarCarritoEnBD(List<carrito> carritoList, long idUsuario) {
        long sigID = getMaxId() + 1; 

        String sql = "INSERT INTO Carrito (ID, id_usuario, ID_PRODUCTO, nombre_producto, cantidad, precio) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactoryCarrito.getConnection2()) {
            for (carrito item : carritoList) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setLong(1, sigID);  
                    preparedStatement.setLong(2, idUsuario);
                    preparedStatement.setLong(3, item.getID_PRODUCTO());
                    preparedStatement.setString(4, item.getNombre_producto());
                    preparedStatement.setInt(5, item.getCantidad());
                    preparedStatement.setDouble(6, item.getPrecio());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    private long getMaxId() {
    	
        try (Connection conn = ConnectionFactoryCarrito.getConnection2()) {
            String sql = "SELECT MAX(ID) FROM Carrito";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;  
    }
    @Override
    public List<carrito> listUserId(long userId) {
        List<carrito> historial = new ArrayList<>();

       
        String sql = "SELECT * FROM carrito WHERE id_usuario = ?";
        
        try (Connection conn = ConnectionFactoryCarrito.getConnection2();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            
            preparedStatement.setLong(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    carrito carrito = new carrito();
                    carrito.setId(resultSet.getLong("id"));
                    carrito.setIdUsuario(resultSet.getLong("id_usuario"));
                    carrito.setID_PRODUCTO(resultSet.getLong("ID_PRODUCTO"));
                    carrito.setNombre_producto(resultSet.getString("nombre_producto"));
                    carrito.setCantidad(resultSet.getInt("cantidad"));
                    carrito.setPrecio(resultSet.getDouble("precio"));
                    
                    historial.add(carrito);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historial;
    }
  
}
	

	
	
	
	












