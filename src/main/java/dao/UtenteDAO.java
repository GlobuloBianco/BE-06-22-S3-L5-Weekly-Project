package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import com.diogonunes.jcolor.Ansi;
import static com.diogonunes.jcolor.Attribute.*;
import app.Main;
import entities.Utente;
import util.JpaUtil;

public class UtenteDAO extends JpaUtil{

	public static void save(Utente u) {
		try {
			t.begin();
			em.persist(u);
			t.commit();
		} catch (Exception x) {
			System.out.println(Ansi.colorize("Ops! Qualcosa è andato storto durante la registrazione... Riprova!", RED_TEXT()));
		}
	}

	public static Utente creaUtente(String nome, String cognome, LocalDate nascita, String tessera) {

		Utente u = new Utente();
		u.setNome(nome);
		u.setCognome(cognome);
		u.setDataNascita(nascita);
		u.setTessera(tessera);

		return u;
	}

	public static void salvaUtenti(ArrayList<Utente> userList) {
		System.out.println("Stiamo registrando l'utente...");
		Main.attesa(1.5);
		System.out.println("Inserendo nuovi dati...");
		Main.attesa(0.5);
		for (Utente u : userList) {
			save(u);
		}
		Main.attesa(1);
		System.out.println(Ansi.colorize("Utenti registrati con successo!", GREEN_TEXT()));
		Main.attesa(0.5);
		System.out.println("Grazie a tutti coloro che si sono registrati!");
		System.out.println(Ansi.colorize("----------------------------------------------", CatalogoDAO.getRandomColor()));
	}
}
