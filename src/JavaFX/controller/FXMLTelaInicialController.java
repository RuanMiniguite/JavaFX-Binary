package JavaFX.controller;

import java.awt.Button;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        
        
        StringBuilder BinText = new StringBuilder();
        char[] chars = palavraLabel.toCharArray();
        for (char aChar : chars) {
            BinText.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   
                            .replaceAll(" ", "0")                         
            );
        }
        
        String resultBin = prettyBinary(BinText, 8, " ");
        
//        System.out.println(resultBin);
//        System.out.println(palavraTextF);  
        
        if(palavraTextF.equals(resultBin)){
            acertos++;
            Lacertos.setText(Integer.toString(acertos));
        }else{
            erros++;
            Lerros.setText(Integer.toString(erros));
        }

        //Campos Ultima palavra
        UltPalavra.setText(palavraLabel);
        binC.setText(prettyBinary(BinText, 8, " "));
        BinE.setText(palavraTextF);
        
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
