package factories;


import daos.articulosDAO;
import daos.articulosDAOdb;
import daos.articulosDaoSingleton;
import daos.carritoDAO;
import daos.carritoDAOdb;



public class daoFactory {
	    public static final String DATA_BASE = "DATA_BASE";
	    public static final String SINGLETON = "SINGLETON";
	    public static final String FILES = "FILES";

	    private String fuente = DATA_BASE;

	    public articulosDAO getArticulosDAO() {
	        articulosDAO dao = switch (this.fuente) {
	            case SINGLETON -> articulosDaoSingleton.getInstance();
	            case FILES -> null;
	            case DATA_BASE -> new articulosDAOdb();
	            default -> null;
	        };
	        return dao;
	    }
	
	    public carritoDAO getCarritoDAO() {
	    	carritoDAO dao = switch (this.fuente) {
	    		case DATA_BASE -> new carritoDAOdb();
	    		default -> null;
	    	};
	    	return dao;
	    	
	    }

	
		
	}
