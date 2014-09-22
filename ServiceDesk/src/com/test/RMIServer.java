package com.test;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.interf.test.Costant;
public class RMIServer {

	

	public static void main(String[] args) throws RemoteException, 
											AlreadyBoundException{
		
//		try {
//			Process p = Runtime.getRuntime().exec("ps -ef | grep rmiregistry | awk '{ print $2 }' | kill -9");
//		} catch (IOException e) {
//			System.out.println("problemi con rmiregistry");
//		}
		
		
		RemoteImpl impl = new RemoteImpl();
		
//		try {
//			System.out.println("Provo a fare UNBIND");
//			registry.unbind(Costant.RMI_ID);
//			System.out.println("esito ok");
//		} catch (NotBoundException e) {
//			System.out.println("esito unbind negativo");
//			e.printStackTrace();
//		}
		Registry registry = LocateRegistry.createRegistry(Costant.RMI_PORT);
		registry.bind(Costant.RMI_ID, impl);
		System.out.println(registry.list());
		System.out.println("Server started...");
		
//		UnicastRemoteObject.unexportObject(registry,true); 
		
	}
}
