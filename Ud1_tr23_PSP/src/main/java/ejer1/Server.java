package ejer1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	public static final int PUERTO = 2000;
	public static void main(String[] args) 
	{
		double resultado = 0;
		
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
			
			
			resultado = Math.sqrt(flujoEntrada.read());
			System.out.println("Server: el resultado de la operacion es -> " + resultado);
			
			dataOutputStream.writeDouble(resultado);	
			
			
		} catch (IOException iOException) 
		{
			
			iOException.printStackTrace();
		}finally 
		{
			try {
				flujoEntrada.close();
				
			} catch (IOException iOException) {
				iOException.printStackTrace();
			}
			try {
				dataOutputStream.close();
				
			} catch (IOException iOException) {
				iOException.printStackTrace();
			}
			try {
				socketCliente.close();
			} catch (IOException iOExceptione) {
				
				iOExceptione.printStackTrace();
			}
			try {
				socketServidor.close();
			} catch (IOException iOException) {
				iOException.printStackTrace();
			}

		}
		
		
		
		
	}
}
