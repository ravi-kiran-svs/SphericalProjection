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

}