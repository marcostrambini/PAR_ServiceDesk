package univr;



import java.io.*;
import java.rmi.server.RMIClassLoader;



public class MinimalAdmin {


    static String codbase = new String();

    static final String clientClass = "univr.ImplClientSDAdmin";



/**
 *	recuoer del codice dal codebase
 */

    public static void main(String[] args) throws Exception {
    	String hostname = "";
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Inserisci l'ip del server che funge da codebase: ");
    	hostname= br.readLine();
		
    	
   	codbase = "http://"+hostname+"/public_html/server";
    
   	System.setSecurityManager(new SecurityManager());
	Class classClient = null;
	Runnable client = null;
	try{
        	classClient = RMIClassLoader.loadClass(codbase, clientClass);
        	client = (Runnable) classClient.newInstance();
	}catch (ClassNotFoundException e){System.out.println("Impossibile contattare il sistema, potrebbe non essere attivo oppure si contattato l'host errato. ");}
        client.run();
    }
}
