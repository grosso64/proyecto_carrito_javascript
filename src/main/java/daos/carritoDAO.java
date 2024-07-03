package daos;

import java.util.List;


import Modelos.carrito;


public interface carritoDAO {

	
	
	public List<carrito> listAll();
	
	public carrito findById(long id);
	
	public void insert(carrito carritoo);
	
	void guardarCarritoEnBD(List<carrito> carritoList, long idUsuario);
	
	public List<carrito> listUserId(long userId);

	}


	
	
	
	
	
	
	
	
	
	
	
