package it.prova.gestioneordini.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;
	
	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine get(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Ordine findByIdFetchingArticoli(Long id) {
		TypedQuery<Ordine> query = entityManager.createQuery("select o from Ordine o left join fetch o.articoli a where o.id = :idOrd", Ordine.class);
		query.setParameter("idOrd", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}
	
	public List<Ordine> findAllWIthArticoloWithCategoria(Categoria input){
		Query q = entityManager.createNativeQuery("select distinct o.id from ordine o INNER JOIN articolo a on o.id=a.ordine_id INNER JOIN articolo_categoria ac on a.id=ac.articolo_id INNER JOIN categoria c on c.id=ac.categoria_id where c.id= :idCat");
		q.setParameter("idCat", input.getId());
		return q.getResultList();
	}
	
	public Ordine findIlPiuRecentementeSpeditoDataCategoria(Categoria input) {
		TypedQuery<Ordine> query = entityManager.createQuery(
				"select o from Ordine o join o.articoli a join a.categorie c where c.id = ?1 order by o.dataSpedizione desc", Ordine.class);
		return query.setParameter(1, input.getId()).getResultList().get(0);
	}
	
	public List<String> findIndirizziConStringaNelNumeroSerialeArticoli(int input){
		Query q = entityManager.createNativeQuery("select distinct o.indirizzoSpedizione from ordine o INNER JOIN articolo a ON o.id=a.ordine_id INNER JOIN articolo_categoria ac ON a.id=ac.articolo_id INNER JOIN categoria c ON ac.categoria_id=c.id where a.numeroseriale LIKE ?");
		q.setParameter(1, "%"+input+"%");
		return q.getResultList();
	}

}
