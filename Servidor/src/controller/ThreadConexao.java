/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Sabrina Winckler
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import view.Comandos;

public class ThreadConexao extends Thread {

    private Socket socket;
    private boolean conectado;
//    private final Server server;
    private PrintWriter print;

    public ThreadConexao(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        conectado = true;
        try {

            InputStream input = socket.getInputStream(); //cria um "leitor" para receber o que o usuário escreve
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));//Guarda o que o usuário digitou

            OutputStream enviador = socket.getOutputStream(); //receptor para o que foi escrito
            print = new PrintWriter(enviador, true); //cria um PrintWriter para printar os dados recebidos do servidor

            //String userID = String.valueOf(this.getId()); //pega o id do usuário 
            String clientMessage;
//            ClienteHttp cliente = new ClienteHttp();
//            cliente.criarConexaoSocket(socket);
            print.println(Comandos.init(socket));

            while (conectado) {
                clientMessage = reader.readLine(); //recebe do cliente o que foi digitado
                System.out.println(clientMessage); //printa no console do servidor a mensagem recebida

                print.println(Comandos.opcoes(clientMessage));
                  
                if (clientMessage.equalsIgnoreCase("\\sair")) {
                    conectado = false;
                    print.println("Programa Encerrado");
                }

            }

            socket.close(); //ao sair fecha o socket que estava sendo utilizado pelo usuário

        } catch (IOException | ParserConfigurationException | SAXException | URISyntaxException | ParseException ex) {
            Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
