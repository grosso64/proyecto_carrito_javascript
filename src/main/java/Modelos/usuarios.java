package Modelos;

import java.io.Serializable;

public class usuarios implements Serializable {


	private static final long serialVersionUID = 5197634095108245024L;

	private long id;
	private String usuario;
	private String clave;
	private Double saldo;
	
	public usuarios() {
		super();
	}
	public usuarios(int id, String usuario, String clave, Double saldo) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.clave = clave;
		this.saldo = saldo;
	}
	public Double getSaldo() {
		return this.saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	

}
