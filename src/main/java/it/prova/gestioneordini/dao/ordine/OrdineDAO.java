package it.prova.gestioneordini.dao.ordine;

import java.util.List;

import it.prova.gestioneordini.dao.IBaseDAO;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine>{
	
	public Ordine findByIdFetchingArticoli(Long id);
	
	public List<Ordine> findAllWIthArticoloWithCategoria(Categoria input);
	
	public Ordine findIlPiuRecentementeSpeditoDataCategoria(Categoria input);
	
	public List<String> findIndirizziConStringaNelNumeroSerialeArticoli(int input);
}
