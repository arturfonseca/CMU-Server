package cmu.server.elements;

public class Bike {
	public String name;
	public Boolean isBooked = false;
	public String bookedBy;

	public Bike(String _name) {
		name = _name;
	}

	public String print() {
		return name + "_" + isBooked;
	}

	public String book(String user) {
		if (isBooked) {
			return "ERROR";
		} else {
			isBooked = true;
			bookedBy = user;
			return "OK";

		}
	}

}
