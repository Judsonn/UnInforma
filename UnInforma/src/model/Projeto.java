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
 *Classe para objetos do tipo de projeto, onde são contidos valores e métodos para o mesmo
 * @author Judson e Sabrina 
 * @version 1.0
 */
public class Projeto implements Serializable{
  //declaração das variáveis.  
    private String campus;
    private String descricao;
    private String coordenador;
    private String validade;
    private AREA area;

    public Projeto() {
    }
/**
 * 
 * @param campus - construtor de campus do projeto.
 * @param descricao - construtor de descrição do projeto.
 * @param coordenador - construtor de coordenador do projeto.
 * @param validade - construtor de validade do projeto.
 */
    public Projeto(String campus, String descricao, String coordenador, String validade) {
        this.campus = campus;
        this.descricao = descricao;
        this.coordenador = coordenador;
        this.validade = validade;
    }
/**
 * 
 * @param area - areas de atuaçaõ do projeto.
 */
    public void setArea(String area) {
        this.area = AREA.valueOf(area.toUpperCase());
    }
/**
 * 
 * @param campus - campus do projeto. 
 */
    public void setCampus(String campus) {
        this.campus = campus;
    }
/**
 * 
 * @param validade - validade do projetos.
 */
    public void setValidade(String validade) {
        this.validade = validade;
    }
/**
 * 
 * @param descricao - descrição do projetos.  
 */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
/**
 * 
 * @param coordenador - coordenador do projeto.
 */
    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }
/**
 * 
 * @return retorna a area do projeto.
 */
    public AREA getArea() {
        return area;
    }
/**
 * 
 * @return retorna o campus do projeto. 
 */
    public String getCampus() {
        return campus;
    }
/**
 * 
 * @return retorna a descrição do projeto.
 */
    public String getDescricao() {
        return descricao;
    }
/**
 * 
 * @return retorna o coordenador do projeto.
 */
    public String getCoordenador() {
        return coordenador;
    }
/**
 * 
 * @return retorna a validade do projeto. 
 */
    public String getValidade() {
        return validade;
    }
/**
 * 
 * @return retorna os objetos de campus, descrição, coordenador e validade do projeto. 
 */
    @Override
    public String toString() {
        return "Projeto: " + "Campus: " + campus + "\n Descricao=" + descricao + "\n Coordenador=" + coordenador + " \n Validade: "+ validade +" \n\n";
    }
    
    
}
