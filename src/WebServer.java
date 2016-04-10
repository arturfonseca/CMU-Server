import java.io.*;
import java.net.InetSocketAddress;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {
	static Storage s = new Storage();

	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/run", new MyHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
		System.out.println("Started");
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		buffer.readLine();
		server.stop(0);
		System.out.println("Stopped");

	}

	static class MyHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange t) throws IOException {
			String query = t.getRequestURI().getQuery();
			String result = "ERROR";
			if (query != null)
				result = s.run(query);
			t.sendResponseHeaders(200, result.length());
			OutputStream os = t.getResponseBody();
			os.write(result.getBytes());
			os.close();

		}
	}

}