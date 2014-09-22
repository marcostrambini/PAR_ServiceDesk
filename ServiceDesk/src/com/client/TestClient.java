package com.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.interf.test.Costant;
import com.interf.test.TestRemote;

public class TestClient {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("192.168.0.17",Costant.RMI_PORT);
		TestRemote remote = (TestRemote) registry.lookup(Costant.RMI_ID);
		System.out.println(remote.isLoginValid("pippo"));
		System.out.println(remote.isLoginValid("test"));
	}

}
