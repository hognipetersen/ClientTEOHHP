package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Weather extends JFrame {

	private JPanel contentPane;

	//	public static void main (String []args){
	//		Calendar frameTabel = new Calendar();
	//	}

	/**
	 * Create the frame.
	 */

	public Weather() {
		super("Weather");
		setDefaultCloseOperation(onClose());
		setBounds(100, 100, 320, 568);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelStatusBarPlaceholder = new JPanel();
		panelStatusBarPlaceholder.setBackground(Color.GRAY);
		panelStatusBarPlaceholder.setBounds(0, 0, 320, 20);
		contentPane.add(panelStatusBarPlaceholder);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(276, 21, 44, 44);
		contentPane.add(btnBack);

		btnBack.addActionListener(new ActionBack());
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
