package gui;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.swing.*;

import logic.ConnectionsSocket;
import shared.CreateCalendarObject;
import shared.CreateCalendarReturnObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

	public class CreateCalendar extends JFrame{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 128743479876712760L;
		
		String msg = "";
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> author = new ArrayList<String>();
		JPanel contentPane = new JPanel();
		JButton btnCreateCalendar = new JButton("Create");
		JButton btnCancel= new JButton("Cancel");
		JTextField txtCname = new JTextField(30);
		JTextField txtPrivPub = new JTextField(30);
		JTextField txtUser = new JTextField(30);
		JTextField txtAuthor = new JTextField(30);
		JButton btnAddUser = new JButton("Add");
		JButton btnAddAuthors = new JButton("Add");
		JTextArea textAreaUsers = new JTextArea();
		JTextArea textAreaAuthors = new JTextArea();
		JLabel PW = new JLabel("Please write 1 for private and 0 for public");
		JLabel CN = new JLabel("Calendar name: ");
		JLabel PP = new JLabel ("Is it private or public?");
		JLabel US = new JLabel("Insert subscriber username:");
		JLabel AU = new JLabel("Insert author username:");
		
		
		public CreateCalendar(){
			super("Create Calendar");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(100, 100, 320, 568);
			contentPane.setLayout(null);
			
			
			txtCname.setBounds(187,80,120,20);
			txtPrivPub.setText("1(Priv.) - 0(Publ.)");
			txtPrivPub.setBounds(187,114,120,20);
			txtUser.setBounds(35,346,58,20);
			txtAuthor.setBounds(35,183,58,20);
			btnCreateCalendar.setBounds(0, 497, 160, 49);;
			btnCancel.setBounds(160, 497, 160, 49);
			btnAddUser.setBounds(87, 343, 67, 29);
			btnAddAuthors.setBounds(87, 180, 67, 29);
			textAreaUsers.setBounds(157, 346, 150, 117);
			textAreaAuthors.setBounds(157, 183, 150, 117);
			CN.setBounds(35, 75, 115, 30);
			PP.setBounds(35, 117, 150, 15);
			US.setBounds(35, 311, 185, 30);
			AU.setBounds(35, 156, 150, 15);
			PW.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			PW.setBounds(344, 115, 198, 16);
			
			
			contentPane.add(txtCname);
			contentPane.add(txtPrivPub);
			contentPane.add(txtUser);
			contentPane.add(txtAuthor);
			contentPane.add(btnCreateCalendar);
			contentPane.add(btnAddUser);
			contentPane.add(btnAddAuthors);
			contentPane.add(btnCancel);
			contentPane.add(textAreaUsers);
			contentPane.add(textAreaAuthors);
			contentPane.add(CN);
			contentPane.add(PP);
			contentPane.add(US);
			contentPane.add(AU);
			contentPane.add(PW);
			
			getContentPane().add(contentPane);
			
			JButton btnBack = new JButton("Back");
			btnBack.setBounds(276, 21, 44, 44);
			contentPane.add(btnBack);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.GRAY);
			panel.setBounds(0, 0, 320, 20);
			contentPane.add(panel);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			btnCreateCalendar.addActionListener(new ActionCreateCalendar());
			btnAddAuthors.addActionListener(new ActionAddAuthors());
			btnAddUser.addActionListener(new ActionAddUser());
			btnCancel.addActionListener(new ActionBack());
			btnBack.addActionListener(new ActionBack());
			
		}
		
		public class ActionBack implements ActionListener{
			public void actionPerformed(ActionEvent event){
				Calendar calendar = new Calendar();
				calendar.setVisible(true);
				dispose();
			}
		}
		
		public class ActionCreateCalendar implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
							String cname = txtCname.getText();
							String privpub = txtPrivPub.getText();
							CreateCalendarObject createcalendar = new CreateCalendarObject(cname, privpub, user, author);
							createcalendar.setCalendarName(cname);
							createcalendar.setPrivatePublic(privpub);
							createcalendar.setUsers(user);
							createcalendar.setAuthors(author);
							Gson gson = new Gson();
							String jsonString = gson.toJson(createcalendar);
							ConnectionsSocket connection = new ConnectionsSocket();
							CreateCalendarReturnObject createcalendarreturn = new CreateCalendarReturnObject();
							try {
								msg = connection.connectToServerAndSendReturnObject(jsonString);
							} catch (JsonSyntaxException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							if(createcalendarreturn.isCreated()){
								JOptionPane.showMessageDialog(null, msg);
								setVisible(false);
							}else{
								
								JOptionPane.showMessageDialog(null, msg);
							}
						}
					}
			
				public class ActionAddAuthors implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						author.add(txtAuthor.getText());
						String arrayListOutput = "";
						for (String i: author){
							arrayListOutput = arrayListOutput.concat(i);
							arrayListOutput = arrayListOutput.concat("\n");
						}
						textAreaAuthors.setText(arrayListOutput);
						txtAuthor.setText("");
						txtAuthor.requestFocus();
						
					}
					
				}
				
				
				public class ActionAddUser implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						user.add(txtUser.getText());
						String arrayListOutput = "";
						for (String i: user){
							arrayListOutput = arrayListOutput.concat(i);
							arrayListOutput = arrayListOutput.concat("\n");
						}
						textAreaUsers.setText(arrayListOutput);
						txtUser.setText("");
						txtUser.requestFocus();
						
					}
					
				}
	}
	


