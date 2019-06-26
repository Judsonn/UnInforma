/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabri
 */
public class Server implements Runnable {
    
    private ServerSocket servidor;
    private int porta;

    public Server(int port) throws IOException {
        servidor = new ServerSocket(port);
        porta = port;
    }
    
    public Server() {
        
    }
    
    public ServerSocket getServidor() {
        return servidor;
    }

    public int getPorta() {
        return porta;
    }
    
    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
       
    }
    
//    public static void main(String[] args) throws IOException {
//        /* cria um socket "servidor" associado a porta 8000
//         já aguardando conexões
//         */
//        ServerSocket servidor = new ServerSocket(8000);
//        ExecutorService pool = Executors.newFixedThreadPool(20);
//
//        while (true) {
//            //cria uma nova thread para cada nova solicitacao de conexao
//            pool.execute(new ThreadConexao(servidor.accept()));
//        }
//    }

    @Override
    public void run() {
        /* fica aguardando conexões
         */
        
        try {
            
            ExecutorService pool = Executors.newFixedThreadPool(20);

            while (true) {
                //cria uma nova thread para cada nova solicitacao de conexao
                pool.execute(new ThreadConexao(servidor.accept()));
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
