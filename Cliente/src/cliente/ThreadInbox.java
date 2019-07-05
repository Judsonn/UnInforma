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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabri
 */
public class ThreadInbox extends Thread {

    private Socket socket;
    private BufferedReader br;

    /**
     * método construtor da classe
     *
     * @param socket utiliza o socket criado do cliente para enviar a resposta
     * do server
     */
    public ThreadInbox(Socket socketeleito) throws IOException {
        this.socket = socketeleito;
        //Socket que o cliente está utilizando
        InputStream entrada = socketeleito.getInputStream();
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
                try {
                    this.socket.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ThreadInbox.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (Throwable ex1) {
                    Logger.getLogger(ThreadInbox.class.getName()).log(Level.SEVERE, null, ex1);
                }
                break;
                
            }
        }
    }
}
