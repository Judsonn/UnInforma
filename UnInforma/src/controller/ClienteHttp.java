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
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import model.Projeto;

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
        //this.url = new URL("https://spreadsheets.google.com/feeds/list/1-tA1_e8ePTrBOFSkIGym6C26mW2PZCZfinE5CHV38P4/1/public/values?alt=json");
       
        
        this.client = new Socket(url.toString(), 80);
        
    }
    
    public void requisitar() throws IOException{

        String g = "https://spreadsheets.google.com/feeds/list/1-tA1_e8ePTrBOFSkIGym6C26mW2PZCZfinE5CHV38P4/1/public/values?alt=json";
        String get = "GET" + g + "HTTP/1.1\n";
        //get += "Host " + url.getHost() + "\n\n";
        
        ObjectOutputStream saida = new ObjectOutputStream(this.client.getOutputStream());
        Projeto p = new Projeto(" ");
        saida.writeObject(p);
        
        System.out.println(saida.toString());
        System.out.println(p.toString());
        
        
        PrintWriter s_out = null;
	BufferedReader s_in = null;
        s_out = new PrintWriter(this.client.getOutputStream(), true);
        
        s_in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
       
        //Get response from server
        System.out.println(s_out.toString());
        System.out.println(p.toString());
        System.out.println(s_in.toString());
        System.out.println(s_in.readLine());
	String response;
	while ((response = s_in.readLine()) != null) 
	{
		System.out.println( response );
	}
       

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
