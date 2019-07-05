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
    
    public static void conectar(Socket socket) throws IOException {
        new ThreadInbox(socket).start(); //executa o metodo run() da classe ThreadInbox
        new ThreadCompose(socket).start(); //executa o metodo run() da classe ThreadCompose
    }

}
