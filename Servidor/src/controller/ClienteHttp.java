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
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Judson e Sabrina.
 *
 */
//Classe que faz a conexão com o servidor e requisição
public class ClienteHttp {

    private static AREA area;
    private static ArrayList listaProjetos = new ArrayList();
    private static ArrayList listaDeCursos = new ArrayList();
    /**
     * Atributos para o trabalho 02
     */

    private HttpURLConnection conexao;
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://localhost:8080/uniforma/";

    

    public ArrayList getListaProjetos() {
        return listaProjetos;
    }

    public ArrayList getListaDeCursos() {
        return listaDeCursos;
    }

    /**
     * CRIA conexão ultilizando socket
     *
     * @param socket
     * @throws IOException - poderá lançar uma exceção que deve ou ser relançada
     * ou capturada pelo método que a está chamando
     */
    public static void criarSocket(Socket socket, long cliente) throws IOException {
        //Cria o socket com o host e porta que serão consultados
       

    }

    /**
     * CONEXÃO UTILIZANDO HttpUrlConection
     *
     * REQUISIÇÃO GENÉRICA
     *
     * @param arquivo - define o caminho, nome e extensão do arquivo que deseja
     * consultar no servidor.
     * @return retorna uma lista de arquivos CSVs
     * @throws IOException Caso ele não consiga fazer a requisição, o método
     * indica que este método poderá lançar uma exceção que deve ou ser
     * relançada ou capturada pelo método que a está chamando
     */
    static String requisitarGET(String arquivo, long cliente) throws IOException {
        URL url = new URL(GET_URL + arquivo + ".csv");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = conexao.getResponseCode();
        System.out.println(" GET Response Code :: " + responseCode +" ]");
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader infos = new BufferedReader(new InputStreamReader(
                    conexao.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = infos.readLine()) != null) {
                response.append(inputLine).append("\n");
            }
            infos.close();

            return response.toString();
        } else {
            System.out.println(" Requisição GET  não funcionou ]");
        }
        return null;
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
    static String requisitarProjeto(String areaproj, long cliente) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        //Define arquivo que será consultado
        String arquivo = "projeto" + areaproj;
        //Limpa lista de projetos caso haja algum registro
        listaProjetos.removeAll(listaProjetos);
        //Chama método que monta a lista de projetos e adiciona na lista de projetos a partir da resposta da requisição
        listaProjetos = Leitor.montarListaProjeto(requisitarGET(arquivo, cliente), areaproj);

        return listaProjetos.toString();

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
    public static String dividirPorArea(long cliente) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        String response = "\n %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% \n";

        response += "PROJETOS DE ENSINO: ";
        response += "\n ----------------------------------- \n";
        response += requisitarProjeto("ensino", cliente);
        response += "\n %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% \n";

        response += "PROJETOS DE EXTENSÃO: ";
        response += "\n ----------------------------------- \n";

        response += requisitarProjeto("extensao", cliente);
        response += "\n %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% \n";

        response += "PROJETOS DE PESQUISA: ";
        response += "\n ----------------------------------- \n";

        response += requisitarProjeto("pesquisa", cliente);
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
    public static String requisitarCurso(String campus, long cliente) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {

        //Define arquivo e caminho que será consultado
        String arquivo = "curso/" + campus;
        //Limpa lista de cursos
        listaDeCursos.removeAll(listaDeCursos);
        //Chama método que monta a lista de projetos e adiciona na lista de projetos a partir da resposta da requisição
        listaDeCursos = Leitor.montarListaCurso(requisitarGET(arquivo, cliente), campus);
        return listaDeCursos.toString();
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
    public static String mostrarCursos(String campus, long cliente) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        String response = "\n ----------------------------------------------------------- \n";
        response += "\n\n O cursos de graduação e pós-graducação disponíeveis em " + campus.toUpperCase() + " são: \n\n";
        response += "\n ----------------------------------------------------------- \n";
        response += requisitarCurso(campus, cliente);

        return response;
    }

}
