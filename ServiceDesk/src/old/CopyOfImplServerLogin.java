package old;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.MarshalledObject;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.ArrayList;


public class CopyOfImplServerLogin extends UnicastRemoteObject implements InterfaceLogin, Unreferenced {

	private Remote SC;
	private Remote MS;
	
	
	
	
	protected CopyOfImplServerLogin() throws RemoteException {
		super();
		
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
		Tools tools = new Tools();
		String nomeFile = "utenti.txt";
		int utenteTrovato = 0;
		try {
			ArrayList<String> arrayListUtenti = tools.leggiFileRitorna(nomeFile);
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



		if(utenteTrovato>=1)
			return true;
		else
			return false;


	}




	@Override
	public void registrati(String user, String password) throws RemoteException {
		Tools tools = new Tools();
		String nomeFile = "utenti.txt";
		if(!tools.esisteFile(nomeFile)){
			try {
				tools.creaFile(nomeFile);
			} catch (FileNotFoundException e) {
				System.out.println("non riesco a generare il file utenti.txt");

			}

		}else{

			if(!trovaUtente(user)){
				try {
					tools.scriviFile(nomeFile, (user + ","+password+",user"));

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
							
				}
			}else{
				System.out.println("utente "+user+" gia' presente!");	
			}
		}
	}

	private boolean trovaUtente(String user){

		Tools tools = new Tools();
		String nomeFile = "utenti.txt";
		int utenteTrovato = 0;
		try {
			ArrayList<String> arrayListUtenti = tools.leggiFileRitorna(nomeFile);
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

	public static void main(String[] args) throws RemoteException{
		Tools tools = new Tools();
		String nomeFile = "utenti.txt";
		if(!tools.esisteFile(nomeFile))
			try {
				tools.creaFile(nomeFile);
			} catch (FileNotFoundException e) {
				System.out.println("problemi di creazione del file "+nomeFile+" nel main del server di Login");
			}

		CopyOfImplServerLogin sl = new CopyOfImplServerLogin();
		Registry reg = LocateRegistry.createRegistry(2345);
		System.out.println("ho lanciato il seguente registro alla porta 2345: "+reg);
		reg.rebind("ServerLogin", sl);
		System.out.println("ho appena fatto la bind");

	}




	@Override
	public MarshalledObject login(String username, String password)
			throws RemoteException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void unreferenced() {
		// TODO Auto-generated method stub
		
	}

}
