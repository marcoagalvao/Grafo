import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Aresta {

    private Vertice origem;
    private Vertice destino;
    private int peso;

    public Aresta(Vertice origem, Vertice destino){
        this.origem = origem;
        this.destino = destino;
    }

    public String toString(){
        return "(" + origem.getNome() + "," + destino.getNome() + ")";
    }
}
