package graphs.basics;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Graph {
Map<Vertex,Set<Vertex>> vertexes;
Graph.Edge ed;
Boolean isDirected;

public class Edge {
	public void creation(Vertex V1, Vertex V2) {
	Set<Vertex> li1 = vertexes.get(V1);
	li1.add(V2);
	if(!isDirected) {
	Set<Vertex> li2 = vertexes.get(V2);
	li2.add(V1);
		}
	}

	public void removal(Vertex V1, Vertex V2) {
	Set<Vertex> li1 = vertexes.get(V1);
	li1.remove(V2);
	if(!isDirected) {
	Set<Vertex> li2 = vertexes.get(V2);	
	li2.remove(V1);
		}
	}
}

public Graph(Boolean isDirected) {
	vertexes = new HashMap<Vertex,Set<Vertex>>();
	ed = this.new Edge();
	this.isDirected = isDirected;
}

public void createEdge(String label1, String label2) {
	ed.creation(new Vertex(label1), new Vertex(label2));
}

public void removeEdge(String label1, String label2) {
	ed.removal(new Vertex(label1), new Vertex(label2));
}

public void addVertex(String label) {
    vertexes.putIfAbsent(new Vertex(label), new HashSet<Vertex>());
}

public void removeVertex(String label) {
	Vertex v = new Vertex(label);
	if(!isDirected) {
	Set<Vertex> li = vertexes.get(v);
	for(Vertex elem : li) {
		Set<Vertex> temp = vertexes.get(elem);
		temp.remove(v);
		}
	}
	vertexes.remove(v);
}

@Override
public String toString() {
Iterator<Map.Entry<Vertex,Set<Vertex>>> itr = vertexes.entrySet().iterator();
String s = "";
while(itr.hasNext()) {
Map.Entry<Vertex,Set<Vertex>> entry = itr.next(); 
s = s + entry.getKey() + "---> ";
for(Vertex elem : entry.getValue()) {
	s = s + elem.toString() + " ";
}
s = s + "\n";
}
return s;
}

}
