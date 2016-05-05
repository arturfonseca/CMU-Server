package cmu.server;

import java.util.ArrayList;
import cmu.server.elements.*;

public class Storage {
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Station> stations = new ArrayList<Station>();

	public Storage() {
		// SET UP
		users.add(new User("a", "f"));
		Station s = new Station("lisboa_1", "38.747151, -9.118308", 3);
		stations.add(s);

		s = new Station("lisboa_2", "38.737187, -9.133885", 3);
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

	public String book(String _name, String station) {
		for (Station s : stations) {
			if (s.name.equals(station)) {
				if (s.available > 0) {
					s.booked++;
					s.available--;
					s.reservations.add(_name);
					return "OK";
				}

			}
		}
		return "ERROR";
	}

	public String dropoff(String _name, String station) {
		for (Station s : stations) {
			if (s.name.equals(station)) {
				s.available++;
				return "OK";
			}

		}
		return "ERROR";
	}

	public String pickup(String _name, String station) {
		for (Station s : stations) {
			if (s.name.equals(station)) {
				if (s.reservations.contains(_name)) {
					s.booked--;
					s.reservations.remove(_name);
					return "OK";
				}

			}

		}
		return "ERROR";
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
				String result = "";
				for (Trajectory t : u.trajectories) {
					result += t.print();
				}
				return result;
			}
		}
		return "ERROR";

	}

	public ArrayList<String> getStations() {
		ArrayList<String> StationCoordinates = new ArrayList<String>();
		for (Station s : stations) {
			if (s.available > 0) {
				StationCoordinates.add(s.getLocation());
			}

		}
		return StationCoordinates;
	}
}
