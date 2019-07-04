/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author sabri
 */
public class Conexao implements Hello{

    @Override
    public void printMsg() throws RemoteException {
        System.out.println("This is an example RMI program"); 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
}
