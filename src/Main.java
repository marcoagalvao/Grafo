public class Main {
    public static void main(String[] args) {

        Grafo g = new Grafo(true, false);

        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B");
        Vertice v3 = new Vertice("C");
        Vertice v4 = new Vertice("D");
        g.addVertices(v1);
        g.addVertices(v2);
        g.addVertices(v3);
        g.addVertices(v4);

        Aresta a1 = new Aresta(v1,v2);
        Aresta a2 = new Aresta(v2,v4);
        Aresta a3 = new Aresta(v4,v3);
        Aresta a4 = new Aresta(v4,v2);
        g.addArestas(a1);
        g.addArestas(a2);
        g.addArestas(a3);
        g.addArestas(a4);



        System.out.println(g);
        g.isDigrafo();
        //g.ordemETamanho();
        //g.adjacencias();
        //g.grau();
        //g.matrizAdjacencia();
        //g.matrizIncidencia();
        System.out.println("O comprimento desse caminho é " + g.calculaComprimento(new Vertice[]{v1,v2,v3,v4}));
        g.caminhoMenorComprimento(new Vertice[]{v2,v3,v4}, new Vertice[]{v1,v2,v3,v4}, new Vertice[]{v3,v4});
    }
}