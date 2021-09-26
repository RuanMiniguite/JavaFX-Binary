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
    
    @FXML
    private Label Lacertos;
    @FXML
    private Label Lerros;
    
    int acertos = 0;
    int erros = 0;
    
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
        String palavraLabel = palavraDici.getText();
        String palavraTextF = textBin.getText();

        
//        System.out.printf(palavraLabel);
//        System.out.println(palavraTextF);
        
        
        StringBuilder BinTextF = new StringBuilder();
        char[] chars2 = palavraTextF.toCharArray();
        for (char aChar : chars2) {
            BinTextF.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   
                            .replaceAll(" ", "0")                         
            );
        }
        
//        System.out.println(BinTextF);
//        System.out.println(prettyBinary(BinTextF, 8, " "));


        StringBuilder BinLabel = new StringBuilder();
        char[] chars1 = palavraLabel.toCharArray();
        for (char aChar : chars1) {
            BinLabel.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   
                            .replaceAll(" ", "0")                         
            );
        }
        
//        System.out.println(BinLabel);
//        System.out.println(prettyBinary(BinLabel, 8, " "));
        
        
        //Campos Ultima palavra
        UltPalavra.setText(palavraLabel);
        binC.setText(prettyBinary(BinLabel, 8, " "));
        BinE.setText(prettyBinary(BinTextF, 8, " "));
                
    }

    
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
