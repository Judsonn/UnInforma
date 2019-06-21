/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.ServerSocket;
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

    private static List servidores = new ArrayList();

    public static List getServidores() {
        return servidores;
    }

    public static void main(String[] args) throws IOException {

        ExecutorService pool = Executors.newFixedThreadPool(20);
        int[] port;
        port = new int[2];
        port[0] = 8000;
        port[1] = 80;

        for (int i = 0; i < port.length; i++) {
            //cria um novo server para cada porta de conexao
            Server servidor = new Server(port[i]);
            //Adiciona na listta de servidores rodando
            servidores.add(servidor);
            //Executa servidor
            pool.execute(servidor);
        }
    }

}
