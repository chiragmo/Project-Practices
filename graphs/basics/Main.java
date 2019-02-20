package graphs.basics;

public class Main {
	public static void main(String[] args) {
		boolean isDirected = false;

		Graph graph = new Graph(isDirected);
		
		graph.addVertex("new york");
		graph.addVertex("dallas");
		graph.addVertex("chicago");
		graph.addVertex("binghamton");

		graph.createEdge("new york","dallas");
		graph.createEdge("new york","chicago");
		graph.createEdge("chicago","binghamton");
		graph.createEdge("dallas","binghamton");
		graph.createEdge("binghamton", "new york");
		System.out.println(graph);

		graph.removeEdge("chicago","binghamton");
		graph.removeVertex("dallas");
		System.out.println(graph);
	}
}