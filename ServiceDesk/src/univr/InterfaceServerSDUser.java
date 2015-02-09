package univr;

import java.io.Serializable;
import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServerSDUser extends Serializable, Remote {
	public void apriSegnalazione(String user, String messaggio) throws RemoteException;
	public void registraAccesso(String ip, String nome) throws RemoteException;
	public void cancellaAccesso(String ip, String nome) throws RemoteException;
//	public int getNewTicketNumber() throws RemoteException;

}
