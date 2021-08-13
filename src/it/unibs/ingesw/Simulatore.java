package it.unibs.ingesw;

import java.util.ArrayList;

public class Simulatore {

	Petri_network rete;
	
	public Simulatore (Petri_network rete) {
		this.rete = rete;
	}
	
	public void nextStep(){
		int possibili = transAttivabili().size();
		if (possibili == 0) 
			System.out.println("BLOCCO CRITICO OCIO TERMINIAMO IL PROGRAMMA");
		else if (possibili == 1) {
			stampAttivabili();
			System.out.println("C'è un'unica transizione attivabile, prossimo step:");
			modificaToken(transAttivabili().get(0));
			Menu_Visua.printPetriNet(rete);
			}
		
		else {
			//stampa a video, richiede la scelta, esegue
			System.out.println("Quale transizione vuoi attivare?");
			stampAttivabili();
			int scelta = Utility.readLimitedInt(0, transAttivabili().size() -1);
			modificaToken(transAttivabili().get(scelta));
			Menu_Visua.printPetriNet(rete);
		}
	}
	
	
	private void stampAttivabili() {
		System.out.println("Lista transizioni attivabili:");
		for (int i=0; i<transAttivabili().size(); i++) {
			System.out.println(i + ") " + transAttivabili().get(i).getName());
		}
	}
	
	
	private ArrayList<Petri_transition> transAttivabili(){
		ArrayList<Petri_transition> risultato = new ArrayList<>();
		
		for (Petri_transition pt : rete.getTransitions()) {
			if(attivabile(pt))
				risultato.add(pt);
		}
		return risultato;
	}
	
	private boolean attivabile (Petri_transition pt) {
		boolean exist = checkIfOneLinkExistWithTrans(pt);
		//prima di tutto controlliamo se almeno un link ha come destinazione la transizione
		if(!exist)
			return false;
		for (Petri_link l : rete.getLinks()) {
			if (l.getDestination().getId() == pt.getId()) {
				if( l.getOrigin().getValue() < pt.getValue())
					return false;
			
			}
			
		}
		return true;
	}
	/**
	 * 
	 * @param pt la transizione da andare a controllare
	 * @return un booleano che dice se almeno un link ha come destinazione la transizione
	 */
	private boolean checkIfOneLinkExistWithTrans(Petri_transition pt) {
		for(Petri_link l : rete.getLinks()) {
			if(l.getDestination().getId() == pt.getId())
				return true;
		}
		return false;
	}

	private void modificaToken(Petri_transition pt) {
		rete.reduceToken(pt.getId(), pt.getValue());;
		rete.addToken(pt.getId(), 1); //viene passato 1 perchè per ora è il valore di default
	}
	
}
