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
        
        menu();
        
        
    }
        
        private static void menu(){
        	String opcao;
		Scanner op = new Scanner(System.in);
		do{
            System.out.println("   ====================================================");        
            System.out.println("\n              ### BEM VINDO A UNINFORMA###"             );
            System.out.println("    | Escolha um dos comandos para continuar           |");         
            System.out.println("    |     COMANDOS                                     |");
            System.out.println("    |      /universidade                               |");
            System.out.println("    |      /projeto                                    |");
            System.out.println("    |      /cursos                                     |");
            System.out.println("    |     sair                                         |");        
            System.out.println("     ========================= =========================\n");
		opcao = op.next();
		switch(opcao){
		case "sair":			
			System.out.println("Ok, ate mais ...");
			break;
		case "/universidade":
                        System.out.println("instanciar classe universidade");
			break;
		case "/projeto":
                        System.out.println("instanciar classe projeto");
			break;
		case "/curso":
		        System.out.println("instanciar classe curso");
			break;
		default:
			System.out.println("Opcao invalida !!!");
		}
		}while(!opcao.equals("sair"));
		System.out.println("Programa encerrado.");
	}
}

