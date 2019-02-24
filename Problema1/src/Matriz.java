
/**
 *
 * @author Pedro
 */

//Classe criada para armazenar a matriz de caracteres e realizar as operaçoes.
public class Matriz {
    
    private char[][] matriz;
    private int tamanho;
    private int indice;
    private String palavra;
    private char[] palavraTransformada;
    
    //Construtor
    public Matriz(String palavraCodificada, int indice){
        
        this.indice = indice;
        this.tamanho = palavraCodificada.length();
        this.palavra = palavraCodificada;
        this.matriz = new char[palavra.length()][palavra.length()];
    }
    
    //Construtor
    public Matriz(String palavra){
        
        this.indice = -1;
        this.tamanho = palavra.length();
        this.matriz = new char[palavra.length()][palavra.length()];
        this.palavra = palavra;
        this.palavraTransformada = new char[palavra.length()];
        this.criarMatriz();
    }
    
    public char[][] getMatriz(){
        
        return this.matriz;
    }
    
    public String getPalavra(){
        
        return this.palavra;
    }
    
    public int getIndice(){
        
        for (int p=0; p < this.tamanho; p++){
            for (int i = 0; i < this.tamanho; i++){
                if(this.matriz[p][i] == this.palavra.charAt(i)){
                    if(i==this.tamanho-1){
                        this.indice=p;
                    }
                }else{
                    break;
                }
                
            }
        }
        return this.indice;
    }
    
    //Retorna a palavra transformada
    public String getTransformacao(){
        
        for(int i=0; i < this.tamanho; i++){
            this.palavraTransformada[i]=this.matriz[i][this.tamanho-1];
        }
        return new String(this.palavraTransformada);
    }
    
    private void criarMatriz(){
        
        this.popularMatriz(); 
        this.ordenarMatriz();
    }
    
    //Popula a matriz de caracteres a partir da palavra base
    private void popularMatriz(){
        
        int aux = this.tamanho - 1;
        int aux2;
        for (int i = 0; i< this.tamanho; i++){
            this.matriz[0][i]=this.palavra.charAt(i);
        }
        for (int i = 1; i < this.tamanho; i++){
            aux2=aux;
            for (int j = 0; j < this.tamanho; j++){
                this.matriz[i][j]= this.palavra.charAt(aux2%tamanho);
                aux2++;
            }
            aux--;
        }
    }
    
    private void trocaLinha(int l1, int l2){
        
        char aux;
        for(int i=0; i < this.tamanho; i++){
            aux = this.matriz[l1][i];
            this.matriz[l1][i] = this.matriz[l2][i];
            this.matriz[l2][i] = aux;
        }
        
    }
    
    private void ordenarMatriz(){
        
        for (int p=0; p < this.tamanho; p++){
            for (int i = p + 1; i < this.tamanho; i++){
                for (int j = 0; j < this.tamanho; j++){
                    if(this.matriz[p][j] > this.matriz[i][j]){
                        this.trocaLinha(p, i);
                    }
                    if(this.matriz[p][j] < this.matriz[i][j]){
                        break;
                    }
                }
            }
        }
    }
    
    public char[] decodificar(){
        
        int numColuna = this.tamanho - 1;
        char[] palavraDecodificada = new char[this.tamanho]; 
        for (int p=0; p < this.tamanho; p++){
            for (int i = (this.tamanho - 1); i >= 0 ; i--){
                this.matriz[i][numColuna] = this.palavra.charAt(i);
            }
            this.matriz = reordenarMatriz(this.matriz, numColuna);
            numColuna--;
        }     
        System.arraycopy(this.matriz[indice], 0, palavraDecodificada, 0, this.tamanho);
        return palavraDecodificada;
    }
    
    
    
    private void trocaLinhaAux(int l1, int l2){
        
        char aux;
        for(int i=0; i < this.tamanho; i++){
            aux =this.matriz[l1][i];
            this.matriz[l1][i] = this.matriz[l2][i];
            this.matriz[l2][i] = aux;
        }
        
    }
    
    private char[][] reordenarMatriz(char[][] m, int numColuna){
        
        for (int p=0; p < this.tamanho; p++){
            for (int i = p + 1; i < this.tamanho; i++){
                for (int j = numColuna; j < this.tamanho; j++){
                    if(m[p][j] > m[i][j]){
                        this.trocaLinhaAux(p, i);
                    }
                    if(m[p][j] < m[i][j]){
                        break;
                    }
                }
            }
        }
        return m;
    }
    
    
    
    
}
