package it.prova.gestioneordini.model;

import java.util.List;

public class Categoria {
	
	private Long id;
	private String descrizione;
	private String codice;
	private List<Articolo> articoli;
	
	public Categoria(String descrizione, String codice) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public List<Articolo> getArticoli() {
		return articoli;
	}
	public void setArticoli(List<Articolo> articoli) {
		this.articoli = articoli;
	}
	
	
	
}
