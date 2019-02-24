
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Pedro
 */
public class TesteMelhorPlano {
    
    // Metodo que retorna um String com o endereco fornecido pelo usuário
    private static String lerInput(){
        
        Scanner ler = new Scanner(System.in);
        System.out.printf("Informe o nome de arquivo texto:\n(Favor especificar o endereço completo até o arquivo, como: C:/Desktop/Inputs/encode2.in)\n\n");
        String enderecoArq = ler.nextLine();
        return enderecoArq;
    }
    
    // Metodo que le o endereco de um arquivo e retorna o conteúdo inserido dentro do arquivo.
    private static String lerString(String enderecoArq){
               
        String palavra = "";
        try{
            FileReader arq = new FileReader(enderecoArq);
            BufferedReader lerArq = new BufferedReader(arq);
            palavra = lerArq.readLine(); 
        }catch(IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
        }
        return palavra;
    }
    
    //Metodo que dependendo do endereco do arquivo realiza as funcoes de enconde ou de decode.
    public static void menu() throws IOException{
        
        String enderecoArq = lerInput();
        FileWriter arq = new FileWriter(transformaEndereco(enderecoArq));
        PrintWriter gravarArq = new PrintWriter(arq);
        if(enderecoArq.matches("(.*)encode(.*)")){
            Matriz matriz = new Matriz(lerString(enderecoArq));   
            gravarArq.printf("[\""+matriz.getTransformacao()+"\", "+matriz.getIndice()+"]\n");
        }else if((enderecoArq.matches("(.*)decode(.*)"))){
            String input = lerString(enderecoArq);
            int indice = Character.getNumericValue(input.charAt(input.length()-2));
            Matriz matriz = new Matriz(input.substring(2, (input.length()-5)),indice);
            gravarArq.printf(String.valueOf(matriz.decodificar()));
        }else{
            System.out.println("O nome do arquivo deve conter a palavra ENCODE ou DECODE. Tente um outro arquivo ou aperte 1 para finalizar. \n");
            menu();
        }
        arq.close();
    }
    
    //Metodo que altera o endereco de entrada .in para .out.
    public static String transformaEndereco(String endereco){
        String enderecoOut = "";
        for(int i = 0; i<endereco.length()-2; i++){
            enderecoOut += endereco.charAt(i);
        }
        enderecoOut+="out";
        return enderecoOut;
    }
    
    public static void main(String[] args) throws IOException{
        
        menu();
    }
}
