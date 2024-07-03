package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.articulos;

import factories.ConnectionFactory2;



public class articulosDAOdb implements articulosDAO  {
	@Override
	public List<articulos> listAll() {
		
	List<articulos> listado = new ArrayList<articulos>();

	String sql = "select * from  articulos";

	try (Connection conn = ConnectionFactory2.getConnection1()) {

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			articulos articulo = new articulos();

			articulo.setId(rs.getLong("id"));
			articulo.setNombre(rs.getString("nombre"));
			articulo.setDescripcion(rs.getString("Descripcion"));
			articulo.setPrecio(rs.getDouble("precio"));
			articulo.setStock(rs.getInt("stock"));
			
			listado.add(articulo);

		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
		return listado;
	}

@Override
public articulos findById(long id) {
	
	articulos articulo = null;
	try (Connection conn = ConnectionFactory2.getConnection1()) {
		
		String query = "select * from articulos where id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			articulo = new articulos();

			articulo.setId(rs.getLong("id"));
			articulo.setNombre(rs.getString("nombre"));
			articulo.setDescripcion(rs.getString("Descripcion"));
			articulo.setPrecio(rs.getDouble("precio"));
			articulo.setStock(rs.getInt("stock"));
			
		}
		
		
	} catch (SQLException e) {

		e.printStackTrace();
	}

	
	
	
	
	
	// TODO Auto-generated method stub
	return articulo;
}

@Override
public void insert(articulos articulo)   {

	try (Connection conn = ConnectionFactory2.getConnection1()) {

		String sql = "INSERT INTO articulos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, articulo.getNombre());
        ps.setString(2, articulo.getDescripcion());
        ps.setDouble(3, articulo.getPrecio());
        ps.setInt(4, articulo.getStock());

		ps.executeUpdate();

	} catch (SQLException e) {

		e.printStackTrace();
	}

}
@Override
public void updatestock(articulos articulo) {

	try (Connection conn = ConnectionFactory2.getConnection1()) {

		String sql = "UPDATE articulos SET  stock = ? WHERE id = ?";

		PreparedStatement ps = conn.prepareStatement(sql);

	
        ps.setInt(1, articulo.getStock());
        ps.setLong(2, articulo.getId());
        

		ps.executeUpdate();

	} catch (SQLException e) {

		e.printStackTrace();
	}

}
@Override
public void update(articulos articulo) {

	try (Connection conn = ConnectionFactory2.getConnection1()) {

		String sql = "UPDATE articulos SET nombre = ?, descripcion = ?, precio = ?, stock = ? WHERE id = ?";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, articulo.getNombre());
        ps.setString(2, articulo.getDescripcion());
        ps.setDouble(3, articulo.getPrecio());
        ps.setInt(4, articulo.getStock());
        ps.setLong(5, articulo.getId());
        

		ps.executeUpdate();

	} catch (SQLException e) {

		e.printStackTrace();
	}

}
@Override
public void delete(long id) {

	try (Connection conn = ConnectionFactory2.getConnection1()) {

		String sql = "delete from articulos " + " where id=?";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, id);

		ps.executeUpdate();

	} catch (SQLException e) {

		e.printStackTrace();
	}



}
}


