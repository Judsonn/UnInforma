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
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 *
 * @author sabri
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, RemoteException, AccessException {

        //Conecta ao servidor líder
        InetAddress addr = InetAddress.getByName("127.0.0.1");
        Socket lider = new Socket(addr, 1224);

        //Aguarda informações do servidor líder
        InputStream entrada = lider.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
        //Recebe endereço ip e porta ao qual será alocado
        String ip = br.readLine();
        int porta = Integer.parseInt(br.readLine());
        //Quebra string do endereço IP
        String[] strIP = ip.split("/");
        InetAddress end = InetAddress.getByName(strIP[1]);
        //Fecha socket do líder neste cliente
        lider.close();
        //Cria socket com IP e porta do servidor que recebeu para o cliente utilizar
        Socket cliente = new Socket(end, porta);
        //Conecta cliente à sua thread no servidor
        conectar(cliente);

    }

    public static int sorterar() {

        int[] port;
        port = new int[2];
        port[0] = 8000;
        port[1] = 80;

        int escolhida = 0;

        int num = (int) Math.floor(Math.random() * (10 - 2 + 1) + 2);
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
        return escolhida;
    }

    public static void conectar(Socket socket) throws IOException {
        new ThreadInbox(socket).start(); //executa o metodo run() da classe ThreadInbox
        new ThreadCompose(socket).start(); //executa o metodo run() da classe ThreadCompose
    }

}
