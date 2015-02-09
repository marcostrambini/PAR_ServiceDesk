package univr;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public interface InterfaceServerSDAdmin extends Serializable,Remote {
	public int getNumeroSegnalazioniTOT() throws RemoteException;
	public int getNumeroClients() throws RemoteException ;
	public String visualizzaClient() throws RemoteException;
	public void attivazione() throws RemoteException;
	public int getNumeroSegnalazioniChiuse() throws RemoteException;
	public String visualizzaSegnalazioni() throws RemoteException;
	public String visualizzaSegnalazioni(int numero) throws RemoteException;
	public void rispondiSegnalazione(int numero, String messaggio) throws RemoteException;
	public String chiudiSegnalazione(int numero) throws RemoteException;
	public void apriSegnalazione(String user, String messaggio) throws RemoteException;
	
	

}
