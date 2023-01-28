package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import enumList.Periodicita;

@Entity
@DiscriminatorValue("Rivista")
public class Rivista extends Catalogo{
	
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;
	
	//getters & setters
	public Periodicita getPeriodicita() {
		return periodicita;
	}

	public void setPeriodicita(Periodicita periodicita) {
		this.periodicita = periodicita;
	}
	
	//methods
	public String getInfo() {
		return "Titolo: " + getTitolo() + " | Codice ISBN: " + getISBN() + " | Anno pubblicazione: " + getAnnoPub() + " | Numero Pagine: " + getNumPag() + " | Periodicita: " + getPeriodicita();
	}
}