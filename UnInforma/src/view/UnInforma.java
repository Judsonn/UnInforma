/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ClienteHttp;
import enumerator.CAMPUS;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/** 
 * Classe que cria as linhas de comandos, responsável em fazer a interação com o usuário. 
 * @author Judson e Sabrina.
 * @version 1.0
 */
public class UnInforma {

    public static void main(String[] args) {
        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(UnInforma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(UnInforma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(UnInforma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(UnInforma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UnInforma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Inicialização do programa 
 * @throws IOException retorna uma exceção caso não consiga iniciar o programa 
 * @throws ParserConfigurationException retorna uma exceção indicando um erro grave na configuração do sistema.
 * @throws SAXException retorna uma exceção que o método pode  conter informações básicas de erro ou aviso do analisador XML. 
 * @throws URISyntaxException Exceção lançada para indicar que uma string não pôde ser analisada como uma referência de URI.
 * @throws ParseException Tipo de exceção para problemas de análise, usado quando conteúdo que não está em conformidade com a sintaxe especificada.
 */

    private static void init() throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {

        ClienteHttp cliente = new ClienteHttp();
        System.out.println("    =========================================================\n");
        System.out.println("    |        ### BEM VINDO A UNINFORMA - UNIPAMPA###        |");
        System.out.println("    | Digite \\comandos para listar os comandos disponiveis |\n"
                + "    |         ou digite \\sair para finalizar o programa    |\n");
        System.out.println("    =========================================================\n");
        opcoes(cliente);

    }
    
    
  /**
   * Requisição de nivél básico: além de ser o comando básico, esse método dá o acesso aos comandos de nível intermediário e avançado.
   * @param cliente - Recebe o cliente criado na inicialização.  
   * @throws IOException Sinaliza que ocorreu uma exceção de entrado ou saida de dados.
   * @throws ParserConfigurationException retorna uma exceção indicando um erro grave na configuração do método de opção.
   * @throws SAXException  retorna uma exceção que o método pode  conter informações básicas de erro ou aviso do analisador XML.
   * @throws URISyntaxException Exceção lançada para indicar que uma string não pôde ser analisada como uma referência de URI.
   * @throws ParseException Tipo de exceção para problemas de análise, usado quando conteúdo que não está em conformidade com a sintaxe do formato especificado.
   */
    
    private static void opcoes(ClienteHttp cliente) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        String opcao;
        String curso;
        Scanner op = new Scanner(System.in);
        Scanner cur = new Scanner(System.in);
// loop de interação com o usuário, caso o usuário informe um comando errado, o programa vai invalidar a informação e vai fazer outra interação com o usuário. 
        do {
            System.out.println("    |            O QUE VOCÊ DESEJA SABER?               |");
            opcao = op.next();
            switch (opcao) {
                //caso o usuário escolha comandos, o sistema vai lista o comandos disponíveis para o usuário
                case "\\comandos":
                    comandos();
                    break;
                //caso o usuário escolha o comando de projetos, o sistama vai executar o método dividirPorArea.
                case "\\projetos":
                    System.out.println(cliente.dividirPorArea());
                    break;
                //caso o usuário escolha o comando de cursos o sistema vai mostrar os campus existentes e vai pedir para escolher um campus e vai lista o campus para o usuário.
                case "\\cursos_em":
                    System.out.println("\n Os campus existentes são: " + CAMPUS.stringCAMPUS());
                    System.out.println("\n Digite o campus que deseja consultar: ");
                    curso = cur.next();
                // se o campus existe ele vai mostrar os cursos.
                    if (CAMPUS.exists(curso)) {
                        System.out.println(cliente.mostrarCursos(curso.toLowerCase()));
                //caso contrário o campus não existe. 
                    } else {
                        System.out.println("Este Campus não existe.");
                    }
                    break;
               //caso o usuário deseje sair do programa
                case "\\sair":
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida !!!");
            }
        } while (!opcao.equals("\\sair"));

    }
/**
 * Comando de nível básico
 * Interface de interação com o usuário, apresentando os comandos disponíveis no sistema.
 */
    private static void comandos()  {
        System.out.println("   =======================================================================================");
        System.out.println("    |                     ##LISTA DE COMANDOS DISPONÍVEIS##                                   |\n");
        System.out.println("    |      \\projetos -> Pesquisa e retorna todos os projetos existentes na universidade      |\n"
                + "             |  separando-os por seu tipo (pesquisa, extensão e ensino).                               |\n");
        System.out.println("    |      \\cursos_em  ->  pesquisa campus existentes e retorna, em seguida insira o <campus>|\n"
                + "             |                        que deseja consultar.                                            |\n");
        System.out.println("    |      \\comandos -> lista os comandos disponiveis                                        |");
        System.out.println("    |      \\sair -> para encerrar o programa                                                 |");
        System.out.println("     =====================================================================================\n");
    }

}
