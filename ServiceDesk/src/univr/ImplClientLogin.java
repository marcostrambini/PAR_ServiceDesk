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

public class ImplClientLogin {

	static InterfaceLogin server = null;




	public static void main(String[] args) throws RemoteException  {
		//		if(System.getSecurityManager()==null)
		//			System.setSecurityManager(new RMISecurityManager());
		try {
			while(true){
		
				
				Registry reg = LocateRegistry.getRegistry(2345);
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				server =(InterfaceLogin)reg.lookup("ServerLogin");
				System.out.println("-----------  MENU ------------");
				System.out.println("Effettuare il login: [1]");
				System.out.println("Registrazione user : [2]");
				System.out.println("scelta: ");
				int scelta = Integer.parseInt(br.readLine());

				switch(scelta){
				case 1: 
					System.out.println("Login utente: ");
					System.out.print("user: ");
					String user = br.readLine();
					System.out.print("pass: ");
					String password = br.readLine();	
					System.out.println("provo il login con user="+user+"  e pass =" +password + " , risultato->"+server.isValidLogin(user, password));
					if(server.isValidLogin(user, password))	
						System.out.println("Bene "+user+" la tua login e' corretta");
					else
						System.out.println("login incorretta o manca registrazione");
					break;
				case 2:
					System.out.println("Registazione utente: ");
					System.out.print("user: ");
					String userR = br.readLine();
					System.out.print("pass: ");
					String passwordR = br.readLine();	
									
					server.registrati(userR, passwordR);
					System.out.println("utente "+ userR + " registrato");
					break;

				default: break;
				}






			}

		} catch (NotBoundException | IOException e) {

			e.printStackTrace();
		}



	}

}
