package JavaFX.controller;

import java.awt.Button;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;


public class FXMLTelaInicialController implements Initializable {
    
    @FXML
    private Button ok;
    @FXML
    private Label palavraDici;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getWord();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLTelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLTelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
  
   
    
    public void getWord() throws FileNotFoundException, IOException{
        Random gerador = new Random();
        int lin = gerador.nextInt(29857) + 1;
        int i = 0;
        
        System.out.println(lin);
        
        FileReader arq = new FileReader("C:\\Users\\ruanm\\Documents\\Github\\JavaFX-Binary\\src\\JavaFX\\database\\Lista-de-Palavras.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        
        do{
            if(i == lin){
                System.out.printf("%s\n", linha);
                
                palavraDici.setText(linha.toLowerCase());
            }
            linha = lerArq.readLine();  
            i++;
        }while(lin >= i);
       
        arq.close();
    }
    
}
