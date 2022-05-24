package it.prova.gestioneordini.test;

import it.prova.gestioneordini.service.ArticoloService;
import it.prova.gestioneordini.service.CategoriaService;
import it.prova.gestioneordini.service.MyServiceFactory;
import it.prova.gestioneordini.service.OrdineService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Ordine;

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
			/*
			testInserimentoNuovoOrdine(ordineServiceInstance);
			System.out.println("Nella tabella Ordine ci sono " +ordineServiceInstance.listAll().size()+ " elementi.");
			*/
			testAggiornaOrdine(ordineServiceInstance);
			
		}catch (Throwable e) {
			e.printStackTrace();
		} finally {

			EntityManagerUtil.shutdown();
		}
		
	}
	
	private static void testInserimentoNuovoOrdine(OrdineService ordineServiceInstance) throws Exception{
		System.out.println("testInserimentoNuovoOrdine inizializzato.......");
		Ordine nuovoOrdine = new Ordine("Alessio Conti", "Via Mosca, 52", new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-20"));
		ordineServiceInstance.inserisci(nuovoOrdine);
		if(nuovoOrdine.getId() == null)
			throw new RuntimeException("testInserimentoNuovoOrdine FALLITO!");
		
		System.out.println("testInserimentoNuovoOrdine concluso.......");
	}
	
	private static void testAggiornaOrdine(OrdineService ordineServiceInstance) throws Exception{
		System.out.println("testAggiornaOrdine inizializzato.......");
		Ordine ordineTemp = new Ordine("Prova1", "Via Mosca, 1111", new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01"));
		ordineServiceInstance.inserisci(ordineTemp);
		if(ordineTemp.getId() == null)
			throw new RuntimeException("testAggiornaOrdine FALLITO!");
		
		LocalDateTime createDateTimeIniziale = ordineTemp.getCreateDateTime();
		LocalDateTime updateDateTimeIniziale = ordineTemp.getUpdateDateTime();
		
		ordineServiceInstance.aggiorna(ordineTemp);
		
		if (ordineTemp.getUpdateDateTime().isAfter(updateDateTimeIniziale))
			throw new RuntimeException("testAggiornaOrdine fallito: le date di modifica sono disallineate ");

		// la data creazione deve essere uguale a quella di prima
		if (!ordineTemp.getCreateDateTime().equals(createDateTimeIniziale))
			throw new RuntimeException("testAggiornaOrdine fallito: la data di creazione è cambiata ");
		
		System.out.println("testAggiornaOrdine concluso.......");
	}
	
	private static void testInserimentoArticoloInOrdine(OrdineService ordineServiceInstance, ArticoloService articoloServiceInstance) throws Exception{
		System.out.println("testInserimentoArticoloInOrdine inizializzato.......");
		Ordine ordineProvaInserimento = new Ordine("Prova2", "Via Mosca, 10", new SimpleDateFormat("yyyy-MM-dd").parse("2004-07-27"));
		ordineServiceInstance.inserisci(ordineProvaInserimento);
		if(ordineProvaInserimento.getId() == null)
			throw new RuntimeException("testInserimentoArticoloInOrdine FALLITO: ordine non completato");
		
		Articolo articoloDaInserire1 = new Articolo("Test Descrizione1", 00010001, 13, new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-23"));
		if(articoloDaInserire1.getId() == null)
			throw new RuntimeException("testInserimentoArticoloInOrdine FALLITO: articolo non completato");
		
		Articolo articoloDaInserire2 = new Articolo("Test Descrizione2", 00010002, 22, new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-23"));
		if(articoloDaInserire2.getId() == null)
			throw new RuntimeException("testInserimentoArticoloInOrdine FALLITO: articolo non completato");
		
		//DA COMPLETARE IN CLASSE, INFORMAZIONI POCO CHIARE
	}

}
