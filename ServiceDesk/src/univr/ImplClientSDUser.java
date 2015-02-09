package univr;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Properties;

public class ImplClientSDUser implements Serializable{

	//static InterfaceServerSDUser server = null;
	static InterfaceServerSDAdmin server = null;



	public static void main(String[] args) throws RemoteException  {
		
		String hostname="";

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		if(System.getSecurityManager()==null)
					System.setSecurityManager(new RMISecurityManager());
		
		try {
			
		System.out.println("Dove si trova in esecuzione il server centrale? [1]=localhost [2]=remoto");
		if (br.readLine().equals("1"))
			hostname = "localhost";
		else{
			System.out.println("Inserisci l'hostname o l'indirizzo ip del server: ");
			hostname = br.readLine();
			
		}
		
		
		
		
			while(true){
		
				
				Registry reg = LocateRegistry.getRegistry(hostname, 1099);
//				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				//server =(InterfaceServerSDUser)reg.lookup("ServerSD");
				server =(InterfaceServerSDAdmin)reg.lookup("ServerSD");
				System.out.println("-----------  MENU ------------");
				System.out.println("Apri segnalazione  : [1]");
				System.out.println("Registrazione user : [2]");
				System.out.println("scelta: ");
				int scelta = Integer.parseInt(br.readLine());

				switch(scelta){
				case 1: 
					//Tools tools = new Tools();
					System.out.println("nome utente: ");
					String user = br.readLine();
					System.out.println("inserisci il tipo di problema");
					String messaggio = br.readLine();
					server.apriSegnalazione(user, messaggio);
					
					
				case 2:
				
					break;

				default: break;
				}






			}

		} catch (NotBoundException | IOException e) {

			e.printStackTrace();
		}



	}

}
