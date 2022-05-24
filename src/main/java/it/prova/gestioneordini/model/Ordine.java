package it.prova.gestioneordini.model;

import java.util.Date;
import java.util.List;

public class Ordine {
	
	private Long id;
	private String nomeDestinatario;
	private String indirizzoSpedizione;
	private Date dataSpedizione;
	private List<Articolo> articoli;
	
	public Ordine(String nomeDestinatario, String indirizzoSpedizione, Date dataSpedizione) {
		super();
		this.nomeDestinatario = nomeDestinatario;
		this.indirizzoSpedizione = indirizzoSpedizione;
		this.dataSpedizione = dataSpedizione;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}
	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}
	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}
	public Date getDataSpedizione() {
		return dataSpedizione;
	}
	public void setDataSpedizione(Date dataSpedizione) {
		this.dataSpedizione = dataSpedizione;
	}
	public List<Articolo> getArticoli() {
		return articoli;
	}
	public void setArticoli(List<Articolo> articoli) {
		this.articoli = articoli;
	}
	
	
	
}
