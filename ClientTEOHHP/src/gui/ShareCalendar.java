package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ShareCalendar extends JFrame {

	private static final long serialVersionUID = 7387816570062683716L;
	private JPanel contentPane;
	
	public ShareCalendar() {
		super("ShareCalendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel lblThisFunctionIs = new JLabel("This function is not available yet.");
		lblThisFunctionIs.setBounds(52, 230, 215, 20);
		contentPane.add(lblThisFunctionIs);

		btnBack.addActionListener(new ActionBack());
	}
	
	public class ActionBack implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Calendar calendar = new Calendar();
			calendar.setVisible(true);
			dispose();
		}
	}
	
}
