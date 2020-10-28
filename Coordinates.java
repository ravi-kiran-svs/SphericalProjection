import java.util.ArrayList;

class Coordinates	{

	static Colour[] colours = new Colour[] {
		new Colour(255, 25, 25),	//Red
		new Colour(25, 255, 25),	//Green
		new Colour(25, 25, 255),	//Blue

		new Colour(25, 255, 255),	//Cyan
		new Colour(255, 25, 255),	//Magenta
		new Colour(255, 255, 25),	//Yellow

		new Colour(255, 125, 25),	//Orange
		new Colour(125, 255, 25),	//Yellow - Green
		new Colour(25, 255, 125),	//Cyan - Green
		new Colour(25, 125, 225),	//Cyan - Blue
		new Colour(125, 25, 255),	//Purple
		new Colour(255, 25, 125),	//Magenta - Red

		new Colour(255, 125, 125),	//Soft Red
		new Colour(125, 255, 125),	//Soft Green
		new Colour(125, 125, 255),	//Soft Blue

		new Colour(255, 255, 255)};	//White

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