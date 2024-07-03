package daos;

import java.util.List;

import Modelos.articulos;


public interface articulosDAO {


		
		
		public List<articulos> listAll();
		
		public articulos findById(long id);
		
		public void insert(articulos articulo);
		
		public void updatestock(articulos articulo);
		
		public void update(articulos articulo);
		
		public void delete(long id);
		
		public default void delete(articulos articulo) {
			this.delete(articulo.getId());
		}

		
		

	}

