package daos;

import java.util.Comparator;
import java.util.List;

import daos.articulosDaoSingleton;

import Modelos.articulos;

public class articulosDaoSingleton implements articulosDAO  {
	
private static articulosDaoSingleton singleton;
	
	public static articulosDaoSingleton getInstance() {
		if(singleton == null) {
			singleton = new articulosDaoSingleton();
		}
		return singleton;
	};
	
	
	private List<articulos> listado;
	@Override
	public List<articulos> listAll() {
		return this.listado.stream().toList();
	}
	@Override
	public void insert(articulos articulo) {
		long ultId = this.listado
		.stream()
		.max(Comparator.comparing(articulos::getId) )		
		.map(articulos::getId)
		.orElse(0L);
		
		articulo.setId(ultId+1);
		
		this.listado.add(articulo);
	}
	public void updatestock(articulos articulo) {
		 
	    for (articulos existeArticulo : this.listado) {
	        if (existeArticulo.getId() == articulo.getId()) {

	        	existeArticulo.setPrecio(articulo.getStock());

	            System.out.println("Artículo actualizado: " + articulo.getId());
	            return; 
	        }
	    }
	    System.out.println("El artículo con ID " + articulo.getId() + " no existe en la lista.");
	}
	
	@Override
	public void update(articulos articulo) {
	 
	    for (articulos existeArticulo : this.listado) {
	        if (existeArticulo.getId() == articulo.getId()) {

	        	existeArticulo.setNombre(articulo.getNombre());
	        	existeArticulo.setDescripcion(articulo.getDescripcion());
	        	existeArticulo.setPrecio(articulo.getPrecio());
	        	existeArticulo.setPrecio(articulo.getStock());

	            System.out.println("Artículo actualizado: " + articulo.getId());
	            return; 
	        }
	    }
	    System.out.println("El artículo con ID " + articulo.getId() + " no existe en la lista.");
	}
	
	public void delete(long id) {
		this.listado.removeIf(p->p.getId() == id);
		
	}
	@Override
	public articulos findById(long id) {
		return this.listado.stream()
				.filter( p-> p.getId() == id )
				.findFirst()
				.orElse(null);
	}

}
