package Modelos;

import java.io.Serializable;

public class admin implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 412062062296565287L;
	private long idadmin;
	private String usuarioadmin;
	private String claveadmin;
	
	public admin() {
		super();
	}
	public admin(long idadmin, String usuarioadmin, String claveadmin) {
		super();
		this.idadmin = idadmin;
		this.usuarioadmin = usuarioadmin;
		this.claveadmin = claveadmin;
	}
	public long getIdadmin() {
		return idadmin;
	}
	public void setIdadmin(long idadmin) {
		this.idadmin = idadmin;
	}
	public String getUsuarioadmin() {
		return usuarioadmin;
	}
	public void setUsuarioadmin(String usuarioadmin) {
		this.usuarioadmin = usuarioadmin;
	}
	public String getClaveadmin() {
		return claveadmin;
	}
	public void setClaveadmin(String claveadmin) {
		this.claveadmin = claveadmin;
	}

}
