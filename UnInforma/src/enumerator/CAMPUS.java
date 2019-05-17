/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumerator;

/**
 * enum dos campus do comando de curso. 
 * @author Judson e Sabrina
 * @version 1.0
 */
public enum CAMPUS {
    ALEGRETE, BAGE, CACAPAVADOSUL, DOMPEDRITO, ITAQUI, JAGUARAO, LIVRAMENTO, SAOBORJA, SAOGABRIEL, URUGUAIANA;
   
    
    
   /**
     * Verifica se o campus existe.
     * @param campus- passa o campus pesquisado como parâmetro 
     * @return retorna se o campus pesquisado é existente ou não 
     */
    public static boolean exists(String campus){
        for (CAMPUS value : CAMPUS.values()) {
            if (value.toString().equalsIgnoreCase(campus)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 
     * @return retorna string dos campus.
     */
    public static String stringCAMPUS(){
        String campus = "ALEGRETE, BAGE, CACAPAVADOSUL, DOMPEDRITO, ITAQUI, JAGUARAO, LIVRAMENTO, SAOBORJA, SAOGABRIEL, URUGUAIANA";
        return campus;
    }
}
