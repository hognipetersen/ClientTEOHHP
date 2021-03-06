package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import logic.ConnectionsSocket;

import com.google.gson.Gson;

import shared.LogInObject;
import shared.LogInReturnObject;

public class Login extends JFrame {

	private static final long serialVersionUID = 8297256499558401484L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private String jsonIn;

	private ConnectionsSocket connectionsSocket = new ConnectionsSocket();
	private LogInObject loginObject = new LogInObject();
	private LogInReturnObject loginReturnObject = new LogInReturnObject();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JButton btnLogin = new JButton("Login");
	/**
	 * Create the frame.
	 */
	public Login() {
		super("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldUsername = new JTextField();
		textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsername.setText("Username");
		textFieldUsername.setBounds(93, 115, 134, 28);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		passwordFieldPassword.setBounds(91, 258, 137, 28);
		contentPane.add(passwordFieldPassword);


		btnLogin.setBounds(93, 401, 134, 28);
		contentPane.add(btnLogin);

		JLabel lblPassword = new JLabel("Enter password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(93, 230, 134, 16);
		contentPane.add(lblPassword);

		JLabel lblEnterUsername = new JLabel("Enter username:");
		lblEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterUsername.setBounds(93, 87, 134, 16);
		contentPane.add(lblEnterUsername);
		btnLogin.addActionListener(new ActionPerformed());
	}

	public class ActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String userName = textFieldUsername.getText();
			String password = passwordFieldPassword.getText();
			loginObject.setAuthUsername(userName);
			loginObject.setAuthPassword(password);
			loginObject.setIsAdmin(false);
			Gson gson = new Gson();
			String jsonOut = gson.toJson(loginObject);
			try {
				jsonIn = connectionsSocket.connectToServerAndSendReturnObject(jsonOut);
			} catch (IOException e) {
				e.printStackTrace();
			}
			loginReturnObject = gson.fromJson(jsonIn, LogInReturnObject.class);

			if(loginReturnObject.isLogOn()){
				MainMenu mainMenu = new MainMenu();
				mainMenu.setVisible(true);
				dispose();
			}else{
				JOptionPane.showMessageDialog(null, loginReturnObject.getExplanation());
				textFieldUsername.setText("");
				passwordFieldPassword.setText("");
				textFieldUsername.requestFocus();
			}

		}
	
	}
}

