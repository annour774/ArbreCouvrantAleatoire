import java.util.*;

public class PrimAlgorithm {

    Graph graph;
    ArrayList<Arc> tree;
    TreeSet<Arc> F;
    BitSet reached;

    private void push(int vertex) {
        for (Arc arc : graph.outNeighbours(vertex)) F.add(arc);
    }

    private void explore(Arc nextArc) {
        if (reached.get(nextArc.getDest())) return;
        reached.set(nextArc.getDest());
        tree.add(nextArc);
        push(nextArc.getDest());
    }


    private void apa(int source){
        reached.set(source);
        push(source);
        while(!F.isEmpty()){
            explore(F.pollFirst());
        }
    }

    private PrimAlgorithm (Graph graph) {
        this.graph = graph;
        this.tree = new ArrayList<>();
        F = new TreeSet<>(Comparator.comparing(Arc::getWeight));
        this.reached = new BitSet();
    }

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        PrimAlgorithm algo = new PrimAlgorithm(graph);
        algo.apa(root);
        return algo.tree;
    }
}
