import java.util.ArrayList;
import java.lang.Math;

class TetrahedralCoordinates	{

	public static ArrayList<Coordinates.Vertex> getVertices(int n)	{
		ArrayList<Coordinates.Vertex> vertices = new ArrayList();
		Vector[] axisZ = new Vector[] {
			new Vector(1, 1, -1), 
			new Vector(-1, 1, 1), 
			new Vector(-1, -1, -1), 
			new Vector(1, -1, 1)};

		Vector[] axis = new Vector[] { 
			new Vector(-1, 0, -1), 
			new Vector(0, -1, 1), 
			new Vector(1, 0, -1), 
			new Vector(0, 1, 1)};

		for(int i=0; i<4; i++)	{
			Vector axis1 = Vector.normalized(axisZ[i]);
			Vector axis2 = Vector.normalized(axis[i]);
			Vector axis3 = Vector.normalized(Vector.mult(axis[(i+3)%4], -1));

			float length = ((float) Math.sqrt(2))/n;
			Vector origin = Vector.add(Vector.mult(axis1, ((float) Math.sqrt(1f/12))), Vector.add(Vector.mult(axis2, -((float) Math.sqrt(2)/3)), 
				Vector.mult(axis3, -((float) Math.sqrt(2)/3))));
			for (int u = 0; u <= n; u++) { 
				for (int v = 0; v <= n-u; v++) {
					
					Vector p = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, v*length)));
					p.normalize();

					boolean contains = false;
					for(Coordinates.Vertex vertex : vertices)	{
						if (vertex.v.appxEquals(p)) {
							contains = true;
							break;
						}
					}

					if (!contains) {
						if (u == 0 || v == 0 || u+v == n) {
							vertices.add(new Coordinates.Vertex(p, Coordinates.colours[6]));
						}else {
							vertices.add(new Coordinates.Vertex(p, Coordinates.colours[i]));
						}
					}
				}
			}
		}
		return vertices;
	}

	public static ArrayList<Coordinates.Edge> getEdges(int n)	{
		ArrayList<Coordinates.Edge> edges = new ArrayList();
		Vector[] axisZ = new Vector[] {
			new Vector(1, 1, -1), 
			new Vector(-1, 1, 1), 
			new Vector(-1, -1, -1), 
			new Vector(1, -1, 1)};

		Vector[] axis = new Vector[] { 
			new Vector(-1, 0, -1), 
			new Vector(0, -1, 1), 
			new Vector(1, 0, -1), 
			new Vector(0, 1, 1)};

		for(int i=0; i<4; i++)	{
			Vector axis1 = Vector.normalized(axisZ[i]);
			Vector axis2 = Vector.normalized(axis[i]);
			Vector axis3 = Vector.normalized(Vector.mult(axis[(i+3)%4], -1));

			float length = ((float) Math.sqrt(2))/n;
			Vector origin = Vector.add(Vector.mult(axis1, ((float) Math.sqrt(1f/12))), Vector.add(Vector.mult(axis2, -((float) Math.sqrt(2)/3)), 
				Vector.mult(axis3, -((float) Math.sqrt(2)/3))));
			for (int u = 0; u < n; u++) { 
				for (int v = 0; v < n-u; v++) {
					
					Vector p1 = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, v*length)));
					p1.normalize();
					Vector p2 = Vector.add(origin, Vector.add(Vector.mult(axis2, (u+1)*length), Vector.mult(axis3, v*length)));
					p2.normalize();
					Vector p3 = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, (v+1)*length)));
					p3.normalize();

					boolean contains = false;
					for(Coordinates.Edge edge : edges)	{
						if ((edge.v1.appxEquals(p1) && edge.v2.appxEquals(p2)) || (edge.v1.appxEquals(p2) && edge.v2.appxEquals(p1))) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						if (v == 0) {
							edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[6]));
						}else {
							edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[i]));
						}
					}

					contains = false;
					for(Coordinates.Edge edge : edges)	{
						if ((edge.v1.appxEquals(p1) && edge.v2.appxEquals(p3)) || (edge.v1.appxEquals(p3) && edge.v2.appxEquals(p1))) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						if (u == 0) {
							edges.add(new Coordinates.Edge(p3, p1, Coordinates.colours[6]));
						}else {
							edges.add(new Coordinates.Edge(p3, p1, Coordinates.colours[i]));
						}
					}

					contains = false;
					for(Coordinates.Edge edge : edges)	{
						if ((edge.v1.appxEquals(p2) && edge.v2.appxEquals(p3)) || (edge.v1.appxEquals(p3) && edge.v2.appxEquals(p2))) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						if (u + v == n-1) {
							edges.add(new Coordinates.Edge(p2, p3, Coordinates.colours[6]));
						}else {
							edges.add(new Coordinates.Edge(p2, p3, Coordinates.colours[i]));
						}
					}

				}
			}
		}
		return edges;
	}

	public static ArrayList<Coordinates.Vertex> getCutVertices(int n)	{
		ArrayList<Coordinates.Vertex> vertices = new ArrayList();
		Vector[] axisZ = new Vector[] {
			new Vector(1, 1, -1), 
			new Vector(-1, 1, 1), 
			new Vector(-1, -1, -1), 
			new Vector(1, -1, 1)};

		Vector[] axis = new Vector[] { 
			new Vector(-1, 0, -1), 
			new Vector(0, -1, 1), 
			new Vector(1, 0, -1), 
			new Vector(0, 1, 1)};

		for(int i=0; i<4; i++)	{
			Vector axis1 = Vector.normalized(axisZ[i]);
			Vector axis2 = Vector.normalized(axis[i]);
			Vector axis3 = Vector.normalized(Vector.mult(axis[(i+3)%4], -1));

			float length = ((float) Math.sqrt(2))/n;
			Vector origin = Vector.add(Vector.mult(axis1, ((float) Math.sqrt(1f/12)) + 0.1f), Vector.add(Vector.mult(axis2, -((float) Math.sqrt(2)/3)), 
				Vector.mult(axis3, -((float) Math.sqrt(2)/3))));
			for (int u = 0; u <= n; u++) { 
				for (int v = 0; v <= n-u; v++) {
					
					Vector p = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, v*length)));
					p.normalize();

					if (u == 0 || v == 0 || u+v == n) {
						vertices.add(new Coordinates.Vertex(p, Coordinates.colours[6]));
					}else {
						vertices.add(new Coordinates.Vertex(p, Coordinates.colours[i]));
					}

				}
			}
		}
		return vertices;
	}

	public static ArrayList<Coordinates.Edge> getCutEdges(int n)	{
		ArrayList<Coordinates.Edge> edges = new ArrayList();
		Vector[] axisZ = new Vector[] {
			new Vector(1, 1, -1), 
			new Vector(-1, 1, 1), 
			new Vector(-1, -1, -1), 
			new Vector(1, -1, 1)};

		Vector[] axis = new Vector[] { 
			new Vector(-1, 0, -1), 
			new Vector(0, -1, 1), 
			new Vector(1, 0, -1), 
			new Vector(0, 1, 1)};

		for(int i=0; i<4; i++)	{
			Vector axis1 = Vector.normalized(axisZ[i]);
			Vector axis2 = Vector.normalized(axis[i]);
			Vector axis3 = Vector.normalized(Vector.mult(axis[(i+3)%4], -1));

			float length = ((float) Math.sqrt(2))/n;
			Vector origin = Vector.add(Vector.mult(axis1, ((float) Math.sqrt(1f/12)) + 0.05f), Vector.add(Vector.mult(axis2, -((float) Math.sqrt(2)/3)), 
				Vector.mult(axis3, -((float) Math.sqrt(2)/3))));
			for (int u = 0; u < n; u++) { 
				for (int v = 0; v < n-u; v++) {
					
					Vector p1 = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, v*length)));
					p1.normalize();
					Vector p2 = Vector.add(origin, Vector.add(Vector.mult(axis2, (u+1)*length), Vector.mult(axis3, v*length)));
					p2.normalize();
					Vector p3 = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, (v+1)*length)));
					p3.normalize();

					if (v == 0) {
						edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[6]));
					}else {
						edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[i]));
					}
					if (u == 0) {
						edges.add(new Coordinates.Edge(p3, p1, Coordinates.colours[6]));
					}else {
						edges.add(new Coordinates.Edge(p3, p1, Coordinates.colours[i]));
					}
					if (u + v == n-1) {
						edges.add(new Coordinates.Edge(p2, p3, Coordinates.colours[6]));
					}else {
						edges.add(new Coordinates.Edge(p2, p3, Coordinates.colours[i]));
					}
					
				}
			}
		}
		return edges;
	}

}