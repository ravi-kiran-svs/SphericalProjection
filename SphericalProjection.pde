import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

Vector[] origin = {new Vector(300, 300, 0), new Vector(900, 300, 0)};
int[] scale = {180, 90};

Vector cX, cY, cZ;

//order -> UP, RIGHT, DOWN, LEFT
boolean[] isKeyOn = new boolean[] {false, false, false, false};

ArrayList<Coordinates.Vertex> vertices;
ArrayList<Coordinates.Edge> edges;

void setup()	{
	size(1200, 600);
	//noSmooth();

	cX = new Vector(1, 0, 0);
	cY = new Vector(0, 1, 0);
	cZ = new Vector(0, 0, 1);

	vertices = Coordinates.getVertices(4);
	edges = Coordinates.getEdges(4);
}

void draw()	{

	int x = (isKeyOn[1]? 1: 0) - (isKeyOn[3]? 1: 0);
	int y = (isKeyOn[0]? 1: 0) - (isKeyOn[2]? 1: 0);
	Vector input = new Vector(x, y, 0);
	input.normalize();
	input.into(0.01);
	Vector inputX = new Vector(input.x, 0, 0);
	Vector inputY = new Vector(0, input.y, 0);

	float[][] M = Matrix.inverse33(Matrix.getTransformationalMatrixXYZ(cX, cY, cZ));
	if (input.x != 0) {
		inputX = Matrix.vXm33(inputX, M);
		cZ.plus(inputX);
		cZ.normalize();
		cX = Vector.cross(cY, cZ);
	}
	if (input.y != 0) {
		inputY = Matrix.vXm33(input, M);
		cZ.plus(inputY);
		cZ.normalize();
		cY = Vector.cross(cZ, cX);
	}

    M = Matrix.getTransformationalMatrixXYZ(cX, cY, cZ);
    /*ArrayList<Coordinates.Vertex> verticesTransformed = new ArrayList();
    ArrayList<Coordinates.Vertex> verticesProjected = new ArrayList();
    for (Coordinates.Vertex vertex : vertices) {
    	Vector v = Matrix.vXm33(vertex.v, M);
    	if (v.z >= -0.2) {
    		verticesTransformed.add(new Coordinates.Vertex(v, vertex.c));
    	}

    	Vector v1 = v.copy();
    	float phi = acos(v1.z);
    	if (phi < 2.9) {
	    	v1.z = 0;
	    	v1.normalize();
	    	v1.into(phi);
	    	verticesProjected.add(new Coordinates.Vertex(v1, vertex.c));
    	}
    }
    Collections.sort(verticesTransformed, new Comparator<Coordinates.Vertex>()	{
    	public int compare(Object v1, Object v2)	{
    		return (int) (v1.v.z - v2.v.z);
    	}
    });*/

    ArrayList<Coordinates.Edge> edgesTransformed = new ArrayList();
    ArrayList<Coordinates.Edge> edgesProjected = new ArrayList();
    for (Coordinates.Edge edge : edges) {
    	Vector v1 = Matrix.vXm33(edge.v1, M);
    	Vector v2 = Matrix.vXm33(edge.v2, M);
    	if (v1.z + v2.z >= -0.2) {
    		edgesTransformed.add(new Coordinates.Edge(v1, v2, edge.c));
    	}

    	Vector v3 = v1.copy();
    	Vector v4 = v2.copy();
    	float phi3 = acos(v3.z);
    	float phi4 = acos(v4.z);
    	if (phi3 < 2.9 && phi4 < 2.9) {
	    	v3.z = 0;
	    	v3.normalize();
	    	v3.into(phi3);
	    	v4.z = 0;
	    	v4.normalize();
	    	v4.into(phi4);
	    	edgesProjected.add(new Coordinates.Edge(v3, v4, edge.c));
    	}
    }
    /*Collections.sort(edgesTransformed, new Comparator<Coordinates.Edge>()	{
    	public int compare(Coordinates.Edge e1, Coordinates.Edge e2)	{
    		int z1 = (int) (e1.v1.z + e1.v2.z);
    		int z2 = (int) (e2.v1.z + e2.v2.z);
    		return z1 - z2;
    	}
    });*/

	background(0, 0, 0);
	for(Coordinates.Edge edge : edgesTransformed)	{
		stroke(color(edge.c.r, edge.c.g, edge.c.b));
		strokeWeight(2);
		line(origin[0].x + scale[0]*edge.v1.x, origin[0].y - scale[0]*edge.v1.y, 
			origin[0].x + scale[0]*edge.v2.x, origin[0].y - scale[0]*edge.v2.y);
	}
	/*for(Coordinates.Vertex vertex : verticesTransformed)	{
		stroke(color(vertex.c.r, vertex.c.g, vertex.c.b));
		strokeWeight(5);
		point(origin[0].x + scale[0]*vertex.v.x, origin[0].y - scale[0]*vertex.v.y);
	}*/
	for(Coordinates.Edge edge : edgesProjected)	{
		if (edge.v1.mag() + edge.v2.mag() > 5) {
			stroke(color(edge.c.r/4, edge.c.g/4, edge.c.b/4));
		}else {
			stroke(color(edge.c.r, edge.c.g, edge.c.b));
		}
		if (edge.v1.mag() + edge.v2.mag() > 4.5) {
			strokeWeight(1);
		}else {
			strokeWeight(2);
		}
		line(origin[1].x + scale[1]*edge.v1.x, origin[1].y - scale[1]*edge.v1.y, 
			origin[1].x + scale[1]*edge.v2.x, origin[1].y - scale[1]*edge.v2.y);
	}
	/*for(Coordinates.Vertex vertex : verticesProjected)	{
		stroke(color(vertex.c.r, vertex.c.g, vertex.c.b));
		strokeWeight(5);
		point(origin[1].x + scale[1]*vertex.v.x, origin[1].y - scale[1]*vertex.v.y);
	}*/

	println(frameRate);

}

void keyPressed()	{
	if (keyCode == UP) {
		isKeyOn[0] = true;
	}
	if (keyCode == RIGHT) {
		isKeyOn[1] = true;
	}
	if (keyCode == DOWN) {
		isKeyOn[2] = true;
	}
	if (keyCode == LEFT) {
		isKeyOn[3] = true;
	}
}

void keyReleased()	{
	if (keyCode == UP) {
		isKeyOn[0] = false;
	}
	if (keyCode == RIGHT) {
		isKeyOn[1] = false;
	}
	if (keyCode == DOWN) {
		isKeyOn[2] = false;
	}
	if (keyCode == LEFT) {
		isKeyOn[3] = false;
	}
}