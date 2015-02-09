package old;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.MarshalledObject;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationID;
import java.rmi.activation.ActivationSystem;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.ArrayList;

import org.nocrala.tools.texttablefmt.*;

public class CopyOfImplServerSDAct extends Activatable implements InterfaceServerSDAdmin , Unreferenced, Serializable{


	protected CopyOfImplServerSDAct(ActivationID id, MarshalledObject data) throws RemoteException, ActivationException {
		super(id, 3456);
		
		//da vedere se questa parte metterla nel main
		Tools tools = new Tools();
		
		String nomeFileSegnalazioni = "segnalazioni.txt";
		String nomeFileTicket = "numeroTicket.txt";

		System.out.println(" ********** SERVICE DESK SERVER CENTRALE **********");
		System.out.println(" ********** Sono dentro il costruttore del Server Centrale.");
		System.out.println(" ********** E' stato invocato con successo il costruttore della superclasse Activatable, ");
		System.out.println(" ********** passando come parametro l'ActivationID "+id+" del server che verra esportato alla porta "+3456);
		ActivationSystem actS = ActivationGroup.getSystem();
		System.out.println(" ********** La referenza al sistema di attivazione (rmid) e': "+actS);
		ActivationDesc actD = actS.getActivationDesc(id);
		System.out.println(" ********** Ho ricavato l' ActivationDescriptor "+actD+", associato al server attivabile grazie all'ActivationID="+id);
		System.out.println(" ********** Costruttore Server Centrale terminato.");
		
		try {
			if(!tools.esisteFile(nomeFileSegnalazioni)){
				tools.creaFile(nomeFileSegnalazioni);
				System.out.println(" ********** ho creato il file "+nomeFileSegnalazioni+" che non esisteva");
				tools.scriviFile(nomeFileSegnalazioni, "0,utente test,aperta, anomalia test, messaggio test");
				System.out.println("********** ho inizializzato il file "+nomeFileSegnalazioni+" con i nomi degli attributi");
			}
		} catch (FileNotFoundException e) {
			System.out.println(" !!!!!!!! ATTENZIONE: problemi di creazione del file: "+nomeFileSegnalazioni);
		}


		try {
			if(!tools.esisteFile(nomeFileTicket)){
				tools.creaFile(nomeFileTicket);
				System.out.println(" ********** ho creato il file "+nomeFileTicket+" che non esisteva");
				tools.scriviFile(nomeFileTicket, "1");
			}
		} catch (FileNotFoundException e) {
			System.out.println(" !!!!!!!! ATTENZIONE: problemi di creazione del file: "+nomeFileTicket);
		}
		// Fine Blocco per la creazione di file di testo
	}

	Tools tools = new Tools();

	
	/**
	 * metodo per il client user a disposizione per aprire una segnalazione
	 */
	@Override
	public void apriSegnalazione(String user, String messaggio) throws RemoteException {
		Tools tools = new Tools();
		String nomeFile = "segnalazioni.txt";
		if(!tools.esisteFile(nomeFile)){
			try {
				tools.creaFile(nomeFile);
				//				tools.scriviFile(nomeFile, "TICKET" + "," + "UTENTE" + ","+ "STATO"+ "," + "MESSAGGIO"+","+"SOLUZIONE");

			} catch (FileNotFoundException e1) {
				System.out.println("[ATTENZIONE errore nella routine di apertura segnalazione] Problemi di creazione del file "+nomeFile);
			}
		}


		try {
			
			String record = getNewTicketNumber() + "," + user + ","  + "aperta" + ", " +messaggio + ","+ "pending";


			tools.scriviFile(nomeFile, record);


		} catch (IOException e) {
			System.out.println("[ATTENZIONE errore nella routine di apertura segnalazione] Problemi con la lettura del file: "+nomeFile);
		}
	}



	/**
	 * metodo che restituisce la lista delle segnalazioni scritte nel file
	 */
	@Override
	public String visualizzaSegnalazioni() throws RemoteException {
		Tools tools = new Tools();
		Table t = new Table(5);
		String nomeFile = "segnalazioni.txt";

		try {
			System.out.println("--> Richiamato il metodo di visualizzazione della lista chiamate da parte del client");
			ArrayList<String> arrayListSegnalazioni = tools.leggiFileRitorna(nomeFile);

			String[][] riga = new String[arrayListSegnalazioni.size()][5];
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				String[] campi = arrayListSegnalazioni.get(i).split(",");
				riga[i][0] = campi[0];
				riga[i][1] = campi[1];
				riga[i][2] = campi[2];
				riga[i][3] = campi[3];
				riga[i][4] = campi[4];
			}

			addIntestazioneSegnalazione(t);



			for(int i =0; i<riga.length;i++){
				for(int c=0;c<5;c++){
					t.addCell(riga[i][c]);
				}
			}

			String render = t.render();
			return render;




		} catch (IOException e) {
			String msg = "[ATTENZIONE nella routine di visualizza segnalazioni] Ho qualche problema di lettura del file "+nomeFile + " dal metodo che visualizza le segnalazioni chiuse!";
			System.out.println(msg);
			return msg;

		}


	}

	/**
	 * metodo che restituisce  una segnalazione in particolare
	 */
	@Override
	public String visualizzaSegnalazioni(int numero) throws RemoteException {
		Tools tools = new Tools();
		Table t = new Table(5);
		String nomeFile = "segnalazioni.txt";
		int segnalazioneTrovata = -1;
		String record = "";
		ArrayList<String> arrayListSegnalazioni = new ArrayList<String>();
		try {
			System.out.println("--> Richiamato il metodo di visualizzazione della chiamata n° "+ numero + " da parte del client");
			arrayListSegnalazioni = tools.leggiFileRitorna(nomeFile);
			String[][] riga = new String[arrayListSegnalazioni.size()][5];
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				String[] campi = arrayListSegnalazioni.get(i).split(",");
				riga[i][0] = campi[0];
				riga[i][1] = campi[1];
				riga[i][2] = campi[2];
				riga[i][3] = campi[3];
				riga[i][4] = campi[4];
			}

			for(int i =0; i<riga.length;i++){
				if(numero == Integer.parseInt(riga[i][0]) )
					segnalazioneTrovata = i;
			}

			addIntestazioneSegnalazione(t);
			for(int i=0;i<5;i++)
				t.addCell(riga[segnalazioneTrovata][i]);









		} catch (IOException e) {
			String msg = "[ATTENZIONE nella routine di visualizza segnalazione particolare] Problemi di lettura del file "+nomeFile;
			System.out.println(msg);
			return msg;
		}

		//		if (segnalazioneTrovata != 0){
		//			
		//			String render = t.render();
		//			return "\nLa segnalazione cercata e' la seguente: " + "\n" + arrayListSegnalazioni.get(segnalazioneTrovata)+
		//					"\n*******************************************\n";
		//		}else
		//			return "\nla segnalazione numero "+numero+" non e' stata trovata. controlla meglio..\n";
		//
		//	}

		if (segnalazioneTrovata != -1){

			String render = t.render();
			return "\nLa segnalazione cercata e' la seguente: " + "\n" + render + "\n";
		}else
			return "\nla segnalazione numero "+numero+" non e' stata trovata. controlla meglio..\n";

	}



	/**
	 * metodo che aggiorna la segnalazione con un messaggio da parte dell'admin
	 */
	@Override
	public void rispondiSegnalazione(int numero, String messaggio)
			throws RemoteException {

		String nomeFile = "segnalazioni.txt";
		int segnalazioneTrovata = -1;
		try {
			System.out.println("--> Richiamato aggiornamento per la segnalazione numero "+numero);
			ArrayList<String> arrayListSegnalazioni = tools.leggiFileRitorna(nomeFile);

			String[][] riga = new String[arrayListSegnalazioni.size()][5];
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				String[] campi = arrayListSegnalazioni.get(i).split(",");
				riga[i][0] = campi[0];
				riga[i][1] = campi[1];
				riga[i][2] = campi[2];
				riga[i][3] = campi[3];
				riga[i][4] = campi[4];
			}

			for(int i =0; i<riga.length;i++){
				if(numero == Integer.parseInt(riga[i][0]) )
					segnalazioneTrovata = i;
			}

			if(segnalazioneTrovata > -1){
				riga[segnalazioneTrovata][4] = messaggio;


				String[] listaAggiornata = new String[riga.length];

				for(int i =0;i<riga.length;i++)
					listaAggiornata[i] = riga[i][0] + "," + riga[i][1] + "," +riga[i][2] + "," +riga[i][3] + "," +riga[i][4];

				tools.clearFile(nomeFile);
				tools.scriviFile(nomeFile, listaAggiornata);
				System.out.println("--> Segnalazione numero "+numero+" aggiornata con il seguente messaggio: "+messaggio);
			}
			else
				System.out.println("[ATTENZIONE] Non riesco a trovare la segnalazione numero "+numero+ " che cerchi!");




		} catch (IOException e) {
			System.out.println("[ATTENZIONE] Ho qualche problema di lettura del file "+nomeFile + " dal metodo che chiude le segnalazioni!");
		}













	}

	/**
	 * metodo che chiude una segnalazione dato il numero
	 */
	@Override
	public String chiudiSegnalazione(int numero) throws RemoteException {
		String nomeFile = "segnalazioni.txt";
		int segnalazioneTrovata = -1;
		try {
			System.out.println("--> Richiamata chiusura della segnalazione numero "+numero);
			ArrayList<String> arrayListSegnalazioni = tools.leggiFileRitorna(nomeFile);

			String[][] riga = new String[arrayListSegnalazioni.size()][5];
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				String[] campi = arrayListSegnalazioni.get(i).split(",");
				riga[i][0] = campi[0];
				riga[i][1] = campi[1];
				riga[i][2] = campi[2];
				riga[i][3] = campi[3];
				riga[i][4] = campi[4];
			}

			for(int i =0; i<riga.length;i++){
				if(numero == Integer.parseInt(riga[i][0]) )
					segnalazioneTrovata = i;
			}

			if(segnalazioneTrovata > -1){
				riga[segnalazioneTrovata][2] = "closed";


				String[] listaAggiornata = new String[riga.length];

				for(int i =0;i<riga.length;i++)
					listaAggiornata[i] = riga[i][0] + "," + riga[i][1] + "," +riga[i][2] + "," +riga[i][3] + "," +riga[i][4];

				tools.clearFile(nomeFile);
				tools.scriviFile(nomeFile, listaAggiornata);
				System.out.println("--> Segnalazione n° "+numero+" chiusa con successo!");
				return "segnalazione numero "+numero+" chiusa!";
			}
			else
				return "[ATTENZIONE errori nella routine di chiusura segnalazioni] Non riesco a trovare la segnalazione numero "+numero+ " che cerchi!";




		} catch (IOException e) {
			String msg = "[ATTENZIONE] Ho qualche problema di lettura del file "+nomeFile + " dal metodo che chiude le segnalazioni!";
			System.out.println(msg);
			return msg;
		}


	}

	/**
	 * metodo che ritorna il numero delle segnalazioni in totale
	 */
	@Override
	public int getNumeroSegnalazioniTOT() throws RemoteException {
		String nomeFile = "segnalazioni.txt";
		
		try {
			int numeroSegnalazioni = tools.leggiFileRitorna(nomeFile).size();
			//System.out.println("Numero segnalazioni registrate = "+numeroSegnalazioni);
			return (numeroSegnalazioni);
		} catch (IOException e) {
			System.out.println("[ATTENZIONE errori nel conteggio del numero delle segnalazioni] Ho qualche problema di lettura del file "+nomeFile + " dal metodo che conta le segnalazioni!");
			return 0;
		}
	}


	/**
	 * metodo che ritorna il numero delle segnalazioni chiuse
	 */
	@Override
	public int getNumeroSegnalazioniChiuse() throws RemoteException {
		String nomeFile = "segnalazioni.txt";
		int segnalazioniChiuse = 0;
		try {

			ArrayList<String> arrayListSegnalazioni = tools.leggiFileRitorna(nomeFile);

			String[][] riga = new String[arrayListSegnalazioni.size()][5];
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				String[] campi = arrayListSegnalazioni.get(i).split(",");
				riga[i][0] = campi[0];
				riga[i][1] = campi[1];
				riga[i][2] = campi[2];
				riga[i][3] = campi[3];
				riga[i][4] = campi[4];
			}

			for(int i =0; i<riga.length;i++){
				if(riga[i][2].equals("closed") )
					segnalazioniChiuse++;
			}


			return segnalazioniChiuse;




		} catch (IOException e) {

			System.out.println("[ATTENZIONE] Ho qualche problema di lettura del file "+nomeFile + " dal metodo che conta le segnalazioni chiuse!");
			return segnalazioniChiuse;
		}
	}




	/**
	 * metodo che ritorna il prossimo numero ticket
	 * @return
	 */
	public int getNewTicketNumber() {
		Tools tools = new Tools();
		String nomeFile = "numeroTicket.txt";
		if(tools.esisteFile(nomeFile)){
			try {
				ArrayList<String> listaNumeriTicket = tools.leggiFileRitorna(nomeFile);
				int numTicket = Integer.parseInt(listaNumeriTicket.get(0))+1;
				tools.clearFile(nomeFile);
				tools.scriviFile(nomeFile, String.valueOf(numTicket));
				System.out.println("I Ticket sono arrivati al numero: "+numTicket);
				return numTicket;

			} catch (IOException e) {
				System.out.println("[ATTENZIONE errori nella routine di calcolo del numero ticket] Problemi con la lettura del file: "+nomeFile);
			}
		}
		return 0;
	}



	/**
	 * metodo che crea l'intestazione della tabella
	 * @param t
	 */
	public void addIntestazioneSegnalazione(Table t){
		t.addCell("TICKET");
		t.addCell("UTENTE");
		t.addCell("STATO");
		t.addCell("MESSAGGIO");
		t.addCell("SOLUZIONE");
	}


	@Override
	public void unreferenced() {
		Tools tools = new Tools();
		try {
			
			System.out.println(" [GC] Sono dentro il metodo unreferenced del server centrale: " + this + " richiamato il "+tools.getDataOra());
			System.out.println(" [GC] Ho invocato il metodo inactive per disattivare il server attivabile");
			System.out.println(" [GC] Tale metodo si occupa anche di de-esportare il server");
			
			Naming.unbind("ServerSD");
            boolean ok = inactive(getID());
			
			System.out.println(" [GC] Il server "+this+" e' inattivo? "+ok);
			System.out.println(" [GC] Sto invocando il garbage collector dentro la JVM del server attivabile");
			System.gc();
			System.out.println(" [GC] Sto uscendo dal metodo unreferenced.");
		}
		catch (Exception e) {System.out.println("errore unreferenced. "+e);}
	}



	/**
	 * metodo main
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args){
		Tools tools = new Tools();

		// Blocco per la creazione di file di testo
		String nomeFileSegnalazioni = "segnalazioni.txt";
		String nomeFileTicket = "numeroTicket.txt";


		if(System.getSecurityManager()==null){
			System.setSecurityManager(new RMISecurityManager());
		}

		/**
		try {
			if(!tools.esisteFile(nomeFileSegnalazioni)){
				tools.creaFile(nomeFileSegnalazioni);
				System.out.println("ho creato il file "+nomeFileSegnalazioni+" che non esisteva");
				//				tools.scriviFile(nomeFileSegnalazioni, "TICKET" + "," + "UTENTE" + ","+ "STATO"+ "," + "MESSAGGIO"+","+"SOLUZIONE");
				System.out.println("no inizializzato il file "+nomeFileSegnalazioni+" con i nomi degli attributi");
			}
		} catch (FileNotFoundException e) {
			System.out.println("problemi di creazione del file: "+nomeFileSegnalazioni);
		}


		try {
			if(!tools.esisteFile(nomeFileTicket)){
				tools.creaFile(nomeFileTicket);
				System.out.println("ho creato il file "+nomeFileTicket+" che non esisteva");
				tools.scriviFile(nomeFileTicket, "1");
			}
		} catch (FileNotFoundException e) {
			System.out.println("probmei di creazione del file: "+nomeFileTicket);
		}
		// Fine Blocco per la creazione di file di testo
		**/
	}




}










