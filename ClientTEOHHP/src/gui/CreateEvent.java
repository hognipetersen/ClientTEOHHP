package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import shared.CreateEventObject;
import shared.CreateEventReturnObject;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import logic.ConnectionsSocket;

public class CreateEvent extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2159230638249490832L;
	
	JPanel panel = new JPanel();
	JLabel type = new JLabel("What kind of event is it?");
	JLabel eventName = new JLabel("Event name: ");
	JLabel description = new JLabel("Undertitle:");
	JLabel location = new JLabel("Location: ");
	JLabel calendarName = new JLabel("Calendar: ");
	JLabel explanation = new JLabel("Please enter the calendar you want your event to appear in");
	JLabel startDate = new JLabel("Start date: ");
	JLabel startTime = new JLabel("Start time: ");
	JLabel endDate = new JLabel("End date: ");
	JLabel explanationDateEnd = new JLabel("Please write in format yyyy-mm-dd");
	JLabel endTime = new JLabel("End time: ");
	JLabel explanationTimeEnd = new JLabel("Please write in format hh:mm:ss in 24-hour format");
	JTextField typ = new JTextField();
	JTextField eName = new JTextField();
	JTextField desc = new JTextField();
	JTextField loc = new JTextField();
	JTextField calName = new JTextField();
	JTextField startD = new JTextField();
	JTextField startT = new JTextField();
	JTextField endD = new JTextField();
	JTextField endT = new JTextField();
	JButton createEvent = new JButton("Create event");
	JButton cancel = new JButton("Cancel");
	private final JPanel panel_1 = new JPanel();
	
	
	public CreateEvent(){
		super("Create Event");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 568);
		panel.setLayout(null);

		type.setBounds(27,101,157,20);
		eventName.setBounds(27,37,150,20);
		description.setBounds(27,133,81,20);
		location.setBounds(27,69,67,20);
		calendarName.setBounds(27, 165, 67, 20);
		explanation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		explanation.setBounds(348, 64, 288, 20);
		startDate.setBounds(27, 229, 77, 20);
		startTime.setBounds(27, 316, 77, 20);
		endDate.setBounds(27, 261, 67, 15);
		explanationDateEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		explanationDateEnd.setBounds(27, 197, 173, 20);
		endTime.setBounds(27, 348, 67, 15);
		explanationTimeEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		explanationTimeEnd.setBounds(27, 288, 260, 16);
		typ.setBounds(196,101,105,20);
		eName.setBounds(196,133,105,20);
		desc.setBounds(196,37,67,20);
		loc.setBounds(196,69,105,20);
		calName.setBounds(196, 165, 105, 20);
		startD.setBounds(196, 229, 105, 20);
		startT.setBounds(196, 316, 105, 20);
		endD.setBounds(196, 258, 105, 20);
		endT.setBounds(196, 345, 105, 20);
		createEvent.setBounds(0, 497, 160, 49);
		cancel.setBounds(160, 497, 160, 49);
		
		panel.add(type);
		panel.add(eventName);
		panel.add(description);
		panel.add(location);
		panel.add(calendarName);
		panel.add(explanation);
		panel.add(startDate);
		panel.add(startTime);
		panel.add(endDate);
		panel.add(explanationDateEnd);
		panel.add(endTime);
		panel.add(explanationTimeEnd);
		panel.add(typ);
		panel.add(eName);
		panel.add(desc);
		panel.add(loc);
		panel.add(calName);
		panel.add(startD);
		panel.add(startT);
		panel.add(endD);
		panel.add(endT);
		panel.add(createEvent);
		panel.add(cancel);
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		createEvent.addActionListener(new ActionCreateEvent());
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
			Events events = new Events();
			events.setVisible(true);
			dispose();
		}
	}
	
	public class ActionCreateEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String ty = typ.getText();	
			String en = eName.getText();
			String des = desc.getText();
			String locat = loc.getText();
			String calNa = calName.getText();
			String starD = startD.getText();
			String starT = startT.getText();
			String enD = endD.getText();
			String enT = endT.getText();
			CreateEventObject createeventobject = new CreateEventObject();
			createeventobject.setType(ty);
			createeventobject.setEventName(en);
			createeventobject.setDescription(des);
			createeventobject.setLocation(locat);
			createeventobject.setCalendarName(calNa);
			createeventobject.setStartDate(starD);
			createeventobject.setStartTime(starT);
			createeventobject.setEndDate(enD);
			createeventobject.setEndTime(enT);
			Gson gson = new Gson();
			String jsonString = gson.toJson(createeventobject);
			ConnectionsSocket connection = new ConnectionsSocket();
			CreateEventReturnObject createeventreturnobject = new CreateEventReturnObject();
			try {
				createeventreturnobject = gson.fromJson(connection.connectToServerAndSendReturnObject(jsonString), CreateEventReturnObject.class);
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			if(createeventreturnobject.isCreated()){
				JOptionPane.showMessageDialog(null, createeventreturnobject.getMessage());
			}else{
				
				JOptionPane.showMessageDialog(null, createeventreturnobject.getMessage());
			}
		}
	}
				
			
			
				
			}


