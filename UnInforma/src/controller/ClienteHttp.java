/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sabrina Winckler
 */
//Classe que faz a conexão com o servidor e requisição
public class ClienteHttp {
    private ServerSocket server;
    private Socket client;
    private URL url;
    private HttpURLConnection conexao;

    public ClienteHttp() {
    }
    
    
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    //     CONEXÃO UTILIZANDO Socket            //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    
    public void criarConexaoSocket() throws IOException{
        //URL do site que será consultado
        this.url = new URL("http://unipampa.edu.br");
        this.client = new Socket(url.getHost(), 80);
        System.out.println(this.client.isConnected() + " Site: " + this.client.getInetAddress());
        
    }
    
    public void definirRequisicao(String requisicao){
        String get = "GET" + url.getPath() + "HTTP/1.1\n";
        get += "Host"+ url.getHost() + "\n\n";
    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    //     CONEXÃO UTILIZANDO HttpUrlConection  //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    
    public void criarConexaoHttp() throws IOException {

        //URL do site que será consultado
        this.url = new URL("http://unipampa.com");

        //O HttpUrlConnection class é usado para todos os tipos de requests: GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE.
        this.conexao = (HttpURLConnection) url.openConnection();

        //Define o método da conexão como GET
        conexao.setRequestMethod("GET");
    }

    public void definirParametroHttp(String parametro) throws IOException {
        //Para adicionar parametros ao request, setar true na propriedade doOutput
        //Escrever a String param1=value&param2=value no OutputStream da instancia de HttpUrlConnection
        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");
        conexao.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(conexao.getOutputStream());
        out.writeBytes(ParametroBuilder.getParamsString(parameters));
        out.flush();
        out.close();

    }
}
