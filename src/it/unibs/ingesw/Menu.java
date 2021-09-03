package it.unibs.ingesw;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public final class Menu {
		
	private final String MENUSTART[] = {
			"MENU:",
			"___________________________",
			"1:entra in modalit� configuratore",
			"2:entra in modalit� fruitore",
			"0:Esci",
			"___________________________",
			
	};
	
	private static Network currentNetwork;
	private static ArrayList<Network> networks;
	private static ArrayList<Petri_network> petriNetworks;
	
	/**
	 * Costruisce un men� inizializzando l'array di reti e creando, se non esiste ancora, il file su cui verranno
	 * salvate
	 */
	public Menu() {
		
		networks = new ArrayList<>();
		petriNetworks = new ArrayList<>();
		WriteN.fileCreation();
		Network.network_id = Utility.getMax(ReadN.getNetIDsFromFile(Network.class));
		Petri_network.petriNetworkId = Utility.getMax(ReadN.getNetIDsFromFile(Petri_network.class));//maxPetriId();
	}
	
	/**
	 *metodo principale di avvio del men�, switch che richiama tutte le funzionalit�, 0 per uscire
	 */
	public void startMenu() {
		loadSavedNets();
		int select = -1;
		do {
			for (String s : MENUSTART) {
				System.out.println(s);
			}
			
			select = Utility.readLimitedInt(0, MENUSTART.length-4);
			switch (select) {
				case 1:
					Menu_configuratore.configuratore();
					break;
				case 2:
					Menu_fruitore.menuFriutore();
					break;
				case 0:
					Utility.close();
					break;
			}
		}while(select != 0);
		
	}
	
	public void loadSavedNets() {
		ArrayList<String> n = new ArrayList<String>();
		ArrayList<String> pn = new ArrayList<String>();
		ArrayList<String> pnp = new ArrayList<String>();
			try {
				n = ReadN.readFile(Network.class);
				pn = ReadN.readFile(Petri_network.class);

			} catch (FileNotFoundException | IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(String s : n) 
				networks.add((Network) ReadN.jsonToObject(s, Network.class));
			for(String s : pn)
				petriNetworks.add((Petri_network) ReadN.jsonToObject(s, Petri_network.class));
	}
	
	public static Network getCurrentNetwork() {
		return currentNetwork;
	}
	
	public static ArrayList<Network> getNetworks(){
		return networks;
	}

	public static ArrayList<Petri_network> getPetriNetworks(){
		return petriNetworks;
	}
}
	
