package old;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Ticket {

	private String nomeUtente;
	private String cognomeUtente;
	private String tipoChiamata;
	private String descChiamata;
	private String stato;
	private String risoluzione;
	private int numTicket;
	
	Tools tools = new Tools();
	
	public Ticket(String nomeUtente, String cognomeUtente, String tipoChiamta, String descChiamata){
		controllaFile("numeroTicket.txt");
		this.nomeUtente = nomeUtente;
		this.cognomeUtente = cognomeUtente;
		this.tipoChiamata = tipoChiamta;
		this.descChiamata = descChiamata;
		this.numTicket = getNumeroTicket();
		
	}
	
	public void controllaFile(String nomeFile){
		Tools tools = new Tools();
		if(!tools.esisteFile(nomeFile)){
			try {
				tools.creaFile(nomeFile);
				tools.scriviFile(nomeFile, "0");
			} catch (FileNotFoundException e) {
				System.out.println("Problemi di creazione del file: "+nomeFile);
			}
		}
	}
	
	public int getNumeroTicket(){
		Tools tools = new Tools();
		String nomeFile = "numeroTicket.txt";
		if(tools.esisteFile(nomeFile)){
		try {
			ArrayList<String> listaNumeriTicket = tools.leggiFileRitorna(nomeFile);
			int numTicket = Integer.parseInt(listaNumeriTicket.get(0))+1;
			tools.clearFile(nomeFile);
			tools.scriviFile(nomeFile, String.valueOf(numTicket));
			return numTicket;
			
		} catch (IOException e) {
			System.out.println("Problemi con la lettura del file: "+nomeFile);
		}
		}
		return 0;
	}
	
	public String getNome(){
		return nomeUtente;
	}
	
	public String getChiamata(){
		String msg = "Numero Ticket: "+numTicket + "\n" +
					 "Nome Utente: "+ nomeUtente + " " + cognomeUtente + "\n" +
					 "Tipo chiamata: " + tipoChiamata + "\n" +
					 "Descrizione:" + "\n" +
					 descChiamata + "\n" +
					 "-----------------------------------------------------------";
		
		
		return msg;
	}
	
	public static void main(String[] args) {
		Ticket t1 = new Ticket("Marco", "Strambini", "HW", "Problemi con internet");
		Ticket t2 = new Ticket("Pippo", "Strambini", "SW", "Problemi conl cazzo");
		Ticket t3 = new Ticket("GIGI", "D'Alessio", "HW", "Problemi col cervello");
		Ticket t4 = new Ticket("Sasha", "Grey", "SW", "Pube rasato");
		System.out.println(t1.getChiamata());
		System.out.println(t2.getChiamata());
		System.out.println(t3.getChiamata());
		System.out.println(t4.getChiamata());
	}
	
}
