package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	JButton btnCalendars= new JButton("Calendar");

	//	public static void main (String []args){
	//		Calendar frameTabel = new Calendar();
	//	}

	/**
	 * Create the frame.
	 */

	public MainMenu() {
		super("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 568);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelStatusBarPlaceholder = new JPanel();
		panelStatusBarPlaceholder.setBackground(Color.GRAY);
		panelStatusBarPlaceholder.setBounds(0, 0, 320, 20);
		contentPane.add(panelStatusBarPlaceholder);

		btnCalendars.setBounds(0, 497, 62, 49);
		contentPane.add(btnCalendars);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(276, 21, 44, 44);
		contentPane.add(btnLogout);

		JButton btnEvents = new JButton("Events");
		btnEvents.setBounds(59, 497, 64, 49);
		contentPane.add(btnEvents);

		JButton btnNotes = new JButton("Notes");
		btnNotes.setBounds(121, 497, 64, 49);
		contentPane.add(btnNotes);

		JButton btnWeather = new JButton("Weather");
		btnWeather.setBounds(182, 497, 71, 49);
		contentPane.add(btnWeather);

		JButton btnQOTD = new JButton("QOTD");
		btnQOTD.setBounds(250, 497, 71, 49);
		contentPane.add(btnQOTD);

		btnLogout.addActionListener(new ActionLogout());
		btnCalendars.addActionListener(new ActionCalendars());
		btnEvents.addActionListener(new ActionEvents());
		btnNotes.addActionListener(new ActionNotes());
		btnWeather.addActionListener(new ActionWeather());
		btnQOTD.addActionListener(new ActionQOTD());
	}

	public class ActionLogout implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Login login = new Login();
			login.setVisible(true);
			dispose();
		}
	}
	
	public class ActionCalendars implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Calendar calendar = new Calendar();
			calendar.setVisible(true);
			dispose();
		}
	}

	public class ActionEvents implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Events events = new Events();
			events.setVisible(true);
			dispose();
		}
	}
	
	public class ActionNotes implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Notes notes = new Notes();
			notes.setVisible(true);
			dispose();
		}
	}
	
	public class ActionWeather implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Weather weather = new Weather();
			weather.setVisible(true);
			dispose();
		}
	}
	
	public class ActionQOTD implements ActionListener{
		public void actionPerformed(ActionEvent event){
			QOTD qotd = new QOTD();
			qotd.setVisible(true);
			dispose();
		}
	}
}
