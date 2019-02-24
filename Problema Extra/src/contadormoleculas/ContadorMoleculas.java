package contadormoleculas;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.*;
/**
 *
 * @author Pedro Augusto Prosdocimi Resende
 */
public class ContadorMoleculas {

    // Metodo que retorna um String com o endereco fornecido pelo usuário
    private static String lerInput(){
        
        Scanner ler = new Scanner(System.in);
        System.out.printf("Informe o nome de arquivo texto:\n(Favor especificar o endereço completo até o arquivo, como: C:/Desktop/Inputs/test.in)\n\n");
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
    
    // Metodo que recebe o conteúdo do arquivo e resolve os parenteses. Por exemplo, se há na equação um (H20)2,
    //o metodo irá retirar os parenteses e multiplicar as moléculas pelo número multiplicador fora do parenteses.
    private static String resolverParenteses(String equacao){
        Pattern ptrn = Pattern.compile("(\\()([a-zA-Z0-9]+)(\\))");
        Matcher matcher = ptrn.matcher(equacao);
        while (matcher.find()) {
            int m;
            try{
                if(Character.isDigit(equacao.charAt(matcher.end()))){
                    m=Character.getNumericValue(equacao.charAt(matcher.end()));
                }else{
                    m=1;
                }
            }catch(StringIndexOutOfBoundsException e){
                m=1;           
            }
            String aux = equacao.substring(matcher.start()+1, matcher.end()-1);
            aux = multiplicaSubEquacao(aux, m);
            if(m!=1) equacao = equacao.substring(0, matcher.start()) + aux + equacao.substring(matcher.end()+1, equacao.length());
            if(m==1){
                if(matcher.end() == equacao.length()){
                    equacao = equacao.substring(0, matcher.start()) + aux;
                }else{
                    equacao = equacao.substring(0, matcher.start()) + aux + equacao.substring(matcher.end(), equacao.length());
                }
            }
            matcher = ptrn.matcher(equacao);
        }
        return equacao;
    }
    
    //Esse metodo recebe uma subString pertencente a equacao e o multiplicador presente fora do parenteses, colchetes ou chaves.
    //O metodo devolve a subString com cada molecula multiplicada pelo numero multiplicador.
    private static String multiplicaSubEquacao(String input, int multiplicador){
        String resp = "";
        for(int j = 0; j< input.length()-1; j++){
            if(Character.isUpperCase(input.charAt(j)) && Character.isLowerCase(input.charAt(j+1))){
                    if(j!=input.length()-2){
                        if(Character.isUpperCase(input.charAt(j+2))){
                            String aux = input.substring(j,j+2);
                            resp = resp + aux + Integer.toString(multiplicador);
                        }
                    }
                    
                }
                if(Character.isUpperCase(input.charAt(j)) && Character.isLowerCase(input.charAt(j+1))){
                    String aux;
                    int i;
                    if(j!=input.length()-2){
                        if(Character.isDigit(input.charAt(j+2))){
                            aux = input.substring(j,j+2);
                            if(Character.isDigit(input.charAt(j+3))){
                                String a = input.substring(j+2,j+4);
                                i= Integer.parseInt(a);
                            }else{
                                i = Character.getNumericValue(input.charAt(j+2));
                            }
                            resp = resp + aux + Integer.toString(i*multiplicador);
                        }
                    }
                    
                }
                if(Character.isUpperCase(input.charAt(j)) && Character.isUpperCase(input.charAt(j+1))){
                   char aux = input.charAt(j);
                   resp = resp + aux + Integer.toString(multiplicador);
                }
                if(Character.isUpperCase(input.charAt(j)) && Character.isDigit(input.charAt(j+1))){
                    char aux = input.charAt(j);
                    int i;
                    if(j!=input.length()-2){
                        
                        if(Character.isDigit(input.charAt(j+2))){
                            
                            String a = input.substring(j+1,j+3);
                            i= Integer.parseInt(a);
                        }else{
                            i = Character.getNumericValue(input.charAt(j+1));
                        }
                    }else{
                        i = Character.getNumericValue(input.charAt(j+1));
                    }
                    resp = resp + aux + Integer.toString(i*multiplicador);
                }
                if(Character.isUpperCase(input.charAt(input.length()-1)) && j==input.length()-2){
                    char aux = input.charAt(input.length()-1);
                    resp = resp + aux + Integer.toString(multiplicador);
                }
                if(Character.isUpperCase(input.charAt(input.length()-2)) && Character.isLowerCase(input.charAt(input.length()-1)) && j==input.length()-2){
                    String aux = input.substring(input.length()-2,input.length());
                    resp = resp + aux + Integer.toString(multiplicador);
                }
            
        }
        return resp;
    }
    // Metodo que recebe o conteúdo do arquivo e resolve os colchetes. Por exemplo, se há na equação um [H20]2,
    //o metodo irá retirar os colchetes e multiplicar as moléculas pelo número multiplicador fora do colchetes.
    private static String resolverColchetes(String equacao){
        Pattern ptrn = Pattern.compile("(\\[)([a-zA-Z0-9]+)(\\])");
        Matcher matcher = ptrn.matcher(equacao);
        while (matcher.find()) {
            int m;
            try{
                if(Character.isDigit(equacao.charAt(matcher.end()))){
                    m=Character.getNumericValue(equacao.charAt(matcher.end()));
                }else{
                    m=1;
                }
            }catch(StringIndexOutOfBoundsException e){
                m=1;           
            }
            String aux = equacao.substring(matcher.start()+1, matcher.end()-1);
            aux = multiplicaSubEquacao(aux, m);
            if(m!=1) equacao = equacao.substring(0, matcher.start()) + aux + equacao.substring(matcher.end()+1, equacao.length());
            if(m==1){
                if(matcher.end() == equacao.length()){
                    equacao = equacao.substring(0, matcher.start()) + aux;
                }else{
                    equacao = equacao.substring(0, matcher.start()) + aux + equacao.substring(matcher.end(), equacao.length());
                }
            }
            matcher = ptrn.matcher(equacao);
        }
        return equacao;
        
    }
    // Metodo que recebe o conteúdo do arquivo e resolve as chaves. Por exemplo, se há na equação um {H20}2,
    //o metodo irá retirar as chaves e multiplicar as moléculas pelo número multiplicador fora das chaves.
    private static String resolverChaves(String equacao){
        Pattern ptrn = Pattern.compile("(\\{)([a-zA-Z0-9]+)(\\})");
        Matcher matcher = ptrn.matcher(equacao);
        while (matcher.find()) {
            int m;
            try{
                if(Character.isDigit(equacao.charAt(matcher.end()))){
                    m=Character.getNumericValue(equacao.charAt(matcher.end()));
                }else{
                    m=1;
                }
            }catch(StringIndexOutOfBoundsException e){
                m=1;           
            }
            String aux = equacao.substring(matcher.start()+1, matcher.end()-1);
            aux = multiplicaSubEquacao(aux, m);
            if(m!=1) equacao = equacao.substring(0, matcher.start()) + aux + equacao.substring(matcher.end()+1, equacao.length());
            if(m==1){
                if(matcher.end() == equacao.length()){
                    equacao = equacao.substring(0, matcher.start()) + aux;
                }else{
                    equacao = equacao.substring(0, matcher.start()) + aux + equacao.substring(matcher.end(), equacao.length());
                }
            }
            matcher = ptrn.matcher(equacao);
        }
        return equacao;
        
    }
    
    //O metodo recebe a equacao livre dos colchetes, parenteses e chaves. O intuito desse metodo é agrupar as moléculas iguais e somar o numero de cada uma delas.
    private static void somarMoleculas(String equacao, String endereco) throws IOException{
        String resp = "";
        Elemento[] vetor = new Elemento[equacao.length()];
        int tam = 0;
        for(int i = 0; i<equacao.length(); i++){
            if(Character.isUpperCase(equacao.charAt(i)) && Character.isLowerCase(equacao.charAt(i+1))){
                if(i!=equacao.length()-1){
                    if(Character.isDigit(equacao.charAt(i+2))){
                        if(Character.isDigit(equacao.charAt(i+3))){
                            String a = equacao.substring(i+2,i+4);
                            int j= Integer.parseInt(a);
                            if(moleculaInclusa(vetor,tam,equacao.substring(i,i+2))!=-1){
                                vetor[moleculaInclusa(vetor,tam,equacao.substring(i,i+2))].addMoleculas(j);
                            }else{
                                vetor[tam++] = new Elemento(equacao.substring(i,i+2), j);
                            }
                        }else{
                            if(moleculaInclusa(vetor,tam,equacao.substring(i,i+2))!=-1){
                                vetor[moleculaInclusa(vetor,tam,equacao.substring(i,i+2))].addMoleculas(Character.getNumericValue(equacao.charAt(i+2)));
                            }else{
                                vetor[tam++] = new Elemento(equacao.substring(i,i+2), Character.getNumericValue(equacao.charAt(i+2)));
                            }
                        }
                    }
                    if(Character.isUpperCase(equacao.charAt(i+2))){
                        if(moleculaInclusa(vetor,tam,equacao.substring(i,i+2))!=-1){
                            vetor[moleculaInclusa(vetor,tam,equacao.substring(i,i+2))].addMoleculas(1);
                        }else{
                            vetor[tam++] = new Elemento(equacao.substring(i,i+2),1);
                        }
                    }
                }
            }
            boolean aa = false;
            if(Character.isUpperCase(equacao.charAt(i))){
                if(i!=equacao.length()-1){
                   if(Character.isDigit(equacao.charAt(i+1))){
                        if(i!=equacao.length()-2){
                            aa=Character.isDigit(equacao.charAt(i+2));
                        }                       
                            if(aa){
                                String a = equacao.substring(i+1,i+3);
                                int j= Integer.parseInt(a);
                                if(moleculaInclusa(vetor,tam,equacao.substring(i,i+1))!=-1){
                                     vetor[moleculaInclusa(vetor,tam,equacao.substring(i,i+1))].addMoleculas(j);
                                 }else{
                                    vetor[tam++] = new Elemento(equacao.substring(i,i+1),j);
                                }
                            
                       }else{
                           if(moleculaInclusa(vetor,tam,equacao.substring(i,i+1))!=-1){
                                vetor[moleculaInclusa(vetor,tam,equacao.substring(i,i+1))].addMoleculas(Character.getNumericValue(equacao.charAt(i+1)));
                            }else{
                                vetor[tam++] = new Elemento(equacao.substring(i,i+1), Character.getNumericValue(equacao.charAt(i+1)));
                            }
                       }   
                        
                    }
                    if(Character.isUpperCase(equacao.charAt(i+1))){
                        if(moleculaInclusa(vetor,tam,equacao.substring(i,i+1))!=-1){
                            vetor[moleculaInclusa(vetor,tam,equacao.substring(i,i+1))].addMoleculas(1);
                        }else{
                            vetor[tam++] = new Elemento(equacao.substring(i,i+1),1);
                        }
                    } 
                }
            }
        }
        FileWriter arq = new FileWriter(endereco);
        PrintWriter gravarArq = new PrintWriter(arq);
        for(int i=0; i<tam;i++){
            gravarArq.printf(vetor[i].getElemento()+": "+ vetor[i].getNumMoleculas()+", ");
            
        }
        arq.close();
    }
   
    //Metodo verifica se a molecula mol ja foi inserida no vetor de elementos. Se sim, retorna a posicao do vetor onde a molecula se encontra no vetor. Se nao, devolve -1.
    private static int moleculaInclusa(Elemento[] e,int tam, String mol){
        int resp = -1;
        for(int i = 0; i<tam-1; i++){
            if(e[i].elemento.equals(mol)) resp=i;
        }
        return resp;
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
        String endereco = lerInput();
        String equacao = lerString(endereco);
        equacao = resolverParenteses(equacao);
        equacao = resolverColchetes(equacao);
        equacao = resolverChaves(equacao);
        String enderecoOut = transformaEndereco(endereco);
        somarMoleculas(equacao,enderecoOut);
    }
    
}
