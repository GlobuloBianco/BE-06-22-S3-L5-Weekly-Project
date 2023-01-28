package entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Prestito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;

    @ManyToOne
    @JoinColumn(name = "catalogo_id")
	private Catalogo prestato;

	@Column(name = "inizio_prestito")
	private LocalDate pInizio;

	@Column(name = "scadenza_prestito")
	private LocalDate pScadenza;

	@Column(name = "restituito")
	private LocalDate pRestituito;

	//getter & setter
	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Catalogo getPrestato() {
		return prestato;
	}

	public void setPrestato(Catalogo prestato) {
		this.prestato = prestato;
	}

	public LocalDate getpInizio() {
		return pInizio;
	}

	public void setpInizio(LocalDate pInizio) {
		this.pInizio = pInizio;
	}

	public LocalDate getpScadenza() {
		return pScadenza;
	}

	public void setpScadenza(LocalDate pScadenza) {
		this.pScadenza = pScadenza;
	}

	public LocalDate getpRestituito() {
		return pRestituito;
	}

	public void setpRestituito(LocalDate pRestituito) {
		this.pRestituito = pRestituito;
	}
	
	//methods
	public String getInfo() {
		return "Utente: " + getUtente().getTessera() + " | Codice ISBN: " + getPrestato().getISBN() + " | Data ritiro: " + getpInizio() + " | Data scadenza: " + getpScadenza() + " | Consegna effettiva: " + getpRestituito();
	}
}