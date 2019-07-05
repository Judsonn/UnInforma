/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author sabri
 */
/*
 * Gerencia escalabilidade dos servers
 */
public class MultiServer {

    private static List<Server> servidores = new ArrayList<Server>();
    private static Server lider;

    public static List getServidores() {
        return servidores;
    }

    public static void main(String[] args) throws IOException {

        InetAddress addr = InetAddress.getByName("127.0.0.1"); //localhost
        lider = new Server(1224, addr);
        executarGrupo();

        //Para aguardar conexões de clientes e alocá-los
        while (true) {
            Socket socket = lider.getServidor().accept();
            System.out.println(socket.toString());

            OutputStream enviador = socket.getOutputStream();
            PrintWriter print = new PrintWriter(enviador, true);

            ServerSocket server = alocarCliente();

            //Passa informações do servidor ao qual será alocado para o Cliente
            print.println(server.getInetAddress());
            print.println(server.getLocalPort());
        }

    }

    public static void executarGrupo() throws IOException {

        ExecutorService pool = Executors.newFixedThreadPool(20);
        int[] port;
        port = new int[2];
        port[0] = 8000;
        //  port[1] = 80;
        InetAddress[] addr = generateEndIP();

        for (int i = 0; i < port.length; i++) {
            //cria um novo server para cada porta de conexao e Adiciona endereço IP
            Server servidor = new Server(port[i], addr[i]);

            //Adiciona na listta de servidores rodando
            servidores.add(servidor);
            //Executa servidor
            pool.execute(servidor);
        }
    }

    public static InetAddress[] generateEndIP() throws IOException {
        InetAddress inet[] = new InetAddress[2];
        inet[0] = InetAddress.getByName("127.0.0.1");     //Endereço do localhost do Xampp
        // inet[1] = InetAddress.getByName("192.168.1.115"); //Endereço Ipv4 da rede Eduroam = 10.2.243.196
        return inet;
    }

    public static ServerSocket alocarCliente() {
        int porta = sorterarPortaCliente();
        Server server = pegarServidor(porta);
        return server.getServidor();
    }

    public static int sorterarPortaCliente() {

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

    public static Server pegarServidor(int porta) {
        for (Server server : servidores) {
            if (server.getPorta() == porta) {
                return server;
            }
        }
        return null;
    }

}
