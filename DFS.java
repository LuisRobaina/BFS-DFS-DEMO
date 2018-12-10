import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

class DFS {
    ArrayList<Node> nodes;
    // Visiting Queue
    Stack<Node> visit;
    // Map set to backtrace
    HashMap<Node, Node> backtrace;
    // Set of visited
    ArrayList<Node> visited;

    DFS(ArrayList<Node> nodes) {
        visit = new Stack();
        visited = new ArrayList<>();
        this.nodes = nodes;
        traverse(nodes.get(0), false, null, null);
    }

    DFS(ArrayList<Node> nodes, Node source, Node sink) {
        backtrace = new HashMap<Node, Node>();
        visit = new Stack();
        visited = new ArrayList<>();
        this.nodes = nodes;
        traverse(source, true, source, sink);
    }

    private void traverse(Node n, boolean path, Node source, Node sink) {
        visit.push(n);
        visited.add(n);
        StdDraw.filledCircle(n.x, n.y, 0.005);
        if (path)
            explore(n, source, sink);
        else
            explore(n);

        System.out.println("DONE");
    }

    int color = 0;

    private void explore(Node n, Node source, Node sink) {
        for (Node a : n.bag) {
            if (a == sink) {
                backtrace.put(a, n);
                switch (color) {
                case 0:
                    StdDraw.setPenColor(Color.BLUE);
                    break;
                case 1:
                    StdDraw.setPenColor(Color.ORANGE);
                    break;
                case 2:
                    StdDraw.setPenColor(Color.darkGray);
                    break;
                case 3:
                    StdDraw.setPenColor(Color.green);
                    break;
                default:
                    StdDraw.setPenColor(Color.magenta);
                }
                while (a != source) {
                    Node priv = backtrace.get(a);
                    StdDraw.line(a.x, a.y, priv.x, priv.y);
                    a = priv;
                }
                color++;
            }

            StdDraw.setPenColor(Color.RED);
            if (!visited.contains(a)) {
                StdDraw.line(n.x, n.y, a.x, a.y);
                visited.add(a);
                visit.push(a);
                backtrace.put(a, n);
                visited.add(a);
                // StdDraw.pause(1500);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.line(n.x, n.y, a.x, a.y);
                explore(a, source, sink);
            }
        }
        visit.pop();
    }

    private void explore(Node n) {
        for (Node a : n.bag) {
            StdDraw.setPenColor(Color.RED);
            if (!visited.contains(a)) {
                StdDraw.line(n.x, n.y, a.x, a.y);
                visited.add(a);
                visit.push(a);
                visited.add(a);
                // StdDraw.pause(1500);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.line(n.x, n.y, a.x, a.y);
                explore(a);
            }
        }
        visit.pop();
    }

}