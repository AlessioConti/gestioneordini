package it.prova.gestioneordini.test;

import it.prova.gestioneordini.service.ArticoloService;
import it.prova.gestioneordini.service.CategoriaService;
import it.prova.gestioneordini.service.MyServiceFactory;
import it.prova.gestioneordini.service.OrdineService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class TestOrdini {

	public static void main(String[] args) {
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();

		try {

			System.out.println(
					"Nella tabella Articolo ci sono " + articoloServiceInstance.listAll().size() + " elementi.");
			System.out.println(
					"Nella tabella Categoria ci sono " + categoriaServiceInstance.listAll().size() + " elementi.");
			System.out.println("Nella tabella Ordine ci sono " + ordineServiceInstance.listAll().size() + " elementi.");

			System.out.println("INIZIO TESTING");

			testInserimentoNuovoOrdine(ordineServiceInstance);
			System.out.println("Nella tabella Ordine ci sono " + ordineServiceInstance.listAll().size() + " elementi.");

			testAggiornaOrdine(ordineServiceInstance);

			testInserimentoArticoloInOrdine(ordineServiceInstance, articoloServiceInstance);

			testRimuoviArticoloDaOrdine(ordineServiceInstance, articoloServiceInstance);

			testAggiungiArticoloACategoria(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);

			testAggiungiCategoriaAdArticolo(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);

			testRimozioneArticolo(articoloServiceInstance);

			testRimozioneCategoria(categoriaServiceInstance);

			testRimozioneOrdine(ordineServiceInstance);

			testCercaPerCategoria(ordineServiceInstance, categoriaServiceInstance);

			testPrendiCategorieDatoUnOrdine(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);

			testPrendiSommaArticoliDaUnaCategoria(articoloServiceInstance, categoriaServiceInstance,
					ordineServiceInstance);

			testFindIlPiuRecentementeSpeditoDataCategoria(articoloServiceInstance, categoriaServiceInstance,
					ordineServiceInstance);

			testCercaListaOrdiniFattiAFebbraio(articoloServiceInstance, categoriaServiceInstance,
					ordineServiceInstance);

			testCercaLaSommaPrezziArticoliMarioRossi(articoloServiceInstance, categoriaServiceInstance,
					ordineServiceInstance);

			testCercaListaIndirizziConNumeroSerialeArticoloCheContiene(articoloServiceInstance,
					categoriaServiceInstance, ordineServiceInstance);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {

			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserimentoNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testInserimentoNuovoOrdine inizializzato.......");
		Ordine nuovoOrdine = new Ordine("Alessio Conti", "Via Mosca, 52",
				new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-20"));
		ordineServiceInstance.inserisci(nuovoOrdine);
		if (nuovoOrdine.getId() == null)
			throw new RuntimeException("testInserimentoNuovoOrdine FALLITO!");

		System.out.println("testInserimentoNuovoOrdine concluso.......");
	}

	private static void testAggiornaOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testAggiornaOrdine inizializzato.......");
		Ordine ordineTemp = new Ordine("Prova1", "Via Mosca, 1111",
				new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01"));
		ordineServiceInstance.inserisci(ordineTemp);
		if (ordineTemp.getId() == null)
			throw new RuntimeException("testAggiornaOrdine FALLITO!");

		LocalDateTime createDateTimeIniziale = ordineTemp.getCreateDateTime();
		LocalDateTime updateDateTimeIniziale = ordineTemp.getUpdateDateTime();

		ordineServiceInstance.aggiorna(ordineTemp);

		if (ordineTemp.getUpdateDateTime().isAfter(updateDateTimeIniziale))
			throw new RuntimeException("testAggiornaOrdine fallito: le date di modifica sono disallineate ");

		if (!ordineTemp.getCreateDateTime().equals(createDateTimeIniziale))
			throw new RuntimeException("testAggiornaOrdine fallito: la data di creazione è cambiata ");

		System.out.println("testAggiornaOrdine concluso.......");
	}

	private static void testInserimentoArticoloInOrdine(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance) throws Exception {
		System.out.println("testInserimentoArticoloInOrdine inizializzato.......");
		Ordine ordineProvaInserimento = new Ordine("Prova7", "Via Mosca, 22",
				new SimpleDateFormat("yyyy-MM-dd").parse("2004-07-27"));

		Articolo articoloDaInserire1 = new Articolo("Test Descrizione1", 00010001, 13,
				new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-23"));

		articoloDaInserire1.setOrdine(ordineProvaInserimento);

		ordineServiceInstance.inserisci(ordineProvaInserimento);

		articoloServiceInstance.inserisci(articoloDaInserire1);

		System.out.println("testInserimentoArticoloInOrdine concluso.......");
	}

	private static void testRimuoviArticoloDaOrdine(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance) throws Exception {
		System.out.println("testRimuoviArticoloDaOrdine inizializzato.......");

		Ordine ordineProvaRimozione = new Ordine("Prova8", "Via Mosca, 24",
				new SimpleDateFormat("yyyy-MM-dd").parse("2004-06-11"));

		Articolo articoloDaInserire5 = new Articolo("Test Descrizione7", 00010007, 113,
				new SimpleDateFormat("yyyy-MM-dd").parse("2002-05-23"));

		articoloDaInserire5.setOrdine(ordineProvaRimozione);

		ordineServiceInstance.inserisci(ordineProvaRimozione);

		articoloServiceInstance.inserisci(articoloDaInserire5);

		articoloServiceInstance.rimuovi(articoloDaInserire5.getId());

		System.out.println("testRimuoviArticoloDaOrdine concluso.......");
	}

	private static void testAggiungiArticoloACategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testAggiungiArticoloACategoria inizializzato.......");

		Ordine ordineProvaRimozione = new Ordine("Prova9", "Via Mosca, 26",
				new SimpleDateFormat("yyyy-MM-dd").parse("2004-06-11"));

		Articolo articoloDaInserire6 = new Articolo("Test Descrizione8", 0010002, 213,
				new SimpleDateFormat("yyyy-MM-dd").parse("2005-05-23"));

		articoloDaInserire6.setOrdine(ordineProvaRimozione);
		ordineServiceInstance.inserisci(ordineProvaRimozione);

		articoloServiceInstance.inserisci(articoloDaInserire6);

		Categoria categoriaCollegamento = new Categoria("Categoria 1", "00010001");
		categoriaServiceInstance.inserisci(categoriaCollegamento);

		categoriaServiceInstance.aggiungiArticolo(categoriaCollegamento, articoloDaInserire6);

		System.out.println("testAggiungiArticoloACategoria concluso.......");
	}

	private static void testAggiungiCategoriaAdArticolo(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testAggiungiCategoriaAdArticolo iniziallizzato......");

		Ordine ordineProvaRimozione = new Ordine("Prova10", "Via Mosca, 28",
				new SimpleDateFormat("yyyy-MM-dd").parse("2004-06-11"));

		Articolo articoloDaInserire7 = new Articolo("Test Descrizione9", 0010004, 223,
				new SimpleDateFormat("yyyy-MM-dd").parse("2005-05-23"));

		articoloDaInserire7.setOrdine(ordineProvaRimozione);
		ordineServiceInstance.inserisci(ordineProvaRimozione);
		articoloServiceInstance.inserisci(articoloDaInserire7);

		Categoria categoriaNuova = new Categoria("Categoria 2", "00010002");
		categoriaServiceInstance.inserisci(categoriaNuova);

		articoloServiceInstance.aggiungiCategoria(articoloDaInserire7, categoriaNuova);

		System.out.println("testAggiungiCategoriaAdArticolo concluso......");
	}

	private static void testRimozioneArticolo(ArticoloService articoloServiceInstance) throws Exception {
		System.out.println("testRimozioneArticolo inizializzato......");

		List<Articolo> articoli = articoloServiceInstance.listAll();

		Articolo articoloDaRimuovere = articoli.get(0);

		articoloServiceInstance.rimuovi(articoloDaRimuovere.getId());

		System.out.println("testRimozioneArticolo concluso.....");
	}

	private static void testRimozioneCategoria(CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println("testRimozioneCategoria inizializzato.....");

		List<Categoria> categorie = categoriaServiceInstance.listAll();

		Categoria categoriaDaRimuovere = categorie.get(0);

		categoriaServiceInstance.rimuovi(categoriaDaRimuovere.getId());

		System.out.println("testRimozioneCategoria concluso.....");
	}

	private static void testRimozioneOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testRimozioneOrdine inizializzato.......");

		List<Ordine> ordini = ordineServiceInstance.listAll();

		Ordine ordineDaRimuovere = ordini.get(0);

		ordineServiceInstance.rimuovi(ordineDaRimuovere.getId());

		System.out.println("testRimozioneOrdine concluso.......");
	}

	private static void testCercaPerCategoria(OrdineService ordineServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println("testCercaPerCategoria inizializzato........");

		List<Categoria> categorie = categoriaServiceInstance.listAll();

		Categoria categoriaPerRicerca = categorie.get(0);

		ordineServiceInstance.cercaOrdiniConArticoliDiCategoria(categoriaPerRicerca);

		System.out.println("testCercaPerCategoria concluso........");
	}

	private static void testPrendiCategorieDatoUnOrdine(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testPrendiCategorieDatoUnOrdine inizializzato.......");

		Ordine ordineProva = new Ordine("Prova12", "Via Mosca, 32",
				new SimpleDateFormat("yyyy-MM-dd").parse("2004-06-11"));

		Articolo articoloDaInserire7 = new Articolo("Test Descrizione11", 0020001, 300,
				new SimpleDateFormat("yyyy-MM-dd").parse("2005-05-23"));

		articoloDaInserire7.setOrdine(ordineProva);
		ordineServiceInstance.inserisci(ordineProva);
		articoloServiceInstance.inserisci(articoloDaInserire7);

		Categoria categoriaNuova = new Categoria("Categoria 2", "00010002");
		categoriaServiceInstance.inserisci(categoriaNuova);

		articoloServiceInstance.aggiungiCategoria(articoloDaInserire7, categoriaNuova);

		List<Categoria> list = categoriaServiceInstance.cercaCategorieDistinteDatoUnOrdine(ordineProva);

		for (Categoria categoriaInput : list)
			System.out.println(categoriaInput);

		System.out.println("testPrendiCategorieDatoUnOrdine concluso.......");
	}

	private static void testPrendiSommaArticoliDaUnaCategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testPrendiSommaArticoliDaUnaCategoria inizializzato.........");

		Ordine ordineProva = new Ordine("Prova12", "Via Mosca, 32",
				new SimpleDateFormat("yyyy-MM-dd").parse("2004-06-11"));

		Articolo articoloDaInserire7 = new Articolo("Test Descrizione11", 0020001, 300,
				new SimpleDateFormat("yyyy-MM-dd").parse("2005-05-23"));

		articoloDaInserire7.setOrdine(ordineProva);
		ordineServiceInstance.inserisci(ordineProva);
		articoloServiceInstance.inserisci(articoloDaInserire7);

		Categoria categoriaNuova = new Categoria("Categoria 2", "00010002");
		categoriaServiceInstance.inserisci(categoriaNuova);
		System.out.println(categoriaNuova.getId());

		articoloServiceInstance.aggiungiCategoria(articoloDaInserire7, categoriaNuova);

		long prezzo = articoloServiceInstance.trovaSommaPrezziDataUnaCategoria(categoriaNuova);

		System.out.println(prezzo);

		System.out.println("testPrendiSommaArticoliDaUnaCategoria concluso.........");
	}

	private static void testFindIlPiuRecentementeSpeditoDataCategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testFindIlPiuRecentementeSpeditoDataCategoria inizializzato......");

		Ordine ordineProvaRimozione = new Ordine("Prova14", "Via Mosca, 40",
				new SimpleDateFormat("yyyy-MM-dd").parse("2004-06-11"));

		Articolo articoloDaInserire9 = new Articolo("Test Descrizione12", 0010002, 213,
				new SimpleDateFormat("yyyy-MM-dd").parse("2005-05-23"));
		Categoria categoriaNuova = new Categoria("Categoria 10", "00010001");

		ordineServiceInstance.inserisci(ordineProvaRimozione);
		articoloDaInserire9.setOrdine(ordineProvaRimozione);
		articoloServiceInstance.inserisci(articoloDaInserire9);

		categoriaServiceInstance.inserisci(categoriaNuova);
		articoloServiceInstance.aggiungiCategoria(articoloDaInserire9, categoriaNuova);

		System.out.println(ordineServiceInstance.prendiOrdineSpeditoPiuRecente(categoriaNuova));
		System.out.println("testFindIlPiuRecentementeSpeditoDataCategoria concluso......");
	}

	private static void testCercaListaOrdiniFattiAFebbraio(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testCercaListaOrdiniFattiAFebbraio inizializzato........");

		Ordine ordineProvaRimozione = new Ordine("Prova15", "Via Mosca, 42",
				new SimpleDateFormat("yyyy-MM-dd").parse("2022-02-11"));

		Articolo articoloDaInserire9 = new Articolo("Test Descrizione13", 0040002, 213,
				new SimpleDateFormat("yyyy-MM-dd").parse("2005-05-23"));
		Categoria categoriaNuova = new Categoria("Categoria 11", "00010003");

		ordineServiceInstance.inserisci(ordineProvaRimozione);
		articoloDaInserire9.setOrdine(ordineProvaRimozione);
		articoloServiceInstance.inserisci(articoloDaInserire9);

		categoriaServiceInstance.inserisci(categoriaNuova);
		articoloServiceInstance.aggiungiCategoria(articoloDaInserire9, categoriaNuova);

		List<String> codiciCategorie = categoriaServiceInstance.cercaListaOrdiniFattiAFebbraio();

		for (String stringInput : codiciCategorie)
			System.out.println(stringInput);

		System.out.println("testCercaListaOrdiniFattiAFebbraio concluso........");
	}

	private static void testCercaLaSommaPrezziArticoliMarioRossi(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testCercaLaSommaPrezziArticoliMarioRossi inizializzato.....");

		Ordine ordinePerTest15 = new Ordine("Mario Rossi", "Via Mosca 44",
				new SimpleDateFormat("dd/MM/yyyy").parse("10/05/2009"));
		Articolo articoloPerTest12 = new Articolo("Prova16", 00060001, 125,
				new SimpleDateFormat("dd/MM/yyyy").parse("04/05/2021"));
		Articolo articoloPerTest13 = new Articolo("Prova17", 00060002, 100,
				new SimpleDateFormat("dd/MM/yyyy").parse("14/07/2021"));
		articoloPerTest12.setOrdine(ordinePerTest15);
		articoloPerTest13.setOrdine(ordinePerTest15);
		ordineServiceInstance.inserisci(ordinePerTest15);
		articoloServiceInstance.inserisci(articoloPerTest12);
		articoloServiceInstance.inserisci(articoloPerTest13);
		Categoria cartegoriaPerTest7 = new Categoria("Libri", "012914G");
		categoriaServiceInstance.inserisci(cartegoriaPerTest7);
		articoloServiceInstance.aggiungiCategoria(articoloPerTest12, cartegoriaPerTest7);
		articoloServiceInstance.aggiungiCategoria(articoloPerTest13, cartegoriaPerTest7);

		System.out.println(articoloServiceInstance.trovaSommaArticoliPerMarioRossi());

		System.out.println("testCercaLaSommaPrezziArticoliMarioRossi concluso........");
	}

	private static void testCercaListaIndirizziConNumeroSerialeArticoloCheContiene(
			ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		System.out.println("testCercaListaIndirizziConNumeroSerialeArticoloCheContiene inizializzato...");

		Ordine ordinePerTest16 = new Ordine("Alessio Conti", "Via Mosca 46",
				new SimpleDateFormat("dd/MM/yyyy").parse("30/07/2012"));
		Articolo articoloPerTest13 = new Articolo("Prova17", 00060002, 250,
				new SimpleDateFormat("dd/MM/yyyy").parse("04/05/2020"));

		articoloPerTest13.setOrdine(ordinePerTest16);

		ordineServiceInstance.inserisci(ordinePerTest16);
		articoloServiceInstance.inserisci(articoloPerTest13);

		Categoria cartegoriaPerTest8 = new Categoria("Libri", "012914G");

		categoriaServiceInstance.inserisci(cartegoriaPerTest8);
		articoloServiceInstance.aggiungiCategoria(articoloPerTest13, cartegoriaPerTest8);
		int controlloStringa = 006;
		List<String> risultatiQuery = ordineServiceInstance
				.cercaListaIndirizziConNumeroSerialeArticoloCheContiene(controlloStringa);
		for (String stringInput : risultatiQuery)
			System.out.println(stringInput);

		System.out.println("testCercaListaIndirizziConNumeroSerialeArticoloCheContiene concluso...");
	}
}
