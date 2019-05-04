/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Sabrina Winckler
 */
public class Projeto implements Serializable{

    String nome;
    
    public Projeto(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Projeto{" + "nome=" + nome + '}';
    }
    
}
