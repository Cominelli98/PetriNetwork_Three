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
			System.out.println("C'� un'unica transizione attivabile, prossimo step:");
			modificaToken(transAttivabili().get(0));
			Menu_visua.p
			}
		
		else {
			//stampa a video, richiede la scelta, esegue
			System.out.println("Quale transizione vuoi attivare?");
			stampAttivabili();
			int scelta = Utility.readLimitedInt(0, transAttivabili().size() -1);
			modificaToken(transAttivabili().get(scelta));
			
		}
		//restituzione nuova marcatura
	}
	
	
	public void stampAttivabili() {
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
	
	private boolean attivabile(Petri_transition pt) {
		for (Petri_link l : rete.getLinks()) {
			if (l.getDestination() == pt) {
				if(((Petri_location) l.getOrigin()).getToken() < pt.getCost())
					return false;
			}
			
		}
		return true;
	}
	
	private void riduciToken(Petri_transition pt) {
		for (Petri_link l : rete.getLinks()) {
			if (l.getDestination() == pt) {
				((Petri_location) l.getOrigin()).reduceToken(pt.getCost());
			}
		}
	}
		
	private void aggiungiToken(Petri_transition pt) {
		for (Petri_link l : rete.getLinks()) {
			if (l.getOrigin() == pt) {
				((Petri_location) l.getDestination()).addToken(1); //TO UNDERSTAND: QUANTO AUMENTANO I TOKEN DELLA DESTINAZIONE AAAAAAAAAAAAAAAAAAAAAAAAAAA
			}
		}
	}
	
	private void modificaToken(Petri_transition pt) {
		riduciToken(pt);
		aggiungiToken(pt);
	}
	
}