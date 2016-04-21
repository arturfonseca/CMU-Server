package cmu.server;

public class Parser {
	Storage s = new Storage();

	public String run(String cmd) {
		String[] parsed = cmd.split(":");
		String op = parsed[0];
		String args = "";
		if (parsed.length > 1) {
			args = parsed[1];
		}

		if (op.equals("regist")) {
			return s.regist(args.split(",")[0], args.split(",")[1]);
		} else if (op.equals("login")) {
			return s.login(args.split(",")[0], args.split(",")[1]);
		} else if (op.equals("pickup")) {
			return s.pickup(args.split(",")[0]);
		} else if (op.equals("dropoff")) {
			return s.dropoff(args.split(",")[0], args.split(",")[1]);
		} else if (op.equals("list")) {
			return s.list();
		} else if (op.equals("book")) {
			return s.book(args.split(",")[0], args.split(",")[1],args.split(",")[2]);
		} else if (op.equals("addTrajectory")) {
			return s.trajectory(args.split(",")[0], args.split(",")[1], args.split(",")[2]);
		} else
			return "ERROR";
	}

}
