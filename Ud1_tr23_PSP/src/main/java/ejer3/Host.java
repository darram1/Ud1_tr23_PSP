	package ejer3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.text.html.HTMLEditorKit.Parser;

public class Host {
	private static final String HOST = "localhost";
	
	public static void main(String[] args) 
	{
		
		String date1 = "12-12-2012";
		String date2 = "21-12-2020";
		
		Scanner sc = new Scanner(System.in);
		Socket socketCliente = null;
		DataInputStream flujoEntrada = null;
		DataOutputStream dataOutputStream = null;
		SimpleDateFormat f = null;
		
		try 
		{
			socketCliente = new Socket(HOST, Server.PUERTO);
			
			
			flujoEntrada = new DataInputStream(socketCliente.getInputStream());
			dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
			
			f = new SimpleDateFormat("dd-mm-yyyy");
			
			Date fecha1 = f.parse(date1);
			Date fecha2 = f.parse(date2);
			
			dataOutputStream.writeLong(fecha1.getTime());
			dataOutputStream.writeLong(fecha2.getTime());
			
			fecha1.setTime( flujoEntrada.readLong() );
			
			System.out.println( "La fecha mas antigua es: " + fecha1 ) ;
			
			
			
		} catch (UnknownHostException uenknownHostException) {
			
			uenknownHostException.printStackTrace();
		} catch (IOException iOException) 
		
		{
			
			iOException.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
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
