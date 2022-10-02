package ejer3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server 
{
	public static final int PUERTO = 2000;
	public static void main(String[] args) 
	{
		double num1 = 0;
		double num2 = 0;
		double result = 0;
		char op = ' ';
		
		
		ServerSocket socketServidor = null;
		Socket socketCliente = null;
		DataOutputStream dataOutputStream = null;
		DataInputStream flujoEntrada = null;
		
		try 
		{
			socketServidor = new ServerSocket(PUERTO);
			System.out.println("Escucho el puerto " + PUERTO);
			
			
			socketCliente = socketServidor.accept();
			
			dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
			flujoEntrada = new DataInputStream(socketCliente.getInputStream());
			
				
			Date fecha1 = new Date();
			fecha1.setTime(flujoEntrada.readLong());
			Date fecha2 = new Date();
			fecha2.setTime(flujoEntrada.readLong());
			
			if(fecha1.before(fecha2)) 
			{
				dataOutputStream.writeLong(fecha1.getTime());
				
			}else{
				dataOutputStream.writeLong(fecha2.getTime());
			}
			
		} 
		catch (IOException iOException) 
		{
			
			iOException.printStackTrace();
		}finally 
		{
			if(flujoEntrada == null) 
			{
				try {
					flujoEntrada.close();
					
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
			if(dataOutputStream == null)
			{
				try {
					dataOutputStream.close();
					
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
			if(socketCliente == null)
			{
				try {
					socketCliente.close();
				} catch (IOException iOExceptione) {
					
					iOExceptione.printStackTrace();
				}
			}
			if(socketServidor == null)
			{
				try {
					socketServidor.close();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
		}
	}
}
