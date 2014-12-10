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
import java.rmi.RemoteException;
import java.io.OutputStream;
import java.rmi.*;
import java.net.*;

/**
 * Codice di inizializzazione del sistema di server.
 *
 *
 */
final class SetupServerSD {



	/**
	 * metodo main che inizializza il sistema.
	 *
	 *
	 */

        public static void main(String args[]) {

        		String lease=args[0];
        		//String lease = "60000";
        		
      		
                String policyGroup = System.getProperty("activation.policy");
                String implCodebase = System.getProperty("activation.impl.codebase");
                String classNameImpl = System.getProperty("activation.classeserver");
                
                System.out.println("Sono il codice di inizializzazione dei server.");
        		
                if(System.getSecurityManager()==null){
        			System.setSecurityManager(new RMISecurityManager());
        		}
                
//                System.getProperties().put("java.rmi.dgc.leaseValue", lease);
                System.out.println("Tempo di lease a parametro in millisecondi = "+lease);
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
             		
             		System.out.println("java.rmi.dgc.leaseValue = "+lease);
             		prop.put("java.rmi.dgc.leaseValue", lease);

             		
// creazione del gruppo di attivazione del server centrale attivabile

             			System.out.println(" ");
                        System.out.println("Creo il gruppo di attivazione del server Centrale.");
                        ActivationGroupDesc groupDesc = new ActivationGroupDesc(prop, null);
                        System.out.println("Gruppo creato.");
                        // registrazione del gruppo di attivazione
                        ActivationGroupID groupID = ActivationGroup.getSystem().registerGroup(groupDesc);
                        System.out.println("Il gruppo e' stato creato,  registrato col sistema d'attivazione, ed ha identificativo = "+groupID);
                        // creazione di un descrittore per il main server
                        System.out.println("Creo l'Activation Descriptor.");
                        ActivationDesc actDesc = new ActivationDesc(groupID, classNameImpl, implCodebase, null);
			System.out.println("Activation Descriptor creato."+actDesc);
                        // Registro il server attivabile
                        System.out.println("Ora registro il descrittore.");
                        
                        InterfaceServerSDAdmin stub_ServerSD = (InterfaceServerSDAdmin)Activatable.register(actDesc);
                        //ImplServerSDAct stub_ServerSD = (ImplServerSDAct)Activatable.register(actDesc);
                         
                        
                        System.out.println("Descrittore registrato.");
                        System.out.println("E' stato creato l'activation descriptor del server che e' stato registrato col demone d'attivazione");
                        System.out.println("Il server attivabile che adesso puo' essere acceduto attraverso lo stub: "+stub_ServerSD);
                       
//                        Naming.rebind("ServerSD", stub_ServerSD);
                        Naming.rebind("ServerSD", stub_ServerSD);
                        InterfaceServerSDAdmin test = (InterfaceServerSDAdmin) Naming.lookup("ServerSD");
                        System.out.println("se faccio una lookup vedo: "+test.toString());
//			
//	                    File filecen = new File ("FileStubCentrale");
//				        FileOutputStream out = new FileOutputStream(filecen);
//				        ObjectOutputStream oout = new ObjectOutputStream(out);
//				        stubmar=new MarshalledObject(stub_ServerSD);
//				        oout.writeObject(stubmar);
//				        oout.close();
//				        System.out.println("Salvata la referenza al server centrale");
//				        System.out.println(" ");


////Invocazione del metodo test per attivare il server centrale
//try {
//			System.out.println(" ");
//			System.out.println("Ora Invoco il metodo test per attivare il ServerCentrale");
//        		System.out.println("Uso lo stub appena creato per invocare il metodo remoto del server");
//        		System.out.println("Poiche' e' la prima invocazione, cio' causera' il lancio del server centrale.");
//       			stub_sc.testCentrale();
//			System.out.println("E' terminato il metodo test.Il Server Centrale e' ora attivo");
//
//
//
// 	}catch (Exception e) {System.out.println("Errori nella chiamata metodo test: "+e);}
                        
                        
	}catch (Exception e) {System.out.println("SetupServerSD : Errori nella fase di setup: "+e);}




        } //fine main


}
