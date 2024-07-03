package Modelos;

import java.io.Serializable;

public class articulos implements Serializable {

	
	private static final long serialVersionUID = 6704515405440446248L;
	private long id;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;
	
	public articulos() {
		super();
	}
	public articulos(String nombre, String descripcion, double precio, int stock) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
