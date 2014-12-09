package gui;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import shared.QuoteObject;
import logic.ConnectionsSocket;

public class QOTD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7582310636159821990L;
	
	JPanel contentPane;
	JTextField author = new JTextField();
	JTextField subject = new JTextField();
	JTextArea quote = new JTextArea();

	//	public static void main (String []args){
	//		Calendar frameTabel = new Calendar();
	//	}

	/**
	 * Create the frame.
	 */

	public QOTD() {
		super("QOTD");
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

		author.setBounds(164, 277, 156, 44);
		author.setEditable(false);
		subject.setBounds(6, 277, 154, 44);
		subject.setEditable(false);
		quote.setBounds(6, 77, 308, 188);
		quote.setWrapStyleWord(true);
		quote.setLineWrap(true);
		quote.setEditable(false);

		btnBack.addActionListener(new ActionBack());
		contentPane.add(author);
		contentPane.add(subject);
		contentPane.add(quote);
		
		showQuote();
	}

	public void showQuote(){
		String getQuote = "getQuote";
		QuoteObject quoteobject = new QuoteObject();
		Gson gson = new Gson();
		ConnectionsSocket connectionsSocket = new ConnectionsSocket();
		try {
			quoteobject = gson.fromJson(connectionsSocket.connectToServerAndSendReturnObject(getQuote), QuoteObject.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		subject.setText(quoteobject.getTopic());
		quote.setText(quoteobject.getQuote());
		author.setText(quoteobject.getAuthor());

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
