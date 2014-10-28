package univr;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.nocrala.tools.texttablefmt.Table;

public interface InterfaceServerSDAdmin extends Remote {
	public int getNumeroSegnalazioniTOT() throws RemoteException;
	public int getNumeroSegnalazioniChiuse() throws RemoteException;
	public String visualizzaSegnalazioni() throws RemoteException;
	public String visualizzaSegnalazioni(int numero) throws RemoteException;
	public void rispondiSegnalazione(int numero, String messaggio) throws RemoteException;
	public String chiudiSegnalazione(int numero) throws RemoteException;

	

}
