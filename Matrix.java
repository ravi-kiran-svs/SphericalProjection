import java.lang.Math;

class Matrix{

	public static float det33(float[][] M)	{
		float det = 0;
		for(int i=0; i<3; i++)	{
			det +=M[0][i]*(M[1][(i+1)%3]*M[2][(i+2)%3] - M[1][(i+2)%3]*M[2][(i+1)%3]);
		}
		return det;
	}

	public static float[][] inverse33(float[][] M)	{
		float[][] invM = new float[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		float det = det33(M);
		for(int i=0; i<3; i++)	{
			for(int j=0; j<3; j++)	{
				invM[j][i] = (M[(i+1)%3][(j+1)%3]*M[(i+2)%3][(j+2)%3] - 
					M[(i+1)%3][(j+2)%3]*M[(i+2)%3][(j+1)%3])/det;
			}
		}
		return invM;
	}

	public static Vector vXm33(float[][] M, Vector p)	{
		Vector v = new Vector(0, 0, 0);
	    v.x = M[0][0]*p.x + M[0][1]*p.y + M[0][2]*p.z;
	    v.y = M[1][0]*p.x + M[1][1]*p.y + M[1][2]*p.z;
	    v.z = M[2][0]*p.x + M[2][1]*p.y + M[2][2]*p.z;
	    return v;
	}

	public static float[][] getTransformationalMatrixXYZ(Vector x, Vector y, Vector z)	{
		float[][] M = new float[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
	    M[0][0] = x.x;
	    M[0][1] = x.y;
	    M[0][2] = x.z;

	    M[1][0] = y.x;
	    M[1][1] = y.y;
	    M[1][2] = y.z;

	    M[2][0] = z.x;
	    M[2][1] = z.y;
	    M[2][2] = z.z;

	    return M;
	}

	public static float[][] getRotationalMatrixAboutZ(float theta)	{
		float[][] M = new float[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
	    M[0][0] = (float) Math.cos(theta);
	    M[0][1] = (float) -Math.sin(theta);
	    M[0][2] = 0;

	    M[1][0] = (float) Math.sin(theta);
	    M[1][1] = (float) Math.cos(theta);
	    M[1][2] = 0;

	    M[2][0] = 0;
	    M[2][1] = 0;
	    M[2][2] = 1;

	    return M;
	}

}