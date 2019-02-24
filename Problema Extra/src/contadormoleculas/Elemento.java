package contadormoleculas;

/**
 *
 * @author Pedro Augusto Prosdocimi Resende
 */

//Classe criada para fazer um vetor com o elemento e o seu numero de moleculas.
public class Elemento {
    public String elemento;
    int numMoleculas;
    
    public Elemento(){
        
    }
    
    public Elemento(String elemento, int numMoleculas){
        this.elemento=elemento;
        this.numMoleculas = numMoleculas;
    }
    
    public String getElemento(){
        return this.elemento;
    }
    
    public int getNumMoleculas(){
        return this.numMoleculas;
    }
    
    public void addMoleculas(int m){
        this.numMoleculas +=m;
    }
    
}
