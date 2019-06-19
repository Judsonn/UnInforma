/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author sabri
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8000);
         new ThreadReceiveFromServer(socket).start(); //executa o metodo run() da classe ReadThread
         new ThreadSendToServer(socket).start(); //executa o metodo run() da classe WriteThread
    }

    public void conectar() throws IOException {

    }

    public void sair() throws IOException {

        //socket.close();
    }
}
