/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enumerator.AREA;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Sabrina Winckler
 */
public class Projeto implements Serializable{
    
    private String campus;
    private String descricao;
    private String coordenador;
    private String validade;
    private AREA area;

    public Projeto() {
    }
/**
 * 
 * @param campus
 * @param descricao
 * @param coordenador
 * @param validade 
 */
    public Projeto(String campus, String descricao, String coordenador, String validade) {
        this.campus = campus;
        this.descricao = descricao;
        this.coordenador = coordenador;
        this.validade = validade;
    }

    public void setArea(String area) {
        this.area = AREA.valueOf(area.toUpperCase());
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }

    public AREA getArea() {
        return area;
    }

    public String getCampus() {
        return campus;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCoordenador() {
        return coordenador;
    }

    public String getValidade() {
        return validade;
    }

    @Override
    public String toString() {
        return "Projeto: " + "Campus: " + campus + "\n Descricao=" + descricao + "\n Coordenador=" + coordenador + " \n Validade: "+ validade +" \n\n";
    }
    
    
}
