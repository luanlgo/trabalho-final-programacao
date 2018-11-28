package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;

/**
 *
 * @author lmilano
 */
public class Executer {

    File arquivoUpload;
    ArrayList<String> palavras = new ArrayList<>();
    ArrayList<String> palavrasNovas = new ArrayList<>();
    ArrayList<ArrayList<Integer>> anagramas = new ArrayList<>();
    
    public ArrayList<ArrayList<Integer>> getAnagrama() {
        return this.anagramas;
    }

    public void UploadArquivo() {
        JFileChooser file = new JFileChooser(); 
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i= file.showSaveDialog(null);
        if (i==1){
            System.out.println("Tipo de arquivo invalido");
        } else {
            arquivoUpload = file.getSelectedFile();
        }
    }
    
    public void LerLinha() {
        BufferedReader file; 
        try {
            file = new BufferedReader(new FileReader(arquivoUpload));
            String str;
            try {
                while(( str = file.readLine())!=null){
                    palavras.add(str);
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }        
    }
    
    
    public static void main (String[] argss) {
        Executer executador = new Executer();
        
        executador.UploadArquivo();
        executador.LerLinha();
        executador.PalavraPorPalavra();
        executador.SepararAnagrama();
        
        for (ArrayList palavras : executador.anagramas) {
            System.out.print((palavras.size()) + "-");
            System.out.print("[");
            for (Object palavra : palavras) {
                System.out.print(executador.palavras.get((int)palavra) + 
                    (palavras.get(palavras.size() -1).equals(palavra) ? "" : ", ")
                );
            }
            System.out.println("]");
        }
    }
    
    public ArrayList<String> ListarAnnagrama(ArrayList<ArrayList<Integer>> anagramas) {
        ArrayList<String> anagramaRetorno = new ArrayList<>();
        for (ArrayList pls : anagramas) {
            String linhaAnagrama = "";
            linhaAnagrama += ((pls.size()) + "-");
            linhaAnagrama += "[";
            for (Object palavra : pls) {
                linhaAnagrama += (palavras.get((int)palavra) + 
                    (pls.get(pls.size() -1).equals(palavra) ? "" : ", ")
                );
            }
            linhaAnagrama += "]";
            anagramaRetorno.add(linhaAnagrama);
        }
        return anagramaRetorno;
    }
    
    public void PalavraPorPalavra() {
        for (String palavra : palavras) {
            char letras[] = palavra.toCharArray();
            Arrays.sort(letras);
            palavrasNovas.add(new String(letras));
        }
    }
    
    public void SepararAnagrama() {
        for (int i = 0; i < palavrasNovas.size(); i++) {
            String palavra = palavrasNovas.get(i);
            anagramas.add(new ArrayList<>());
            anagramas.get(i).add(i);
            for (int j = i + 1; j < palavrasNovas.size(); j++) {
                if (palavra.equals(palavrasNovas.get(j))) {
                    anagramas.get(i).add(j);
                    palavrasNovas.remove(j);
                }
            }
        }
    }
}
