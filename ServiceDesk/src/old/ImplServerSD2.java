package old;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.nocrala.tools.texttablefmt.*;

public class ImplServerSD2 extends UnicastRemoteObject implements InterfaceServerSDAdmin,
InterfaceServerSDUser {



	protected ImplServerSD2() throws RemoteException {
		super();

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
				System.out.println("problemi di creazione del file "+nomeFile);
			}
		}


		try {
			String record = getNewTicketNumber() + "," + user + ","  + "aperta" + ", " +messaggio + ","+ "pending";


			tools.scriviFile(nomeFile, record);


		} catch (IOException e) {
			System.out.println("Problemi con la lettura del file: "+nomeFile);
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

			System.out.println("ho qualche problema di lettura del file "+nomeFile + " dal metodo che visualizza le segnalazioni chiuse!");
			return null;

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
			System.out.println("problemi di lettura del file "+nomeFile);
			return "problemi con la ricerca della segnalazione";
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
				System.out.println("segnalazione numero "+numero+" aggiornata!");
			}
			else
				System.out.println("non riesco a trovare la segnalazione numero "+numero+ " che cerchi!");




		} catch (IOException e) {
			System.out.println("ho qualche problema di lettura del file "+nomeFile + " dal metodo che chiude le segnalazioni!");
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
				return "segnalazione numero "+numero+" chiusa!";
			}
			else
				return "non riesco a trovare la segnalazione numero "+numero+ " che cerchi!";




		} catch (IOException e) {
			return "ho qualche problema di lettura del file "+nomeFile + " dal metodo che chiude le segnalazioni!";
		}


	}

	/**
	 * metodo che ritorna il numero delle segnalazioni in totale
	 */
	@Override
	public int getNumeroSegnalazioniTOT() throws RemoteException {
		String nomeFile = "segnalazioni.txt";

		try {
			return (tools.leggiFileRitorna(nomeFile).size());
		} catch (IOException e) {
			System.out.println("ho qualche problema di lettura del file "+nomeFile + " dal metodo che conta le segnalazioni!");
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

			System.out.println("ho qualche problema di lettura del file "+nomeFile + " dal metodo che conta le segnalazioni chiuse!");
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
				return numTicket;

			} catch (IOException e) {
				System.out.println("Problemi con la lettura del file: "+nomeFile);
			}
		}
		return 0;
	}




	public void addIntestazioneSegnalazione(Table t){
		t.addCell("TICKET");
		t.addCell("UTENTE");
		t.addCell("STATO");
		t.addCell("MESSAGGIO");
		t.addCell("SOLUZIONE");
	}


	/**
	 * metodo main
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		Tools tools = new Tools();
		String nomeFileSegnalazioni = "segnalazioni.txt";
		String nomeFileTicket = "numeroTicket.txt";
		
		

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


		
		System.setSecurityManager(new RMISecurityManager());
		ImplServerSD2 ssd = new ImplServerSD2();
		Registry reg = LocateRegistry.createRegistry(3456);
		System.out.println("ho lanciato il seguente registro alla porta 3456: "+reg);
		reg.rebind("ServerSD", ssd);
		System.out.println("ho appena fatto la bind del server centrale");
		try {
			System.out.println("Il server centrale si trova sull'ip: " +InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			System.out.println("errore durante il recupero dell'indirizzo ip!");
		}
		

	}





}
