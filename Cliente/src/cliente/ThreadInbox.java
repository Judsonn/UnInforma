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
public class ThreadInbox extends Thread {

    private BufferedReader br;
    private Socket socket;

    /**
     * método construtor da classe
     *
     * @param socket utiliza o socket criado do cliente para enviar a resposta
     * do server
     */
    public ThreadInbox(Socket socket) throws IOException { 
        //Socket que o cliente está utilizando
        this.socket = socket;

        InputStream entrada = socket.getInputStream();
        //armaneza entrada do cliente
        br = new BufferedReader(new InputStreamReader(entrada)); 

    }

    @Override
    public void run() {
        while (true) {
            try {
                //Lê linha recebida do servidor e mostra
                String response = br.readLine(); 
                System.out.println(response);

            } catch (IOException ex) {
                System.out.println("Sua conexão foi encerrada");
                break;
            }
        }
    }
}
