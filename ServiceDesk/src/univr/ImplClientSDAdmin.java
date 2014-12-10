package univr;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Properties;



public class ImplClientSDAdmin {

	static InterfaceServerSDAdmin server = null;




	public static void main(String[] args) throws RemoteException  {
		//		if(System.getSecurityManager()==null)
		//			System.setSecurityManager(new RMISecurityManager());
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String hostname = "";
		
		if(System.getSecurityManager()==null){
			System.setSecurityManager(new RMISecurityManager());
		}
		
	    	
		
		
		try {
		
			System.out.println("Dove si trova in esecuzione il server centrale? [1]=localhost [2]=remoto");
			if (br.readLine().equals("1"))
				hostname = "localhost";
			else{
				System.out.println("Inserisci l'hostname o l'indirizzo ip del server: ");
				hostname = br.readLine();
				
			}
			
		
		
		
		
			System.out.println("Provo a contattare il server "+hostname);
			
			Registry reg = LocateRegistry.getRegistry(hostname, 1098);
			
//			//se unicast
//			server =(InterfaceServerSDAdmin)reg.lookup("ServerSD");


			//se Activatable

			String location = "rmi://"+hostname+"/ServerSD";
			
			InterfaceServerSDAdmin server = (InterfaceServerSDAdmin)Naming.lookup(location);
		
			while(true){
		
				
			

				
				
//				server =(InterfaceServerSDAdmin)Naming.lookup(location);
				
				System.out.println("\n| -----------  MENU Service Desk ---------- |");
				System.out.println("| Riepiloghi:                               |");
				System.out.println("| Totale segnalazioni registrate......: ("+server.getNumeroSegnalazioniTOT()+") |");
				System.out.println("| Totale segnalazioni chiuse..........: ("+server.getNumeroSegnalazioniChiuse()+") |");
				System.out.println("| Totale segnalazioni ancora aperte...: ("+(server.getNumeroSegnalazioniTOT() -server.getNumeroSegnalazioniChiuse())+") |");
				System.out.println("|                                           |");
				System.out.println("| Funzioni:                                 |");
				System.out.println("| Visualizza tutte le segnalazioni    : [1] |");
				System.out.println("| Visualizza segnalazione particolare : [2] |");
				System.out.println("| Rispondi a segnalazione particolare : [3] |");
				System.out.println("| Chiudi una segnalazione particolare : [4] |");
				System.out.println("| Esci dal programma                  : [5] |");
				
				System.out.println("| scelta: ");
				int scelta = Integer.parseInt(br.readLine());

				int ticket =0;
				switch(scelta){
				
				case 1: 
					
					System.out.println(server.visualizzaSegnalazioni());
								
				
					break;
					
					
				case 2:
					System.out.println("Inserisci il numero della segnalazione che vuoi cercare: ");
				    ticket = Integer.parseInt(br.readLine());
					System.out.println(server.visualizzaSegnalazioni(ticket));
					ticket=0;
					break;

				
				case 3:
					System.out.println("Inserisci il numero della segnalazione che vuoi aggiornare: ");
				    ticket = Integer.parseInt(br.readLine());
					System.out.println(server.visualizzaSegnalazioni(ticket));
					System.out.println("inserisci il messaggio che vuoi lasciare: ");
					String messaggio = br.readLine();
					System.out.println("aggiorno la segnalazione "+ticket+" ...");
					server.rispondiSegnalazione(ticket, messaggio);
					ticket=0;
					break;
				
				
				
				
				case 4:
					System.out.println("Inserisci il numero della segnalazione che vuoi chiudere: ");
					ticket = Integer.parseInt(br.readLine());
					System.out.println(server.chiudiSegnalazione(ticket));
					ticket=0;
					break;
					
				case 5:
					System.exit(0);
					break;
			
				
			
					
					
				default: break;
				}






			}

		} catch (NotBoundException | IOException e) {

			e.printStackTrace();
		}



	}

}
