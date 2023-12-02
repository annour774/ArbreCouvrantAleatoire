import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

public class AldousBroder {

    Graph graph;
    ArrayList<Arc> frontier;
    ArrayList<Arc> tree;
    Random random;
    ArrayList<Integer> sommetsAtteints;
    private Arc unVoisin(int vertex) {
        List<Arc> voisins = graph.outNeighbours(vertex);
        return  voisins.get(random.nextInt(voisins.size()));
    }

    private void ajouter(Arc sommet) {
        if(! sommetsAtteints.contains(sommet.getDest())){
            sommetsAtteints.add(sommet.getDest());
            tree.add(sommet);
        }

    }

    private void aab(int sommetDepart) {
        int sommetCourant = sommetDepart;
        while(sommetsAtteints.size() != graph.adjacency.size()){
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
        this.sommetsAtteints = new ArrayList<>();
    }

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        AldousBroder algo = new AldousBroder(graph);
        algo.aab(root);
        return algo.tree;
    }
}
