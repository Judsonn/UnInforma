/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

    public Projeto() {
    }

    public Projeto(String campus, String descricao, String coordenador, String validade) {
        this.campus = campus;
        this.descricao = descricao;
        this.coordenador = coordenador;
        this.validade = validade;
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
        return "Projeto{" + "campus=" + campus + ", descricao=" + descricao + ", coordenador=" + coordenador + '}';
    }
    
    
}
