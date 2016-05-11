package cmu.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import cmu.server.elements.*;

public class Storage {
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Station> stations = new ArrayList<Station>();

	public Storage() {
		// SET UP
		users.add(new User("a", "f"));
		Station s = new Station("station_lisboa_1", "38.747151,-9.118308", 10);
		stations.add(s);

		s = new Station("station_lisboa_2", "38.737187,-9.133885", 10);
		stations.add(s);
		book("a","station_lisboa_1");
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

	public String setpoints(String _name, String points) {
		for (User u : users) {
			if (u.name.equals(_name)) {
				u.points = Integer.parseInt(points);
				return u.points + "";
			}
		}
		return "ERROR";
	}

	public String incpoints(String _name, String points) {
		for (User u : users) {
			if (u.name.equals(_name)) {
				u.points += Integer.parseInt(points);
				return u.points + "";
			}
		}
		return "ERROR";
	}

	public String decpoints(String _name, String points) {
		for (User u : users) {
			if (u.name.equals(_name)) {
				u.points -= Integer.parseInt(points);
				return u.points + "";
			}
		}
		return "ERROR";
	}

	public String transfer(String _name, String points, String receiver) {
		incpoints(receiver, points);
		return decpoints(_name, points);
	}

	public String getpoints(String _name) {
		for (User u : users) {
			if (u.name.equals(_name)) {
				return u.points + "";
			}
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

	public String booked(String _name, String station) {
		for (Station s : stations) {
			if (s.name.equals(station)) {
				if (s.booked(_name))
					return "OK";

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

	public HashMap<String,String> getStations() {
		HashMap<String,String> StationCoordinates = new HashMap<String,String>();
		for (Station s : stations) {
			if (s.available > 0) {
				StationCoordinates.put(s.getLocation(),s.name);
			}

		}
		return StationCoordinates;
	}
}
