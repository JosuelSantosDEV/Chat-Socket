package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import server.ServerSocketChat;

public class ClientSocket{
	public static void main(String[] args) {
		
		
			try {
				
				final Socket client = new Socket("127.0.0.1", ServerSocketChat.PORT);
				
				// Lendo mensagem do servidor
				
				new Thread() {
					public void run() {
						
						BufferedReader bufferRead;
			
						try {
							bufferRead = new BufferedReader(new InputStreamReader(client.getInputStream()));
							
							String msg = "";
							
							while(true) {
								msg =  bufferRead.readLine();
								System.out.println(msg);
							}
						} catch (IOException errorRead) {
							System.out.println("Impossivel ler a mensagem do servidor: " + errorRead.getMessage());
							
						}
						
						
					};
				}.start();
				
				// Escrevendo para o servidor
				
				PrintWriter printWrite = new PrintWriter(client.getOutputStream(), true);
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				
				while(true) {
					String msgPronpt = bufferRead.readLine();
					printWrite.println(msgPronpt);
				}
				
				
			} catch (UnknownHostException errorHost) {
				System.out.println("O endereço host é invalido: " + errorHost.getMessage());
			} catch (IOException errorPort) {
				System.out.println("A porta está invalida: " + errorPort.getMessage());
			}
		
		
	}

}
