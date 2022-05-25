package it.prova.gestioneordini.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="descrizione")
	private String descrizione;
	@Column(name="codice")
	private String codice;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	private List<Articolo> articoli = new ArrayList<Articolo>();
	
	public Categoria() {}
	
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

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", descrizione=" + descrizione + ", codice=" + codice + "]";
	}
	
	
	
}
