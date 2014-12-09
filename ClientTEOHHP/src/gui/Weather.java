package gui;

import javax.swing.*;

import shared.Forecast;
import shared.WeatherReturObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import logic.ConnectionsSocket;

public class Weather extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	JTextArea weatherText = new JTextArea();

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
		scrollPane.setSize(300, 300);
		scrollPane.setLocation(10, 77);
		
		contentPane.add(scrollPane);

		JPanel panelStatusBarPlaceholder = new JPanel();
		panelStatusBarPlaceholder.setBackground(Color.GRAY);
		panelStatusBarPlaceholder.setBounds(0, 0, 320, 20);
		contentPane.add(panelStatusBarPlaceholder);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(276, 21, 44, 44);
		contentPane.add(btnBack);

		btnBack.addActionListener(new ActionBack());
		forecast();
	}
	
	public void forecast(){
		String getForecast = "getClientForecast";
		WeatherReturObject weather = new WeatherReturObject();
		Gson gson = new Gson();
		ConnectionsSocket connectionsSocket = new ConnectionsSocket();
		try {
			weather = gson.fromJson(connectionsSocket.connectToServerAndSendReturnObject(getForecast), WeatherReturObject.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String weatherDay = "";
		for(Forecast i: weather.getWeather()){
				weatherDay = weatherDay.concat("Day: " + i.getDate());
				weatherDay = weatherDay.concat("\n");
				weatherDay = weatherDay.concat("Celsius " + i.getCelsius());
				weatherDay = weatherDay.concat("\n");
				weatherDay = weatherDay.concat(i.getDesc());
				weatherDay = weatherDay.concat("\n" + "\n");
			}
		scrollPane.setViewportView(weatherText);
		weatherText.setText(weatherDay);
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
