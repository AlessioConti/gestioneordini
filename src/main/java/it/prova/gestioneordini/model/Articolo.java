package it.prova.gestioneordini.model;

import java.util.Date;
import java.util.List;

public class Articolo {
	
	private Long id;
	private String descrizione;
	private int numeroSeriale;
	private int prezzoSingolo;
	private Date dataInserimento;
	private List<Categoria> categorie;
	
	public Articolo(String descrizione, int numeroSeriale, int prezzoSingolo, Date dataInserimento) {
		super();
		this.descrizione = descrizione;
		this.numeroSeriale = numeroSeriale;
		this.prezzoSingolo = prezzoSingolo;
		this.dataInserimento = dataInserimento;
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
	public int getNumeroSeriale() {
		return numeroSeriale;
	}
	public void setNumeroSeriale(int numeroSeriale) {
		this.numeroSeriale = numeroSeriale;
	}
	public int getPrezzoSingolo() {
		return prezzoSingolo;
	}
	public void setPrezzoSingolo(int prezzoSingolo) {
		this.prezzoSingolo = prezzoSingolo;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public List<Categoria> getCategorie() {
		return categorie;
	}
	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}
	
	
	
}
