/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enumerator.CAMPUS;
import enumerator.GRUA;
import enumerator.MODALIDADE;

/**
 *Classe para objetos do tipo de curso, onde são contidos valores e métodos para o mesmo
 * @author Judson e Sabrina
 * @version 1.0
 */
public class Curso {
//declarção das variáveis.
    private String nome;
    private String turno;
    private float qntSemestre;
    private double chTotal;
    private CAMPUS campus;
    private GRUA grua;
    private MODALIDADE modalidade;
/**
 * 
 * @param nome- construtor do nome do curso.
 * @param turno- construtor de turno do curso.
 * @param qntSemestre- construtor de quantidade de semestres do curso.
 * @param chTotal- contrutor de carga horaria total do curso.
 * @param mod- contrutor de modalidade do curos.
 * @param grua - construtor de grau do curso.
 */
    public Curso(String nome, String turno, float qntSemestre, double chTotal, MODALIDADE mod, GRUA grua) {
        this.nome = nome;
        this.turno = turno;
        this.qntSemestre = qntSemestre;
        this.chTotal = chTotal;
        this.modalidade = mod;
        this.grua = grua;
    }
/**
 * 
 * @param campus - campus do curso
 */
    public void setCampus(CAMPUS campus) {
        this.campus = campus;
    }
/**
 * 
 * @return retorna o nome do curso. 
 */
    public String getNome() {
        return nome;
    }
/**
 * 
 * @param nome - nome do curso.
 */
    public void setNome(String nome) {
        this.nome = nome;
    }
/**
 * 
 * @return retorna o turno do curso.
 */
    public String getTurno() {
        return turno;
    }
/**
 * 
 * @param turno turno do curso.
 */
    public void setTurno(String turno) {
        this.turno = turno;
    }
/**
 * 
 * @return retorna a quantidade de semestres do curso.
 */
    public float getQntSemestre() {
        return qntSemestre;
    }
/**
 * 
 * @param qntSemestre quantidade de semestre do curso.
 */
    public void setQntSemestre(float qntSemestre) {
        this.qntSemestre = qntSemestre;
    }
/**
 * 
 * @return retorna a carga horaria total do curso.
 */
    public double getChTotal() {
        return chTotal;
    }
/**
 * 
 * @param chTotal carga horaria total do curso.
 */
    public void setChTotal(double chTotal) {
        this.chTotal = chTotal;
    }
/**
 * 
 * @return retorna os objetos nome, turma, quantidade de semestre, carga horaria total, graue modalidade do curso.
 */
    @Override
    public String toString() {
        return  "Nome: " + nome + 
                "\n Turno: " + turno + 
                "\n Quantidade de Semestre: " + qntSemestre + " semestres " +
                "\n Carga Horária Total: "+ chTotal + " horas "+
                "\n Grua: " + grua.name() + " em " + nome +
                "\n Modalidade: " + modalidade.name() +
                "\n\n";
    }

}
