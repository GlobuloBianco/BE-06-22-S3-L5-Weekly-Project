package dao;

import java.util.List;
import java.util.Random;
import javax.persistence.NoResultException;
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Attribute.*;
import app.Main;
import entities.Catalogo;
import entities.Libro;
import entities.Rivista;
import enumList.Periodicita;
import util.JpaUtil;

public class CatalogoDAO extends JpaUtil {
	public static void save(Catalogo e) {
		try {
			t.begin();
			em.persist(e);
			t.commit();
		} catch (Exception x) {
			System.out.println(Ansi.colorize(
					"Ops! Qualcosa è andato storto durante l'inserimento dell'Elemento.. Riprova!", RED_TEXT()));
		}
	}

	// --------------------Libri--------------------//
	public static Libro creaLibro(String titolo, int anno, int pagine, String autore, String genere, int ISBN) {
		Libro l = new Libro();
		l.setTitolo(titolo);
		l.setAnnoPub(anno);
		l.setNumPag(pagine);
		l.setAutore(autore);
		l.setGenere(genere);
		l.setISBN(ISBN);
		return l;
	}

	public static void salvaLibri(List<Libro> libri) {
		System.out.println("Stiamo mettendo a posto lo scaffale...");
		Main.attesa(1.5);
		System.out.println("Inserendo nuovo elemento...");
		Main.attesa(0.5);
		for (Libro l : libri) {
			save(l);
		}
		Main.attesa(1);
		System.out.println(Ansi.colorize("Inserimento avvenuto con successo!", GREEN_TEXT()));
		Main.attesa(0.5);
		System.out.println("Scaffale in ordine, pronto per le prossime aggiunte!");
		System.out.println(Ansi.colorize("----------------------------------------------", getRandomColor()));
	}

	// --------------------Riviste--------------------//
	public static Rivista creaRivista(String titolo, int anno, int pagine, Periodicita prd, int ISBN) {
		Rivista r = new Rivista();
		r.setTitolo(titolo);
		r.setAnnoPub(anno);
		r.setNumPag(pagine);
		r.setPeriodicita(prd);
		r.setISBN(ISBN);
		return r;
	}

	public static void salvaRiviste(List<Rivista> riviste) {
		for (Rivista r : riviste) {
			save(r);
		}
	}

	// --------------------Elimina--------------------//
	public static void deleteByISBN(int isbn) {
		Catalogo e = em.find(Catalogo.class, isbn);
		if (e != null) {
			try {
				t.begin();
				em.remove(e);
				System.out.println(Ansi.colorize("L'oggetto è stato eliminato correttamente!", GREEN_TEXT()));
				t.commit();
			} catch (Exception x) {
				System.out.println(Ansi.colorize("Il codice ISBN '" + isbn + "' da lei inserito non è stato trovato!", RED_TEXT()));
			}
		} else { // un controllo per verificare se l'oggetto esiste effettivamente nel database prima di eliminarlo
			System.out.println(
					Ansi.colorize("Il codice ISBN '" + isbn + "' da lei inserito non è stato trovato!", RED_TEXT()));
		}
	}

	// --------------------CercaByISBN--------------------//
	public static Catalogo getByISBN(int isbn) {
	    try {
	    	Catalogo c = em.createQuery("SELECT c FROM Catalogo c WHERE c.ISBN = :isbn", Catalogo.class)
	                .setParameter("isbn", isbn)
	                .getSingleResult();
	        System.out.println(Ansi.colorize(c.getInfo(), getRandomColor()));
	        return c;
	    } catch (NoResultException x) {
	        System.out.println(Ansi.colorize("Nessun elemento trovato con ISBN: " + isbn, RED_TEXT()));
	        return null;
	    }
	}
	
	// --------------------CercaByAnno--------------------//
	public static void getByAnno(int annoPub) {
	    try {
	        List<Catalogo> list = em.createQuery("SELECT c FROM Catalogo c WHERE c.annoPub = :annoPub", Catalogo.class)
	                .setParameter("annoPub", annoPub)
	                .getResultList();
	        if(list.isEmpty()){
	            System.out.println(Ansi.colorize("Non è stato trovato nessun elemento con l'anno di pubblicazione: " + annoPub, RED_TEXT()));
	        }else {
	            for (int i = 0; i < list.size(); i++) {
	                Catalogo c = list.get(i);
	                System.out.println(Ansi.colorize(c.getInfo(), getRandomColor()));
	            }
	        }
	    } catch (Exception x) {
	    	 System.out.println(Ansi.colorize("Si è verificato un errore durante la ricerca", RED_TEXT()));
	    }
	}
	// --------------------CercaByAutore--------------------//
	public static List<Libro> getByAutore(String autore) {
	    try {
	        List<Libro> list = em.createQuery("SELECT l FROM Libro l WHERE l.autore LIKE :autore", Libro.class)
	        		.setParameter("autore", "%" + autore + "%")
	                .getResultList();
	        if(list.size()>0){
	            for(Libro l:list) {
	                	System.out.println(Ansi.colorize(l.getInfo(), getRandomColor()));
	            }
	        }else{
	            System.out.println(Ansi.colorize("Nessun elemento trovato con l'autore: " + autore, RED_TEXT()));
	        }
	        return list;
	    } catch (Exception x) {
	    	 System.out.println(Ansi.colorize("Si è verificato un errore durante la ricerca", RED_TEXT()));
	        return null;
	    }
	}
	
	// --------------------CercaByTitolo--------------------//
	public static List<Catalogo> getByTitolo(String titolo) {
	    try {
	        List<Catalogo> cat = em.createQuery("SELECT e FROM Catalogo e WHERE e.titolo LIKE :titolo", Catalogo.class)
	                .setParameter("titolo", "%" + titolo + "%")
	                .getResultList();
	        for (Catalogo l : cat) {
	            System.out.println(Ansi.colorize(l.getInfo(), getRandomColor()));
	        }
	        if (cat.size() == 0) {
	            System.out.println(Ansi.colorize("Nessun elemento trovato con titolo: " + titolo, RED_TEXT()));
	        }
	        return cat;
	    } catch (Exception x) {
	        System.out.println(Ansi.colorize("Si è verificato un errore durante la ricerca", RED_TEXT()));
	        return null;
	    }
	}

	// --------------------Random Syso Colors--------------------//
	public static Attribute getRandomColor() {
	    Attribute[] arrayColori = { YELLOW_TEXT(), BRIGHT_CYAN_TEXT(), BRIGHT_MAGENTA_TEXT(), BRIGHT_GREEN_TEXT() };
	    Random r = new Random();
	    int i = r.nextInt(arrayColori.length);
	    return arrayColori[i];
	}
}
