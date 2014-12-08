package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Notes extends JFrame {

	private JPanel contentPane;

	//	public static void main (String []args){
	//		Calendar frameTabel = new Calendar();
	//	}

	/**
	 * Create the frame.
	 */

	public Notes() {
		super("Notes");
		setDefaultCloseOperation(onClose());
		setBounds(100, 100, 320, 568);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		
		JTextArea textAreaNotesList = new JTextArea();
		textAreaNotesList.setBounds(0, 64, 320, 434);
		contentPane.add(textAreaNotesList);

		btnCreate.addActionListener(new ActionCreateNote());
		btnDelete.addActionListener(new ActionDeleteNote());
		btnBack.addActionListener(new ActionBack());
	}

	public class ActionCreateNote implements ActionListener{
		public void actionPerformed(ActionEvent event){
			CreateNote createNote = new CreateNote();
			createNote.setVisible(true);
			dispose();
		}
	}

	public class ActionDeleteNote implements ActionListener{
		public void actionPerformed(ActionEvent event){
			DeleteNote deleteNote = new DeleteNote();
			deleteNote.setVisible(true);
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
