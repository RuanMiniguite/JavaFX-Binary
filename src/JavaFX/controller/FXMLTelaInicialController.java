package JavaFX.controller;

import java.awt.Button;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;


public class FXMLTelaInicialController implements Initializable {
    
    @FXML
    private Button ok;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            abrirArquivo();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLTelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrirArquivo() throws FileNotFoundException, IOException{
        int i = 0;
        System.out.println("Test");
        
        FileReader arq = new FileReader("C:\\Users\\ruanm\\Documents\\Github\\JavaFX-Binary\\src\\JavaFX\\database\\Lista-de-Palavras.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        
        while(i < 10){
            System.out.printf("%s\n", linha);
            linha = lerArq.readLine();
                    
            i++;
        }
        
        arq.close();
    }
    
}
