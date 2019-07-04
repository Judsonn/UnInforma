/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabri
 */
public class Distribuidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            MulticastSocket ms = new MulticastSocket();
            InetAddress sessAddr = InetAddress.getByName("224.2.76.24"); 
            ms.joinGroup(sessAddr);
            
            byte[] audioBuf = new byte[1024]; 
            DatagramPacket dp = new DatagramPacket(audioBuf, 1024); 
            ms.receive(dp);
            
        } catch (IOException ex) {
            Logger.getLogger(Distribuidor.class.getName()).log(Level.SEVERE, null, ex);
        }
       String ip = ""; 
       int port = 1;
        try {
            Socket socket = new Socket(ip, port); //Servidor em IP diferente
        } catch (IOException ex) {
            Logger.getLogger(Distribuidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
