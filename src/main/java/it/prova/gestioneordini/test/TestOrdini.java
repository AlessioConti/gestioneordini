package it.prova.gestioneordini.test;

import it.prova.gestioneordini.service.ArticoloService;
import it.prova.gestioneordini.service.CategoriaService;
import it.prova.gestioneordini.service.MyServiceFactory;
import it.prova.gestioneordini.service.OrdineService;
import it.prova.gestioneordini.dao.EntityManagerUtil;

public class TestOrdini {

	public static void main(String[] args) {
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		
		try {
			
			System.out.println("Nella tabella Articolo ci sono " +articoloServiceInstance.listAll().size()+ " elementi.");
			System.out.println("Nella tabella Categoria ci sono " +categoriaServiceInstance.listAll().size()+ " elementi.");
			System.out.println("Nella tabella Ordine ci sono " +ordineServiceInstance.listAll().size()+ " elementi.");
			
			System.out.println("INIZIO TESTING");
			
			
			
		}catch (Throwable e) {
			e.printStackTrace();
		} finally {

			EntityManagerUtil.shutdown();
		}
		
	}

}
