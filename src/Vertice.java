import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vertice {

    private String nome;
    private int grau;
    private int grauOut;
    private int grauIn;
    public Vertice(String nome){
        this.nome = nome;
    }

    public String toString(){
        return this.nome;
    }

}
