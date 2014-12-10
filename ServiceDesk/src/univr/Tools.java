package univr;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.ObjectInputStream.GetField;
//import java.io.Reader;
import java.net.InetAddress;
//import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.JTable;




/**
 * Classe di utilita'
 * @author marcostrambini
 *
 */
public class Tools implements Serializable{

	public static ArrayList<String> repoString = new ArrayList<String>();
	
	
	/**
	 * metodo che esegue un ping passando come parametro indirizzo ip
	 * @param ipAddress
	 * @return
	 * @throws IOException
	 */
	public boolean ping(String ipAddress) throws IOException{
		
	    InetAddress inet = InetAddress.getByName(ipAddress);
	    return inet.isReachable(3000);
	}
	
	/**
	 * metodo che esegue un ping passando come parametro indirizzo ip e timeout
	 * @param ipAddress
	 * @param time
	 * @return
	 * @throws IOException
	 */
	public boolean ping(String ipAddress, int time) throws IOException{
		
	    InetAddress inet = InetAddress.getByName(ipAddress);
	    return inet.isReachable(time);
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
	 * metodo che legge un file e ne stampa il contenuto riga per riga nel terminale
	 * @param nomeFile
	 * @throws IOException
	 */
	public void leggiFile(String nomeFile) throws IOException{
		FileReader fr = new FileReader(nomeFile);
        BufferedReader br = new BufferedReader(fr);
        String sCurrentLine;
        while((sCurrentLine = br.readLine()) != null){
        	System.out.println(sCurrentLine);
        }
		fr.close();
			
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
	 * metodo che legge il contenuto di un file e lo restituisce in un Array List di stringhe
	 * @param nomeFile
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String>  leggiFileRitorna(String nomeFile) throws IOException{ 
//	public String[] leggiFileRitorna(String nomeFile) throws IOException{
		ArrayList<String> arrayLetto = new ArrayList<String>();
		FileReader fr = new FileReader(nomeFile);
		BufferedReader br = new BufferedReader(fr);
		String sCurrentLine;
		while((sCurrentLine = br.readLine()) != null){
		arrayLetto.add(sCurrentLine);
		}
		
		String[] arrayReturn = listToArray(arrayLetto);
//		arrayLetto.add("FINE");
		fr.close();
		return arrayLetto; 
//		return arrayReturn;
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
	 * cancella un file dato il nome come parametro
	 * @param nomeFile
	 */
	public void deleteFile(String nomeFile){
		File fileDelete = new File(nomeFile);
		fileDelete.delete();
	}
	
	/**
	 * metodo che genera il file di configurazione config.ini
	 * i parametri sono verificate in fase di inserimento tramite il PannelloInserimentoConfig
	 * @param listaParametri
	 * @throws IOException
	 */
	public void generaConfigIni(String[] listaParametri) throws IOException{
		String nomeFile = "config.ini";
		
		creaFile(nomeFile);
		clearFile(nomeFile);
		
		scriviFile(nomeFile, "**************************");
		scriviFile(nomeFile, "* File di configurazione *");
		scriviFile(nomeFile, "**************************");
		scriviFile(nomeFile, "* ip address start");
		if (listaParametri[0] != null)
			scriviFile(nomeFile, "ipStart = "+listaParametri[0]);
		else
			scriviFile(nomeFile, "ipStart = 0.0.0.0");	
		
		scriviFile(nomeFile, "* ip address end");
		if (listaParametri[1] != null)
		scriviFile(nomeFile, "ipEnd = "+listaParametri[1]);
		else
			scriviFile(nomeFile, "ipEnd = 0.0.0.0");	
		scriviFile(nomeFile, "* subnet mask");
		
		if (listaParametri[2] != null)
		scriviFile(nomeFile, "mask = "+listaParametri[2]);
		else
			scriviFile(nomeFile, "mask = 0.0.0.0");	
		scriviFile(nomeFile, "* gateway");
		
		if (listaParametri[3] != null)
		scriviFile(nomeFile, "gateway = "+listaParametri[3]);
		else
			scriviFile(nomeFile, "gateway = 0.0.0.0");	
		scriviFile(nomeFile, "* ip db");
		
		if (listaParametri[4] != null)
		scriviFile(nomeFile, "ipDb = "+listaParametri[4]);
		else
			scriviFile(nomeFile, "ipDb = 0.0.0.0");	
		scriviFile(nomeFile, "* Nome db");
		
		if (listaParametri[5] != null)
		scriviFile(nomeFile, "nomeDb = "+listaParametri[5]);
		else
			scriviFile(nomeFile, "nomeDb = no_name");	
		scriviFile(nomeFile, "* user Db");
		
		if (listaParametri[6] != null)
		scriviFile(nomeFile, "userDb = "+listaParametri[6]);
		else
			scriviFile(nomeFile, "userDb = no_name");	
		scriviFile(nomeFile, "* pwd db");
		
		if (listaParametri[7] != null)
		scriviFile(nomeFile, "pwdDb = "+listaParametri[7]);
		else
			scriviFile(nomeFile, "pwdDb = no_name");	
		
	}
	
	
	/**
	 * metodo che verifica che la lunghezza della stringa ipAddress sia da 7 a 15 caratteri
	 * @param ipAddress
	 * @return
	 */
	private boolean verificoLughezzaIp(String ipAddress){
		
		if (ipAddress.length()>=7 && ipAddress.length()<=15 )
			return true;
		else 
			return false;
	}
	
	/**
	 * metodo che verifica che ci siano 3 puntini nella stringa ipAddress
	 * @param ipAddress
	 * @return
	 */
	private boolean verificoPuntiniIp(String ipAddress){
		int contaPuntiniCharAt =0;
		for (int i =0; i<ipAddress.length(); i++){
			if (ipAddress.charAt(i) == '.'){
				contaPuntiniCharAt++;
			}
		}
		if (contaPuntiniCharAt == 3)
			return true;
		else
			return false;
	}
	
	/**
	 * metodo che verifica la consistenza di ogni parametro che compone l'ip minimo 0, max 255
	 * @param ipAddress
	 * @return
	 */
	private boolean verificoConsistenzaValorParametriIp(String ipAddress){
		String test;
		

		
		test = ipAddress.substring(0, ipAddress.indexOf('.'));
//		System.out.println(test);
		if (verificoInt255(test)){
			
			ipAddress = ipAddress.substring(ipAddress.indexOf('.')+1);
			test = ipAddress.substring(0, ipAddress.indexOf('.'));
//			System.out.println(test);
			if (verificoInt255(test)){
			
				ipAddress = ipAddress.substring(ipAddress.indexOf('.')+1);
				test = ipAddress.substring(0, ipAddress.indexOf('.'));
//				System.out.println(test);
				if (verificoInt255(test)){
					ipAddress = ipAddress.substring(ipAddress.indexOf('.')+1);
//					System.out.println(ipAddress);
					return verificoInt255(ipAddress);
					}else
						return false;
			}else
				return false;
		}else
			return false;
				

	}
	
	/**
	 * metodo che verifica se una stringa trasformata in un numero sia compresa tra 0 e 255
	 * @param valore
	 * @return
	 */
	private boolean verificoInt255(String valore){
		if (Integer.parseInt(valore) >=0 && Integer.parseInt(valore) <= 255){
//			System.out.println("vero");
			return true;
		}else{
//			System.out.println("falso");
			return false;
		}
	}
	
	/**
	 * metodo che verifica la consistenza di un indirizzo ip
	 * @param ipAddress
	 * @return
	 */
	public boolean checkIp(String ipAddress){
		if (verificoLughezzaIp(ipAddress)){
//			System.out.println("lunghezza ok");
			if(verificoPuntiniIp(ipAddress)){
//				System.out.println("puntini ok");
				if (verificoConsistenzaValorParametriIp(ipAddress)){
//					System.out.println("consistenza parametri");
					return true;
			}else
					return false;
			}else	
				return false;
			}else
			return false;
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
	 * Funzione che ritorna la tabella degli ip classificati
	 * associate
	 * @return 
	 * @throws IOException 
	 */
	public String[][] getTabellaClassificazione() throws IOException 	{
		String nomeFile = "classificazione.txt";
		
		ArrayList<String> listaClassificazione;
		String[] arrayClassificazione;

		listaClassificazione = leggiFileRitorna(nomeFile);
		arrayClassificazione = listToArray(listaClassificazione); 
		
		String[][] tabellaConfigurazione = new String[arrayClassificazione.length][3];
		
				for (int r = 0; r < arrayClassificazione.length ; r++){
					StringTokenizer st = new StringTokenizer(arrayClassificazione[r]);
					tabellaConfigurazione[r][0] = st.nextToken();	
					st.nextToken();
					tabellaConfigurazione[r][1] = st.nextToken();
					st.nextToken();
					tabellaConfigurazione[r][2] = st.nextToken();
				}
				return tabellaConfigurazione;
		}
	
	
	/**
	 * Funzione che ritorna in forma tabellare il contenuto di un file opportunamente formattato
	 * @return 
	 * @throws IOException 
	 */
	public String[][] getTabellaDaFile(String nomeFile) throws IOException 	{

		if (!esisteFile(nomeFile)){
			System.out.println("il file non esiste");
			return null;
		} else {


			ArrayList<String> listaRigheFile;
			String[] arrayRigheFile;

			listaRigheFile = leggiFileRitorna(nomeFile);
			arrayRigheFile = listToArray(listaRigheFile); 
			int nColonne = 0;

			try{
				StringTokenizer st = new StringTokenizer(arrayRigheFile[0]);
				for(int i=1; i<10; i++){
					st.nextToken();
					nColonne = i;

				}

			}catch(Exception e){

			};

//			System.out.println("N Colonne = "+nColonne);

			String[][] tabellaFile = new String[arrayRigheFile.length][nColonne];

			for (int r = 0; r < arrayRigheFile.length ; r++){
				StringTokenizer st = new StringTokenizer(arrayRigheFile[r]);
				for(int c=0; c<nColonne; c++){
					tabellaFile[r][c] = st.nextToken();	

				}


			}
			return tabellaFile;		
		}

	}
	
	/**
	 * Funzione che ritorna il numero delle colonne di un file
	 * @return 
	 * @throws IOException 
	 */
	public int getColoneTabellaDaFile(String nomeFile) throws IOException 	{

		if (!esisteFile(nomeFile)){
			System.out.println("il file non esiste");
			return 0;
		} else {


			ArrayList<String> listaRigheFile;
			String[] arrayRigheFile;

			listaRigheFile = leggiFileRitorna(nomeFile);
			arrayRigheFile = listToArray(listaRigheFile); 
			int nColonne = 0;

			try{
				StringTokenizer st = new StringTokenizer(arrayRigheFile[0]);
				for(int i=1; i<10; i++){
					st.nextToken();
					nColonne = i;

				}

			}catch(Exception e){

			};

//			System.out.println("N Colonne = "+nColonne);

	
			return nColonne;

			}
		}
	
	
	
	/**
	 * metodo che ritorna la tabella degli ip classificati in base ad un filtro
	 * @param filtro
	 * @param tabella
	 * @return
	 */
	public String[][] getTabellaFiltrata(String filtro, String[][] tabella){
//		Tools tools = new Tools();
		
		

		ArrayList<ArrayList<String>> listTemp = new ArrayList<ArrayList<String>>();
		
		String[][] tabellaFiltrata = new String[tabella.length][3];
		int pos =0;
		
		for(int i =0; i<tabella.length; i++){
//			
			 if (tabella[i][1].equals(filtro)){
	
				
				tabellaFiltrata[pos][0] = tabella[i][0];
//				System.out.print(tabellaFiltrata[pos][0]+" ");
				tabellaFiltrata[pos][1] = tabella[i][1];
//				System.out.print(tabellaFiltrata[pos][1]+" ");
				tabellaFiltrata[pos][2] = tabella[i][2];
//				System.out.println(tabellaFiltrata[pos][2]);
			
				pos++;
		}
			
		}
		
		String[][] tabellaFiltrataPulita = new String[pos][3];
		
		for(int i=0; i<pos;i++){
			if (tabellaFiltrata[i][1].equals(filtro)){
			tabellaFiltrataPulita[i][0] = tabellaFiltrata[i][0];
			tabellaFiltrataPulita[i][1] = tabellaFiltrata[i][1];
			tabellaFiltrataPulita[i][2] = tabellaFiltrata[i][2];
			}
		}
	

		
		
		System.out.println("righe: "+pos);
		return tabellaFiltrataPulita;
				
	}
	
	
	public JTable getJTableOfGroups(String[][] tabella){
		String[] campi = {"Indirizzo IP", "Descrizione"};
		JTable table = new JTable(tabella, campi);
		table.setPreferredScrollableViewportSize(new Dimension(500, 500));
		return table;
	}
	
	

	
	/**
	 * metodo che ritorna il nome del gruppo ad una posizione data come parametro
	 * @param posizione
	 * @return
	 */
	public String getGruppoAllaPosizione(int posizione){
		ArrayList<String> listaGruppi = null;
		
		try {
			listaGruppi = leggiFileRitorna("groups.ini");
		} catch (IOException e) {
			System.out.println("problemi di lettura del file groups.ini");
		}

		if(posizione <= listaGruppi.size())
			return listaGruppi.get(posizione);
		else{
			System.out.println("posizione richiesta non valida");
			return null;
		}
	}	
	
	/**
	 * metodo che ritorna la posizione di un determinato gruppo
	 * @param gruppo
	 * @return
	 */
	public int getPosizioneDelGruppo(String gruppo){
		ArrayList<String> listaGruppi = null;

		try {
			listaGruppi = leggiFileRitorna("groups.ini");
		} catch (IOException e) {
			System.out.println("problemi di lettura del file groups.ini");
		}

		int pos = 0;
		for(int i=0; i<listaGruppi.size();i++){
			if(listaGruppi.get(i).equals(gruppo))
				pos = i;
		}
		
		return pos;
		
	}
	
	
	
	/**
	 * metodo che stampa a console il contenuto di un array di stringhe
	 * @param arrayString
	 */
	public void stampaSuConsole(String[] arrayString){
		
		for(int i=0; i<arrayString.length; i++)
			System.out.println(arrayString[i]);
		
		System.out.println("Elementi nell'array: "+arrayString.length);
		
	}

	/**
	 * metodo che stampa a console il contenuto di un ArrayList di stringhe
	 * @param arrayList
	 */
	public void stampaSuConsole(ArrayList<String> arrayList){
		
		for(int i=0; i<arrayList.size(); i++)
			System.out.println(arrayList.get(i));
		
		System.out.println("Elementi nell'arrayList: "+arrayList.size());
	}

	/**
	 * metodo che stampa a console il contenuto di una tabella a 3 colonne
	 * @param arrayList
	 */
	public void stampaSuConsole(String[][] tabella){
		
		for(int r=0; r<tabella.length; r++)
			System.out.println(tabella[r][0] + " - " + tabella[r][1] + " - " + tabella[r][2]);
		System.out.println("Record in tabella: "+tabella.length);
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
	 * metodo che ritorna il codice di stato
	 * @param code
	 * @param ipAddress
	 * @return
	 */
	public String getStatusCode(int code, String ipAddress){
		switch(code){
		case 1: return "cod01 - Dispositivo " + ipAddress + " online alle: " + getDataOra(); 
		case 2: return "cod02 - Dispositivo " + ipAddress + " offline alle: " + getDataOra();
		default: return "";
		}
	}
	
	/**
	 * metodo che scrive sul file di logStatus
	 * @param messaggio
	 */
	public void scriviLogStatus(String messaggio){
		String nomeFile = "logStatus.txt";

		if(!esisteFile(nomeFile)){
			try {
				creaFile(nomeFile);
			} catch (FileNotFoundException e) {
				System.out.println("problemi di creazione file: "+nomeFile);
			}
		}

		try {
			scriviFile(nomeFile, messaggio);
		} catch (FileNotFoundException e) {
			System.out.println("problemi di scrittura su file: "+nomeFile);
		}
	}
	
	/**
	 * metodo che pulisce il file di log
	 * @throws FileNotFoundException
	 */
	public static void clearLogStatus() throws FileNotFoundException{
		String nomeFile = "logStatus.txt";
		clearFile(nomeFile);
	}
	
	
	
	public static void main(String[] args) throws IOException{
		
		Tools tools = new Tools();
		String nomeFile = "flags.txt";
		tools.creaFile(nomeFile);
		String[][] test = tools.getTabellaDaFile(nomeFile);
		
		for(int r=0; r<test.length; r++){
			for(int c=0; c<tools.getColoneTabellaDaFile(nomeFile); c++){
				System.out.print(test[r][c]+" ");
			}
		System.out.println();
	    }
	}
	
}
