package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipologia")
public class Catalogo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private int ISBN;
	
	private String titolo;
	private int annoPub;
	private int numPag;
	
	//getter & setter
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public int getAnnoPub() {
		return annoPub;
	}
	public void setAnnoPub(int annoPub) {
		this.annoPub = annoPub;
	}
	public int getNumPag() {
		return numPag;
	}
	public void setNumPag(int numPag) {
		this.numPag = numPag;
	}
	
	//methods
	public String getInfo() {
		return "Titolo: " + getTitolo() + " | Codice ISBN: " + getISBN() + " | Anno pubblicazione: " + getAnnoPub() + " | Numero Pagine: " + getNumPag();
	}
}