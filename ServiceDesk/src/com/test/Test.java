package com.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Test {

	public static int numero = 2;
	public static String pathDb = "/Mago/javarmi/univr/db/";
	
	
	/**
	 * metodo che legge il contenuto di un file e lo restituisce in un Array List di stringhe
	 * @param nomeFile
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String>  leggiFileRitorna(String nomeFile) throws IOException{ 
	
		ArrayList<String> arrayLetto = new ArrayList<String>();
		FileReader fr = new FileReader(nomeFile);
		BufferedReader br = new BufferedReader(fr);
		String sCurrentLine;
		while((sCurrentLine = br.readLine()) != null){
		arrayLetto.add(sCurrentLine);
		}
		
		//String[] arrayReturn = listToArray(arrayLetto);
	
		fr.close();
		return arrayLetto; 
	
	}
	
	
	
	
	public static void main(String[] args) {


			String nomeFile = "segnalazioni.txt";
			int segnalazioneTrovata = -1;
			String record = "";
			ArrayList<String> arrayListSegnalazioni = new ArrayList<String>();
			try {
				System.out.println("--> Richiamato il metodo di visualizzazione della chiamata n "+ numero + " da parte del client");
				arrayListSegnalazioni = leggiFileRitorna(pathDb+nomeFile);
				String[][] riga = new String[arrayListSegnalazioni.size()][5];
				for(int i =0; i<arrayListSegnalazioni.size();i++){
				System.out.println(arrayListSegnalazioni.get(i));
				}
				
				
				for(int i =0; i<arrayListSegnalazioni.size();i++){
					String[] campi = arrayListSegnalazioni.get(i).split("\\|");
					riga[i][0] = campi[0];
					System.out.print(riga[i][0]);
					riga[i][1] = campi[1];
					System.out.print(riga[i][1]);
					riga[i][2] = campi[2];
					System.out.print(riga[i][2]);
					riga[i][3] = campi[3];
					System.out.print(riga[i][3]);
					riga[i][4] = campi[4];
					System.out.println(riga[i][4]);
				}

				for(int i =0; i<riga.length;i++){
					if(numero == Integer.parseInt(riga[i][0]) )
						segnalazioneTrovata = i;
				}

//				addIntestazioneSegnalazione(t);
//				for(int i=0;i<5;i++)
//					t.addCell(riga[segnalazioneTrovata][i]);


			} catch (IOException e) {
				String msg = "[ATTENZIONE nella routine di visualizza segnalazione particolare] Problemi di lettura del file "+ pathDb+nomeFile;
				System.out.println(msg);
				
			}



			if (segnalazioneTrovata != -1){

//				String render = t.render();
				String msg = "";
				msg = arrayListSegnalazioni.get(segnalazioneTrovata);
				
				System.out.println( "\nLa segnalazione cercata e' la seguente: " + "\n" + msg + "\n");
			}else
				System.out.println( "\nla segnalazione numero "+numero+" non e' stata trovata. controlla meglio..\n");

		}


	
}


