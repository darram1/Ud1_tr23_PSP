	package ejer2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.text.html.HTMLEditorKit.Parser;

public class Host {
	private static final String HOST = "localhost";
	
	public static void main(String[] args) 
	{
		double num1 = 0;
		double num2 = 0;
		char op = ' ';
		
		Scanner sc = new Scanner(System.in);
		Socket socketCliente = null;
		DataInputStream flujoEntrada = null;
		DataOutputStream dataOutputStream = null;
		
		
		try 
		{
			socketCliente = new Socket(HOST, Server.PUERTO);
			
			
			flujoEntrada = new DataInputStream(socketCliente.getInputStream());
			dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
			
			
			
			
			
			System.out.println("Numero 1: ");
			num1= Integer.parseInt(sc.nextLine());
			dataOutputStream.writeDouble(num1);
			
			System.out.println("Numero 2: ");
			num2= Integer.parseInt(sc.nextLine());
			dataOutputStream.writeDouble(num2);
			
			System.out.println("Operador: ");
			op= sc.next().charAt(0);
			dataOutputStream.writeChar(op);
			
			System.out.println("Host: el resultado de la operacion es -> "  + flujoEntrada.readDouble());
			
			
			
		} catch (UnknownHostException uenknownHostException) {
			
			uenknownHostException.printStackTrace();
		} catch (IOException iOException) 
		{
			
			iOException.printStackTrace();
		}finally 
		{
			if(sc == null)
			{
				sc.close();
			}
			
			if(dataOutputStream == null) 
			{
				try {
					dataOutputStream.close();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
			
			if(flujoEntrada == null) 
			{
				try {
					flujoEntrada.close();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
			
			if(socketCliente == null ) {
				try {
					socketCliente.close();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
		}
	}
}
