package univr;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServerSDUser extends Remote {
	public void apriSegnalazione(String user, String messaggio) throws RemoteException;
//	public int getNewTicketNumber() throws RemoteException;

}
