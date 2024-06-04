package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientChat extends Thread{

	private Socket client;
	public String nameClient;
	
	public ClientChat(Socket cliente) {
		this.client = cliente;
		start();
	}
	
	@Override
	public void run() {
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter printWrite = new PrintWriter(client.getOutputStream(), true);
			
			printWrite.println("Informe seu nome: ");
			String name =  bufferRead.readLine();
			this.nameClient =  name;
			printWrite.println("Server: Olá "+ this.nameClient);
			
			String msg = "";
			
			while(true) {
				msg =  bufferRead.readLine();
				printWrite.println("Você: "+ msg);
			}
			
		} catch (IOException errorGetInputStream) {
			System.out.println("Erro ao obter e ler mensagem do  cliente: "+ errorGetInputStream.getMessage());
		}
		
	}

}
