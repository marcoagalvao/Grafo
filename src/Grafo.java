import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Grafo {

    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;
    private int ordem;
    private int tamanho;
    private boolean direcionado;
    private boolean ponderado;

    public Grafo() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }

    public Grafo(boolean direcionado, boolean ponderado) {
        this.direcionado = direcionado;
        this.ponderado = ponderado;
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }

    public void addVertices(Vertice vertice) {
        vertices.add(vertice);
        ordem++;
    }

    public void addArestas(Aresta aresta) {
        arestas.add(aresta);
        tamanho++;
    }

    public boolean isDigrafo() {
        for (Aresta a : arestas) {
            if (a.getOrigem() == a.getDestino()) {
                System.out.println("É um digrafo, pois possui um SelfLoop no vértice: " + a.getOrigem().getNome());
                direcionado = true;
                return true;
            }
            for (Aresta b : arestas) {
                if (a.getOrigem() == b.getDestino() && a.getDestino() == b.getOrigem()) {
                    System.out.println("É um digrafo, pois possui arestas em duplo sentido nos vértices: " + a.getOrigem().getNome() + "," + b.getOrigem().getNome());
                    direcionado = true;
                    return true;
                }
            }
        }
        if(direcionado){
            System.out.println("O grafo é direcionado");
        }else{
            System.out.println("O grafo não é direcionado");
        }
        return direcionado;
    }

    public String toString() {
        StringBuilder infoGrafo = new StringBuilder();
        infoGrafo.append("Grafo -> Vértices:");
        infoGrafo.append(vertices.stream().map(vertice -> "," + vertice.getNome()).collect(Collectors.joining("")));
        infoGrafo.append("\nArestas: ").append(arestas);
        return infoGrafo.toString();

    }

    public void ordemETamanho() {
        System.out.println("A ordem do grafo é: " + ordem);
        System.out.println("O tamanho do grafo é: " + tamanho);
        System.out.println();
    }

    public void adjacencias() {
        if (direcionado) {
            for (Vertice v : vertices) {
                ArrayList<Vertice> adj = new ArrayList<>();
                for (Aresta a : arestas) {
                    if (a.getDestino() == v) {
                        adj.add(a.getOrigem());
                    }
                }
                System.out.println("O vértice " + v.getNome() + " é adjacente a: " + adj);
                System.out.println();
            }
        } else {
            for (Vertice v : vertices) {
                ArrayList<Vertice> adj = new ArrayList<>();
                for (Aresta a : arestas) {
                    if (a.getDestino() == v) {
                        adj.add(a.getOrigem());
                    }
                    if (a.getOrigem() == v) {
                        adj.add(a.getDestino());
                    }
                }
                System.out.println("O vértice " + v.getNome() + " é adjacente a: " + adj);
                System.out.println();
            }
        }
    }

    public void grau() {
        if (direcionado) {
            for (Vertice v : vertices) {
                int grauin = 0;
                int grauout = 0;
                for (Aresta a : arestas) {
                    if (a.getOrigem() == v) {
                        grauout++;
                    }
                    if (a.getDestino() == v) {
                        grauin++;
                    }
                }
                v.setGrauIn(grauin);
                v.setGrauOut(grauout);
                v.setGrau(grauin + grauout);

                System.out.println("O vértice " + v.getNome() + " possui grau in = " + v.getGrauIn());
                System.out.println("O vértice " + v.getNome() + " possui grau out = " + v.getGrauOut());
                System.out.println("O vértice " + v.getNome() + " possui grau total = " + v.getGrau());
                System.out.println();
            }
        } else {
            for (Vertice v : vertices) {
                int grau = 0;
                for (Aresta a : arestas) {
                    if (a.getOrigem() == v) {
                        grau++;
                    }
                    if (a.getDestino() == v) {
                        grau++;
                    }
                }
                v.setGrau(grau);

                System.out.println("O vértice " + v.getNome() + " possui o grau = " + v.getGrau());
            }
        }

    }

    public void matrizAdjacencia() {
        int[][] matriz = new int[ordem][ordem];
        if (direcionado) {
            for (Aresta aresta : arestas) {
                int origem = vertices.indexOf(aresta.getOrigem());
                int destino = vertices.indexOf(aresta.getDestino());
                matriz[origem][destino] = 1;
            }

            System.out.println("Matriz de adjacência do grafo direcionado:");

            System.out.print("  ");
            for (Vertice v : vertices) {
                System.out.print(v.getNome() + " ");
            }

            System.out.println();

            for (int i = 0; i < ordem; i++) {
                System.out.print(vertices.get(i).getNome() + " ");
                for (int j = 0; j < ordem; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            for (Aresta aresta : arestas) {
                int origem = vertices.indexOf(aresta.getOrigem());
                int destino = vertices.indexOf(aresta.getDestino());
                matriz[origem][destino] = 1;
                matriz[destino][origem] = 1;
            }
            System.out.println("Matriz de adjacência do grafo não direcionado:");

            System.out.print("  ");
            for (Vertice v : vertices) {
                System.out.print(v.getNome() + " ");
            }

            System.out.println();

            for (int i = 0; i < ordem; i++) {
                System.out.print(vertices.get(i).getNome() + " ");
                for (int j = 0; j < ordem; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public void matrizIncidencia() {
        int[][] matriz = new int[ordem][tamanho];
        //Sai = -1 || Entra = +1
        if (direcionado) {
            int coluna = 0;
            for (Aresta aresta : arestas) {
                int origem = vertices.indexOf(aresta.getOrigem());
                int destino = vertices.indexOf(aresta.getDestino());
                matriz[origem][coluna] = -1;
                matriz[destino][coluna] = 1;

                coluna++;
            }

            System.out.println();
            System.out.println("Matriz de incidência do grafo direcionado:");

            System.out.print("  ");
            for (int k = 0; k < tamanho; k++){
                System.out.print("  a" + (k + 1));
            }

            System.out.println();

            for (int i = 0; i < ordem; i++) {
                System.out.print(vertices.get(i).getNome() + "   ");
                for (int j = 0; j < coluna; j++) {
                    System.out.print(matriz[i][j] + "   ");
                }
                System.out.println();
            }
        } else {
            int coluna = 0;
            for (Aresta aresta : arestas) {
                int origem = vertices.indexOf(aresta.getOrigem());
                int destino = vertices.indexOf(aresta.getDestino());

                matriz[origem][coluna] = 1;
                matriz[destino][coluna] = 1;

                coluna++;
            }

            System.out.println();
            System.out.println("Matriz de incidência do grafo não direcionado:");


            for (int k = 0; k < ordem; k++){
                System.out.print(" a" + (k + 1));
            }

            System.out.println();

            for (int i = 0; i < ordem; i++) {
                System.out.print(vertices.get(i).getNome() + " ");
                for (int j = 0; j < coluna; j++) {
                    System.out.print(matriz[i][j] + "  ");
                }
                System.out.println();
            }
        }
    }

    public int calculaComprimento(Vertice[] caminho){
        if(!ponderado) return caminho.length - 1;
        int comprimento = 0;
        for(int i = 0; i < caminho.length - 1; i++){ // length-1 é pra nao ultrapassar o limite do array, uma vez: (1,5,7,9) Caminhos: 1-5|5-7|7-9.
            for(Aresta aresta : arestas){
                if(aresta.getOrigem().equals(caminho[i]) && aresta.equals(caminho[i+1])){ // ai aqui seria, i=1 i+1=5 e na ultima iteração ficaria i=7 i+1=9. Ai para o for, pois se for i=9 o i+1 nao existiria
                    comprimento += aresta.getPeso();
                    break; // aqui ele da o break quando encontrar a aresta que tem a origem e o destino igual ao que buscamos
                }
            }
        }
        return comprimento;
    }

    public void caminhoMenorComprimento(Vertice[]... caminhos){
        int menorComprimento = Integer.MAX_VALUE;
        Vertice[] menorCaminho = null;
        for(Vertice[] caminho : caminhos){
            int comprimento = calculaComprimento(caminho);
            if(comprimento < menorComprimento){
                menorComprimento = comprimento;
                menorCaminho = caminho;
            }
        }
        System.out.println("Menor Caminho: " + Arrays.toString(menorCaminho));
        System.out.println("Comprimento: "+ menorComprimento);
    }

    public void buscaEmProfundidade(Vertice inicio, Vertice destino) {
        boolean encontrado;
        List<Vertice> visitados = new ArrayList<>();
        List<Vertice> caminho = new ArrayList<>();
        caminho.add(inicio);

        // Verifica se o vértice de início é igual ao vértice de destino
        if (inicio.equals(destino)) {
            encontrado = true;
        } else {
            // Realiza a busca em profundidade recursiva
            encontrado = buscaEmProfundidadeRecursivo(inicio, destino, visitados, caminho);
        }

        // Verifica se o destino foi encontrado ou não
        if (encontrado) {
            // Calcula o comprimento do caminho e exibe o resultado
            int comprimento = calculaComprimento(caminho.toArray(new Vertice[0]));
            System.out.println("Caminho encontrado: " + caminho);
            System.out.println("Comprimento do caminho: " + comprimento);
        } else {
            // Exibe uma mensagem indicando que o vértice de destino não foi encontrado
            System.out.println("O vértice destino não foi encontrado.");
            System.out.println("Percurso ao longo do grafo: " + caminho);
        }
    }

    private boolean buscaEmProfundidadeRecursivo(Vertice atual, Vertice destino, List<Vertice> visitados, List<Vertice> caminho) {
        // Adiciona o vértice atual à lista de visitados
        visitados.add(atual);

        // Verifica se o vértice atual é igual ao vértice de destino
        if (atual.equals(destino)) {
            return true; // Retorna true, indicando que o destino foi encontrado
        }

        // Obtém os vizinhos do vértice atual
        List<Vertice> vizinhos = obterVizinhos(atual);
        for (Vertice vizinho : vizinhos) {
            if (!visitados.contains(vizinho)) {
                // Adiciona o vizinho ao caminho
                caminho.add(vizinho);
                // Realiza a busca em profundidade recursiva a partir do vizinho
                boolean encontrado = buscaEmProfundidadeRecursivo(vizinho, destino, visitados, caminho);
                if (encontrado) {
                    return true; // Retorna true, indicando que o destino foi encontrado
                }
            }
        }

        return false; // Retorna false, indicando que o destino não foi encontrado
    }

    private List<Vertice> obterVizinhos(Vertice vertice) {
        List<Vertice> vizinhos = new ArrayList<>();

        // Percorre todas as arestas do grafo
        for (Aresta aresta : arestas) {
            if (direcionado) {
                // Se o grafo for direcionado, verifica se a origem da aresta é igual ao vértice fornecido
                if (aresta.getOrigem().equals(vertice)) {
                    // Adiciona o destino da aresta à lista de vizinhos
                    vizinhos.add(aresta.getDestino());
                }
            } else {
                // Se o grafo não for direcionado
                if (aresta.getOrigem().equals(vertice)) {
                    // Se a origem da aresta for igual ao vértice fornecido, adiciona o destino à lista de vizinhos
                    vizinhos.add(aresta.getDestino());
                } else if (aresta.getDestino().equals(vertice)) {
                    // Se o destino da aresta for igual ao vértice fornecido, adiciona a origem à lista de vizinhos
                    vizinhos.add(aresta.getOrigem());
                }
            }
        }

        return vizinhos; // Retorna a lista de vizinhos do vértice
    }


}