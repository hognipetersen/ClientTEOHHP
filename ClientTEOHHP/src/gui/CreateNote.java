package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.ConnectionsSocket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.SaveNoteObject;
import shared.SaveNoteReturnObject;
import java.awt.Font;
import java.awt.Color;

public class CreateNote extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -723347384797988705L;
	
	JPanel panel = new JPanel();
	JLabel username = new JLabel("Please enter your username: ");
	JLabel content = new JLabel("Note content: ");
	JLabel eventName = new JLabel("Enter Event to add note to");
	JTextField name = new JTextField();
	JTextArea con = new JTextArea();
	JTextField eventN = new JTextField();
	JButton addNote = new JButton("Add note");
	JButton cancel = new JButton("Cancel");
	private final JPanel panel_1 = new JPanel();
	
	public CreateNote(){
		super("Create note");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 568);
		panel.setLayout(null);

		username.setBounds(6,96,180,20);
		content.setBounds(6,160,150,20);
		eventName.setBounds(6,32,180,20);
		name.setBounds(6,128,150,20);
		con.setBounds(6,184,308,301);
		eventN.setBounds(6,64,150,20);
		addNote.setBounds(0, 497, 160, 49);
		cancel.setBounds(160, 497, 160, 49);
		
		panel.add(username);
		panel.add(content);
		panel.add(eventName);
		panel.add(name);
		panel.add(con);
		panel.add(eventN);
		panel.add(addNote);
		panel.add(cancel);
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		addNote.addActionListener(new ActionAddNote());
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

	public class ActionAddNote implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String uname = name.getText();
			String cont = con.getText();
			String nameE = eventN.getText();
			SaveNoteObject savenoteobject = new SaveNoteObject();
			savenoteobject.setUserEmail(uname);
			savenoteobject.setNoteContent(cont);
			savenoteobject.setEventName(nameE);
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
				JOptionPane.showMessageDialog(null, savenotereturn.getMessage());
			}else{
				
				JOptionPane.showMessageDialog(null, savenotereturn.getMessage());
				name.setText("");
				con.setText("");
				eventN.setText("");
				name.requestFocus();
				
			}
	}
			
			
		}
			
		}

