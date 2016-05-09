package cmu.server.elements;

import java.io.Serializable;
import java.util.ArrayList;

public class Station{
	public String name;
	public String location;
	public int available;
	public int booked=0;
	public ArrayList<String> reservations = new ArrayList<String>();
	public Station(String _name, String _location,int _available) {
		name=_name;
		location=_location;
		available=_available;
		
	}

	public String print() {
		String result = available+","+booked;
		
		return result;
	}

	public String getLocation() {
		return location;
	}
	public boolean booked (String name){
		return reservations.contains(name);
	}
	
	
}
