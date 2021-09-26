package JavaFX.controller;

import java.awt.Button;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class FXMLTelaInicialController implements Initializable {
    
    @FXML
    private Button ok;
    @FXML
    private Label palavraDici;
    @FXML
    private TextField textBin;
    
    @FXML
    private Label UltPalavra;
    @FXML
    private Label binC;
    @FXML
    private Label BinE;
    
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
                palavraDici.setText(linha.toLowerCase());
            }
            linha = lerArq.readLine();  
            i++;
        }while(lin >= i);
       
        arq.close();
    }
    
    @FXML
    public void clickOk(){
        String palavratxt = palavraDici.getText();
        String palavraBin = textBin.getText();

        
//        System.out.printf(palavratxt );
//        System.out.println(palavraBin);
        
        StringBuilder result = new StringBuilder();
        char[] chars = palavratxt.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        
        System.out.println(result);
        System.out.println(prettyBinary(result, 8, " "));
        
        //Campos Ultima palavra
        UltPalavra.setText(palavratxt);
    }

    
//    Formatação binario 8 casa
    public static String prettyBinary(StringBuilder binary, int blockSize, String separator) {

        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }

}
