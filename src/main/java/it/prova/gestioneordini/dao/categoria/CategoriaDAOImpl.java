package it.prova.gestioneordini.dao.categoria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class CategoriaDAOImpl implements CategoriaDAO {

	private EntityManager entityManager;

	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Categoria get(Long id) throws Exception {
		return entityManager.find(Categoria.class, id);
	}

	@Override
	public void update(Categoria input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Categoria input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Categoria input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Categoria findByIdFetchingArticoli(Long id) {
		TypedQuery<Categoria> query = entityManager.createQuery(
				"select c from Categoria c left join fetch c.articoli a where c.id = :idCat", Categoria.class);
		query.setParameter("idCat", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	public List<Categoria> findAllCategorieDistinteByOrdine(Ordine input) {
		Query q = entityManager.createNativeQuery(
				"select distinct c.descrizione from categoria c INNER JOIN articolo_categoria ac ON c.id=ac.categoria_id INNER JOIN articolo a ON a.id=ac.articolo_id INNER JOIN ordine o ON a.ordine_id=a.id WHERE o.id= :idOrd");
		q.setParameter("idOrd", input.getId());
		return q.getResultList();
	}

	public List<String> findCodiciConOrdiniFattiAFebbraio() {
		Query q = entityManager.createNativeQuery(
				"select distinct c.codice from categoria c INNER JOIN articolo_categoria ac ON c.id=ac.categoria_id INNER JOIN articolo a ON a.id=ac.articolo_id INNER JOIN ordine o ON o.id=a.ordine_id WHERE o.dataspedizione BETWEEN '2022-02-01' AND '2022-02-28'");
		return q.getResultList();
	}

}
