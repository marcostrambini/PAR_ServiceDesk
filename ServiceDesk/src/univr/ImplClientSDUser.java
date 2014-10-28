package univr;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ImplClientSDUser {

	static InterfaceServerSDUser server = null;




	public static void main(String[] args) throws RemoteException  {
		//		if(System.getSecurityManager()==null)
		//			System.setSecurityManager(new RMISecurityManager());
		try {
			while(true){
		
				
				Registry reg = LocateRegistry.getRegistry(3456);
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				server =(InterfaceServerSDUser)reg.lookup("ServerSD");
				System.out.println("-----------  MENU ------------");
				System.out.println("Apri segnalazione  : [1]");
				System.out.println("Registrazione user : [2]");
				System.out.println("scelta: ");
				int scelta = Integer.parseInt(br.readLine());

				switch(scelta){
				case 1: 
					Tools tools = new Tools();
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
