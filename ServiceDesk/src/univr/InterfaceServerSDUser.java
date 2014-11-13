package univr;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServerSDUser extends Serializable, Remote {
	public void apriSegnalazione(String user, String messaggio) throws RemoteException;
//	public int getNewTicketNumber() throws RemoteException;

}
