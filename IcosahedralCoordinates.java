import java.util.ArrayList;
import java.lang.Math;

class IcosahedralCoordinates	{

	static float phi = 1.61803f;

	static Vector[][] faces = new Vector[][] {
		{
			Vector.mult(new Vector(0, -1, phi), 0.5f), 
			Vector.mult(new Vector(0, 1, phi), 0.5f), 
			Vector.mult(new Vector(-phi, 0, 1), 0.5f)
		}, 
		{
			Vector.mult(new Vector(0, 1, phi), 0.5f), 
			Vector.mult(new Vector(0, -1, phi), 0.5f), 
			Vector.mult(new Vector(phi, 0, 1), 0.5f)
		}, 

		{
			Vector.mult(new Vector(-phi, 0, 1), 0.5f), 
			Vector.mult(new Vector(0, -1, phi), 0.5f), 
			Vector.mult(new Vector(-1, -phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(0, -1, phi), 0.5f), 
			Vector.mult(new Vector(phi, 0, 1), 0.5f), 
			Vector.mult(new Vector(1, -phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(0, -1, phi), 0.5f), 
			Vector.mult(new Vector(-1, -phi, 0), 0.5f), 
			Vector.mult(new Vector(1, -phi, 0), 0.5f)
		}, 
		
		{
			Vector.mult(new Vector(0, 1, phi), 0.5f), 
			Vector.mult(new Vector(phi, 0, 1), 0.5f), 
			Vector.mult(new Vector(1, phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(-phi, 0, 1), 0.5f), 
			Vector.mult(new Vector(0, 1, phi), 0.5f), 
			Vector.mult(new Vector(-1, phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(0, 1, phi), 0.5f), 
			Vector.mult(new Vector(1, phi, 0), 0.5f), 
			Vector.mult(new Vector(-1, phi, 0), 0.5f)
		}, 

		{
			Vector.mult(new Vector(0, 1, -phi), 0.5f), 
			Vector.mult(new Vector(0, -1, -phi), 0.5f), 
			Vector.mult(new Vector(-phi, 0, -1), 0.5f)
		}, 
		{
			Vector.mult(new Vector(0, -1, -phi), 0.5f), 
			Vector.mult(new Vector(0, 1, -phi), 0.5f), 
			Vector.mult(new Vector(phi, 0, -1), 0.5f)
		}, 

		{
			Vector.mult(new Vector(0, -1, -phi), 0.5f), 
			Vector.mult(new Vector(-phi, 0, -1), 0.5f), 
			Vector.mult(new Vector(-1, -phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(phi, 0, -1), 0.5f), 
			Vector.mult(new Vector(0, -1, -phi), 0.5f), 
			Vector.mult(new Vector(1, -phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(-1, -phi, 0), 0.5f), 
			Vector.mult(new Vector(0, -1, -phi), 0.5f), 
			Vector.mult(new Vector(1, -phi, 0), 0.5f)
		}, 
		
		{
			Vector.mult(new Vector(phi, 0, -1), 0.5f), 
			Vector.mult(new Vector(0, 1, -phi), 0.5f), 
			Vector.mult(new Vector(1, phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(0, 1, -phi), 0.5f), 
			Vector.mult(new Vector(-phi, 0, -1), 0.5f), 
			Vector.mult(new Vector(-1, phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(1, phi, 0), 0.5f), 
			Vector.mult(new Vector(0, 1, -phi), 0.5f), 
			Vector.mult(new Vector(-1, phi, 0), 0.5f)
		}, 

		{
			Vector.mult(new Vector(phi, 0, 1), 0.5f), 
			Vector.mult(new Vector(phi, 0, -1), 0.5f), 
			Vector.mult(new Vector(1, phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(phi, 0, -1), 0.5f), 
			Vector.mult(new Vector(phi, 0, 1), 0.5f), 
			Vector.mult(new Vector(1, -phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(-phi, 0, -1), 0.5f), 
			Vector.mult(new Vector(-phi, 0, 1), 0.5f), 
			Vector.mult(new Vector(-1, phi, 0), 0.5f)
		}, 
		{
			Vector.mult(new Vector(-phi, 0, 1), 0.5f), 
			Vector.mult(new Vector(-phi, 0, -1), 0.5f), 
			Vector.mult(new Vector(-1, -phi, 0), 0.5f)
		}
	};

	public static ArrayList<Coordinates.Vertex> getVertices(int n)	{
		ArrayList<Coordinates.Vertex> vertices = new ArrayList();

		for(int i=0; i<20; i++)	{
			Vector[] face = faces[i];
			Vector centroid = Vector.add(Vector.add(face[0], face[1]), face[2]);
			centroid.normalize();

			Vector origin = face[0];
			Vector axis2 = Vector.sub(face[1], face[0]);
			Vector axis3 = Vector.sub(face[2], face[0]);
			float length = axis2.mag()/n;

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

		for(int i=0; i<20; i++)	{
			Vector[] face = faces[i];
			Vector centroid = Vector.add(Vector.add(face[0], face[1]), face[2]);
			centroid.normalize();

			Vector origin = face[0];
			Vector axis2 = Vector.sub(face[1], face[0]);
			Vector axis3 = Vector.sub(face[2], face[0]);
			float length = axis2.mag()/n;

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
							edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[Coordinates.colours.length - 1]));
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
							edges.add(new Coordinates.Edge(p3, p1, Coordinates.colours[Coordinates.colours.length - 1]));
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
							edges.add(new Coordinates.Edge(p2, p3, Coordinates.colours[Coordinates.colours.length - 1]));
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

		for(int i=0; i<20; i++)	{
			Vector[] face = faces[i];
			Vector centroid = Vector.add(Vector.add(face[0], face[1]), face[2]);
			centroid.normalize();
			Vector[] cutVertices = new Vector[] {
				Vector.add(face[0], Vector.mult(centroid, 0.2f)), 
				Vector.add(face[1], Vector.mult(centroid, 0.2f)), 
				Vector.add(face[2], Vector.mult(centroid, 0.2f)), 
			};

			Vector origin = cutVertices[0];
			Vector axis2 = Vector.sub(cutVertices[1], cutVertices[0]);
			Vector axis3 = Vector.sub(cutVertices[2], cutVertices[0]);
			float length = axis2.mag()/n;

			for (int u = 0; u <= n; u++) { 
				for (int v = 0; v <= n-u; v++) {
					Vector p = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, v*length)));
					p.normalize();

					if (u == 0 || v == 0 || u+v == n) {
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

		for(int i=0; i<20; i++)	{
			Vector[] face = faces[i];
			Vector centroid = Vector.add(Vector.add(face[0], face[1]), face[2]);
			centroid.normalize();

			Vector[] cutVertices = new Vector[] {
				Vector.add(face[0], Vector.mult(centroid, 0.1f)), 
				Vector.add(face[1], Vector.mult(centroid, 0.1f)), 
				Vector.add(face[2], Vector.mult(centroid, 0.1f)), 
			};

			Vector origin = cutVertices[0];
			Vector axis2 = Vector.sub(cutVertices[1], cutVertices[0]);
			Vector axis3 = Vector.sub(cutVertices[2], cutVertices[0]);
			float length = axis2.mag()/n;

			for (int u = 0; u < n; u++) { 
				for (int v = 0; v < n-u; v++) {
					Vector p1 = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, v*length)));
					p1.normalize();
					Vector p2 = Vector.add(origin, Vector.add(Vector.mult(axis2, (u+1)*length), Vector.mult(axis3, v*length)));
					p2.normalize();
					Vector p3 = Vector.add(origin, Vector.add(Vector.mult(axis2, u*length), Vector.mult(axis3, (v+1)*length)));
					p3.normalize();

					if (v == 0) {
						edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[Coordinates.colours.length - 1]));
					}else {
						edges.add(new Coordinates.Edge(p1, p2, Coordinates.colours[i]));
					}
					if (u == 0) {
						edges.add(new Coordinates.Edge(p3, p1, Coordinates.colours[Coordinates.colours.length - 1]));
					}else {
						edges.add(new Coordinates.Edge(p3, p1, Coordinates.colours[i]));
					}
					if (u + v == n-1) {
						edges.add(new Coordinates.Edge(p2, p3, Coordinates.colours[Coordinates.colours.length - 1]));
					}else {
						edges.add(new Coordinates.Edge(p2, p3, Coordinates.colours[i]));
					}
				}
			}
		}
		return edges;
	}

}