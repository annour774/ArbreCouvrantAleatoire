import java.util.*;

public class AldousBroder {

    Graph graph;
    ArrayList<Arc> frontier;
    ArrayList<Arc> tree;
    Random random;
    BitSet sommetsAtteints;
    int cpt = 0;
    private Arc unVoisin(int vertex) {
        List<Arc> voisins = graph.outNeighbours(vertex);
        return  voisins.get(random.nextInt(voisins.size()));
    }

    private void ajouter(Arc sommet) {
        if(! sommetsAtteints.get(sommet.getDest())){
            sommetsAtteints.set(sommet.getDest());
            tree.add(sommet);
            cpt++;
        }

    }

    private void aab(int sommetDepart) {
        int sommetCourant = sommetDepart;
        cpt++;
        sommetsAtteints.set(sommetCourant);
        while(cpt < graph.order){
            Arc unVoisin = unVoisin(sommetCourant);
            sommetCourant = unVoisin.getDest();
            ajouter(unVoisin);
        }
    }

    private AldousBroder (Graph graph) {
        this.graph = graph;
        this.frontier = new ArrayList<>();
        this.tree = new ArrayList<>();
        this.random = new Random();
        this.sommetsAtteints = new BitSet();
    }

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        AldousBroder algo = new AldousBroder(graph);
        algo.aab(root);
        return algo.tree;
    }
}
