package cmu.server.elements;

import java.util.ArrayList;

public class Station {
	public String name;
	public String location;
	public ArrayList<String> bikes = new ArrayList<String>();

	public Station(String _name, String _location, ArrayList<String> list) {
		name=_name;
		location=_location;
		bikes=list;
	}

	public String print() {
		String result = name+":";
		result +=bikes.size()+" bikes:";
		for (String b : bikes) {
			result +="\n "+ b;
		}
		result += "\n";
		return result;
	}
}
