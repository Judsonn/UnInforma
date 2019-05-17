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
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ThreadConexao implements Runnable {

    private final Socket socket;
    private boolean conectado;

    public ThreadConexao(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        conectado = true;
        //imprime na tela o IP do cliente
        System.out.println(socket.getInetAddress());
        while (conectado) {
            try {
                ClienteHttp cliente = new ClienteHttp();
                //cria uma requisicao a partir do InputStream do cliente
                System.out.println(cliente.dividirPorArea());
                System.out.println(cliente.mostrarCursos("alegrete"));
                //RequisicaoHTTP requisicao = RequisicaoHTTP.lerRequisicao(socket.getInputStream());
                //se a conexao esta marcada para se mantar viva entao seta keepalive e o timeout

                //se o caminho foi igual a / entao deve pegar o /index.html
            } catch (IOException ex) {
                //quando o tempo limite terminar encerra a thread
                if (ex instanceof SocketTimeoutException) {
                    try {
                        conectado = false;
                        socket.close();
                    } catch (IOException ex1) {
                        Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
