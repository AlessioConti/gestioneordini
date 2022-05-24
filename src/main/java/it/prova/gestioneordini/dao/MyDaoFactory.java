package it.prova.gestioneordini.dao;

import it.prova.gestioneordini.dao.articolo.ArticoloDAO;
import it.prova.gestioneordini.dao.articolo.ArticoloDAOImpl;
import it.prova.gestioneordini.dao.categoria.CategoriaDAO;
import it.prova.gestioneordini.dao.categoria.CategoriaDAOImpl;
import it.prova.gestioneordini.dao.ordine.OrdineDAO;
import it.prova.gestioneordini.dao.ordine.OrdineDAOImpl;

public class MyDaoFactory {
	
	private static OrdineDAO ordineDAOInstance = null;
	private static ArticoloDAO articoloDAOInstance = null;
	private static CategoriaDAO categoriaDAOInstance = null;
	
	public static OrdineDAO getOrdineDAOInstance() {
		if(ordineDAOInstance == null)
			ordineDAOInstance = new OrdineDAOImpl();
		
		return ordineDAOInstance;
	}
	
	public static ArticoloDAO getArticoloDAO() {
		if(articoloDAOInstance == null)
			articoloDAOInstance = new ArticoloDAOImpl();
			
		return articoloDAOInstance;
	}
	
	public static CategoriaDAO getCategoriaDAO() {
		if(categoriaDAOInstance == null)
			categoriaDAOInstance = new CategoriaDAOImpl();
		
		return categoriaDAOInstance;
	}
	
}
