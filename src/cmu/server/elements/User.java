package cmu.server.elements;

import java.util.ArrayList;
import java.util.Hashtable;

public class User {
	public String name;
	public String pw;
	public int points=1000;
	//public ArrayList<Trajectory> trajectories=new ArrayList<Trajectory>();
	public Hashtable<String,String> trajectories = new Hashtable<String,String>();

	public User(String _name, String _pw) {
		name = _name;
		pw = _pw;
	}
	
	public boolean match(String _name,String _pw){
		return _name.equals(name)&&_pw.equals(pw);
	}
	public void addTrajectory (String date, String t, String points){
		trajectories.put(date, t);
		this.points += Integer.parseInt(points);
	}
}
