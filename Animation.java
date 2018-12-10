import java.util.*;
import java.awt.Color;
import java.io.*;

class Node {
	double x;
	double y;
	ArrayList<Node> bag; // bag of adjacents nodes.

	Node(double x, double y) {
		this.x = x;
		this.y = y;
		bag = new ArrayList<>();
	}

	public boolean equals(Node that) {
		if (that == null)
			return false;
		if (Math.abs(this.x - that.x) <= 0.01 && Math.abs(this.y - that.y) <= 0.01)
			return true;
		return false;
	}

	public void connect(Node that) {
		if (this == that)
			return;
		this.bag.add(that);
	}
}

public class Animation {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter number of vertices: ");
		int N = in.nextInt(); // Number of nodes on the graph
		ArrayList<Node> nodes = new ArrayList<>();
		Node p;
		boolean equals = false;
		StdDraw.setCanvasSize(1500, 1000);
		StdDraw.setPenRadius(0.005);

		for (int i = 0; i < N; i++) {
			equals = false;
			p = new Node((Math.random() * 0.99) + 0.005, (Math.random() * 0.99) + 0.005);
			for (Node n : nodes) {
				if (n.equals(p)) {
					equals = true;
					break;
				}
			}
			if (equals) {
				i--;
				continue;
			}
			nodes.add(p);
			StdDraw.filledCircle(p.x, p.y, 0.005);
		}

		// Generate random edges
		for (int i = 0; i < N; i++) {
			int randomSource = (int) Math.floor(Math.random() * (nodes.size() - 1)); // get a random index of a node
			int randomNode = (int) Math.floor(Math.random() * (nodes.size() - 1)); // get a random index of a node

			Node A = nodes.get(randomNode);
			Node B = nodes.get(randomSource);
			A.connect(B);
			B.connect(A);
			StdDraw.line(A.x, A.y, B.x, B.y);
		}

		System.out.println(
				"\n\n**********************************************************************************\n\t\tWELCOME TO THE BFS and DFS DEMO\n**********************************************************************************");
		String traverse = " ";
		traverse = in.nextLine();
		boolean path = false;
		Node source = null;
		Node sink = null;
		while (!traverse.contains("EXIT")) {
			if (traverse.contains("SET")) {
				path = true;
				source = nodes.get((int) Math.floor(Math.random() * nodes.size() - 1));
				while (source.bag.isEmpty()) {
					source = nodes.get((int) Math.floor(Math.random() * nodes.size() - 1));
				}
				sink = nodes.get((int) Math.floor(Math.random() * nodes.size() - 1));
				while (sink.bag.isEmpty()) {
					sink = nodes.get((int) Math.floor(Math.random() * nodes.size() - 1));
				}
				StdDraw.setPenColor(Color.RED);
				StdDraw.setPenRadius(0.05);
				StdDraw.text(source.x, source.y + 0.005, "SOURCE");
				StdDraw.text(sink.x, sink.y + 0.005, "SINK");
				StdDraw.setPenRadius(0.005);
			}
			if (traverse.contains("BFS")) {
				if (path) {
					BFS bfs = new BFS(nodes, source, sink); // Run BFS
				}

				else {
					BFS bfs = new BFS(nodes); // Run BFS
				}

			} else if (traverse.contains("DFS")) {
				// call depth first search traverse.
				if (path) {
					DFS dfs = new DFS(nodes, source, sink); // Run DFS
				}

				else {
					DFS dfs = new DFS(nodes); // Run DFS
				}
			}
			traverse = in.nextLine();
		}

		System.out.println("BYE");
		return;
	}
}
