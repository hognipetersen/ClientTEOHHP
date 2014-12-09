package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.SaveNoteObject;
import shared.SaveNoteReturnObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import logic.ConnectionsSocket;

public class DeleteNote extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7236495971658627391L;
	JPanel panel = new JPanel();
	JLabel event = new JLabel("Please type event of note to delete");
	JLabel user = new JLabel("Enter your username:");
	JTextField eventId = new JTextField();
	JTextField userId = new JTextField();
	JButton delete = new JButton("Delete");
	JButton cancel = new JButton("Cancel");
	private final JPanel panel_1 = new JPanel();
	
	public DeleteNote(){
		super("Delete Note");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 568);
		panel.setLayout(null);

		event.setBounds(10, 105, 344, 20);
		user.setBounds(10, 32, 295, 20);
		eventId.setBounds(10, 137, 144, 29);
		userId.setBounds(10, 64, 144, 29);
		delete.setBounds(0, 497, 160, 49);
		cancel.setBounds(160, 497, 160, 49);
		
		panel.add(event);
		panel.add(user);
		panel.add(eventId);
		panel.add(userId);
		panel.add(delete);
		panel.add(cancel);
		
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		delete.addActionListener(new ActionDelete());
		cancel.addActionListener(new ActionBack());
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(276, 21, 44, 44);
		panel.add(btnBack);
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(0, 0, 320, 20);
		
		panel.add(panel_1);
		btnBack.addActionListener(new ActionBack());
		
	}
	
	public class ActionBack implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Notes notes = new Notes();
			notes.setVisible(true);
			dispose();
		}
	}
	
	public class ActionDelete implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String eventID = eventId.getText();
			String userID = userId.getText();
			SaveNoteObject savenoteobject = new SaveNoteObject();
			savenoteobject.setEventName(eventID);
			savenoteobject.setUserEmail(userID);
			savenoteobject.setNoteContent(" ");
			Gson gson = new Gson();
			String jsonString = gson.toJson(savenoteobject);
			ConnectionsSocket connection = new ConnectionsSocket();
			SaveNoteReturnObject savenotereturn = new SaveNoteReturnObject();
			try {
				savenotereturn = gson.fromJson(connection.connectToServerAndSendReturnObject(jsonString), SaveNoteReturnObject.class);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			if(savenotereturn.isUpdated()){
				JOptionPane.showMessageDialog(null, "Your note has now been deleted");
			}else{
				
				JOptionPane.showMessageDialog(null, "Oops, something went wrong. Please try again");
				eventId.setText("");
				userId.setText("");
				eventId.requestFocus();
				
			}
	}
			
			
		}


}
