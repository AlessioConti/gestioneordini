package it.prova.gestioneordini.dao.categoria;

import java.util.List;

import it.prova.gestioneordini.dao.IBaseDAO;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public interface CategoriaDAO extends IBaseDAO<Categoria> {
	public Categoria findByIdFetchingArticoli(Long id);

	public List<Categoria> findAllCategorieDistinteByOrdine(Ordine input);

	public List<String> findCodiciConOrdiniFattiAFebbraio();
}
