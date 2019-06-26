/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testepln;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabri
 */
public class TestePLN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (InputStream modelIn = new FileInputStream("lang-model-name.bin")) {
            SomeModel model = new SomeModel(modelIn);
            ToolName toolName = new ToolName(model);
            String output[] = toolName.executeTask("This is a sample text.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestePLN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestePLN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
