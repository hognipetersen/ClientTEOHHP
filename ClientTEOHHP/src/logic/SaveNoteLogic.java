package logic;
import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

import com.google.gson.*;

public class SaveNoteLogic implements Serializable{
	private static final long serialVersionUID = -8026572303276942230L;
	private Socket connection;
	private String ServerIP;
	private ObjectOutputStream output;

	public static void main(String[] args) throws IOException{
		SaveNoteLogic instance = new SaveNoteLogic();
		instance.setUserEmailFromGUI();
		instance.setNoteContentFromGUI();
		instance.setEventNameFromGUI();
		instance.javaToJson();
		instance.encryptSaveNoteObject();
		instance.connectToServer();
		instance.setupStreams();
		instance.sendObjectToServer();
	}
	
	SaveNoteObject saveNoteObject = new SaveNoteObject();
	Encryption encryptedSaveNoteObject = new Encryption();
	Gson gson = new Gson();
	String json;
	
	private void setUserEmailFromGUI(){
		saveNoteObject.setUserEmail(JOptionPane.showInputDialog("Enter user email:"));
		
		System.out.println(saveNoteObject.getUserEmail());
	}
	
	private void setNoteContentFromGUI(){
		saveNoteObject.setNoteContent(JOptionPane.showInputDialog("Enter note content"));
		
		System.out.println(saveNoteObject.getNoteContent());
	}
	
	private void setEventNameFromGUI(){
		saveNoteObject.setEventName(JOptionPane.showInputDialog("Enter event name"));
		
		System.out.println(saveNoteObject.getEventName());
	}
	
	private void javaToJson(){
		json = gson.toJson(saveNoteObject);
		System.out.println(json);
	}	
		//write converted json data to a file named "CountryGSON.json"  		
//		try {  
//		   FileWriter writer = new FileWriter("/Users/hogni/Desktop/HHPGSON.json");  
//		   writer.write(json);  
//		   writer.close();  
//		    
//		  } catch (IOException e) {  
//		   e.printStackTrace();  
//		  }  

	private byte[] encryptSaveNoteObject(){
//		encryptedSaveNoteObject.encrypt(json);
		System.out.println(encryptedSaveNoteObject.encrypt(json));
		encryptedSaveNoteObject.
	}
	
	private void connectToServer() throws IOException{
		System.out.println("Attempting connection...");
		connection = new Socket(InetAddress.getByName(ServerIP), 8349);
		System.out.println("Connected to "+connection.getInetAddress().getHostAddress());
	}
	
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.close();
		System.out.println("\nDude, your streams are now good to go! \n");
	}
	
	private void sendObjectToServer(){
		try{
			output.writeObject(json);
			output.flush();
			System.out.println("sendObjectToServer"+json);
		}catch (IOException ioException){
			System.out.println("Dude, something went wrong with sending the object to the server");
		}
	}
	
}
