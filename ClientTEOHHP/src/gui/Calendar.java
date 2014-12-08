package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Calendar extends JFrame {

	private JPanel contentPane;
	
//	public static void main (String []args){
//		Calendar frameTabel = new Calendar();
//	}

	/**
	 * Create the frame.
	 */
	public Calendar() {
		super("Calendar");
		setDefaultCloseOperation(onClose());
		setBounds(100, 100, 320, 568);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelStatusBarPlaceholder = new JPanel();
		panelStatusBarPlaceholder.setBackground(Color.GRAY);
		panelStatusBarPlaceholder.setBounds(0, 0, 320, 20);
		contentPane.add(panelStatusBarPlaceholder);
		
		JButton btnCreateCalendar = new JButton("Create");
		btnCreateCalendar.setBounds(0, 497, 106, 49);
		contentPane.add(btnCreateCalendar);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(276, 21, 44, 44);
		contentPane.add(btnBack);
		
		JButton btnDeleteCalendar = new JButton("Delete");
		btnDeleteCalendar.setBounds(108, 497, 106, 49);
		contentPane.add(btnDeleteCalendar);
		
		JButton btnShareCalendar = new JButton("Share");
		btnShareCalendar.setBounds(215, 497, 106, 49);
		contentPane.add(btnShareCalendar);
		btnCreateCalendar.addActionListener(new ActionCreateCalendar());
		btnDeleteCalendar.addActionListener(new ActionDeleteCalendar());
		btnShareCalendar.addActionListener(new ActionShareCalendar());
		btnBack.addActionListener(new ActionBack());
	}

	public class ActionCreateCalendar implements ActionListener{
		public void actionPerformed(ActionEvent event){
			CreateCalendar createCalendar = new CreateCalendar();
			createCalendar.setVisible(true);
			dispose();
		}
	}

	public class ActionDeleteCalendar implements ActionListener{
		public void actionPerformed(ActionEvent event){
			DeleteCalendar deleteCalendar = new DeleteCalendar();
			deleteCalendar.setVisible(true);
			dispose();
		}
	}
	
	public class ActionShareCalendar implements ActionListener{
		public void actionPerformed(ActionEvent event){
			ShareCalendar shareCalendar = new ShareCalendar();
			shareCalendar.setVisible(true);
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
