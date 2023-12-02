import java.util.*;

public class PrimAlgorithm {

    Graph graph;
    ArrayList<Arc> tree;
     int[] F;
    int maxWeight = 0;
    double[] d;
    int n;
    int[] pred;

    private void apa(int s) {
        F = new int[n];
        d = new double[n];
        pred = new int[n];
        int length = n;
        int u;
        for(int i = 0; i < n; i++) {
            d[i] = n * maxWeight + 1;
        }
        d[s] = 0;
        pred[s] = -1;
        while(length != 0){
            u = extraireLeMin(F, d, length);
            length--;
            for(Arc arc: graph.outNeighbours(u)){
                if(d[arc.getDest()] > arc.getWeight()) {
                    d[arc.getDest()] = arc.getWeight();
                    pred[arc.getDest()] = u;
                };
            }
        }
    }

    public int extraireLeMin(int[] F, double[] d, int length) {
        double minD = maxWeight * n + 1;
        int index = 0;
        for(int i = 0; i < length; i++)
            if(d[F[i]] < minD) {
                minD = d[F[i]];
                index = i;
            }
        int tmp = F[length - 1];
        F[length - 1] = F[index];
        F[index] = F[length - 1];
        return F[length - 1];
    }

    public void creerArbre(){
        for(int i = 0; i < n; i++){
            tree.add(new Arc(new Edge(pred[i], (int)d[i], 0), false));
        }
    }

    private PrimAlgorithm (Graph graph) {
        this.graph = graph;
        this.tree = new ArrayList<>();
        this.n = graph.order;
    }

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        PrimAlgorithm algo = new PrimAlgorithm(graph);
        algo.apa(root);
        algo.creerArbre();
        return algo.tree;
    }
}
