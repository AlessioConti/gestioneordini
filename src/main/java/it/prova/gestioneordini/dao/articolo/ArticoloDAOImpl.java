package it.prova.gestioneordini.dao.articolo;

import java.util.List;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo get(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Articolo findByIdFetchingCategorie(Long id) {
		TypedQuery<Articolo> query = entityManager.createQuery(
				"select a from Articolo a left join fetch a.categorie c where a.id = :idArt", Articolo.class);
		query.setParameter("idArt", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	public long findPrezzoTotaleDaUnaCategoria(Categoria input) {

		Query q = entityManager.createNativeQuery(
				"select sum(a.prezzosingolo) from articolo a inner join articolo_categoria ac on a.id=ac.articolo_id inner join categoria c on c.id=ac.categoria_id where c.id = :idCat");
		q.setParameter("idCat", input.getId());
		BigDecimal result = (BigDecimal) q.getResultList().stream().findFirst().get();
		return result.longValue();
	}
	
	public long findSommaArticoliPerMarioRossi() {
		Query q = entityManager.createNativeQuery("select sum(a.prezzosingolo) from articolo a INNER JOIN ordine o ON o.id=a.ordine_id WHERE o.nomedestinatario LIKE 'Mario Rossi'");
		BigDecimal result = (BigDecimal) q.getResultList().stream().findFirst().get();
		return result.longValue();
	}

}
