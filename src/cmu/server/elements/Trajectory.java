package cmu.server.elements;


public class Trajectory {
	String points;
	String date;
	public Trajectory (String _date,String _points){
		date=_date;
		points=_points;
	}
	public String print() {
		// TODO Auto-generated method stub
		return date+"@"+points+",";
	}
}
