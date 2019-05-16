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
 *
 * @author Sabrina Winckler
 */
public class Curso {

    private String nome;
    private String turno;
    private float qntSemestre;
    private double chTotal;
    private CAMPUS campus;
    private GRUA grua;
    private MODALIDADE modalidade;
/**
 * 
 * @param nome
 * @param turno
 * @param qntSemestre
 * @param chTotal
 * @param mod
 * @param grua 
 */
    public Curso(String nome, String turno, float qntSemestre, double chTotal, MODALIDADE mod, GRUA grua) {
        this.nome = nome;
        this.turno = turno;
        this.qntSemestre = qntSemestre;
        this.chTotal = chTotal;
        this.modalidade = mod;
        this.grua = grua;
    }

    public void setCampus(CAMPUS campus) {
        this.campus = campus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public float getQntSemestre() {
        return qntSemestre;
    }

    public void setQntSemestre(float qntSemestre) {
        this.qntSemestre = qntSemestre;
    }

    public double getChTotal() {
        return chTotal;
    }

    public void setChTotal(double chTotal) {
        this.chTotal = chTotal;
    }

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
