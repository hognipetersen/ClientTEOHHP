package gui;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.GetCalendarObject;
import shared.GetCalendarReturnObject;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import logic.ConnectionsSocket;

public class Events extends JFrame {

	private JPanel contentPane;
	JButton addNote = new JButton("Add Note");
	JTextField username = new JTextField("Enter your username");
	JButton showCalendars = new JButton("Show calendars");
	JTextArea list = new JTextArea();
	private final JScrollPane scrollPane_1 = new JScrollPane();

	public Events() {
		super("Events");
		setDefaultCloseOperation(onClose());
		setBounds(100, 100, 320, 568);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane_1.setBounds(0, 85, 320, 270);
		contentPane.add(scrollPane_1);
		
		addNote.setBounds(96, 438, 124, 47);
		contentPane.add(addNote);
		
		username.setBounds(0, 37, 198, 30);
		contentPane.add(username);
		
		showCalendars.setBounds(190, 38, 124, 30);
		contentPane.add(showCalendars);

		JPanel panelStatusBarPlaceholder = new JPanel();
		panelStatusBarPlaceholder.setBackground(Color.GRAY);
		panelStatusBarPlaceholder.setBounds(0, 0, 320, 20);
		contentPane.add(panelStatusBarPlaceholder);

		JButton btnCreate= new JButton("Create");
		btnCreate.setBounds(0, 497, 160, 49);
		contentPane.add(btnCreate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(160, 497, 160, 49);
		contentPane.add(btnDelete);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(276, 21, 44, 44);
		contentPane.add(btnBack);
		
		JTextArea textAreaEventList = new JTextArea();
		textAreaEventList.setBounds(0, 70, 320, 300);
		contentPane.add(textAreaEventList);

		scrollPane_1.setViewportView(list);
		showCalendars.addActionListener(new ActionShowCalendars());
		btnCreate.addActionListener(new ActionCreateEvent());
		btnDelete.addActionListener(new ActionDeleteEvent());
		btnBack.addActionListener(new ActionBack());
	}

	public class ActionShowCalendars implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GetCalendarObject getCalendarObject = new GetCalendarObject();
			getCalendarObject.setUserID(username.getText());
			Gson gson = new Gson();
			String jsonString = gson.toJson(getCalendarObject);
			ConnectionsSocket connectionsSocket = new ConnectionsSocket();
			GetCalendarReturnObject calendarreturnobject = new GetCalendarReturnObject();
			try {
				calendarreturnobject = gson.fromJson(connectionsSocket.connectToServerAndSendReturnObject(jsonString), GetCalendarReturnObject.class);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			String calendars = "";
			for(ArrayList<Event> i: calendarreturnobject.getCalendars()){
				for(Event x: i){
					calendars = calendars.concat(x.getDescription());
					calendars = calendars.concat("\n");
				}
			}
			
			list.setText(calendars);
		}
		
	}
	
	public class ActionCreateEvent implements ActionListener{
		public void actionPerformed(ActionEvent event){
			CreateEvent createEvent = new CreateEvent();
			createEvent.setVisible(true);
			dispose();
		}
	}

	public class ActionDeleteEvent implements ActionListener{
		public void actionPerformed(ActionEvent event){
			DeleteEvent deleteEvent = new DeleteEvent();
			deleteEvent.setVisible(true);
			dispose();
		}
	}
	
	public class ActionBack implements ActionListener{
		public void actionPerformed(ActionEvent event){
			MainMenu mainMenu = new MainMenu();
			mainMenu.setVisible(true);
			dispose();
		}
	}


	public int onClose(){
		setVisible(false);
		return 1;
	}
}
