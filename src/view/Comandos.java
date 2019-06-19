
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ClienteHttp;
import enumerator.CAMPUS;
import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author sabri
 */
public class Comandos {

    private static ClienteHttp cliente;

    /**
     * Inicialização do programa
     *
     * @param socket
     * @param mensagem
     * @return
     * @throws IOException retorna uma exceção caso não consiga iniciar o
     * programa
     * @throws ParserConfigurationException retorna uma exceção indicando um
     * erro grave na configuração do sistema.
     * @throws SAXException retorna uma exceção que o método pode conter
     * informações básicas de erro ou aviso do analisador XML.
     * @throws URISyntaxException Exceção lançada para indicar que uma string
     * não pôde ser analisada como uma referência de URI.
     * @throws ParseException Tipo de exceção para problemas de análise, usado
     * quando conteúdo que não está em conformidade com a sintaxe especificada.
     */

    public static String init(Socket socket) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {

        cliente = new ClienteHttp();
        cliente.criarConexaoSocket(socket);
        String response = "===========================\n"
                + "    |        ### BEM VINDO A UNINFORMA - UNIPAMPA###        |"
                + "    | Digite \\comandos para listar os comandos disponiveis |\n"
                + "    |         ou digite \\sair para finalizar o programa    |\n"
                + "    =========================================================\n";
        return response + "\n" + cliente.getClient().toString();
    }

    /**
     * Requisição de nivél básico: além de ser o comando básico, esse método dá
     * o acesso aos comandos de nível intermediário e avançado.
     *
     * @param mensagem
     * @return
     * @throws IOException Sinaliza que ocorreu uma exceção de entrado ou saida
     * de dados.
     * @throws ParserConfigurationException retorna uma exceção indicando um
     * erro grave na configuração do método de opção.
     * @throws SAXException retorna uma exceção que o método pode conter
     * informações básicas de erro ou aviso do analisador XML.
     * @throws URISyntaxException Exceção lançada para indicar que uma string
     * não pôde ser analisada como uma referência de URI.
     * @throws ParseException Tipo de exceção para problemas de análise, usado
     * quando conteúdo que não está em conformidade com a sintaxe do formato
     * especificado.
     */
    public static String opcoes(String mensagem) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        String opcao;
        String campus;
        // Socket sock = new Socket("localhost", 8000);
// loop de interação com o usuário, caso o usuário informe um comando errado, o programa vai invalidar a informação e vai fazer outra interação com o usuário. 
        do {
            opcao = mensagem;
            switch (opcao) {
                //caso o usuário escolha comandos, o sistema vai lista o comandos disponíveis para o usuário
                case "\\comandos":
                    return comandos();
                //caso o usuário escolha o comando de projetos, o sistama vai executar o método dividirPorArea.
                case "\\projetos":
                    return cliente.dividirPorArea();
                //caso o usuário escolha o comando de cursos o sistema vai mostrar os campus existentes e vai pedir para escolher um campus e vai lista o campus para o usuário.
                case "\\cursos_em":

                    String[] arrayOpcao = opcao.split(" "); //Divide comando do parâmetro          
                    campus = arrayOpcao[1];//Pega somente o parâmetro
                    
                    // se o campus existe ele vai mostrar os cursos.
                    if (CAMPUS.exists(campus)) {
                        return cliente.mostrarCursos(campus.toLowerCase());
                        //caso contrário o campus não existe. 
                    } else {
                        return "Este Campus não existe.";
                    }
                //caso o usuário deseje saber os campus existentes
                case "\\campus":
                    return ("\n Os campus existentes são: " + CAMPUS.stringCAMPUS());
                default:
                    return opcao;
            }
        } while (!opcao.equals("\\sair"));

    }

    /**
     * Comando de nível básico Interface de interação com o usuário,
     * apresentando os comandos disponíveis no sistema.
     *
     * @return
     */
    public static String comandos() {
        String response = ("   =======================================================================================")
                + ("    |                     ##LISTA DE COMANDOS DISPONÍVEIS##                                   |\n")
                + ("    |      \\projetos -> Pesquisa e retorna todos os projetos existentes na universidade      |\n"
                + "             |  separando-os por seu tipo (pesquisa, extensão e ensino).                               |\n")
                + ("    |      \\cursos_em  ->  pesquisa campus existentes e retorna, em seguida insira o <campus>|\n"
                + "             |                        que deseja consultar.                                            |\n")
                + ("    |      \\comandos -> lista os comandos disponiveis                                        |")
                + ("    |      \\sair -> para encerrar o programa                                                 |")
                + ("     =====================================================================================\n");
        return response;
    }
}
