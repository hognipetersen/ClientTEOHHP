package logic;

import java.io.*;

import javax.swing.JOptionPane;

import shared.Encryption;
import shared.GetNoteObject;
import shared.SaveNoteObject;
import shared.SaveNoteReturnObject;

import com.google.gson.*;

public class SaveNote implements Serializable{
	private static final long serialVersionUID = -8026572303276942230L;
	
	private String jsonIn;
	private String jsonOut;
	
	private ConnectionsSocket connectionsSocket = new ConnectionsSocket();
	private SaveNoteObject saveNoteObject = new SaveNoteObject();
	private Gson gson = new Gson();
	private SaveNoteReturnObject saveNoteReturnObject = new SaveNoteReturnObject(); 

	
	public void setUserEmailFromGUI(){
		saveNoteObject.setUserEmail(JOptionPane.showInputDialog("Enter user email:"));
		
		System.out.println(saveNoteObject.getUserEmail());
	}
	
	public void setNoteContentFromGUI(){
		saveNoteObject.setNoteContent(JOptionPane.showInputDialog("Enter note content"));
		
		System.out.println(saveNoteObject.getNoteContent());
	}
	
	public void setEventNameFromGUI(){
		saveNoteObject.setEventName(JOptionPane.showInputDialog("Enter event name"));
		
		System.out.println(saveNoteObject.getEventName());
	}
	
	public void javaToJson(){
		jsonOut = gson.toJson(saveNoteObject);
		System.out.println(jsonOut);
	}
	
	public void serverCommunication() throws IOException{
		jsonIn = connectionsSocket.connectToServerAndSendReturnObject(jsonOut);
		saveNoteReturnObject = gson.fromJson(jsonIn, SaveNoteReturnObject.class);
		System.out.println(saveNoteReturnObject.getMessage());
	}
}
