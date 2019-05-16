/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enumerator.AREA;
import enumerator.CAMPUS;
import enumerator.GRUA;
import enumerator.MODALIDADE;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import model.Curso;
import model.Projeto;

/**
 *
 * @author Sabrina Winckler
 */
//Classe - Biblioteca que trata os dados que rebe do servidor
public class Leitor {

    private static int quantidadeProjetos;
    private static int quantidadeCursos;
    private static String cabecalho;
/**
 * 
 * @param br
 * @param areaProj
 * @return
 * @throws ParseException
 * @throws IOException 
 */
    public static ArrayList montarListaProjeto(BufferedReader br, String areaProj) throws ParseException, IOException {

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
        for (int i = 0; i < 9; i++) {
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
            p.setArea(areaProj);
            //Adiciona na lista de projetos
            listaProjetos.add(p);

        }

        Leitor.quantidadeProjetos = listaProjetos.size();

        return listaProjetos;
    }
/**
 * 
 * @param br
 * @param campus
 * @return
 * @throws ParseException
 * @throws IOException 
 */
    public static ArrayList montarListaCurso(BufferedReader br, String campus) throws ParseException, IOException {

        //Criando variáveis locais para os atributos de Projeto
        String nome;
        String turno;
        float qntSemestre;
        double chTotal;
        GRUA grua;
        MODALIDADE modalidade;
        Curso c;     

        //Variáveis para manipular o array recebido do servidor
        String[] cursoStringArray;
        String cursoString;
        String campusArq;

        //Variável para a lista de projetos que é inicialmente vazia
        ArrayList<Curso> listaCursos = new ArrayList();

        //Identifica cabeçalho da requisição e armazena no atributo
        for (int i = 0; i < 9; i++) {
            cabecalho = br.readLine() + " \n";
        }

        //Enquanto a linha não for nula, ele lê cada linha com as informações do projeto
        while ((cursoString = br.readLine()) != null) {
            //Define split do csv
            cursoStringArray = cursoString.split(",");

            //Aramzena valores do vetor nas variáveis locais que correspondem aos atributos
            campusArq = cursoStringArray[0];
            nome = cursoStringArray[1];
            turno = cursoStringArray[2];
            qntSemestre = Float.parseFloat(cursoStringArray[3]);
            chTotal = Integer.parseInt(cursoStringArray[4]);
            grua = GRUA.valueOf(cursoStringArray[5].toUpperCase());
            modalidade = MODALIDADE.valueOf(cursoStringArray[6].toUpperCase());

            //Cria curso
            c = new Curso(nome, turno, qntSemestre, chTotal, modalidade, grua);
            c.setCampus(CAMPUS.valueOf(campus.toUpperCase()));

            //Adiciona na lista de cursos
            listaCursos.add(c);

        }

        Leitor.quantidadeCursos = listaCursos.size();

        return listaCursos;
    }

    public static int getQuantidadeProjetos() {
        return quantidadeProjetos;
    }

    public static int getQuantidadeCursos() {
        return quantidadeCursos;
    }

    public static String getCabecalho() {
        return cabecalho;
    }

}
