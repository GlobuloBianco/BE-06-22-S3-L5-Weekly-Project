package app;

import java.time.LocalDate;
import java.util.ArrayList;
import dao.CatalogoDAO;
import dao.PrestitoDAO;
import dao.UtenteDAO;
import entities.Libro;
import entities.Prestito;
import entities.Rivista;
import entities.Utente;
import enumList.Periodicita;

public class Main {	

	//----------------------------------------------------------------------!!
	//-------------------------switch per attivare--------------------------!!
						static boolean crea = true;
					    static boolean deleteConISBN = true;
					    static boolean getISBN = true;
					    static boolean getAutore = true;
					    static boolean getTitolo = true;
					    static boolean getTessera = true;
					    static boolean getScaduti = true;
    //----------------------------------------------------------------------!!
    //----------------------------------------------------------------------!!
	static ArrayList<Libro> libList = new ArrayList<Libro>();
	static ArrayList<Rivista> rivList = new ArrayList<Rivista>();
	static ArrayList<Utente> userList = new ArrayList<Utente>();
	static ArrayList<Prestito> pList = new ArrayList<Prestito>();
	
	public static void main(String[] args) {

		if (crea) creazioni();
		//attesa(2); // il tempo che inserisce i dati nel database prima di checkare
		if (deleteConISBN) CatalogoDAO.deleteByISBN(41252);
		if (getISBN) CatalogoDAO.getByISBN(41254);
		if (getAutore) CatalogoDAO.getByAutore("Tol");
		if (getTitolo) CatalogoDAO.getByTitolo("Il Sig");
		if (getTessera) PrestitoDAO.getByTessera("S4LGK16L");
		if (getScaduti) PrestitoDAO.getByScaduti();
	}
	
	public static void creazioni() {
		//Libri
		libList.add(CatalogoDAO.creaLibro("Il Signore degli Anelli", 2022, 500, "J.R.R. Tolkien", "Fantasy", 46536));
		libList.add(CatalogoDAO.creaLibro("Harry Potter e la Pietra Filosofale", 2022, 400, "J.K. Rowling", "Fantasy", 81253));
		libList.add(CatalogoDAO.creaLibro("La Divina Commedia", 2022, 600, "Dante Alighieri", "Narrativa", 41254));

		//Riviste
		rivList.add(CatalogoDAO.creaRivista("Vogue", 2022, 200, Periodicita.MENSILE, 14393));
		rivList.add(CatalogoDAO.creaRivista("National Geographic", 2022, 150, Periodicita.MENSILE, 14394));
		rivList.add(CatalogoDAO.creaRivista("Cosmopolitan", 2021, 100, Periodicita.SEMESTRALE, 14395));

		//Utenti
		userList.add(UtenteDAO.creaUtente("Mario", "Rossi", LocalDate.of(1999, 8, 1), "L2JGL3DL"));
		userList.add(UtenteDAO.creaUtente("Lucia", "Bianchi", LocalDate.of(2000, 5, 12), "S5HLSJ5L"));
		userList.add(UtenteDAO.creaUtente("Giovanni", "Neri", LocalDate.of(1998, 3, 3), "S4LGK16L"));

		//Prestiti		
		pList.add(PrestitoDAO.creaPrestito(rivList.get(0), userList.get(0), LocalDate.of(2023, 1, 13), LocalDate.of(2023, 2, 11)));
		pList.add(PrestitoDAO.creaPrestito(rivList.get(1), userList.get(1), LocalDate.of(2023, 3, 15), LocalDate.of(2023, 3, 28)));
		pList.add(PrestitoDAO.creaPrestito(libList.get(0), userList.get(2),  LocalDate.of(2023, 5, 21), LocalDate.of(2023, 6, 29)));
		pList.add(PrestitoDAO.creaPrestito(libList.get(1), userList.get(0), LocalDate.now(), LocalDate.now().plusDays(50)));

		//Salvataggi
		CatalogoDAO.salvaLibri(libList);
		attesa(0.5);
		UtenteDAO.salvaUtenti(userList);
		attesa(0.5);
		CatalogoDAO.salvaRiviste(rivList);
		attesa(0.5);
		PrestitoDAO.salvaPrestiti(pList);
	}

	public static void attesa(double sec) {
		int millisec = (int) (sec * 1000);
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}