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
	 * Variabile che memorizza l'ip del server su cui attivare il sistema.
	 */
        private static String lease;

	/**
	 * metodo main che inizializza il sistema.
	 *
	 *
	 */

        public static void main(String args[]) {

        		lease=args[0];
                String policyGroup = System.getProperty("univr.policy");
                String implCodebase = System.getProperty("univr.impl.codebase");
                System.out.println("Sono il codice di inizializzazione dei server.");
                System.setSecurityManager(new RMISecurityManager());
                System.getProperties().put("java.rmi.dgc.leaseValue","lease");

                try {
                		
//                	MarshalledObject stubmar=null;
                    Properties prop = new Properties();
                	prop.put("java.security.policy", policyGroup);
             		prop.put("univr.impl.codebase", implCodebase);
             		prop.put("java.class.path", "no_classpath");

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
                        ActivationDesc actDesc = new ActivationDesc(groupID, "univr.ImplServerSDAct",implCodebase, null);
			System.out.println("Activation Descriptor creato."+actDesc);
                        // Registro il server attivabile
                        System.out.println("Ora registro il descrittore.");
                        InterfaceServerSDAdmin stub_ServerSD = (InterfaceServerSDAdmin)Activatable.register(actDesc);
                        System.out.println("Descrittore registrato.");
                        System.out.println("E' stato creato l'activation descriptor del server che e' stato registrato col demone d'attivazione");
                        System.out.println("Il server attivabile che adesso puo' essere acceduto attraverso lo stub: "+stub_ServerSD);
                        Naming.rebind("ServerSD", stub_ServerSD);
                        
			
//                        File filecen = new File ("FileStubCentrale");
//			            FileOutputStream out = new FileOutputStream(filecen);
//        		ObjectOutputStream oout = new ObjectOutputStream(out);
//        		stubmar=new MarshalledObject(stub_sc);
//       			oout.writeObject(stubmar);
//       			oout.close();
//			System.out.println("Salvata la referenza al server centrale");
//			System.out.println(" ");


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
