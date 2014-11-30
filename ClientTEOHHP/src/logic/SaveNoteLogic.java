package logic;

import java.io.*;

import javax.swing.JOptionPane;

import shared.Encryption;
import shared.SaveNoteObject;

import com.google.gson.*;

public class SaveNoteLogic implements Serializable{
	private static final long serialVersionUID = -8026572303276942230L;
	ConnectionsSocket connectionsSocket = new ConnectionsSocket();
	
	SaveNoteObject saveNoteObject = new SaveNoteObject();
	Encryption encryptedSaveNoteObject = new Encryption();
	Gson gson = new Gson();
	String json;
	
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
		json = gson.toJson(saveNoteObject);
		System.out.println(json);
	}
	
	public void serverCommunication() throws IOException{
		connectionsSocket.connectToServerAndSendObject(json);
	}
}
