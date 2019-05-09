/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Sabrina Winckler
 */
public class Curso {
    private String nome;
    private String turno;
    private float qntSemestre;
    private double chTotal;

    public Curso(String nome, String turno, float qntSemestre, double chTotal) {
        this.nome = nome;
        this.turno = turno;
        this.qntSemestre = qntSemestre;
        this.chTotal = chTotal;
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
        return "Curso:" + "nome=" + nome + "/n turno=" + turno + ",/n qntSemestre=" + qntSemestre + ",/n chTotal=" + chTotal + '}';
    }
    
 
    
}
