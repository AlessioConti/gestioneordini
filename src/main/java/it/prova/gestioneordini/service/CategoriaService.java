package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.categoria.CategoriaDAO;
import it.prova.gestioneordini.model.Categoria;


public interface CategoriaService {
	
	public List<Categoria> listAll() throws Exception;
	
	public Categoria caricaSingoloElemento(Long  id) throws Exception;
	
	public void aggiorna(Categoria categoriaInstance) throws Exception;
	
	public void inserisci(Categoria categoriaInstance) throws Exception;
	
	public void rimuovi(Long id) throws Exception;
	
	public void setCategoriaDAO(CategoriaDAO categoriaDAO);
}