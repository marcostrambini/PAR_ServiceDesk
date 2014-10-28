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



public class ImplClientSDAmin {

	static InterfaceServerSDAdmin server = null;




	public static void main(String[] args) throws RemoteException  {
		//		if(System.getSecurityManager()==null)
		//			System.setSecurityManager(new RMISecurityManager());
		try {
			while(true){
		
				
				Registry reg = LocateRegistry.getRegistry(3456);
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				server =(InterfaceServerSDAdmin)reg.lookup("ServerSD");
	
				
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
					
					
			
				
			
					
					
				default: break;
				}






			}

		} catch (NotBoundException | IOException e) {

			e.printStackTrace();
		}



	}

}
