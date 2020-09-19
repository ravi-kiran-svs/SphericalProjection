import java.util.ArrayList;

class Coordinates	{

	static Colour[] colours = new Colour[] {
		new Colour(255, 125, 125), 
		new Colour(125, 255, 125), 
		new Colour(125, 125, 255), 
		new Colour(0, 255, 255), 
		new Colour(255, 0, 255), 
		new Colour(255, 255, 0), 
		new Colour(255, 255, 255)};

	static class Colour{
		int r, g, b;
		public Colour(int r, int g, int b)	{
			this.r = r;
			this.g = g;
			this.b = b;
		}
	}

	static class Vertex{
		Vector v;
		Colour c;

		public Vertex(Vector v, Colour c)	{
			this.v = v;
			this.c = c;
		}

		public boolean equals(Vertex vertex) {
			return v.equals(vertex.v);
		}
	}

	static class Edge{
		Vector v1, v2;
		Colour c;

		public Edge(Vector v1, Vector v2, Colour c)	{
			this.v1 = v1;
			this.v2 = v2;
			this.c = c;
		}

		public boolean equals(Edge e) {
			return (v1.equals(e.v1) && v2.equals(e.v2)) || (v1.equals(e.v2) && v2.equals(e.v1));
		}
	}

	public static ArrayList<Vertex> getVertices(int n)	{
		ArrayList<Vertex> vertices = new ArrayList();
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
			for(int u = -n; u <= n; u++)	{
				for(int v = -n; v <= n; v++)	{

					Vector p = Vector.add(axis1, Vector.add(Vector.mult(axis2, (float) u/n), Vector.mult(axis3, (float) v/n)));
					p.normalize();

					boolean contains = false;
					for(Vertex vertex : vertices)	{
						if (vertex.v.equals(p)) {
							contains = true;
							break;
						}
					}

					if (!contains) {
						if (u == -n || v == -n || u == n || v == n) {
							vertices.add(new Vertex(p, colours[6]));
						}else {
							vertices.add(new Vertex(p, colours[i]));
						}
					}

				}
			}
		}
		return vertices;
	}

	public static ArrayList<Edge> getEdges(int n)	{
		ArrayList<Edge> edges = new ArrayList();
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
			for(int u = -n; u <= n; u++)	{
				for(int v = -n; v < n; v++)	{

					Vector p1 = Vector.add(axis1, Vector.add(Vector.mult(axis2, (float) u/n), Vector.mult(axis3, (float) v/n)));
					p1.normalize();
					Vector p2 = Vector.add(axis1, Vector.add(Vector.mult(axis2, (float) u/n), Vector.mult(axis3, (float) (v+1)/n)));
					p2.normalize();

					Vector p3 = Vector.add(axis1, Vector.add(Vector.mult(axis2, (float) v/n), Vector.mult(axis3, (float) u/n)));
					p3.normalize();
					Vector p4 = Vector.add(axis1, Vector.add(Vector.mult(axis2, (float) (v+1)/n), Vector.mult(axis3, (float) u/n)));
					p4.normalize();

					boolean contains = false;
					for(Edge edge : edges)	{
						if ((edge.v1.equals(p1) && edge.v2.equals(p2)) || (edge.v1.equals(p2) && edge.v2.equals(p1))) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						if (u == -n || u == n) {
							edges.add(new Edge(p1, p2, colours[6]));
						}else {
							edges.add(new Edge(p1, p2, colours[i]));
						}
					}

					contains = false;
					for(Edge edge : edges)	{
						if ((edge.v1.equals(p3) && edge.v2.equals(p4)) || (edge.v1.equals(p4) && edge.v2.equals(p3))) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						if (u == -n || u == n) {
							edges.add(new Edge(p3, p4, colours[6]));
						}else {
							edges.add(new Edge(p3, p4, colours[i]));
						}
					}

				}
			}
		}
		return edges;
	}

}