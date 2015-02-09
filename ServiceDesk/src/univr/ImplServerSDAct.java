package univr;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import java.util.Calendar;
import java.util.GregorianCalendar;



public class ImplServerSDAct extends Activatable implements InterfaceServerSDAdmin , Unreferenced, Serializable{

	public String pathDb = "/Mago/javarmi/univr/db/";
	String nomeFileAccessi = "accessi.txt";
	
	protected ImplServerSDAct(ActivationID id, MarshalledObject data) throws RemoteException, ActivationException {
		super(id, 3456);
		
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
			System.out.println(" ########## Gestione creazione database ##########");
			if(!esisteFile(pathDb+nomeFileSegnalazioni)){
				creaFile(pathDb+nomeFileSegnalazioni);
				System.out.println(" ********** ho creato il file "+nomeFileSegnalazioni+" che non esisteva nel percorso "+pathDb);
				scriviFile(pathDb+nomeFileSegnalazioni, "0|utente test|aperta|anomalia test|messaggio test");
				System.out.println(" ********** ho inizializzato il file "+pathDb+nomeFileSegnalazioni+" con i nomi degli attributi");
			}else{
				System.out.println(" ********** file "+pathDb+nomeFileSegnalazioni + " esistente");
			}
		} catch (FileNotFoundException e) {
			System.out.println(" !!!!!!!! ATTENZIONE: problemi di creazione del file: "+pathDb+nomeFileSegnalazioni);
		}


		try {
			System.out.println(" ########## Gestione creazione file ticket ##########");
			if(!esisteFile(pathDb+nomeFileTicket)){
				creaFile(pathDb+nomeFileTicket);
				System.out.println(" ********** ho creato il file "+pathDb+nomeFileTicket+" che non esisteva nel percorso "+pathDb);
				scriviFile(pathDb+nomeFileTicket, "1");
			}else{
				System.out.println(" ********** file "+pathDb+nomeFileTicket + " esistente");
			}
		} catch (FileNotFoundException e) {
			System.out.println(" !!!!!!!! ATTENZIONE: problemi di creazione del file: "+pathDb+nomeFileTicket);
		}
		
	
		try {
			System.out.println(" ########## Gestione creazione file accessi ##########");
			if(!esisteFile(pathDb+nomeFileAccessi)){
				creaFile(pathDb+nomeFileAccessi);
				System.out.println(" ********** ho creato il file "+pathDb+nomeFileAccessi+" che non esisteva nel percorso "+pathDb);
				
			}else{
				System.out.println(" ********** file "+pathDb+nomeFileAccessi + " esistente");
				clearFile(nomeFileAccessi);
				System.out.println(" ********** file "+pathDb+nomeFileAccessi + " e' stato pulito!");
			}
		} catch (FileNotFoundException e) {
			System.out.println(" !!!!!!!! ATTENZIONE: problemi di creazione del file: "+pathDb+nomeFileAccessi);
		}
		
		
		// Fine Blocco per la creazione di file di testo
	}
	
	
	

	//Tools tools = new Tools();

	
	/**
	 * metodo per il client user a disposizione per aprire una segnalazione
	 */
	@Override
	public void apriSegnalazione(String user, String messaggio) throws RemoteException {
		
		String nomeFile = "segnalazioni.txt";
		
		try {
			
			String record = getNewTicketNumber() + "|" + user + "|"  + "aperta" + "| " +messaggio + "|"+ "pending";


			scriviFile(pathDb+nomeFile, record);


		} catch (IOException e) {
			System.out.println("[ATTENZIONE errore nella routine di apertura segnalazione] Problemi con la lettura del file: "+nomeFile);
		}
	}



	/**
	 * metodo che restituisce la lista delle segnalazioni scritte nel file
	 */
	@Override
	public String visualizzaSegnalazioni() throws RemoteException {
		
//		Table t = new Table(5);
		String nomeFile = "segnalazioni.txt";

		try {
			System.out.println("--> Richiamato il metodo di visualizzazione della lista chiamate da parte del client");
			ArrayList<String> arrayListSegnalazioni = leggiFileRitorna(pathDb+nomeFile);

//			String[][] riga = new String[arrayListSegnalazioni.size()][5];
//			for(int i =0; i<arrayListSegnalazioni.size();i++){
//				String[] campi = arrayListSegnalazioni.get(i).split("|");
//				riga[i][0] = campi[0];
//				riga[i][1] = campi[1];
//				riga[i][2] = campi[2];
//				riga[i][3] = campi[3];
//				riga[i][4] = campi[4];
//			}

			//addIntestazioneSegnalazione(t);


			String msg = "";
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				msg	+= arrayListSegnalazioni.get(i) + "\n";
			}

		
			return msg;




		} catch (IOException e) {
			String msg = "[ATTENZIONE nella routine di visualizza segnalazioni] Ho qualche problema di lettura del file "+pathDb+nomeFile + " dal metodo che visualizza le segnalazioni chiuse!";
			System.out.println(msg);
			return msg;

		}


	}

	/**
	 * metodo che restituisce  una segnalazione in particolare
	 */
	@Override
	public String visualizzaSegnalazioni(int numero) throws RemoteException {
//		Tools tools = new Tools();
//		Table t = new Table(5);
		String nomeFile = "segnalazioni.txt";
		int segnalazioneTrovata = -1;
		String record = "";
		ArrayList<String> arrayListSegnalazioni = new ArrayList<String>();
		try {
			System.out.println("--> Richiamato il metodo di visualizzazione della chiamata n° "+ numero + " da parte del client");
			arrayListSegnalazioni = leggiFileRitorna(pathDb+nomeFile);
			String[][] riga = new String[arrayListSegnalazioni.size()][5];
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				String[] campi = arrayListSegnalazioni.get(i).split("\\|");
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

//			addIntestazioneSegnalazione(t);
//			for(int i=0;i<5;i++)
//				t.addCell(riga[segnalazioneTrovata][i]);


		} catch (IOException e) {
			String msg = "[ATTENZIONE nella routine di visualizza segnalazione particolare] Problemi di lettura del file "+ pathDb+nomeFile;
			System.out.println(msg);
			return msg;
		}



		if (segnalazioneTrovata != -1){

//			String render = t.render();
			String msg = "";
			msg = arrayListSegnalazioni.get(segnalazioneTrovata);
			
			return "\nLa segnalazione cercata e' la seguente: " + "\n" + msg + "\n";
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
			ArrayList<String> arrayListSegnalazioni = leggiFileRitorna(pathDb+nomeFile);

			String[][] riga = new String[arrayListSegnalazioni.size()][5];
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				String[] campi = arrayListSegnalazioni.get(i).split("\\|");
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
					listaAggiornata[i] = riga[i][0] + "|" + riga[i][1] + "|" +riga[i][2] + "|" +riga[i][3] + "|" +riga[i][4];

				clearFile(pathDb+nomeFile);
				scriviFile(pathDb+nomeFile, listaAggiornata);
				System.out.println("--> Segnalazione numero "+numero+" aggiornata con il seguente messaggio: "+messaggio);
			}
			else
				System.out.println("[ATTENZIONE] Non riesco a trovare la segnalazione numero "+numero+ " che cerchi!");




		} catch (IOException e) {
			System.out.println("[ATTENZIONE] Ho qualche problema di lettura del file "+pathDb+nomeFile + " dal metodo che chiude le segnalazioni!");
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
			ArrayList<String> arrayListSegnalazioni = leggiFileRitorna(pathDb+nomeFile);

			String[][] riga = new String[arrayListSegnalazioni.size()][5];
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				String[] campi = arrayListSegnalazioni.get(i).split("\\|");
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
					listaAggiornata[i] = riga[i][0] + "|" + riga[i][1] + "|" +riga[i][2] + "|" +riga[i][3] + "|" +riga[i][4];

				clearFile(pathDb+nomeFile);
				scriviFile(pathDb+nomeFile, listaAggiornata);
				System.out.println("--> Segnalazione n° "+numero+" chiusa con successo!");
				return "segnalazione numero "+numero+" chiusa!";
			}
			else
				return "[ATTENZIONE errori nella routine di chiusura segnalazioni] Non riesco a trovare la segnalazione numero "+numero+ " che cerchi!";




		} catch (IOException e) {
			String msg = "[ATTENZIONE] Ho qualche problema di lettura del file "+pathDb+nomeFile + " dal metodo che chiude le segnalazioni!";
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
			int numeroSegnalazioni = leggiFileRitorna(pathDb+nomeFile).size();
			//System.out.println("Numero segnalazioni registrate = "+numeroSegnalazioni);
			return (numeroSegnalazioni);
		} catch (IOException e) {
			System.out.println("[ATTENZIONE errori nel conteggio del numero delle segnalazioni] Ho qualche problema di lettura del file "+pathDb+nomeFile + " dal metodo che conta le segnalazioni!");
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

			ArrayList<String> arrayListSegnalazioni = leggiFileRitorna(pathDb+nomeFile);

			String[][] riga = new String[arrayListSegnalazioni.size()][5];
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				String[] campi = arrayListSegnalazioni.get(i).split("\\|");
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

			System.out.println("[ATTENZIONE] Ho qualche problema di lettura del file "+pathDb+nomeFile + " dal metodo che conta le segnalazioni chiuse!");
			return segnalazioniChiuse;
		}
	}




	/**
	 * metodo che ritorna il prossimo numero ticket
	 * @return
	 */
	public int getNewTicketNumber() {
		//Tools tools = new Tools();
		String nomeFile = "numeroTicket.txt";
		if(esisteFile(pathDb+nomeFile)){
			try {
				ArrayList<String> listaNumeriTicket = leggiFileRitorna(pathDb+nomeFile);
				int numTicket = Integer.parseInt(listaNumeriTicket.get(0))+1;
				clearFile(pathDb+nomeFile);
				scriviFile(pathDb+nomeFile, String.valueOf(numTicket));
				System.out.println("I Ticket sono arrivati al numero: "+numTicket);
				return numTicket;

			} catch (IOException e) {
				System.out.println("[ATTENZIONE errori nella routine di calcolo del numero ticket] Problemi con la lettura del file: "+nomeFile);
			}
		}
		return 0;
	}




	@Override
	public void unreferenced() {
		//Tools tools = new Tools();
		try {
			
			System.out.println(" [GC] Sono dentro il metodo unreferenced del server centrale: " + this + " richiamato il "+getDataOra());
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
	 * metodo che verifica l'esistenza di un file passandone il nome come parametro
	 * @param nomeFile
	 * @return
	 */
	public boolean esisteFile(String nomeFile){
		File file = new File(nomeFile);
		return (file.exists());
	}

	
	/**
	 * metodo per la creazione di un file passado il nome
	 * @param nomeFile
	 * @throws FileNotFoundException 
	 */
	public void creaFile(String nomeFile) throws FileNotFoundException{
		File file = new File(nomeFile);
		
		if (file.exists())
			System.out.println(nomeFile + " esiste gia'");
		else{

			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}
	}
	
    /**
     * metodo che scrive una stringa in un file
     * @param nomeFile
     * @param parola
     * @throws FileNotFoundException
     */
	public void scriviFile(String nomeFile, String parola) throws FileNotFoundException{
		FileOutputStream fos = new FileOutputStream(nomeFile, true);
		PrintWriter scrivi = new PrintWriter(fos);
		scrivi.println(parola);
		scrivi.close();
	}
	
	/**
	 * metodo che copia il contenuto di un ArrayList in un Array di stringhe adeguatamente dimensionato
	 * @param arrayList
	 * @return
	 */
	public String[] listToArray(ArrayList<String> arrayList){
		String[] array = new String[arrayList.size()];
		for(int i=0; i<arrayList.size();i++)
			array[i] = arrayList.get(i);
		
		return array;
	}
	
	/**
	 * pulisce il contenuto di un file dato il nome come parametro
	 * @param nomeFile
	 * @throws FileNotFoundException
	 */
	public static void clearFile(String nomeFile) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter(nomeFile); 
		pw.close();
		
	}
	
	/**
	 * metodo che scrive una array di stringhe su file
	 * @param nomeFile
	 * @param parole
	 * @throws FileNotFoundException
	 */
	public void scriviFile(String nomeFile, String[] parole) throws FileNotFoundException{
		
		FileOutputStream fos = new FileOutputStream(nomeFile, true);
		PrintWriter scrivi = new PrintWriter(fos);
		for (int i=0; i<parole.length; i++)
		  scrivi.println(parole[i]);
		
		scrivi.close();
	}
	
	/**
	 * metodo che ritorna Data e Ora correnti
	 * @return
	 */
	public String getDataOra(){
		Calendar cal = new GregorianCalendar();
		int giorno = cal.get(Calendar.DAY_OF_MONTH);
		int mese = cal.get(Calendar.MONTH)+1;
		int anno = cal.get(Calendar.YEAR);
		String orario;
		int ore = cal.get(Calendar.HOUR);
		int minuti = cal.get(Calendar.MINUTE);
		int secondi = cal.get(Calendar.SECOND);

		if(cal.get(Calendar.AM_PM) == 0)
			orario = "A.M.";
		else
			orario = "P.M.";
		
		String giornoString="";
		String meseString="";
		String oreString="";
		String minutiString="";
		String secondiString="";
		
		if (giorno < 10)
			giornoString = "0"+giorno;
		else
			giornoString= ""+giorno;
		
		if (mese < 10)
			meseString = "0"+mese;
		else
			meseString= ""+mese;
		
		if (ore < 10)
			oreString = "0"+ore;
		else
			oreString= ""+ore;
		
		if (minuti < 10)
			minutiString = "0"+minuti;
		else
			minutiString= ""+minuti;
		
		if (secondi < 10)
			giornoString = "0"+giorno;
		else
			secondiString= ""+secondi;
		
		String dataOra = anno + "/" + meseString + "/" + giornoString + " - " +oreString + ":" + minutiString + ":" + secondiString + " " + orario;
		return dataOra;
	}
	

	/**
	 * metodo che legge il contenuto di un file e lo restituisce in un Array List di stringhe
	 * @param nomeFile
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String>  leggiFileRitorna(String nomeFile) throws IOException{ 
	
		ArrayList<String> arrayLetto = new ArrayList<String>();
		FileReader fr = new FileReader(nomeFile);
		BufferedReader br = new BufferedReader(fr);
		String sCurrentLine;
		while((sCurrentLine = br.readLine()) != null){
		arrayLetto.add(sCurrentLine);
		}
		
		//String[] arrayReturn = listToArray(arrayLetto);
	
		fr.close();
		return arrayLetto; 
	
	}


	/**
	 * mtodo che registra ip e user su file passati dal mobile server
	 * @param ip
	 * @param user
	 */
	public void registraClient(String ip, String user){
		try {
			scriviFile(nomeFileAccessi, user);
			System.out.println("registrato utente: "+user+" da postazione "+ip);
		} catch (FileNotFoundException e) {
			System.out.println("[ATTENZIONE errori sulla registrazione dei utente o ip] Ho qualche problema di lettura del file "+pathDb+nomeFileAccessi + " dal metodo che registra user e ip dei client!");
		}
	}

	/**
	 * metodo che restituisce la lista dei client collegati
	 */
	public String visualizzaClient() throws RemoteException {
		

		try {
			System.out.println("--> Richiamato il metodo di visualizzazione della lista dei client collegati");
			ArrayList<String> arrayListSegnalazioni = leggiFileRitorna(pathDb+nomeFileAccessi);

			String msg = "";
			for(int i =0; i<arrayListSegnalazioni.size();i++){
				msg	+= arrayListSegnalazioni.get(i) + "\n";
			}
		
			return msg;

		} catch (IOException e) {
			String msg = "[ATTENZIONE nella routine di visualizzazione client collegati] Ho qualche problema di lettura del file "+pathDb+nomeFileAccessi + " dal metodo che visualizza i client collegati!";
			System.out.println(msg);
			return msg;
		}
	}
	
	/**
	 * metodo che ritorna il numero dei clients collegati
	 */
	public int getNumeroClients() throws RemoteException {
	
		try {
			int nuemeroClients = leggiFileRitorna(pathDb+nomeFileAccessi).size();
			//System.out.println("Numero segnalazioni registrate = "+numeroSegnalazioni);
			return (nuemeroClients);
		} catch (IOException e) {
			System.out.println("[ATTENZIONE errori nel conteggio del numero dei clients] Ho qualche problema di lettura del file "+pathDb+nomeFileAccessi + " dal metodo che conta i clients!");
			return 0;
		}
	}
	
	public void attivazione() throws RemoteException {
		System.out.println(" ********** Attivazione del Server SD dal metodo interno");
	}
	

	/**
	 * metodo main
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args){
//		Tools tools = new Tools();

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










