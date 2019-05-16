/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumerator;

/**
 *
 * @author Sabrina Winckler
 */
public enum CAMPUS {
    ALEGRETE, BAGE, CACAPAVADOSUL, DOMPEDRITO, ITAQUI, JAGUARAO, LIVRAMENTO, SAOBORJA, SAOGABRIEL, URUGUAIANA;
    
    public static boolean exists(String campus){
        for (CAMPUS value : CAMPUS.values()) {
            if (value.toString().equalsIgnoreCase(campus)) {
                return true;
            }
        }
        return false;
    }
    
    public static String stringCAMPUS(){
        String campus = "ALEGRETE, BAGE, CACAPAVADOSUL, DOMPEDRITO, ITAQUI, JAGUARAO, LIVRAMENTO, SAOBORJA, SAOGABRIEL, URUGUAIANA";
        return campus;
    }
}
