/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author sabri
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        int[] port;
        port = new int[2];
        port[0] = 8000;
        port[1] = 80;

        int escolhida = 0;

        int num = (int) Math.floor(Math.random() * (10 - 2 + 1) + 2);
        System.out.println(num);
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                escolhida = port[0];
                break;
            }
            if (num - i == 0) {
                escolhida = port[1];
                break;
            }
            if (num <= 7) {
                escolhida = port[1];
                break;
            }
            if (num >= 9) {
                escolhida = port[0];
                break;
            }

            escolhida = port[0];

        }

        Socket socket = new Socket("localhost", escolhida);
        System.out.println("Conectado a porta: " + escolhida);
        conectar(socket);
    }

    public static void conectar(Socket socket) throws IOException {
        new ThreadInbox(socket).start(); //executa o metodo run() da classe ThreadInbox
        new ThreadCompose(socket).start(); //executa o metodo run() da classe ThreadCompose
    }

}
