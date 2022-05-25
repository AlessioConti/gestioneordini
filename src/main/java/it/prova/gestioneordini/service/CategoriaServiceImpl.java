package it.prova.gestioneordini.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.dao.categoria.CategoriaDAO;
import it.prova.gestioneordini.exception.CategoriaConArticoliAssegnatiException;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO categoriaDAO;

	@Override
	public List<Categoria> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Categoria caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			categoriaDAO.setEntityManager(entityManager);

			categoriaDAO.update(categoriaInstance);

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
	public void inserisci(Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			categoriaDAO.setEntityManager(entityManager);

			categoriaDAO.insert(categoriaInstance);

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
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	@Override
	public void rimuovi(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			categoriaDAO.setEntityManager(entityManager);

			Categoria categoriaDaEliminare = categoriaDAO.findByIdFetchingArticoli(id);

			if (!categoriaDaEliminare.getArticoli().isEmpty())
				throw new CategoriaConArticoliAssegnatiException("Impossibile cancellare la categoria");

			categoriaDAO.delete(categoriaDaEliminare);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public void aggiungiArticolo(Categoria categoriaInstance, Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			categoriaDAO.setEntityManager(entityManager);

			categoriaInstance = entityManager.merge(categoriaInstance);

			articoloInstance = entityManager.merge(articoloInstance);

			categoriaInstance.getArticoli().add(articoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public List<Categoria> cercaCategorieDistinteDatoUnOrdine(Ordine input) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.findAllCategorieDistinteByOrdine(input);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public List<String> cercaListaOrdiniFattiAFebbraio() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.findCodiciConOrdiniFattiAFebbraio();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

}
