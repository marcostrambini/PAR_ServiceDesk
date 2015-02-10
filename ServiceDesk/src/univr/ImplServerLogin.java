package univr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.MarshalledObject;
import java.rmi.Naming;
import java.rmi.Remote;
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


public class ImplServerLogin extends Activatable implements InterfaceLogin, Unreferenced {

	private Remote SC;
	private Remote MS;
	
	public String pathDb = "/Mago/javarmi/univr/db/";
	String nomeFileUtenti = "utenti.txt";
	
	
	protected ImplServerLogin(ActivationID id, MarshalledObject data) throws RemoteException, ActivationException {
		super(id, 10001);
		
		System.out.println(" ********** SERVICE DESK SERVER LOGIN **********");
		System.out.println(" ********** Sono dentro il costruttore del Server di Login.");
		System.out.println(" ********** E' stato invocato con successo il costruttore della superclasse Activatable, ");
		System.out.println(" ********** passando come parametro l'ActivationID "+id+" del server che verra esportato alla porta "+10001);
		ActivationSystem actS = ActivationGroup.getSystem();
		System.out.println(" ********** La referenza al sistema di attivazione (rmid) e': "+actS);
		ActivationDesc actD = actS.getActivationDesc(id);
		System.out.println(" ********** Ho ricavato l' ActivationDescriptor "+actD+", associato al server attivabile grazie all'ActivationID="+id);
		System.out.println(" ********** Costruttore Server Centrale terminato.");
		
		
		
		try {
			System.out.println(" ########## Gestione creazione file utenti ##########");
			if(!esisteFile(pathDb+nomeFileUtenti)){
				creaFile(pathDb+nomeFileUtenti);
				System.out.println(" ********** ho creato il file "+pathDb+nomeFileUtenti+" che non esisteva nel percorso "+pathDb);
				scriviFile(pathDb+nomeFileUtenti, "admin,admin");
			}else{
				System.out.println(" ********** file "+pathDb+nomeFileUtenti + " esistente");
			}
		} catch (FileNotFoundException e) {
			System.out.println(" !!!!!!!! ATTENZIONE: problemi di creazione del file: "+pathDb+nomeFileUtenti);
		}
		
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * con questo metodo controllo la validita' del login e verifico se Admin o User semplice
	 */
	@Override
	public boolean isValidLogin(String user, String pwd) throws RemoteException {
		
		String nomeFile = "utenti.txt";
		int utenteTrovato = 0;
		try {
			ArrayList<String> arrayListUtenti = leggiFileRitorna(nomeFile);
			String[][] utentiEPass = new String[arrayListUtenti.size()][3];
			for(int i =0; i<arrayListUtenti.size();i++){
				String[] credenziali = arrayListUtenti.get(i).split(",");
				utentiEPass[i][0] = credenziali[0];
				utentiEPass[i][1] = credenziali[1];
				utentiEPass[i][2] = credenziali[2];
			}

			for(int i =0; i<utentiEPass.length;i++){
				if(user.equals(utentiEPass[i][0]) && pwd.equals(utentiEPass[i][1]))
					utenteTrovato++;
			}






		} catch (IOException e) {
			System.out.println("problemi di lettura del file "+nomeFile);
		}



		if(utenteTrovato>=1){
			if(user.equals("admin") && pwd.equals("admin")){
				//ritorna il MA
			}else{
				//ritorna il MS
			}
			
			
			return true;
		}
			
		else
			return false;


	}




	@Override
	public void registrati(String user, String password) throws RemoteException {
		
		String nomeFile = "utenti.txt";
		if(!esisteFile(nomeFile)){
			try {
				creaFile(nomeFile);
			} catch (FileNotFoundException e) {
				System.out.println("non riesco a generare il file utenti.txt");

			}

		}else{

			if(!trovaUtente(user)){
				try {
					scriviFile(nomeFile, (user + ","+password+",user"));

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
							
				}
			}else{
				System.out.println("utente "+user+" gia' presente!");	
			}
		}
	}

	
	/**
	 * metodo che cerca l'utente nel file
	 * @param user
	 * @return
	 */
	private boolean trovaUtente(String user){

		
		String nomeFile = "utenti.txt";
		int utenteTrovato = 0;
		try {
			ArrayList<String> arrayListUtenti = leggiFileRitorna(nomeFile);
			String[][] utentiEPass = new String[arrayListUtenti.size()][3];
			for(int i =0; i<arrayListUtenti.size();i++){
				String[] credenziali = arrayListUtenti.get(i).split(",");
				utentiEPass[i][0] = credenziali[0];
				utentiEPass[i][1] = credenziali[1];
				utentiEPass[i][2] = credenziali[2];
			}

			for(int i =0; i<utentiEPass.length;i++){
				if(user.equals(utentiEPass[i][0]) )
					utenteTrovato++;
			}

		} catch (IOException e) {
			System.out.println("problemi di lettura del file "+nomeFile);
		}

		if(utenteTrovato>=1)
			return true;
		else
			return false;

	}

//	public static void main(String[] args) throws RemoteException{
//		
//		String nomeFile = "utenti.txt";
//		if(!esisteFile(nomeFile))
//			try {
//				creaFile(nomeFile);
//			} catch (FileNotFoundException e) {
//				System.out.println("problemi di creazione del file "+nomeFile+" nel main del server di Login");
//			}

	//	ImplServerLogin sl = new ImplServerLogin();
//		Registry reg = LocateRegistry.createRegistry(2345);
//		System.out.println("ho lanciato il seguente registro alla porta 2345: "+reg);
//		reg.rebind("ServerLogin", sl);
//		System.out.println("ho appena fatto la bind");

//	}

	
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
		
		String[] arrayReturn = listToArray(arrayLetto);

		fr.close();
		return arrayLetto; 

	}

	/**
	 * metodo che verifica l'esistenza di un file passandone il nome come parametro
	 * @param nomeFile
	 * @return
	 */
	public static boolean esisteFile(String nomeFile){
		File file = new File(nomeFile);
		return (file.exists());
	}

	
	/**
	 * metodo per la creazione di un file passado il nome
	 * @param nomeFile
	 * @throws FileNotFoundException 
	 */
	public static void creaFile(String nomeFile) throws FileNotFoundException{
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
	
	public void attivazione() throws RemoteException {
		System.out.println(" ********** Attivazione del Server SD dal metodo interno");
	}
	
	
	@Override
	public MarshalledObject login(String username, String password)
			throws RemoteException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void unreferenced() {
		//Tools tools = new Tools();
		try {
			
			System.out.println(" [GC] Sono dentro il metodo unreferenced del server login: " + this + " richiamato il "+getDataOra());
			System.out.println(" [GC] Ho invocato il metodo inactive per disattivare il server attivabile");
			System.out.println(" [GC] Tale metodo si occupa anche di de-esportare il server");
			
			Naming.unbind("ServerLogin");
            boolean ok = inactive(getID());
			
			System.out.println(" [GC] Il server "+this+" e' inattivo? "+ok);
			System.out.println(" [GC] Sto invocando il garbage collector dentro la JVM del server attivabile");
			System.gc();
			System.out.println(" [GC] Sto uscendo dal metodo unreferenced.");
		}
		catch (Exception e) {System.out.println("errore unreferenced. "+e);}
	}

}
