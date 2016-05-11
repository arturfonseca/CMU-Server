package cmu.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class Parser {
	Storage s = new Storage();

	public String run(String cmd) {
		String[] parsed = cmd.split(":");
		String op = parsed[0];
		String args = "";
		if (parsed.length > 1) {
			args = parsed[1];
		}
		/*regist:a,f
		 *login:a,f 
		 *pickup 
		 *dropoff 
		 *list 
		 *book 
		 *addTrajectory:a,12-03-2016,1;1-1;2-1;3-2;3
		 *showTrajectory:a ->12-03-2016@1;1-1;2-1;3-2;3,
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 *
		 */

		if (op.equals("regist")) {
			return s.regist(args.split(",")[0], args.split(",")[1]);
		} else if (op.equals("login")) {
			return s.login(args.split(",")[0], args.split(",")[1]);
		} else if (op.equals("pickup")) {
			return s.pickup(args.split(",")[0],args.split(",")[1]);
		} else if (op.equals("dropoff")) {
			return s.dropoff(args.split(",")[0], args.split(",")[1]);
		} else if (op.equals("list")) {
			return s.list();
		} else if (op.equals("book")) {
			return s.book(args.split(",")[0], args.split(",")[1]);
		} else if (op.equals("addTrajectory")) {
			return s.trajectory(args.split(",")[0], args.split(",")[1], args.split(",")[2]);
		} else if (op.equals("showTrajectory")) {
			return s.showTrajectory(args.split(",")[0]);
		} else if (op.equals("booked")) {
			return s.booked(args.split(",")[0],args.split(",")[1]);
		}else if(op.equals("getStations")){
			System.out.println(ObjectToString(s.getStations()));
			return ObjectToString(s.getStations());
		} else if (op.equals("setpoints")) {
			return s.setpoints(args.split(",")[0], args.split(",")[1]);
		} else if (op.equals("getpoints")) {
			return s.getpoints(args.split(",")[0]);
		} else if (op.equals("incpoints")) {
			return s.incpoints(args.split(",")[0], args.split(",")[1]);
		} else if (op.equals("decpoints")) {
			return s.decpoints(args.split(",")[0], args.split(",")[1]);
		} else if (op.equals("transferpoints")) {
			return s.transfer(args.split(",")[0], args.split(",")[1], args.split(",")[2]);
		} else
			return "CMD not found";
	}
	
	private String ObjectToString(Object object){
		byte[] buff = null;
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			buff = bos.toByteArray();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(buff);
	}
}
