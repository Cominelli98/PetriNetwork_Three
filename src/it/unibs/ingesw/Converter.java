package it.unibs.ingesw;

import java.util.ArrayList;

public final class Converter {
	
	
	public static Petri_location toPetri (Location l, int petriNetId) {
		return new Petri_location(l, petriNetId);
	}
	
	public static ArrayList<Petri_location> toPetriLocations (ArrayList<Location> l, int petriNetId){
		
		ArrayList<Petri_location> temp = new ArrayList<>();
		for (Location location : l) {
			temp.add(toPetri(location, petriNetId));
		}
		return temp;
	}
	
	public static Petri_transition toPetri (Transition t, int petriNetId) {
		return new Petri_transition(t, petriNetId);
	}
	
	public static ArrayList<Petri_transition> toPetriTransitions (ArrayList<Transition> t, int petriNetId){
		
		ArrayList<Petri_transition> temp = new ArrayList<>();
		for (Transition transition : t) {
			temp.add(toPetri(transition, petriNetId));
		}
		return temp;
	}
	
	
	public static Petri_link toPetri (ArrayList<Petri_location> pl, ArrayList<Petri_transition> pt, Link l, int petriNetId) {
		Node tempOrigin = null;
		Node tempDestination = null;
		for (Location location : pl) {
			if(location.getId() == l.getOrigin().getId())
				tempOrigin = location;
		}
		
		for(Transition transition : pt) {
			if(transition.getId() == l.getOrigin().getId())
				tempOrigin = transition;
		}
		
		for (Location location : pl) {
			if(location.getId() == l.getDestination().getId())
				tempDestination = location;
		}
		
		for(Transition transition : pt) {
			if(transition.getId() == l.getDestination().getId())
				tempDestination = transition;
		}
		return new Petri_link(tempOrigin, tempDestination, petriNetId);
	}
	
	
	
	public static ArrayList<Petri_link> toPetriLinks (ArrayList<Petri_location> pl, ArrayList<Petri_transition> pt, ArrayList<Link> l, int petriNetId){
		ArrayList<Petri_link> temp = new ArrayList<>();
		for(Link link : l) {
			temp.add(toPetri(pl, pt, link, petriNetId));
		}
		return temp;
	}
	
	
	
	
	
	
	
	
}
