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
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import model.Curso;
import model.Projeto;
import model.Universidade;
import org.xml.sax.SAXException;

/**
 *
 * @author Sabrina Winckler
 */
public class UnInforma {

    /**
     * @param args the command line arguments
     */
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

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    //     INICIALIZAÇÃO DO PROGRAMA            //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    private static void init() throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {

        ClienteHttp cliente = new ClienteHttp();
        System.out.println("    =========================================================\n");
        System.out.println("    |        ### BEM VINDO A UNINFORMA - UNIPAMPA###        |");
        System.out.println("    | Digite \\comandos para listar os comandos disponiveis |\n"
                + "    |         ou digite \\sair para finalizar o programa    |\n");
        System.out.println("    =========================================================\n");
        opcoes(cliente);

    }
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
    //     REQUISIÇÃO DE NÍVEL BÁSICO            //
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//

    private static void opcoes(ClienteHttp cliente) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        String opcao;
        String curso;
        Scanner op = new Scanner(System.in);
        Scanner cur = new Scanner(System.in);

        do {
            System.out.println("    |            O QUE VOCÊ DESEJA SABER?               |");
            opcao = op.next();
            switch (opcao) {
                case "\\comandos":
                    comandos(cliente);
                    break;
                case "\\projetos":
                    System.out.println(cliente.dividirPorArea());
                    break;
                case "\\cursos_em":
                    System.out.println("\n Os campus existentes são: " + CAMPUS.stringCAMPUS());
                    System.out.println("\n Digite o campus que deseja consultar: ");
                    curso = cur.next();
                    if (CAMPUS.exists(curso)) {
                        System.out.println(cliente.mostrarCursos(curso.toLowerCase()));
                    } else {
                        System.out.println("Este Campus não existe.");
                    }
                    break;
                case "\\sair":
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida !!!");
            }
        } while (!opcao.equals("\\sair"));

    }

    private static void comandos(ClienteHttp cliente) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, ParseException {
        System.out.println("   =======================================================================================");
        System.out.println("    |                     ##LISTA DE COMANDOS DISPONÍVEIS##                              |\n");
        System.out.println("    |      \\projetos -> Pesquisa e retorna todos os projetos existentes na universidade |\n"
                + "    |       separando-os por seu tipo (pesquisa, extensão e ensino).                     |\n");
        System.out.println("    |      \\cursos_em <campus> ->  pesquisa e retorna os cursos disponíveis no campus   |\n"
                + "    |                              passado como parâmetro.                               |");
        System.out.println("    |      \\comandos -> lista os comandos disponiveis                                   |");
        System.out.println("    |      \\sair -> para encerrar o programa                                            |");
        System.out.println("     =====================================================================================\n");
    }

}
