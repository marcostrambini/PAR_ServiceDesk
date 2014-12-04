package univr;

import java.io.*;
import java.rmi.RMISecurityManager;
import java.rmi.server.RMIClassLoader;


/**
 * Client minimale che si collega a un codebase pubblico tramite protocollo IIOP
  e si scarica la classe del client
 */
public class MinAdmin {

	/**
	 * Variabile che memorizza il codebase
	 *
	 */
	static String codbase = new String();
	/**
	 * Variabile che memorizza la classe da scaricare dal codebase
	 *
	 */
	static final String clientClass = "univr.ImplClientSDAdmin";
	/**


/**
	 *	Main , recupera all'avvio il codice dal codebase
	 */

	public static void main(String[] args) throws Exception {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String hostname = "";



	

			System.out.println("Dove si trova in esecuzione il server centrale? [1]=localhost [2]=remoto");
			if (br.readLine().equals("1"))
				hostname = "localhost";
			else{
				System.out.println("Insirisci l'hostname o l'indirizzo ip del server: ");
				hostname = br.readLine();

			}





			System.out.println("Provo a contattare il server "+hostname);


			codbase = "http://"+hostname+"/client/";

			if(System.getSecurityManager()==null){
				System.setSecurityManager(new RMISecurityManager());
			}
			
			Class classClient = null;
			Runnable client = null;
			
			try{
				classClient = RMIClassLoader.loadClass(codbase, clientClass);
				client = (Runnable) classClient.newInstance();
			}catch (ClassNotFoundException e){System.out.println("Impossibile contattare il sistema, potrebbe non essere attivo oppure si  contattato l'host errato. ");}
			client.run();
		}
	}
