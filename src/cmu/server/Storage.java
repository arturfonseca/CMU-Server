package cmu.server;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

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
				
				// don't allow to give more points then user has 
				if((u.points - Integer.parseInt(points)) < 0) {
					 u.points -= u.points;
				}
				else {
					u.points -= Integer.parseInt(points);
				}
				return u.points + "";
			}
		}
		return "ERROR";
	}

	public String transfer(String _name, String points, String receiver) {
		// to prevent the user to send more points then has
		// calculate new points and save result
		String actual = getpoints(_name);
		String result = decpoints(_name, points);
		if(result.equals("0"))
			incpoints(receiver, actual);
		else
			incpoints(receiver, points);
		return result;
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

	public String trajectory(String user, String date, String coordinates, String points) {
		System.out.println(user+";"+date+";");
		System.out.println(coordinates);
		System.out.println(points);
		for (User u : users) {
			if (u.name.equals(user)) {
				u.addTrajectory(date,coordinates,points);
				return "OK";
			}
		}
		return "ERROR";

	}
	
	public ArrayList<String> getAllTrajectories(String user){
		for(User u: users){
			if(u.name.equals(user)){
				return new ArrayList<String>(u.trajectories.keySet());
			}
		}
		return new ArrayList<String>();
	}

	public String getTrajectory(String user, String date){
		for(User u: users){
			if(u.name.equals(user)){
				System.out.println(u.trajectories.get(date));
				return u.trajectories.get(date);
			}
		}
		return "Error";
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
	
	public String getLastTrajectory(String user){
		for(User u: users){
			if(u.name.equals(user)){
				Enumeration<String> keys = u.trajectories.keys();
				String key = keys.nextElement();
				while(keys.hasMoreElements()){
					key = keys.nextElement();
				}
				return u.trajectories.get(key);
			}
		}
		return "Error";
	}
}
