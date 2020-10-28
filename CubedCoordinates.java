import java.util.ArrayList;

class CubedCoordinates	{

	public static ArrayList<Coordinates.Vertex> getVertices(int n)	{
		ArrayList<Coordinates.Vertex> vertices = new ArrayList();
		Vector[] axis = new Vector[] {
			new Vector(1, 0, 0), 
			new Vector(0, 1, 0), 
			new Vector(0, 0, 1), 
			new Vector(-1, 0, 0), 
			new Vector(0, -1, 0), 
			new Vector(0, 0, -1)};

		for(int i=0; i<6; i++)	{
			Vector axis1 = axis[(i+0)%6];
			Vector axis2 = axis[(i+1)%6];
			Vector axis3 = axis[(i+2)%6];

			float length = 1f/n;
			Vector origin = Vector.add(Vector.mult(axis1, 0.5f), Vector.add(Vector.mult(axis2, -0.5f), Vector.mult(axis3, -0.5f)));
			for (int u = 0; u <= n; u++) {
				for (int v = 0; v <= n; v++) {
					
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
						if (u == 0 || v == 0 || u == n || v == n) {
							vertices.add(new Coordinates.Vertex(p, Coordinates.colours[Coordinates.colours.length - 1]));
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
		Vector[] axis = new Vector[] {
			new Vector(1, 0, 0), 
			new Vector(0, 1, 0), 
			new Vector(0, 0, 1), 
			new Vector(-1, 0, 0), 
			new Vector(0, -1, 0), 
			new Vector(0, 0, -1)};

		for(int i=0; i<6; i++)	{
			Vector axis1 = axis[(i+0)%6];
			Vector axis2 = axis[(i+1)%6];
			Vector axis3 = axis[(i+2)%6];

			float length = (1f/n);
			Vector origin = Vector.add(Vector.mult(axis1, 0.5f), Vector.add(Vector.mult(axis2, -0.5f), Vector.mult(axis3, -0.5f)));
			for (int u = 0; u <= n; u++) {
				for (int v = 0; v < n; v++) {

					Vector p1 = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, v*length)));
					p1.normalize();
					Vector p2 = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, (v+1)*length)));
					p2.normalize();

					Vector p3 = Vector.add(origin, Vector.add(Vector.mult(axis2, v*length), Vector.mult(axis3, u*length)));
					p3.normalize();
					Vector p4 = Vector.add(origin, Vector.add(Vector.mult(axis2, (v+1)*length), Vector.mult(axis3, u*length)));
					p4.normalize();

					boolean contains = false;
					for(Coordinates.Edge edge : edges)	{
						if ((edge.v1.appxEquals(p1) && edge.v2.appxEquals(p2)) || (edge.v1.appxEquals(p2) && edge.v2.appxEquals(p1))) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						if (u == 0 || u == n) {
							edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[Coordinates.colours.length - 1]));
						}else {
							edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[i]));
						}
					}

					contains = false;
					for(Coordinates.Edge edge : edges)	{
						if ((edge.v1.appxEquals(p3) && edge.v2.appxEquals(p4)) || (edge.v1.appxEquals(p4) && edge.v2.appxEquals(p3))) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						if (u == 0 || u == n) {
							edges.add(new Coordinates.Edge(p3, p4, Coordinates.colours[Coordinates.colours.length - 1]));
						}else {
							edges.add(new Coordinates.Edge(p3, p4, Coordinates.colours[i]));
						}
					}

				}
			}
		}
		return edges;
	}

	public static ArrayList<Coordinates.Vertex> getCutVertices(int n)	{
		ArrayList<Coordinates.Vertex> vertices = new ArrayList();
		Vector[] axis = new Vector[] {
			new Vector(1, 0, 0), 
			new Vector(0, 1, 0), 
			new Vector(0, 0, 1), 
			new Vector(-1, 0, 0), 
			new Vector(0, -1, 0), 
			new Vector(0, 0, -1)};

		for(int i=0; i<6; i++)	{
			Vector axis1 = axis[(i+0)%6];
			Vector axis2 = axis[(i+1)%6];
			Vector axis3 = axis[(i+2)%6];

			float length = 1f/n;
			Vector origin = Vector.add(Vector.mult(axis1, 0.6f), Vector.add(Vector.mult(axis2, -0.5f), Vector.mult(axis3, -0.5f)));
			for (int u = 0; u <= n; u++) {
				for (int v = 0; v <= n; v++) {
					
					Vector p = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, v*length)));
					p.normalize();

					if (u == 0 || v == 0 || u == n || v == n) {
						vertices.add(new Coordinates.Vertex(p, Coordinates.colours[Coordinates.colours.length - 1]));
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
		Vector[] axis = new Vector[] {
			new Vector(1, 0, 0), 
			new Vector(0, 1, 0), 
			new Vector(0, 0, 1), 
			new Vector(-1, 0, 0), 
			new Vector(0, -1, 0), 
			new Vector(0, 0, -1)};

		for(int i=0; i<6; i++)	{
			Vector axis1 = axis[(i+0)%6];
			Vector axis2 = axis[(i+1)%6];
			Vector axis3 = axis[(i+2)%6];

			float length = (1f/n);
			Vector origin = Vector.add(Vector.mult(axis1, 0.6f), Vector.add(Vector.mult(axis2, -0.5f), Vector.mult(axis3, -0.5f)));
			for (int u = 0; u <= n; u++) {
				for (int v = 0; v < n; v++) {

					Vector p1 = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, v*length)));
					p1.normalize();
					Vector p2 = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, (v+1)*length)));
					p2.normalize();

					Vector p3 = Vector.add(origin, Vector.add(Vector.mult(axis2, v*length), Vector.mult(axis3, u*length)));
					p3.normalize();
					Vector p4 = Vector.add(origin, Vector.add(Vector.mult(axis2, (v+1)*length), Vector.mult(axis3, u*length)));
					p4.normalize();

					if (u == 0 || u == n) {
						edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[Coordinates.colours.length - 1]));
					}else {
						edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[i]));
					}

					if (u == 0 || u == n) {
						edges.add(new Coordinates.Edge(p3, p4, Coordinates.colours[Coordinates.colours.length - 1]));
					}else {
						edges.add(new Coordinates.Edge(p3, p4, Coordinates.colours[i]));
					}

				}
			}
		}
		return edges;
	}

}