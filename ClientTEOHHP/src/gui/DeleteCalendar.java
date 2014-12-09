package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.ConnectionsSocket;
import shared.DeleteCalendarObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.awt.Font;
import java.awt.Color;

public class DeleteCalendar extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5034047532157719179L;

	String msg = "";
	JPanel contentPane = new JPanel();
	JLabel UN = new JLabel("Insert your username: ");
	JLabel CD = new JLabel("Please type in the calendar you want to delete: ");
	JTextField username = new JTextField();
	JTextField calToDelete = new JTextField();
	JButton delete = new JButton("Delete");
	JButton cancel = new JButton("Cancel");

	public DeleteCalendar(){
		super("Delete Calendar");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 568);
		contentPane.setLayout(null);

		UN.setBounds(88, 130, 152, 20);
		CD.setBounds(19, 321, 295, 20);
		username.setBounds(88, 162, 144, 29);
		calToDelete.setBounds(88, 353, 144, 29);
		delete.setBounds(0, 497, 160, 49);
		cancel.setBounds(160, 497, 160, 49);

		contentPane.add(UN);
		contentPane.add(CD);
		contentPane.add(username);
		contentPane.add(calToDelete);
		contentPane.add(delete);
		contentPane.add(cancel);


		getContentPane().add(contentPane);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(276, 21, 44, 44);
		contentPane.add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 320, 20);
		contentPane.add(panel);
		btnBack.addActionListener(new ActionBack());

		delete.addActionListener(new ActionDelete());
		cancel.addActionListener(new ActionBack());
	}

	public class ActionBack implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Calendar calendar = new Calendar();
			calendar.setVisible(true);
			dispose();
		}
	}

	public class ActionDelete implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String uname = username.getText();
			String caldel = calToDelete.getText();
			DeleteCalendarObject deletecalobject = new DeleteCalendarObject();
			deletecalobject.setuserID(uname);
			deletecalobject.setCalendarToDelete(caldel);
			Gson gson = new Gson();
			String jsonString = gson.toJson(deletecalobject);
			ConnectionsSocket connection = new ConnectionsSocket();
			try {
				msg = connection.connectToServerAndSendReturnObject(jsonString);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, msg);
			calToDelete.setText("");
			calToDelete.requestFocus();
		}
	}	
}



