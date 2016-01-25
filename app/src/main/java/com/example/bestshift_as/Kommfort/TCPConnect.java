package com.example.bestshift_as.Kommfort;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;

public class TCPConnect extends AsyncTask<JSONObject,Void,JSONObject>{
	
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	private String ip;
	private int port;
	private JSONObject response = null;
	
	@Override
	protected void onPreExecute() {
		ip = "192.168.0.1"; //Eure Raspberry PI Adresse
		port = 0000; //Der Port
	}
	@SuppressWarnings("unchecked")
	@Override
	protected JSONObject doInBackground(JSONObject...Command) {
		
		//String command = Command[0].toString() + sEnd;
	
		try {	
			socket = new Socket(ip,port);
		} catch (IOException e) {
			try {
				return (JSONObject) response.put("ERR","Fehlgeschlagen! " + e.toString());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		
		try{
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(Command[0]);
			
			in = new ObjectInputStream(socket.getInputStream());
			response = (JSONObject) in.readObject();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				socket.close();
		
				System.out.println("Connection closed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;
	}	
}