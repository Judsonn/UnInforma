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
import java.util.Scanner;
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
        //Scanner para setar endereços ip dos servidores
        Scanner sc = new Scanner(System.in);
        //Cria o Servidor líder que precisa estar no mesmo IP e Porta que o clinte "burro" vai acessar inicialmente, neste caso o localhost
        InetAddress addr = InetAddress.getByName("127.0.0.1"); 
        lider = new Server(1224, addr);
        
        
        //Mostra líder no servidor
        System.out.println("LÍDER: "+ lider.getServidor().toString());
        
        System.out.println("Por favor informe o IP do servidor 1: ");
        String ip1 = sc.next();
        System.out.println("Por favor informe o IP do servidor 2: ");
        String ip2 = sc.next();
        
        //Executa grupo de servidores "escravos"
        executarGrupo(ip1, ip2);

        
        boolean vivo = true;
        
        //Aguarda conexões de clientes e aloca-os
        do {
            Socket socket = lider.getServidor().accept();
            OutputStream enviador = socket.getOutputStream();
            PrintWriter print = new PrintWriter(enviador, true);

            ServerSocket server = alocarCliente();

            //Passa informações do servidor ao qual será alocado para o Cliente
            print.println(server.getInetAddress());
            print.println(server.getLocalPort());
        }while(vivo);

    }

    //Método que executa grupo de servidores 
    public static void executarGrupo(String ip1, String ip2) throws IOException {

        ExecutorService pool = Executors.newFixedThreadPool(20);
        
        //Armazena portas em um vetor
        int[] port;
        port = new int[2];
        port[0] = 8000;
        port[1] = 80;
        InetAddress[] addr = gerarEndIP(ip1, ip2);

        for (int i = 0; i < port.length; i++) {
            //cria um novo server para cada porta de conexao e Adiciona endereço IP
            Server servidor = new Server(port[i], addr[i]);

            //Adiciona na listta de servidores rodando
            servidores.add(servidor);
            //Executa servidor
            pool.execute(servidor);
        }
    }

    //Método que gera o endereço IP 
    public static InetAddress[] gerarEndIP(String ip1, String ip2) throws IOException {
        
        //Armazena Ips em um vetor
        InetAddress inet[] = new InetAddress[2];
        //Lista de endereços: 
        //Endereço Ipv4 da rede de casa = 192.168.1.106, localhost do Xampp = 127.0.0.1, Ipv4 da rede Eduroam = 10.2.243.196 e VM = 192.168.56.1
        inet[0] = InetAddress.getByName(ip1);
        inet[1] = InetAddress.getByName(ip2); 
        return inet;
    }

    //Método que aloca o cliente
    public static ServerSocket alocarCliente() {
        int porta = sorterarPortaCliente();
        Server server = pegarServidor(porta);
        return server.getServidor();
    }
    
//Método que sorteia a porta do cliente
    public static int sorterarPortaCliente() {

        int[] port;
        port = new int[2];
        port[0] = 8000;
        port[1] = 80;

        int escolhida = 0;
        
        //Número randomico  para sorteio de porta
        int num = (int) Math.floor(Math.random() * (10 - 2 + 1) + 2);
        //Itera sobre num randomico
        for (int i = 1; i < num; i++) {
            //Condições para a escolha da porta!
            if (num - i == 0) {
                escolhida = port[1];
                break;
            }
            if (num <= 3) {
                escolhida = port[1];
                break;
            }
            if (num + i == 10) {
                escolhida = port[0];
                break;
            }
            if(num >= 6){
                escolhida = port[0];
                break;
            }
            escolhida = port[0];
        }
        //Retorna porta sorteada
        return escolhida;
    }

    //Pega servidor a partir da porta sorteada
    public static Server pegarServidor(int porta) {
        for (Server server : servidores) {
            if (server.getPorta() == porta) {
                return server;
            }
        }
        return null;
    }

}
