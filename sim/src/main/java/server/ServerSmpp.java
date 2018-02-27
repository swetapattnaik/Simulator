package server;

import java.io.IOException;
import java.util.ArrayList;

import org.smpp.SmppObject;
import org.smpp.debug.Debug;
import org.smpp.debug.Event;
import org.smpp.debug.FileDebug;
import org.smpp.debug.FileEvent;
import org.smpp.smscsim.Simulator;

public class ServerSmpp {
	static String usersFileName = "./users.txt";

	/**
	 * Directory for creating of debug and event files.
	 */
	static final String dbgDir = "./";

	/**
	 * The debug object.
	 */
	static Debug debug = new FileDebug(dbgDir, "sim.dbg");
	/**
	 * The event object.
	 */
	static Event event = new FileEvent(dbgDir, "sim.evt");
	static Simulator s=new Simulator();
	
public static void main(String[] args) throws IOException {
	
	
	s.start();
}
public void startSimulator() throws IOException{
	SmppObject.setDebug(debug);
	SmppObject.setEvent(event);
	debug.activate();
	event.activate();
	s.start();
}
public static ArrayList<String> recieveClients() throws IOException {
	ArrayList<String> clients=s.listClients();
	return clients;
}
}
