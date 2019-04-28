/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;
import model.Curso;
import model.Projeto;
import model.Universidade;

/**
 *
 * @author Sabrina Winckler
 */
public class UnInforma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        comandos();

    }

    private static void menu() {
        String opcao;
        Scanner op = new Scanner(System.in);
        do {
            System.out.println("   =======================================================================================");
            System.out.println("\n              ### BEM VINDO A UNINFORMA###");
            System.out.println("    |      /projeto -> Pesquisa e retorna todos os projetos existentes na universidade  |\n"
                    +          "    |                  separando-os por seu tipo (pesquisa, extensão e ensino).         |");
            System.out.println("    |      /cursos ->  pesquisa e retorna os cursos disponíveis em cada campus campus   |");
            System.out.println("    |     voltar                                                                        |");
            System.out.println("     =====================================================================================\n");
            opcao = op.next();
            switch (opcao) {
                
                case "/universidade":
                    System.out.println("instanciar classe universidade");
                    break;
                case "/projeto":
                    System.out.println("instanciar classe projeto");
                    break;
                case "/curso":
                    System.out.println("instanciar classe curso");
                    break;
                    case"voltar":
                    break;
                default:
                    System.out.println("Opcao invalida !!!");
            }
        } while (!opcao.equals("voltar"));
      
    }

    private static void comandos() {
        String comandos;
        Scanner c = new Scanner(System.in);

        do {
            System.out.println("   ===========================================================");
            System.out.println("\n              ### BEM VINDO A UNINFORMA###");
            System.out.println("    | Digite /comandos para listar os comandos disponiveis |\n"
                    +          "    |         ou digite sair para finalizar o programa     |");
            System.out.println("    |     sair                                             |");
            System.out.println("     ========================= ================================\n");

            comandos = c.next();
            switch (comandos) {
                case "/comandos":
                    menu();
                    break;
                case "sair":
                    System.out.println("Programa encerrado.");
                    break;
            }
        } while (!comandos.equals("sair"));

    }
}
