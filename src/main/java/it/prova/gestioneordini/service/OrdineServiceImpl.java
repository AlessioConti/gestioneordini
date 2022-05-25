package it.prova.gestioneordini.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.dao.ordine.OrdineDAO;
import it.prova.gestioneordini.exception.OrdineConArticoliAssegnatiException;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDAO ordineDAO;
	@Override
	public List<Ordine> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			ordineDAO.setEntityManager(entityManager);
			
			return ordineDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);
			
			return ordineDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			
			ordineDAO.setEntityManager(entityManager);
			
			ordineDAO.update(ordineInstance);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisci(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			
			ordineDAO.setEntityManager(entityManager);
			
			ordineDAO.insert(ordineInstance);
			
			entityManager.getTransaction().commit();
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setOrdineDAO(OrdineDAO ordineDAO) {
		this.ordineDAO = ordineDAO;
	}

	@Override
	public void rimuovi(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			
			ordineDAO.setEntityManager(entityManager);
			
			Ordine ordineRimuovere = ordineDAO.findByIdFetchingArticoli(id);
			
			if(!ordineRimuovere.getArticoli().isEmpty())
				throw new OrdineConArticoliAssegnatiException("Impossibile cancellare l'ordine");
			
			ordineDAO.delete(ordineRimuovere);
			
			entityManager.getTransaction().commit();
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	public void aggiungiArticolo(Ordine ordineInstance, Articolo articoloInstance) throws Exception{
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			
			ordineDAO.setEntityManager(entityManager);
			
			ordineInstance = entityManager.merge(ordineInstance);
			
			articoloInstance = entityManager.merge(articoloInstance);
			
			ordineInstance.getArticoli().add(articoloInstance);
			
			entityManager.getTransaction().commit();
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	public List<Ordine> cercaOrdiniConArticoliDiCategoria(Categoria input) throws Exception{
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			
			ordineDAO.setEntityManager(entityManager);
			
			return ordineDAO.findAllWIthArticoloWithCategoria(input);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	public Ordine prendiOrdineSpeditoPiuRecente(Categoria input) throws Exception{
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			 ordineDAO.setEntityManager(entityManager);
			 
			 return ordineDAO.findIlPiuRecentementeSpeditoDataCategoria(input);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
