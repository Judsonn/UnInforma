/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enumerator.AREA;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import static java.net.HttpURLConnection.HTTP_VERSION;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.Response;
import model.Projeto;
import org.omg.CORBA.Request;
import org.xml.sax.SAXException;

/**
 *
 * @author Sabrina Winckler
 */
//Classe que faz a conexão com o servidor e requisição
public class ClienteHttp {

    private Socket client;
    private AREA area;
    private ArrayList listaProjetos;
    private ArrayList listaDeCursos;

    //%%%% ATRIBUTOS PARA O T02 %%%//
    private ServerSocket server;
    private URL url;
    private HttpURLConnection conexao;

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    // MÉTODOS DEFAULT: construtor e getters             //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    public ClienteHttp() {
        this.listaProjetos = new ArrayList();
        this.listaDeCursos = new ArrayList();
    }

    public ArrayList getListaProjetos() {
        return listaProjetos;
    }

    public ArrayList getListaDeCursos() {
        return listaDeCursos;
    }

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    //     CONEXÃO UTILIZANDO Socket            //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    public void criarConexaoSocket() throws IOException {
        //Cria o socket com o host e porta que serão consultados
        this.client = new Socket("localhost", 80);

    }
    
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    //          REQUISIÇÃO GENÉRICA             //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    public BufferedReader requisitar(String arquivo) throws IOException{
    //Define requisição GET com o caminho para acessar o arquivo
        String requisicao = ""
                + "GET /uniforma/" + arquivo + " HTTP/1.1\r\n"
                + "Host: localhost\r\n"
                + "\r\n";

        //OutputStream serve para enviar a requisição, por isso o nome
        OutputStream enviador = this.client.getOutputStream();
        //Cria um vetor de bytes para enviar a requisição ao servidor
        byte[] b = requisicao.getBytes();
        //Escreve o vetor de bytes no enviador da requisição
        enviador.write(b);
        //Define finalização da escrita
        enviador.flush();
        //Define um BufferedReader para ler a partir do InputStream que vem do servidor
        BufferedReader infos = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        return infos;
    }
    
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    //     REQUISIÇÃO DE NÍVEL INTERMEDIÁRIO     //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    public void requisitarProjeto(String areaproj) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        //Define arquivo que será consultado
        String arquivo = "teste" + areaproj + ".csv";
        //Limpa lista de projetos caso haja algum registro
        this.listaProjetos.removeAll(listaProjetos);
        //Chama método que monta a lista de projetos e adiciona na lista de projetos a partir da resposta da requisição
        this.listaProjetos = Leitor.montarListaProjeto(requisitar(arquivo), areaproj);  
      
    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    //      MÉTODO QUE MONTA O RESPONSE          //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    public String dividirPorArea() throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        String response = "\n %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% \n";
        
        response += "PROJETOS DE ENSINO: ";
        response += "\n ----------------------------------- \n";
        criarConexaoSocket();
        requisitarProjeto("ensino");//ensino
        response += this.listaProjetos.toString();
        response += "\n %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% \n";
        

        response += "PROJETOS DE EXTENSÃO: ";
        response += "\n ----------------------------------- \n";
        criarConexaoSocket();
        requisitarProjeto("extensao");//extensao
        response += this.listaProjetos.toString();
        response += "\n %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% \n";

        response += "PROJETOS DE PESQUISA: ";
        response += "\n ----------------------------------- \n";
        criarConexaoSocket();
        requisitarProjeto("pesquisa");//pesquisa
        response += this.listaProjetos.toString();
        response += "\n %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% \n";

        return response;
    }

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    //     REQUISIÇÃO DE NÍVEL AVANÇADO          //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    public void requisitarCurso(String campus) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {

        //Define arquivo e caminho que será consultado
        String arquivo = "curso/" + campus + ".csv";
        //Limpa lista de cursos
        this.listaDeCursos.removeAll(listaDeCursos);
        //Chama método que monta a lista de projetos e adiciona na lista de projetos a partir da resposta da requisição
        this.listaDeCursos = Leitor.montarListaCurso(requisitar(arquivo), campus);

    }
    
    public String mostrarCursos(String campus) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException{
        String response = "\n\n O cursos disponíeveis em " + campus.toUpperCase() + " são: \n\n";
        criarConexaoSocket();
        requisitarCurso(campus);
        response += this.listaDeCursos.toString();
        return response;
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
