package cmu.server;

import java.util.ArrayList;
import cmu.server.elements.*;

public class Storage {
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Station> stations = new ArrayList<Station>();

	public Storage() {
		// SET UP
		users.add(new User("a", "f"));
		ArrayList<Bike> bikes = new ArrayList<Bike>();
		bikes.add(new Bike("b1"));
		bikes.add(new Bike("b2"));
		Station s = new Station("lisboa_1", "38.747151, -9.118308", bikes);
		stations.add(s);
		bikes = new ArrayList<Bike>();
		bikes.add(new Bike("b3"));
		s = new Station("lisboa_2", "38.737187, -9.133885", bikes);
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

	public String book(String _name, String bike, String station) {
		for (Station s : stations) {
			if (s.name.equals(station)) {
				for (Bike b : s.bikes) {
					if (b.name.equals(bike)) {
						return b.book(_name);
					}
				}
			}
		}
		return "ERROR";
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

	public String trajectory(String user, String date, String points) {
		for (User u : users) {
			if (u.name.equals(user)) {
				u.addTrajectory(new Trajectory(date, points));
				return "OK";
			}
		}
		return "ERROR";

	}
	public String showTrajectory(String user) {
		for (User u : users) {
			if (u.name.equals(user)) {
				String result="";
				for (Trajectory t:u.trajectories){
					result+=t.print();
				}
				return result;
			}
		}
		return "ERROR";

	}

	public ArrayList<String> getStations() {
		ArrayList<String> StationCoordinates = new ArrayList<String>();
		for(Station s: stations){
			if(!s.getBikes().isEmpty()){
				StationCoordinates.add(s.getLocation());
			}
		}
		return StationCoordinates;
	}
}
