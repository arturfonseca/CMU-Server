package cmu.server.elements;
public class User {
	public String name;
	public String pw;
	public int points = 0;

	public User(String _name, String _pw) {
		name = _name;
		pw = _pw;
	}
	
	public boolean match(String _name,String _pw){
		return _name.equals(name)&&_pw.equals(pw);
	}
}
