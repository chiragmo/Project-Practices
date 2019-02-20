package graphs.basics;

public class Vertex {
String label;

public Vertex(String label) {
this.label = label;
}

@Override
public String toString() {
	return this.label;
}

@Override
public boolean equals(Object obj) {	
 if(obj == this) {
 	return true;
 }
 if(obj == null) {
 	return false;
 }
 if(obj instanceof Vertex) {
 	Vertex cmp1 = (Vertex) obj;
 	if(cmp1.label.equals(this.label)) {
 		return true;
 	}
 }
 return false;
}

@Override
public int hashCode() {
	int res = 17;
	res = 31 * res + this.label.hashCode();
	return res;
}

}