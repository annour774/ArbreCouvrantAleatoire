import java.util.ArrayList;
import java.util.BitSet;

import java.util.Random;
public class RandomParcours {

    Graph graph;
    ArrayList<Arc> frontier;
    ArrayList<Arc> tree;
    BitSet reached;
    Random random;
    private void push(int vertex) {
        frontier.addAll(graph.outNeighbours(vertex));
    }

    private void explore(Arc nextArc) {
        if (reached.get(nextArc.getDest())) return;
        reached.set(nextArc.getDest());
        tree.add(nextArc);
        push(nextArc.getDest());
    }
    private void arp(int startingVertex) {
        reached.set(startingVertex);
        push(startingVertex);
        while (!frontier.isEmpty()) {
            int index = random.nextInt(frontier.size());
            Arc sommet = frontier.remove(index);
            if(sommet!= null) {
                explore(sommet);
            } else {
                explore(frontier.get(random.nextInt(frontier.size())));
            }
        }
    }

    private RandomParcours (Graph graph) {
        this.graph = graph;
        this.frontier = new ArrayList<>();
        this.tree = new ArrayList<>();
        this.reached = new BitSet(graph.order);
        this.random = new Random();
    }

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        RandomParcours algo = new RandomParcours(graph);
        algo.arp(root);
        return algo.tree;
    }

}