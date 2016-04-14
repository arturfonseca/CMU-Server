package cmu.server;

import java.util.ArrayList;
import cmu.server.elements.*;

public class Storage {
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Station> stations = new ArrayList<Station>();

	public Storage() {
		// SET UP
		ArrayList<String> bikes = new ArrayList<String>();
		bikes.add("b1");
		bikes.add("b2");
		Station s = new Station("lisboa_1", null, bikes);
		stations.add(s);
		bikes = new ArrayList<String>();
		bikes.add("b3");
		s = new Station("lisboa_2", null, bikes);
		stations.add(s);
	}

	public String regist(String _name, String _pw) {
		for (User u : users) {
			if (u.name.equals(_name))
				return "ERROR";
		}
		users.add(new User(_name, _pw));
		return "OK";
	}

	public String login(String _name, String _pw) {
		for (User u : users) {
			if (u.match(_name, _pw))
				return "OK";
		}
		return "ERROR";
	}

	public String book(String _name, String bike) {
		return "";
	}

	public String dropoff(String bike, String station) {
		return "";
	}

	public String pickup(String bike) {
		return "";
	}

	public String list() {
		String result = "";
		for (Station s : stations) {
			result += s.print() + "\n";

		}
		return result;
	}
}
