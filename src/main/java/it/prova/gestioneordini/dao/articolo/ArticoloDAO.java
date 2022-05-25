package it.prova.gestioneordini.dao.articolo;

import it.prova.gestioneordini.dao.IBaseDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;

public interface ArticoloDAO extends IBaseDAO<Articolo>{
	public Articolo findByIdFetchingCategorie(Long id);
	
	public long findPrezzoTotaleDaUnaCategoria(Categoria input);
	
	public long findSommaArticoliPerMarioRossi();
}
