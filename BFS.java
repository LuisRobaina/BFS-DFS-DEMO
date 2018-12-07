import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import com.sun.javafx.collections.MappingChange.Map;

// BFS 
class BFS {
    ArrayList<Node> nodes;
    // Visiting Queue
    Queue<Node> visit;
    // Map set to backtrace
    HashMap<Node, Node> backtrace;

    // Set of visited
    ArrayList<Node> visited;

    BFS(ArrayList<Node> nodes) {
        visit = new LinkedList<>();
        visited = new ArrayList<>();
        this.nodes = nodes;
        traverse(nodes.get(0), false, null, null);
    }

    BFS(ArrayList<Node> nodes, Node source, Node sink) {
        backtrace = new HashMap<Node, Node>();
        visit = new LinkedList<>();
        visited = new ArrayList<>();
        this.nodes = nodes;
        traverse(source, true, source, sink);
    }

    private void traverse(Node n, boolean path, Node source, Node sink) {
        visit.add(n);
        visited.add(n);
        StdDraw.filledCircle(n.x, n.y, 0.005);

        while (!visit.isEmpty()) {
            n = visit.remove();
            if (path)
                explore(n, source, sink);
            else
                explore(n);
        }
        System.out.println("DONE");
    }

    private void explore(Node n, Node source, Node sink) {

        for (Node a : n.bag) {
            StdDraw.setPenColor(Color.RED);
            System.out.println(a);

            if (a == sink) {
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.line(n.x, n.y, a.x, a.y);
                visited.add(sink);
                Node current = n;

                while (current != source) {
                    Node priv = backtrace.get(current);
                    StdDraw.line(current.x, current.y, priv.x, priv.y);
                    current = priv;
                }
                // found path
            }

            if (!visited.contains(a)) {
                StdDraw.line(n.x, n.y, a.x, a.y);
                visit.add(a);
                backtrace.put(a, n);
                // Map
                visited.add(a);
                StdDraw.pause(1500);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.line(n.x, n.y, a.x, a.y);

            }
        }
    }

    private void explore(Node n) {
        for (Node a : n.bag) {
            StdDraw.setPenColor(Color.RED);
            System.out.println(a);
            if (!visited.contains(a)) {
                StdDraw.line(n.x, n.y, a.x, a.y);
                visit.add(a);
                // Map
                visited.add(a);
                StdDraw.pause(1500);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.line(n.x, n.y, a.x, a.y);

            }
        }
    }

}