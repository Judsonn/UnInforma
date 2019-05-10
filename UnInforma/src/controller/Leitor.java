/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.Projeto;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Sabrina Winckler
 */
public class Leitor {

    private static int quantidadeProjetos;
    private static String cabecalho;

    public static ArrayList montarListaProjeto(BufferedReader br) throws ParseException, IOException {

        //Criando variáveis locais para os atributos de Projeto
        String campus;
        String desc;
        String coord;
        String val;
        Projeto p;

        //Variáveis para manipular o array recebido do servidor
        String[] projetoStringArray;
        String projetoString;

        //Variável para a lista de projetos que é inicialmente vazia
        ArrayList<Projeto> listaProjetos = new ArrayList();

        //Identifica cabeçalho da requisição e armazena no atributo
        for(int i=0; i<9;i++){
            cabecalho = br.readLine() + " \n";
        }
       
        //Enquanto a linha não for nula, ele lê cada linha com as informações do projeto
        while ((projetoString = br.readLine()) != null) {
            //Define split do csv
            projetoStringArray = projetoString.split(",");
            
            //Aramzena valores do vetor nas variáveis locais que correspondem aos atributos
            campus = projetoStringArray[0];
            desc = projetoStringArray[1];
            coord = projetoStringArray[2];
            val = projetoStringArray[3];

            //Cria projeto
            p = new Projeto(campus, desc, coord, val);
            
            //Adiciona na lista de projetos
            listaProjetos.add(p);
            
        }

        Leitor.quantidadeProjetos = listaProjetos.size();

        return listaProjetos;
    }

    public static int getQuantidadeProjetos() {
        return quantidadeProjetos;
    }

    public static String getCabecalho() {
        return cabecalho;
    }
    
}
