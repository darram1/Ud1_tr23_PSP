package ejer1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Host {
	private static final String HOST = "localhost";
	
	public static void main(String[] args) 
	{
		Socket socketCliente = null;
		DataInputStream flujoEntrada = null;
		DataOutputStream dataOutputStream = null;
		
		try 
		{
			socketCliente = new Socket(HOST, Server.PUERTO);
			
			
			flujoEntrada = new DataInputStream(socketCliente.getInputStream());
			dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
			
			
			
			dataOutputStream.write(25);
			
			System.out.println("Host: el resultado de la operacion es -> " + flujoEntrada.readDouble());
			
			
			
		} catch (UnknownHostException uenknownHostException) {
			
			uenknownHostException.printStackTrace();
		} catch (IOException iOException) 
		{
			
			iOException.printStackTrace();
		}finally 
		{
			try {
				dataOutputStream.close();
			} catch (IOException iOException) {
				iOException.printStackTrace();
			}
			try {
				flujoEntrada.close();
			} catch (IOException iOException) {
				iOException.printStackTrace();
			}
			try {
				socketCliente.close();
			} catch (IOException iOException) {
				iOException.printStackTrace();
			}
			
		}
		
	}
}
