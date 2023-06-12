public class Main {
    public static void main(String[] args) {

        Grafo g = new Grafo(true, false);

        Vertice v1 = new Vertice("1");
        Vertice v2 = new Vertice("2");
        Vertice v3 = new Vertice("3");
        Vertice v4 = new Vertice("4");
        Vertice v5 = new Vertice("5");
        Vertice v6 = new Vertice("6");
        Vertice v7 = new Vertice("7");
        g.addVertices(v1);
        g.addVertices(v2);
        g.addVertices(v3);
        g.addVertices(v4);
        g.addVertices(v5);
        g.addVertices(v6);
        g.addVertices(v7);


        Aresta a1 = new Aresta(v1,v2);
        Aresta a2 = new Aresta(v2,v3);
        Aresta a3 = new Aresta(v2,v4);
        Aresta a4 = new Aresta(v3,v1);
        Aresta a5 = new Aresta(v4,v1);
        Aresta a6 = new Aresta(v4,v3);
        Aresta a7 = new Aresta(v5,v6);
        Aresta a8 = new Aresta(v5,v7);
        Aresta a9 = new Aresta(v6,v2);
        Aresta a10 = new Aresta(v7,v4);

        g.addArestas(a1);
        g.addArestas(a2);
        g.addArestas(a3);
        g.addArestas(a4);
        g.addArestas(a5);
        g.addArestas(a6);
        g.addArestas(a7);
        g.addArestas(a8);
        g.addArestas(a9);
        g.addArestas(a10);



        System.out.println(g);
        g.isDigrafo();
        //g.ordemETamanho();
        //g.adjacencias();
        //g.grau();
        //g.matrizAdjacencia();
        //g.matrizIncidencia();
        //System.out.println("O comprimento desse caminho é " + g.calculaComprimento(new Vertice[]{v1,v2,v3,v4}));
        //g.caminhoMenorComprimento(new Vertice[]{v2,v3,v4}, new Vertice[]{v1,v2,v3,v4}, new Vertice[]{v3,v4});

        g.buscaEmProfundidade(v5, v1);
        g.buscaEmLargura(v5,v1);
    }
}