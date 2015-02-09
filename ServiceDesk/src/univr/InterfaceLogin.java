package univr;

import java.rmi.MarshalledObject;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceLogin extends Remote {
	
	
	public MarshalledObject login(String username, String password) throws RemoteException, ClassNotFoundException;
	public boolean isValidLogin(String user, String pwd) throws RemoteException;
	public void registrati(String nome, String cognome) throws RemoteException;
}
