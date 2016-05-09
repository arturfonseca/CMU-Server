package cmu.server.elements;

import java.util.ArrayList;

public class User {
	public String name;
	public String pw;
	public String points="0";
	public ArrayList<Trajectory> trajectories=new ArrayList<Trajectory>();

	public User(String _name, String _pw) {
		name = _name;
		pw = _pw;
	}
	
	public boolean match(String _name,String _pw){
		return _name.equals(name)&&_pw.equals(pw);
	}
	public void addTrajectory (Trajectory t){
		trajectories.add(t);
	}
}
