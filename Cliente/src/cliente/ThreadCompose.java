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
import java.util.ArrayList;
import java.util.List;

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
     * @param socket para enviar mensagem ao sever
     * 
     */
    public ThreadCompose(Socket socketeleito) throws IOException { //construtor do ThreadCompose
        this.socket = socketeleito;
        //recebe mensagem do socket
        OutputStream mensagem = socketeleito.getOutputStream(); 
        //cria um escritor para a mensagem
        print = new PrintWriter(mensagem, true); 

    }

    @Override
    public void run() {
        try {
            //Recebe informações digitadas no console pelo cliente
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msgEntrada;

            do {
                //Le mensagem a ser enviada ao servidor e envia através do print
                msgEntrada = br.readLine(); 
                print.println(msgEntrada);

            } while (!msgEntrada.equalsIgnoreCase("\\sair"));//Enquanto o usuário não sair

            socket.close();
            
        } catch (IOException ex) {

            System.out.println("Erro ao enviar mensagem");
        }
    }
}
