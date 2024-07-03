package Modelos;

import java.io.Serializable;

public class carrito implements Serializable {

    private static final long serialVersionUID = 6704515405440446248L;
    private long id;
    private long idUsuario;
    private long ID_PRODUCTO;
    private String nombre_producto;
    private int cantidad;
    private double precio;

    public carrito() {
        super();
    }

    public carrito(long idUsuario, long ID_PRODUCTO, String nombre_producto, int cantidad, double precio) {
        super();
        this.idUsuario = idUsuario;
        this.ID_PRODUCTO = ID_PRODUCTO;
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getId() {
		return id;
	}

    public void setId(long id) {
        this.id = id;
    }

    public long getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(long ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
