package it.unibs.ingesw;


public final class Menu_configuratore {
	
	private final static String MENU_CONFIGURATORE[] = {
			"Scegli cosa fare:",
			"___________________________",
			"1:crea una nuova rete",
			"2:Crea una nuova rete di petri",
			"3:Visualizza una rete",
			"4:Salva una o pi� reti",
			"0:Indietro",
			"___________________________"};
	
	private final static String SCELTA_VISUALIZZAZIONE[] = {
			"Scegli cosa fare:",
			"___________________________",
			"1:visualizza una rete",
			"2:visualizza una rete di petri",
			"0:Indietro",
			"___________________________"};
	
	private final static String SCELTA_SALVATAGGIO[] = {
			"Scegli cosa fare:",
			"___________________________",
			"1:salvare una rete",
			"2:salvare una rete di petri",
			"0:Indietro",
			"___________________________"};
	
	public static void configuratore() {
		int select = -1;
		do {
			printMenu(MENU_CONFIGURATORE);
			select = Utility.readLimitedInt(0, MENU_CONFIGURATORE.length-4);
			switch(select) {
			case 1:
				Menu_Reti.createNetwork(Menu.getCurrentNetwork(), Menu.getNetworks());
				break;
			case 2:
				Menu_Petri.createPetri(Menu.getPetriNetworks(), Menu.getNetworks());
				break;
			case 3:
				visualizationOption();
				break;
			case 4:
				saveOption();
				break;
			case 0:
				break;
			}
		}while(select != 0);
	}
	
	private static void saveOption() {
		int select = -1;
		do {
			printMenu(SCELTA_SALVATAGGIO);
			select = Utility.readLimitedInt(0, SCELTA_SALVATAGGIO.length-4);
			switch(select) {
			case 1:
				Menu_Salva.saveOption(Menu.getNetworks());
				break;
			case 2:
				Menu_Salva.pSaveOption(Menu.getPetriNetworks());
				break;
			case 0:
				break;
			}
		}while(select != 0);
	}

	private static void visualizationOption() {
		int select = -1;
		do {
			printMenu(SCELTA_VISUALIZZAZIONE);
			select = Utility.readLimitedInt(0, SCELTA_VISUALIZZAZIONE.length-4);
			switch(select) {
			case 1:
				 Menu_Visua.netViewer(Menu.getNetworks());
				break;
			case 2:
				 Menu_Visua.petriNetViewer(Menu.getPetriNetworks());
				break;
			case 0:
				break;
			}
		}while(select != 0);
		
	}

	public static void printMenu(String [] toPrint) {
		for(String s :toPrint)
			System.out.println(s);
	}
}


