package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import client.ClientChat;

public class ServerSocketChat {
	public final static Integer PORT = 9999;
	public static ServerSocket server = null;
	
	
	public static void main(String[] args) {
		
		try {
			 server =   new ServerSocket(PORT);
			 System.out.println("Servidor em execução na porta: "+ PORT);
			 
			 while(true) {
				 Socket socketClient =  server.accept();
				 new ClientChat(socketClient);
			 }
			 
		} catch (IOException errorOpen) {
			System.out.println("Erro ao instaciar servidor :" + errorOpen.getMessage());
			if(server != null) {
				try {
					server.close();
				} catch (IOException errorClose) {
					System.out.println("Erro ao fechar servidor :" + errorClose.getMessage());
				}
			}
		}
	}

}
