package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import com.diogonunes.jcolor.Ansi;
import static com.diogonunes.jcolor.Attribute.*;
import app.Main;
import entities.Libro;
import entities.Prestito;
import entities.Rivista;
import entities.Utente;
import util.JpaUtil;
@SuppressWarnings("unchecked")
public class PrestitoDAO extends JpaUtil {
	
    public static void save(Prestito p) {
        try {
            t.begin();
            em.persist(p);
            t.commit();
        } catch (Exception x) {
            System.out.println(Ansi.colorize("Ops! Qualcosa è andato storto durante la registrazione... Riprova!", RED_TEXT()));
        }
    }
    //--------------------CreaByPrestito--------------------//
    public static Prestito creaPrestito(Rivista oggetto, Utente ut, LocalDate inizio, LocalDate restituito) {
    	Prestito p = new Prestito();
    	
    	p.setPrestato(oggetto);
    	p.setUtente(ut);
    	p.setpInizio(inizio);
    	p.setpScadenza(inizio.plusDays(30));
    	p.setpRestituito(restituito);
    	
    	return p;
    }
    
    public static Prestito creaPrestito(Libro oggetto, Utente ut, LocalDate inizio, LocalDate restituito) {
    	Prestito p = new Prestito();
    	
    	p.setPrestato(oggetto);
    	p.setUtente(ut);
    	p.setpInizio(inizio);
    	p.setpScadenza(inizio.plusDays(30));
    	p.setpRestituito(restituito);
    	
    	return p;
    }
 // --------------------SalvaPrestito--------------------//
	public static void salvaPrestiti(ArrayList<Prestito> pList) {
        System.out.println("Stiamo registrando i prestiti...");
        Main.attesa(1.5);
        System.out.println("Inserendo nuovi dati...");
        Main.attesa(0.5);
		for (Prestito p : pList) {
			save(p);
		}
        Main.attesa(1);
        System.out.println(Ansi.colorize("Registrazione avvenuta con successo!", GREEN_TEXT()));
        Main.attesa(0.5);
        System.out.println("Grazie per aver utilizzato il nostro servizio bibliotecario!");
		System.out.println(Ansi.colorize("----------------------------------------------", CatalogoDAO.getRandomColor()));
	}
	// --------------------GetByTessera--------------------//
	public static void getByTessera(String utente) {
		//SELECT p FROM public.prestito p WHERE p.utente_id = 1 AND p.restituito > p.scadenza_prestito;
	    Query query = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.tessera = :utente AND p.pRestituito > p.pScadenza", Prestito.class);
	    query.setParameter("utente", utente);
		List<Prestito> prestito = query.getResultList();
	    if(prestito.isEmpty()) {
	        System.out.println(Ansi.colorize("Nessun prestito trovato!!" , RED_TEXT()));
	    } else {
	        for(Prestito p: prestito) {
	            System.out.println(Ansi.colorize(p.getInfo() , CatalogoDAO.getRandomColor()));
	        }
	    }
	}
	//SELECT p FROM public.prestito p WHERE p.restituito IS NULL AND p.utente_id = 'S4LGK16L';
	
	// --------------------CercaByPrestitiScaduti--------------------//
	public static void getByScaduti() {
	    //SELECT p FROM Prestito p WHERE p.pRestituito IS NULL AND p.pScadenza < CURRENT_DATE;
	    Query query = em.createQuery("SELECT p FROM Prestito p WHERE p.pScadenza <  p.pRestituito", Prestito.class);
		List<Prestito> prestiti = query.getResultList();
	    if(prestiti.isEmpty()) {
	        System.out.println(Ansi.colorize("Nessun prestito scaduto trovato!!" , RED_TEXT()));
	    } else {
	        for(Prestito p: prestiti) {
	            System.out.println(Ansi.colorize(p.getInfo() , CatalogoDAO.getRandomColor()));
	        }
	    }
	}
}
