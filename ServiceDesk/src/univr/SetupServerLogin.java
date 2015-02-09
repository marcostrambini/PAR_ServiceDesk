package univr;




import java.rmi.RMISecurityManager;
import java.io.*;
import java.rmi.activation.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import java.net.MalformedURLException;
import java.rmi.MarshalledObject;
import java.rmi.Naming;
import java.rmi.RemoteException;



import java.io.OutputStream;
import java.rmi.*;
import java.net.*;

/**
 * Codice di inizializzazione del sistema di server.
 *
 *
 */
final class SetupServerLogin {

	
       

    	public static  String pathDb = "/Mago/javarmi/univr/db/";
    	static String nomeFileStub = "stubLogin";

	/**
	 * metodo main che inizializza il sistema.
	 *
	 *
	 */

        public static void main(String args[]) {

		
                String policyGroup = System.getProperty("activation.policy");
                String implCodebase = System.getProperty("activation.impl.codebase");
                String classNameImpl = System.getProperty("activation.classeserver");
                String rmicodebase = System.getProperty("java.rmi.server.codebase");
                
                System.out.println("Sono il codice di inizializzazione dei server.");
             
                if(System.getSecurityManager()==null){
        			System.setSecurityManager(new RMISecurityManager());
        		}
              
		try {	
			
			System.out.println("Carico le proprieta': ");	
        	//MarshalledObject stubmar=null;
            Properties prop = new Properties();
            
            System.out.println("java.security.policy = "+policyGroup);
        	prop.put("java.security.policy", policyGroup);
        	
        	System.out.println("activation.impl.codebase = "+implCodebase);
     		prop.put("activation.impl.codebase", implCodebase);
     		
     		System.out.println("java.class.path = "+"no_classpath");
     		prop.put("java.class.path", "no_classpath");
     		
     		System.out.println("java.rmi.dgc.leaseValue = 600000");
     		prop.put("java.rmi.dgc.leaseValue", 600000);
     		
     		  System.out.println("java.rmi.server.codebase = "+rmicodebase);
          	prop.put("java.rmi.server.codebase", rmicodebase);
			
			
			
			
			
//			MarshalledObject stubmar = null;
//			InterfaceServerSDAdmin stubCentrale =null;
//			//FileInputStream file = new FileInputStream(pathDb+nomeFileStub);
//			FileInputStream file = new FileInputStream("univr/db");
//			
//			ObjectInputStream oin = new ObjectInputStream(file);
//			Object object = ((MarshalledObject) oin.readObject()).get();
//			stubCentrale = (InterfaceServerSDAdmin)object;
//			stubmar = new MarshalledObject(stubCentrale);
//			System.out.println("Ho ricavato la referenza al server centrale marshalizzata :"+stubmar);
//			


// creazione del gruppo di attivazione server autenticazione attivabile

			System.out.println("Creo il gruppo di attivazione del server Autenticazione.");
			ActivationGroupDesc groupDesc = new ActivationGroupDesc(prop, null);
			System.out.println("Gruppo creato.");
			// registrazione del gruppo di attivazione
			ActivationGroupID groupID = ActivationGroup.getSystem().registerGroup(groupDesc);
			System.out.println("Il gruppo e' stato creato,  registrato col sistema d'attivazione, ed ha identificativo = "+groupID);
			// creazione di un descrittore per il main server
			System.out.println("Creo l'Activation Descriptor.");
			
//			ActivationDesc actDesc = new ActivationDesc(groupID, classNameImpl, implCodebase, stubmar);
			ActivationDesc actDesc = new ActivationDesc(groupID, classNameImpl, implCodebase, null);
			
//			System.out.println("Abbiamo creato l'act descriptor con la referenza al Server Centrale , tale referenza: "+stubmar);
		
			System.out.println("Abbiamo creato l'act descriptor con la referenza al Server Centrale , tale referenza: "+null);
			
			
			System.out.println("L' Activation Descriptor del server Autenticazione stato creato "+actDesc);
			// Registro il server attivabile
			System.out.println("Ora registro il descrittore.");
			InterfaceLogin stub_ServerLogin = (InterfaceLogin)Activatable.register(actDesc);
			System.out.println("Descrittore registrato.");
			System.out.println("E' stato creato l'activation descriptor del server che e' stato registrato col demone d'attivazione");
			System.out.println("Il server attivabile che adesso puo' essere acceduto attraverso lo stub: "+stub_ServerLogin);
			
			 Naming.rebind("ServerLogin", stub_ServerLogin);
			
			System.out.println("Creo file per contenere la referenza del server autenticazione");
			File fileaut = new File ("FileStubLogin");
			FileOutputStream out = new FileOutputStream(fileaut);
        	ObjectOutputStream oout = new ObjectOutputStream(out);
        	oout.writeObject(new MarshalledObject(stub_ServerLogin));
        	oout.close();
			System.out.println("Salvata la referenza al server autenticazione");
			System.out.println(" ");



//Invocazione dei metodi test per attivarli
 try {
			System.out.println(" ");
			System.out.println("Ora Invoco il metodo test per attivarlo");
        	System.out.println("Uso lo stub appena creato per invocare il metodo remoto del server");
			System.out.println("Poiche' e' la prima invocazione, cio' causera' il lancio del server autenticazione.");
			stub_ServerLogin.attivazione();
			System.out.println("E' terminato il metodo test.Il Server Autenticazione e' attivo");

		} catch (Exception e) {System.out.println("Errori nella chiamata metodo test: "+e);}


		} catch (Exception e) {System.out.println("Errori nell'inizializzazione: "+e);}

	


        } //fine main


}
