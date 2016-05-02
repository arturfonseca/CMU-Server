package cmu.server.elements;

import java.io.Serializable;
import java.util.ArrayList;

public class Station{
	public String name;
	public String location;
	public ArrayList<Bike> bikes = new ArrayList<Bike>();

	public Station(String _name, String _location, ArrayList<Bike> list) {
		name=_name;
		location=_location;
		bikes=list;
	}

	public String print() {
		String result = name+":";
		result +=bikes.size()+" bikes:";
		for (Bike b : bikes) {
			result +="\n "+ b.print();
		}
		result += "\n";
		return result;
	}

	public String getLocation() {
		return location;
	}
	
	public ArrayList<Bike> getBikes(){
		return bikes;
	}
}
