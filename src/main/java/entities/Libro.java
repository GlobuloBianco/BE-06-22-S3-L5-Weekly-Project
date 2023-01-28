package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Libro")
public class Libro extends Catalogo {
	private String autore;
	private String genere;
	
//getters & setters
	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	//methods
	public String getInfo() {
		return "Titolo: " + getTitolo() + " | Codice ISBN: " + getISBN() + " | Anno pubblicazione: " + getAnnoPub() + " | Numero Pagine: " + getNumPag() + " | Autore: " + getAutore() + " | Genere: " + getGenere();
	}
}