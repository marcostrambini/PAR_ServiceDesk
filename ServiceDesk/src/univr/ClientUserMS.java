package univr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import old.ImplServerSD;
import old.Tools;

public class ClientUserMS extends UnicastRemoteObject  {
	
	
	
	

	protected ClientUserMS() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;





	public static void main(String[] args) throws RemoteException {
		
		ClientUserMS cliUser = new ClientUserMS();
		Registry reg = LocateRegistry.createRegistry(10000);
		System.out.println("ho lanciato il seguente registro alla porta 10000: "+reg);
		reg.rebind("ClientUserMS", cliUser);
		System.out.println("ho appena fatto la bind del mobile server");
		try {
			System.out.println("Il mobile server si trova sull'ip: " +InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			System.out.println("errore durante il recupero dell'indirizzo ip!");
		}
		
		
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
			String location = "rmi://"+hostname+"/ServerSD";
			
			InterfaceServerSDUser server = (InterfaceServerSDUser)Naming.lookup(location);
		
			System.out.println("inserisci il nome utente: ");
			String nome = br.readLine();
			String ip = InetAddress.getLocalHost().getHostAddress();
			server.registraAccesso(ip, nome);
			
			while(true){
									
//				server =(InterfaceServerSDAdmin)Naming.lookup(location);
				
				System.out.println("\n| -----------  MENU Client ---------- |");
		                                  
				System.out.println("| Funzioni:                                 |");
				System.out.println("| Apri una segnalazione			      : [1] |");
				System.out.println("| Esci dal programma                  : [2] |");
				
				System.out.println("| scelta: ");
				int scelta = Integer.parseInt(br.readLine());

				int ticket =0;
				switch(scelta){
				
				case 1: 
					
					System.out.println("Snserisci la tua segnalazione: ");
					String segnalazione = br.readLine();
					server.apriSegnalazione(nome, segnalazione);
					System.out.println("Segnalazione inserita");
					break;
								
				case 2:
					server.cancellaAccesso(ip, nome);
					System.out.println("Arrivederci");
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
