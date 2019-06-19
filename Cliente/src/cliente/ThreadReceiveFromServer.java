/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author sabri
 */
public class ThreadReceiveFromServer extends Thread{
    private BufferedReader reader;
    private Socket socket;
    /**
     * método construtor da  classe
     * @param socket utiliza o socket criado do cliente para enviar a resposta do server
     */
    public ThreadReceiveFromServer(Socket socket) { //construtor recebe o socket setado ao criar o cliente
        this.socket = socket;

        try {
            InputStream input = socket.getInputStream(); 
            reader = new BufferedReader(new InputStreamReader(input)); //recebe e lê o input do cliente
        } catch (IOException ex) {
            System.out.println("Erro ao pegar input: " + ex.getMessage());
        }
    }

    /**
     *executa o método leitor do cliente
     */
    @Override
    public void run() { 
        while (true) {
            try {
                System.out.print(">> ");
                String response = reader.readLine(); //recebe a linha escrita do cliente para o server
                System.out.println(response);

            } catch (IOException ex) {
                System.out.println("Você foi desconectado do servidor: " + ex.getMessage());
                break;
            }
        }
    }
}

