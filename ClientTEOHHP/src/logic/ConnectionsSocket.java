package logic;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import shared.Encryption;

public class ConnectionsSocket {
	
	private Socket connection;
	private String jsonIn;
	private byte[] byteArrayIn = new byte[500000];
	private byte[] byteArrayOut = new byte[500000];
	
	private Encryption encryptionObject = new Encryption(); 
	
	public String connectToServerAndSendObject(String jsonOut) throws IOException{
		System.out.println("Attempting connection...");
		connection = new Socket(InetAddress.getByName("localhost"), 8349);
		System.out.println("Connected to "+connection.getInetAddress().getHostAddress());
		byteArrayOut = encryptionObject.encrypt(jsonOut);
		connection.getOutputStream().write(byteArrayOut);
		connection.getInputStream().read(byteArrayIn);
		jsonIn = encryptionObject.decrypt(byteArrayIn);
		return jsonIn;
	}

}
