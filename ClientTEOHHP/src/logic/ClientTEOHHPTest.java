package logic;
import java.io.IOException;


public class ClientTEOHHPTest {
	
	public static void main(String[] args) throws IOException{
		SaveNoteLogic saveNoteLogicObject = new SaveNoteLogic();
		saveNoteLogicObject.setUserEmailFromGUI();
		saveNoteLogicObject.setNoteContentFromGUI();
		saveNoteLogicObject.setEventNameFromGUI();
		saveNoteLogicObject.javaToJson();
		saveNoteLogicObject.serverCommunication();
	}

}
