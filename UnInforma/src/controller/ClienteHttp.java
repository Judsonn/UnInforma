/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import model.Projeto;
import org.xml.sax.SAXException;

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
        //this.url = new URL("https://spreadsheets.google.com/feeds/list/1-tA1_e8ePTrBOFSkIGym6C26mW2PZCZfinE5CHV38P4/1/public/values");
        this.url = new URL("http://localhost/uniforma/");
        this.client = new Socket(url.getHost(), 80);
        
    }
    
    public void requisitarProjeto() throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException{

        String arquivo = "teste.csv";
        String get = "GET" + this.url.getPath() + arquivo + "HTTP/1.1\n";
        get += "Host " + url.getHost() + "\n\n";
        
        System.out.println(this.url.getFile() + " \n" + get);

        Leitor.carregarArquivo(this.url.toString() + arquivo);
        Leitor.montarLista();
        
    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    //     CONEXÃO UTILIZANDO HttpUrlConection  //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    
    public void criarConexaoHttp() throws IOException {

        //URL do site que será consultado
        this.url = new URL("https://docs.google.com/spreadsheets/d/1-tA1_e8ePTrBOFSkIGym6C26mW2PZCZfinE5CHV38P4");

        //O HttpUrlConnection class é usado para todos os tipos de requests: GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE.
        this.conexao = (HttpURLConnection) url.openConnection();

        //Define o método da conexão como GET
        conexao.setRequestMethod("GET");
    }

}
