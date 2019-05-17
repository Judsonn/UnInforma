/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enumerator.AREA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * 
 * @author Judson e Sabrina.
 * 
 */
//Classe que faz a conexão com o servidor e requisição
public class ClienteHttp {

    private Socket client;
    private AREA area;
    private ArrayList listaProjetos;
    private ArrayList listaDeCursos;
    /**
     * Atributos para o trabalho 02
     */
    private ServerSocket server;
    private URL url;
    private HttpURLConnection conexao;

    /**
     * método default, construtor getters
     */
    public ClienteHttp() throws IOException {
        this.listaProjetos = new ArrayList();
        this.listaDeCursos = new ArrayList();
    }

    public ArrayList getListaProjetos() {
        return listaProjetos;
    }

    public ArrayList getListaDeCursos() {
        return listaDeCursos;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    /**
     * CRIA conexão ultilizando socket
     *
     * @throws IOException - poderá lançar uma exceção que deve ou ser relançada
     * ou capturada pelo método que a está chamando
     */
    public void criarConexaoSocket() throws IOException {
        //Cria o socket com o host e porta que serão consultados
        this.client = new Socket("localhost", 80);

    }

    /**
     * REQUISIÇÃO GENÉRICA
     *
     * @param arquivo - define o caminho, nome e extensão do arquivo que deseja
     * consultar no servidor.
     * @return retorna uma lista de arquivos CSVs
     * @throws IOException Caso ele não consiga fazer a requisição, o método
     * indica que este método poderá lançar uma exceção que deve ou ser
     * relançada ou capturada pelo método que a está chamando
     */
    public BufferedReader requisitar(String arquivo) throws IOException {
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

    /**
     * REQUISIÇÃO DE NÍVEL INTERMEDIÁRIO
     *
     * @param areaproj - define a área do projeto para fazer a consulta no
     * servidor.
     * @throws IOException -
     * @throws ParserConfigurationException - Lança execeção de parser feito no
     * leitor que deve ou ser relançada ou capturada pelo método que a está
     * chamando
     * @throws SAXException
     * @throws URISyntaxException
     * @throws ParseException - Lança execeção de parse feito no leitor que deve
     * ou ser relançada ou capturada pelo método que a está chamando
     */
    public void requisitarProjeto(String areaproj) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        //Define arquivo que será consultado
        String arquivo = "projeto" + areaproj + ".csv";
        //Limpa lista de projetos caso haja algum registro
        this.listaProjetos.removeAll(listaProjetos);
        //Chama método que monta a lista de projetos e adiciona na lista de projetos a partir da resposta da requisição
        this.listaProjetos = Leitor.montarListaProjeto(requisitar(arquivo), areaproj);

    }

    /**
     * MÉTODO QUE MONTA O RESPONSE DIVIDINDO POR ÁREA OS PROJETOS
     *
     * @return respose em forma de string com projetos divididos por áreas
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws URISyntaxException
     * @throws ParseException
     */
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

    /**
     * REQUISIÇÃO DE NÍVEL AVANÇADO
     *
     * @param campus - Parâmetro passado para pesquisar cursos no campus
     * escolhido
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws URISyntaxException
     * @throws ParseException
     */
    public void requisitarCurso(String campus) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {

        //Define arquivo e caminho que será consultado
        String arquivo = "curso/" + campus + ".csv";
        //Limpa lista de cursos
        this.listaDeCursos.removeAll(listaDeCursos);
        //Chama método que monta a lista de projetos e adiciona na lista de projetos a partir da resposta da requisição
        this.listaDeCursos = Leitor.montarListaCurso(requisitar(arquivo), campus);

    }

    /**
     * MÉTODO CHAMADO PARA MOSTRAR CURSOS DE GRADUAÇÃO E PÓS-GRADUAÇÃO
     *
     * @param campus - Parâmetro que recebe do usuário
     * @return response com lista de cursos do campus escolhido
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws URISyntaxException
     * @throws ParseException
     */
    public String mostrarCursos(String campus) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        String response = "\n ----------------------------------------------------------- \n";
        response += "\n\n O cursos de graduação e pós-graducação disponíeveis em " + campus.toUpperCase() + " são: \n\n";
        response += "\n ----------------------------------------------------------- \n";
        criarConexaoSocket();
        requisitarCurso(campus);
        response += this.listaDeCursos.toString();
        return response;
    }

    /**
     * CONEXÃO UTILIZANDO HttpUrlConection
     *
     * @throws IOException
     */
    public void criarConexaoHttp() throws IOException {

        //URL do site que será consultado
        this.url = new URL("https://docs.google.com/spreadsheets/d/1-tA1_e8ePTrBOFSkIGym6C26mW2PZCZfinE5CHV38P4");

        //O HttpUrlConnection class é usado para todos os tipos de requests: GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE.
        this.conexao = (HttpURLConnection) url.openConnection();

        //Define o método da conexão como GET
        conexao.setRequestMethod("GET");
    }

}
