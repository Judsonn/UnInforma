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
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
 * @author Lucas
 */
public class Leitor {

    private static File arquivo;
    private static int quantidadeProjetos;

    public static File carregarArquivo(String caminho) throws ParserConfigurationException, SAXException, IOException {
        
        arquivo =  new File(caminho);
        
       // DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

       // dbf.setNamespaceAware(false);

       // DocumentBuilder docBuilder = dbf.newDocumentBuilder();

       // Document doc = docBuilder.parse(arquivo);
        
        return arquivo;
    }

    public static ArrayList montarLista() throws ParseException {
        
        String campus;
        String desc;
        String coord;
        String val;
        //DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Projeto p;
        
        String[] projetoStringArray;
        String projetoString;
        
        ArrayList<Projeto> listaProjetos = new ArrayList();
        FileReader reader;
        BufferedReader br;

        try {
            reader = new FileReader(arquivo);
            br = new BufferedReader(reader);
            while ((projetoString = br.readLine()) != null) {
                projetoStringArray = projetoString.split(",");

                campus = projetoStringArray[0];
                desc = projetoStringArray[1];
                coord = projetoStringArray[2];
                val = projetoStringArray[3];

                p = new Projeto(campus, desc, coord, val);
                listaProjetos.add(new Projeto(campus, desc, coord, val));
                
                System.out.println(p.toString());

            }

        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }

        Leitor.quantidadeProjetos = listaProjetos.size();

        return listaProjetos;
    }

    public static int getQuantidadeProjetos() {
        return quantidadeProjetos;
    }

}
