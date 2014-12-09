package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.DeleteEventObject;
import shared.DeleteEventReturnObject;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import logic.ConnectionsSocket;

public class DeleteEvent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6305776104349130233L;
	
	JPanel panel = new JPanel();
	JLabel UN = new JLabel("Insert your username: ");
	JLabel ED = new JLabel("Please type in the event you want to delete: ");
	JTextField username = new JTextField();
	JTextField eventToDelete = new JTextField();
	JButton delete = new JButton("Delete");
	JButton cancel = new JButton("Cancel");
	private final JPanel panel_1 = new JPanel();
	
	public DeleteEvent(){
		super("Delete Event");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 568);
		panel.setLayout(null);

		UN.setBounds(25, 133, 150, 20);
		ED.setBounds(25, 277, 295, 20);
		username.setBounds(25, 156, 144, 29);
		eventToDelete.setBounds(25, 303, 144, 29);
		delete.setBounds(0, 497, 160, 49);
		cancel.setBounds(160, 497, 160, 49);
		
		panel.add(UN);
		panel.add(ED);
		panel.add(username);
		panel.add(eventToDelete);
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
	
	public class ActionDelete implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String uname = username.getText();
			String eventdel = eventToDelete.getText();
			DeleteEventObject deleteeventobject = new DeleteEventObject();
			deleteeventobject.setuserID(uname);
			deleteeventobject.setEventToDelete(eventdel);
			Gson gson = new Gson();
			String jsonString = gson.toJson(deleteeventobject);
			ConnectionsSocket connection = new ConnectionsSocket();
			DeleteEventReturnObject deleteeventreturnobject = new DeleteEventReturnObject();
			try {
				deleteeventreturnobject = gson.fromJson(connection.connectToServerAndSendReturnObject(jsonString), DeleteEventReturnObject.class);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(deleteeventreturnobject.isDeleted()){
				JOptionPane.showMessageDialog(null, deleteeventreturnobject.getMessage());
			}else{
				
				JOptionPane.showMessageDialog(null, deleteeventreturnobject.getMessage());
				eventToDelete.setText("");
				eventToDelete.requestFocus();
			}
	}
	
	}
	
	public class ActionBack implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Events events = new Events();
			events.setVisible(true);
			dispose();
		}
	}


}
