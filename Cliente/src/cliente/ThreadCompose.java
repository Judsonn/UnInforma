/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author sabri
 */
public class ThreadCompose extends Thread {

    private PrintWriter print;
    private Socket socket;

    /**
     * construtor da classe
     *
     * @param socket utiliza o socket criado do cliente para receber a resposta
     * do server
     */
    public ThreadCompose(Socket socket) throws IOException { //construtor do WriteThread
        this.socket = socket;

        //recebe resposta do socket
        OutputStream resposta = socket.getOutputStream(); 
        print = new PrintWriter(resposta, true); //cria um escritor com a resposta

    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msgEntrada;

            do {
                msgEntrada = br.readLine(); //
                print.println(msgEntrada);

            } while (!msgEntrada.equalsIgnoreCase("\\sair"));

            socket.close();
            
        } catch (IOException ex) {

            System.out.println("Erro ao enviar mensagem");
        }
    }
}
